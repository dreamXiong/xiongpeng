package com.linkon.hgl.web.action;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.common.MessageEnum;
import com.liguo.hgl.dto.MyOrderForm;
import com.liguo.hgl.exceptions.TransactionException;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbAccount;
import com.liguo.hgl.proxydao.model.TbAccountBank;
import com.liguo.hgl.proxydao.model.TbCashAccount;
import com.liguo.hgl.proxydao.model.TbIntegral;
import com.liguo.hgl.proxydao.model.TbWithdrawals;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.TbAccountBankService;
import com.liguo.hgl.service.TbAccountService;
import com.liguo.hgl.service.TbCashAccountService;
import com.liguo.hgl.service.TbIntegralService;
import com.liguo.hgl.service.TbWithdrawalsService;
/**
 * shop用户操作类
 * CashAccountController.java
 * @2016-6-17	
 * @author 周
 */
@Controller
@RequestMapping("cashAccount")
public class CashAccountController extends IBaseController{
	
	
	@Autowired
	protected TbCashAccountService tbCashAccountService;
	
	@Autowired
	private TbAccountBankService tbAccountBankService;
	
	@Autowired
	private TbWithdrawalsService tbWithdrawalsService;
	
	@Autowired
	private TbAccountService tbAccountService;
	
	@Autowired
	private TbIntegralService integralService;
	
	@Override
	protected void init(PageDto page, HttpServletRequest request,HttpServletResponse response, Model model) {
		MyOrderForm myOrderForm = new MyOrderForm();
		myOrderForm.setEndTime(System.currentTimeMillis()+"");
		Calendar c = Calendar.getInstance();
	    c.add(Calendar.MONTH, -1);
	    myOrderForm.setStartTime(c.getTimeInMillis()+"");
	    myOrderForm.setUserId(getAccountId());
	    List<TbCashAccount> cList = tbCashAccountService.selectCashAccountList(myOrderForm, page);
	    model.addAttribute(HglContants.PAGE_DTO_ID, page);
	    
	    TbAccount tbAccount = tbAccountService.selectByPrimaryKey(getAccountId());
		
		BigDecimal balance = new BigDecimal(tbAccount.getBalance());
    	BigDecimal freeze = new BigDecimal(tbAccount.getFreeze());
    	double money = balance.subtract(freeze).doubleValue();
    	DecimalFormat myformat=new DecimalFormat("0.00");
    	String str = myformat.format(money);   
    	
    	Criteria example = new Criteria();
    	example.put("userId", this.getLoginUserId());
    	List<TbIntegral> tbIntegral = integralService.selectByObject(example);
    	if(tbIntegral.size() == 1){
    		//积分账户
    		model.addAttribute("tbIntegral",tbIntegral.get(0));
    	}
    	model.addAttribute("money",str);
		model.addAttribute("tbCashAccount", tbAccount);
	    model.addAttribute("cList", cList);
	}

