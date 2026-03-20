package com.rewards.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for Rewards API Spring Boot Application. Enables component
 * scanning from base package.
 */
@SpringBootApplication
public class RewardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(RewardsApplication.class, args);
	}

}
