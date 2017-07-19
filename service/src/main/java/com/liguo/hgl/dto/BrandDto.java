package com.liguo.hgl.dto;

import org.springframework.web.multipart.MultipartFile;

import com.liguo.hgl.proxydao.model.TbBrand;

public class BrandDto extends TbBrand {
	
	private MultipartFile imgFile;
	private String imgNameInfo;
    public MultipartFile getImgFile() {
        return imgFile;
    }
 
    public void setImgFile(MultipartFile imgFile) {
        this.imgFile = imgFile;
    }
 
    @Override
    public String toString() {
        return "UploadDemoVo [imgFile=" + imgFile + "]";
    }
     
    public boolean validateFile(){
    	String reg = ".+(.JPEG|.jpeg|.JPG|.jpg|.GIF|.gif|.BMP|.bmp|.PNG|.png)$";
      /*  if(!ConstantUtil.fileTypeImg.contains(this.getImgFile().getContentType())){
            throw new ServiceException("文件类型只能是jpeg、png！");
        }
        if(this.getImgFile().getSize() > 1000 * 100){
            throw new ServiceException("文件最大不能超过100KB！");
        }*/
    /*	if(this.getImgFile().getContentType() ){}*/
        return true;
    }
	public String getImgNameInfo() {
		return imgNameInfo;
	}

	public void setImgNameInfo(String imgNameInfo) {
		this.imgNameInfo = imgNameInfo;
	}
 
}
