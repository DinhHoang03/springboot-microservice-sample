package com.example.notification_service.listener;

import com.example.notification_service.event.OrderPlacedEvent;
import com.example.notification_service.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderEventListener {

    private final EmailService emailService;

    @KafkaListener(topics = "order-topic", groupId = "notification-group", containerFactory = "orderPlacedEventListenerFactory")
    public void handleOrderEvent(OrderPlacedEvent event) {
        System.out.println("Nhận được event từ kafka: " + event);

        //Thực thi email ở đây
        emailService.sendOrderEmail(event);
    }
}
