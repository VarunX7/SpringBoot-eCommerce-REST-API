package com.sonata.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sonata.Model.OrderItem;
import com.sonata.Model.Product;
import com.sonata.Repository.OrderItemRepository;
import com.sonata.Repository.ProductRepository;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;
    
    @Autowired
    private ProductRepository productRepo;

    // Create OrderItem
    public OrderItem saveOrderItem(OrderItem orderItem) {
    	
    	Product product = productRepo.findById(orderItem.getProduct()).orElse(null);
    	
    	if(product != null) {
    		if(product.getStockQuantity() < orderItem.getQuantity() && product.getStockQuantity() < 10) {
    			orderItem.setQuantity(product.getStockQuantity());
    		}
    		else if(orderItem.getQuantity() > 10) {
    			orderItem.setQuantity((long)10);
    		}
    		orderItem.setTotalPrice(product.getPrice() * orderItem.getQuantity());
    		product.setStockQuantity(product.getStockQuantity() - orderItem.getQuantity());
    		productRepo.save(product);
    		return orderItemRepository.save(orderItem);
    	}
    	return null;
    }
    
    // Create multiple orderItems
    public List<OrderItem> saveMultipleOrderItems(List<OrderItem> orderItems){
    	List<OrderItem> orderItemsUpdated = orderItems.stream()
    			.map(this::saveOrderItem)
    			.collect(Collectors.toList());
    	return orderItemsUpdated;
    }

    // Get OrderItem by ID
    public OrderItem getOrderItemById(Long id) {
        return orderItemRepository.findById(id).orElse(null);
    }
    
    public List<OrderItem> getOrderItemsByOrder(Long orderNo){
    	return orderItemRepository.findByOrderNo(orderNo);
    }

    // Get all OrderItems
    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    // Update an OrderItem
//    public OrderItem updateOrderItem(Long id, OrderItem updatedOrderItem) {
//        OrderItem existingOrderItem = orderItemRepository.findById(id).orElse(null);
//        if (existingOrderItem != null) {
//            existingOrderItem.setOrder(updatedOrderItem.getOrder());
//            existingOrderItem.setProduct(updatedOrderItem.getProduct());
//            existingOrderItem.setQuantity(updatedOrderItem.getQuantity());
//            existingOrderItem.setTotalPrice(updatedOrderItem.getTotalPrice());
//            return orderItemRepository.save(existingOrderItem);
//        }
//        return null;
//    }

    // Delete an OrderItem by ID
    public void deleteOrderItem(Long id) {
        orderItemRepository.deleteById(id);
    }
}
