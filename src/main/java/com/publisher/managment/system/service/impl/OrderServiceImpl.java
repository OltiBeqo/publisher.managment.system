package com.publisher.managment.system.service.impl;

import com.publisher.managment.system.dto.BookDTO;
import com.publisher.managment.system.dto.OrderDTO;
import com.publisher.managment.system.dto.UserDTO;
import com.publisher.managment.system.dto.auth.SecurityUtil;
import com.publisher.managment.system.entity.Book;
import com.publisher.managment.system.entity.Order;
import com.publisher.managment.system.entity.User;
import com.publisher.managment.system.entity.enums.OrderStatus;
import com.publisher.managment.system.entity.enums.Role;
import com.publisher.managment.system.exception.BadRequestException;
import com.publisher.managment.system.exception.ExceptionMessage;
import com.publisher.managment.system.exception.ResourceNotFoundException;
import com.publisher.managment.system.mapper.BookMapper;
import com.publisher.managment.system.mapper.LibraryMapper;
import com.publisher.managment.system.mapper.OrderMapper;
import com.publisher.managment.system.repository.BookRepository;
import com.publisher.managment.system.repository.LibraryRepository;
import com.publisher.managment.system.repository.OrderRepository;
import com.publisher.managment.system.repository.UserRepository;
import com.publisher.managment.system.service.OrderService;
import com.publisher.managment.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl extends ExceptionMessage implements OrderService {
    @Autowired private OrderRepository orderRepository;
    @Autowired private LibraryRepository libraryRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private UserService userService;
    @Autowired private BookRepository bookRepository;
    @Override
    @Transactional
    public OrderDTO createOrder(OrderDTO orderDTO, Integer libraryId) {
        Order order = OrderMapper.toEntity(orderDTO);
        order.setLibrary(LibraryMapper.toEntity(orderDTO.getLibrary()));
//        order.setPayment(); // do shtuar mapper dhe te merret nga
        order.setCourier(getRandomCourier());
        order.setBook(retrieveBooks(orderDTO.getBook()));
        order.setUser(User.builder().id(SecurityUtil.getUserIdFromContext()).build());
        return OrderMapper.toDto(orderRepository.save(order));
    }

    private List<Book> retrieveBooks(List<BookDTO> books) {
        books.forEach(bookDTO -> checkBooks(bookDTO));
        return books.stream().map(BookMapper::toEntity).collect(Collectors.toList());
    }

    private void checkBooks(BookDTO bookDTO) {
        Book bookEntity = bookRepository.findById(bookDTO.getId()).orElseThrow(() -> new ResourceNotFoundException(BOOK_NOT_FOUND));
        Integer quantity = bookEntity.getQuantity();
        Integer orderedQuantity = bookDTO.getQuantity();
        if (orderedQuantity <= quantity) {
            bookEntity.setQuantity(quantity - orderedQuantity);
            bookRepository.save(bookEntity);
        } else {
            throw new BadRequestException(String.format(QUANTITY_NOT_AVAILABLE, orderedQuantity));
        }
    }

    private User getRandomCourier() {
        return userRepository.findAllByRole(Role.fromValue(Role.COURIER.getValue())).stream()
                .findAny()
                .orElseThrow(() -> new ResourceNotFoundException("Courier not available"));
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
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException(String.format(ORDER_NOT_FOUND, orderId)));
        order.setOrderStatus(OrderStatus.fromValue(status));

    }

    @Override
    public List<OrderDTO> getOrdersByClient(Integer libraryId) {
        return orderRepository.findAllByLibraryId(libraryId).stream().map(OrderMapper::toDto).collect(Collectors.toList());
    }

//    @Override
//    public List<OrderDTO> getOrdersByPaymentMethod(PaymentMethod paymentMethod) {
//        return orderRepository.findAllByPaymentMethod(paymentMethod).stream().map(OrderMapper::toDto).collect(Collectors.toList());
//    }

    @Override
    public List<OrderDTO> getOrdersAssigned(OrderDTO orderDTO, UserDTO userDTO) {
        return orderRepository.findByCourier_Id(userDTO.getId()).stream().map(OrderMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public long getTotalOfOrders(Integer orderId) {
        return orderRepository.findAll().stream().count();
    }

    @Override
    public Double getTotalRevenue() {
        return orderRepository.findAll().stream()
                .map(order -> order.getTotalAmount())
                .mapToDouble(Double::doubleValue).sum();
    }

    @Override
    public Book setDiscount(Double percentage, Integer bookId) {
        return null;
    }
}
