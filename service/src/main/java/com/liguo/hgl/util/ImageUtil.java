package com.liguo.hgl.util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

import com.liguo.hgl.common.HglContants;

public class ImageUtil {

    // --------------------------------------------------------------------------------------------
    // static methods
    // --------------------------------------------------------------------------------------------

    /**
     * 保存图片
     * @param file
     * @param imageId
     * @param moduleId
     */
   /* public static void saveImageFile(FormFile file, String imageId, String moduleId, String dir) {

        String pathDir = System.getProperty("user.home") + File.separator + EcWebConstants.FILE_PATH + File.separator + moduleId + File.separator + dir;
        String startWith = imageId.substring(0, imageId.lastIndexOf("_"));
        File tmpFile = new File(pathDir);
        if (!tmpFile.exists()) {
            tmpFile.mkdirs();
        } else {
            //先删除之前存在的文件
            File[] list = tmpFile.listFiles();
            for (File del : list) {
                if (del.isFile() && del.getName().startsWith(startWith)) {
                    del.delete();
                }
            }
        }

        InputStream is = null;
        OutputStream os = null;
        try {

            if (new File(pathDir + "/" + imageId).length() < 1024 * 1024) {

                File imageFile = new File(pathDir, imageId);
                is = file.getInputStream();
                os = new FileOutputStream(imageFile);
                while (true) {
                    byte[] buffer = new byte[10 * 1024];
                    int read = is.read(buffer);
                    if (read < 0) {
                        break;
                    }

                    os.write(buffer);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }*/

    /**
     * 保存图片
     * @param file
     * @param imageId
     * @param moduleId
     */
   /* public static void saveImageFile(FormFile file, String imageId, String moduleId) {

        String pathDir = System.getProperty("user.home") + File.separator + EcWebConstants.FILE_PATH + File.separator + moduleId;

        File tmpFile = new File(pathDir);
        if (!tmpFile.exists()) {
            tmpFile.mkdirs();
        }

        InputStream is = null;
        OutputStream os = null;
        try {

            if (new File(pathDir + "/" + imageId).length() < 1024 * 1024) {
                File imageFile = new File(pathDir, imageId);
                is = file.getInputStream();
                os = new FileOutputStream(imageFile);
                while (true) {
                    byte[] buffer = new byte[10 * 1024];
                    int read = is.read(buffer);
                    if (read < 0) {
                        break;
                    }

                    os.write(buffer);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
*/
    /* ----------------------------------------------------------------------------------------- */

    /**
     * 显示服务器上的图片
     * @param imageId
     * @param moduleId
     */
    public static void showImage(String imageId, String moduleId,HttpServletResponse response,HttpServletRequest request) {

        try {
        	response.setContentType("image/png");
        	
        	String pathDir = HglContants.LOGO_PATH;
            File imageFile = new File(pathDir, imageId);
            
            if(!imageFile.exists()){
            	response.setStatus(204);
            	return;
            }
            
            String fileMd5 = DigestUtils.md5Hex(new FileInputStream(imageFile));
            
        	//加入浏览器缓存判断
            String etag = request.getHeader("If-None-Match");
            if(etag != null && etag.equals(fileMd5)){
            	response.setStatus(304);
            	response.setHeader("ETag",fileMd5);
            	response.setHeader("Last-Modified","Fri, 13 Nov 2015 02:49:08 GMT");
            	return;
            }
            response.setHeader("ETag",fileMd5);
            response.setHeader("Last-Modified","Fri, 13 Nov 2015 02:49:08 GMT");
            //加入浏览器缓存判断end
            
            InputStream is = new FileInputStream(imageFile);
            
            OutputStream os = response.getOutputStream();
            while (true) {
                byte[] buffer = new byte[10 * 1024];
                int read = is.read(buffer);
                if (read < 0) {
                    break;
                }

                os.write(buffer);
            }

            os.close();
            is.close();
            
        } catch (Exception e) {

        }
    }
    
