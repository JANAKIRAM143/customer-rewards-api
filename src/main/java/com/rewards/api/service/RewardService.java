package com.rewards.api.service;

import java.util.List;

import com.rewards.api.dto.RewardSummaryResponse;

/**
 * Service responsible for customer reward calculations.
 */
public interface RewardService {

	/**
	 * Calculates reward summary for a given customer.
	 *
	 * @param customerId customer identifier
	 * @return reward summary containing monthly and total rewards
	 */
	RewardSummaryResponse fetchRewardsByCustomerId(Long customerId);

	/**
	 * Calculates reward summaries for all customers.
	 *
	 * @return list of reward summaries
	 */
	List<RewardSummaryResponse> fetchRewardsForAllCustomers();

}
