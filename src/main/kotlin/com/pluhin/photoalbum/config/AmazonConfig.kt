package com.pluhin.photoalbum.config

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AmazonConfig {

  @Bean
  fun s3Client() = AmazonS3ClientBuilder.defaultClient()
}