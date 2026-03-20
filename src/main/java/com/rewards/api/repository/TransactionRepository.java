package com.rewards.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rewards.api.entity.Transaction;

/**
 * Repository for Transaction persistence operations.
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	List<Transaction> findByCustomerId(Long customerId);
}
