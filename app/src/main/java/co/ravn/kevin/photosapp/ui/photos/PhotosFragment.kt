package co.ravn.kevin.photosapp.ui.photos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import co.ravn.kevin.photosapp.R
import co.ravn.kevin.photosapp.databinding.FragmentPhotosBinding
import co.ravn.kevin.photosapp.model.Photo
import co.ravn.kevin.photosapp.ui.decorators.SpacingItemDecorator
import co.ravn.kevin.photosapp.utils.gone
import co.ravn.kevin.photosapp.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotosFragment : Fragment() {

    private var _binding: FragmentPhotosBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<PhotosViewModel>()

    private val adapter by lazy { PhotoAdapter(::navigateToPhotoDetail) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhotosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initUi()
        initObservers()
    }

    private fun initUi() = with(binding) {
        progress.visible()
        noData.gone()

        val spacing = resources.getDimensionPixelSize(R.dimen.padding_standard)
        photosList.addItemDecoration(SpacingItemDecorator(spacing))
        photosList.adapter = adapter
        photosList.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun initObservers() = with(binding) {
        viewModel.photos.observe(viewLifecycleOwner) { photos ->
            adapter.updateData(photos)

            progress.gone()
            if (photos.isEmpty()) {
                noData.visible()
            } else {
                noData.gone()
            }
        }
    }

    private fun navigateToPhotoDetail(photo: Photo) {
        val directions = PhotosFragmentDirections.actionShowPhotoDetail(photo)
        findNavController().navigate(directions)
    }

    companion object {
        private const val TAG = "PhotosFragment"
    }
}