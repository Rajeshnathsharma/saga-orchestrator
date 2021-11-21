package com.design.order.service;

import com.design.dto.OrchestratorRequestDTO;
import com.design.dto.OrderRequestDTO;
import com.design.dto.OrderResponseDTO;
import com.design.enums.OrderStatus;
import com.design.order.entity.PurchaseOrder;
import com.design.order.repository.PurchaseOrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.FluxSink;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class OrderService {

    // product price map
    private static final Map<Integer, Double> PRODUCT_PRICE =  Stream.of(
  		  new AbstractMap.SimpleEntry<>(1, 100d), 
  		  new AbstractMap.SimpleEntry<>(2, 200d),
  		  new AbstractMap.SimpleEntry<>(3, 300d))
  		  .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private FluxSink<OrchestratorRequestDTO> sink;

    public PurchaseOrder createOrder(OrderRequestDTO orderRequestDTO){
        PurchaseOrder purchaseOrder = this.purchaseOrderRepository.save(this.dtoToEntity(orderRequestDTO));
        this.sink.next(this.getOrchestratorRequestDTO(orderRequestDTO));
        return purchaseOrder;
    }

    public List<OrderResponseDTO> getAll() {
        return this.purchaseOrderRepository.findAll()
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    private PurchaseOrder dtoToEntity(final OrderRequestDTO dto){
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setId(dto.getOrderId());
        purchaseOrder.setProductId(dto.getProductId());
        purchaseOrder.setUserId(dto.getUserId());
        purchaseOrder.setStatus(OrderStatus.ORDER_CREATED);
        purchaseOrder.setPrice(PRODUCT_PRICE.get(purchaseOrder.getProductId()));
        return purchaseOrder;
    }

    private OrderResponseDTO entityToDto(final PurchaseOrder purchaseOrder){
        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setOrderId(purchaseOrder.getId());
        dto.setProductId(purchaseOrder.getProductId());
        dto.setUserId(purchaseOrder.getUserId());
        dto.setStatus(purchaseOrder.getStatus());
        dto.setAmount(purchaseOrder.getPrice());
        return dto;
    }

    public OrchestratorRequestDTO getOrchestratorRequestDTO(OrderRequestDTO orderRequestDTO){
        OrchestratorRequestDTO requestDTO = new OrchestratorRequestDTO();
        requestDTO.setUserId(orderRequestDTO.getUserId());
        requestDTO.setAmount(PRODUCT_PRICE.get(orderRequestDTO.getProductId()));
        requestDTO.setOrderId(orderRequestDTO.getOrderId());
        requestDTO.setProductId(orderRequestDTO.getProductId());
        return requestDTO;
    }

}
