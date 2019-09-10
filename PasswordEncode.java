package com.nucleus.utility;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class PasswordEncode {
	
	
	/*----------------------encoding user password using BCrypt Algorithm------------------*/
	
	
	public static String encodePwd(String password)
	{
		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
		return encoder.encode(password);
		
	}
	
	
}


