package com.linkon.hgl.web.action;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.common.MessageEnum;
import com.liguo.hgl.exceptions.TransactionException;
import com.liguo.hgl.proxydao.dto.CashAccountDto;
import com.liguo.hgl.proxydao.dto.IntegralDetailedDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbAccount;
import com.liguo.hgl.proxydao.model.TbAccountBank;
import com.liguo.hgl.proxydao.model.TbIntegral;
import com.liguo.hgl.proxydao.model.TbWithdrawals;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.TbAccountBankService;
import com.liguo.hgl.service.TbAccountService;
import com.liguo.hgl.service.TbCashAccountService;
import com.liguo.hgl.service.TbFreezingService;
import com.liguo.hgl.service.TbIntegralDetailedService;
import com.liguo.hgl.service.TbIntegralService;
import com.liguo.hgl.service.TbWithdrawalsService;
import com.liguo.hgl.util.StringUtil;
/**
 * 终端客户首页
 * */
@RequestMapping("/myWallet")
@Controller
public class MyWalletController extends IBaseController {
	
	@Autowired
	private TbAccountService tbAccountService;
	
	@Autowired
	private TbIntegralService tbIntegralService;
	
	@Autowired
	private TbCashAccountService tbCashAccountService;
	
	@Autowired
	private TbIntegralDetailedService tbIntegralDetailedService;
	
	@Autowired
	private TbFreezingService tbFreezingService;
	
	@Autowired
	private TbAccountBankService tbAccountBankService;
	
	@Autowired
	private TbWithdrawalsService tbWithdrawalsService;
	
	@Override
	protected void init(PageDto page, HttpServletRequest request,HttpServletResponse response, Model model) {
		TbAccount tbAccount = tbAccountService.selectByPrimaryKey(getAccountId());
		Criteria example = new Criteria();
		example.put("accountId", getAccountId());
		page.pageSize = 12;
		List<CashAccountDto> cashList = tbCashAccountService.selectCashAccount(example,page,0);
		BigDecimal balance = new BigDecimal(tbAccount.getBalance());
    	BigDecimal freeze = new BigDecimal(tbAccount.getFreeze());
    	double money = balance.subtract(freeze).doubleValue();
    	DecimalFormat myformat=new DecimalFormat("0.00");
    	String str = myformat.format(money);   
    	model.addAttribute("cashList",cashList);
    	model.addAttribute("money",str);
		model.addAttribute("tbCashAccount", tbAccount);
		model.addAttribute("pageCount",StringUtil.resetPage(tbCashAccountService.countByObject(example), page.pageSize));
	}
	
	@RequestMapping(value = "/tbAccountPage")
    public String tbAccountPage(Model model,Integer pageIndex,PageDto page,Integer tbIntegralId){
    	Criteria c = new Criteria();
    	c.put("accountId", getAccountId());
		page.pageSize = 12;
		List<CashAccountDto> cashList = tbCashAccountService.selectCashAccount(c,page,pageIndex);
		model.addAttribute("cashList",cashList);
    	return "myWallet/myWalletList";
    }

	@Override
	public String doSearchResult() {
		// TODO Auto-generated method stub
		return null;
	}
	@RequestMapping("/integral")
	public String integral(Model model,PageDto page){
		Criteria criteria = new Criteria();
		criteria.put("userId", getLoginUserId());
		List<TbIntegral> tbIntegralList = tbIntegralService.selectByObject(criteria);
		TbIntegral tbIntegral = new TbIntegral();
		if(tbIntegralList.size() > 0){
			tbIntegral = tbIntegralList.get(0);
			Criteria c = new Criteria();
			c.put("integralId", tbIntegral.getId());
			page.pageSize = 12;
			Integer recordCount = tbIntegralDetailedService.countByObject(c);
			int pageCount = (recordCount-1)/page.pageSize+1;
			List<IntegralDetailedDto> tbIntegralDetailed = tbIntegralDetailedService.selectIntegralDetailedPage(c,page,0);
			model.addAttribute("tbIntegralDetailed", tbIntegralDetailed);
			model.addAttribute("pageCount", pageCount);
		}
		model.addAttribute("tbIntegral", tbIntegral);
		return "myWallet/integral";
	}
	/**
	 * 账户资金提现
	 * */
	@RequestMapping("/withdrawals")
	public String withdrawals(Model model,Integer accountId){
		
		//如果该用户的店铺ID与绑定店铺ID一致则说明该用户是店铺登陆账户，在手机端不具有提现功能。
		if(getShopId() != null){
			if(getRecommendShopId().toString().equals(getShopId().toString())){
				return null;
			}
		}
		TbAccount tbAccount = tbAccountService.selectByPrimaryKey(accountId);
		BigDecimal balance = new BigDecimal(tbAccount.getBalance());
    	BigDecimal freeze = new BigDecimal(tbAccount.getFreeze());
    	double money = balance.subtract(freeze).doubleValue();
		model.addAttribute("tbAccount", tbAccount);
		Criteria e = new Criteria();
		e.put("accountId", accountId);
		List<TbAccountBank> tList = tbAccountBankService.selectByObject(e);
		DecimalFormat myformat=new DecimalFormat("0.00");
    	String str = myformat.format(money);   
    	
    	model.addAttribute("tList", tList);
		model.addAttribute("money", str);
		return "myWallet/withdrawals";
	}
	
