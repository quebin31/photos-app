package co.ravn.kevin.photosapp.utils

import android.net.Uri
import co.ravn.kevin.photosapp.model.Photo

fun Photo.getMockUrlImage(): Uri {
    return Uri.parse("https://picsum.photos/id/$id/600")
}