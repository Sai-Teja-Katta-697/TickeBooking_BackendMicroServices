package com.ticketbooking.booking_service.kafka;

import com.ticketbooking.avro.BookingEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaProducerConfig {
    @Bean
    public NewTopic bookingAvroTopic() {
        return new NewTopic(
                "booking-avro", // topic name
                3,              // number of partitions
                (short) 1        // replication factor
        );
    }
    @Bean
    public KafkaTemplate<String, BookingEvent> kafkaTemplate(ProducerFactory<String, BookingEvent> pf) {
        return new KafkaTemplate<>(pf);
    }
}
