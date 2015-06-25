package com.userprofile.otp;

import java.util.UUID;

import org.springframework.security.core.token.SecureRandomFactoryBean;

public class TestRandom {

	public static void main(String[] args) throws Exception {
		
		System.out.println(UUID.randomUUID());
		SecureRandomFactoryBean bean = new SecureRandomFactoryBean();
		System.out.println(bean.getObject().getSeed(8));
		
		OTPScheduler otpScheduler = new OTPScheduler();
		otpScheduler.createOTPFor5Minutes();
		Thread.sleep(234);
		System.out.println(otpScheduler.getRandomOTPString());
	}
}
