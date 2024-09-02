package com.sonata.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sonata.Model.OrderItem;
import com.sonata.Repository.OrderItemRepository;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    // Create OrderItem
    public OrderItem saveOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
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
