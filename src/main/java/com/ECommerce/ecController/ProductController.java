package com.ECommerce.ecController;

import java.awt.PageAttributes.MediaType;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.rsocket.service.RSocketExchange;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;



import com.ECommerce.ecEntity.ProductEntity;
import com.ECommerce.ecService.ProductService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/mobiles")
public class ProductController {
	@Autowired
	ProductService proSer;
	
	@PostMapping("/postData")
    public ResponseEntity<ProductEntity> createProduct(
            @RequestParam("model") String model,
            @RequestParam("brand") String brand,
            @RequestParam("price") float price,
            @RequestParam("quantity") int quantity,
            @RequestParam("image") MultipartFile imageFile
    ) throws IOException {
        ProductEntity savedProduct = proSer.saveProduct(model, brand, price, quantity, imageFile);
        return ResponseEntity.ok(savedProduct);
    }
	
	@GetMapping("/getAll")
    public List<ProductEntity> getAllMobiles() {
        return proSer.getAllMobiles();
    }
	
	@GetMapping("getBrandName/{brand}")
	public List<ProductEntity> getBrandName(@PathVariable String brand){
		return proSer.getBrandName(brand);
	}
	
	@GetMapping("getById/{n}")
	public ProductEntity getById(@PathVariable int n ) {
		return  proSer.getById(n);
		
	}
	@PutMapping("/updateById/{n}")
    public ResponseEntity<ProductEntity> updateProduct(
            @PathVariable int n,
            @RequestParam String model,
            @RequestParam String brand,
            @RequestParam float price,
            @RequestParam int quantity,
            @RequestParam(required = false) MultipartFile imageFile
    ) throws IOException {
        ProductEntity updatedProduct = proSer.updateProduct(n, model, brand, price, quantity, imageFile);
        return ResponseEntity.ok(updatedProduct);
    }
	
	@DeleteMapping("/deleteById/{n}")
	public String deleteById(@PathVariable int n) {
		return proSer.deleteById(n);
	}
}
