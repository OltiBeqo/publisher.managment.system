package com.publisher.managment.system.service.impl;

import com.publisher.managment.system.entity.Library;
import com.publisher.managment.system.entity.Order;
import com.publisher.managment.system.entity.Payment;
import com.publisher.managment.system.entity.User;
import com.publisher.managment.system.entity.enums.Gender;
import com.publisher.managment.system.entity.enums.OrderStatus;
import com.publisher.managment.system.entity.enums.PaymentMethod;
import com.publisher.managment.system.entity.enums.Role;
import com.publisher.managment.system.exception.ResourceNotFoundException;
import com.publisher.managment.system.repository.PaymentRepository;
import com.publisher.managment.system.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PaymentServiceImplTest {

    @MockBean
    private PaymentRepository paymentRepository;
    @Autowired
    private PaymentService toTest;

    public void test_addPayment_ok() {

    }

    public void test_updatePayment_ok() {

    }

    @Test
    public void test_getPayments_emptyList() {
        when(paymentRepository.findByDeleted(anyBoolean())).thenReturn(new ArrayList<>());
        assertTrue(toTest.getPaymentsByStatus(anyBoolean()).isEmpty());
    }

    @Test
    public void test_getPayments_ok() {

        User user = new User();
        user.setId(1);
        user.setUsername("test");
        user.setPassword("test");
        user.setFirstname("test");
        user.setLastname("test");
        user.setOrders(new ArrayList<>());
        user.setRole(Role.EMPLOYEE);
        user.setGender(Gender.MALE);
        user.setCreatedAt(LocalDate.of(2023, 1, 1).atStartOfDay());
        user.setModifiedAt(LocalDate.of(2023, 1, 1).atStartOfDay());

        User courier = new User();
        courier.setId(1);
        courier.setUsername("test");
        courier.setPassword("test");
        courier.setFirstname("test");
        courier.setLastname("test");
        courier.setOrders(new ArrayList<>());
        courier.setRole(Role.COURIER);
        courier.setGender(Gender.MALE);
        courier.setCreatedAt(LocalDate.of(2023, 1, 1).atStartOfDay());
        courier.setModifiedAt(LocalDate.of(2023, 1, 1).atStartOfDay());

        Library library = new Library();
        library.setId(1);
        library.setLibrary("Library");
        library.setAddress("test");
        library.setEmail("test@test.com");
        library.setPhoneNumber("12345678");
        library.setDeleted(false);
        library.setCreatedAt(LocalDate.of(2023, 1, 1).atStartOfDay());
        library.setModifiedAt(LocalDate.of(2023, 1, 1).atStartOfDay());

        Order order = new Order();
        order.setUser(user);
        order.setCourier(courier);
        order.setLibrary(library);
        order.setPayment(new Payment());
        order.setId(1);
        order.setComment("Comment");
        order.setTotalAmount(10.0d);
        order.setDiscount(10.0d);
        order.setOrderStatus(OrderStatus.COMPLETED);
        order.setDeleted(false);
        order.setCreatedAt(LocalDate.of(2023, 1, 1).atStartOfDay());
        order.setModifiedAt(LocalDate.of(2023, 1, 1).atStartOfDay());

        Payment payment = new Payment();
        payment.setId(1);
        payment.setOrder(order);
        payment.setPaymentMethod(PaymentMethod.CASH);
        payment.setAmount(10.0d);
        payment.setTransactionId("any number");
        payment.setDeleted(false);
        payment.setCreatedAt(LocalDate.of(2023, 1, 1).atStartOfDay());
        payment.setModifiedAt(LocalDate.of(2023, 1, 1).atStartOfDay());

        ArrayList<Payment> paymentList = new ArrayList<>();
        paymentList.add(payment);
        when(paymentRepository.findByDeleted(anyBoolean())).thenReturn(paymentList);
        assertEquals(1, toTest.getPaymentsByStatus(anyBoolean()).size());
        verify(paymentRepository).findByDeleted(anyBoolean());
    }

    @Test
    public void test_deletePayment_ok() {
        User user = new User();
        user.setId(1);
        user.setUsername("test");
        user.setPassword("test");
        user.setFirstname("test");
        user.setLastname("test");
        user.setOrders(new ArrayList<>());
        user.setRole(Role.EMPLOYEE);
        user.setGender(Gender.MALE);
        user.setCreatedAt(LocalDate.of(2023, 1, 1).atStartOfDay());
        user.setModifiedAt(LocalDate.of(2023, 1, 1).atStartOfDay());

        User courier = new User();
        courier.setId(1);
        courier.setUsername("test");
        courier.setPassword("test");
        courier.setFirstname("test");
        courier.setLastname("test");
        courier.setOrders(new ArrayList<>());
        courier.setRole(Role.COURIER);
        courier.setGender(Gender.MALE);
        courier.setCreatedAt(LocalDate.of(2023, 1, 1).atStartOfDay());
        courier.setModifiedAt(LocalDate.of(2023, 1, 1).atStartOfDay());

        Library library = new Library();
        library.setId(1);
        library.setLibrary("Library");
        library.setAddress("test");
        library.setEmail("test@test.com");
        library.setPhoneNumber("12345678");
        library.setDeleted(false);
        library.setCreatedAt(LocalDate.of(2023, 1, 1).atStartOfDay());
        library.setModifiedAt(LocalDate.of(2023, 1, 1).atStartOfDay());

        Order order = new Order();
        order.setUser(user);
        order.setCourier(courier);
        order.setLibrary(library);
        order.setPayment(new Payment());
        order.setId(1);
        order.setComment("Comment");
        order.setTotalAmount(10.0d);
        order.setDiscount(10.0d);
        order.setOrderStatus(OrderStatus.COMPLETED);
        order.setDeleted(false);
        order.setCreatedAt(LocalDate.of(2023, 1, 1).atStartOfDay());
        order.setModifiedAt(LocalDate.of(2023, 1, 1).atStartOfDay());

        Payment payment = new Payment();
        payment.setId(1);
        payment.setOrder(order);
        payment.setPaymentMethod(PaymentMethod.CASH);
        payment.setAmount(10.0d);
        payment.setTransactionId("any number");
        payment.setDeleted(false);
        payment.setCreatedAt(LocalDate.of(2023, 1, 1).atStartOfDay());
        payment.setModifiedAt(LocalDate.of(2023, 1, 1).atStartOfDay());

        Optional<Payment> result = Optional.of(payment);
        doNothing().when(paymentRepository).delete(Mockito.<Payment>any());
        when(paymentRepository.findById(Mockito.<Integer>any())).thenReturn(result);
        toTest.deletePayment(1);
        verify(paymentRepository).findById(Mockito.<Integer>any());
        verify(paymentRepository).delete(Mockito.<Payment>any());

    }

    @Test
    public void test_deletePayment_ko(){

        Optional<Payment> result = Optional.of(new Payment());
        doThrow(new ResourceNotFoundException("payment not found")).when(paymentRepository).delete(Mockito.<Payment>any());
        when(paymentRepository.findById(Mockito.<Integer>any())).thenReturn(result);
        assertThrows(ResourceNotFoundException.class, () -> toTest.deletePayment(1));
        verify(paymentRepository).findById(Mockito.<Integer>any());
        verify(paymentRepository).delete(Mockito.<Payment>any());
    }

    @Test
    public void test_getTotalRevenue_ok() {

        User courier = new User();
        courier.setId(1);
        courier.setUsername("test");
        courier.setPassword("test");
        courier.setFirstname("test");
        courier.setLastname("test");
        courier.setRole(Role.COURIER);
        courier.setGender(Gender.MALE);
        courier.setCreatedAt(LocalDate.of(2023, 1, 1).atStartOfDay());
        courier.setModifiedAt(LocalDate.of(2023, 1, 1).atStartOfDay());
        courier.setOrders(new ArrayList<>());

        Library library = new Library();
        library.setId(1);
        library.setLibrary("Library");
        library.setAddress("test");
        library.setEmail("test@test.com");
        library.setPhoneNumber("123456789");
        library.setDeleted(false);
        library.setCreatedAt(LocalDate.of(2023, 1, 1).atStartOfDay());
        library.setModifiedAt(LocalDate.of(2023, 1, 1).atStartOfDay());

        Order order = new Order();
        order.setId(1);
        order.setComment("Comment");
        order.setLibrary(library);
        order.setPayment(new Payment());
        order.setUser(new User());
        order.setCourier(courier);
        order.setOrderStatus(OrderStatus.COMPLETED);
        order.setDiscount(10.0d);
        order.setTotalAmount(10.0d);
        order.setDeleted(false);
        order.setCreatedAt(LocalDate.of(2023, 1, 1).atStartOfDay());
        order.setModifiedAt(LocalDate.of(2023, 1, 1).atStartOfDay());

        Payment payment = new Payment();
        payment.setId(1);
        payment.setOrder(order);
        payment.setAmount(10.0d);
        payment.setPaymentMethod(PaymentMethod.CASH);
        payment.setTransactionId("not important");
        payment.setDeleted(true);
        payment.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        payment.setModifiedAt(LocalDate.of(1970, 1, 1).atStartOfDay());

        ArrayList<Payment> paymentList = new ArrayList<>();
        paymentList.add(payment);
        when(paymentRepository.findAll()).thenReturn(paymentList);
        assertEquals(10.0d, toTest.getTotalRevenue().doubleValue());
        verify(paymentRepository).findAll();
    }

    @Test
    public void test_getPaymentByMethod_emptyList(){
        when(paymentRepository.findAllByPaymentMethod(Mockito.<PaymentMethod>any())).thenReturn(new ArrayList<>());
        assertTrue(toTest.getPaymentsByMethod(PaymentMethod.CASH).isEmpty());
        verify(paymentRepository).findAllByPaymentMethod(Mockito.<PaymentMethod>any());
    }
}
