package com.product.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.product.dto.MessageDto;
import com.product.exception.ProductException;
import com.product.model.Product;
import com.product.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService ps;

	@PostMapping("/fileupload")
	public ResponseEntity<MessageDto> fileUpload(@RequestBody MultipartFile file) throws ProductException, IOException{
		MessageDto m=ps.fileUpload(file);
		return new ResponseEntity<MessageDto>(m,HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Product> addProduct(@RequestBody Product product){
		Product p=ps.addProduct(product);
		return new ResponseEntity<Product>(p,HttpStatus.OK);
	}
	
	@GetMapping("/findall")
	public ResponseEntity<List<Product>> findAll() throws ProductException{
		List<Product>list=ps.findAll();
		return new ResponseEntity<List<Product>>(list,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/search/{name}")
	public ResponseEntity<List<Product>> search(@PathVariable("name") String name) throws ProductException{
		List<Product>list=ps.searchProduct(name);
		return new ResponseEntity<List<Product>>(list,HttpStatus.ACCEPTED);
	}
	
}
