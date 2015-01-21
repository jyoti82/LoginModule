package com.jyoti.loginmodule;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



public class ApplicationConfig {


	@JsonProperty("DefaultApplicationBaseUrl")
	private String defaultBaseUrl;
	//private static final String DEBUG = "debug";
	//private static final String RELEASE = "release";
	// Not required yet but may be required in future
	// private static final String PRODUCTION = "production";
	// private static final String DISTRIBUTION = "distribution";
	//@JsonProperty("Build")
	//private String buildType;
	/*
	@JsonProperty("MSOId")
	private String msoId = "0";

	@JsonProperty("AppSupportedLanguages")
	private List<String> applicationSupportedLanguages;

	
	
	public boolean isMultiTenant() {
		if(msoId.equals("0")) return true;
		return false;
	}

	public String getMsoId() {
		return msoId;
	}

	public void setMsoId(String msoId) {
		this.msoId = msoId;
		
	public List<String> getApplicationSupportedLanguages() {
		return applicationSupportedLanguages;
	}

	public void setApplicationSupportedLanguages(List<String> applicationSupportedLanguages) {
		this.applicationSupportedLanguages = applicationSupportedLanguages;
	}

	}
	*/
	
	
	//@JsonProperty("DefaultAdvertiseBaseUrl")
	//private String defaultBaseAdvertiseUrl;
	
	
/*
	public String getBuildType() {
		return buildType;
	}

	public void setBuildType(String buildType) {
		this.buildType = buildType;
	}
	
	public boolean enableEnvironmentSelection() {
		if(buildType.equalsIgnoreCase(RELEASE) || buildType.equalsIgnoreCase(DEBUG)) {
			return true;
		}
		return false;
	}
	*/
	public int getLogLevel() {
		
		/*
		if(buildType.equalsIgnoreCase(DEBUG)) {
			return LogService.LOG_LEVEL_VERBOSE;
		}
		return LogService.LOG_LEVEL_DISABLED;
		*/
		return 0;
	}

	public String getDefaultBaseUrl() {
		return defaultBaseUrl;
	}

	public void setDefaultBaseUrl(String defaultBaseUrl) {
		this.defaultBaseUrl = defaultBaseUrl;
	}
/*
	public String getDefaultBaseAdvertiseUrl() {
		return defaultBaseAdvertiseUrl;
	}

	public void setDefaultBaseAdvertiseUrl(String defaultBaseAdvertiseUrl) {
		this.defaultBaseAdvertiseUrl = defaultBaseAdvertiseUrl;
	}

*/
}
