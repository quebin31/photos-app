package co.ravn.kevin.photosapp.ui.photos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.ravn.kevin.photosapp.databinding.ListItemPhotoBinding
import co.ravn.kevin.photosapp.model.Photo
import co.ravn.kevin.photosapp.utils.getMockUrlImage
import com.bumptech.glide.Glide


typealias OnClickCallback = (Photo) -> Unit

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
        title.text = photo.title
        Glide.with(itemView).load(photo.getMockUrlImage()).into(thumbnail)

        root.setOnClickListener { onClick(photo) }
    }
}