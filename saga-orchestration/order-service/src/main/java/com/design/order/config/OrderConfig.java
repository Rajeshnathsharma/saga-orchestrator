package com.design.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.design.dto.OrchestratorRequestDTO;

import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.FluxSink;

@Configuration
public class OrderConfig {

    @Bean
    public DirectProcessor<OrchestratorRequestDTO> publisher(){
        return DirectProcessor.create();
    }

    @Bean
    public FluxSink<OrchestratorRequestDTO> sink(DirectProcessor<OrchestratorRequestDTO> publisher){
        return publisher.sink();
    }

}
