package com.publisher.managment.system.service.impl;

import com.publisher.managment.system.dto.BookDTO;
import com.publisher.managment.system.dto.OrderDTO;
import com.publisher.managment.system.dto.auth.SecurityUtil;
import com.publisher.managment.system.entity.Book;
import com.publisher.managment.system.entity.Order;
import com.publisher.managment.system.entity.enums.OrderStatus;
import com.publisher.managment.system.exception.BadRequestException;
import com.publisher.managment.system.exception.ExceptionMessage;
import com.publisher.managment.system.exception.ResourceNotFoundException;
import com.publisher.managment.system.mapper.OrderMapper;
import com.publisher.managment.system.repository.BookRepository;
import com.publisher.managment.system.repository.OrderRepository;
import com.publisher.managment.system.service.OrderService;
import com.publisher.managment.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl extends ExceptionMessage implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private BookRepository bookRepository;

    @Override
    @Transactional
    public OrderDTO createOrder(OrderDTO orderDTO) {
        checkBooks(orderDTO.getBooks());
        calculateTotalAmount(orderDTO);
        orderDTO.setOrderStatus(OrderStatus.IN_PENDING.getValue());
        orderDTO.setCourier(userService.getRandomCourier());
        orderDTO.setUser(userService.getUserById(SecurityUtil.getUserIdFromContext()));
        return OrderMapper.toDto(orderRepository.save(OrderMapper.toEntity(orderDTO)));
    }

    private void checkBooks(List<BookDTO> books) {
        books.forEach(bookDTO ->
        {
            Book bookEntity = bookRepository.findById(bookDTO.getId()).orElseThrow(() -> new ResourceNotFoundException(BOOK_NOT_FOUND));
            Integer quantity = bookEntity.getQuantity();
            Integer orderedQuantity = bookDTO.getQuantity();
            if (orderedQuantity <= quantity) {
                bookEntity.setQuantity(quantity - orderedQuantity);
                bookRepository.save(bookEntity);
            } else {
                throw new BadRequestException(String.format(QUANTITY_NOT_AVAILABLE, orderedQuantity));
            }
        });
    }

    @Override
    public List<OrderDTO> getOrdersByStatus(boolean isDeleted) {
        return orderRepository.findByDeleted(isDeleted).stream().map(OrderMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public OrderDTO getOrderById(Integer id) {
        return orderRepository.findById(id)
                .map(OrderMapper::toDto).orElseThrow(() -> new ResourceNotFoundException(String.format(ORDER_NOT_FOUND, id)));
    }

    @Override
    @Transactional
    public OrderDTO updateOrder(Integer id, OrderDTO orderDTO) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(ORDER_NOT_FOUND, id)));
        return OrderMapper.toDto(orderRepository.save(OrderMapper.toEntityForUpdate(order, orderDTO)));
        //TODO ROLLBACK IF ORDER GET CANCELLED
    }

    @Override
    @Transactional
    public void deleteOrderById(Integer id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(ORDER_NOT_FOUND, id)));
        orderRepository.delete(order);
    }

    @Override
    public List<OrderDTO> getOrdersByClient(Integer libraryId) {
        return orderRepository.findAllByLibraryId(libraryId).stream().map(OrderMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getOrdersAssigned() {
        return orderRepository.findByCourierId(SecurityUtil.getUserIdFromContext())
                .stream().map(OrderMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getOrdersByCourier(Integer courierId) {
        return orderRepository.findByCourierId(courierId).stream().map(OrderMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public long getTotalOfOrders() {
        return orderRepository.findAll().stream().count();
    }

    @Override
    public Double getTotalRevenue() {
        return orderRepository.findAll().stream().map(Order::getTotalAmount).mapToDouble(Double::doubleValue).sum();
    }

    private void calculateTotalAmount(OrderDTO orderDTO) {
        double totalAmount = orderDTO.getBooks().stream()
                .map(bookDTO -> {
                    double price = bookRepository.findById(bookDTO.getId()).get().getPrice();
                    return price * bookDTO.getQuantity();
                })
                .mapToDouble(Double::doubleValue).sum();
        orderDTO.setTotalAmount(totalAmount - (totalAmount * orderDTO.getDiscount()));
    }
}
