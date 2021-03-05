package co.ravn.kevin.photosapp.utils

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}


@BindingAdapter("imageUrl", "transitionIsPostponed")
fun loadImageLoading(imageView: ImageView, url: String, transitionIsPostponed: Boolean) {
    val circularProgressDrawable = CircularProgressDrawable(imageView.context)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()

    // This may be right now, but not all ImageViews are inside a fragment.
    val fragment by lazy {  imageView.findFragment<Fragment>() }
    val maybeStartTransition = {
        if (transitionIsPostponed)
            fragment.startPostponedEnterTransition()
    }

    GlideApp
        .with(imageView)
        .load(url)
        .placeholder(circularProgressDrawable)
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                maybeStartTransition()
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                maybeStartTransition()
                return false
            }
        })
        .into(imageView)
}