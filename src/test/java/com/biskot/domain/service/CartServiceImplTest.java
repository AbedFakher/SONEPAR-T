package com.biskot.domain.service;

import static org.mockito.Mockito.times;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.biskot.domain.exception.BusinessException;
import com.biskot.domain.exception.NotFoundException;
import com.biskot.domain.model.Cart;
import com.biskot.domain.model.Item;
import com.biskot.domain.model.Product;
import com.biskot.domain.spi.CartPersistencePort;
import com.biskot.domain.spi.ProductPort;
import com.google.common.collect.ImmutableSet;

@ExtendWith(MockitoExtension.class)
class CartServiceImplTest {
	

	@InjectMocks
    private CartServiceImpl cartService;
	
	@Mock
    private CartPersistencePort cartPersistencePort;
	
	@Mock
    private ProductPort productPort;
	
	@Test
    void should_create_cart() {
        cartService.createCart();
        Mockito.verify(cartPersistencePort,times(1))
        .saveCart(ArgumentMatchers.any(Cart.class));
    }
	
	@Test
    void should_throw_data_not_found_exception_when_cart_not_found(){
		
        Mockito.when((cartPersistencePort.getCart(9)))
        .thenReturn(Optional.empty());
       
        Exception exception = assertThrows(NotFoundException.class,
            			() -> cartService.getCart(9));

        assertEquals("Cart.NotFound", exception.getMessage());
                
	}
	
	@Test
    void should_throw_data_not_found_exception_when_product_not_found(){
        Mockito.when((productPort.getProduct(999)))
        .thenReturn(null);
       
        Exception exception = assertThrows(NotFoundException.class,
            			() -> cartService.addItemToCart(9, 999, 1));

        assertEquals("Product.NotFound", exception.getMessage());
	}
	
	@Test
    void should_throw_Business_exception_when_exceed_allowed_different_items() {
        
		long cartId = 1;
        Cart cart = new Cart();
        Set<Item> items = LongStream.range(1, 4)
        				.mapToObj(i -> Item.builder()
        						.product(Product.builder()
        								.id(i)
        								.build())
        						.quantity(1)
        						.build())
        				.collect(Collectors.toSet());
        cart.setItems(items);
        Product product = Product.builder()
        		.id(9l)
        		.quantityInStock(1000)
        		.build();
        
        Mockito.when(cartPersistencePort.getCart(cartId)).thenReturn(Optional.of(cart));
        Mockito.when(productPort.getProduct(9)).thenReturn(product);
        

        Exception exception = assertThrows(BusinessException.class,
    			() -> cartService.addItemToCart(cartId, 9, 1));

        assertEquals("MaxItems.Exceed", exception.getMessage());

        Mockito.verify(cartPersistencePort, Mockito.never()).saveCart(cart);
    }
	
	 @Test
	 void should_throw_Business_exception_when_added_quantity_exceed_product_stock(){
			long cartId = 1;
	        Cart cart = new Cart();
	        int quantity = 3;
	        Product product = Product.builder()
	        		.id(9l)
	        		.quantityInStock(2)
	        		.build();
	        
	        Mockito.when(cartPersistencePort.getCart(cartId)).thenReturn(Optional.of(cart));
	        Mockito.when(productPort.getProduct(9)).thenReturn(product);
	        

	        Exception exception = assertThrows(BusinessException.class,
	    			() -> cartService.addItemToCart(cartId, 9, quantity));

	        assertEquals("Product.Quantity", exception.getMessage());

	        Mockito.verify(cartPersistencePort, Mockito.never()).saveCart(cart);
	 }
	
	 @Test
	 void should_throw_Business_exception_when_max_allowed_price_exceeded(){
			long cartId = 1;
	        Cart cart = new Cart().init();
	        cart.setItems(new HashSet<>(ImmutableSet.of(Item.builder()
	        		.product(Product.builder()
	        				.id(1l)
	        				.unitPrice(90.00)
	        				.build())
	        		.quantity(1)
	        		.build())));
	        Product product = Product.builder()
	        		.id(9l)
	        		.quantityInStock(10)
	        		.unitPrice(20.00)
	        		.build();
	        
	        Mockito.when(cartPersistencePort.getCart(cartId)).thenReturn(Optional.of(cart));
	        Mockito.when(productPort.getProduct(9)).thenReturn(product);
	        
	        Exception exception = assertThrows(BusinessException.class,
	    			() -> cartService.addItemToCart(cartId, 9, 1));

	        assertEquals("TotalPrice.Exceed", exception.getMessage());

	        Mockito.verify(cartPersistencePort, Mockito.never()).saveCart(cart);
	 }
	 
	 @Test
	 void should_update_item_if_already_exist(){
	        Cart cart = new Cart().init();
	        cart.setCartId(1l);
	        cart.setItems(new HashSet<>(ImmutableSet.of(Item.builder()
	        		.product(Product.builder()
	        				.id(7l)
	        				.unitPrice(90.00)
	        				.build())
	        		.quantity(1)
	        		.build())));
	        
	        Product product = Product.builder()
	        		.id(7l)
	        		.quantityInStock(10)
	        		.unitPrice(20.00)
	        		.build();
	        
	        Mockito.when(cartPersistencePort.getCart(cart.getCartId())).thenReturn(Optional.of(cart));
	        Mockito.when(productPort.getProduct(product.getId())).thenReturn(product);
	         
	        cartService.addItemToCart(cart.getCartId(), product.getId(), 2);
	        
	        Mockito.verify(cartPersistencePort,times(1))
	        .saveCart(ArgumentMatchers.any(Cart.class));
	        
	        ArgumentCaptor<Cart> captor = ArgumentCaptor.forClass(Cart.class);
	        Mockito.verify(cartPersistencePort).saveCart(captor.capture());
	        Cart cartToSave = captor.getValue();
	        
	        assertThat(cartToSave.getItems()).hasSize(1);
	        
	        assertThat(cartToSave.getItems().stream())
	        .filteredOn(item-> item.getQuantity()==2).isNotEmpty();
		 
	 }
	
	
	
}