package com.linkon.admin.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dto.IntegralMallRecordDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbIntegralMallRecord;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.TbIntegralMallRecordService;

@Controller
@RequestMapping("integralMallRecord")
public class IntegralMallRecordController extends IBaseController {
	
	@Autowired
	protected TbIntegralMallRecordService tbIntegralMallRecordService;

	/**
	 * 查询列表
	 */
	@Override
	protected void init(PageDto page, HttpServletRequest request,HttpServletResponse response, Model model) {
		Criteria criteria = new Criteria();
		criteria.put("payStatus", HglContants.integrall_mall_pay_1106);
		List<IntegralMallRecordDto> integralMallRecordList = tbIntegralMallRecordService.selectByObject(criteria,page);
		model.addAttribute("integralMallRecordList", integralMallRecordList);
    	model.addAttribute(HglContants.PAGE_DTO_ID, page);
	}
	
	/**
	 * 分页查询
	 * @param request
	 * @param shopName
	 * @param page
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/searchResult")
    public String searchResult(HttpServletRequest request,String goodsName,PageDto page,Model model) throws IOException {
		//根据商品名称模糊查询
		Criteria criteria = new Criteria();
		criteria.put("goodsName",goodsName);
		criteria.put("payStatus", HglContants.integrall_mall_pay_1106);
		List<IntegralMallRecordDto> integralMallRecordList = tbIntegralMallRecordService.selectByObject(criteria,page);
		model.addAttribute(HglContants.PAGE_DTO_ID, page);
		model.addAttribute("integralMallRecordList", integralMallRecordList);
    	model.addAttribute("goodsName", goodsName);
        return "/integralMallRecord/integralMallRecordList";
    }

    /**
     * 修改状态
     * @param id
     * @return
     */
    @RequestMapping("/modifyStatusIntegralMallRecordId")
    public String modifyStatusIntegralMallRecordId(Integer id) throws Exception{
    	TbIntegralMallRecord integralMallRecordPersistent = tbIntegralMallRecordService.selectByPrimaryKey(id);
    	TbIntegralMallRecord integralMallRecord = new TbIntegralMallRecord();
    	integralMallRecord.setId(id);
    	integralMallRecord.setPlatStatus(HglContants.integrall_mall_plat_1110);
    	integralMallRecord.setVersion(integralMallRecordPersistent.getVersion());
    	int count = tbIntegralMallRecordService.updateByPrimaryKeySelective(integralMallRecord);
		this.logger.info("修改状态受影响行数: " + count);
		return "redirect:/integralMallRecord/index";
    }
    
	@Override
	public String doSearchResult() {
		return null;
	}
	
}