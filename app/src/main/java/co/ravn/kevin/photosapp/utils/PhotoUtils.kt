package co.ravn.kevin.photosapp.utils

import co.ravn.kevin.photosapp.model.Photo

fun Photo.getMockUrlImage(): String = "https://picsum.photos/id/$id/600"