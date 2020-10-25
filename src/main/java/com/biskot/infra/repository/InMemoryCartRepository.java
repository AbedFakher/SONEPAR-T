package com.biskot.infra.repository;

import com.biskot.domain.model.Cart;
import com.biskot.domain.spi.CartPersistencePort;
import com.biskot.infra.repository.entity.CartEntity;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryCartRepository implements  CartPersistencePort {

    @Autowired
	private CartJPARepository cartJPARepository;
	
    @Autowired
	private ModelMapper modelMapper;	
	
	@Override
    public Optional<Cart> getCart(long id) {
		Optional<CartEntity> cartEntity = cartJPARepository.findById(id);
        return cartEntity.map(entity-> modelMapper.map(entity, Cart.class));
    }

    @Override
    public void saveCart(Cart cart) {
    	CartEntity cartEntity = modelMapper.map(cart, CartEntity.class);
    	cartJPARepository.save(cartEntity);
    }
}
