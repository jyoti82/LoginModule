package com.jyoti.loginmodule;



public class Constants {


	public static final ApplicationConfig applicationConfig = MainApplication.getCustomConfiguration();
	
	//public static final String applicationName = MainApplication.getContext().getResources().getString(R.string.app_name);
	
	public static final String javaPackageName = "com.rovicorp.totalguide";
	
	public static final String PREFS = "TotalGuidePreferences";
	
	public static final String BRANDING_ASSETS_FOLDER_NAME = "branding";
	
	public static final String LogOnAppName = "Nike";
	
	public static final String localReplaceString = "{locale}";
	
	public static final String userSessionRenewalError = "userSessionRenewalError";
	
	public static final long resDownloadGapMilliSeconds = 3 * 24 * 60 * 60 * 1000;
	
	public static final long devicesDataExpiryTime = 6 * 60 * 60 * 1000;
	
	public static final long serviceDataExpiryTime = 6 * 60 * 60 * 1000;
	
	public static final long autoLoginPeriod = 9 * 60 * 60 *1000 + 50 * 60;
	
	// Number of activities to be maintained in back stack (VALUE >= 2)
	public static final int ACTIVITY_STACK_COUNT = 10;
	
	// Number of days of data to be fetched
	public static final int FETCH_DAYS = 14;

	public static final int DEFAULT_CACHE_EXPIRY_HOURS = 24;
	// Timeout in seconds for requests
	public static final int TIMEOUT_IN_SECONDS = 120;

	// Encryption key for storing preferences
	public static final String ENCRYPTION_KEY = "087c6f00-3001-11e3-aa6e-0800200c9a66";

	// Polling parameters for requests
	public static final int GET_RECORDINGS_SYNC_DELAY = 5 * 60 * 1000;
	
	// Program schedule 6d page size
	public static final int PROGRAM_SCHEDULE_PAGE_SIZE = 10;
	public static final int PROGRAM_SCHEDULE_FETCH_SIZE = 10;
	public static final int PROGRAM_SCHEDULE_CACHE_SIZE = 1000;

	// Request parameters for FOR YOU
	public static final int FOR_YOU_PAGE_SIZE = 32;
	public static final int FOR_YOU_MAX_COUNT = 32;
	public static final int PREFETCH_COUNT_FOR_FOR_YOU = 3;

	// Home screen slide show item count
	public static final int HOME_PREMIERINGS_COUNT = 5;
	// Request parameter for HOME PREMIERINGS
	public static final int HOME_PREMIERINGS_DURATION = 60;
	// Threshold items for HOME PREMIERINGS
	public static final int HOME_PREMIERINGS_THRESHOLD_ITEMS = 4;
	// Carousel scroll time
	public static final int HOME_PREMIERINGS_CAROUSEL_SCROLL_TIME = 5000;

	// Grid parameter for data slot duration
	public static final int GRID_SLOT_IN_HOURS = 4;
	public static final int PRIMETIME_SUBSLOT = 40;
	// Gap for advertisement in list
	public static final int LIST_ADVERTISE_GAP = 10;
	public static final int GRID_DETAILS_CACHE_SIZE = 1000;

	// Request parameter for SERVICE DETAILS
	public static int CHANNEL_THUMBNAIL_WIDTH;
	public static int CHANNEL_THUMBNAIL_HEIGHT;

	// List prefetch details parameters
	public static final int PREFETCH_COUNT_FOR_CELEB_CREDITS = 5;
	public static final int PREFETCH_COUNT_FOR_CELEB_SCHEDULE = 5;
	public static final int PREFETCH_COUNT_FOR_SIMILAR_PROGRAM = 5;
	// Search page size
	public static final int SEARCH_PAGE_SIZE = 30;
	public static final int SEARCH_FETCH_SIZE = 3;
	public static final int SEARCH_CACHE_SIZE = 1000;
	public static final int SEARCH_PREFETCH_DETAILS_SIZE = 5;
	public static final int SEARCH_TITLE_MAX_CHAR_COUNT = 30;
	
	// WhatsOn page size
	public static final int WHATSON_PAGE_SIZE = 13;
	public static final int WHATSON_FETCH_SIZE = 3;
	public static final int WHATSON_CACHE_SIZE = 1000;
	public static final int WHATSON_PREFETCH_DETAILS_SIZE = 5;
	
	// Image request sizes
	public static int HOME_SCREEN_CAROUSEL_IMAGE_WIDTH;
	public static int HOME_SCREEN_CAROUSEL_IMAGE_HEIGHT;
    
    public static int FAVORITES_IMAGE_WIDTH;
    public static int FAVORITES_IMAGE_HEIGHT;
    
    public static int DVR_IMAGE_WIDTH;
    public static int DVR_IMAGE_HEIGHT;
    
    public static int WHATS_ON_IMAGE_WIDTH;
    public static int WHATS_ON_IMAGE_HEIGHT;
    
    public static int GRID_LISTING_IMAGE_WIDTH;
    public static int GRID_LISTING_IMAGE_HEIGHT;
    
    public static int CAST_CREW_IMAGE_WIDTH;
    public static int CAST_CREW_IMAGE_HEIGHT;
    
    public static int CREDITS_IMAGE_WIDTH;
	public static int CREDITS_IMAGE_HEIGHT;
    
    public static int SEARCH_IMAGE_WIDTH;
    public static int SEARCH_IMAGE_HEIGHT;
    
    public static int CELEB_SCHEDULE_IMAGE_WIDTH;
    public static int CELEB_SCHEDULE_IMAGE_HEIGHT;
    
    public static int FOR_YOU_IMAGE_WIDTH;
    public static int FOR_YOU_IMAGE_HEIGHT;
    
