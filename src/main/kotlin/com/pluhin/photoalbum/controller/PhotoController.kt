package com.pluhin.photoalbum.controller

import com.pluhin.photoalbum.model.CreateAlbumRequest
import com.pluhin.photoalbum.service.PhotoService
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
class PhotoController(private val photoService: PhotoService) {

  @PostMapping("/album/{albumId}/upload")
  fun uploadImages(
      @PathVariable albumId: Long,
      photos: List<MultipartFile>): Mono<ServerResponse> {
    return photoService.uploadPhotos(albumId, photos)
        .flatMap { ServerResponse.noContent().build() }
  }

  @GetMapping("/album/{albumId}/photos")
  fun getAlbumPhotos(
      @PathVariable albumId: Long,
      @RequestParam skip: Int
  ): Flux<ServerResponse> {
    return photoService.getAlbumPhotos(albumId, skip)
        .flatMap { ServerResponse.ok().body(BodyInserters.fromObject(it)) }
  }

  @PostMapping("/album")
  fun createAlbum(@RequestBody request: CreateAlbumRequest): Mono<ServerResponse> {
    return photoService.createAlbum(request.name)
        .flatMap { ServerResponse.noContent().build() }
  }
}