package com.model2.mvc.common.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CommonUtil {
	
	///Field
	
	///Constructor
	
	///Method
	public static String null2str(String org, String converted) {  //문자열이 null이거나 공백일 경우 대체 문자열로 변환
		if (org == null || org.trim().length() == 0)
			return converted;
		else
			return org.trim();
	}

	public static String null2str(String org) {   //문자열이 null이거나 공백일 경우 빈 문자열로 변환
		return CommonUtil.null2str(org, "");
	}

	public static String null2str(Object org) {   //Object가 null이면 빈 문자열로, 그렇지 않으면 BigDecimal 형식으로 변환하여 문자열로 반환
		if (org != null && org instanceof java.math.BigDecimal) {
			return CommonUtil.null2str((java.math.BigDecimal) org, "");
		} else {
			return CommonUtil.null2str((String) org, "");
		}
	}

	public static String null2str(java.math.BigDecimal org, String converted) {    //BigDecimal이 null이면 대체 문자열로, 그렇지 않으면 문자열로 반환
		if (org == null)
			return converted;
		else
			return org.toString();
	}

	public static String null2str(java.math.BigDecimal org) {  //BigDecimal이 null이면 빈 문자열로, 그렇지 않으면 문자열로 반환
		return CommonUtil.null2str(org, "");
	}

	public static String toDateStr(String dateStr) {   //8자리의 날짜 문자열(yyyyMMdd)을 yyyy/MM/dd 형식으로 변환
		if (dateStr == null)
			return "";
		else if (dateStr.length() != 8)
			return dateStr;
		else
			return dateStr.substring(0, 4) + "/" + dateStr.substring(4, 6)
					+ "/" + dateStr.substring(6, 8);
	}

	public static String toDateStr(Timestamp date) {   //Timestamp 객체를 yyyy/MM/dd HH:mm:ss 형식의 문자열로 변환
		if (date == null)
			return "";
		else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			return sdf.format(new Date(date.getTime()));
		}
	}

	public static String toSsnStr(String ssnStr) {   // 13자리의 주민등록번호 문자열을 XXXXXX-XXXXXXX 형식으로 변환
		if (ssnStr == null)
			return "";
		else if (ssnStr.length() != 13)
			return ssnStr;
		else
			return ssnStr.substring(0, 6) + "-" + ssnStr.substring(6, 13);
	}

	public static String toAmountStr(String amountStr) {    //숫자 형식의 문자열을 천 단위로 쉼표(,)를 추가하여 변환
		String returnValue = "";
		if (amountStr == null)
			return returnValue;
		else {
			int strLength = amountStr.length();

			if (strLength <= 3)
				return amountStr;
			else {
				String s1 = "";
				String s2 = "";
				for (int i = strLength - 1; i >= 0; i--)
					s1 += amountStr.charAt(i);

				for (int i = strLength - 1; i >= 0; i--) {
					s2 += s1.charAt(i);
					if (i % 3 == 0 && i != 0)
						s2 += ",";
				}

				return s2;
			}
		}
	}

	public static String toAmountStr(java.math.BigDecimal amount) {  //BigDecimal을 문자열로 변환한 후 toAmountStr(String amountStr)를 호출하여 변환
		if (amount == null) {
			return "";
		} else {
			return toAmountStr(amount.toString());
		}
	}
}
