package com.gibhub.dreamshops.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gibhub.dreamshops.models.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    void deleteAllByCartId(Long id);

}
