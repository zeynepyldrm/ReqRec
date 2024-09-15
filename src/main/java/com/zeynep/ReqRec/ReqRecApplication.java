package com.zeynep.ReqRec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.zeynep.ReqRec")
public class ReqRecApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReqRecApplication.class, args);
	}

}
