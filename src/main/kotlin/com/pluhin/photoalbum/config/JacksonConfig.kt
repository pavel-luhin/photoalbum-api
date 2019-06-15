package com.pluhin.photoalbum.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.context.annotation.Bean

open class JacksonConfig {

  @Bean
  fun jackson() = ObjectMapper().registerModule(KotlinModule())
}