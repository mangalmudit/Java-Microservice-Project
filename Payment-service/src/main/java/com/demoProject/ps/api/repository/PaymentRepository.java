package com.demoProject.ps.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demoProject.ps.api.Entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

	Payment findByorderId(int orderId);

}
