package com.jyoti.loginmodule.models;





public class UserData {


	AuthenticatedUserInfo authUserInfo;
	String sessionSecurityToken;
	

	String countryCode;
	// This flag is set if proper service data is available (i.e. set -- even if null)
	private boolean serviceDataValid = false;
	

	// This flag is set if proper device data is available
	private boolean deviceDataValid = false;
	
	private boolean serviceDataFetched = false;

	public UserData(AuthenticatedUserInfo authUserInfo,String sessionSecurityToken) {
		super();
		this.authUserInfo = authUserInfo;
		this.sessionSecurityToken = sessionSecurityToken;
	}

	public AuthenticatedUserInfo getAuthenticatedUserInfo() {
		return authUserInfo;
	}
	
	public String getSessionSecurityToken() {
		return sessionSecurityToken;
	}
	

	public boolean isServiceDataValid() {
		return serviceDataValid;
	}
	
	
	
	public boolean isDeviceDataValid() {
		return deviceDataValid;
	}
	
	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public boolean isServiceDataFetched() {
		return serviceDataFetched;
	}

	public void setServiceDataFetched(boolean serviceDataFetched) {
		this.serviceDataFetched = serviceDataFetched;
	}

}
