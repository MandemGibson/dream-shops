package com.gibhub.dreamshops.service.cart;

import java.math.BigDecimal;

import com.gibhub.dreamshops.models.Cart;

public interface ICartService {
    Cart getCart(Long id);

    void clearCart(Long id);

    BigDecimal getTotalPrice(Long id);
}
