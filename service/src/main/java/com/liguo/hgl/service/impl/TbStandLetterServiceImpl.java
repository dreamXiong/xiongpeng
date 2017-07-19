package com.liguo.hgl.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dao.TbStandLetterMapper;
import com.liguo.hgl.proxydao.dao.TbWebUserMapper;
import com.liguo.hgl.proxydao.dto.LetterActivityDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbStandLetter;
import com.liguo.hgl.proxydao.model.TbWebUser;
import com.liguo.hgl.proxydao.util.DateUtil;
import com.liguo.hgl.service.TbStandLetterService;

@Service
@Scope(value="prototype")
public class TbStandLetterServiceImpl implements TbStandLetterService {
    @Autowired
    private TbStandLetterMapper tbStandLetterMapper;
    
    @Autowired
    private TbWebUserMapper tbWebUserMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbStandLetterServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbStandLetterMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbStandLetter selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbStandLetterMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbStandLetter> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbStandLetterMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbStandLetterMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbStandLetter record) throws RuntimeException {
        try {
            return this.tbStandLetterMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbStandLetter record) throws RuntimeException {
        try {
            return this.tbStandLetterMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbStandLetter record) throws RuntimeException {
        try {
            return this.tbStandLetterMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

	@Override
	public int addLetter(Integer loginUserId, Map<String, Object> param) {
		
		List<TbStandLetter> letters = new ArrayList<TbStandLetter>();
		TbWebUser tbWebUser = tbWebUserMapper.selectByPrimaryKey(loginUserId);
		String userListIds = param.get("userListIds").toString();
		int type=Integer.parseInt(param.get("type").toString());
		int count =0;
		if(StringUtils.isNotBlank(userListIds)){
			//发送人
			TbStandLetter standLetter = insertSendLetter(param, tbWebUser,
					userListIds, type);
			
			letters.add(standLetter);
		
		
			//接受人
			String[] userArray = userListIds.split(",");
		    for (int i = 0; i < userArray.length; i++) {
		    	TbStandLetter tbStandLetter = new TbStandLetter();
		    	tbStandLetter.setUserId(Integer.parseInt(userArray[i]));
		    	tbStandLetter.setLetterType(HglContants.LETTER_IS_ACCEPT);
		    	tbStandLetter.setRecipientid(tbWebUser.getId().toString());
		    	tbStandLetter.setRecipientname(tbWebUser.getName());
		    	tbStandLetter.setType(type);
				if(HglContants.STAND_LETTER_CONTENT.equals(type)){
					tbStandLetter.setContent(param.get("content").toString());
					tbStandLetter.setActivityId(0);
				}else if(HglContants.STAND_LETTER_ACTIVITY.equals(type)){
					tbStandLetter.setContent(null);
					tbStandLetter.setActivityId(Integer.parseInt(param.get("activityId").toString()));
				}
				tbStandLetter.setShopid(tbWebUser.getShopId());
				tbStandLetter.setReserved(HglContants.LETTER_DEFAULT);//
				tbStandLetter.setCreateDt(System.currentTimeMillis());
				letters.add(tbStandLetter);
				
		    }
		    count = tbStandLetterMapper.insertList(letters);//批量插入
		    logger.debug("----发送用户的数量"+count);
		}		
	
		return count;
	}

	private TbStandLetter insertSendLetter(Map<String, Object> param,
			TbWebUser tbWebUser, String userListIds, int type) {
		TbStandLetter standLetter = new TbStandLetter();
		standLetter.setUserId(tbWebUser.getId());//我的Id
		standLetter.setLetterType(HglContants.LETTER_IS_SEND);
		standLetter.setRecipientid(userListIds);
		standLetter.setRecipientname(param.get("userListNames").toString());
		standLetter.setShopid(tbWebUser.getShopId());
		standLetter.setType(type);
		if(HglContants.STAND_LETTER_CONTENT.equals(type)){
			standLetter.setContent(param.get("content").toString());
			standLetter.setActivityId(0);
		}else if(HglContants.STAND_LETTER_ACTIVITY.equals(type)){
			standLetter.setContent(null);
			standLetter.setActivityId(Integer.parseInt(param.get("activityId").toString()));
		}
		standLetter.setReserved(HglContants.LETTER_IS_READ);//已读
		standLetter.setCreateDt(System.currentTimeMillis());
		return standLetter;
	}

	@Override
	public List<LetterActivityDto> selectLetters(Integer userId) {
		 Criteria criteria = new Criteria();
		 criteria.put("userId", userId);
		 int count = selectLetterCount(userId);
		 if(count<HglContants.STAND_LETTER_NUMBER){
			 criteria.put("numbers", HglContants.STAND_LETTER_NUMBER);
		 }else{
			 criteria.put("numbers", count);
		 }
		 readLetter(null,userId);
		return tbStandLetterMapper.selectLetters(criteria);
	}

	@Override
	public List<LetterActivityDto> getLetterByShopId(Integer uId, Integer num,
			Integer loginUserId) {
		TbWebUser tbWebUser = tbWebUserMapper.selectByPrimaryKey(uId);
		if(tbWebUser!=null){
			Criteria criteria = new Criteria();
			//criteria.put("sendShopUserId", tbWebUser.getId());
			criteria.put("oneRecipientId", tbWebUser.getId());
			criteria.put("userId", loginUserId);
			if(num !=null && num> HglContants.STAND_LETTER_NUMBER){
				criteria.put("numbers",num);
			}else{
				 criteria.put("numbers", HglContants.STAND_LETTER_NUMBER);
			}
			 readLetter(uId,loginUserId);
			return tbStandLetterMapper.selectLetters(criteria);
		}
		return null;
	}
	/**
	 * 标识已读 
	 * @param uId
	 * @param loginUserId
	 * @return
	 * @author zss
	 */
	private int readLetter(Integer uId, Integer loginUserId) {
		Criteria criteria = new Criteria();
		criteria.put("otherUserId", uId);
		criteria.put("myUserId", loginUserId);
		criteria.put("reservedState", HglContants.LETTER_DEFAULT);//未读
		criteria.put("reservedStateRead", HglContants.LETTER_IS_READ);
		int count=tbStandLetterMapper.updateReservedState(criteria);
		logger.debug("----修改已读信数量"+count);
		return count;
	}

	@Override
	public Map<String, Object> addLetterByUser(Map<String, Object> param,
			Integer loginUserId) {
		 Map<String,Object> map = new HashMap<String, Object>();
		TbWebUser tbWebUser = tbWebUserMapper.selectByPrimaryKey(loginUserId);
		String userListIds = param.get("userListIds").toString();
		int type=Integer.parseInt(param.get("type").toString());
		int count =0;
		if(StringUtils.isNotBlank(userListIds)){
			//发送人
		
			TbStandLetter standLetter = insertSendLetter(param, tbWebUser,
					userListIds, type);
			
			count =tbStandLetterMapper.insert(standLetter);
		
		
			//接受人
			
	    	TbStandLetter tbStandLetter = new TbStandLetter();
	    	tbStandLetter.setUserId(Integer.parseInt(userListIds));
	    	tbStandLetter.setLetterType(HglContants.LETTER_IS_ACCEPT);
	    	tbStandLetter.setRecipientid(tbWebUser.getId().toString());
	    	tbStandLetter.setRecipientname(tbWebUser.getName());
	    	tbStandLetter.setType(type);
			if(HglContants.STAND_LETTER_CONTENT.equals(type)){
				tbStandLetter.setContent(param.get("content").toString());
				tbStandLetter.setActivityId(0);
			}else if(HglContants.STAND_LETTER_ACTIVITY.equals(type)){
				tbStandLetter.setContent(null);
				tbStandLetter.setActivityId(Integer.parseInt(param.get("activityId").toString()));
			}
			tbStandLetter.setShopid(tbWebUser.getShopId());
			tbStandLetter.setReserved(HglContants.LETTER_DEFAULT);//
			tbStandLetter.setCreateDt(System.currentTimeMillis());
			count =tbStandLetterMapper.insert(tbStandLetter);
			if(count>0){
				map.put("errcode", 0);
				TbStandLetter letter= tbStandLetterMapper.selectByPrimaryKey(standLetter.getId());
				letter.setReservedstr(DateUtil.parseLong(letter.getCreateDt(), "yyyy-MM-dd  HH:mm:ss"));
				map.put("sendLetter", letter);
			}
		}else if(count == 0){
			map.put("errcode", 1);
			map.put("msg", "发送失败");
		}	
		
		return map;
	}

	@Override
	public int selectNewLetter(Integer uId, Integer loginUserId) {
		Criteria criteria = new Criteria();
		criteria.put("userId", loginUserId);
		criteria.put("recipientid", uId);
		criteria.put("reserved", HglContants.LETTER_DEFAULT);
		List<TbStandLetter> letters = tbStandLetterMapper.selectByObject(criteria);
		if(letters!=null){
			return letters.size();
		}
		return 0;
	}

	@Override
	public int selectLetterCount(Integer loginUserId) {
		Criteria criteria = new Criteria();
		criteria.put("userId", loginUserId);
		criteria.put("reserved", HglContants.LETTER_DEFAULT);
		List<TbStandLetter> letters=tbStandLetterMapper.selectByObject(criteria);
		if(letters!=null){
			return letters.size();
		}
		return 0;
	}
}