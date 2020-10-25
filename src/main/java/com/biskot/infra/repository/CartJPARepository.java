package com.biskot.infra.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.biskot.infra.repository.entity.CartEntity;

@Repository
public interface CartJPARepository extends CrudRepository<CartEntity, Long> {
	

}
