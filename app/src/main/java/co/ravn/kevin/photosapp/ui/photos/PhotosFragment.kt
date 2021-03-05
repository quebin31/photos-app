package co.ravn.kevin.photosapp.ui.photos

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(android.R.transition.fade)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        postponeEnterTransition()
        initBinding()
        initUi()
        initObservers()
    }

    private fun initBinding() = with(binding) {
        lifecycleOwner = viewLifecycleOwner
        viewModel = this@PhotosFragment.viewModel
    }

    private fun initUi() = with(binding) {
        progress.visible()

        val spacing = resources.getDimensionPixelSize(R.dimen.padding_standard)
        photosList.addItemDecoration(SpacingItemDecorator(spacing))
        photosList.adapter = adapter
        photosList.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun initObservers() {
        viewModel.photos.observe(viewLifecycleOwner) { photos ->
            adapter.updateData(photos)
            binding.progress.gone()

            binding.root.doOnPreDraw {
                startPostponedEnterTransition()
            }
        }
    }

    private fun navigateToPhotoDetail(photo: Photo, transitions: Array<Pair<View, String>>) {
        val directions = PhotosFragmentDirections.actionShowPhotoDetail(photo)
        val extras = FragmentNavigatorExtras(*transitions)
        findNavController().navigate(directions, extras)
    }
}