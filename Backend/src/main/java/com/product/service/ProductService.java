package com.product.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.product.dto.MessageDto;
import com.product.exception.ProductException;
import com.product.model.Product;

public interface ProductService {
	
	public MessageDto fileUpload(MultipartFile file)throws ProductException, IOException;
	
	public Product addProduct(Product product);
	
	public List<Product> searchProduct(String name)throws ProductException;
	
	public List<Product> findAll()throws ProductException;

}
