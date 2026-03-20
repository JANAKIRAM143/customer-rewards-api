package com.rewards.api.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.rewards.api.dto.RewardSummaryResponse;
import com.rewards.api.exception.CustomerNotFoundException;
import com.rewards.api.service.RewardService;

/**
 * Controller layer unit tests using WebMvcTest.
 */
@WebMvcTest(RewardController.class)
class RewardControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private RewardService rewardService;

	@Test
	@DisplayName("Should return wrapped reward response for valid customer")
	void shouldReturnRewardsForCustomer() throws Exception {

		RewardSummaryResponse mockResponse = RewardSummaryResponse.builder().customerId(1L)
				.monthlyRewards(Map.of("JANUARY", 90)).totalRewards(90).build();

		when(rewardService.fetchRewardsByCustomerId(1L)).thenReturn(mockResponse);

		mockMvc.perform(get("/api/rewards/1")).andExpect(status().isOk())
				.andExpect(jsonPath("$.status").value("SUCCESS"))
				.andExpect(jsonPath("$.message").value("Rewards fetched successfully"))
				.andExpect(jsonPath("$.data.customerId").value(1)).andExpect(jsonPath("$.data.totalRewards").value(90))
				.andExpect(jsonPath("$.data.monthlyRewards.JANUARY").value(90));
	}

	@Test
	@DisplayName("Should return 404 when customer not found")
	void shouldReturn404WhenCustomerNotFound() throws Exception {

		when(rewardService.fetchRewardsByCustomerId(99L))
				.thenThrow(new CustomerNotFoundException("No transactions found for customer: 99"));

		mockMvc.perform(get("/api/rewards/99")).andExpect(status().isNotFound())
				.andExpect(jsonPath("$.status").value("ERROR"))
				.andExpect(jsonPath("$.message").value("No transactions found for customer: 99"));
	}

	@Test
	@DisplayName("Should return wrapped list of all customer rewards")
	void shouldReturnAllRewards() throws Exception {

		List<RewardSummaryResponse> list = List.of(
				RewardSummaryResponse.builder().customerId(1L).monthlyRewards(Map.of("JANUARY", 90)).totalRewards(90)
						.build(),
				RewardSummaryResponse.builder().customerId(2L).monthlyRewards(Map.of("FEBRUARY", 120)).totalRewards(120)
						.build());

		when(rewardService.fetchRewardsForAllCustomers()).thenReturn(list);

		mockMvc.perform(get("/api/rewards")).andExpect(status().isOk()).andExpect(jsonPath("$.status").value("SUCCESS"))
				.andExpect(jsonPath("$.data[0].customerId").value(1))
				.andExpect(jsonPath("$.data[1].customerId").value(2));
	}
}