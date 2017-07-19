package com.liguo.hgl.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dao.TbActivityInfoMapper;
import com.liguo.hgl.proxydao.dto.ActivityInfoDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbActivityInfo;
import com.liguo.hgl.proxydao.model.TbWebUser;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.proxydao.util.StringUtil;
import com.liguo.hgl.service.TbActivityInfoService;
import com.liguo.hgl.service.TbWebUserService;
import com.liguo.hgl.util.ImageUtil;

@Service
@Scope(value="prototype")
public class TbActivityInfoServiceImpl implements TbActivityInfoService {
    @Autowired
    private TbActivityInfoMapper tbActivityInfoMapper;
    
    @Autowired
    private TbWebUserService webUserService;

    private static final Logger logger = LoggerFactory.getLogger(TbActivityInfoServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbActivityInfoMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbActivityInfo selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbActivityInfoMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbActivityInfo> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbActivityInfoMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbActivityInfoMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbActivityInfo record) throws RuntimeException {
        try {
            return this.tbActivityInfoMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbActivityInfo record) throws RuntimeException {
        try {
            return this.tbActivityInfoMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbActivityInfo record) throws RuntimeException {
        try {
            return this.tbActivityInfoMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

	@Override
	public List<TbActivityInfo> selectObjectByPage(Criteria example,
			PageDto page) throws RuntimeException {
		try{
			return this.tbActivityInfoMapper.selectObjectByPage(example, page);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public int insert(TbActivityInfo record) throws RuntimeException {
		try{
			return this.tbActivityInfoMapper.insert(record);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public int insertActivityInfo(ActivityInfoDto activityInfoDto,Integer createdBy)
			throws RuntimeException {
		try{
			TbWebUser webUser = webUserService.selectByPrimaryKey(createdBy);
					
			TbActivityInfo activityInfo = new TbActivityInfo();			
			activityInfo.setShopId(webUser.getShopId());
			activityInfo.setActivityName(activityInfoDto.getActivityName());
			activityInfo.setActivityDetail(activityInfoDto.getActivityDetail());
								
			if(StringUtil.isNotBlank(activityInfoDto.getStartYear()) && 
			   StringUtil.isNotBlank(activityInfoDto.getStartMonth()) && 
			   StringUtil.isNotBlank(activityInfoDto.getStartDay())){
				String startDate = activityInfoDto.getStartYear()+"-"+activityInfoDto.getStartMonth()+"-"+activityInfoDto.getStartDay();
				activityInfo.setStartDate(Date.valueOf(startDate));
			}
			if(StringUtil.isNotBlank(activityInfoDto.getEndYear())&&
			   StringUtil.isNotBlank(activityInfoDto.getEndMonth())&&
			   StringUtil.isNotBlank(activityInfoDto.getEndDay())){
				String endDate = activityInfoDto.getEndYear()+"-"+activityInfoDto.getEndMonth()+"-"+activityInfoDto.getEndDay();				
				activityInfo.setEndDate(Date.valueOf(endDate));
			}			
			if(StringUtil.isNotBlank(activityInfoDto.getStartHour())&&StringUtil.isNotBlank(activityInfoDto.getStartMin())){
				String startTime = activityInfoDto.getStartHour()+":"+activityInfoDto.getStartMin()+":00";
				activityInfo.setStartTime(Time.valueOf(startTime));
			}			
			if(StringUtil.isNotBlank(activityInfoDto.getEndHour())&&StringUtil.isNotBlank(activityInfoDto.getEndMin())){
				String endTime = activityInfoDto.getEndHour()+":"+activityInfoDto.getEndMin()+":00";
				activityInfo.setEndTime(Time.valueOf(endTime));
			}
						
			activityInfo.setDisplayBegin(activityInfoDto.getDisplayBegin());
			activityInfo.setDisplayRemaining(activityInfo.getDisplayRemaining());			
			activityInfo.setStatus(activityInfoDto.getStatus());
			activityInfo.setCreatedBy(createdBy);
			activityInfo.setCreatedDate(System.currentTimeMillis());
			activityInfo.setVersion(1);
			
			this.tbActivityInfoMapper.insert(activityInfo);
			
			Integer id = activityInfo.getId();
			String newName ="";
			String oldName = activityInfoDto.getTitleImage();
			if(StringUtil.isNotBlank(oldName)){
				newName = StringUtil.changeFileName(oldName.split("_")[0],oldName);
				ImageUtil.changActivityImageName(oldName,newName,id);				
			}
			activityInfo.setTitleImage(newName);
			
			newName = "";
			oldName = activityInfoDto.getDetailImageOne();			
			if(StringUtil.isNotBlank(oldName)){				
				newName = StringUtil.changeFileName(oldName.split("_")[0],oldName);
				ImageUtil.changActivityImageName(oldName,newName,id);							
			}
			activityInfo.setImage(newName);
			
			newName = "";
			oldName = activityInfoDto.getDetailImageTwo();
			if(StringUtil.isNotBlank(oldName)){				
				newName = StringUtil.changeFileName(oldName.split("_")[0],oldName);
				ImageUtil.changActivityImageName(oldName,newName,id);
			}
			activityInfo.setImage(activityInfo.getImage()+","+newName);
			
			newName = "";
			oldName = activityInfoDto.getDetailImageThree();
			if(StringUtil.isNotBlank(oldName)){
				newName = StringUtil.changeFileName(oldName.split("_")[0],oldName);
				ImageUtil.changActivityImageName(oldName,newName, activityInfo.getId());				
			}
			activityInfo.setImage(activityInfo.getImage()+","+newName);
			
			return this.updateByPrimaryKey(activityInfo);          
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public ActivityInfoDto selectActivityInfoDto(Integer id) {
		
		ActivityInfoDto activityInfoDto = new ActivityInfoDto();
		TbActivityInfo activityInfo = this.selectByPrimaryKey(id);
		if(activityInfo != null){
			activityInfoDto.setId(activityInfo.getId());
			activityInfoDto.setActivityName(activityInfo.getActivityName());
			activityInfoDto.setActivityDetail(activityInfo.getActivityDetail());
			activityInfoDto.setDisplayBegin(activityInfo.getDisplayBegin());
			activityInfoDto.setDisplayRemaining(activityInfo.getDisplayRemaining());
			activityInfoDto.setTitleImage(activityInfo.getTitleImage());
			
			List<String> imgList = new ArrayList<String>();
			if(StringUtils.isNotBlank(activityInfo.getImage())){
				imgList = Arrays.asList(activityInfo.getImage().split(","));
				System.out.println(imgList.size());
				if((imgList.size()>=1) && StringUtil.isNotBlank(imgList.get(0))){
					activityInfoDto.setDetailImageOne(imgList.get(0));
				}
				if((imgList.size()>=2) && StringUtil.isNotBlank(imgList.get(1))){
					activityInfoDto.setDetailImageTwo(imgList.get(1));
				}
				if((imgList.size()>=3) && StringUtil.isNotBlank(imgList.get(2))){	
					activityInfoDto.setDetailImageThree(imgList.get(2));
				}
			}
			
			activityInfoDto.setStatus(activityInfo.getStatus());
			activityInfoDto.setCreatedBy(activityInfo.getCreatedBy());
			activityInfoDto.setCreatedDate(activityInfo.getCreatedDate());
		}
		return activityInfoDto;
	}

	@Override
	public int updateActivityInfoDto(ActivityInfoDto activityInfoDto) throws RuntimeException {
		try{
			TbActivityInfo activityInfo = this.selectByPrimaryKey(activityInfoDto.getId());
			if(activityInfo!=null){
				String imgList = "";
				activityInfo.setActivityName(activityInfoDto.getActivityName());
				activityInfo.setActivityDetail(activityInfoDto.getActivityDetail());			
				activityInfo.setDisplayBegin(activityInfoDto.getDisplayBegin());
				activityInfo.setDisplayRemaining(activityInfoDto.getDisplayRemaining());
				activityInfo.setStatus(activityInfoDto.getStatus());
				Integer id = activityInfoDto.getId();
				String newName ="";
				List<String> strList =null;
				if(StringUtil.isNotBlank(activityInfo.getImage())){
					strList = Arrays.asList(activityInfo.getImage().split(","));
				}

				String oldName = activityInfoDto.getTitleImage();
				if(StringUtil.isNotBlank(oldName)){
					newName = StringUtil.changeFileName(oldName.split("_")[0],oldName);
					ImageUtil.changActivityImageName(oldName,newName,id);
					activityInfo.setTitleImage(newName);
				}
												
				oldName = activityInfoDto.getDetailImageOne();
				if(StringUtil.isNotBlank(oldName)){				
					newName = StringUtil.changeFileName(oldName.split("_")[0],oldName);
					ImageUtil.changActivityImageName(oldName,newName,id);
					imgList = newName;
				}else{
					if(strList.size()>=1){
						imgList = strList.get(0);
					}
				}
				
				
				oldName = activityInfoDto.getDetailImageTwo();
				if(StringUtil.isNotBlank(oldName)){				
					newName = StringUtil.changeFileName(oldName.split("_")[0],oldName);
					ImageUtil.changActivityImageName(oldName,newName,id);
					imgList = imgList+","+newName;
				}else{
					if(strList.size()>=2){
						imgList =imgList+","+strList.get(1);
					}
				}
				
				oldName = activityInfoDto.getDetailImageThree();
				if(StringUtil.isNotBlank(oldName)){
					newName = StringUtil.changeFileName(oldName.split("_")[0],oldName);
					ImageUtil.changActivityImageName(oldName,newName, activityInfo.getId());
					imgList=imgList+","+newName;
				}else{
					if(strList.size()>=3){
						imgList =imgList+","+strList.get(2);
					}
				}
				activityInfo.setImage(imgList);
				return this.tbActivityInfoMapper.updateByPrimaryKeySelective(activityInfo);
			}
					
			return 0;
		}catch(Exception e){
			throw new RuntimeException(e);
		}	
	}


	public boolean uploadImage(MultipartFile file,String fileName,String floder) throws IOException{
				
		long imageSize = file.getSize();
		
		if(imageSize>5242880){
			return false;
		}
		
		logger.info("文件长度:"+file.getSize());
		logger.info("文件类型:"+file.getContentType());
		logger.info("文件名称:"+file.getName());
		logger.info("文件原始名称:"+file.getOriginalFilename());
		logger.info("=================================");
		String path = HglContants.ACTIVITY_PATH+floder;
		File targetFile = new File(path,fileName);
		if(!targetFile.exists()){
			targetFile.mkdirs();
		}
		
		file.transferTo(targetFile);
		
		return true;
	}

	@Override
	public boolean uploadForm(MultipartFile file, String filename, String path)
			throws Exception {
		String pathDir = path;
		logger.info(path);
		File tmpFile = new File(pathDir);
		if(!tmpFile.exists()){
			tmpFile.mkdirs();
		}
		this.uplodaFile(pathDir, file, filename);
		return false;
	}
	
	private boolean uplodaFile(String destinationDir,MultipartFile file,String filename) throws Exception{
		logger.info("文件长度: " + file.getSize());
        logger.info("文件类型: " + file.getContentType());
        logger.info("文件名称: " + file.getName());
        logger.info("文件原名: " + file.getOriginalFilename());
        logger.info("========================================");
        try {
            SaveFileFromInputStream(file.getInputStream(), destinationDir, filename);
        }
        catch (IOException e) {
            logger.info(e.getMessage());
            return false;
        }
        return true;
	}
	
	 private void SaveFileFromInputStream(InputStream stream, String path, String filename) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(path + "/" + filename);
        int byteCount = 0;
        byte[] bytes = new byte[1024];
        while ((byteCount = stream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, byteCount);
        }
        outputStream.close();
        stream.close();
	 }
	 
}