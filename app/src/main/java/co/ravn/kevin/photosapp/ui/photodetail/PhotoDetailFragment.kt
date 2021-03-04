package co.ravn.kevin.photosapp.ui.photodetail

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import co.ravn.kevin.photosapp.databinding.FragmentPhotoDetailBinding
import co.ravn.kevin.photosapp.utils.GlideApp
import co.ravn.kevin.photosapp.utils.getMockUrlImage
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoDetailFragment : Fragment() {

    private var _binding: FragmentPhotoDetailBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<PhotoDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhotoDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val inflater = TransitionInflater.from(requireContext())
        enterTransition = inflater.inflateTransition(android.R.transition.fade)
        sharedElementEnterTransition = inflater.inflateTransition(android.R.transition.move)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        postponeEnterTransition()
        initUi()
    }

    private fun initUi() = with(binding) {
        val context = requireContext()
        val photo = args.photo

        fullImage.transitionName = "photo-${args.photo.id}"
        title.text = photo.title

        val circularProgressDrawable = CircularProgressDrawable(context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        GlideApp
            .with(context)
            .load(photo.getMockUrlImage())
            .placeholder(circularProgressDrawable)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    startPostponedEnterTransition()
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    startPostponedEnterTransition()
                    return false
                }

            })
            .into(fullImage)
    }
}