	@RequestMapping("/withdrawalsSave")
	public String withdrawalsSave(Model model,Integer accountId,Integer accountBankId,Double money) throws TransactionException{
		TbAccountBank tbAccountBank = tbAccountBankService.selectByPrimaryKey(accountBankId);
		if(tbAccountBank == null){
			logger.debug("该账户不存在！！");
			throw new TransactionException(MessageEnum.E9000);
		}
		if(getAccountId().toString().equals(accountId.toString())){
			tbAccountService.withdrawalsSave(accountId, accountBankId, money, getLoginUserId(),getRecommendShopId());
		}
		return "redirect:/myWallet/withdrawalsSuccess";
	}
	
	@RequestMapping("/withdrawalsSuccess")
	public String withdrawalsSuccess(Model model) throws TransactionException{
		model.addAttribute("accountId", getAccountId());
		return "myWallet/success";
	}
	
	@RequestMapping("/accountBank")
	public String accountBank(Model model) throws TransactionException{
		Criteria example = new Criteria();
		example.put("accountId",getAccountId() );
		List<TbAccountBank> aList= tbAccountBankService.selectByObject(example);
		model.addAttribute("aList", aList);
		model.addAttribute("accountId", getAccountId());
		return "myWallet/accountBank";
	}
	
	@RequestMapping("/saveAccountBank")
	public String saveAccountBank(Model model,Integer accountId,String bankAccount,String bank,String name,String branch) throws TransactionException{
		TbAccountBank tbAccountBank = new TbAccountBank();
		tbAccountBank.setBankAccount(bankAccount);
		tbAccountBank.setAccountId(accountId);
		tbAccountBank.setBank(bank);
		tbAccountBank.setBranch(branch);
		tbAccountBank.setName(name);
		tbAccountBank.setVersion(0);
		tbAccountBankService.insertSelective(tbAccountBank);
		return "redirect:/myWallet/withdrawalsSuccess";
	}
	@RequestMapping("/deleteAccountBank")
	public String deleteAccountBank(Model model,Integer accountBankId) throws TransactionException{
		Criteria example = new Criteria();
		example.put("accountbankId", accountBankId);
		example.put("state", 0);
		List<TbWithdrawals> w = tbWithdrawalsService.selectByObject(example);
		if(w.size() > 0){
			logger.debug("该账户存在提现记录");
        	throw new TransactionException(MessageEnum.E9000);
		}
		tbAccountBankService.deleteByPrimaryKey(accountBankId);
		return "redirect:/myWallet/withdrawalsSuccess";
	}
	
	@RequestMapping("/addValidateAccount")
	@ResponseBody
	public Map<String,Object> addValidateAccount(Model model,String bankAccount){
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", HglContants.NO_REPEAT);
		Criteria e = new Criteria();
		e.put("bankAccount", bankAccount.trim());
		List<TbAccountBank> tList = tbAccountBankService.selectByObject(e);
		if(tList.size() > 0){
			map.put("code", HglContants.IS_REPEAT);
		}
		return map;
	}
	
	/**
	 * 
	 * 微信充值页面
	 * 
	 * */
	@RequestMapping(value = "/rechargePage")
	public String recharge(Model model){
		//判断该用户绑定店铺是否开通了平台结算功能
		model.addAttribute("accountId",getAccountId());
		return "myWallet/rechargePage";
	}
	
	@RequestMapping(value = "/integralDetailedPage")
    public String IntegralDetailedPage(Model model,Integer pageIndex,PageDto page,Integer tbIntegralId){
    	Criteria c = new Criteria();
    	c.put("integralId", tbIntegralId);
		page.pageSize = 12;
    	List<IntegralDetailedDto> tbIntegralDetailed = tbIntegralDetailedService.selectIntegralDetailedPage(c,page,pageIndex);
    	model.addAttribute("tbIntegralDetailed", tbIntegralDetailed);
    	return "myWallet/integralList";
    }
}
