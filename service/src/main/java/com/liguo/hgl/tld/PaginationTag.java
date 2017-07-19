package com.liguo.hgl.tld;

import java.io.IOException;
import java.io.PrintStream;
import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;


import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.proxydao.util.StringUtil;

public class PaginationTag extends TagSupport
{
  private static final long serialVersionUID = 8952532540293482809L;
  private static final String DEFAULT_PAGE_ACTION = "searchResult";
  private static final String DEFAULT_PAGE_DTO = HglContants.PAGE_DTO_ID;
  private static final int SELECT_PAGE_SIZE = 5;
  private String styleId;
  private String styleClass;
  private String pageDtoName;
  private String pageAction;
  private String formId = "";
  private String dataDomId="";
  private boolean isShowSelect = true;
  private boolean showHref = true;
  private String location;

  public int doEndTag()
    throws JspException
  {
    try
    {
      this.pageContext.getOut().write(generateHtml());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return 6;
  }

  private String generateHtml()
  {
	  PageDto page = null;

    if (StringUtil.isBlank(this.pageDtoName)) {
      this.pageDtoName = DEFAULT_PAGE_DTO;
    }
    if (StringUtil.isBlank(this.pageAction)) {
      this.pageAction = DEFAULT_PAGE_ACTION;
    }
    Object obj = this.pageContext.getRequest().getAttribute(this.pageDtoName);
    if (obj == null)
    {
      return "";
    }

    page = (PageDto)this.pageContext.getRequest().getAttribute(this.pageDtoName);

    int prePageNo = page.prePageNo;
    int nextPageNo = page.nextPageNo;
    int pageCount = page.pageCount;
    int pageIndex = page.pageIndex;
    int pageSize = page.pageSize;
    int recordCount = page.recordCount;
    //如果数据条数小于等于单页数据条数 则不显示分页控件
    if(recordCount<=pageSize){
    	return "";
    }
    StringBuilder html = new StringBuilder(new StringBuilder().append("<div class='text-center'><div class='pagination clear'> <form onsubmit='return false;' class='paginationForm' pageDtoName='").append(this.pageDtoName).append("' formId='").append(this.formId.trim()).append("'  act='").append(this.pageAction.trim()).append("' dataDomId='" ).append(this.dataDomId.trim()).append("'  " ).toString());

    if (StringUtil.isNotBlank(this.styleClass)) {
      html.append(this.styleClass);
    }

    if (StringUtil.isNotBlank(this.styleId)) {
      html.append(" id='").append(this.styleId).append("' ");
    }
    html.append(">");

    if ((this.location == "") || (this.location == null) || (this.location == "foot"))
    {
      if (pageIndex > 1)
      {
        html.append("<a ");
        if (this.showHref) {
          html.append(" href='javascript:void(0)' ");
        }
        html.append(new StringBuilder().append(" class='pg page' siz='").append(pageSize).append("' idx=1>首页</a> ").toString());
        html.append(" <a  ");
        if (this.showHref) {
          html.append(" href='javascript:void(0)' ");
        }
        html.append(new StringBuilder().append("  siz='").append(pageSize).append("' class='pg page' idx=").toString());
        html.append(prePageNo);
        html.append(">上一页</a>");
      }

      int pageStart = 1;
      if ((pageCount > 5) && (pageIndex - 2 > 0)) {
        pageStart = pageIndex - 2;
      }

      int count = (5 + pageStart - 1) > pageCount ? pageCount : 5 + pageStart - 1;
      if(count==pageCount && (pageCount-5 > 0)){
    	  pageStart=pageCount-5;
      }
      for (int i = pageStart; i <= count; i++) {
        if (pageIndex == i) {
          html.append("<span class='page active'>");
          html.append(i);
          html.append("</span>");
        } else {
          html.append("<a ");
          if (this.showHref) {
            html.append(" href='javascript:void(0)' ");
          }
          html.append("  class='pg page' idx='");
          html.append(i);
          html.append("'");
          html.append(" siz='");
          html.append(pageSize).append("' >");
          html.append(i);
          html.append("</a>");
        }
      }
      if (pageIndex < pageCount) {
        html.append("<a ");
        if (this.showHref) {
          html.append(" href='javascript:void(0)' ");
        }
        html.append(new StringBuilder().append(" class='pg page' siz='").append(pageSize).append("' idx=").toString()).append(nextPageNo);
        html.append(">下一页</a><a  ");
        if (this.showHref) {
          html.append(" href='javascript:void(0)' ");
        }
        html.append(new StringBuilder().append(" siz='").append(pageSize).append("' idx='").append(pageCount).append("' class='pg page'>尾页</a>").toString());
      }
      html.append(new StringBuilder().append("  到<input type='text'  siz='").append(pageSize).append("' class='pageInput number text-center' value='").append(pageIndex).append("' name='").toString());
      html.append(this.pageDtoName).append(new StringBuilder().append(".pageIndex' />/").append(pageCount).append("页<button type='button' class='pgBtn' siz='").append(pageSize).append("' id='goPage' value='' name=''>确定 </button>").toString());
  
    }
    else if (this.location == "top") {
      html.append(new StringBuilder());
      html.append("<a ");
      if (this.showHref) {
        html.append(" href='javascript:void(0)' ");
      }
      html.append("  class='pg page' idx='");
      html.append(prePageNo);
      html.append("'");
      html.append(" siz='");
      html.append(pageSize).append("' >");
      html.append("<");
      html.append("</a>");
      
      html.append("&nbsp;&nbsp;&nbsp;<span>").append(pageIndex).append("/").append(pageCount).append("</span>&nbsp;&nbsp;&nbsp;");
      
      html.append("<a ");
      if (this.showHref) {
        html.append(" href='javascript:void(0)' ");
      }
      html.append("  class='pg page' idx='");
      html.append(nextPageNo);
      html.append("'");
      html.append(" siz='");
      html.append(pageSize).append("' >");
      html.append(">");
      html.append("</a>");
    }
    html.append("</form></div></div>");
    System.out.println(html.toString());
    return html.toString();
  }

  public String getStyleId()
  {
    return this.styleId;
  }

  public void setStyleId(String styleId)
  {
    this.styleId = styleId;
  }

  public String getStyleClass()
  {
    return this.styleClass;
  }

  public void setStyleClass(String styleClass)
  {
    this.styleClass = styleClass;
  }

  public String getPageDtoName()
  {
    return this.pageDtoName;
  }

  public void setPageDtoName(String pageDtoName)
  {
    this.pageDtoName = pageDtoName;
  }

  public String getPageAction()
  {
    return this.pageAction;
  }

  public void setPageAction(String pageAction)
  {
    this.pageAction = pageAction;
  }

  public String getFormId()
  {
    return this.formId;
  }

  public void setFormId(String formId)
  {
    this.formId = formId;
  }

  public boolean getIsShowSelect()
  {
    return this.isShowSelect;
  }

  public void setIsShowSelect(boolean isShowSelect)
  {
    this.isShowSelect = isShowSelect;
  }

  public boolean getShowHref()
  {
    return this.showHref;
  }

  public void setShowHref(boolean showHref)
  {
    this.showHref = showHref;
  }

  public String getLocation()
  {
    return this.location;
  }

  public void setLocation(String location)
  {
    this.location = location;
  }

	public String getDataDomId() {
		return dataDomId;
	}
	
	public void setDataDomId(String dataDomId) {
		this.dataDomId = dataDomId;
	}
  
}