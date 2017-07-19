package com.liguo.hgl.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dao.TbUserInfoMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbUserInfo;
import com.liguo.hgl.service.TbUserInfoService;

@Service
@Scope(value="prototype")
public class TbUserInfoServiceImpl implements TbUserInfoService {
    @Autowired
    private TbUserInfoMapper tbUserInfoMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbUserInfoServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbUserInfoMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbUserInfo selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbUserInfoMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbUserInfo> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbUserInfoMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbUserInfoMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbUserInfo record) throws RuntimeException {
        try {
            return this.tbUserInfoMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbUserInfo record) throws RuntimeException {
        try {
            return this.tbUserInfoMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbUserInfo record) throws RuntimeException {
        try {
            return this.tbUserInfoMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

	@Override
	public int insert(TbUserInfo record) throws RuntimeException {
		
		return this.tbUserInfoMapper.insert(record);
	}
	
	@Override
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
		String path = HglContants.USER_PATH+floder;
		File targetFile = new File(path,fileName);
		if(!targetFile.exists()){
			targetFile.mkdirs();
		}
		
		file.transferTo(targetFile);
		
		return true;
	}
	
}