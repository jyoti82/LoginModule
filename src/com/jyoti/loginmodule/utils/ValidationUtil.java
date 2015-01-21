package com.jyoti.loginmodule.utils;

import java.util.regex.Pattern;

public class ValidationUtil {

	private static String usernameRegex = "^[\\p{L}\\d][\\p{L}\\d.@-]{3,24}";
	private static String emailIDRegex = "(?:[a-z0-9!#$%\\&'*+/=?\\^_`{|}~-]+(?:\\.[a-z0-9!#$%\\&'*+/=?\\^_`{|}"+"~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\"+"x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-"+"z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5"+"]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-"+"9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21"+"-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
	private static String passwordRegex = "^\\S{5,50}$";
	private static String zipCodeRegex = "(^\\d{5}(-\\d{4})?$)|(^[ABCEGHJKLMNPRSTVXYabceghjklmnprstvxy]{1}\\d{1}[A-Za-z]{1}[ ]?\\d{1}[A-Za-z]{1}[A-Za-z,0-9]{1}$)";
	public static final String deviceNameRegex = "^[a-zA-ZÀ-ÿ0-9_\\s-]{1,15}";
	
	public static boolean usernameValidation(String username) {
		if(!Pattern.matches(usernameRegex, username)) {
			return true;
		} else {
			return false;	
		}
	}
	
	public static boolean emptyField(String value) {
		if(value.length() == 0) {
			return true;
		} else {
			return false;			
		}
	}
	
	public static boolean emailValidation(String emailID) {
		if (emailID == null || emailID.length() < 6 || emailID.length() > 254) return true;
		boolean isValid = (!Pattern.compile(emailIDRegex, Pattern.CASE_INSENSITIVE).matcher(emailID).matches());
		if(emailID.indexOf("@") > 64)  return true;
		return isValid;
	}
	
	public static boolean passwordValidation(String password) {
		if(!Pattern.matches(passwordRegex, password)) {
			return true;
		} else {
			return false;	
		}
	}
	
	public static boolean zipCodeValidation(String zipCode) {
		if(!Pattern.matches(zipCodeRegex, zipCode)) {
			return true;
		} else {
			return false;	
		}
	}
	
	public static boolean deviceNameValidation(String name) {
		if (name == null) return false;
		return name.matches(deviceNameRegex);
	}

	public static boolean mobileValidation(String mobile) {
		// TODO Auto-generated method stub
		
		return false;
	}
}
