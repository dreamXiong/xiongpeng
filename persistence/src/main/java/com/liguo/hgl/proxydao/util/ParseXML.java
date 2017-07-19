package com.liguo.hgl.proxydao.util;
import java.io.ByteArrayInputStream;
import java.util.Properties;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
public class ParseXML { 
	//定义一个Proerties用来存放属性值 
	private Properties props; 
	public Properties getProps(){ 
		return this.props; 
	} 

	public void parseString(String xmlstring)throws Exception{ 
		//将我们的解析器对象化 
		XmlNodeProperties handler=new XmlNodeProperties(); 
		//获取SAX工厂对象 
		SAXParserFactory factory=SAXParserFactory.newInstance(); 
		factory.setNamespaceAware(false); 
		factory.setValidating(false); 
		//获取SAX解析 
		SAXParser parser=factory.newSAXParser(); 

		try{ 
			//String newgbstr=xmlstring.replace("utf-8", "GB2312");
			//ByteArrayInputStream stream = new ByteArrayInputStream(xmlstring.getBytes("gb2312"));
			String newgbstr=xmlstring.replace("GB2312", "utf-8");
			ByteArrayInputStream stream = new ByteArrayInputStream(newgbstr.getBytes("utf-8"));
			//将解析器和解析对象xml联系起来，开始解析 
			//parser.parse(xmlstring, handler);
			parser.parse(stream, handler); 
			//获取解析成功后的属性 
			props=handler.getProps(); 
		}finally{ 
			factory=null; 
			parser=null; 
			handler=null; 
		} 
	} 
	public void parseFile(String xmlfilename)throws Exception{ 
		//将我们的解析器对象化 
		XmlNodeProperties handler=new XmlNodeProperties(); 
		//获取SAX工厂对象 
		SAXParserFactory factory=SAXParserFactory.newInstance(); 
		factory.setNamespaceAware(false); 
		factory.setValidating(false); 
		//获取SAX解析 
		SAXParser parser=factory.newSAXParser(); 

		try{ 
			//将解析器和解析对象xml联系起来，开始解析 
			parser.parse(xmlfilename, handler);
			//获取解析成功后的属性 
			props=handler.getProps(); 
		}finally{ 
			factory=null; 
			parser=null; 
			handler=null; 
		} 
	} 
} 
