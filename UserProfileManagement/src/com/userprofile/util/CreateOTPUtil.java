package com.userprofile.util;

import java.security.SecureRandom;
import java.util.Random;

/**
 * @author analian
 *
 */
public class CreateOTPUtil {

	private static char[] VALID_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456879!\"#$%&'()*+`-./:;<>=?@[]\\^_,".toCharArray();

	private static int NUMBER_OF_OTP_CHARACTERS=8;
	
	/**
	 * @param numChars
	 * @return
	 */
	public static String randomAlphaNumericString(int numChars) {
		SecureRandom srand = new SecureRandom();
		Random rand = new Random();
		char[] buff = new char[numChars];

		for (int i = 0; i < numChars; ++i) {
			if ((i % 19) == 0) {
				rand.setSeed(srand.nextLong()); // 128 bits of random!
			}
			buff[i] = VALID_CHARACTERS[rand.nextInt(VALID_CHARACTERS.length)];// 90
		}
		return new String(buff);
	}

	
	public static String getRandomString() {
		String retVal = randomAlphaNumericString(NUMBER_OF_OTP_CHARACTERS);
		int numOfSpecial = 0;
		int numOfLetters = 0;
		int numOfDigits = 0;

		if(!(validateRandomString(retVal, numOfSpecial, numOfLetters, numOfDigits))){
			retVal = getRandomString();
		}
		return retVal;
	}

	public static boolean validateRandomString(String randomStr,
			int numOfSpecial, int numOfLetters, int numOfDigits) {
		byte[] bytes = randomStr.getBytes();
		for (byte tempByte : bytes) {
			if ((tempByte >= 33 && tempByte <= 47) || (tempByte >= 58 && tempByte <= 64) || (tempByte >= 91 && tempByte <= 96)) {
				numOfSpecial++;
			}
			char tempChar = (char) tempByte;
			if (Character.isDigit(tempChar)) {
				numOfDigits++;
			}
			if (Character.isLetter(tempChar)) {
				numOfLetters++;
			}
		}
		if ((numOfDigits > 0) && (numOfLetters > 0) && (numOfSpecial > 0)) {
			return true;
		} else {
			return false;
		}
	}
}
