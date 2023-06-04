package com.publisher.managment.system.dto.projections;

public interface OrderSummary {

    Integer getLibrary_Id();
    Integer getTotal_Orders();

    interface TotalOrders{
        Integer getTotal_Orders();
    }
}
