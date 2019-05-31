package com.pluhin.photoalbum

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PhotoAlbumApp

fun main(args: Array<String>) {
  runApplication<PhotoAlbumApp>(*args)
}
