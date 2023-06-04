package com.publisher.managment.system.service.impl;

import com.publisher.managment.system.configuration.SecurityUtil;
import com.publisher.managment.system.dto.BookDTO;
import com.publisher.managment.system.dto.OrderDTO;
import com.publisher.managment.system.dto.request.SearchRequest;
import com.publisher.managment.system.dto.search.SearchSpecification;
import com.publisher.managment.system.entity.Book;
import com.publisher.managment.system.entity.Order;
import com.publisher.managment.system.entity.OrdersBooks;
import com.publisher.managment.system.entity.enums.OrderStatus;
import com.publisher.managment.system.exception.BadRequestException;
import com.publisher.managment.system.exception.ExceptionMessage;
import com.publisher.managment.system.exception.ResourceNotFoundException;
import com.publisher.managment.system.mapper.OrderMapper;
import com.publisher.managment.system.repository.BookRepository;
import com.publisher.managment.system.repository.OrderRepository;
import com.publisher.managment.system.repository.OrdersBooksRepository;
import com.publisher.managment.system.service.OrderService;
import com.publisher.managment.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl extends ExceptionMessage implements OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final BookRepository bookRepository;
    private final OrdersBooksRepository ordersBooksRepository;

    @Override
    public Page<OrderDTO> getOrdersPaginated(Pageable pageable) {

        Page<Order> orderPage = orderRepository.findAll(pageable);
        List<Order> orderList = orderPage.getContent();
        List<OrderDTO> content = orderList.stream().map(OrderMapper::toDto).collect(Collectors.toList());

        return new PageImpl<>(content, orderPage.getPageable(), orderPage.getSize());
    }

    @Override
    public Page<OrderDTO> searchOrder(SearchRequest request) {
        SearchSpecification<Order> specification = new SearchSpecification<>(request);
        Pageable pageable = SearchSpecification.getPageable(request.getPage(), request.getSize());
        Page<Order> orderPage = orderRepository.findAll(specification, pageable);
        List<Order> orderList = orderPage.getContent();
        List<OrderDTO> content = orderList.stream().map(OrderMapper::toDto).collect(Collectors.toList());

        return new PageImpl<>(content, orderPage.getPageable(), orderPage.getSize());
    }

    @Override
    @Transactional
    public OrderDTO createOrder(OrderDTO orderDTO) {
        calculateTotalAmount(orderDTO);
        orderDTO.setOrderStatus(OrderStatus.IN_PENDING.getValue());
        orderDTO.setCourier(userService.getRandomCourier());
        orderDTO.setUser(userService.getUserById(SecurityUtil.getUserIdFromContext()));
        Order order = orderRepository.save(OrderMapper.toEntity(orderDTO));
        saveOrderBooks(order.getId(), orderDTO.getBooks());
        return OrderMapper.toDto(order);
    }

    private void checkBooks(BookDTO bookDTO) {
        Book bookEntity = bookRepository.findById(bookDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException(String.format(BOOK_NOT_FOUND, bookDTO.getId())));
        Integer quantity = bookEntity.getQuantity();
        Integer orderedQuantity = bookDTO.getQuantity();
        if (orderedQuantity <= quantity) {
            bookEntity.setQuantity(quantity - orderedQuantity);
            bookRepository.save(bookEntity);
        } else {
            throw new BadRequestException(String.format(QUANTITY_NOT_AVAILABLE, orderedQuantity));
        }
    }

    @Override
    @Transactional
    public void updateOrderStatus(OrderDTO orderDTO) {
        Order order = orderRepository.findById(orderDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException(String.format(ORDER_NOT_FOUND, orderDTO.getId())));
        if (order.getOrderStatus().equals(OrderStatus.CANCELLED)) {
            throw new BadRequestException(String.format(ORDER_ALREADY_CANCELLED, order.getId()));
        } else if (!orderDTO.getOrderStatus().equals(OrderStatus.CANCELLED.getValue())) {
            orderRepository.save(OrderMapper.toEntityForUpdate(order, orderDTO));
        } else {
            rollbackChanges(order);
        }
    }

    private void rollbackChanges(Order order) {
        updateBookQuantity(order.getId());
        order.setTotalAmount(0.0);
        order.setOrderStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);
    }

    private void saveOrderBooks(Integer orderId, List<BookDTO> books) {
        books.forEach(bookDTO -> {
            checkBooks(bookDTO);
            OrdersBooks ordersBooks = new OrdersBooks();
            ordersBooks.setOrderId(orderId);
            ordersBooks.setBookId(bookDTO.getId());
            ordersBooks.setBookQuantity(bookDTO.getQuantity());
            ordersBooksRepository.save(ordersBooks);
        });
    }

    private void updateBookQuantity(Integer orderId) {
        List<OrdersBooks> ordersBooks = ordersBooksRepository.findByOrderId(orderId);
        ordersBooks.forEach(relation -> {
            Book book = bookRepository.findById(relation.getBookId())
                    .orElseThrow(() -> new ResourceNotFoundException(String.format(BOOK_NOT_FOUND, relation.getBookId())));
            book.setQuantity(book.getQuantity() + relation.getBookQuantity());
            bookRepository.save(book);
        });
    }

    @Override
    public List<OrderDTO> getOrdersByStatus(boolean isDeleted) {
        return orderRepository.findByDeleted(isDeleted).stream().map(OrderMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public OrderDTO getOrderById(Integer id) {
        return orderRepository.findById(id).map(OrderMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(ORDER_NOT_FOUND, id)));
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
    public long getTotalOfOrders() {
        return orderRepository.findAll().size();
    }

    private void calculateTotalAmount(OrderDTO orderDTO) {
        double totalAmount = orderDTO.getBooks().stream().map(bookDTO -> {
            double price = bookRepository.findById(bookDTO.getId()).get().getPrice();
            return price * bookDTO.getQuantity();
        }).mapToDouble(Double::doubleValue).sum();
        orderDTO.setTotalAmount(totalAmount - (totalAmount * orderDTO.getDiscount()));
    }
}