    /**
     * 显示服务器上的图片
     * @param imageId
     * @param moduleId
     */
    public static void mergeAndShowImage(String[] imageIds, String moduleId,HttpServletResponse response,HttpServletRequest request) {

        try {
        	
        	//计算缓存id，将imageIds合并md5
        	String cacheId = "";
        	for(String t : imageIds){
        		cacheId += t;
        	}
        	cacheId = DigestUtils.md5Hex(cacheId);
        	
        	//加入浏览器缓存判断
            String etag = request.getHeader("If-None-Match");
            if(etag != null && etag.equals(cacheId)){
            	response.setStatus(304);
            	response.setHeader("ETag",cacheId);
            	response.setHeader("Last-Modified","Fri, 13 Nov 2015 02:49:08 GMT");
            	return;
            }
            response.setHeader("ETag",cacheId);
            response.setHeader("Last-Modified","Fri, 13 Nov 2015 02:49:08 GMT");
            //加入浏览器缓存判断end
        	
        	int imgWidth = 170;
        	int imgHeight = 68;
        	int rowCount = 2;
        	int colCount = 7;
        	
            String pathDir = HglContants.LOGO_PATH;
            
            BufferedImage result = new BufferedImage(imgWidth*colCount,imgHeight*rowCount,BufferedImage.TYPE_INT_RGB);
            //第一张图片本来应该显示“热门商家”四个字，这里第一张图片填充第一个商家的logo，在页面上“热门商家”四个字的图片通过img标签来显示
            int[] imgArray = getImageRGBArray(new File(pathDir, imageIds[0]),imgWidth,imgHeight);
            result.setRGB(0,0, imgWidth,imgHeight,imgArray,0,imgWidth);
            
            
            String imageId = null;
            for(int i = 0 ; i < imageIds.length ; i ++){
            	
            	imageId = imageIds[i];
            	File imageFile = new File(pathDir, imageId);
            	imgArray = getImageRGBArray(imageFile,imgWidth,imgHeight);
            	result.setRGB(imgWidth*((i+1)%(colCount)),imgHeight*(i>5?1:0), imgWidth,imgHeight,imgArray,0,imgWidth);
            }
            
            response.setContentType("image/png");
            OutputStream os = response.getOutputStream();
            ImageIO.write(result, "jpg", os);
            os.flush();
            os.close();
            
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
    
    private static int[] getImageRGBArray(File imageFile,int width,int height) throws IOException{
    	
    	int[] imgArray;
    	if(!imageFile.exists()){
    		imgArray = new int[width*height];
    		for(int i = 0 ; i < imgArray.length ; i ++){
    			imgArray[i] = -1;
    		}
    		return imgArray;
    	}
    	
    	BufferedImage image = ImageIO.read(imageFile);
    	
    	BufferedImage sizeOkImg = new BufferedImage(width,height,image.getType());
    	Graphics g = sizeOkImg.getGraphics();
    	g.drawImage(image.getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING),0,0,width,height,null);
    	g.dispose();
    	
    	imgArray = new int[width*height];
    	imgArray = sizeOkImg.getRGB(0,0,width,height,imgArray,0,width);
    	
    	return imgArray;
    }

    /* ----------------------------------------------------------------------------------------- */

    /**
     * 删除图片
     * @param imageId
     * @param moduleId
     */
    public static void deleteImage(String imageId, String moduleId) {

        deleteImageSingle(imageId, moduleId);
    }

    /**
     * 显示服务器上的图片
     * @param path 图片目录
     * @param imageName 图片名称
     */
    public static void showImage(String path,String imageName,HttpServletResponse response) {

        try {
            String pathDir = System.getProperty("user.home") + File.separator+path + File.separator+imageName;
            
            System.out.println("图片路径："+pathDir);
            
            File imageFile = new File(pathDir);
            InputStream is = new FileInputStream(imageFile);
            OutputStream os = response.getOutputStream();
            while (true) {
                byte[] buffer = new byte[10 * 1024];
                int read = is.read(buffer);
                if (read < 0) {
                    break;
                }
                os.write(buffer);
            }
            os.close();
            is.close();
        } catch (Exception e) {

        }
    }
    
    /**
     * 显示服务器上的图片
     * @param path 图片目录
     * @param imageName 图片名称
     */
    public static void showImageProduct(Integer id,String imgName,HttpServletResponse response) {
        try {
            String pathDir = HglContants.PRODUCT_PATH+id+File.separator+imgName;
            System.out.println(pathDir);
            File imageFile = new File(pathDir);
            InputStream is = new FileInputStream(imageFile);
            OutputStream os = response.getOutputStream();
            while (true) {
                byte[] buffer = new byte[10 * 1024];
                int read = is.read(buffer);
                if (read < 0) {
                    break;
                }
                os.write(buffer);
            }
            os.close();
            is.close();
        } catch (Exception e) {
        }
    }
    
    public static void showImageWapProduct(Integer id,String imgName,HttpServletResponse response) {
        try {
            String pathDir = HglContants.WAP_PRODUCT+id+File.separator+imgName;
            System.out.println(pathDir);
            File imageFile = new File(pathDir);
            InputStream is = new FileInputStream(imageFile);
            OutputStream os = response.getOutputStream();
            while (true) {
                byte[] buffer = new byte[10 * 1024];
                int read = is.read(buffer);
                if (read < 0) {
                    break;
                }
                os.write(buffer);
            }
            os.close();
            is.close();
        } catch (Exception e) {
        }
    }
    
    public static void showImageWapProduct(Integer id,String imgName,HttpServletResponse response,String path) {
        try {
           /* String pathDir = HglContants.WAP_PRODUCT+id+File.separator+imgName;*/
            String pathDir = path+id+File.separator+imgName;
            System.out.println(pathDir);
            File imageFile = new File(pathDir);
            InputStream is = new FileInputStream(imageFile);
            OutputStream os = response.getOutputStream();
            while (true) {
                byte[] buffer = new byte[10 * 1024];
                int read = is.read(buffer);
                if (read < 0) {
                    break;
                }
                os.write(buffer);
            }
            os.close();
            is.close();
        } catch (Exception e) {
        }
    }
    /* ----------------------------------------------------------------------------------------- */

    public static void showWapHeaderImage(Integer id,String imageName,HttpServletRequest request,HttpServletResponse response){
    	String pathDir = HglContants.USER_PATH + id + File.separator + imageName;
    	System.out.println("头像路径: "+pathDir);   	
    	try {
    		File fileImage = new File(pathDir);
    		if(!fileImage.exists()){
    			return;
    		}
			InputStream is = new FileInputStream(fileImage);
			OutputStream os = response.getOutputStream();
			while(true){
                byte[] buffer = new byte[10*1024];
                int read = is.read(buffer);
                if (read<0){
                    break;
                }
                os.write(buffer);
            }
            os.close();
            is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
    public static void deleteImageSingle(String imageId, String moduleId) {
        String pathDir;
        File imageFile;

        if (moduleId == null) {
            pathDir = HglContants.LOGO_PATH;
            imageFile = new File(pathDir, imageId + ".jpg");
        } else {
            pathDir = HglContants.LOGO_PATH;
            imageFile = new File(pathDir, imageId);
        }

        if (imageFile.exists()) {
            imageFile.delete();
        }
    }

    /* ----------------------------------------------------------------------------------------- */

    /*
     * 品牌图片
     * imgName:要修改的图片的全称
     * newName:新命名
     * 用于品牌上传文件名字修改*/
    public static void changImageName(String imgName,String newName) throws Exception{
    	/* File srcDir=new File(HglContants.LOGO_PATH+imgFile.getImgNameInfo());   */
    	 File srcDir=new File(HglContants.LOGO_PATH+imgName);
		 File srcDir1=new File(HglContants.LOGO_PATH+File.separator);
		 FileInputStream fis = new FileInputStream(srcDir);
		 String destFileName = newName;
    	 FileOutputStream fos = new FileOutputStream(new File(srcDir1,destFileName));
    	 /*给传过来的图片重全名*/
    	 copy(fis,fos);
    	 fis.close();
    	 fos.close();
    }
    
    /*
     * 产品图片
     * imgName:要修改的图片的全称
     * newName:新命名
     * 用于品牌上传文件名字修改*/
    public static void changImageName(String imgName,String newName,Integer id) throws Exception{
    	/* File srcDir=new File(HglContants.LOGO_PATH+imgFile.getImgNameInfo());   */
    	 File srcDir=new File(HglContants.PRODUCT_TESTPATH+imgName);
		 File srcDir1=new File(HglContants.PRODUCT_PATH+File.separator+id);
		 
		 if (!srcDir1.exists()) {
			 srcDir1.mkdirs();
         }
		 FileInputStream fis = new FileInputStream(srcDir);
		 String destFileName = newName;
    	 FileOutputStream fos = new FileOutputStream(new File(srcDir1,destFileName));
    	 /*给传过来的图片重全名*/
    	 copy(fis,fos);
    	 fis.close();
    	 fos.close();
    }
    /*
     * 图片修改名字后上传（重新绘制小图片）
     * imgName:要修改的图片的全称
     * newName:新命名
     * 用于品牌上传文件名字修改*/
    public static void uploadNewImage(String imgName,String newName,Integer id) throws Exception{
    	/* File srcDir=new File(HglContants.LOGO_PATH+imgFile.getImgNameInfo());   */
    	/* File fileName = new File(HglContants.CUSTOMER_TESTPATH);*/
    	 File srcDir = new File(HglContants.CUSTOMER_TESTPATH+imgName);
		 /*File srcDir1=new File(HglContants.CUSTOMER_PATH+File.separator+id);*/
		 //图片进行压缩上传
		 PicCompressUtil.compressionImage(srcDir, newName, imgName, HglContants.CUSTOMER_PATH+id+File.separator,400,400);
	
    }
    
    /*
     * 图片修改名字后上传（重新绘制小图片）
     * testImgName:要修改的图片的全称
     * newName:新命名
     * 用于品牌上传文件名字修改*/
    public static void commonUploadNewImage(String testImgName,String newName,Integer id,String testPath,String newPath) throws Exception{
    	 File srcDir = new File(testImgName);
		 //图片进行压缩上传
		 PicCompressUtil.compressionImage(srcDir, newName, testImgName, newPath+id+File.separator,400,400);
	
    }
    /**
     * 公共的图片
     * 上传到指定路径
     * path1:图片路径包抱图片名字
     * path2:图片要上传的路径
     * id: 以ID名字给文件命名
     * newName:图片的新名字
     * */
    public static void changImagePath(String path1,String path2,Integer id,String newName) throws Exception{
    	/* File srcDir=new File(HglContants.LOGO_PATH+imgFile.getImgNameInfo());   */
    	 File srcDir=new File(path1);
		 File srcDir1=new File(path2+id);
		 if (!srcDir1.exists()) {
			 srcDir1.mkdirs();
         }
		 FileInputStream fis = new FileInputStream(srcDir);
		 String destFileName = newName;
    	 FileOutputStream fos = new FileOutputStream(new File(srcDir1,destFileName));
    	 /*给传过来的图片重全名*/
    	 copy(fis,fos);
    	 fis.close();
    	 fos.close();
    }
    
    public static void changRegisterPathAndName(String imgName,String newName,Integer id) throws Exception{
    	/* File srcDir=new File(HglContants.LOGO_PATH+imgFile.getImgNameInfo());   */
    	 File srcDir=new File(HglContants.SHOP_INFO_TESTPATH+imgName);
		 File srcDir1=new File(HglContants.SHOP_INFO_PATH+File.separator+id);
		 
		 if (!srcDir1.exists()) {
			 srcDir1.mkdirs();
         }
		 FileInputStream fis = new FileInputStream(srcDir);
		 String destFileName = newName;
    	 FileOutputStream fos = new FileOutputStream(new File(srcDir1,destFileName));
    	 /*给传过来的图片重全名*/
    	 copy(fis,fos);
    	 fis.close();
    	 fos.close();
    }
    
    public static void changRegisterPathAndBrandLogoName(String imgName,String newName,Integer id) throws Exception{
    	 File srcDir=new File(HglContants.SHOP_INFO_TESTPATH+imgName);
		 File srcDir1=new File(HglContants.LOGO_PATH+File.separator);
		 if (!srcDir1.exists()) {
			 srcDir1.mkdirs();
         }
		 FileInputStream fis = new FileInputStream(srcDir);
		 String destFileName = newName;
    	 FileOutputStream fos = new FileOutputStream(new File(srcDir1,destFileName));
    	 /*给传过来的图片重全名*/
    	 copy(fis,fos);
    	 fis.close();
    	 fos.close();
    }
    
    
    private static void copy(InputStream ips,OutputStream fos) throws Exception{
      	int len = 0;
      	byte[] buf = new byte[1024];
      	while((len = ips.read(buf)) != -1){
      		fos.write(buf,0,len);
      	}
      }
    /**
     * 读取经销商图片
     * @param id 经销商shopid
     * @param imgName 图片名称
     * @param response
     * @author zss
     */
	public static void showImageShop(Integer id, String imgName,
			HttpServletResponse response) {
		  try {
	            String pathDir = HglContants.SHOP_INFO_PATH+id+File.separator+imgName;
	            System.out.println(pathDir);
	            File imageFile = new File(pathDir);
	            InputStream is = new FileInputStream(imageFile);
	            OutputStream os = response.getOutputStream();
	            while (true) {
	                byte[] buffer = new byte[10 * 1024];
	                int read = is.read(buffer);
	                if (read < 0) {
	                    break;
	                }
	                os.write(buffer);
	            }
	            os.close();
	            is.close();
	        } catch (Exception e) {
	        }
		
	}
	
	public static void showImageActivity(Integer id,String imgName,HttpServletResponse response){
		String pathDir = HglContants.ACTIVITY_PATH+id+File.separator+imgName;
		System.out.println(pathDir);
		
		try {
			File imageFile = new File(pathDir);
			InputStream is = new FileInputStream(imageFile);
			OutputStream os = response.getOutputStream();
			while (true) {
				byte[] buffer = new byte[10 * 1024];
				int read = is.read(buffer);
				if (read < 0) {
					break;
				}
				os.write(buffer);
			}
			os.close();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public static void changMerchantsPathAndName(String string, String newName) throws Exception {
		 File srcDir=new File(HglContants.SHOP_INFO_TESTPATH+string);
		 File srcDir1=new File(HglContants.MERCHANT_PATH+File.separator);
		 
		 if (!srcDir1.exists()) {
			 srcDir1.mkdirs();
         }
		 FileInputStream fis = new FileInputStream(srcDir);
		 String destFileName = newName;
    	 FileOutputStream fos = new FileOutputStream(new File(srcDir1,destFileName));
    	 /*给传过来的图片重全名*/
    	 copy(fis,fos);
    	 fis.close();
    	 fos.close();
		
	}
	/**
	 * 读取招商图片信息
	 * @param id 招商Id
	 * @param response
	 * @author zss
	 */
	public static void showImageMerchants(Integer id,
			HttpServletResponse response) {
		 try {
	            String pathDir = HglContants.MERCHANT_PATH+id+".jpg";
	            File imageFile = new File(pathDir);
	            InputStream is = new FileInputStream(imageFile);
	            OutputStream os = response.getOutputStream();
	            while (true) {
	                byte[] buffer = new byte[10 * 1024];
	                int read = is.read(buffer);
	                if (read < 0) {
	                    break;
	                }
	                os.write(buffer);
	            }
	            os.close();
	            is.close();
	        } catch (Exception e) {
	        }
		
	}
	/**
	 * 将path2目录下的所有文件Copy到path1
	 * id:新文件名
	 * */
	 public static void copyPathChangeName(String path1,String path2,Integer id) throws Exception{
		 File[] fileList=new File(path2).listFiles();
		 FileInputStream fis = null;
		 File srcDir1=new File(path1+id.toString());
		 if (!srcDir1.exists()) {
			 srcDir1.mkdirs();
	         }
		 FileOutputStream fos = null;
		 for(int i=0;i<fileList.length;i++){
			 System.out.println(fileList[i].getName());
			 File srcDir=new File(path2+File.separator+fileList[i].getName());
			 fis = new FileInputStream(srcDir);
			 fos = new FileOutputStream(new File(srcDir1,fileList[i].getName()));
			 copy(fis,fos);
		 }
		 fis.close();
    	 fos.close();
    	 }

	 /**
	  * 修改漏发图片位置
	  * @param string
	  * @param newName
	  * @param orderGroupId
	  * @author zss
	 * @throws Exception 
	  */
	public static void changReissueImagePathAndName(String imgName,
			String newName, Integer id) throws Exception {
		File srcDir=new File(HglContants.SHOP_INFO_TESTPATH+imgName);
		 File srcDir1=new File(HglContants.REISSUE_ODERGROUP_PATH+File.separator+id);
		 
		 if (!srcDir1.exists()) {
			 srcDir1.mkdirs();
        }
		 FileInputStream fis = new FileInputStream(srcDir);
		 String destFileName = newName;
	   	 FileOutputStream fos = new FileOutputStream(new File(srcDir1,destFileName));
	   	 /*给传过来的图片重全名*/
	   	 copy(fis,fos);
	   	 fis.close();
	   	 fos.close();
		
	}

	public static void showIReissueOderImage(Integer id,String imgName,
			HttpServletResponse response)  {
		/*File file = new File(HglContants.REISSUE_ODERGROUP_PATH+File.separator+id);
		File [] fileList = file.listFiles();
		InputStream is = null;
		OutputStream os =null;
		 for(int i=0;i<fileList.length;i++){
			 System.out.println(fileList[i].getName());
			
			 is = new FileInputStream(HglContants.REISSUE_ODERGROUP_PATH+id+File.separator+fileList[i].getName());
			 os = response.getOutputStream();
          
		 }
		 os.close();
         is.close();*/
		  try {
	            String pathDir = HglContants.REISSUE_ODERGROUP_PATH+id+File.separator+imgName;
	            File imageFile = new File(pathDir);
	            InputStream is = new FileInputStream(imageFile);
	            OutputStream os = response.getOutputStream();
	            while (true) {
	                byte[] buffer = new byte[10 * 1024];
	                int read = is.read(buffer);
	                if (read < 0) {
	                    break;
	                }
	                os.write(buffer);
	            }
	            os.close();
	            is.close();
	        } catch (Exception e) {
	        }
		
	}
    /**
     * 显示服务器上相册空间的图片
     * @param path 图片目录
     * @param imageName 图片名称
     */
    public static void showImageAlbumspace(String id,String imgName,HttpServletResponse response) {
        try {
            String pathDir = HglContants.WAP_ALBUM_SPACE_MIN_SAVE_PATH+id+File.separator+imgName;
            System.out.println(pathDir);
            File imageFile = new File(pathDir);
            InputStream is = new FileInputStream(imageFile);
            OutputStream os = response.getOutputStream();
            while (true) {
                byte[] buffer = new byte[10 * 1024];
                int read = is.read(buffer);
                if (read < 0) {
                    break;
                }
                os.write(buffer);
            }
            os.close();
            is.close();
        } catch (Exception e) {
        }
    }
    
    /*移动活动图片路径*/
    public static void changActivityImageName(String imgName,String newName,Integer id) throws Exception{
    	 File srcDir=new File(HglContants.ACTIVITY_TESTPATH+imgName);
		 File srcDir1=new File(HglContants.ACTIVITY_PATH+File.separator+id);
		 
		 if (!srcDir1.exists()) {
			 srcDir1.mkdirs();
         }
		 FileInputStream fis = new FileInputStream(srcDir);
		 String destFileName = newName;
    	 FileOutputStream fos = new FileOutputStream(new File(srcDir1,destFileName));
    	 /*给传过来的图片重全名*/
    	 copy(fis,fos);
    	 fis.close();
    	 fos.close();
    }
    
    /*显示活动图片*/
    public static void showActivityImage(Integer id,String imgName,HttpServletResponse response){
    	try {
			String pathDir = HglContants.ACTIVITY_PATH + id + File.separator
 					+ imgName;
			System.out.println("活动图片路径: " + pathDir);
			File imageFile = new File(pathDir);
			if(!imageFile.exists()){
				return;
			}
			InputStream is = new FileInputStream(imageFile);
			OutputStream os = response.getOutputStream();
			while (true) {
				byte[] buffer = new byte[10 * 1024];
				int read = is.read(buffer);
				if (read < 0) {
					break;
				}
				os.write(buffer);
			}
			os.close();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    /**
     * 个人二维码
     * @param code
     * @param response
     * @author zss
     */
	public static void showImageLogoCode(String code,
			HttpServletResponse response) {
		try {
			String pathDir = HglContants.USER_LOGO_CODE_PATH  + File.separator
 					+ code+".jpg";
			File imageFile = new File(pathDir);
			InputStream is = new FileInputStream(imageFile);
			OutputStream os = response.getOutputStream();
			while (true) {
				byte[] buffer = new byte[10 * 1024];
				int read = is.read(buffer);
				if (read < 0) {
					break;
				}
				os.write(buffer);
			}
			os.close();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public static void showImageshopCode(Integer id,
			HttpServletResponse response) {
		try {
			String pathDir = HglContants.SHOP_LOGO_CODE_PATH+ File.separator
 					+ id+".jpg";
			File imageFile = new File(pathDir);
			InputStream is = new FileInputStream(imageFile);
			OutputStream os = response.getOutputStream();
			while (true) {
				byte[] buffer = new byte[10 * 1024];
				int read = is.read(buffer);
				if (read < 0) {
					break;
				}
				os.write(buffer);
			}
			os.close();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
   public static void showImageCommon(String imgPath,String imageName,HttpServletResponse response) {

        try {
            String pathDir = imgPath +imageName;
            System.out.println("图片路径："+pathDir);
            File imageFile = new File(pathDir);
            InputStream is = new FileInputStream(imageFile);
            OutputStream os = response.getOutputStream();
            while (true) {
                byte[] buffer = new byte[10 * 1024];
                int read = is.read(buffer);
                if (read < 0) {
                    break;
                }
                os.write(buffer);
            }
            os.close();
            is.close();
        } catch (Exception e) {

        }
    }
	    
}
