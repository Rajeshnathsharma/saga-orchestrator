package com.design.order.eventhandlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.design.dto.OrchestratorRequestDTO;
import com.design.dto.OrchestratorResponseDTO;
import com.design.order.service.OrderEventUpdateService;

import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.Flux;

import java.util.function.Consumer;
import java.util.function.Supplier;

@Configuration
public class OrderEventHandler {

    @Autowired
    private DirectProcessor<OrchestratorRequestDTO> source;

    @Autowired
    private OrderEventUpdateService service;

    @Bean
    public Supplier<Flux<OrchestratorRequestDTO>> supplier(){
        return () -> Flux.from(source);
    };

    @Bean
    public Consumer<Flux<OrchestratorResponseDTO>> consumer(){
        return (flux) -> flux
                            .subscribe(responseDTO -> this.service.updateOrder(responseDTO));
    };

}
