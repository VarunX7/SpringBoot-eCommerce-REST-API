package com.sonata.Service;

import com.sonata.Model.Product;
import com.sonata.Repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    public void setup() {
        productRepository.deleteAll();
    }

    @Test
    public void testSaveProduct() {
        Product product = new Product();
        product.setProductName("Test Product");
        product.setCategoryId(1L);
        product.setDescription("Test Description");
        product.setPrice(99.99);
        product.setStockQuantity(10L);
        product.setRating(5.0);

        Product savedProduct = productService.saveProduct(product);
        assertNotNull(savedProduct.getId());
        assertEquals("Test Product", savedProduct.getProductName());
    }

    @Test
    public void testSaveMultipleProducts() {
        Product product1 = new Product();
        product1.setProductName("Product 1");
        product1.setCategoryId(1L);

        Product product2 = new Product();
        product2.setProductName("Product 2");
        product2.setCategoryId(2L);

        List<Product> products = Arrays.asList(product1, product2);
        List<Product> savedProducts = productService.saveMultipleProducts(products);

        assertEquals(2, savedProducts.size());
    }

    @Test
    public void testGetProductById() {
        Product product = new Product();
        product.setProductName("Test Product");
        product.setCategoryId(1L);

        Product savedProduct = productService.saveProduct(product);
        Product foundProduct = productService.getProductById(savedProduct.getId());

        assertNotNull(foundProduct);
        assertEquals("Test Product", foundProduct.getProductName());
    }

    @Test
    public void testGetAllProducts() {
        Product product1 = new Product();
        product1.setProductName("Product 1");
        productService.saveProduct(product1);

        Product product2 = new Product();
        product2.setProductName("Product 2");
        productService.saveProduct(product2);

        List<Product> products = productService.getAllProducts();
        assertEquals(2, products.size());
    }

    @Test
    public void testGetProductsByCategory() {
        Product product1 = new Product();
        product1.setProductName("Product 1");
        product1.setCategoryId(1L);
        productService.saveProduct(product1);

        Product product2 = new Product();
        product2.setProductName("Product 2");
        product2.setCategoryId(2L);
        productService.saveProduct(product2);

        List<Product> products = productService.getProductsByCategory(1L);
        assertEquals(1, products.size());
        assertEquals("Product 1", products.get(0).getProductName());
    }

    @Test
    public void testGetProductsByNameString() {
        Product product1 = new Product();
        product1.setProductName("Unique Product");
        productService.saveProduct(product1);

        Product product2 = new Product();
        product2.setProductName("Another Product");
        productService.saveProduct(product2);

        List<Product> products = productService.getProductsByNameString("Unique");
        assertEquals(1, products.size());
        assertEquals("Unique Product", products.get(0).getProductName());
    }

    @Test
    public void testUpdateProduct() {
        Product product = new Product();
        product.setProductName("Old Name");
        product.setCategoryId(1L);

        Product savedProduct = productService.saveProduct(product);
        savedProduct.setProductName("New Name");
        savedProduct.setPrice(123.45);

        Product updatedProduct = productService.updateProduct(savedProduct.getId(), savedProduct);

        assertNotNull(updatedProduct);
        assertEquals("New Name", updatedProduct.getProductName());
        assertEquals(123.45, updatedProduct.getPrice());
    }

    @Test
    public void testDeleteProduct() {
        Product product = new Product();
        product.setProductName("Test Product");

        Product savedProduct = productService.saveProduct(product);
        productService.deleteProduct(savedProduct.getId());

        Product deletedProduct = productService.getProductById(savedProduct.getId());
        assertNull(deletedProduct);
    }
}
