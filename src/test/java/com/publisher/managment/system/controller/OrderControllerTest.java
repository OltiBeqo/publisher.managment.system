package com.publisher.managment.system.controller;

import com.publisher.managment.system.BaseTest;
import com.publisher.managment.system.dto.BookDTO;
import com.publisher.managment.system.dto.OrderDTO;
import com.publisher.managment.system.service.OrderService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest extends BaseTest {
    @MockBean
    private OrderService toTest;

    @Test
    @Disabled
    public void test_getOrdersByStatus_ok() throws Exception{
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_ADMIN"));
        when(toTest.getOrdersByStatus(anyBoolean())).thenReturn(new ArrayList<>());
        mvc.perform(MockMvcRequestBuilders.get("/orders")
                        .param("isDeleted", String.valueOf(false))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString("[]")))
                .andExpect(status().isOk());
    }

    @Test
    public void test_getOrdersByClient_ok() throws Exception{
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_ADMIN"));
        when(toTest.getOrdersByClient(any())).thenReturn(new ArrayList<>());
        mvc.perform(MockMvcRequestBuilders.get("/orders/library/{libraryId}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString("[]")))
                .andExpect(status().isOk());
    }

    @Test
    public void test_getOrderById_ok() throws Exception {
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_ADMIN"));
        when(toTest.getOrderById(any())).thenReturn(new OrderDTO());
        mvc.perform(MockMvcRequestBuilders.get("/orders/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    public void test_getOrdersAssigned_ok() throws Exception{
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_COURIER"));
        when(toTest.getOrdersAssigned()).thenReturn(new ArrayList<>());
        mvc.perform(MockMvcRequestBuilders.get("/orders/assigned")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString("[]")))
                .andExpect(status().isOk());
    }

    @Test
    public void test_createOrder_ok() throws Exception{
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_ADMIN"));
        doReturn(new OrderDTO()).when(toTest).createOrder(any());
        mvc.perform(MockMvcRequestBuilders.post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(new OrderDTO())))
                .andExpect(status().isOk());
    }

    @Test
    public void test_updateOrder_ok() throws Exception {
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_ADMIN"));
        doNothing().when(toTest).updateOrderStatus(any());
        mvc.perform(MockMvcRequestBuilders.put("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(new OrderDTO())))
                .andExpect(status().isOk());
    }

    @Test
    public void test_deleteOrder_ok() throws Exception{
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_ADMIN"));
        doNothing().when(toTest).deleteOrderById(any());
        mvc.perform(MockMvcRequestBuilders.delete("/orders/{id}", 1))
                .andExpect(status().isOk());
    }

}
