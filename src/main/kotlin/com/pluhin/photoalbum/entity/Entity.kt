package com.pluhin.photoalbum.entity

data class PhotoEntity(val id: Long, val fileName: String, val url: String, val albumId: Long)

data class AlbumEntity(val id: Long, val name: String)