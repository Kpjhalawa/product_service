package com.fackstoreintract.fackstoreinteract.Services;

import com.fackstoreintract.fackstoreinteract.exceptions.ProductNotPresentException;
import com.fackstoreintract.fackstoreinteract.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IProductService {
   Product getSingleProduct(Long id) throws ProductNotPresentException;

   List<Product> getAllProduct();


}
