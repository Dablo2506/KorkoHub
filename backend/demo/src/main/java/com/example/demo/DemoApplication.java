package com.example.demo;

import com.example.demo.Model.User.UserRepository;
import com.example.demo.Model.User.Users;
import org.antlr.v4.runtime.tree.pattern.ParseTreePattern;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
@EntityScan(basePackages = "com.example.demo.Model")
public class DemoApplication {


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	@GetMapping("/api/check-values")
	public Response checkValues(@RequestParam String value1, @RequestParam String value2) {
		if (value1.equals(value2)) {
			return new Response(1);
		} else {
			return new Response(0);
		}
	}
}
