package com.pluhin.photoalbum.repository

import com.pluhin.photoalbum.entity.AlbumEntity
import org.springframework.data.jpa.repository.JpaRepository

interface AlbumRepository: JpaRepository<AlbumEntity, Long>