package co.ravn.kevin.photosapp.ui.photos

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import co.ravn.kevin.photosapp.databinding.FragmentPhotosBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotosFragment : Fragment() {

    private var _binding: FragmentPhotosBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<PhotosViewModel>()

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
        viewModel.photos.observe(viewLifecycleOwner) {
            Log.d(TAG, "onViewCreated: photos = $it")
        }
    }

    companion object {
        private const val TAG = "PhotosFragment"
    }
}