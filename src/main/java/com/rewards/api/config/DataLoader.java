package com.rewards.api.config;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rewards.api.entity.Transaction;
import com.rewards.api.repository.TransactionRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class DataLoader {

	private final TransactionRepository repository;

	@Bean
	CommandLineRunner loadData() {
		return args -> {

			repository.save(new Transaction(null, 1L, 120.0, LocalDate.now().minusMonths(1)));
			repository.save(new Transaction(null, 1L, 75.0, LocalDate.now().minusMonths(2)));
			repository.save(new Transaction(null, 1L, 200.0, LocalDate.now().minusMonths(3)));

			repository.save(new Transaction(null, 2L, 90.0, LocalDate.now().minusMonths(1)));
			repository.save(new Transaction(null, 2L, 40.0, LocalDate.now().minusMonths(2)));
			repository.save(new Transaction(null, 2L, 130.0, LocalDate.now().minusMonths(3)));
		};
	}
}