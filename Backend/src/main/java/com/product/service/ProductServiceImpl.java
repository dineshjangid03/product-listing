package com.product.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.product.dto.MessageDto;
import com.product.exception.ProductException;
import com.product.model.Product;
import com.product.repository.ProductRepo;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepo pr;

	
	
	@Override
	public MessageDto fileUpload(MultipartFile file) throws ProductException, IOException {
		String pathName=new ClassPathResource("").getFile().getAbsolutePath();

		Files.copy(file.getInputStream(),Paths.get(pathName+"/static/images"+File.separator+file.getOriginalFilename()) , StandardCopyOption.REPLACE_EXISTING);
		String download=ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/").path(file.getOriginalFilename()).toUriString();
		MessageDto md=new MessageDto();
		md.setMessage(download);
		return md;
	}



	@Override
	public Product addProduct(Product product) {
		return pr.save(product);
	}



	@Override
	public List<Product> searchProduct(String name) throws ProductException {
		List<Product> list=pr.findByProductNameLike(name);
		if(list.size()==0) {
			throw new ProductException("product not found");
		}
		return list;
	}



	@Override
	public List<Product> findAll() throws ProductException {
		List<Product> list=pr.findAll();
		if(list.size()==0) {
			throw new ProductException("product not found");
		}
		return list;
	}
	

}