    public static int PROGRAM_SYNOPSIS_IMAGE_WIDTH;
    public static int PROGRAM_SYNOPSIS_IMAGE_HEIGHT;
    
    public static int CELEBRITY_BIOGRAPHY_IMAGE_WIDTH;
    public static int CELEBRITY_BIOGRAPHY_IMAGE_HEIGHT;
    
    public static int SIMILAR_IMAGE_WIDTH;
    public static int SIMILAR_IMAGE_HEIGHT;
    
    public static int VIEW_BY_CHANNEL_WIDTH;
    public static int VIEW_BY_CHANNEL_HEIGHT;
    
    public static int PADDINGLEFT_RADIOBUTTON;
    
    public static boolean isGetMSOInfoFromServer = true;
    public static boolean isGetTimeZoneInfoFromServiceDetails = true;
    public static boolean disableSSLforInternalEnvironments = true;
    /*
    //Set these parameters in custom_parameters.xml
    public static void getDeviceSpecificValues() {
    	
    	CHANNEL_THUMBNAIL_WIDTH = MainApplication.getContext().getResources().getInteger(R.integer.channel_thumbnail_width);
    	CHANNEL_THUMBNAIL_HEIGHT = MainApplication.getContext().getResources().getInteger(R.integer.channel_thumbnail_height);
    	
    	HOME_SCREEN_CAROUSEL_IMAGE_WIDTH = MainApplication.getContext().getResources().getInteger(R.integer.home_screen_carousel_width);
    	HOME_SCREEN_CAROUSEL_IMAGE_HEIGHT = MainApplication.getContext().getResources().getInteger(R.integer.home_screen_carousel_height);
    	
    	FAVORITES_IMAGE_WIDTH = MainApplication.getContext().getResources().getInteger(R.integer.favorites_image_width);
    	FAVORITES_IMAGE_HEIGHT = MainApplication.getContext().getResources().getInteger(R.integer.favorites_image_height);
    	
    	DVR_IMAGE_WIDTH = MainApplication.getContext().getResources().getInteger(R.integer.dvr_image_width);
    	DVR_IMAGE_HEIGHT = MainApplication.getContext().getResources().getInteger(R.integer.dvr_image_height);
    	
    	WHATS_ON_IMAGE_WIDTH = MainApplication.getContext().getResources().getInteger(R.integer.whats_on_image_width);
    	WHATS_ON_IMAGE_HEIGHT = MainApplication.getContext().getResources().getInteger(R.integer.whats_on_image_height);
    	
    	GRID_LISTING_IMAGE_WIDTH = MainApplication.getContext().getResources().getInteger(R.integer.grid_listing_image_width);
    	GRID_LISTING_IMAGE_HEIGHT = MainApplication.getContext().getResources().getInteger(R.integer.grid_listing_image_height);
    	
    	CAST_CREW_IMAGE_WIDTH = MainApplication.getContext().getResources().getInteger(R.integer.cast_crew_image_width);
    	CAST_CREW_IMAGE_HEIGHT = MainApplication.getContext().getResources().getInteger(R.integer.cast_crew_image_height);
    	
    	CREDITS_IMAGE_WIDTH = MainApplication.getContext().getResources().getInteger(R.integer.credits_image_width);
    	CREDITS_IMAGE_HEIGHT = MainApplication.getContext().getResources().getInteger(R.integer.credits_image_height);
    	
    	SEARCH_IMAGE_WIDTH = MainApplication.getContext().getResources().getInteger(R.integer.search_image_width);
    	SEARCH_IMAGE_HEIGHT = MainApplication.getContext().getResources().getInteger(R.integer.search_image_height);
    	
    	CELEB_SCHEDULE_IMAGE_WIDTH = MainApplication.getContext().getResources().getInteger(R.integer.celeb_schedule_image_width);
    	CELEB_SCHEDULE_IMAGE_HEIGHT = MainApplication.getContext().getResources().getInteger(R.integer.celeb_schedule_image_height);
    	
    	FOR_YOU_IMAGE_WIDTH = MainApplication.getContext().getResources().getInteger(R.integer.for_you_image_width);
    	FOR_YOU_IMAGE_HEIGHT = MainApplication.getContext().getResources().getInteger(R.integer.for_you_image_height);
    	
    	PROGRAM_SYNOPSIS_IMAGE_WIDTH = MainApplication.getContext().getResources().getInteger(R.integer.program_synopsis_image_width);
    	PROGRAM_SYNOPSIS_IMAGE_HEIGHT = MainApplication.getContext().getResources().getInteger(R.integer.program_synopsis_image_height);
    	
    	CELEBRITY_BIOGRAPHY_IMAGE_WIDTH = MainApplication.getContext().getResources().getInteger(R.integer.celebrity_biography_image_width);
    	CELEBRITY_BIOGRAPHY_IMAGE_HEIGHT = MainApplication.getContext().getResources().getInteger(R.integer.celebrity_biography_image_height);
    	
    	SIMILAR_IMAGE_WIDTH = MainApplication.getContext().getResources().getInteger(R.integer.similar_image_width);
    	SIMILAR_IMAGE_HEIGHT = MainApplication.getContext().getResources().getInteger(R.integer.similar_image_height);
    	
    	VIEW_BY_CHANNEL_WIDTH = MainApplication.getContext().getResources().getInteger(R.integer.view_by_channel_width);
    	VIEW_BY_CHANNEL_HEIGHT = MainApplication.getContext().getResources().getInteger(R.integer.view_by_channel_height);
    	
    	PADDINGLEFT_RADIOBUTTON = (int) MainApplication.getContext().getResources().getDimension(R.dimen.radio_btn_paddingLeft_pre_api16);
    }
*/


}
