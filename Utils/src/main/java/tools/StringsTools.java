package tools;

import java.util.ArrayList;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class StringsTools {
    public StringsTools() {
    }

    /**
     * 把多个连续的分隔符split分解成单个分隔符，并把前后的分隔符去掉

     * @param cols  ",,,aa,bbb,cc,ee,,,sdf,,,"
     * @param split  ",,"
     * @param toSplit ","
     * @return
     */
    public static String strSplit(String source,String split){
	if(source == null || split == null){
	    return null;
	}
	try{
	    while (source.indexOf(split + split) > -1) { // 去掉连续的分隔符
		source = source.substring(0, source.indexOf(split + split)) +
		    source.substring(source.indexOf(split + split) + 1);
	    }
	    if (source.substring(0, 1).equals(split)) { // 去掉第一个分隔符
		source = source.substring(1);
	    }
	    if (source.substring(source.length() - 1).equals(split)) { //去掉最后一个分隔符
		source = source.substring(0, source.length() - 1);
	    }
	}catch(Exception e){
	    return "";
	}
	return source;
    }

    /**
     * 把多个连续的分隔符split分解成单个分隔符，并把前后的分隔符去掉

     * @param cols  ",,,aa,bbb,cc,ee,,,sdf,,,"
     * @param split  ",,"
     * @param toSplit ","
     * @return
     */
    public static String strSplit2(String source,String split){
	if(source == null || split == null){
	    return null;
	}
	try{
	    while (source.indexOf(split + split) > -1) { // 去掉连续的分隔符
		source = source.substring(0, source.indexOf(split + split)) +
		    source.substring(source.indexOf(split + split) + 1);
	    }
	}catch(Exception e){
	    return "";
	}
	return source;
    }

    /**
     *  把以split分隔的字符串分组放入list中，source经过处理
     * @param source
     * @param split
     * @return
     */
    public static ArrayList strToList(String source,String split){
	if(source == null || split == null){
	    return null;
	}
	ArrayList list = new ArrayList();
	source = strSplit(source,split);// 整理字符串

	if(source.indexOf(split) == -1){
	    list.add(source.trim()); // 单个
	    return list;
	}
	while(source.indexOf(split) > -1){
	    int L1 = source.indexOf(split);
	    String s1 = source.substring(0,L1);
	    if(!"".equals(s1)){ //字符不包括空格

		list.add(s1);
	    }
	    source = source.substring(L1 + 1);
	    if(source.indexOf(split) == -1){
		list.add(source); // 加入最后一个

	    }
	}
	return list;
    }

    /**
     *  把以split分隔的字符串分组放入list中，source没有经过处理
     *  ,,  之间加入空格放入list 中 把连续的之间加入空格
     * @param source
     * @param split
     * @return
     */
    public static ArrayList strToList2(String source,String split){
	if(source == null || split == null){
	    return null;
	}
	ArrayList list = new ArrayList();
	if(source.indexOf(split) == -1){
	    list.add(source.trim()); // 单个
	    return list;
	}
	while(source.indexOf(split) > -1){
	    int L1 = source.indexOf(split);
	    String s1 = source.substring(0,L1);
	    if(!"".equals(s1)){ //字符不包括空格

		list.add(s1);
	    }else{
		list.add(" ");
	    }
	    source = source.substring(L1 + 1);
	    if(source.indexOf(split) == -1){
		if (!"".equals(source.trim())) { //字符不包括空格

		    list.add(source.trim()); // 加入最后一个

		} else {
		    list.add(" ");
		}

	    }
	}
	return list;
    }

    //单个字符替换
    public static String replaceAll(String value, String one, String two) {
	if(value == null || one == null || two == null){
	    return null;
	}
	value = strSplit(value,one);
	String ret = "";
	try {
	    for (int i = 0; i < value.length(); i++) {
		String tmp = value.substring(i, i + 1);
		if (tmp.equals(one)) {
		    tmp = two;
		}
		ret = ret + tmp;
	    }
	} catch (Exception ex) {
	    ret = "";
	    //ex.printStackTrace();
	}
	return ret;
    }

    //单个字符替换,不替换重复的one
    public static String replaceAll2(String value, String one, String two) {
	if(value == null || one == null || two == null){
	    return null;
	}
	String ret = "";
	try {
	    for (int i = 0; i < value.length(); i++) {
		String tmp = value.substring(i, i + 1);
		if (tmp.equals(one)) {
		    tmp = two;
		}
		ret = ret + tmp;
	    }
	} catch (Exception ex) {
	    ret = "";
	    //ex.printStackTrace();
	}
	return ret;
    }

    public static String getStrSlip(String str, int length){
	if(str == null){
	    return null;
	}
	if(str.length() < length){
	    return str;
	} else{
	    return str.substring(0, length);
	}
    }

    public static boolean isNumber(String str){
	try{
	    if(str == null || "".equals(str.trim())){
		return true;
	    }
	    Integer l = new Integer(str.trim());
	    return true;
	} catch(Exception e){
	    return false;
	}
    }

    public static String xmlEncoding(String str){
	if(str == null){
	    return null;
	}
	try{
	    return new String(str.getBytes(),"GBK");
	} catch(Exception ex){
	    return str;
	}
    }

//    public static




    public static void main(String[] str){
System.out.println(">>" + "sss".equalsIgnoreCase("SSS"));
//	System.out.println(">>>>   " + StringsTools.replaceAll2(",a,b,,,c,",",","0"));
//	System.out.println(">>>   " + "a,,b,,,c".replaceAll(",,",",' ',"));
//	System.out.println(" " + StringsTools.replaceAll("aa，爸爸，常常，dd,cc,","，",","));
    }




}