/*
 *  缩略图实现，将图片(jpg、bmp、png、gif等等)真实的变成想要的大小 
 */
package com.liguo.hgl.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import javax.imageio.ImageIO;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.liguo.hgl.proxydao.dao.TbSystemConfigMapper;


/*******************************************************************************
 * 缩略图类（通用） 本java类能将jpg、bmp、png、gif图片文件，进行等比或非等比的大小转换。 具体使用方法
 * compressPic(大图片路径,生成小图片路径,大图片文件名,生成小图片文名,生成小图片宽度,生成小图片高度,是否等比缩放(默认为true))
 */
public class PicCompressUtil {
    private File file = null; // 文件对象
    private File inputFile; // 输入图路径
    private String outputDir; // 输出图路径
    private String outputFileName; // 输出图文件名
    private int outputWidth = 100; // 默认输出图片宽
    private int outputHeight = 100; // 默认输出图片高
    private boolean proportion = true; // 是否等比缩放标记(默认为等比缩放)
    
    @Autowired
    private TbSystemConfigMapper tbSystemConfigMapper;
    
    private final static Log LOGGER = LogFactory.getLog(PicCompressUtil.class);

    public PicCompressUtil() { // 初始化变量
        inputFile = null;
        outputDir = "";
        outputFileName = "";
        outputWidth = 100;
        outputHeight = 100;
    }

    public void setInputFile(File inputFile) {
        this.inputFile = inputFile;
    }

    public void setOutputDir(String outputDir) {
        this.outputDir = outputDir;
    }

    public void setOutputFileName(String outputFileName) {
        this.outputFileName = outputFileName;
    }

    public void setOutputWidth(int outputWidth) {
        this.outputWidth = outputWidth;
    }

    public void setOutputHeight(int outputHeight) {
        this.outputHeight = outputHeight;
    }

    public void setWidthAndHeight(int width, int height) {
        this.outputWidth = width;
        this.outputHeight = height;
    }

    /*
     * 获得图片大小 传入参数 String path ：图片路径
     */
    public long getPicSize(String path) {
        file = new File(path);
        return file.length();
    }

    // 图片处理
    public byte[] compressPic(String suffix) {
        byte[] imageByte = null;
        try {
            Image img = ImageIO.read(inputFile);
            // 判断图片格式是否正确
            if (img.getWidth(null) == -1) {
                System.out.println(" can't read,retry!" + "<BR>");
                return null;
            } else {
                int newWidth;
                int newHeight;
                newWidth = outputWidth; // 输出的图片宽度
                newHeight = outputHeight; // 输出的图片高度
                BufferedImage tag = new BufferedImage((int) newWidth,
                        (int) newHeight, BufferedImage.TYPE_INT_RGB);

                /*
                 * Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
                 */
//              tag.getGraphics().drawImage(
//                      img.getScaledInstance(newWidth, newHeight,
//                              Image.SCALE_SMOOTH), 0, 0, null);
                /*
                 * Image.SCALE_FAST 的缩略算法,在这种缩放算法中，缩放速度比缩放平滑度具有更高的优先级。
                 */
                
                long drawImageTime =Calendar.getInstance().getTimeInMillis();
                LOGGER.info(">>>>>>>>>>>>>>>>>>绘图1开始时间:"+drawImageTime);
                tag.getGraphics().drawImage(img, 0, 0,newWidth,newHeight, null);
                long drawImageEndTime = Calendar.getInstance().getTimeInMillis()-drawImageTime;
                LOGGER.info(">>>>>>>>>>>>>>>>>>绘图1间隔时间:"+drawImageEndTime);
                
                
                FileOutputStream out = new FileOutputStream(outputDir
                        + File.separator + outputFileName);
                ImageIO.write(tag, "jpg", out);
                //上传至文件服务器
//              suffix =FastDFSClient.uploadFile(baos.toByteArray(), suffix);
//              LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>FastDFSClient create object:"+FastDFSClient.class);
                out.close();
            }
        } catch (Exception ex) {
            LOGGER.error(">>>>>>>>>>>>>>>>>>>>>>>error is message:"+ex.getMessage());
        }
        return imageByte;
    }
    
    
    // 图片处理
    public Boolean compressPic() {
        try {

            Image img = ImageIO.read(inputFile);
            // 判断图片格式是否正确
            if (img.getWidth(null) == -1) {
                System.out.println(" can't read,retry!" + "<BR>");
                return false;
            } else {
                int newWidth;
                int newHeight;
                // 判断是否是等比缩放
                if (this.proportion == true) {
                    // 为等比缩放计算输出的图片宽度及高度
                    double rate1 = ((double) img.getWidth(null))
                            / (double) outputWidth + 0.1;
                    double rate2 = ((double) img.getHeight(null))
                            / (double) outputHeight + 0.1;
                    // 根据缩放比率大的进行缩放控制
                    double rate = rate1 > rate2 ? rate1 : rate2;
                    newWidth = (int) (((double) img.getWidth(null)) / rate);
                    newHeight = (int) (((double) img.getHeight(null)) / rate);
                } else {
                    newWidth = outputWidth; // 输出的图片宽度
                    newHeight = outputHeight; // 输出的图片高度
                }
                BufferedImage tag = new BufferedImage((int) newWidth,
                        (int) newHeight, BufferedImage.TYPE_INT_RGB);

                /*
                 * Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
                 */
                tag.getGraphics().drawImage(
                        img.getScaledInstance(newWidth, newHeight,
                                Image.SCALE_SMOOTH), 0, 0, null);
                FileOutputStream out = new FileOutputStream(outputDir
                        + File.separator + outputFileName);
                ImageIO.write(tag, "jpg", out);
                out.close();
            }
        } catch (IOException ex) {
//          ex.printStackTrace();
            return false;
        }
        return true;
    }

