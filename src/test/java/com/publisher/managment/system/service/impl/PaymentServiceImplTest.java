package com.publisher.managment.system.service.impl;

import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.publisher.managment.system.dto.BookDTO;
import com.publisher.managment.system.dto.LibraryDTO;
import com.publisher.managment.system.dto.OrderDTO;
import com.publisher.managment.system.dto.PaymentDTO;
import com.publisher.managment.system.dto.UserDTO;
import com.publisher.managment.system.repository.PaymentRepository;
import com.publisher.managment.system.service.OrderService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {PaymentServiceImpl.class})
@ExtendWith(SpringExtension.class)
class PaymentServiceImplTest {
    @MockBean
    private OrderService orderService;

    @MockBean
    private PaymentRepository paymentRepository;

    @Autowired
    private PaymentServiceImpl paymentServiceImpl;

    /**
     * Method under test: {@link PaymentServiceImpl#addPayment(PaymentDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddPayment() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.publisher.managment.system.service.impl.PaymentServiceImpl.addPayment(PaymentServiceImpl.java:29)
        //   See https://diff.blue/R013 to resolve this issue.

        paymentServiceImpl.addPayment(new PaymentDTO());
    }

    /**
     * Method under test: {@link PaymentServiceImpl#addPayment(PaymentDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddPayment2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.publisher.managment.system.service.impl.PaymentServiceImpl.addPayment(PaymentServiceImpl.java:31)
        //   See https://diff.blue/R013 to resolve this issue.

        when(orderService.getOrderById(Mockito.<Integer>any())).thenReturn(new OrderDTO());

        OrderDTO order = new OrderDTO();
        order.setId(1);

        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setOrder(order);
        paymentServiceImpl.addPayment(paymentDTO);
    }

    /**
     * Method under test: {@link PaymentServiceImpl#addPayment(PaymentDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddPayment3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.RuntimeException: Payment method null not found
        //       at com.publisher.managment.system.entity.enums.PaymentMethod.lambda$fromValue$1(PaymentMethod.java:16)
        //       at java.util.Optional.orElseThrow(Optional.java:290)
        //       at com.publisher.managment.system.entity.enums.PaymentMethod.fromValue(PaymentMethod.java:16)
        //       at com.publisher.managment.system.mapper.PaymentMapper.toEntity(PaymentMapper.java:28)
        //       at com.publisher.managment.system.service.impl.PaymentServiceImpl.addPayment(PaymentServiceImpl.java:32)
        //   See https://diff.blue/R013 to resolve this issue.

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setTotalAmount(10.0d);
        when(orderService.getOrderById(Mockito.<Integer>any())).thenReturn(orderDTO);

        OrderDTO order = new OrderDTO();
        order.setId(1);

        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setOrder(order);
        paymentServiceImpl.addPayment(paymentDTO);
    }

    /**
     * Method under test: {@link PaymentServiceImpl#addPayment(PaymentDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddPayment4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.RuntimeException: Payment method null not found
        //       at com.publisher.managment.system.entity.enums.PaymentMethod.lambda$fromValue$1(PaymentMethod.java:16)
        //       at java.util.Optional.orElseThrow(Optional.java:290)
        //       at com.publisher.managment.system.entity.enums.PaymentMethod.fromValue(PaymentMethod.java:16)
        //       at com.publisher.managment.system.mapper.PaymentMapper.toEntity(PaymentMapper.java:28)
        //       at com.publisher.managment.system.service.impl.PaymentServiceImpl.addPayment(PaymentServiceImpl.java:32)
        //   See https://diff.blue/R013 to resolve this issue.

        UserDTO user = new UserDTO();
        ArrayList<BookDTO> books = new ArrayList<>();
        LibraryDTO library = new LibraryDTO();
        LocalDateTime createdAt = LocalDate.of(1970, 1, 1).atStartOfDay();
        LocalDateTime modifiedAt = LocalDate.of(1970, 1, 1).atStartOfDay();
        when(orderService.getOrderById(Mockito.<Integer>any())).thenReturn(new OrderDTO(1, "Comment", 10.0d, user, 10.0d,
                "Order Status", books, library, createdAt, modifiedAt, new UserDTO(), true));

        OrderDTO order = new OrderDTO();
        order.setId(1);

        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setOrder(order);
        paymentServiceImpl.addPayment(paymentDTO);
    }

    /**
     * Method under test: {@link PaymentServiceImpl#addPayment(PaymentDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddPayment5() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.publisher.managment.system.service.impl.PaymentServiceImpl.addPayment(PaymentServiceImpl.java:31)
        //   See https://diff.blue/R013 to resolve this issue.

        when(orderService.getOrderById(Mockito.<Integer>any())).thenReturn(null);

        OrderDTO order = new OrderDTO();
        order.setId(1);

        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setOrder(order);
        paymentServiceImpl.addPayment(paymentDTO);
    }

    /**
     * Method under test: {@link PaymentServiceImpl#addPayment(PaymentDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddPayment6() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.publisher.managment.system.service.impl.PaymentServiceImpl.addPayment(PaymentServiceImpl.java:31)
        //   See https://diff.blue/R013 to resolve this issue.

        when(orderService.getOrderById(Mockito.<Integer>any())).thenReturn(new OrderDTO());

        OrderDTO order = new OrderDTO();
        order.setId(1);
        PaymentDTO paymentDTO = mock(PaymentDTO.class);
        when(paymentDTO.getOrder()).thenReturn(new OrderDTO());
        doNothing().when(paymentDTO).setOrder(Mockito.<OrderDTO>any());
        paymentDTO.setOrder(order);
        paymentServiceImpl.addPayment(paymentDTO);
    }

    /**
     * Method under test: {@link PaymentServiceImpl#addPayment(PaymentDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddPayment7() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.RuntimeException: Payment method Payment Method not found
        //       at com.publisher.managment.system.entity.enums.PaymentMethod.lambda$fromValue$1(PaymentMethod.java:16)
        //       at java.util.Optional.orElseThrow(Optional.java:290)
        //       at com.publisher.managment.system.entity.enums.PaymentMethod.fromValue(PaymentMethod.java:16)
        //       at com.publisher.managment.system.mapper.PaymentMapper.toEntity(PaymentMapper.java:28)
        //       at com.publisher.managment.system.service.impl.PaymentServiceImpl.addPayment(PaymentServiceImpl.java:32)
        //   See https://diff.blue/R013 to resolve this issue.

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setTotalAmount(10.0d);
        when(orderService.getOrderById(Mockito.<Integer>any())).thenReturn(orderDTO);

        OrderDTO order = new OrderDTO();
        order.setId(1);
        PaymentDTO paymentDTO = mock(PaymentDTO.class);
        when(paymentDTO.getId()).thenReturn(1);
        when(paymentDTO.getPaymentMethod()).thenReturn("Payment Method");
        doNothing().when(paymentDTO).setAmount(anyDouble());
        when(paymentDTO.getOrder()).thenReturn(new OrderDTO());
        doNothing().when(paymentDTO).setOrder(Mockito.<OrderDTO>any());
        paymentDTO.setOrder(order);
        paymentServiceImpl.addPayment(paymentDTO);
    }

    /**
     * Method under test: {@link PaymentServiceImpl#addPayment(PaymentDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddPayment8() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.publisher.managment.system.mapper.UserMapper.toEntity(UserMapper.java:27)
        //       at com.publisher.managment.system.mapper.OrderMapper.toEntity(OrderMapper.java:33)
        //       at com.publisher.managment.system.mapper.PaymentMapper.toEntity(PaymentMapper.java:30)
        //       at com.publisher.managment.system.service.impl.PaymentServiceImpl.addPayment(PaymentServiceImpl.java:32)
        //   See https://diff.blue/R013 to resolve this issue.

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setTotalAmount(10.0d);
        when(orderService.getOrderById(Mockito.<Integer>any())).thenReturn(orderDTO);

        OrderDTO order = new OrderDTO();
        order.setId(1);
        PaymentDTO paymentDTO = mock(PaymentDTO.class);
        when(paymentDTO.getAmount()).thenReturn(10.0d);
        when(paymentDTO.getId()).thenReturn(1);
        when(paymentDTO.getPaymentMethod()).thenReturn("CASH");
        doNothing().when(paymentDTO).setAmount(anyDouble());
        when(paymentDTO.getOrder()).thenReturn(new OrderDTO());
        doNothing().when(paymentDTO).setOrder(Mockito.<OrderDTO>any());
        paymentDTO.setOrder(order);
        paymentServiceImpl.addPayment(paymentDTO);
    }
}

