package com.linkon.admin.action;

import static com.liguo.hgl.util.JsonUtil.setJson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbAdminUser;
import com.liguo.hgl.proxydao.model.TbShopInfo;
import com.liguo.hgl.proxydao.model.TbShopUserRef;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.proxydao.util.BeanUtil;
import com.liguo.hgl.service.TbAdminUserService;
import com.liguo.hgl.service.TbShopInfoService;
import com.liguo.hgl.service.TbShopUserRefService;

/***
 * 
 * <店铺管理员管理><br>
 * 
 * @FileName ShopUserRefController.java。<br>
 * @Language java。
 * @date 2016年4月28日
 * @author wzt
 */
@Controller
@RequestMapping("/shopUserRef")
public class ShopUserRefController extends IBaseController {

    @Autowired
    private TbShopUserRefService tbShopUserRefService;

    @Autowired
    private TbShopInfoService tbShopInfoService;

    @Autowired
    private TbAdminUserService tbAdminUserService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String SHOP_USER_REF_INIT = "/shopUserRef/shopUserRefList";

    @Override
    protected void init(PageDto page, HttpServletRequest request, HttpServletResponse response, Model model) {
        // TODO Auto-generated method stub

    }

    @Override
    public String doSearchResult() {
        // TODO Auto-generated method stub
        return null;
    }

    /***
     * 
     * <管理员与店铺列表>
     * 
     * @param sur
     *            店铺关系实体
     * @param model
     *            模型
     * @param page
     *            分页
     * @return
     * @author wzt
     * @since 2016年4月28日
     */
    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(TbShopUserRef sur, Model model, PageDto page) {
        logger.debug("start init...");
        Criteria criteria = new Criteria();
        
        //添加店铺表条件为 工厂
        criteria.put("shopType", HglContants.USER_TYPE_FACTOR);
        //添加查询状态
        criteria.put("authStatus", HglContants.WEB_AUTH_STATUS1);
        List<TbShopInfo> shopInfoList = tbShopInfoService.selectByObject(criteria);
        
        //添加查询用户条件 为店铺管理员
        criteria.put("status", HglContants.USER_STATUS_Valid);
        criteria.put("type", HglContants.USER_TYPE_SHOPUSE);
        List<TbAdminUser> adminUserList = tbAdminUserService.selectByObject(criteria);

        // 显示店铺信息
        model.addAttribute("shopInfoList", shopInfoList);

        // 显示管理员信息
        model.addAttribute("adminUserList", adminUserList);

        return SHOP_USER_REF_INIT;
    }

    /***
     * 
     * <通过管理员查询店铺>
     * @param sur      关系实体
     * @param model     模型
     * @param response  返回
     * @author wzt
     * @since   2016年4月28日
     */
    @RequestMapping(value = "/userToShop_ajax", method = RequestMethod.GET)
    public void userToShop(TbShopUserRef sur, Model model, HttpServletResponse response) {
        logger.debug("start userToShop ...");
        List<Object> jsonList = new ArrayList<Object>();

        if(null!=sur){
            sur.setStatus(HglContants.USER_STATUS_Valid);
        }
        // 查询有关系的店铺
        Criteria cc = new Criteria();
        List<TbShopUserRef> refList = tbShopUserRefService.selectByObject(cc);

        // 查询无关系的店铺
        List<Map<String, Object>> notRefList = tbShopUserRefService.findNotUserList(sur);
        
        jsonList.add(refList);
        jsonList.add(notRefList);
        setJson(jsonList, response);
    }

    /***
     * 
     * <新增或者删除店铺与管理员的关系>
     * @param sur       关系实体
     * @param model     模型
     * @param response  返回
     * @author wzt
     * @since   2016年4月28日
     */
    @RequestMapping(value = "/shopRefAdd_ajax", method = RequestMethod.GET)
    public void shopRefAdd(TbShopUserRef sur, Model model, HttpServletResponse response) {
        logger.debug("start shopRefAdd...");
        // 查询，是否存在，存在则删除，不存在则新增
        Criteria cc = new Criteria();
        List<TbShopUserRef> refList = tbShopUserRefService.selectByObject(cc);
        
        if (CollectionUtils.isEmpty(refList)) {
            logger.debug("shopRefAdd is insert ...");
            tbShopUserRefService.insertSelective(sur);
        }
        else {
            logger.debug("shopRefAdd is delete ...");
            tbShopUserRefService.deleteByPrimaryKey(sur);
        }
    }

}
