package com.jyoti.loginmodule;



public class GatewayStatusCode {

	
	public static final int Unknown = 0;
	public static final int Success = 200;
	public static final int NotFoundServerError = 404;
	public static final int InternalServerError = 500;
	public static final int ServiceError = 550;
	public static final int InvalidUsernamePassword = 600;
	public static final int InvalidDevice = 610;
	public static final int InvalidServiceId = 620;
	public static final int InvalidProviderId = 630;
	public static final int InvalidId = 640;
	public static final int InvalidArgument = 650;
	public static final int InvalidPageIndex = 660;
	public static final int InvalidStartDate = 670;
	public static final int InvalidDuration = 680;
	public static final int JsonParsingError = 1000;
	public static final int ConflictsOverriden = 500402;
	public static final int Conflict = 10534;
	public static final int UsernameExists = 602;
	public static final int EmailIdExists = 601;
	public static final int ProviderError = 603;
	public static final int InvalidValues = 604;
	public static final int TuneBusy = 500405;
	public static final int TuneInvalidChannel = 500406;
	public static final int TuneInvalidSource = 500407;
	public static final int TuneSourceNotAuthorized = 500408;
	public static final int TuneSourcePCBlocked = 500409;
	public static final int TunePowerIsOff = 500410;
	public static final int FluxServerRegistrationFailed = 10865;
	public static final int InvalidPin = 10846;
	public static final int ExpiredPin = 10847;
	
	//Not decoded back in ConvertToGatewayCode
	public static final int DeviceNameFormatError = 601;
		
	public static Integer ConvertToGatewayCode(Integer code) {
		Integer result = code;
		switch (code) {
			case GatewayStatusCode.Unknown:
			case GatewayStatusCode.Success:
			case GatewayStatusCode.NotFoundServerError:
			case GatewayStatusCode.InternalServerError:
			case GatewayStatusCode.ServiceError:
			case GatewayStatusCode.InvalidUsernamePassword:
			case GatewayStatusCode.InvalidDevice:
			case GatewayStatusCode.InvalidServiceId:
			case GatewayStatusCode.InvalidProviderId:
			case GatewayStatusCode.InvalidId:
			case GatewayStatusCode.InvalidArgument:
			case GatewayStatusCode.InvalidPageIndex:
			case GatewayStatusCode.InvalidStartDate:
			case GatewayStatusCode.InvalidDuration:
			case GatewayStatusCode.JsonParsingError:
			case GatewayStatusCode.ConflictsOverriden:
			case GatewayStatusCode.Conflict:
			case GatewayStatusCode.UsernameExists:
			case GatewayStatusCode.EmailIdExists:
			case GatewayStatusCode.InvalidValues:
			case GatewayStatusCode.TuneBusy:
			case GatewayStatusCode.TuneInvalidChannel:
			case GatewayStatusCode.TuneInvalidSource:
			case GatewayStatusCode.TuneSourceNotAuthorized:
			case GatewayStatusCode.TuneSourcePCBlocked:
			case GatewayStatusCode.TunePowerIsOff:
			case GatewayStatusCode.FluxServerRegistrationFailed:
			case GatewayStatusCode.InvalidPin:
			case GatewayStatusCode.ExpiredPin:
				break;
			default:
				result = GatewayStatusCode.Unknown;
				break;
		}
		return result;
	}



}
