package com.userprofile.otp;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import com.userprofile.util.CreateOTPUtil;

@Component
public class OTPScheduler {
	
	private final static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	String randomOTPString;
	
	public void createOTPFor5Minutes() {
		final Runnable otpGenerator = new Runnable() {
			public void run() {
				setRandomOTPString(CreateOTPUtil.getRandomString());
			}
		};
				
		scheduler.scheduleAtFixedRate(otpGenerator, 0, 5, TimeUnit.MINUTES);

	}
	/**
	 * @return the randomOTPString
	 */
	public String getRandomOTPString() {
		return randomOTPString;
	}
	/**
	 * @param randomOTPString the randomOTPString to set
	 */
	public final void setRandomOTPString(String randomOTPString) {
		this.randomOTPString = randomOTPString;
	}

}
