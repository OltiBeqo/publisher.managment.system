package com.publisher.managment.system.controller;

import com.publisher.managment.system.BaseTest;
import com.publisher.managment.system.dto.PaymentDTO;
import com.publisher.managment.system.service.PaymentService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
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
    @Disabled
    public void test_addPayment_ok() throws Exception {
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_ADMIN"));
        doReturn(new PaymentDTO()).when(toTest).addPayment(any());
        mvc.perform(MockMvcRequestBuilders.post("/payments"))
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(mapper.writeValueAsString(new PaymentDTO()))
                .andExpect(status().isOk());
    }

    @Test
    public void test_getPaymentsByStatus_ok() throws Exception {
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_ADMIN"));
        when(toTest.getPaymentsByStatus(false)).thenReturn(new ArrayList<>());
        mvc.perform(MockMvcRequestBuilders.get("/payments"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("[]"));
    }

    public void test_getPaymentById_ok() {

    }

    public void test_getTotalRevenue_ok() {

    }

    public void test_deletePayment_ok() {

    }

}
