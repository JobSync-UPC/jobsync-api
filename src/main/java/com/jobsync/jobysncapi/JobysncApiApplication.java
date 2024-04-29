package com.jobsync.jobysncapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@OpenAPIDefinition(info = @Info(title = "JobSync API", version = "v1"))
@EnableFeignClients
@SpringBootApplication
public class JobysncApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(JobysncApiApplication.class, args);
	}
}
