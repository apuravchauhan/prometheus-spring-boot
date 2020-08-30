package com.apurav;

import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.instrument.MeterRegistry;
/**
 * 
 * @author apuravchauhan
 *
 */
@SpringBootApplication
@RestController
@RequestMapping("/")
public class PrometheusSpringBoot {

	private final MeterRegistry meterRegistry;

	public PrometheusSpringBoot(MeterRegistry meterRegistry) {
		this.meterRegistry = meterRegistry;
	}

	@GetMapping("/2xx")
    public String simulate2xxResponse() {
        meterRegistry.counter("orders.2xx","status","OK").increment();
        return "Got 2xx Response";
    }

	@GetMapping("/5xx")
	public String simulate5xxResponse() {
        meterRegistry.counter("orders.5xx","status","NOTOK").increment();
        return "Got 5xx Response";
    }
	
	@PostMapping("/alert-hook")
	public void receiveAlertHook(@RequestBody Map request) throws Exception {
		System.out.println(request);

	}
	public static void main(String[] args) {
		SpringApplication.run(PrometheusSpringBoot.class, args);
	}


}
