package com.gahoccode.elasticsearch_playground;

import org.springframework.boot.SpringApplication;

public class TestElasticsearchPlaygroundApplication {

	public static void main(String[] args) {
		SpringApplication.from(ElasticsearchPlaygroundApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
