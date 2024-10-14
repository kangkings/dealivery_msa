package org.example.orders.domain.delivery.repository;

import org.example.orders.domain.delivery.model.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery,Long> {
}
