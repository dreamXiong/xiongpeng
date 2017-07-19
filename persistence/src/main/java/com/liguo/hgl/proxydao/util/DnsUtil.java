package com.liguo.hgl.proxydao.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;



public class DnsUtil {
	static Logger logger = Logger.getLogger(DnsUtil.class);
	private static Properties propertiesDns=null;
	private static Map<String,String> cacheDns=null;
	///////////////////////////////////////
	///////////////////////////////////////
	
	static {
		if(propertiesDns==null)
		{
			initDnsCache();
		}
	}
	

	public static void main(String[] args) {
		
		String url="http://www.sina.com.cn:8080/index.html";
		System.out.println("url:"+url+",ip:"+doHttpUrlTrans(url));
		url="http://www.sina.com.cn/index.html";
		System.out.println("url:"+url+",ip:"+doHttpUrlTrans(url));
		url="http://11.22.11.22:8080/index.html";
		System.out.println("url:"+url+",ip:"+doHttpUrlTrans(url));
		url="http://localhost:8080/index.html";
		System.out.println("url:"+url+",ip:"+doHttpUrlTrans(url));
		System.out.println("end!");
		
		}
	
	public static void initDnsCache()
	{
		logger.info("-----------DnsUtil InitDnsCache()-----------");
		try {
			if(cacheDns!=null)cacheDns.clear();
			if(propertiesDns!=null)propertiesDns.clear();
			cacheDns =new HashMap<String,String>();;
			logger.info("[DnsUtil]"+"Initialize dns cache.");
			propertiesDns = readPropertiesFile();
			logger.info("[DnsUtil]"+"Load dns.properties.");
			////////////////////////////////////
		} catch (Exception e) {
			logger.info("Exception!!"+e.getMessage());
		}
		logger.info("-----------DnsUtil InitDnsCache Finish!!!-----------");
	}
	
	public static Properties readPropertiesFile() 
	{
		Properties prop = new Properties();
		try
		{
			String fileStr=DnsUtil.class.getClassLoader().getResource("dns.properties").toString();
			File file=new File(URLDecoder.decode((fileStr.substring(5,fileStr.length())),"UTF-8"));
			if(file.exists())
			{
				InputStream in = new FileInputStream(file);
				InputStreamReader read=new InputStreamReader(in,"UTF-8");
				prop.load(read);
				read.close();
				in.close();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return prop;
	}
	
	private static String decodeDomainName(String dnsurl)
	{
		
		if (dnsurl == null)
			return null;
		String domain_ip=cacheDns.get(dnsurl);
		if(domain_ip==null||domain_ip.isEmpty())
		{
			domain_ip=propertiesDns.getProperty(dnsurl);
			if(domain_ip==null||domain_ip.isEmpty())
			{
				try 
				{
					InetAddress address;
					address = InetAddress.getByName(dnsurl);
					//byte[] addr = address.getAddress();
					//domain_ip=Arrays.toString(addr);
					domain_ip=address.getHostAddress();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
			}
			if(domain_ip!=null)
				cacheDns.put(dnsurl, domain_ip);
		}
		logger.info("[DnsUtil]"+"URL:"+dnsurl+",IP:"+domain_ip);
		return domain_ip==null?dnsurl:domain_ip;
	}

	
	public static String doHttpUrlTrans(String url)
	{
		if(url==null||url.isEmpty())return null;
		int start =0;
		int end=0;
		if(url.indexOf("http://")==0)start="http://".length();
		else if(url.indexOf("https://")==0)start="https://".length();
		if(start==0)return url;
		end = url.indexOf("/",start+1);
		String prex=url.substring(0, start);
		String host=url.substring(start, end);
		String Suffix=url.substring(url.indexOf(host)+host.length());
		String ip=host;
		if(host.indexOf(":")>0)
		{
			host=host.substring(0, host.indexOf(":"));
			Suffix=url.substring(url.indexOf(host)+host.length());
		}
		ip=decodeDomainName(host);
		return prex+ip+Suffix;
	}
}
