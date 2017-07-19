package com.liguo.hgl.proxydao.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.Vector;

public class ToolsUtil {
    public static Object transStringToObject(Class<?> paramType, Object objValue, boolean bIsString2Object) throws ParseException {

        Object outValue = null;

        if (paramType == String.class) {
            if (bIsString2Object)
                outValue = ((objValue == null || objValue.equals("null")) ? null : (String) objValue);
            else
                outValue = objValue;
        } else if (paramType == char.class) {
            if (bIsString2Object) {
                int c = ((objValue == null || objValue.equals("null")) ? 0 : Integer.parseInt((String) objValue));
                outValue = (char) c;
            } else {

                int c = String.valueOf(objValue).charAt(0);
                outValue = String.valueOf(c);
            }
        } else if (paramType == Date.class) {
            DateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (bIsString2Object)
                outValue = ((objValue == null || objValue.equals("null")) ? null : f.parse((String) objValue));
            else
                outValue = (objValue == null ? "null" : f.format(objValue));
        } else if (paramType == Double.class) {
            if (bIsString2Object)
                outValue = ((objValue == null || objValue.equals("null")) ? null : Double.valueOf((String) objValue));
            else
                outValue = String.valueOf(objValue);
        } else if (paramType == Long.class) {
            if (bIsString2Object)
                outValue = ((objValue == null || objValue.equals("null")) ? null : Long.valueOf((String) objValue));
            else
                outValue = String.valueOf(objValue);
        } else if (paramType == Integer.class) {
            if (bIsString2Object)
                outValue = ((objValue == null || objValue.equals("null")) ? null : Integer.valueOf((String) objValue));
            else
                outValue = String.valueOf(objValue);
        } else if (paramType == Float.class) {
            if (bIsString2Object)
                outValue = ((objValue == null || objValue.equals("null")) ? null : Float.valueOf((String) objValue));
            else
                outValue = String.valueOf(objValue);
        } else if (paramType == Boolean.class) {
            if (bIsString2Object)
                outValue = ((objValue == null || objValue.equals("null")) ? null : new Boolean((String) objValue));
            else
                outValue = ((Boolean) objValue).toString();
        } else if (paramType == char[].class) {
            if (bIsString2Object)
                outValue = ((objValue == null || objValue.equals("null")) ? null : ((String) objValue).toCharArray());
            else
                outValue = String.valueOf(objValue);
        } else if (paramType == int.class) {
            if (bIsString2Object)
                outValue = ((objValue == null || objValue.equals("null")) ? 0 : Integer.parseInt((String) objValue));
            else
                outValue = String.valueOf(objValue);
        } else if (paramType == boolean.class) {
            if (bIsString2Object)
                outValue = ((new Boolean((String) objValue)).booleanValue());
            else
                outValue = String.valueOf(objValue);
        } else if (paramType == float.class) {
            if (bIsString2Object)
                outValue = ((objValue == null || objValue.equals("null")) ? 0 : Float.parseFloat((String) objValue));
            else
                outValue = String.valueOf(objValue);
        } else if (paramType == double.class) {
            if (bIsString2Object)
                outValue = ((objValue == null || objValue.equals("null")) ? 0 : Double.parseDouble((String) objValue));
            else
                outValue = String.valueOf(objValue);
        } else if (paramType == long.class) {
            if (bIsString2Object)
                outValue = ((objValue == null || objValue.equals("null")) ? 0 : Long.parseLong((String) objValue));
            else
                outValue = String.valueOf(objValue);
        }
        return outValue;
    }

    public static boolean IsblankString(String objValue) {

        return (objValue == null || objValue.isEmpty()) ? true : false;
    }

    public static double String2double(String objValue) {

        return (objValue == null || objValue.equals("null")) ? 0 : Double.parseDouble(objValue);
    }

