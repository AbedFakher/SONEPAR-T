package com.biskot.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biskot.domain.exception.NotFoundException;
import com.biskot.domain.model.Cart;
import com.biskot.domain.model.Product;
import com.biskot.domain.spi.CartPersistencePort;
import com.biskot.domain.spi.ProductPort;

@Service
public class CartServiceImpl implements CartService {
	
	
	@Autowired
    private ProductPort productPort;
	
	@Autowired
    private CartPersistencePort cartPersistencePort;
	
	
    @Override
    public void createCart() {
    	cartPersistencePort.saveCart(new Cart().init());
    }

    @Override
    public Cart getCart(long cartId) {
    	Cart cart = cartPersistencePort.getCart(cartId)
    			.orElseThrow(() -> new NotFoundException("Cart.NotFound",cartId));
		cart.calculatePrice();
    	return cart;
    }

    @Override
    public void addItemToCart(long cartId, long productId, int quantityToAdd) {
    	Product product = Optional.ofNullable(productPort.getProduct(productId))
    			.orElseThrow(() -> new NotFoundException("Product.NotFound",productId));    	
    	Cart cart = cartPersistencePort.getCart(cartId)
    			.orElseThrow(() -> new NotFoundException("Cart.NotFound",cartId));
    	cart.addToCart(product, quantityToAdd);
    	cartPersistencePort.saveCart(cart);
    }
    
    
}
