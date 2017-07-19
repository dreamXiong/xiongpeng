package com.liguo.hgl.proxydao.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;

import com.liguo.hgl.proxydao.model.ProductImport;

/***
 * *
 * 
 * <excel 工具类><br>
 * 
 * @FileName ExcelOperate.java。<br>
 * @Language java。
 * @date 2016年5月25日
 * @author wzt
 */
public class ExcelOperate {
    
    //产品名称的列
    private static final int PRODUCT_NAME_ROW = 0;
    

    /**
     * 
     * 读取Excel的内容，第一维数组存储的是一行中格列的值，二维数组存储的是多少个行
     * 
     * @param file
     *            读取数据的源Excel
     * 
     * @param ignoreRows
     *            读取数据忽略的行数，比喻行头不需要读入 忽略的行数为1
     * 
     * @return 读出的Excel中数据的内容
     * 
     * @throws FileNotFoundException
     * 
     * @throws IOException
     */

    public static List getData2003(File file, int ignoreRows, Boolean AllSheets)

    throws FileNotFoundException, IOException {

        List<List> result = new ArrayList<List>();

        int rowSize = 0;

        BufferedInputStream in = new BufferedInputStream(new FileInputStream(

        file));

        // 打开HSSFWorkbook

        POIFSFileSystem fs = new POIFSFileSystem(in);

        HSSFWorkbook wb = new HSSFWorkbook(fs);

        HSSFCell cell = null;
        int maxSheetIndex = 1;
        if (AllSheets) {
            maxSheetIndex = wb.getNumberOfSheets();
        }
        for (int sheetIndex = 0; sheetIndex < maxSheetIndex; sheetIndex++) {

            HSSFSheet st = wb.getSheetAt(sheetIndex);

            // 第一行为标题，不取
            for (int rowIndex = ignoreRows; rowIndex <= st.getLastRowNum(); rowIndex++) {

                HSSFRow row = st.getRow(rowIndex);

                if (row == null) {

                    continue;

                }

                int tempRowSize = row.getLastCellNum() + 1;

                if (tempRowSize > rowSize) {

                    rowSize = tempRowSize;

                }

                // String[] values = new String[rowSize];
                List values = new ArrayList();

                // Arrays.fill(values, "");

                boolean hasValue = false;
                for (short columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {

                    String value = null;

                    cell = row.getCell(columnIndex);

                    if (cell != null) {

                        // 注意：一定要设成这个，否则可能会出现乱码

                        // cell.setEncoding(HSSFCell.ENCODING_UTF_16);

                        switch (cell.getCellType()) {

                            case HSSFCell.CELL_TYPE_STRING:

                                value = cell.getStringCellValue();

                                break;

                            case HSSFCell.CELL_TYPE_NUMERIC:

                                if (HSSFDateUtil.isCellDateFormatted(cell)) {

                                    Date date = cell.getDateCellValue();

                                    if (date != null) {

                                        value = new SimpleDateFormat("yyyy-MM-dd")

                                        .format(date);

                                    }
                                    else {

                                        value = "";

                                    }

                                }
                                else {

                                    value = new DecimalFormat("0").format(cell

                                    .getNumericCellValue());

                                }

                                break;

                            case HSSFCell.CELL_TYPE_FORMULA:

                                // 导入时如果为公式生成的数据则无值

                                // if (!cell.getStringCellValue().equals("")) {
                                //
                                // value = cell.getStringCellValue();
                                //
                                // } else {
                                //
                                // value = cell.getNumericCellValue() + "";
                                //
                                // }
                                try {

                                    value = String.valueOf(cell.getRichStringCellValue());

                                }
                                catch (IllegalStateException e) {

                                    value = "";

                                }

                                break;

                            case HSSFCell.CELL_TYPE_BLANK:

                                break;

                            case HSSFCell.CELL_TYPE_ERROR:

                                value = "";

                                break;

                            case HSSFCell.CELL_TYPE_BOOLEAN:

                                value = (cell.getBooleanCellValue() == true ? "Y"

                                : "N");

                                break;

                            default:

                                value = "";

                        }

                    }

                    if (columnIndex == 0 && value.trim().equals("")) {

                        break;

                    }

                    // values[columnIndex] = rightTrim(value);
                    // values.put(columnIndex+"", rightTrim(value));
                    values.add(columnIndex, rightTrim(value));

                    hasValue = true;

                }

                if (hasValue) {

                    result.add(values);

                }

            }

        }

        in.close();

        List returnArray = new ArrayList();

        for (int i = 0; i < result.size(); i++) {

            returnArray.add(result.get(i));

        }

        return returnArray;

    }

    public static List getData2007(File file, int ignoreRows, Boolean AllSheets)

    throws FileNotFoundException, IOException {

        List<List> result = new ArrayList<List>();

        int rowSize = 0;

        XSSFWorkbook wb = new XSSFWorkbook(file.getPath());

        XSSFCell cell = null;
        int maxSheetIndex = 1;
        if (AllSheets) {
            maxSheetIndex = wb.getNumberOfSheets();
        }
        for (int sheetIndex = 0; sheetIndex < maxSheetIndex; sheetIndex++) {

            XSSFSheet st = wb.getSheetAt(sheetIndex);

            // 第一行为标题，不取
            // XSSFRow row1 = st.getRow(0);
            // for (short columnIndex = 0; columnIndex <= row1.getLastCellNum();
            // columnIndex++) {
            // System.out.print("key:"+st.getRow(0).getCell(columnIndex).getStringCellValue());
            // }
            for (int rowIndex = ignoreRows; rowIndex <= st.getLastRowNum(); rowIndex++) {

                XSSFRow row = st.getRow(rowIndex);

                if (row == null) {

                    continue;

                }

                int tempRowSize = row.getLastCellNum() + 1;

                if (tempRowSize > rowSize) {

                    rowSize = tempRowSize;

                }

                List values = new ArrayList();

                // Arrays.fill(values, "");

                boolean hasValue = false;
                for (short columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {

                    String value = null;

                    cell = row.getCell(columnIndex);

                    if (cell != null) {

                        // 注意：一定要设成这个，否则可能会出现乱码

                        // cell.setEncoding(XSSFCell.ENCODING_UTF_16);

                        switch (cell.getCellType()) {

                            case XSSFCell.CELL_TYPE_STRING:

                                value = cell.getStringCellValue();

                                break;

                            case XSSFCell.CELL_TYPE_NUMERIC:

                                // if (XSSFDateUtil.isCellDateFormatted(cell)) {
                                //
                                // Date date = cell.getDateCellValue();
                                //
                                // if (date != null) {
                                //
                                // value = new SimpleDateFormat("yyyy-MM-dd")
                                //
                                // .format(date);
                                //
                                // } else {
                                //
                                // value = "";
                                //
                                // }
                                //
                                // } else {

                                value = new DecimalFormat("0").format(cell

                                .getNumericCellValue());

                                // }

                                break;

                            case XSSFCell.CELL_TYPE_FORMULA:

                                // 导入时如果为公式生成的数据则无值
                                //
                                // if (!cell.getStringCellValue().equals("")) {
                                //
                                // value = cell.getStringCellValue();
                                //
                                // } else {
                                //
                                // value = cell.getNumericCellValue() + "";
                                //
                                // }
                                try {

                                    value = String.valueOf(cell.getRichStringCellValue());

                                }
                                catch (IllegalStateException e) {

                                    value = "";

                                }

                                break;

                            case XSSFCell.CELL_TYPE_BLANK:
                                value = "";

                                break;

                            case XSSFCell.CELL_TYPE_ERROR:

                                value = "";

                                break;

                            case XSSFCell.CELL_TYPE_BOOLEAN:

                                value = (cell.getBooleanCellValue() == true ? "Y"

                                : "N");

                                break;

                            default:

                                value = "";

                        }

                    }

                    if (columnIndex == 0 && value.trim().equals("")) {

                        break;

                    }

                    // values[columnIndex] = rightTrim(value);
                    // values.put(columnIndex+"", rightTrim(value));
                    values.add(columnIndex, rightTrim(value));
                    hasValue = true;

                }

                if (hasValue) {

                    result.add(values);

                }

            }

        }

        // in.close();

        List returnArray = new ArrayList();

        for (int i = 0; i < result.size(); i++) {

            returnArray.add(result.get(i));

        }

        return returnArray;

    }

    /**
     * 
     * 去掉字符串右边的空格
     * 
     * @param str
     *            要处理的字符串
     * 
     * @return 处理后的字符串
     */

    public static String rightTrim(String str) {

        if (str == null) {

            return "";

        }

        int length = str.length();

        for (int i = length - 1; i >= 0; i--) {

            if (str.charAt(i) != 0x20) {

                break;

            }

            length--;

        }

        return str.substring(0, length);

    }

    // public class XSSFDateUtil extends DateUtil {
    // protected int absoluteDay(Calendar cal, boolean use1904windowing) {
    // return DateUtil.absoluteDay(cal, use1904windowing);
    // }
    // }
    /**
     * 
     * excel数字转日期
     * 
     * @param intdate
     *            要处理的数字
     * @param sformat
     *            要转换的日期格式：如：yyyy-MM-dd
     * 
     * @return 处理后的字符串
     */
    public static String intToDate(int intdate, String sformat) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1900, 0, -1);// 试出来的，尴尬。
        calendar.add(Calendar.DAY_OF_YEAR, intdate);

        Calendar calendat = Calendar.getInstance();

        SimpleDateFormat sdf = new SimpleDateFormat(sformat);
        String dateStr = sdf.format(calendar.getTime());
        return dateStr;

    }