    /**
     * 判断指定的文件是否存在。
     * 
     * @param fileName
     *            要判断的文件的文件名
     * @return 存在时返回true，否则返回false。
     * @since 0.1
     */
    public static boolean isFileExist(String fileName) {

        boolean b = false;
        try {
            String fileStr = ToolsUtil.class.getClassLoader().getResource(fileName).toString();
            File file = new File(URLDecoder.decode((fileStr.substring(5, fileStr.length())), "UTF-8"));
            if (file.exists()) {
                b = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }

    public static Properties readPropertiesFile(Class<?> classutil, String filename) {

        Properties prop = new Properties();
        try {
            String fileStr = classutil.getClassLoader().getResource(filename).toString();
            File file = new File(URLDecoder.decode((fileStr.substring(5, fileStr.length())), "UTF-8"));
            if (file.exists()) {
                InputStream in = new FileInputStream(file);
                InputStreamReader read = new InputStreamReader(in, "UTF-8");
                prop.load(read);
                read.close();
                in.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prop;
    }

    public static String getUUID() {

        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        // 去掉"-"符号  
        String temp = str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
        //return str+","+temp; 
        return temp;
    }

    //获得指定数量的UUID  
    public static String[] getUUID(int number) {

        if (number < 1) {
            return null;
        }
        String[] ss = new String[number];
        for (int i = 0; i < number; i++) {
            ss[i] = getUUID();
        }
        return ss;
    }

    public static String URLEncode(String srcStr) {

        String dstStr = "";
        if (srcStr != null && !srcStr.isEmpty()) {
            try {
                dstStr = URLEncoder.encode(srcStr, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return dstStr;
    }

    public static String URLEncode(String srcStr, String charset) {

        String dstStr = "";
        if (srcStr != null && !srcStr.isEmpty()) {
            try {
                dstStr = URLEncoder.encode(srcStr, charset);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return dstStr;
    }

    public static String URLDecode(String srcStr) {

        String dstStr = "";
        if (srcStr != null && !srcStr.isEmpty()) {
            try {
                dstStr = URLDecoder.decode(srcStr, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return dstStr;
    }

    public static String URLDecode(String srcStr, String charset) {

        String dstStr = "";
        if (srcStr != null && !srcStr.isEmpty()) {
            try {
                dstStr = URLDecoder.decode(srcStr, charset);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return dstStr;
    }

    public static String escape(String src) {

        int i;
        char j;
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length() * 6);
        for (i = 0; i < src.length(); i++) {
            j = src.charAt(i);
            if (Character.isDigit(j) || Character.isLowerCase(j) || Character.isUpperCase(j))
                tmp.append(j);
            else if (j < 256) {
                tmp.append("%");
                if (j < 16)
                    tmp.append("0");
                tmp.append(Integer.toString(j, 16));
            } else {
                tmp.append("%u");
                tmp.append(Integer.toString(j, 16));
            }
        }
        return tmp.toString();
    }

    public static String unescape(String src) {

        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length());
        int lastPos = 0, pos = 0;
        char ch;
        while (lastPos < src.length()) {
            pos = src.indexOf("%", lastPos);
            if (pos == lastPos) {
                if (src.charAt(pos + 1) == 'u') {
                    ch = (char) Integer.parseInt(src.substring(pos + 2, pos + 6), 16);
                    tmp.append(ch);
                    lastPos = pos + 6;
                } else {
                    ch = (char) Integer.parseInt(src.substring(pos + 1, pos + 3), 16);
                    tmp.append(ch);
                    lastPos = pos + 3;
                }
            } else {
                if (pos == -1) {
                    tmp.append(src.substring(lastPos));
                    lastPos = src.length();
                } else {
                    tmp.append(src.substring(lastPos, pos));
                    lastPos = pos;
                }
            }
        }
        return tmp.toString();
    }

    @SuppressWarnings("unchecked")
    public static String[] split(final String str, final String delim) {

        List list = new ArrayList();
        StringTokenizer st = new StringTokenizer(str, delim);
        while (st.hasMoreElements()) {
            list.add(st.nextElement());
        }
        return (String[]) list.toArray(new String[list.size()]);
    }

    public static String StringtoHex(byte input[]) {

        if (input == null)
            return null;
        StringBuffer output = new StringBuffer(input.length * 2);
        for (int i = 0; i < input.length; i++) {
            int current = input[i] & 0xff;
            if (current < 16)
                output.append("0");
            output.append(Integer.toString(current, 16));
        }

        return output.toString();
    }

    public static String HextoString(String input) {

        if (input == null)
            return null;
        byte[] det = Asc2Bcd(input);
        return new String(det);
    }

    public static byte Asc2Num(byte c) {

        if (c >= 48 && c <= 63) // '0'-'9',':',';','<','=','>','?'
            return (byte) (c - 48);
        else if (c >= 65 && c <= 70) // 'A' - 'F'
            return (byte) (c - 65 + 10);
        else if (c >= 97 && c <= 102) // 'a' - 'f'
            return (byte) (c - 97 + 10);
        else
            return (byte) 0;
    }

    public static String Bcd2Asc(byte[] byteArray) {

        if (byteArray == null)
            return null;
        char charArray[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        StringBuffer stringbuffer = new StringBuffer(2 * byteArray.length);
        for (int i = 0; i < byteArray.length; i++) {
            stringbuffer.append(charArray[byteArray[i] >>> 4 & 0x0f]);
            stringbuffer.append(charArray[byteArray[i] & 0x0f]);
        }
        return stringbuffer.toString();
    }

    public static byte[] Asc2Bcd(String hexString) {

        byte[] rawBytes = hexString.getBytes();
        int strLen = rawBytes.length;
        byte[] strBytes;
        if (strLen % 2 != 0) {
            strBytes = new byte[strLen + 1];
            System.arraycopy(rawBytes, 0, strBytes, 0, strLen);
            strBytes[strLen] = 0x3F;
        } else {
            strBytes = rawBytes;
        }
        byte binData[] = new byte[(strLen + 1) / 2];
        int j = 0;
        for (int k = 0; k < strLen; k++) {
            byte hiByte = strBytes[k];
            byte lowByte = strBytes[++k];
            binData[j] = (byte) (Asc2Num(hiByte) * 16 + Asc2Num(lowByte));
            j++;
        }
        return binData;
    }

    @SuppressWarnings("unchecked")
    public static String StringFormat(String format1, Vector params) {

        String returnstr = null;
        StringTokenizer st = new StringTokenizer(format1, "%");
        // parse parameters
        int num = 0;
        int paramnum = params.size();
        String param;
        while (num < paramnum) {
            if (st.hasMoreTokens()) {
                param = st.nextToken();
                if (returnstr == null)
                    returnstr = param + (String) params.elementAt(num);
                else
                    returnstr = returnstr + param + (String) params.elementAt(num);
            }
            num++;
        }
        while (st.hasMoreTokens()) {
            param = st.nextToken();
            if (returnstr == null)
                returnstr = param;
            else
                returnstr = returnstr + param;
        }
        return returnstr;
    }

    public static String DtringFormat(String format1, String[] params) {

        String returnstr = null;
        StringTokenizer st = new StringTokenizer(format1, "%");
        int num = 0;
        int paramnum = params.length;
        String param;
        while (num < paramnum) {
            if (st.hasMoreTokens()) {
                param = st.nextToken();
                if (returnstr == null)
                    returnstr = param + params[num];
                else
                    returnstr = returnstr + param + params[num];
            }
            num++;
        }
        while (st.hasMoreTokens()) {
            param = st.nextToken();
            if (returnstr == null)
                returnstr = param;
            else
                returnstr = returnstr + param;
        }
        return returnstr;
    }

    public static int transIntFromStr(String srcStr) {

        return srcStr == null ? -1 : Integer.parseInt(srcStr);
    }

    public static Long transLongFromStr(String srcStr) {

        return srcStr == null ? -1 : Long.valueOf(srcStr);
    }

    public static String transDate2Str(Date srcdate) {

        //        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String Str = "";
        if (srcdate != null) {
            Str = sdf.format(srcdate);
        }
        return Str;
    }

    public static String getDateString() {

        Date srcdate = new Date();
        //        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String Str = "";
        if (srcdate != null) {
            Str = sdf.format(srcdate);
        }
        return Str;
    }

    public static Date currentTime() {

        return new Date(currentTimeMillis());
    }

    public static Long currentTimeMillis() {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.clear(Calendar.MILLISECOND);
        return calendar.getTimeInMillis();
    }

    public static boolean checkDateRange(String olddate, int minute) {

        boolean result = false;
        String sdf = "yyyyMMddHHmmss";
        if (olddate != null && olddate.length() == sdf.length()) {
            DateFormat f = new SimpleDateFormat(sdf);
            Date orderDate = null;
            try {
                orderDate = f.parse(olddate);
                Date nowdate = new Date();
                long t1 = nowdate.getTime();
                long t2 = orderDate.getTime();
                long t3 = (long) minute * 60 * 1000;
                result = ((t1 - t2) < t3) ? true : false;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static boolean compareYYYYMMDDDateX(String begindate, String enddate) {

        boolean result = false;
        String sdf = "yyyy-MM-dd";
        if (begindate != null && begindate.length() == sdf.length() && enddate != null && enddate.length() == sdf.length()) {
            DateFormat f = new SimpleDateFormat(sdf);
            Date beginDate = null;
            Date endDate = null;
            try {
                beginDate = f.parse(begindate);
                endDate = f.parse(enddate);
                if (beginDate.equals(endDate) || beginDate.before(endDate)) {
                    result = true;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static boolean compareYYYYMMDDHHMISSDate(String begindate, String enddate) {

        boolean result = false;
        String sdf = "yyyyMMddHHmmss";
        if (begindate != null && begindate.length() == sdf.length() && enddate != null && enddate.length() == sdf.length()) {
            DateFormat f = new SimpleDateFormat(sdf);
            Date beginDate = null;
            Date endDate = null;
            try {
                beginDate = f.parse(begindate);
                endDate = f.parse(enddate);
                if ( beginDate.before(endDate)) {
                    result = true;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    public static String getDateYMDHMS_12bit() {

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");//完整的时间   
        String time = sdf.format(date);
        return time;
    }

    public static String getDateYMDHMS_14bit() {

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");//完整的时间   
        //SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//完整的时间  
        String time = sdf.format(date);
        return time;
    }

    public static String getDateMDHMS_10bit() {

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmmss");//完整的时间   
        String time = sdf.format(date);
        return time;
    }

    public static String getDateYMD_8bit() {

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");//完整的时间   
        String time = sdf.format(date);
        return time;
    }

    public static String getDateYMD_6bit() {

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");//完整的时间   
        String time = sdf.format(date);
        return time;
    }

    public static String getDateHMS_6bit() {

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");//完整的时间   
        String time = sdf.format(date);
        return time;
    }

    public static String tranMoney(Double money) {

        String tvpayFormat = "0.00";
        DecimalFormat myformat = new DecimalFormat();
        myformat.applyPattern(tvpayFormat);
        tvpayFormat = myformat.format(money);
        return tvpayFormat;
    }

    public static String tranMoney(String moneyString) {

        String tvpayFormat = "0.00";
        DecimalFormat myformat = new DecimalFormat();
        myformat.applyPattern(tvpayFormat);
        Double money = Double.valueOf(moneyString);
        if (moneyString.indexOf(".") < 0)
            money = money / 100;
        tvpayFormat = myformat.format(money);
        return tvpayFormat;
    }

    public static String genTxSerNo(String flag) {

        String ret = "";
        ret = Util.getCurrTime().substring(0, 8);
        ret = ret + String.format("%03d", Math.round(Math.random() * 999));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sNow = sdf.format(new Date());
        Date beginDate = new Date();
        try {
            beginDate = sdf.parse(sNow.substring(0, 10) + " 00:00:00");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        ret = ret + String.format("%08d", new Date().getTime() - beginDate.getTime());

        ret = ret + flag;
        return ret;
    }

    /**
     * 增加日期格式字符
     * @param noFmtDate
     * @return
     */
    public static String addFormatDate(String noFmtDate) {

        StringBuilder sb = new StringBuilder(20);
        if (null == noFmtDate || noFmtDate.length() != 8 && noFmtDate.length() != 14) {
            return noFmtDate;
        }
        if (noFmtDate.length() == 8) {
            sb.append(noFmtDate.substring(0, 4)).append("-").append(noFmtDate.substring(4, 6)).append("-").append(noFmtDate.substring(6));
        }
        if (noFmtDate.length() == 14) {
            sb.append(noFmtDate.substring(0, 4)).append("-").append(noFmtDate.substring(4, 6)).append("-").append(noFmtDate.substring(6, 8)).append(" ").append(noFmtDate.substring(8, 10)).append(":").append(noFmtDate.substring(10, 12)).append(":").append(noFmtDate.substring(12));
        }
        return sb.toString();
    }

    /**
     * 去除日期格式字符
     * @param fmtDate
     * @return
     */
    public static String removeFormatDate(String fmtDate) {

        if (null == fmtDate) {
            return fmtDate;
        }
        StringTokenizer st = new StringTokenizer(fmtDate, "\\:\\-\\ \\/", false);
        StringBuilder buf = new StringBuilder(fmtDate.length());
        while (st.hasMoreElements()) {
            buf.append(((String) st.nextElement()).trim());
        }
        return buf.toString();
    }

    public static String getLastDayOfDate(String currDate) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(currDate.substring(0, 4)), Integer.parseInt(currDate.substring(4, 6)) - 1, Integer.parseInt(currDate.substring(6, 8)));
        int ld = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        return currDate.substring(0, 6) + String.format("%02d", ld);

    }

    public static void main(String[] args) {

        System.out.println(ToolsUtil.getDateHMS_6bit());

    }

}
