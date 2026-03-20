package com.rewards.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rewards.api.dto.ApiResponse;
import com.rewards.api.dto.RewardSummaryResponse;
import com.rewards.api.exception.ErrorResponse;
import com.rewards.api.service.RewardService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * REST Controller exposing reward related endpoints.
 */
@RestController
@RequestMapping("/api/rewards")
@RequiredArgsConstructor
@Tag(name = "Rewards API", description = "Operations related to customer reward calculations")
public class RewardController {

	private final RewardService rewardService;

	/**
	 * Fetch reward summary for a given customer.
	 *
	 * @param customerId unique customer identifier
	 * @return wrapped reward summary response
	 */
	@Operation(summary = "Get reward summary for a specific customer")
	@ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Rewards fetched successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class))),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Customer not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class), examples = @ExampleObject(value = """
					{
					  "status": "ERROR",
					  "message": "No transactions found for customer: 99"
					}
					"""))) })
	@GetMapping("/{customerId}")
	public ResponseEntity<ApiResponse<RewardSummaryResponse>> getRewardsByCustomer(
			@Parameter(description = "Customer Id", example = "1") @PathVariable Long customerId) {

		RewardSummaryResponse response = rewardService.fetchRewardsByCustomerId(customerId);

		return ResponseEntity.ok(ApiResponse.<RewardSummaryResponse>builder().status("SUCCESS")
				.message("Rewards fetched successfully").data(response).build());
	}

	/**
	 * Fetch reward summaries for all customers.
	 *
	 * @return wrapped list of reward summaries
	 */
	@Operation(summary = "Get reward summaries for all customers")
	@ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Rewards fetched successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class))) })
	@GetMapping
	public ResponseEntity<ApiResponse<List<RewardSummaryResponse>>> getAllRewards() {

		List<RewardSummaryResponse> responses = rewardService.fetchRewardsForAllCustomers();

		return ResponseEntity.ok(ApiResponse.<List<RewardSummaryResponse>>builder().status("SUCCESS")
				.message("All rewards fetched").data(responses).build());
	}
}
