package com.biskot.infra.gateway;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.biskot.domain.model.Product;
import com.biskot.domain.spi.ProductPort;
import com.biskot.infra.gateway.payload.ProductResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductGateway implements ProductPort {
	
	private final RestTemplate restTemplate;
	private final ModelMapper modelMapper;

	
	@Value("${productUrl}")
	private String productUrl;

    public Product getProduct(long productId) {
    	Map<String, Long> params = new HashMap<>();
    	params.put("productId", productId);
    	URI uri = UriComponentsBuilder.fromHttpUrl(productUrl)
    			.buildAndExpand(params)
    	        .toUri();
    	
    	ResponseEntity<ProductResponse> response = restTemplate.getForEntity(uri, ProductResponse.class);
        return  Optional.ofNullable(response)
                .map(ResponseEntity::getBody)
                .map(body -> modelMapper.map(body, Product.class))
                .orElseThrow();
    	
    }

}
