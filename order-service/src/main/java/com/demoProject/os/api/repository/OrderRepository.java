package com.demoProject.os.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demoProject.os.api.entity.Order;

public interface OrderRepository extends JpaRepository<Order,Integer> {

}
