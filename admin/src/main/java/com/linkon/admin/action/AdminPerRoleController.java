package com.linkon.admin.action;

import static com.liguo.hgl.proxydao.util.BeanUtil.setSearchCondition;
import static com.liguo.hgl.util.JsonUtil.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
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

import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbAdminPermission;
import com.liguo.hgl.proxydao.model.TbAdminRole;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.proxydao.util.StringUtil;
import com.liguo.hgl.service.TbAdminPermissionService;
import com.liguo.hgl.service.TbAdminRoleService;

/***
 * *
 * 
 * <角色权限管理><br>
 * 
 * @FileName AdminPerRoleController.java。<br>
 * @Language java。
 * @date 2016年5月3日
 * @author wzt
 */
@Controller
@RequestMapping("/adminPerRole")
public class AdminPerRoleController extends IBaseController {

    @Autowired
    private TbAdminPermissionService tbAdminPermissionService;

    @Autowired
    private TbAdminRoleService tbAdminRoleService;


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String ADMIN_PER_ROLE_INIT = "/adminPerRole/adminPerRole";
    private static final String ADMIN_PER_ROLE_LIST = "/adminPerRole/adminPerRoleList";
    
    /**zTree默认选中,展开**/
    private static final String CHECKED = "true";

    @Override
    protected void init(PageDto page, HttpServletRequest request, HttpServletResponse response, Model model) {
        // TODO Auto-generated method stub

    }

    @Override
    public String doSearchResult() {
        // TODO Auto-generated method stub
        return null;
    }

    /****
     * 
     * <角色权限列表>
     * @param tar   角色实体
     * @param model 模型      
     * @param page  分页
     * @return
     * @author wzt
     * @since   2016年5月3日
     */
    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(Model model) {
        logger.debug("start init...");
        //查询角色列表
        Criteria c = new Criteria();
        c.setOrderByClause("id desc");
        List<TbAdminRole> adminRoleList =tbAdminRoleService.selectByObject(c);
        
        //查询权限列表
        Criteria cc = new Criteria();
        List<TbAdminPermission>  adminPermissionList =tbAdminPermissionService.selectByObject(cc);
        
        //取版本号，用于后面的更新
        if(!CollectionUtils.isEmpty(adminRoleList)){
            Integer version =adminRoleList.get(0).getVersion();
            model.addAttribute("version", version);
        }
        model.addAttribute("adminRoleList", adminRoleList);
        model.addAttribute("adminPermissionList", adminPermissionList);
        return ADMIN_PER_ROLE_INIT;
    }
    
    /***
     * 
     * <菜单树显示>
     * @param tar
     * @param response
     * @param model
     * @author wzt
     * @since   2016年7月16日
     */
    @RequestMapping(value = "/roleTreeShow_ajax", method = RequestMethod.GET)
    public void roleTreeShow(TbAdminRole tar, HttpServletResponse response,Model model) {
        logger.debug("roleTreeShow init...");
        
        //查询权限列表,获得所有权限节点
        Criteria criteria = new Criteria();
        List<Map<String, Object>>  adminPermissionList =tbAdminPermissionService.selectByObjectToMap(criteria);
        
        //查询角色列表,获得子节点
        List<TbAdminRole> adminRoleList =tbAdminRoleService.selectByObject(setSearchCondition(model, tar));
        if(CollectionUtils.isEmpty(adminRoleList)){
            setJson(adminPermissionList, response);
        }
        String permissionIds =adminRoleList.get(0).getPermissionIds();
        String[] perArr = {};
       if(StringUtil.isNotBlank(permissionIds)){
           perArr=permissionIds.split(",");
       }
       //子节点数组
       List<String> perIdList =Arrays.asList(perArr);
       for(Map<String, Object> perAll:adminPermissionList ){
           String perId =String.valueOf(perAll.get("id"));
           if(perIdList.contains(perId)){
               perAll.put("checked", CHECKED);
           }
           perAll.put("open", CHECKED);
       }
        //获得版本号，用于后面的更新
        List<Object> objList = new ArrayList<Object>();
        Integer version = adminRoleList.get(0).getVersion();
        objList.add(version);
        objList.add(adminPermissionList);
        setJson(objList, response);
    }
    
    /**
     * 
     * <ajax增加角色菜单>
     * @param tbAdminRole
     * @param response
     * @author wzt
     * @since   2016年7月16日
     */
    @RequestMapping(value = "/addRolePer_ajax", method = RequestMethod.GET)
    public void addRolePer(TbAdminRole tbAdminRole,HttpServletResponse response) {
        logger.debug("addRolePer init...");
        if(null!=tbAdminRole&&null!=tbAdminRole.getId()){
            tbAdminRoleService.updateByPrimaryKeySelective(tbAdminRole);
            //查询版本号，更新前端版本
            tbAdminRole=tbAdminRoleService.selectByPrimaryKey(tbAdminRole.getId());
            Integer version =tbAdminRole.getVersion();
            List<Integer> list =new ArrayList<Integer>();
            list.add(version);
            setJson(list, response);
        }
    }
    
    /***
     * 
     * <ajax增加角色>
     * @param tbAdminRole
     * @param model
     * @param page
     * @return
     * @author wzt
     * @since   2016年7月16日
     */
    @RequestMapping(value = "/addRole_ajax", method = RequestMethod.GET)
    public String addRole(TbAdminRole tbAdminRole,Model model, PageDto page) {
        logger.debug("addRole init...");
        //查询是否重复，如果重复，则不允许插入
        Criteria c = new Criteria();
        c.put("name", tbAdminRole.getName());
        List<TbAdminRole> adminRole =tbAdminRoleService.selectByObject(c);
        tbAdminRoleService.insertSelective(tbAdminRole);
        init(model);
       return  ADMIN_PER_ROLE_LIST;
    }
    /***
     * 
     * <效验角色名称是否重复>
     * @param name
     * @param response
     * @author wzt
     * @since   2016年7月16日
     */
    @RequestMapping(value = "/checkRoleName_ajax", method = RequestMethod.GET)
    public void checkRoleName(String name,HttpServletResponse response) {
        logger.debug("addRole init...");
        //查询是否重复，如果重复，则不允许插入
        Criteria c = new Criteria();
        c.put("name", name);
        List<String> list = new ArrayList<String>();
        List<TbAdminRole> adminRole =tbAdminRoleService.selectByObject(c);
        if(CollectionUtils.isEmpty(adminRole)){
            list.add("success");
        }else{
            list.add("error");
        }
        setJson(list, response);
    }
    
    
}
