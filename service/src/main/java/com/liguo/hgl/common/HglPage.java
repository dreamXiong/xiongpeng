package com.liguo.hgl.common;
public class HglPage
{
  public String MAIN_PAGE = "main";
  public String UPDATE_PAGE = "update";
  public String RESULT_PAGE = "list";
  public String DETAIL_PAGE = "detail";

  public HglPage()
  {
  }

  public HglPage(String preFix)
  {
    getClass(); this.MAIN_PAGE = (preFix+"/"+preFix );
    getClass(); this.UPDATE_PAGE = (preFix+"/"+preFix + "Update");
    getClass(); this.RESULT_PAGE = (preFix+"/"+preFix + "List");
    getClass(); this.DETAIL_PAGE = (preFix+"/"+preFix + "Detail");
  }

  public HglPage(String mainPage, String updatePage, String resultPage)
  {
    this.MAIN_PAGE = mainPage;
    this.UPDATE_PAGE = updatePage;
    this.RESULT_PAGE = resultPage;
  }

  public HglPage(String mainPage, String updatePage, String resultPage, String detailPage)
  {
    this.MAIN_PAGE = mainPage;
    this.UPDATE_PAGE = updatePage;
    this.RESULT_PAGE = resultPage;
    this.DETAIL_PAGE = detailPage;
  }
}
