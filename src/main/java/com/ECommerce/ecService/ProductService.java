package com.ECommerce.ecService;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ECommerce.ecDao.ProductDao;
import com.ECommerce.ecEntity.ProductEntity;
import com.ECommerce.ecRepository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	ProductDao proDao;

	@Autowired
	ProductRepository proRep;
	
	 public ProductEntity saveProduct(String model, String brand, float price, int quantity, MultipartFile imageFile) throws IOException {
	        ProductEntity product = new ProductEntity();
	        product.setModel(model);
	        product.setBrand(brand);
	        product.setPrice(price);
	        product.setQuantity(quantity);
	        product.setImageFile(imageFile.getBytes());  // Convert MultipartFile to byte[]
	        return proRep.save(product);
	    }

	public List<ProductEntity> getAllMobiles() {
		
		return proDao.getAllMobiles();
	}

	public List<ProductEntity> getBrandName(String brand) {
		return proDao.getBrandName(brand);
	}

	public ProductEntity getById(int n) {
		return proDao.getById(n);
	}
	

	public String updateById(int n, ProductEntity p) {
		return proDao.updateById(n,p);
	}

	public ProductEntity updateProduct(int n, String model, String brand, float price, int quantity,
			MultipartFile imageFile) {
		
		return proDao.updateProduct(n, model, brand, price, quantity, imageFile);
	}

	public String deleteById(int n) {
		return proDao.deleteById(n);
	}
	
}
