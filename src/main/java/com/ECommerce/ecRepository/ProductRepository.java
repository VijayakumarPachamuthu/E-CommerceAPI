package com.ECommerce.ecRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ECommerce.ecEntity.ProductEntity;

import feign.Param;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer>  {
	
//	@Query("SELECT p FROM ProductEntity p WHERE p.brand = :brand")
	@Query("SELECT p FROM ProductEntity p WHERE LOWER(p.brand) LIKE LOWER(CONCAT('%', :brand, '%'))")
	List<ProductEntity> getBrandName(@Param("brand") String brand);




	


}
