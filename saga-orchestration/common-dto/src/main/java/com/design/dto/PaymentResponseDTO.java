package com.design.dto;



import java.util.UUID;

import com.design.enums.PaymentStatus;


public class PaymentResponseDTO {
    private Integer userId;
    private UUID orderId;
    private Double amount;
    private PaymentStatus status;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public UUID getOrderId() {
		return orderId;
	}
	public void setOrderId(UUID orderId) {
		this.orderId = orderId;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public PaymentStatus getStatus() {
		return status;
	}
	public void setStatus(PaymentStatus status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "PaymentResponseDTO [userId=" + userId + ", orderId=" + orderId + ", amount=" + amount + ", status="
				+ status + "]";
	}
	
	
    
    
}
