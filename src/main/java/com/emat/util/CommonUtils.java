package com.emat.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;


public class CommonUtils
{
  public static boolean isEmpty(String str)
  {
    if (str == null) {
      return true;
    }
    if (str.trim().equals("")) {
      return true;
    }
    return false;
  }
  
  public static boolean isInclude(String str, String searchStr)
  {
    int temp = StringUtils.indexOf(str, searchStr);
    if (temp > -1) {
      return true;
    }
    return false;
  }
  
  public static String passerStr(String str)
  {
    str = StringUtils.trimToEmpty(str);
    
    return str;
  }
  
  public static String passerStr(String str, String defaultVaule)
  {
    str = StringUtils.trimToEmpty(str);
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
  
  public static String encodeReference(String reference)
  {
    try
    {
      reference = URLEncoder.encode(reference, "UTF-8");
      reference = URLEncoder.encode(reference, "UTF-8");
    }
    catch (UnsupportedEncodingException e)
    {
      e.printStackTrace();
    }
    return reference;
  }
  
  public static String decodeReference(String reference)
  {
    try
    {
      reference = URLDecoder.decode(reference, "UTF-8");
      reference = URLDecoder.decode(reference, "UTF-8");
    }
    catch (UnsupportedEncodingException e)
    {
      e.printStackTrace();
    }
    return reference;
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
    str = str.replace("\"", "&quot;");
    
    return str;
  }
  
  public static String encodeStr(String str)
  {
    try
    {
      str = URLEncoder.encode(str, "UTF-8");
    }
    catch (UnsupportedEncodingException e)
    {
      e.printStackTrace();
    }
    return str;
  }
  
  public static String getIDOLTime(Long time, String returnTypeStr)
  {
    String timeStr = null;
    try
    {
      Long tempL = new Long(time.longValue() * 1000L);
      
      timeStr = DateUtils.DateFormat(new Date(tempL.longValue()), returnTypeStr);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return timeStr;
  }
  
  public static List<String> getFilePath(String filePath)
  {
    List<String> list = new ArrayList<String>();
    File file = new File(filePath);
    list = iteratorDir(file, list);
    
    return list;
  }
  
  public static List<String> iteratorDir(File file, List<String> list)
  {
    File[] subFileList = file.listFiles();
    File[] arrayOfFile1;
    int j = (arrayOfFile1 = subFileList).length;
    for (int i = 0; i < j; i++)
    {
      File subFile = arrayOfFile1[i];
      if (subFile.isDirectory()) {
        iteratorDir(subFile, list);
      } else {
        list.add(subFile.getAbsolutePath());
      }
    }
    return list;
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
  
  public static String IDOLEscape(String src)
  {
    try
    {
      src = URLEncoder.encode(src, "UTF-8");
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException) {}
    StringBuffer tmp = new StringBuffer();
    tmp.ensureCapacity(src.length() * 6);
    for (int i = 0; i < src.length(); i++)
    {
      char j = src.charAt(i);
      if ((Character.isDigit(j)) || (Character.isLowerCase(j)) || 
        (Character.isUpperCase(j)) || (String.valueOf(j).equals("%")))
      {
        tmp.append(j);
      }
      else if (j < 'Ā')
      {
        tmp.append("%");
        if (j < '\020') {
          tmp.append("0");
        }
        tmp.append(Integer.toString(j, 16));
      }
      else
      {
        tmp.append("%u");
        tmp.append(Integer.toString(j, 16));
      }
    }
    return tmp.toString();
  }
}
