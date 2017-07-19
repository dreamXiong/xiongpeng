package com.linkon.admin.action;

import static com.liguo.hgl.proxydao.util.BeanUtil.setSearchCondition;
import static com.liguo.hgl.util.JsonUtil.setJson;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.liguo.hgl.proxydao.model.TbAdminRole;
import com.liguo.hgl.proxydao.model.TbWebPermission;
import com.liguo.hgl.proxydao.model.TbWebRole;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.proxydao.util.StringUtil;
import com.liguo.hgl.service.TbWebPermissionService;
import com.liguo.hgl.service.TbWebRoleService;

/****
 * *
 * 
 * <角色管理前台><br>
 * 
 * @FileName WebPerRoleController.java。<br>
 * @Language java。
 * @date 2016年5月6日
 * @author wzt
 */
@Controller
@RequestMapping("/webPerRole")
public class WebPerRoleController extends IBaseController {

    @Autowired
    private TbWebPermissionService tbWebPermissionService;

    @Autowired
    private TbWebRoleService tbWebRoleService;


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String ADMIN_PER_ROLE_INIT = "/webPerRole/webPerRole";
    private static final String ADMIN_PER_ROLE_LIST = "/webPerRole/webPerRoleList";
    
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
        Criteria c = new Criteria();
        c.setOrderByClause("id desc");
        
        //查询角色列表
        List<TbWebRole> webRoleList =tbWebRoleService.selectByObject(c);
        
        //查询权限列表
        Criteria cc = new Criteria();
        List<TbWebPermission>  webPermissionList =tbWebPermissionService.selectByObject(cc);
        
        //取版本号，用于后面的更新
        if(!CollectionUtils.isEmpty(webRoleList)){
            Integer version =webRoleList.get(0).getVersion();
            model.addAttribute("version", version);
        }
        model.addAttribute("webRoleList", webRoleList);
        model.addAttribute("webPermissionList", webPermissionList);
        return ADMIN_PER_ROLE_INIT;
    }
    
    /****
     * 
     * <权限树显示>
     * @param tar
     * @param response
     * @param model
     * @author wzt
     * @since   2016年7月16日
     */
    @RequestMapping(value = "/roleTreeShow_ajax", method = RequestMethod.GET)
    public void roleTreeShow(TbWebRole tar, HttpServletResponse response,Model model) {
        logger.debug("roleTreeShow init...");
        
        Criteria criteria = new Criteria();
        criteria.put("status", 1);
        //查询权限列表,获得所有权限节点
        List<Map<String, Object>>  webPermissionList =tbWebPermissionService.selectByObjectToMap(criteria);
        
        //查询角色列表,获得子节点
        Criteria cc = new Criteria();
        List<TbWebRole> webRoleList =tbWebRoleService.selectByObject(cc);
        if(CollectionUtils.isEmpty(webRoleList)){
            setJson(webPermissionList, response);
        }
        String permissionIds =webRoleList.get(0).getPermissionIds();
        String[] perArr = {};
       if(StringUtil.isNotBlank(permissionIds)){
           perArr=permissionIds.split(",");
       }
       //子节点数组
       List<String> perIdList =Arrays.asList(perArr);
       for(Map<String, Object> perAll:webPermissionList ){
           String perId =String.valueOf(perAll.get("id"));
           if(perIdList.contains(perId)){
               perAll.put("checked", CHECKED);
           }
           perAll.put("open", CHECKED);
       }
        //获得版本号，用于后面的更新
        List<Object> objList = new ArrayList<Object>();
        Integer version = webRoleList.get(0).getVersion();
        objList.add(version);
        objList.add(webPermissionList);
        setJson(objList, response);
    }
    
    /***
     * 
     * <添加角色菜单列表>
     * @param tbWebRole
     * @param response
     * @author wzt
     * @since   2016年7月16日
     */
    @RequestMapping(value = "/addRolePer_ajax", method = RequestMethod.GET)
    public void addRolePer(TbWebRole tbWebRole,HttpServletResponse response) {
        logger.debug("addRolePer init...");
        if(null!=tbWebRole&&null!=tbWebRole.getId()){
            tbWebRoleService.updateByPrimaryKeySelective(tbWebRole);
            //查询版本号，更新前端版本
            tbWebRole=tbWebRoleService.selectByPrimaryKey(tbWebRole.getId());
            Integer version =tbWebRole.getVersion();
            List<Integer> list =new ArrayList<Integer>();
            list.add(version);
            setJson(list, response);
        }
    }
    
    /***
     * 
     * <添加角色>
     * @param tbWebRole
     * @param model
     * @param page
     * @return
     * @author wzt
     * @since   2016年7月16日
     */
    @RequestMapping(value = "/addRole_ajax", method = RequestMethod.GET)
    public String addRole(TbWebRole tbWebRole,Model model, PageDto page) {
        logger.debug("addRole init...");
        tbWebRoleService.insertSelective(tbWebRole);
        init(model);
       return  ADMIN_PER_ROLE_LIST;
    }
    
    /***
     * 
     * <角色名称效验>
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
        List<TbWebRole> adminRole =tbWebRoleService.selectByObject(c);
        if(CollectionUtils.isEmpty(adminRole)){
            list.add("success");
        }else{
            list.add("error");
        }
        setJson(list, response);
    }
}
