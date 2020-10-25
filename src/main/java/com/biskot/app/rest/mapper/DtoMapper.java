package com.biskot.app.rest.mapper;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.biskot.app.contract.model.CartResponse;
import com.biskot.app.contract.model.ItemResponse;
import com.biskot.domain.model.Cart;
import com.biskot.domain.model.Item;

@Component
public class DtoMapper {

	public CartResponse toCartResponse(Cart cart) {
		CartResponse response = new CartResponse();
		response.setId(cart.getCartId());
		response.setTotalPrice(cart.getTotalPrice());
		response.setItems(cart.getItems().stream()
		.map(this::mapToItemResponse)
		.collect(Collectors.toList()));
		return response;
		
	}

	private ItemResponse mapToItemResponse(Item item) {
		ItemResponse itemResponse  = new ItemResponse();
		itemResponse.setProductId(item.getProduct().getId());
		itemResponse.setProductLabel(item.getProduct().getProductLabel());
		itemResponse.setQuantity(item.getQuantity());
		itemResponse.setUnitPrice(item.getProduct().getUnitPrice());
		itemResponse.setLinePrice(item.getLinePrice());
		return itemResponse;
	}
}
