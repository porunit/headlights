package com.headlightbackend.services;

import com.headlightbackend.data.domain.Order;
import com.headlightbackend.data.domain.OrderState;
import com.headlightbackend.data.dto.OrderDTO;
import com.headlightbackend.exceptions.EmptyOrdersListException;
import com.headlightbackend.exceptions.ObjectDoesntExistsException;
import com.headlightbackend.mappers.OrderItemMapper;
import com.headlightbackend.repositories.OrderRepository;
import com.headlightbackend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderItemMapper mapper;

    public void deleteOrderById(Long id) {
        if(id == null){
            throw new NullPointerException("Not deleted. Id cannot be a null");
        }
        orderRepository.deleteById(id);
    }

    public List<OrderDTO> getAllOrders(){
        return orderRepository.findAll()
                .stream().map(this::toDTO).collect(Collectors.toList());
    }
    public List<OrderDTO> getAllOrdersByState(OrderState state){
        return orderRepository.findAllByState(state)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }
    public List<OrderDTO> getOrdersByUser(String username) {
        Long id = userRepository.getUserIdByUsername(username);
        Optional<List<Order>> orders = orderRepository.findAllByUserId(id);
        if (!orders.isPresent()) {
            throw new EmptyOrdersListException("User has empty orders list");
        }
        List<Order> orderList = orders.get();
        return orderList.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public void saveOrder(OrderDTO orderDTO, String username) {
        if(orderDTO == null){
            throw new NullPointerException("Not saved. Order cannot be a null");
        }
        Order order = fromDTO(orderDTO, username);
        log.info(orderDTO);
        log.info(order);
        orderRepository.save(order);
    }

    public void changeAddress(Long id, String address){
        if (!orderRepository.existsById(id)) {
            throw new ObjectDoesntExistsException("Order with id: " + id + " doesnt exist");
        }
        orderRepository.updateAddress(id, address);
    }
    public void changeState(Long id, OrderState state) {
        if (!orderRepository.existsById(id)) {
            throw new ObjectDoesntExistsException("Order with id: " + id + " doesnt exist");
        }
        orderRepository.updateState(id, state);
    }

    private OrderDTO toDTO(Order order) {
        return OrderDTO.builder()
                .id(order.getId())
                .address(order.getAddress())
                .date(order.getDate())
                .orderDetails(order.getOrderDetails())
                .state(String.valueOf(order.getState()))
                .username(order.getUser().getUsername())
                .items(order.getItems().stream().map(mapper::toDTO).collect(Collectors.toList()))
                .build();
    }

    private Order fromDTO(OrderDTO dto, String username) {
        return Order.builder()
                .id(dto.getId())
                .address(dto.getAddress())
                .date(dto.getDate())
                .orderDetails(dto.getOrderDetails())
                .user(userRepository.findFirstByUsername(username))
                .state(OrderState.IN_PROGRESS)
                .items(dto.getItems().stream().map(mapper::fromDTO).collect(Collectors.toList()))
                .build();
    }
}
