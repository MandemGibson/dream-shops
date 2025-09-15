package com.gibhub.dreamshops.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gibhub.dreamshops.models.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
