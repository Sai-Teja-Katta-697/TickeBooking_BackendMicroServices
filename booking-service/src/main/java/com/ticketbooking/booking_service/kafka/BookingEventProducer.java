package com.ticketbooking.booking_service.kafka;

import com.ticketbooking.avro.BookingEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class BookingEventProducer {

    private final KafkaTemplate<String, BookingEvent> kafkaTemplate;

    public BookingEventProducer(KafkaTemplate<String, BookingEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(String bookingId, BookingEvent avroEvent) {
        kafkaTemplate.send("booking-avro", bookingId, avroEvent);
    }
}