    /***
     * 03版本,根据指定列数获取Excel数据,如果未指定列数，就获取Excel的所有列数 解析指定的列数，如未指定，则获取所有列数 去除空行
     * 
     * @param path
     *            导入文件路径
     * @param row
     *            文件模板规定的列数
     * @return
     * @throws IOException
     */
    public List<List<Object>> readXls(String path, int row, int[] arr) throws IOException {
        InputStream is = new FileInputStream(path);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        List<List<Object>> list = new ArrayList<List<Object>>();
        for (int numSheet = 0; numSheet < 1; numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            int rowNum = 1;
            int lastRowNum = hssfSheet.getLastRowNum();
            for (; rowNum <= lastRowNum; rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                List<Object> cellValueList = new ArrayList<Object>();
                Map<Integer, Object> rowMap = new HashMap<Integer, Object>();
                StringBuffer sb = new StringBuffer();
                if (row < 1 && arr.length == 0) {
                    // 解析所有列
                    for (int i = 0; i < hssfRow.getLastCellNum(); i++) {
                        rowMap.put(i, hssfRow.getCell(i));
                        sb.append(hssfRow.getCell(i));
                    }
                }
                // 只解析指定的部分列
                else if (arr.length != 0) {
                    for (int i = 0; i < arr.length; i++) {
                        rowMap.put(i, hssfRow.getCell(arr[i]));
                        sb.append(hssfRow.getCell(arr[i]));
                    }
                }
                // 解析指定的总列
                else {
                    for (int i = 0; i < row; i++) {
                        rowMap.put(i, hssfRow.getCell(i));
                        sb.append(hssfRow.getCell(i));
                    }
                }
                if (!StringUtil.isBlank(sb.toString())) {
                    for (int i = 0; i < rowMap.size(); i++) {
                        cellValueList.add(rowMap.get(i));
                    }
                }

                if (!cellValueList.isEmpty()) {
                    list.add(cellValueList);
                    // System.out.println("目前总行数:" + list.size());
                }
                // float a =rowNum;
                // float b =lastRowNum;
                // float pustMsg = (a / b);
            }
        }
        return list;
    }

