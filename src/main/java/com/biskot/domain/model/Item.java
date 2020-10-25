package com.biskot.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Item {
	private Long itemId;
	private Product product;
	private int quantity;
	private Double linePrice;
	
	public Double calculateLinePrice() {
		if (product == null || product.getUnitPrice()== null)
            return 0.0;
		this.linePrice = this.quantity * product.getUnitPrice();
        return this.linePrice;
	}
}
