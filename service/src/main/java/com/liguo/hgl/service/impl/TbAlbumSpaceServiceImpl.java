package com.liguo.hgl.service.impl;

import com.github.junrar.Archive;
import com.github.junrar.rarfile.FileHeader;
import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dao.TbAlbumSpaceMapper;
import com.liguo.hgl.proxydao.dao.TbProductTypeMapper;
import com.liguo.hgl.proxydao.dao.TbSystemConfigMapper;
import com.liguo.hgl.proxydao.dto.WebUserDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbAlbumSpace;
import com.liguo.hgl.proxydao.model.TbSystemConfig;
import com.liguo.hgl.service.IProductTypeService;
import com.liguo.hgl.service.TbAlbumSpaceService;
import com.liguo.hgl.util.PicCompressUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observer;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Service
@Scope(value="prototype")
public class TbAlbumSpaceServiceImpl implements TbAlbumSpaceService {
    @Autowired
    private TbAlbumSpaceMapper tbAlbumSpaceMapper;
    
    @Autowired
    private TbSystemConfigMapper tbSystemConfigMapper;
    
    @Autowired
    private TbProductTypeMapper tbProductTypeMapper;
    
    @Autowired
    @Qualifier("productTypeService")
    private IProductTypeService productTypeService;
    
    
    
    
    
    
    
    
    
    
    
    
    private static final String IMG_MIME_TYPES = "bmp,jpg,gif,tga,png";
    
    
    