    public byte[] compressPic(File inputFile, String outputDir,
            String outputFileName,String suffix,int width, int height,String minImageSavePath){
        // 输入图路径
        this.inputFile = inputFile;
        // 输出图路径
        this.outputDir = minImageSavePath;
        // 输出图文件名
        this.outputFileName = outputFileName;
        setWidthAndHeight(width, height);
        return compressPic(suffix);
    }

    public byte[] compressPic(MultipartFile file, String outputDir,
            String outputFileName, int width, int height, boolean gp,String suffix,String minImageSavePath) {
        
         CommonsMultipartFile cf= (CommonsMultipartFile)file; 
         DiskFileItem fi = (DiskFileItem)cf.getFileItem(); 
         File inputFile = fi.getStoreLocation(); 
            
        // 输入图路径
        this.inputFile = inputFile;
        // 输出图路径
        this.outputDir = minImageSavePath;
        // 输出图文件名
        this.outputFileName = outputFileName;
        // 设置图片长宽
        setWidthAndHeight(width, height);
        // 是否是等比缩放 标记
        this.proportion = gp;
        return compressPic(suffix);
    }
    
    
    
    /****
     * 压缩图片
     * @param file          原图片
     * @param filename
     * @param path
     * @param suffix
     * @param minImageSavePath
     * @throws FileNotFoundException
     * @throws IOException
     * @author wzt
     * @since   2016年7月9日
     */
    public static void compressionImage(Object file, String filename, String path, String minImageSavePath,int width,int height)
        throws FileNotFoundException, IOException {
        PicCompressUtil mypic = new PicCompressUtil();
        byte[] imageByte = null;
        if(StringUtils.isEmpty(filename)){
           return; 
        }
        String []  suffixArr = filename.split(".");
        String suffix = "";
        if(suffixArr.length<1){
            suffix = "jpg";
        }else{
            suffix =  suffixArr[1];
        }
        File f =new File(minImageSavePath);
        if(!f.exists()){
            f.mkdirs();
        }
        
        if(file instanceof MultipartFile){
            MultipartFile  mFile = (MultipartFile) file; 
            imageByte =mypic.compressPic(mFile, path, filename, 2000, 2000, true,suffix,minImageSavePath);
        }
        
        if(file instanceof File){
            File  mFile = (File) file; 
            imageByte =mypic.compressPic(mFile, path, filename,suffix,width,height,minImageSavePath);
        }
        
    }


    // main测试
    // compressPic(大图片路径,生成小图片路径,大图片文件名,生成小图片文名,生成小图片宽度,生成小图片高度,是否等比缩放(默认为true))
//  public static void main(String[] arg) {
//      PicCompressUtil mypic = new PicCompressUtil();
//      System.out.println("输入的图片大小："
//              + mypic.getPicSize("d:\\test\\2015111917111453.jpg") / 1024
//              + "KB");
//      int count = 0; // 记录全部图片压缩所用时间
//      for (int i = 0; i < 10; i++) {
//          int start = (int) System.currentTimeMillis(); // 开始时间
//          mypic.compressPic(new File("D:\\用户目录\\我的图片\\QQ图片20150729142346.jpg"), "D:\\test\\", "test","jpg");
//          int end = (int) System.currentTimeMillis(); // 结束时间
//          int re = end - start; // 但图片生成处理时间
//          count += re;
//          System.out.println("第" + (i + 1) + "张图片压缩处理使用了: " + re + "毫秒");
//          System.out.println("输出的图片大小："
//                  + mypic.getPicSize("d:\\test\\test\\r1" + i + ".jpg")
//                  / 1024 + "KB");
//      }
//      System.out.println("总共用了：" + count + "毫秒");
//  }
}