    @Test
    public void Test() throws IOException {
        int[] a = new int[] {};
        readXlsx("D://11.xlsx", 0, a);
    }

    /***
     * 07-12版本,根据指定列数获取Excel数据,如果未指定列数，就获取Excel的所有列数 解析指定的列数，如未指定，则获取所有列数
     * 
     * @param arr
     *            指定解析列，以数组的形式
     * @param path
     *            导入文件路径
     * @param row
     *            文件模板规定的列数
     * @return
     * @throws IOException
     */
    public List<List<Object>> readXlsx(String path, int row, int[] arr) throws IOException {
        InputStream is = new FileInputStream(path);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        List<List<Object>> list = new ArrayList<List<Object>>();
        for (int numSheet = 0; numSheet < 1; numSheet++) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
            if (xssfSheet == null) {
                continue;
            }
            for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                Map<Integer, Object> rowMap = new HashMap<Integer, Object>();
                StringBuffer sb = new StringBuffer();
                List<Object> cellValueList = new ArrayList<Object>();
                if (row < 1) {
                    // 解析所有列
                    for (int i = 0; i < xssfRow.getLastCellNum(); i++) {
                        rowMap.put(i, xssfRow.getCell(i));
                        sb.append(xssfRow.getCell(i));
                    }
                }
                // 只解析指定的部分列
                else if (arr.length != 0) {
                    // 解析指定的总列
                    for (int i = 0; i < arr.length; i++) {
                        rowMap.put(i, xssfRow.getCell(arr[i]));
                        sb.append(xssfRow.getCell(arr[i]));
                    }
                }
                // 解析指定的总列
                else {
                    for (int i = 0; i < row; i++) {
                        rowMap.put(i, xssfRow.getCell(i));
                        sb.append(xssfRow.getCell(i));
                    }
                }
                if (!StringUtil.isBlank(sb.toString())) {
                    for (int i = 0; i < rowMap.size(); i++) {
                        cellValueList.add(rowMap.get(i));
                    }
                }

                if (!cellValueList.isEmpty()) {
                    list.add(cellValueList);
                    // System.out.println("目前总行数:" + list.size());
                }
            }
        }
        return list;
    }

    /***
     * 
     * <解析Excel>
     * @param productData           数据源
     * @param arr                   库存数据对应的列     
     * @param arr1                  产品数据对应的列
     * @param keyArr                模板对应的key
     * @param batchNo               批次号
     * @param shopId                店铺id
     * @param messageArr            对应列的错误提示消息
     * @return                      返回一个组装数据的大集合0 是产品，1是库存 ,2是全部
     * @throws IOException
     * @author wzt
     * @since   2016年7月21日
     */
    public List<Object> readXlsxGetTwoArray(MultipartFile productData, int[] arr, int[] arr1,String[] keyArr,String batchNo,Integer shopId,String[] messageArr) throws IOException {
        //产品名称
        String productName =""; 
        InputStream is =productData.getInputStream();
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        //保存所有库存信息的List
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        //保存所有产品信息的List
        List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
        
        //保存去重后的产品Map，按照产品名称为Key
        Map<String,List<Map<String, Object>>> rowMap = new HashMap<String,List<Map<String, Object>>>();
        
        //按照产品名称，保存所有库存的Map
        Map<String,List<Map<String, Object>>> tbwapproductInMap = new HashMap<String,List<Map<String, Object>>>();
        //所有库存List
        List<Map<String, Object>> tbwapproductInList = new ArrayList<Map<String,Object>>();
        
        //效验数据有效性的List
        List<ProductImport> productAllList = new ArrayList<ProductImport>();
        
        for (int numSheet = 0; numSheet < 1; numSheet++) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
            if (xssfSheet == null) {
                continue;
            } 
            //计数，如果空项连续出现5个直接跳出解析
            int count = 0;
            int startCount = 0;//解析行数，用来判断连续5次
            
            //附加属性的值
            String attributes = "";
            for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                //效验数据有效性的Map
                Map<String, Object> productAllMap = new HashMap<String, Object>();
                //保存一行产品信息的Map
                Map<String, Object> productMap = new HashMap<String, Object>();
                //保存一行库存信息的Map
                Map<String, Object> inventoryMap = new HashMap<String, Object>();
                //保存一行产品信息的List
                List<Map<String, Object>> productList = new ArrayList<Map<String, Object>>();
                String errorMsg="";
                // 解析所有列
                for (int i = 0; i < xssfRow.getLastCellNum(); i++) {
                    String val =getStrinCellValue(xssfRow.getCell(i));
                    //使用特殊的读法，获取函数内的值
                    if(i==2||i==5){
                        XSSFCell cell =xssfRow.getCell(i);
                        FormulaEvaluator evaluator = cell.getSheet().getWorkbook().getCreationHelper().createFormulaEvaluator();
                        evaluator.evaluateFormulaCell(cell);
                        CellValue cellValue = evaluator.evaluate(cell);
                        val=String.valueOf((int)cellValue.getNumberValue());
                    }
                    
                    if(i==6){
                        attributes= val;
                    }
                    //属性值
                    if(i==18){
                        //附加属性为空时,不能输入属性值
                        if(StringUtil.isBlank(attributes)&&StringUtil.isNotBlank(val)){
                            errorMsg+="附加属性为空时,不能输入属性值"+"<br/>";
                            continue;
                        }
                        
                        
                        // 属性不为空,属性值必须大于等于属性的个数
                        if(StringUtil.isNotBlank(attributes)){
                            if(StringUtil.isBlank(val)){
                                continue;
                            }
                            
                            String[] tempArr =attributes.split("/");
                            String[] valArr =val.split(";");
                            
                            if(tempArr.length>valArr.length){
                                errorMsg+="附加属性的个数不能大于与属性值的个数"+"<br/>";
                                continue;
                            }
                        }
                        
                        
                        
                    }
                    
                    if(StringUtil.isBlank(val)){
                        String msg =messageArr[i];
                        if(StringUtil.isNotBlank(msg)){
                            errorMsg+=msg+"<br/>";
                        }
                        continue;
                    }
                    
                    
                    //当前列为产品名称
                    if(PRODUCT_NAME_ROW==i){
                        productName=val;
                    }
                    
                    
                    //产品
                    if((Arrays.binarySearch(arr1, i))>=0){
                       //组装一个Map<String,Object>
                        productMap.put(keyArr[i], val);
                        productAllMap.put(keyArr[i], val);
                    }
                    
                    if((Arrays.binarySearch(arr, i))>=0){
                        inventoryMap.put(keyArr[i], val);
                        productAllMap.put(keyArr[i], val);
                    }
                }
                
                
                // 保存列信息
                if(!inventoryMap.values().isEmpty()&&!productMap.values().isEmpty()){
                    
                    productMap.put("shopId", shopId);
                    productMap.put("batchNo", batchNo);
                    productList.add(productMap);    
                    
                    //添加库存默认值
                    String totalInventory =String.valueOf(inventoryMap.get("totalInventory"));
                    //添加库存量，默认与入库量相同
                    inventoryMap.put("saleInventory", totalInventory);
                    //添加占用量,默认0
                    inventoryMap.put("unsaleInventory", 0);
                    //添加装箱数，默认为0箱
                    inventoryMap.put("oneboxCount", 0);
                    //添加上下架状体，默认为0，下架
                    inventoryMap.put("status", 0);
                    
                    //同一个产品名称，保存一条产品记录
                    rowMap.put(productName, productList);
                    
                    //产品名称是否在map中，如果有则在已有的基础上添加一个库存列，如果没有则添加一个新的
                    if(tbwapproductInMap.containsKey(productName)){
                        tbwapproductInList = new ArrayList<Map<String,Object>>();
                        tbwapproductInList =tbwapproductInMap.get(productName);
                        tbwapproductInList.add(inventoryMap);
                    }else{
                        tbwapproductInList = new ArrayList<Map<String,Object>>();
                        tbwapproductInList.add(inventoryMap);
                        tbwapproductInMap.put(productName, tbwapproductInList);
                    }
                    //保存一行数据
                    list.add(inventoryMap);
                    list1.add(productMap);
                    ProductImport pi = new ProductImport();
                    pi=BeanUtil.CopyMapToBeanByJson(productAllMap, ProductImport.class);
                    pi.setMessage(errorMsg);
                    
                    productAllList.add(pi);//所有
                }
                //对空行的处理
                else{
                    if(startCount==rowNum||(startCount+1)==rowNum){
                        count++;
                    }
                    startCount=rowNum;
                    //5次跳出循环
                    if(count==5){
                        break;
                    }
                }
            }
        }
        //返回
        List<Object> objList =new ArrayList<Object>();
        objList.add(rowMap);
        objList.add(tbwapproductInMap);
        objList.add(productAllList);
        return objList;
    }
    
    
    /***
     * 03版本,根据指定列数获取Excel数据,如果未指定列数，就获取Excel的所有列数 解析指定的列数，如未指定，则获取所有列数 去除空行
     * 
     * @param path
     *            导入文件路径
     * @param row
     *            文件模板规定的列数
     * @return
     * @throws IOException
     */
    
    
    
    public static void main(String[] args) {
//        Map<String, String> map = new HashMap<String, String>();     
//        map.put("YYY", "UUU");     
//        map.put("RRR", "TTT"); 
//        List<String> mapValuesList = new ArrayList<String>(map.values()); 
        String s ="107.8";
        float  f =Float.parseFloat(s);
        System.out.println((int)f);
    }
    
    
    public void isValueTypeAdd(Map<String, Object> map,XSSFCell hssfCell,String key){
        if(hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN){  
            map.put(key, hssfCell.getBooleanCellValue());
          }else if(hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC){  
              map.put(key, hssfCell.getNumericCellValue());
          }else{  
            map.put(key, hssfCell.getStringCellValue());
          }  
        
    }
    // HSSFCell
    public static String getStrinCellValue(HSSFCell cell){
        if(cell==null||"null".equals(cell)){
            return "";
        }
        String strCell ="";     
        switch (cell.getCellType()){
        case HSSFCell.CELL_TYPE_STRING:
            strCell=cell.getStringCellValue();
            break;
        case HSSFCell.CELL_TYPE_NUMERIC:
            DecimalFormat df = new DecimalFormat("0"); 
            strCell=df.format(cell.getNumericCellValue());
            break;
        case HSSFCell.CELL_TYPE_BOOLEAN:
            strCell=String.valueOf(cell.getStringCellValue());
        case HSSFCell.CELL_TYPE_BLANK:
            strCell="";
            break;
            default:
                strCell="";
        }
        return strCell;
    }
    
    //XSSFCell
    public static String getStrinCellValue(XSSFCell cell){
        if(cell==null||"null".equals(cell)){
            return "";
        }
        String strCell ="";     
        switch (cell.getCellType()){
            case XSSFCell.CELL_TYPE_STRING:
                strCell=cell.getStringCellValue();
                break;
            case XSSFCell.CELL_TYPE_NUMERIC:
                DecimalFormat df = new DecimalFormat("0"); 
                strCell=df.format(cell.getNumericCellValue());
                break;
            case XSSFCell.CELL_TYPE_BOOLEAN:
                strCell=String.valueOf(cell.getStringCellValue());
            case XSSFCell.CELL_TYPE_BLANK:
                strCell="";
                break;
            default:
                strCell="";
        }
        return strCell;
    }

}
