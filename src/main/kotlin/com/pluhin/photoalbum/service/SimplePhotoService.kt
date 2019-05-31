package com.pluhin.photoalbum.service

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.S3ObjectSummary
import com.pluhin.photoalbum.entity.AlbumEntity
import com.pluhin.photoalbum.model.Photo
import com.pluhin.photoalbum.repository.AlbumRepository
import com.pluhin.photoalbum.repository.PhotoRepository
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.util.function.component1
import reactor.util.function.component2
import java.util.*

@Service
class SimplePhotoService(
    private val s3Client: AmazonS3,
    private val photoRepository: PhotoRepository,
    private val albumRepository: AlbumRepository
) : PhotoService {

  override fun createAlbum(name: String): Mono<Void> {
    return Mono.fromCallable {
      val entity = AlbumEntity(0, name)
      albumRepository.save(entity)
      s3Client.createBucket(name)
    }
        .then()
  }

  override fun getAlbumPhotos(albumId: Long, skip: Int): Flux<Photo> {
    return getAlbum(albumId)
        .map {
          s3Client.listObjects(it.name)
        }
        .map {
          val listing = it.objectSummaries
          if (listing.size > skip) {
            return@map listing.subList(skip, listing.size)
          } else {
            return@map emptyList<S3ObjectSummary>()
          }
        }
        .flatMapIterable { it }
        .map {
          val photo = photoRepository.findByName(it.key)
          Photo(photo.id, photo.fileName, photo.url, photo.url)
        }
  }

  override fun uploadPhotos(albumId: Long, photos: List<MultipartFile>): Mono<Void> {
    return Flux.fromIterable(photos)
        .zipWith(getAlbum(albumId))
        .map { (photo, album) ->
          val metadata = ObjectMetadata()
          metadata.contentLength = photo.size
          metadata.contentType = photo.contentType

          val name = UUID.randomUUID().toString()
          s3Client.putObject(album.name, name, photo.inputStream, metadata)
        }
        .then()
  }

  private fun getAlbum(albumId: Long): Mono<AlbumEntity> {
    return albumRepository.findById(albumId)
        .map { Mono.just(it) }
        .orElseGet { Mono.empty() }
  }
}