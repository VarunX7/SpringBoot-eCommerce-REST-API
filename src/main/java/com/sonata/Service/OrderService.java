package com.sonata.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sonata.Model.Order;
import com.sonata.Repository.OrderRepository;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // Create an Order
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    // Get Order by ID
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }
    
    // Get Order by user
    public List<Order> getOrdersByUser(Long user) {
    	return orderRepository.findOrdersByUser(user);
    }

    // Get all Orders
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Update an Order
    public Order updateOrder(Long id, Order updatedOrder) {
        Order existingOrder = orderRepository.findById(id).orElse(null);
        if (existingOrder != null) {
        	if(updatedOrder.getOrderStatus() != null) {
        		existingOrder.setOrderStatus(updatedOrder.getOrderStatus());
        	}
        	if(updatedOrder.getShippingAddress() != null) {
        		existingOrder.setShippingAddress(updatedOrder.getShippingAddress());        		
        	}
            return orderRepository.save(existingOrder);
        }
        return null;
    }

    // Delete an Order by ID
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
