package com.nipuna.order_service.service;

import com.nipuna.inventory_service.dto.InventoryDTO;
import com.nipuna.order_service.common.ErrorOrderResponse;
import com.nipuna.order_service.common.OrderResponse;
import com.nipuna.order_service.common.SuccessOrderResponse;
import com.nipuna.order_service.dto.OrderDTO;
import com.nipuna.order_service.model.Orders;
import com.nipuna.order_service.repo.OrderRepo;
import com.nipuna.product_service.dto.ProductDTO;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

@Data
@Service
@Transactional
public class OrderService {
    private final WebClient inventoryWebClient;
    private final WebClient productWebClient;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ModelMapper modelMapper;

    public OrderService(WebClient inventoryWebClient,WebClient productWebClient, OrderRepo orderRepo, ModelMapper modelMapper){
        this.inventoryWebClient= inventoryWebClient;
        this.productWebClient=productWebClient;
        this.orderRepo=orderRepo;
        this.modelMapper=modelMapper;
    }

    // get all orders
    public List<OrderDTO> getAllOrders() {
        List<Orders> orderList = orderRepo.findAll();
        return modelMapper.map(orderList, new TypeToken<List<OrderDTO>>() {}.getType());
    }

    // save order
    public OrderResponse saveOrder(OrderDTO orderDTO) {
        Integer itemId = orderDTO.getItemId();

        try {
            InventoryDTO inventoryResponse = inventoryWebClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/items/{itemId}").build(itemId))
                    .retrieve()
                    .bodyToMono(InventoryDTO.class)
                    .block();

            assert inventoryResponse != null;

            Integer productId= inventoryResponse.getProductId();

            ProductDTO productResponse = productWebClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/products/{productId}").build(productId))
                    .retrieve()
                    .bodyToMono(ProductDTO.class)
                    .block();

            assert productResponse!=null;

            if (inventoryResponse.getQuantity() > 0) {
                if (productResponse.getForSale()==1){
                    orderRepo.save(modelMapper.map(orderDTO,Orders.class));
                }else {
                    return new ErrorOrderResponse("This item is not for sale");
                }

                return new SuccessOrderResponse(orderDTO);
            }else {
                     return new ErrorOrderResponse("Item Not Avilable");
            }

        } catch (WebClientResponseException e) {
            if (e.getStatusCode().is5xxServerError()){
                return new ErrorOrderResponse("Item Not Found");
            }
            e.printStackTrace();
        }

       return null;
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
