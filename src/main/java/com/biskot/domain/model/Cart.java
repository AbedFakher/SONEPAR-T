package com.biskot.domain.model;

import java.time.Instant;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.biskot.domain.exception.BusinessException;

@Setter
@Getter
@NoArgsConstructor
public class Cart {
	
	private static final int MAX_CART_PRICE = 100;
	private static final int MAX_ALLOWED_ITEMS = 3;
	
	private Long cartId;
	private Instant creationTime;
	private Instant lastModifiedTime;
	private Set<Item> items;
	private Double totalPrice;
	
	public Cart init() {
		this.creationTime = Instant.now();
		this.items = new HashSet<>();
		this.totalPrice = 0.00;
		return this;
	}
	
	public void addToCart(Product product, int quantityToAdd) {
		checkStockAvailability(product, quantityToAdd);
		Optional<Item> item = findItemifExists(product.getId());
		if(item.isPresent()) {
			item.get().setProduct(product);
			item.get().setQuantity(quantityToAdd);
		}else {
			validateMaxAllowedItems();
			addItem(product,quantityToAdd);
		}
		validateTotalPrice();
		this.lastModifiedTime = Instant.now();
	}
	
	public void calculatePrice() {
		this.totalPrice = items.stream().map(Item::calculateLinePrice)
				.reduce(0.0, Double::sum);
	}


	private void addItem(Product product, int quantityToAdd) {
		items.add(Item.builder()
				.product(product)
				.quantity(quantityToAdd)
				.build());
	}
	
	private Optional<Item> findItemifExists(long productId) {
        return this.items.stream().filter(item -> item.getProduct().getId().equals(
        		productId)).findFirst();
    }
	
	private void checkStockAvailability(Product product, int quantityToAdd) {
		if(product.getQuantityInStock() < quantityToAdd) {
			throw new BusinessException("Product.Quantity", quantityToAdd, product.getQuantityInStock());
		}
	}
	
	private void validateMaxAllowedItems() {
		if (this.getItems().size() == MAX_ALLOWED_ITEMS) {
			throw new BusinessException("MaxItems.Exceed", MAX_ALLOWED_ITEMS);
		}
	}
	
	private void validateTotalPrice() {
		calculatePrice(); 
		if(this.totalPrice > MAX_CART_PRICE) {
			throw new BusinessException("TotalPrice.Exceed", MAX_CART_PRICE);
		}
	}

	

}
