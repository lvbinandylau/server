package com.lvbin.resource;

import com.lvbin.client.AuthClient;
import feign.Client;
import feign.Contract;
import feign.Feign;
import feign.RequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	private static final Logger log = LoggerFactory.getLogger(TestController.class);

	private AuthClient authClient;

	@Autowired
	public TestController(Decoder decoder, Encoder encoder, Client client, Contract contract, RequestInterceptor requestInterceptor) {
		this.authClient = Feign.builder().client(client)
				.encoder(encoder)
				.decoder(decoder)
				.contract(contract)
				.requestInterceptor(requestInterceptor)
				.target(AuthClient.class, "http://sso-auth");
	}

	@GetMapping("/user")
	public Authentication getUser(Authentication authentication) {
		log.info("resource: user {}", authentication);
		return authentication;
	}

	@GetMapping("/time")
	public String getTime() {
		return "res ==> auth: " + authClient.authTime();
	}

	@GetMapping("/lvbin")
	public String getLvbin() {
		return "lvbinandylau";
	}
}
