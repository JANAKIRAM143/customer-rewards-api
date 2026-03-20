package com.rewards.api.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.rewards.api.dto.RewardSummaryResponse;
import com.rewards.api.entity.Transaction;
import com.rewards.api.exception.CustomerNotFoundException;
import com.rewards.api.repository.TransactionRepository;
import com.rewards.api.service.RewardService;
import com.rewards.api.util.RewardCalculator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RewardServiceImpl implements RewardService {

	private final TransactionRepository repository;

	/**
	 * Calculates reward summary for a given customer.
	 *
	 * @param customerId customer identifier
	 * @return reward summary
	 * @throws CustomerNotFoundException when no transactions exist
	 */

	@Override
	public RewardSummaryResponse fetchRewardsByCustomerId(Long customerId) {

		List<Transaction> transactions = repository.findByCustomerId(customerId);

		if (transactions.isEmpty()) {
			throw new CustomerNotFoundException("No transactions found for customer: " + customerId);
		}

		Map<String, Integer> monthlyRewards = transactions.stream()
				.collect(Collectors.groupingBy(t -> t.getTransactionDate().getMonth().name(),
						Collectors.summingInt(t -> RewardCalculator.calculateRewardPoints(t.getAmount()))));

		int totalRewards = monthlyRewards.values().stream().mapToInt(Integer::intValue).sum();

		return RewardSummaryResponse.builder().customerId(customerId).monthlyRewards(monthlyRewards)
				.totalRewards(totalRewards).build();
	}

	/**
	 * Calculates reward summaries for all customers.
	 *
	 * @return list of reward summaries
	 */
	@Override
	public List<RewardSummaryResponse> fetchRewardsForAllCustomers() {

		List<Transaction> allTransactions = repository.findAll();

		Map<Long, List<Transaction>> grouped = allTransactions.stream()
				.collect(Collectors.groupingBy(Transaction::getCustomerId));

		return grouped.entrySet().stream().map(entry -> {

			Map<String, Integer> monthly = entry.getValue().stream()
					.collect(Collectors.groupingBy(t -> t.getTransactionDate().getMonth().name(),
							Collectors.summingInt(t -> RewardCalculator.calculateRewardPoints(t.getAmount()))));

			int total = monthly.values().stream().mapToInt(Integer::intValue).sum();

			return RewardSummaryResponse.builder().customerId(entry.getKey()).monthlyRewards(monthly)
					.totalRewards(total).build();
		}).toList();
	}
}