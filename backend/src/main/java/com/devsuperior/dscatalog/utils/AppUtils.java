package com.devsuperior.dscatalog.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AppUtils {
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public String crypt(String password) {
		return encoder.encode(password);
	}

}
