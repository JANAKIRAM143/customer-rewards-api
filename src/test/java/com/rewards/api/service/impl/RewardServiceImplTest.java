package com.rewards.api.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.rewards.api.dto.RewardSummaryResponse;
import com.rewards.api.entity.Transaction;
import com.rewards.api.exception.CustomerNotFoundException;
import com.rewards.api.repository.TransactionRepository;

/**
 * Unit tests for RewardServiceImpl.
 */
class RewardServiceImplTest {

	@Mock
	private TransactionRepository repository;

	@InjectMocks
	private RewardServiceImpl rewardService;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName("Should calculate rewards correctly for single customer")
	void shouldCalculateRewardsForCustomer() {

		List<Transaction> transactions = List.of(
				Transaction.builder().id(1L).customerId(1L).amount(120.0).transactionDate(LocalDate.of(2024, 1, 10))
						.build(),

				Transaction.builder().id(2L).customerId(1L).amount(75.0).transactionDate(LocalDate.of(2024, 1, 15))
						.build(),

				Transaction.builder().id(3L).customerId(1L).amount(40.0).transactionDate(LocalDate.of(2024, 2, 10))
						.build());

		when(repository.findByCustomerId(1L)).thenReturn(transactions);

		RewardSummaryResponse response = rewardService.fetchRewardsByCustomerId(1L);

		assertEquals(1L, response.getCustomerId());
		assertEquals(115, response.getMonthlyRewards().get("JANUARY"));
		assertEquals(115, response.getTotalRewards());
	}

	@Test
	@DisplayName("Should throw exception when no transactions found")
	void shouldThrowExceptionWhenNoTransactions() {

		when(repository.findByCustomerId(99L)).thenReturn(List.of());

		assertThrows(CustomerNotFoundException.class, () -> rewardService.fetchRewardsByCustomerId(99L));
	}

	@Test
	@DisplayName("Should calculate rewards for multiple customers")
	void shouldCalculateRewardsForAllCustomers() {

		List<Transaction> transactions = List.of(
				Transaction.builder().id(1L).customerId(1L).amount(120.0).transactionDate(LocalDate.of(2024, 1, 10))
						.build(),

				Transaction.builder().id(2L).customerId(2L) // ⭐ IMPORTANT different customer
						.amount(200.0).transactionDate(LocalDate.of(2024, 2, 5)).build());
		when(repository.findAll()).thenReturn(transactions);

		List<RewardSummaryResponse> responses = rewardService.fetchRewardsForAllCustomers();

		assertEquals(2, responses.size());
	}

	@Test
	@DisplayName("Should return empty list when no transactions present")
	void shouldReturnEmptyListWhenNoTransactions() {

		when(repository.findAll()).thenReturn(List.of());

		List<RewardSummaryResponse> responses = rewardService.fetchRewardsForAllCustomers();

		assertTrue(responses.isEmpty());
	}
}