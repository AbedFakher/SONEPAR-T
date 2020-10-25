package com.biskot.infra.gateway;


import java.net.URI;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;


import com.biskot.domain.model.Product;
import com.biskot.infra.gateway.payload.ProductResponse;

@ExtendWith(MockitoExtension.class)
class ProductGatewayTest {
	
	  private ProductGateway productGateway;

	  @Mock
	  private RestTemplate restTemplate;
	  
	  @BeforeEach
	  public void initProductGatewayTest() {
		  productGateway = new ProductGateway(restTemplate, new ModelMapper());
		  ReflectionTestUtils.setField(productGateway, "productUrl", 
	               "http://localhost:9001/products/{productId}");
	  }

	  @Test
	  void should_retrieve_product() {

	        ProductResponse response = new ProductResponse();
	        response.setId(1l);
	        response.setLabel("productLabel");
	        response.setQuantityInStock(10);
	        response.setUnitPrice(10.00);

	        Mockito.when(restTemplate.getForEntity(URI.create("http://localhost:9001/products/1"), ProductResponse.class))
	        .thenReturn(ResponseEntity.ok(response));

	        Product product = Product.builder()
	        		.id(1l)
	        		.productLabel("productLabel")
	        		.quantityInStock(10)
	        		.unitPrice(10.00)
	        		.build();
	        Product result = productGateway.getProduct(1l);

	        Assertions.assertThat(result).isEqualToComparingFieldByField(product);

	    }
}

