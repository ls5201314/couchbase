 package com.emat.util;
 
 import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
 public class StringUtils
 {
   public static boolean isEmpty(String str)
   {
     if (str == null) {
       return true;
     }
 
     return str.trim().equals("");
   }
 
   public static boolean isInclude(String str, String searchStr)
   {
     int temp = org.apache.commons.lang3.StringUtils.indexOf(str, searchStr);
 
     return temp > -1;
   }
 
   public static String passerStr(String str)
   {
     str = org.apache.commons.lang3.StringUtils.trimToEmpty(str);
 
     return str;
   }
 
   public static String passerStr(String str, String defaultVaule)
   {
     str = org.apache.commons.lang3.StringUtils.trimToEmpty(str);
     if (str.equals("")) {
       str = defaultVaule;
     }
 
     return str;
   }
 
   public static String getTrainingText(String queryText)
   {
     String destStr = queryText;
     destStr = destStr.replaceAll("\\sOR", " ");
     destStr = destStr.replaceAll("\\sAND", " ");
     destStr = destStr.replaceAll("\\sNOT", " ");
     destStr = destStr.replaceAll("\\(", "");
     destStr = destStr.replaceAll("\\)", "");
     return destStr;
   }
 
   public static String formatStr(String strText)
   {
     if (!isEmpty(strText))
     {
       strText = strText.replace("<", "&lt;");
       strText = strText.replace(">", "&gt;");
       strText = strText.replace("\"", "&quot;");
 
       strText = strText.replace("&lt;font color=red&gt;", "<font color=red>");
       strText = strText.replace("&lt;/font&gt;", "</font>");
     }
 
     return strText;
   }
 
   public static String disableHtmlStr(String str)
   {
     str = str.replace("<", "&lt;");
     str = str.replace(">", "&gt;");
     str = str.replace("\"", "&quot;").replace("'", "&apos;").replace("&", "&amp;").replace("select", "").replace(".html", "").replace("frame", "").replace("iframe", "").replace("alert", "").replace("onMouse", "").replace("expression", "");
 
     return str;
   }
 
   public static String encodeStr(String str)
   {
     try
     {
       str = URLEncoder.encode(str, "UTF-8");
     } catch (UnsupportedEncodingException e) {
       e.printStackTrace();
     }
     return str;
   }
 
   public static String deleteEndWithStr(String str, String endWithStr)
   {
     if ((isEmpty(str)) || (isEmpty(endWithStr))) {
       return str;
     }
 
     if (str.endsWith(endWithStr)) {
       str = str.substring(0, str.length() - endWithStr.length());
     }
     return str;
   }
 
   public static boolean isHasNumber(String str)
   {
     Pattern pattern = Pattern.compile("[0-9]+");
     return pattern.matcher(str).find();
   }
   
   public static String toString(Object o) {
		return (o == null) ? "" : o.toString();
	}

   public static String toString(Object o, String defaultValue) {
	   
		return (o == null) ? defaultValue : o.toString();
	}
   
   public static int atoi(String number, int defaultValue)
   {
     int returnValue = defaultValue;
     if (number != null) {
       try
       {
         returnValue = Integer.parseInt(number);
       }
       catch (NumberFormatException nfe) {}
     }
     return returnValue;
   }
   
   public static String formatDate(String date)
   {
     if (!isEmpty(date))
     { 
       date = date.trim();
       date = date.replace("\\", " ");
       date = date.replace("-", " ");
       date = date.replace("/", " ");
       date = date.replaceAll("\\s+", ".");
     }
 
     return date;
   }
   
   /**
    * 
    * @param 去除末尾特殊符号
    * @return
    */
   public static String removeLastSymbol(Object obj)
   {
	 String str = toString(obj);
	 if(isEmpty(str))
		 return "";
	 String eL = "[;_；.。\\*]$";
	 Pattern p = Pattern.compile(eL);
	 Matcher m = p.matcher(str);
	 if(m.find())
		 str = str.substring(0, str.length()-1);
     return str;
   }
   
 }

