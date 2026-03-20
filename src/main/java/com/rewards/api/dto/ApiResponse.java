package com.rewards.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Generic API Response wrapper.
 *
 * @param <T> response data type
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse<T> {

	private String status;

	private String message;

	private T data;
}
