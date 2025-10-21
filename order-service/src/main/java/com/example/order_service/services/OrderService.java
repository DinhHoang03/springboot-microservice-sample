package com.example.order_service.services;

import com.example.order_service.event.OrderPlacedEvent;
import com.example.order_service.model.Order;
import com.example.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    public Order createOrder(Order order) {
        Order saved = orderRepository.save(order);

        OrderPlacedEvent event = OrderPlacedEvent.builder()
                .orderId(saved.getId())
                .userId(saved.getUserId())
                .total(saved.getTotal())
                .build();

        kafkaTemplate.send("order-topic", event);
        System.out.println("Đã gửi Kafka event: " + event);

        return saved;
    }
}
