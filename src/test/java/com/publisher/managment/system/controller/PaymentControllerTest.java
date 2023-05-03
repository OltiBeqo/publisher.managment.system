package com.publisher.managment.system.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.publisher.managment.system.BaseTest;
import com.publisher.managment.system.dto.LibraryDTO;
import com.publisher.managment.system.dto.OrderDTO;
import com.publisher.managment.system.dto.PaymentDTO;
import com.publisher.managment.system.dto.UserDTO;
import com.publisher.managment.system.entity.enums.PaymentMethod;
import com.publisher.managment.system.service.PaymentService;
import com.sun.security.auth.UserPrincipal;

import java.time.LocalDate;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PaymentControllerTest extends BaseTest {
    @MockBean
    private PaymentService toTest;

    @Test
    public void test_addPayment_ok() throws Exception {
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_ADMIN"));
        doReturn(new PaymentDTO()).when(toTest).addPayment(any());
        mvc.perform(MockMvcRequestBuilders.post("/payments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(new PaymentDTO())))
                .andExpect(status().isOk());
    }

    @Test
    public void test_getPaymentsByStatus_ok() throws Exception {
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_ADMIN"));
        when(toTest.getPaymentsByStatus(anyBoolean())).thenReturn(new ArrayList<>());
        mvc.perform(MockMvcRequestBuilders.get("/payments")
                        .param("isDeleted", String.valueOf(false))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString("[]")))
                .andExpect(status().isOk());
    }

    @Test
    void test_getPaymentById_ok() throws Exception {
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_ADMIN"));
        when(toTest.getPaymentById(1)).thenReturn(new PaymentDTO());
        mvc.perform(MockMvcRequestBuilders.get("/payments/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(new PaymentDTO())))
                .andExpect(status().isOk());
    }

    @Test
    void test_getTotalRevenue_ok() throws Exception {
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_ADMIN"));
        when(toTest.getTotalRevenue()).thenReturn(1.0d);
        mvc.perform(MockMvcRequestBuilders.get("/payments/totalRevenue")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString("1.0")))
                .andExpect(status().isOk());
    }

    @Test
    void test_deletePayment_ok() throws Exception {
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_ADMIN"));
        doNothing().when(toTest).deletePayment(any());
        mvc.perform(MockMvcRequestBuilders.delete("/payments/{paymentId}", 1))
                .andExpect(status().isOk());
    }
}
