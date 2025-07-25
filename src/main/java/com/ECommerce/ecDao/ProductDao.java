package com.ECommerce.ecDao;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.ECommerce.ecEntity.ProductEntity;
import com.ECommerce.ecRepository.ProductRepository;

@Repository
public class ProductDao {

	@Autowired
	ProductRepository proRepo;

	public List<ProductEntity> getAllMobiles() {
		return proRepo.findAll();
	}

	public List<ProductEntity> getBrandName(String brand) {
		return proRepo.getBrandName(brand);
	}

	public ProductEntity getById(int n) {
		return proRepo.findById(n).orElse(null);
	}

	public String updateById(int n, ProductEntity p) {
		ProductEntity x = proRepo.findById(n).get();
		x.setBrand(p.getBrand());
		x.setImageFile(p.getImageFile());
		x.setModel(p.getModel());
		x.setPrice(p.getPrice());
		x.setQuantity(p.getQuantity());
		proRepo.save(x);
		return "Updated Successfully";
	}

	public ProductEntity updateProduct(int n, String model, String brand, float price, int quantity,
	        MultipartFile imageFile) {

	    Optional<ProductEntity> optionalProduct = proRepo.findById(n);

	    if (optionalProduct.isPresent()) {
	        ProductEntity product = optionalProduct.get();
	        product.setModel(model);
	        product.setBrand(brand);
	        product.setPrice(price);
	        product.setQuantity(quantity);

	        if (imageFile != null && !imageFile.isEmpty()) {
	            try {
	                product.setImageFile(imageFile.getBytes());
	            } catch (IOException e) {
	                throw new RuntimeException("Error reading image file", e);
	            }
	        }

	        return proRepo.save(product);
	    } else {
	        throw new RuntimeException("Product not found with ID: " + n);
	    }
	}

	public String deleteById(int n) {
		proRepo.deleteById(n);
		return "Delete Successfully";
	}


}
