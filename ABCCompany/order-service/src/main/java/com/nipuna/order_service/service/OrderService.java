package com.nipuna.order_service.service;

import com.nipuna.order_service.dto.OrderDTO;
import com.nipuna.order_service.model.Orders;
import com.nipuna.order_service.repo.OrderRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ModelMapper modelMapper;

    // get all orders
    public List<OrderDTO> getAllOrders() {
        List<Orders> orderList = orderRepo.findAll();
        return modelMapper.map(orderList, new TypeToken<List<OrderDTO>>() {}.getType());
    }

    // save order
    public OrderDTO saveOrder(OrderDTO orderDTO) {
        orderRepo.save(modelMapper.map(orderDTO, Orders.class));
        return orderDTO;
    }

    // update order
    public OrderDTO updateOrder(OrderDTO orderDTO) {
        orderRepo.save(modelMapper.map(orderDTO, Orders.class));
        return orderDTO;
    }

    // delete order
    public String deleteOrder(Long orderId) {
        orderRepo.deleteById(Math.toIntExact(orderId));
        return "Order deleted";
    }

    // get order by id
    public OrderDTO getOrderById(Long orderId) {
        Orders order = orderRepo.getOrderById(Math.toIntExact(orderId));
        return modelMapper.map(order, OrderDTO.class);
    }
}
