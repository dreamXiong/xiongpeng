package com.liguo.hgl.tld;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbDict;
import com.liguo.hgl.service.TbDictService;


public class DictLabelTag extends TagSupport
{
	
	/*@Autowired
	private TbDictService tbDictService;*/
		
	private static final long serialVersionUID = 1L;
	  
	private String dictId;
	  
	private String key;

	  public String getDictId() {
		return dictId;
	}
	
	public void setDictId(String dictId) {
		this.dictId = dictId;
	}
	
	public String getKey() {
		return key;
	}
	
	public void setKey(String key) {
		this.key = key;
	}

	public int doStartTag() throws JspException{
		
		
		/*ApplicationContext  ac = new ClassPathXmlApplicationContext("spring/applicationContext-base.xml");
		TbDictServiceImpl tbDictService = (TbDictServiceImpl)ac.getBean("tbDictService");*/
		
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(pageContext.getServletContext());
		
		TbDictService tbDictService = (TbDictService)ctx.getBean("tbDictServiceImpl");

	    try
	    {
	      String out = "";
	      if (this.dictId != null) {
	    	  out = tbDictService.selectByPrimaryKey(Integer.parseInt(dictId)).getValue();
	      }else{
	    	  Criteria example = new Criteria();
	    	  example.put("dictkey", key);
	    	  List<TbDict> dList = tbDictService.selectByObject(example);
	    	  if(dList.size() > 0){
	    		  out = dList.get(0).getValue();
	    	  }
	      }
	      this.pageContext.getOut().write(out);
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	    return 0;
	  }
}