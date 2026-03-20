package com.rewards.api.util;

/**
 * Utility class responsible for reward calculation.
 */
public final class RewardCalculator {

	private RewardCalculator() {
	}

	/**
	 * Calculates reward points for given transaction amount.
	 *
	 * @param amount transaction amount
	 * @return reward points
	 */
	public static int calculateRewardPoints(double amount) {

		if (amount <= 50)
			return 0;

		if (amount <= 100)
			return (int) (amount - 50);

		return (int) ((amount - 100) * 2 + 50);
	}
}