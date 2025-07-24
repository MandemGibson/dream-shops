package com.gibhub.dreamshops.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gibhub.dreamshops.models.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {

}