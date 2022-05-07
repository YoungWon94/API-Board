package com.won.board.constant;

import org.springframework.context.i18n.LocaleContextHolder;

import java.util.HashMap;
import java.util.Locale;

public enum ErrorCode {

	NOT_FOUND("E0001", "조회 결과가 없습니다.", "", "")
	,NOT_ALLOW_STATUS("E0002", "허가되지 않은 상태입니다.", "", "")
	,INVALID_RESOURCE("E0003", "유효하지 않은 리소스입니다.", "", "")
	,PERMISSION_DENIED("E0004", "권한이 없습니다.", "", "")
	;











	private String code;
	private String messageKo;
	private String messageEn;
	private String messageJa;


	private ErrorCode(String code, String messageKo, String messageEn, String messageJa) {
		this.code = code;
		this.messageKo = messageKo;
		this.messageEn = messageEn;
		this.messageJa = messageJa;
	}


	public String getCode() {
		return code;
	}


	public String getMessage() {

		Locale locale = LocaleContextHolder.getLocale();

		String langCode = locale == null ? "" : locale.getLanguage();

		if("en".equals(langCode)) {
			return this.messageEn;
		}
		else if("ja".equals(langCode)) {
			return this.messageJa;
		}
		else {
			return this.messageKo;
		}

	}


	public HashMap<String, Object> getErrorData(){
		HashMap<String, Object> errorData = new HashMap<String, Object>();
		errorData.put("errorCode", code);
		return errorData;
	}




}
