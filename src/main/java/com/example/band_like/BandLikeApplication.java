package com.example.band_like;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BandLikeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BandLikeApplication.class, args);
	}

}
