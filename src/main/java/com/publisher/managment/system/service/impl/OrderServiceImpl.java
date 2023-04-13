package com.publisher.managment.system.service.impl;

import com.publisher.managment.system.dto.BookDTO;
import com.publisher.managment.system.dto.OrderDTO;
import com.publisher.managment.system.dto.UserDTO;
import com.publisher.managment.system.entity.*;
import com.publisher.managment.system.entity.enums.OrderStatus;
import com.publisher.managment.system.entity.enums.PaymentMethod;
import com.publisher.managment.system.entity.enums.Role;
import com.publisher.managment.system.exception.ExceptionMessage;
import com.publisher.managment.system.exception.ResourceNotFoundException;
import com.publisher.managment.system.mapper.OrderMapper;
import com.publisher.managment.system.repository.BookRepository;
import com.publisher.managment.system.repository.LibraryRepository;
import com.publisher.managment.system.repository.OrderRepository;
import com.publisher.managment.system.repository.UserRepository;
import com.publisher.managment.system.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static com.publisher.managment.system.dto.auth.SecurityUtil.getLoggedUser;

@Service
public class OrderServiceImpl extends ExceptionMessage implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private LibraryRepository libraryRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public OrderDTO createOrder(OrderDTO orderDTO, Integer libraryId) {
        Library library = libraryRepository.findById(libraryId).orElseThrow(() -> new ResourceNotFoundException(String.format(LIBRARY_NOT_FOUND, libraryId)));
        Order order = OrderMapper.toEntity(orderDTO);
        order.setLibrary(library);
        order.setUser(getLoggedUser());
//        order.setCourier();
        order.setOrderStatus(OrderStatus.IN_PENDING);
        return OrderMapper.toDto(orderRepository.save(OrderMapper.toEntity(orderDTO)));
    }

    private User getRandomCourier() {
        // merr nje liste me userat qe kane rolin corrier - db
        List<User> couriers = userRepository.findAllByRole(Role.fromValue(Role.COURIER.getValue()));
        // kontrolo cili eshte free - psh vendosu nje limit korriereve sa porosi mund te marrin
        // gjenero nje numer random midis 0 dhe size te listes - google
        return null;
    }
    @Override
    public List<OrderDTO> getOrders() {
        return orderRepository.findAll().stream().map(OrderMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public OrderDTO getOrderById(Integer id) {
        return orderRepository.findById(id).map(OrderMapper::toDto).orElseThrow(() -> new ResourceNotFoundException(String.format(ORDER_NOT_FOUND, id)));
    }

    @Override
    @Transactional
    public OrderDTO updateOrder(Integer id, OrderDTO orderDTO) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format(ORDER_NOT_FOUND, id)));
        return OrderMapper.toDto(orderRepository.save(OrderMapper.toEntityForUpdate(order, orderDTO)));
    }

    @Override
    @Transactional
    public void deleteOrderById(Integer id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format(ORDER_NOT_FOUND, id)));
        orderRepository.delete(order);
    }

    @Override
    public void setOrderStatus(String status, Integer orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(()-> new ResourceNotFoundException(String.format(ORDER_NOT_FOUND, orderId)));
        order.setOrderStatus(OrderStatus.fromValue(status));

    }

    @Override
    public List<OrderDTO> getOrdersByClient(Integer libraryId) {
        return null;
    }

    @Override
    public List<OrderDTO> getOrdersByPaymentMethod(String paymentMethod) {
        return null;
    }

    @Override
    public List<OrderDTO> getOrdersAssigned(OrderDTO orderDTO, UserDTO userDTO) {
        return null;
    }

    @Override
    public Integer getTotalOfOrders(Integer orderId) {
        return null;
    }

    @Override
    public Double getTotalRevenue(Double orderAmount) {
        return null;
    }

    @Override
    public Book setDiscount(Double percentage, BookDTO bookDTO) {
        return null;
    }
}
