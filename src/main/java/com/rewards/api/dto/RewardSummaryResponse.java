package com.rewards.api.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Response DTO containing reward summary.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RewardSummaryResponse {

	private Long customerId;

	private Map<String, Integer> monthlyRewards;

	private Integer totalRewards;
}
