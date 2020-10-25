package com.biskot.infra.config;

import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.biskot.domain.model.Item;
import com.biskot.domain.model.Product;
import com.biskot.infra.repository.entity.ItemEntity;



@Configuration
public class BeansConfiguration {


    @Bean
    public RestTemplate restTesmplate() {
        return new RestTemplate();
    }
    
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
                
        modelMapper.addConverter( new AbstractConverter<Item, ItemEntity>() {
			@Override
			protected ItemEntity convert(Item source) {
				Product product = source.getProduct();
				return ItemEntity.builder()
						.productId(product.getId())
						.productLabel(product.getProductLabel())
						.unitPrice(product.getUnitPrice())
						.quantity(source.getQuantity())
						.build();
			} 	
        });
        modelMapper.addConverter( new AbstractConverter<ItemEntity,Item>() {
			@Override
			protected Item convert(ItemEntity source) {
				return Item.builder()
						.quantity(source.getQuantity())
						.product(Product.builder()
								.id(source.getProductId())
								.productLabel(source.getProductLabel())
								.unitPrice(source.getUnitPrice())
								.build())
						.build();
			} 	
        });
        return modelMapper;
    }


}