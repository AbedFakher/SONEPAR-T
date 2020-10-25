package com.biskot.app.rest;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.biskot.app.contract.model.CartResponse;
import com.biskot.app.contract.model.ItemResponse;
import com.biskot.app.rest.mapper.DtoMapper;
import com.biskot.domain.model.Cart;
import com.biskot.domain.model.Item;
import com.biskot.domain.model.Product;
import com.biskot.domain.service.CartService;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

@ExtendWith(MockitoExtension.class)
class CartControllerTest {
	
	private CartController cartController;
	
	@Mock
    private CartService cartService;
	
	@BeforeEach
	public void initCartControllerTest() {
		cartController = new CartController(cartService, new DtoMapper());
	}
	
	@Test
	void should_get_cart() {
		Cart cart = new Cart();
		cart.setCartId(1l);
		cart.setItems(ImmutableSet.of(BuildItem(1l),BuildItem(2l)));
		cart.calculatePrice();
		Mockito.when((cartService.getCart(1l)))
        .thenReturn(cart);
		
		CartResponse cartResponse = new CartResponse();
		cartResponse.items(ImmutableList.of(BuildItemResponse(1l),BuildItemResponse(2l)));
		cartResponse.setId(1l);
		cartResponse.setTotalPrice(80.00);
		
		ResponseEntity<CartResponse> result = cartController.getCart(1l);
		
		assertThat(result.getBody()).isEqualToComparingFieldByField(cartResponse);
	
	}
	
	private ItemResponse BuildItemResponse(Long productId) {
		ItemResponse response = new ItemResponse();
		response.setProductId(productId);
		response.setProductLabel("productLabel"+productId);
		response.setUnitPrice(20.00);
		response.setQuantity(2);
		response.setLinePrice(40.00);
		return response;
	}
	
	private Item BuildItem(Long productId) {
		return Item.builder()
		.product(Product.builder()
				.id(productId)
				.productLabel("productLabel"+productId)
				.unitPrice(20.00)
				.build())
		.quantity(2)
		.build();
	}
	
	

}