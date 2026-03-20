package com.rewards.api.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents error response returned from API.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {

	private String status;
	private String message;
}

/**
 * Calculates reward summary for a given customer.
 *
 * @param customerId customer identifier
 * @return reward summary
 * @throws CustomerNotFoundException when no transactions exist
 */

/**
 * Calculates reward summaries for all customers.
 *
 * @return list of reward summaries
 */