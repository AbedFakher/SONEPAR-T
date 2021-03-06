package com.biskot.infra.repository.entity;

import java.time.Instant;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CartEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long cartId;
	
	private Instant creationTime;
	
	private Instant lastModifiedTime;
	
	@OneToMany(targetEntity=ItemEntity.class,cascade = CascadeType.ALL, 
            fetch = FetchType.EAGER, orphanRemoval = true)
	private List<ItemEntity> items;
	
}
