package com.model2.mvc.common.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CommonUtil {
	
	///Field
	
	///Constructor
	
	///Method
	public static String null2str(String org, String converted) {  //���ڿ��� null�̰ų� ������ ��� ��ü ���ڿ��� ��ȯ
		if (org == null || org.trim().length() == 0)
			return converted;
		else
			return org.trim();
	}

	public static String null2str(String org) {   //���ڿ��� null�̰ų� ������ ��� �� ���ڿ��� ��ȯ
		return CommonUtil.null2str(org, "");
	}

	public static String null2str(Object org) {   //Object�� null�̸� �� ���ڿ���, �׷��� ������ BigDecimal �������� ��ȯ�Ͽ� ���ڿ��� ��ȯ
		if (org != null && org instanceof java.math.BigDecimal) {
			return CommonUtil.null2str((java.math.BigDecimal) org, "");
		} else {
			return CommonUtil.null2str((String) org, "");
		}
	}

	public static String null2str(java.math.BigDecimal org, String converted) {    //BigDecimal�� null�̸� ��ü ���ڿ���, �׷��� ������ ���ڿ��� ��ȯ
		if (org == null)
			return converted;
		else
			return org.toString();
	}

	public static String null2str(java.math.BigDecimal org) {  //BigDecimal�� null�̸� �� ���ڿ���, �׷��� ������ ���ڿ��� ��ȯ
		return CommonUtil.null2str(org, "");
	}

	public static String toDateStr(String dateStr) {   //8�ڸ��� ��¥ ���ڿ�(yyyyMMdd)�� yyyy/MM/dd �������� ��ȯ
		if (dateStr == null)
			return "";
		else if (dateStr.length() != 8)
			return dateStr;
		else
			return dateStr.substring(0, 4) + "/" + dateStr.substring(4, 6)
					+ "/" + dateStr.substring(6, 8);
	}

	public static String toDateStr(Timestamp date) {   //Timestamp ��ü�� yyyy/MM/dd HH:mm:ss ������ ���ڿ��� ��ȯ
		if (date == null)
			return "";
		else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			return sdf.format(new Date(date.getTime()));
		}
	}

	public static String toSsnStr(String ssnStr) {   // 13�ڸ��� �ֹε�Ϲ�ȣ ���ڿ��� XXXXXX-XXXXXXX �������� ��ȯ
		if (ssnStr == null)
			return "";
		else if (ssnStr.length() != 13)
			return ssnStr;
		else
			return ssnStr.substring(0, 6) + "-" + ssnStr.substring(6, 13);
	}

	public static String toAmountStr(String amountStr) {    //���� ������ ���ڿ��� õ ������ ��ǥ(,)�� �߰��Ͽ� ��ȯ
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

	public static String toAmountStr(java.math.BigDecimal amount) {  //BigDecimal�� ���ڿ��� ��ȯ�� �� toAmountStr(String amountStr)�� ȣ���Ͽ� ��ȯ
		if (amount == null) {
			return "";
		} else {
			return toAmountStr(amount.toString());
		}
	}
}
