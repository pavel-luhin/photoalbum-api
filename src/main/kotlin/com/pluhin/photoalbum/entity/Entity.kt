package com.pluhin.photoalbum.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "photos")
data class PhotoEntity(
    @Id @GeneratedValue val id: Long,
    val fileName: String,
    val url: String,
    val albumId: Long
)

@Entity
@Table(name = "albums")
data class AlbumEntity(
    @Id @GeneratedValue val id: Long,
    val name: String
)