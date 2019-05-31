package com.pluhin.photoalbum.service

import com.pluhin.photoalbum.model.Photo
import org.springframework.web.multipart.MultipartFile
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface PhotoService {

  fun createAlbum(name: String): Mono<Void>

  fun uploadPhotos(albumId: Long, photos: List<MultipartFile>): Mono<Void>

  fun getAlbumPhotos(albumId: Long, skip: Int): Flux<Photo>
}