package com.liguo.hgl.proxydao.page;


/**
 * 分页工具类
 */
public class PageDto {
	  private static final int PAGE_RECORD = 20;
	  private static final int PAGE_MAX_ITEM = 10;
	  
	  /**
	   * 总记录条数
	   */
	  public int recordCount;
	  
	  /**
	   * 总页数
	   */
	  public int pageCount;
	  
	  /**
	   * 单页记录条数
	   */
	  public int pageSize = 15;
	  /**
	   * 当前页
	   */
	  public int pageIndex;
	  /**
	   * 开始记录条数
	   */
	  public int rowStart;
	  /**
	   * 最后一条数据数
	   */
	  public int rowEnd;
	  /**
	   * 上一页 页码
	   */
	  public int prePageNo;
	  /**
	   * 下一页页码
	   */
	  public int nextPageNo;

	  public PageDto()
	  {
	  }

	  public PageDto(int recordCount)
	  {
	    init(recordCount, 20);
	  }

	  public PageDto(int recordCount, int pageSize)
	  {
	    init(recordCount, pageSize);
	  }

	  private void resetPageProperties()
	  {
	    if ((this.pageIndex > this.pageCount) || (this.pageIndex == -1)) {
	      this.pageIndex = this.pageCount;
	    }
	    if (this.pageIndex <= 0) {
	      this.pageIndex = 1;
	    }
	    if (this.pageIndex == 1)
	      this.prePageNo = 1;
	    else {
	      this.prePageNo = (this.pageIndex - 1);
	    }
	    if (this.pageIndex == this.pageCount)
	      this.nextPageNo = this.pageCount;
	    else {
	      this.nextPageNo = (this.pageIndex + 1);
	    }
	    this.rowStart = ((this.pageIndex - 1) * this.pageSize);
	    this.rowEnd = (this.pageIndex * this.pageSize);
	    if (this.rowEnd < this.recordCount)
	      this.rowEnd = this.recordCount;
	  }

	  public void reset(int recordCount)
	  {
	    init(recordCount, this.pageSize);
	    resetPageProperties();
	  }

	  private void init(int recordCount, int pageSize)
	  {
	    this.pageSize = pageSize;
	    if (recordCount == 0) {
	      return;
	    }
	    this.recordCount = recordCount;
	    if (pageSize == 0) {
	      this.pageCount = 1;
	    }
	    else
	      this.pageCount = getPageCountInt(recordCount, pageSize);
	  }
	  
	  public static int getPageCountInt(int recordCount, int pageSize)
	  {
	    return (recordCount + pageSize -1) / pageSize;
	  }

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getRowStart() {
		return rowStart;
	}

	public void setRowStart(int rowStart) {
		this.rowStart = rowStart;
	}

	public int getRowEnd() {
		return rowEnd;
	}

	public void setRowEnd(int rowEnd) {
		this.rowEnd = rowEnd;
	}

	public int getPrePageNo() {
		return prePageNo;
	}

	public void setPrePageNo(int prePageNo) {
		this.prePageNo = prePageNo;
	}

	public int getNextPageNo() {
		return nextPageNo;
	}

	public void setNextPageNo(int nextPageNo) {
		this.nextPageNo = nextPageNo;
	}
	  
	  
	  
	
}
