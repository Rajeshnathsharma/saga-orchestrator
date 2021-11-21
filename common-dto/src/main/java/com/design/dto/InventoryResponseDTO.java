package com.design.dto;



import java.util.UUID;

import com.design.enums.InventoryStatus;


public class InventoryResponseDTO {

    private UUID orderId;
    private Integer userId;
    private Integer productId;
    private InventoryStatus status;
	public UUID getOrderId() {
		return orderId;
	}
	public void setOrderId(UUID orderId) {
		this.orderId = orderId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public InventoryStatus getStatus() {
		return status;
	}
	public void setStatus(InventoryStatus status) {
		this.status = status;
	}
    
    

}