	@RequestMapping(value = "/serachCashAccount")
	public String serachCashAccount(MyOrderForm myOrderForm,Model model,PageDto page) throws ParseException{
		 try{
			 myOrderForm.setStartTime(setDate(myOrderForm.getStartTime(),"yyyy-MM-dd").toString());
			 myOrderForm.setEndTime((setDate(myOrderForm.getEndTime(),"yyyy-MM-dd")+86400000)+"");
		 }catch(ParseException e){
			 e.printStackTrace();
		 }
		 if(StringUtils.isBlank(myOrderForm.getSearchText())){
			 myOrderForm.setSearchText(myOrderForm.getSearchText().trim());
		 }
		 myOrderForm.setUserId(getAccountId());
		 List<TbCashAccount> cList = tbCashAccountService.selectCashAccountList(myOrderForm, page);
		 model.addAttribute("cList", cList);
		 model.addAttribute(HglContants.PAGE_DTO_ID, page);
		return "cashAccount/cashAccountList";
	}
	 private Long setDate(String time,String fomart) throws ParseException{
			SimpleDateFormat sdf = new SimpleDateFormat(fomart);
			long millionSeconds = sdf.parse(time).getTime();//毫秒
			return millionSeconds ;
		}
	@Override
	public String doSearchResult() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 提现页面
	 * */
	@RequestMapping(value = "/accountWithdraw")
	public String accountWithdraw(Model model){
		TbAccount tbAccount = tbAccountService.selectByPrimaryKey(getAccountId());
		Criteria example = new Criteria();
		example.put("accountId", getAccountId());
		List<TbCashAccount> cashList = tbCashAccountService.selectByObject(example);
		BigDecimal balance = new BigDecimal(tbAccount.getBalance());
    	BigDecimal freeze = new BigDecimal(tbAccount.getFreeze());
    	double money = balance.subtract(freeze).doubleValue();
    	
    	Criteria e = new Criteria();
		e.put("accountId", getAccountId());
		List<TbAccountBank> tList = tbAccountBankService.selectByObject(e);
    	DecimalFormat myformat=new DecimalFormat("0.00");
    	String str = myformat.format(money);   
    	
    	model.addAttribute("cashList",cashList);
    	model.addAttribute("money",str);
    	model.addAttribute("tList", tList);
		model.addAttribute("tbCashAccount", tbAccount);
		return "cashAccount/accountWithdraw";
	}
	@RequestMapping("/deleteAccountBankValidate")
	@ResponseBody
	public  Map<String,Object> deleteAccountBankValidate(Model model,Integer accountBankId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", HglContants.NO_REPEAT);
		Criteria example = new Criteria();
		example.put("accountbankId", accountBankId);
		example.put("state", 0);
		List<TbWithdrawals> w = tbWithdrawalsService.selectByObject(example);
		if(w.size() > 0){
			map.put("code", HglContants.IS_REPEAT);
		}
		return map;
	}
	
	@RequestMapping("/deleteAccountBank")
	public String deleteAccountBank(Model model,Integer accountBankId){
		tbAccountBankService.deleteByPrimaryKey(accountBankId);
		Criteria e = new Criteria();
		e.put("accountId", getAccountId());
		List<TbAccountBank> tList = tbAccountBankService.selectByObject(e);
		model.addAttribute("tList", tList);
		return "cashAccount/bankAccountList";
	}
	@RequestMapping("/addAccountBank")
	public String saveAccountBank(Model model,String bankAccount,String bank,String name,String branch){
		TbAccountBank tbAccountBank = new TbAccountBank();
		tbAccountBank.setBankAccount(bankAccount);
		tbAccountBank.setAccountId(getAccountId());
		tbAccountBank.setBank(bank);
		tbAccountBank.setBranch(branch);
		tbAccountBank.setName(name);
		tbAccountBank.setVersion(0);
		tbAccountBankService.insertSelective(tbAccountBank);
		Criteria e = new Criteria();
		e.put("accountId", getAccountId());
		List<TbAccountBank> tList = tbAccountBankService.selectByObject(e);
		model.addAttribute("tList", tList);
		return "cashAccount/bankAccountList";
	}
	
	@RequestMapping("/getMoney")
	public String withdrawalsSave(Model model,Integer accountBankId,Double money) throws TransactionException{
		TbAccountBank tbAccountBank = tbAccountBankService.selectByPrimaryKey(accountBankId);
		if(tbAccountBank == null){
			logger.debug("该账户不存在！！");
			throw new TransactionException(MessageEnum.E9000);
		}
		
		tbAccountService.withdrawalsSave(getAccountId(), accountBankId, money, getLoginUserId(),null);
		return "redirect:/cashAccount/withdrawalsSuccess";
	}
	
	@RequestMapping("/withdrawalsSuccess")
	public String withdrawalsSuccess(Model model,Integer accountId,String bankAccount,String bank,String branch,Double money) throws TransactionException{
		return "cashAccount/success";
	}
	
	@RequestMapping("/addValidateAccount")
	@ResponseBody
	public  Map<String,Object> addValidateAccount(Model model,String bankAccount){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", HglContants.NO_REPEAT);
		Criteria e = new Criteria();
		e.put("bankAccount", bankAccount);
		List<TbAccountBank> tList = tbAccountBankService.selectByObject(e);
		if(tList.size() > 0){
			map.put("code", HglContants.IS_REPEAT);
		}
		return map;
	}
}
