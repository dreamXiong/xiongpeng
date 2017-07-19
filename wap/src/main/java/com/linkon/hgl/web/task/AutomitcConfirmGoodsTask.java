package com.linkon.hgl.web.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.dto.MyOrderForm;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbAlbumSpace;
import com.liguo.hgl.proxydao.model.TbSystemConfig;
import com.liguo.hgl.proxydao.model.TbWapOrderGroup;
import com.liguo.hgl.service.TbAlbumSpaceService;
import com.liguo.hgl.service.TbOrderGroupService;
import com.liguo.hgl.service.TbSystemConfigService;
import com.liguo.hgl.service.TbWapOrderGroupService;
import com.liguo.hgl.service.TbWapOrderTrackingService;
import com.liguo.hgl.util.DateUtil;
@Component
public class AutomitcConfirmGoodsTask {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private TbWapOrderGroupService tbWapOrderGroupService;
    @Autowired
    private TbOrderGroupService tbOrderGroupService;
    @Autowired
    private TbSystemConfigService tbSystemConfigService;
    @Autowired
    private TbWapOrderTrackingService tbWapOrderTrackingService;
    @Autowired
    private TbAlbumSpaceService tbAlbumSpaceService;
    
    
    
    //清除压缩包重复名称的天数:默认1天
    String clearArchiveTime = "1";
    //收货间隔的天数:默认7天
    double timeInMillis = 7;
    
    //一天的毫秒值
    double oneDayTimeInMillis = 1000*60*60*24;
    
    
    public void  automitcConfirm_wap(){
        logger.info(">>>>>>>>>>>>>>start automitcConfirm_wap ...");
        Criteria parameter = new Criteria();
        //TODO 打印日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        parameter.put("orderStatus", HglContants.WAP_ORDER_STATE_610);
        //通过数据库配置  确认收货间隔的毫秒值
        Criteria criteria = new Criteria();
        criteria.put("configKey", "automitcTime");
        List<TbSystemConfig> tbSystemConfig =tbSystemConfigService.selectByObject(criteria);
        if(!CollectionUtils.isEmpty(tbSystemConfig)){
            timeInMillis = Double.parseDouble(tbSystemConfig.get(0).getConfigValue());
        }
        
        parameter.put("seven_day", oneDayTimeInMillis*timeInMillis);
        
        List<TbWapOrderGroup> tbWapOrderGroup =tbWapOrderGroupService.findOrderGroupByOrderStatus(parameter);
        //订单不为空
        if(!CollectionUtils.isEmpty(tbWapOrderGroup)){
            for(TbWapOrderGroup  tog :tbWapOrderGroup){
                Long time =tog.getUpdateTime();
                //TODO 打印日期
                Calendar c =Calendar.getInstance();
                c.setTimeInMillis(time);
                logger.info("自定确认订单的毫秒值:"+time);
                logger.info("7天后日期:"+sdf.format(c.getTime()));
                long  currTime =Calendar.getInstance().getTimeInMillis();
                c.setTimeInMillis(currTime);
                logger.info("当前毫秒值:"+currTime);
                logger.info("当前日期:"+sdf.format(c.getTime()));
                if(time<currTime){
                    MyOrderForm myOrderForm = new MyOrderForm();
                    myOrderForm.setOrderGroupId(tog.getId());
                    myOrderForm.setVersion(tog.getVersion());
                    tbWapOrderGroupService.configGoodsReceipt(myOrderForm,tog.getBuyerId());
                }
            }
        }
        //没有需要自动确认收货的订单
        else{
            logger.info("find wapOrderGroup  is null ");
        }
        
        
        
        /****************清除次日压缩包名称**********************/
        deleteArchiveName();
    }
    
    /***
     * 
     * <清除次日压缩包名称>
     * @author wzt
     * @since   2016年7月16日
     */
    public void deleteArchiveName(){
        logger.info(">>>>>>>>>>>>>>> deleteArchiveName start...");
        Criteria criteria = new Criteria();
        criteria.put("configKey", "clearArchive");
        List<TbSystemConfig>  tbSystemConfig =tbSystemConfigService.selectByObject(criteria);
        if(!CollectionUtils.isEmpty(tbSystemConfig)){
            clearArchiveTime = tbSystemConfig.get(0).getConfigValue();
        }
        logger.info(">>>>>>>>>>>>>>> clearArchiveTime Value "+clearArchiveTime);
        
        criteria.clear();
        criteria.put("imageTagNameAll", 0);//0为任意值,不为空即可,查询所有不为空的压缩包名称
        List<TbAlbumSpace> tbAlbumSpaceList =tbAlbumSpaceService.selectByObject(criteria);
        if(CollectionUtils.isEmpty(tbAlbumSpaceList)){
          return ;
        }
        int j=0;
        for(int i =0;i<tbAlbumSpaceList.size();i++){
            TbAlbumSpace tbAlbumSpace =tbAlbumSpaceList.get(i);
            Date updateTime =tbAlbumSpace.getUpdateTime();
            updateTime=DateUtil.addDay(updateTime, Integer.valueOf(clearArchiveTime));
            if(updateTime.before(new Date())){
                j++;
                tbAlbumSpace.setImageTagName("");
                //清空压缩包名称
                tbAlbumSpaceService.updateByPrimaryKey(tbAlbumSpace);
            }
        }
        logger.info(">>>>>>>>>>>>>>>>>>update count is"+j);
    }
    
    
}

