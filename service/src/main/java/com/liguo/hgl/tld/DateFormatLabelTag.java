package com.liguo.hgl.tld;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.liguo.hgl.proxydao.util.StringUtil;

public class DateFormatLabelTag extends TagSupport
{
  private static final long serialVersionUID = 1L;
  private String value;
  private String pattern;

  public void setValue(String value)
  {
    this.value = value;
  }

  public void setPattern(String pattern)
  {
    this.pattern = pattern;
  }

  public int doStartTag()
    throws JspException
  {
    try
    {
      String out = "";
      if (StringUtil.isNotBlank(this.value)) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(Long.parseLong(this.value));
        SimpleDateFormat dateFormat = new SimpleDateFormat(this.pattern);
        out = dateFormat.format(c.getTime());
      }
      this.pageContext.getOut().write(out);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return 0;
  }
}