    private static final Logger logger = LoggerFactory.getLogger(TbAlbumSpaceServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbAlbumSpaceMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbAlbumSpace selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbAlbumSpaceMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbAlbumSpace> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbAlbumSpaceMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbAlbumSpaceMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbAlbumSpace record) throws RuntimeException {
        try {
            return this.tbAlbumSpaceMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbAlbumSpace record) throws RuntimeException {
        try {
            return this.tbAlbumSpaceMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbAlbumSpace record) throws RuntimeException {
        try {
            return this.tbAlbumSpaceMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<TbAlbumSpace> findImageNowNameBatch(Criteria example) throws RuntimeException {
        
        return this.tbAlbumSpaceMapper.findImageNowNameBatch(example);
    }

    @Override
    public List<TbAlbumSpace> searchImage(Criteria example) throws RuntimeException {
        
        return this.tbAlbumSpaceMapper.searchImage(example);
    }

    @Override
    public List<String> findImageNameOldAll(Criteria example) throws RuntimeException {
        
        return this.tbAlbumSpaceMapper.findImageNameOldAll(example);
    }

    @Override
    public void albumSpaceUpload(WebUserDto webUserDto,HttpServletRequest request,Map<String,String>map) throws RuntimeException {
        
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile imageData = multipartRequest.getFile("file");
        String fileName = imageData.getOriginalFilename();
        String[] arr = fileName.split("\\.");
        String fileType = arr[arr.length - 1].toLowerCase();
        map.put("success", "1");
        map.put("message", "上传成功");
        // 获得图片新名称
        long name = System.currentTimeMillis();
        String tempName =String.valueOf(name)+((int)Math.random()*10);
        
        Integer userId =webUserDto.getId();
        
        /*********动态配置图片大小start*********************/
        TbSystemConfig tbSystemConfig =tbSystemConfigMapper.selectByPrimaryKey(2);
        int width =Integer.valueOf(tbSystemConfig.getConfigKey());
        int height = Integer.valueOf(tbSystemConfig.getConfigValue());
        
        /**********动态配置图片大小end********************/
        
        
        
        Criteria criteria = new Criteria();
        criteria.put("imageUrl", userId);
        //查询用户上传的所有图片名称
        List<String>  oldNameAllList =tbAlbumSpaceMapper.findImageNameOldAll(criteria);
        
        List<String>  oldNameList =new ArrayList<String>();
        
        //组装用户保存文件目录 +当前用户id
        String  dir =HglContants.WAP_ALBUM_SPACE+userId+File.separator;
        
        //组装小图标保存目录
        String minImageSavePath = HglContants.WAP_ALBUM_SPACE_MIN_SAVE_PATH+userId+File.separator;
        
        
        // 图片
        if (IMG_MIME_TYPES.indexOf(fileType) != -1) {
            String imageName=replaceFileName(fileName);
            if(!CollectionUtils.isEmpty(oldNameAllList)&&oldNameAllList.contains(imageName)){
                imageName =UUID.randomUUID().toString()+arr[arr.length-1];
            }
            try {
                //上传到相册目录
                productTypeService.uploadForm(imageData, imageName, dir,arr[1],minImageSavePath);
                PicCompressUtil.compressionImage(new File(dir+imageName), imageName, null, minImageSavePath,width,height);
                //保存图片信息
                saveTbAlbumSpace(imageName, "",webUserDto);
            }
            catch (Exception e) {
                map.put("success", "2");
                map.put("message", "图片上传失败");
                logger.error(">>>>>>>>image upload is error ,error message:" + e.getMessage());
                return ;
            }
        }
        // zip
        else if ("zip".equals(fileType)) {
            try {
                if(checkCompressionFileName(fileName)){
                    map.put("success", "2");
                    map.put("message", "不能在同一天，导入2个相同的压缩包");
                    return ;  
                }
                ZipInputStream zis = new ZipInputStream(imageData.getInputStream());
                ZipEntry entry = null;
                checkFileNameDistinct(zis,oldNameAllList,oldNameList);
                zis = new ZipInputStream(imageData.getInputStream());
                for(int i=0;(entry = zis.getNextEntry()) != null;i++){
                    String imageName  = oldNameList.get(i);
                    productTypeService.SaveFileFromZipInputStream(zis, dir, imageName);
                    PicCompressUtil.compressionImage(new File(dir+imageName), imageName, null, minImageSavePath,width,height);
                    //保存图片信息
                    saveTbAlbumSpace(imageName, fileName,webUserDto);
                }
                zis.closeEntry();
                zis.close();
            }
            catch (IOException e) {
                map.put("success", "2");
                map.put("message", "zip文件上传失败");
                logger.error(">>>>>>>>>>>>>>>解析zip文件出错!!"+e.getMessage());
                return ;
            }
        }
        // rar
        else if ("rar".equals(fileType)) {
             //RAR 必须需要原文件地址，所以此处需做一个文件copy操作,在HglContants.WAP_PRODUCT_TESTPATH中新建一个文件夹，上传完删除此文件夹
            try {
                if(checkCompressionFileName(fileName)){
                    map.put("success", "2");
                    map.put("message", "不能在同一天，导入2个相同的压缩包");
                    return ;
                }
                
                //临时目录
                String tempDir = HglContants.WAP_ALBUM_SPACE+tempName+File.separator;
                productTypeService.uploadForm(imageData, fileName, tempDir);
                //dir为RAR 文件路径
                String filePath =tempDir+fileName;
                
                /*************这里拆分rar内的文件，完成入库操作************/
                Archive archive  = new Archive(new File(filePath));
                FileHeader fh = null;
                Archive tempA  = archive;
                checkFileNameDistinct(tempA,oldNameAllList,oldNameList);
                archive  = new Archive(new File(filePath));
                for(int i=0;(fh = archive.nextFileHeader()) != null;i++){
                    String imageName = oldNameList.get(i);
                    productTypeService.SaveFileFromRarInputStream(archive,fh, dir,imageName);
                    PicCompressUtil.compressionImage(new File(dir+imageName), imageName, null, minImageSavePath,width,height);
                    saveTbAlbumSpace(imageName, fileName,webUserDto);
                }
                archive.close();
                
                //上传成功，删除临时文件夹,以及下面的所有文件
                File file = new File(tempDir);
                deleteDir(file);
                
            }
            catch (Exception e) {
                map.put("success", "2");
                map.put("message", "rar文件上传失败");
                logger.error(">>>>>>>>rar upload is error ,error message:" + e.getMessage());
                return ;
            }
        }
        // 其他均为错误
        else {
            map.put("success", "2");
            map.put("message", "请使用正确的格式,本次上传仅支持图片:bmp,jpg,gif,tga,png压缩文件仅支持:zip,rar");
            logger.error(">>>>>>>>>>>>>>>upload is error,  error message is this upload type not found !!");
            return ;
        }
    }
    
    
    private String replaceFileName(String imageName){
        if(imageName.contains("&")){
            imageName=imageName.replaceAll("&", "hgl");
        }
        if(imageName.contains("?")){
            imageName=imageName.replaceAll("?", "hgl");
        }
        return imageName;
    }
    
    /***
     * 
     * <入库操作>
     * @param url
     * @param nowName       //目前使用原文件名称
     * @param oldName
     * @param tagName
     * @author wzt
     * @since   2016年6月3日
     */
    private   void saveTbAlbumSpace(String oldName,String tagName,WebUserDto webUserDto){
      //保存图片信息
        
        TbAlbumSpace tbAlbumSpace = new TbAlbumSpace();
        //图片路径，是根据相册默认路径+用户id，拼接而成的
        tbAlbumSpace.setImageUrl(String.valueOf(webUserDto.getId()));
        tbAlbumSpace.setUpdateName(webUserDto.getUserName());
        tbAlbumSpace.setImageNameOld(oldName);
        tbAlbumSpace.setImageTagName(tagName);
        tbAlbumSpace.setUpdateTime(Calendar.getInstance().getTime());
        tbAlbumSpaceMapper.insertSelective(tbAlbumSpace);
    }
    
    private  boolean checkCompressionFileName(String fileName) {
        Criteria parameter = new Criteria();
        parameter.put("imageTagName", fileName);
        List<TbAlbumSpace> tbAlbumSpaceList =tbAlbumSpaceMapper.selectByObject(parameter);
        return CollectionUtils.isEmpty(tbAlbumSpaceList)?false:true;
    }
    
    /***
     * 
     * <检查名称是否重复,有重复返回true>
     * @param obj
     * @param list
     * @return
     * @author wzt
     * @since   2016年7月8日
     */
    private void  checkFileNameDistinct(Object obj ,List<String> list,List<String> oldNameList){
        String name ="";
        if(obj instanceof ZipInputStream){
            ZipInputStream zin =(ZipInputStream)obj;
            ZipEntry entry =null;
            try {
                while ((entry = zin.getNextEntry()) != null) {
                    name =entry.getName();
                    name=replaceFileName(name);
                    String temp[] =name.split("\\.");
                    if(list.contains(name)){
                        name =UUID.randomUUID().toString()+"."+temp[temp.length-1];
                    }
                    oldNameList.add(name);
                }
           }
           catch (IOException e) {
             logger.error("checkFileNameDistinct is error ,error message :"+e.getMessage());
               
           }
        }
        
        if(obj instanceof Archive){
            Archive a =(Archive)obj;
            FileHeader fh=null;
            while ((fh = a.nextFileHeader()) != null) {
                name = fh.getFileNameString();
              //如果压缩包中的图片包含特殊字符:"&" "?" ,跳过
                name=replaceFileName(name);
                if(list.contains(name)){
                    name =UUID.randomUUID().toString();
                }
                oldNameList.add(name);
            }
        }
    }
    
    /****
     * 
     * <删除临时文件夹>
     * @param dir
     * @return
     * @author wzt
     * @since   2016年6月3日
     */
    private static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
          //递归删除目录中的子目录下
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }

    @Override
    public void alterImageName(TbAlbumSpace tbAlbumSpace,WebUserDto webUserDto) {
        
        //同步修改服务器文件的名称
        TbAlbumSpace  tbAlbumSpaceOld =tbAlbumSpaceMapper.selectByPrimaryKey(tbAlbumSpace.getId());
        Integer userId =webUserDto.getId();
        String userName =webUserDto.getUserName();
        String imageNameOld =tbAlbumSpaceOld.getImageNameOld();
        String imageNamenow =tbAlbumSpace.getImageNameOld();
        String  oldDir =HglContants.WAP_ALBUM_SPACE+userId+File.separator+imageNameOld;
        String  oldIocDir =HglContants.WAP_ALBUM_SPACE_MIN_SAVE_PATH+userId+File.separator+imageNameOld;
        String  nowDir =HglContants.WAP_ALBUM_SPACE+userId+File.separator+imageNamenow;
        String  nowIocDir =HglContants.WAP_ALBUM_SPACE_MIN_SAVE_PATH+userId+File.separator+imageNamenow;
        File file = new File(oldDir);
        boolean boo= file.renameTo(new File(nowDir));
        File oldIocDirFile = new File(oldIocDir);
        boolean nowIocDirFile= oldIocDirFile.renameTo(new File(nowIocDir));
        if(boo&&nowIocDirFile){
            tbAlbumSpace.setUpdateTime(Calendar.getInstance().getTime());
            tbAlbumSpace.setUpdateName(userName);
            tbAlbumSpaceMapper.updateByPrimaryKeySelective(tbAlbumSpace);
        }
        
    }
}