package com.fackstoreintract.fackstoreinteract.Services;

import com.fackstoreintract.fackstoreinteract.Config.RedisConfig;
import com.fackstoreintract.fackstoreinteract.Dtos.ProductResponseDto;
import com.fackstoreintract.fackstoreinteract.exceptions.ProductNotPresentException;
import com.fackstoreintract.fackstoreinteract.model.Category;
import com.fackstoreintract.fackstoreinteract.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements IProductService{
    @Autowired
   RestTemplate restTemplate;
    @Autowired
    private RedisTemplate<String,Long> redistemplet;
    @Autowired
    RedisTemplate<String, List<Product>> redisTemplate;
    @Override
    public Product getSingleProduct(Long id) throws ProductNotPresentException {
        // IShould pass this 'id' to fakeStore  and get the details of this product.
        //https://fakestoreapi.com/products/1
        if(redistemplet.opsForHash().hasKey("PRODUCTS",id)){
            return (Product) redistemplet.opsForHash().get("PRODUCTS",id);
        }

        if(id>20 && id<=40){
            throw new ProductNotPresentException();
        }
        if(id>40){
            throw new ArithmeticException();
        }
        ProductResponseDto response = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id,
                ProductResponseDto.class);

        Product product = getProductFromResponseDto(response);
        redistemplet.opsForHash().put("PRODUCTS",id,product);
        return product;
    }

    @Override
    public List<Product> getAllProduct() {
        ProductResponseDto[] products = restTemplate.
                getForObject("https://fakestoreapi.com/products/",ProductResponseDto[].class);

        List<Product> output = new ArrayList<>();
        for(ProductResponseDto productResponseDto :  products){
            output.add(getProductFromResponseDto(productResponseDto));
        }
//        redisTemplate.opsForHash().put("PRODUCTS");
        return output;
    }

    private Product getProductFromResponseDto(ProductResponseDto responseDto) {
         Product product = new Product();
         product.setId(responseDto.getId());
         product.setName(responseDto.getTitle());
         product.setDescription(responseDto.getDescription());
         product.setImage(responseDto.getImage());
         product.setPrice(responseDto.getPrice());
         product.setCategory(new Category());
         product.getCategory().setName(responseDto.getCategory());

         return product;
    }
}
