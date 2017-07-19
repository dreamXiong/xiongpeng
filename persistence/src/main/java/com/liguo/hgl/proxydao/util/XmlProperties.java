package com.liguo.hgl.proxydao.util;
import org.xml.sax.helpers.DefaultHandler; 
import java.util.Properties; 
import org.xml.sax.Attributes; 
import org.xml.sax.SAXException; 


public class XmlProperties extends DefaultHandler { 
	//定义一个properties用来存放属性 
	private Properties props; 
	private String currentName; 
	private String RootName;
	private StringBuffer currentValue=new StringBuffer(); 
	public XmlProperties(){ 
		this.props=new Properties();
		RootName="";
	} 
	public Properties getProps(){ 
		return this.props; 
	} 

	//这里是将xml中元素值加入currentValue 
	public void characters(char[] ch, int start, int length) 
	throws SAXException { 

		currentValue.append(ch, start, length); 
	} 
	//在遇到</xx>时，将之间的字符存放在props中间 
	public void endElement(String uri, String localName, String name) 
	throws SAXException { 

		props.put(currentName.toLowerCase(), currentValue.toString().trim());
		if(name.equals(RootName))props.put("rootname",RootName);

	} 
	//定义开始解析元素的方法，这里将<xx>中的名称xx提出来， 
	public void startElement(String uri, String localName, String qName, 
			Attributes attributes) throws SAXException { 

		currentValue.delete(0, currentValue.length()); 
		currentName=qName; 
		if(RootName.length()==0)RootName=qName;

	} 

// 
} 
