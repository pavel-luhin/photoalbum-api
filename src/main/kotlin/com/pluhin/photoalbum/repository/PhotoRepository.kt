package com.pluhin.photoalbum.repository

import com.pluhin.photoalbum.entity.PhotoEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PhotoRepository: JpaRepository<PhotoEntity, Long> {

  fun findByFileName(name: String): PhotoEntity
}