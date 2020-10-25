package com.biskot.app.rest;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.biskot.app.contract.api.CartApi;
import com.biskot.app.contract.model.AddItemRequest;
import com.biskot.app.contract.model.CartResponse;
import com.biskot.app.rest.mapper.DtoMapper;
import com.biskot.domain.model.Cart;
import com.biskot.domain.service.CartService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
@RequiredArgsConstructor
public class CartController implements CartApi{
	
	private final CartService cartService;
	
	
	private final DtoMapper mapper;

	@Override
	public ResponseEntity<Void> addItemToCart(Long cartId, @Valid AddItemRequest addItemRequest) {
		log.debug("REST request to add Item to cart, cartID = {}, AddItemRequest = ", cartId, addItemRequest);
		cartService.addItemToCart(cartId, addItemRequest.getProductId(), addItemRequest.getQuantity());
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> createCart() {
		log.debug("REST request to create new cart");
		cartService.createCart();
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CartResponse> getCart(Long cartId) {
		log.debug("REST request to get cart by id : ={} ", cartId);
		Cart cart = cartService.getCart(cartId);
		CartResponse response = mapper.toCartResponse(cart);
		return ResponseEntity.ok(response);
	}
}
