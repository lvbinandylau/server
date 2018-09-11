package com.lvbin.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.Instant;

/**
 * @author Shenluw
 * 创建日期：2018/6/27 16:52
 */
@FeignClient(value = "authClient", configuration = com.lvbin.client.FeignClientConfig.class)
public interface AuthClient {

	@GetMapping("/api/time")
	Instant authTime();

}
