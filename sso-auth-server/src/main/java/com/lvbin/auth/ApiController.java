package com.lvbin.auth;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ApiController {
	private static final Logger log = LoggerFactory.getLogger(ApiController.class);

	@Autowired
	private UserDao userDao;

	private CustomUserDetailsServiceImpl customUserDetailsService;

	@RequestMapping("/api/user")
	public Authentication getUser(Authentication authentication) {
		return authentication;
	}

	@RequestMapping("/api/time")
	public Instant getTime() {
		return Instant.now();
	}

	@RequestMapping("/success")
	public String success( ) throws IOException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		log.warn(currentPrincipalName + " login success!");
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, String> map = new HashMap<>();
		map.put("msg", "success");
		return objectMapper.writeValueAsString(map);
	}

	@RequestMapping("/loginerror")
	public String loginerror( ) throws IOException {
		log.warn("login error!");
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, String> map = new HashMap<>();
		map.put("msg", "error");
		return objectMapper.writeValueAsString(map);
	}

	@RequestMapping("/getname")
	public String getname( ) throws IOException {
		log.warn("getname");
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, String> map = new HashMap<>();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		map.put("msg", currentPrincipalName);
		return objectMapper.writeValueAsString(map);
	}


	@RequestMapping(value = "/insertUser", method = RequestMethod.POST)
	public String InsertUser(@RequestParam("name") String username, @RequestParam("pwd") String password) throws IOException {
		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, String> map = new HashMap<>();
		String password2 = passwordEncoder.encode(password);
		log.warn(username + ":" + password2);
		String selectname = userDao.UserExisted(username);
		if (selectname == null || selectname.isEmpty())
		//if (userDao.findByName(username).getUsername().isEmpty())
		{
			userDao.InsertUser(username, password2, "user");
			map.put("msg", "success");
		}
		else
		{
			map.put("msg", "用户名已存在");
		}
		return objectMapper.writeValueAsString(map);
	}
}
