package co.ravn.kevin.photosapp.ui.photos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import co.ravn.kevin.photosapp.databinding.ListItemPhotoBinding
import co.ravn.kevin.photosapp.model.Photo
import co.ravn.kevin.photosapp.utils.GlideApp
import co.ravn.kevin.photosapp.utils.getMockUrlImage


typealias OnClickCallback = (Photo, Array<Pair<View, String>>) -> Unit

class PhotoAdapter(private val onClick: OnClickCallback) : RecyclerView.Adapter<PhotoViewHolder>() {

    private val photos = mutableListOf<Photo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemPhotoBinding.inflate(inflater, parent, false)
        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(photos[position], onClick)
    }

    override fun getItemCount(): Int = photos.size

    fun updateData(newPhotos: List<Photo>) {
        photos.clear()
        photos.addAll(newPhotos)
        notifyDataSetChanged()
    }
}

class PhotoViewHolder(private val binding: ListItemPhotoBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(photo: Photo, onClick: OnClickCallback) = with(binding) {
        val context = itemView.context
        val transitionName = "photo-${photo.id}"

        title.text = photo.title
        thumbnail.transitionName = transitionName

        val circularProgressDrawable = CircularProgressDrawable(context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        GlideApp.with(context).load(photo.getMockUrlImage()).placeholder(circularProgressDrawable).into(thumbnail)

        val imageTransition = thumbnail as View to transitionName
        root.setOnClickListener { onClick(photo, arrayOf(imageTransition)) }
    }
}