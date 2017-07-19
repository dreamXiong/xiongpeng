package com.linkon.admin.action;

import static com.liguo.hgl.proxydao.util.BeanUtil.setSearchCondition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbAdminPermission;
import com.liguo.hgl.proxydao.model.TbAdminRole;
import com.liguo.hgl.proxydao.model.TbAdminUser;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.proxydao.util.StringUtil;
import com.liguo.hgl.service.TbAdminPermissionService;
import com.liguo.hgl.service.TbAdminRoleService;

@Controller
@SessionAttributes({"parentList"})
public class IndexController extends IBaseController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TbAdminRoleService tbAdminRoleService;

    @Autowired
    private TbAdminPermissionService tbAdminPermissionService;

    // childNode属性为0，表示该菜单是父菜单
    private static final String CHILD_NODE = "0";

    @RequestMapping("/")
    public String index(Model model) {

        // 加载菜单
        TbAdminUser tbAdminUser = getLoginUser();
        String userName = tbAdminUser.getUserName();
        List<TbAdminPermission> AdminPermissionList = null;
        // 超级管理员拥有所有权限
        if (HglContants.ADMIN_USER_NAME.equals(userName)) {
            Criteria criteria = new Criteria();
            AdminPermissionList = tbAdminPermissionService.selectByObject(criteria);
        }
        else {
            Integer roleId = tbAdminUser.getRoleId();
            TbAdminRole tbAdminRole = tbAdminRoleService.selectByPrimaryKey(roleId);
            // 用户没分配角色
            if (null == tbAdminRole) {
                return "index";
            }

            String perIds = tbAdminRole.getPermissionIds();

            // 角色没有分配菜单
            if (StringUtil.isBlank(perIds)) {
                return "index";
            }
            String[] perArray = perIds.split(",");
            List<String> perList = Arrays.asList(perArray);
            AdminPermissionList = tbAdminPermissionService.selectByStringList(perList);
        }
        List<TbAdminPermission> parentList = getMenuList(AdminPermissionList);
        model.addAttribute("parentList", parentList);
        return "index";
    }

    private List<TbAdminPermission> getMenuList(List<TbAdminPermission> AdminPermissionList) {
        List<TbAdminPermission> parentList = new ArrayList<TbAdminPermission>();
        Map<String, TbAdminPermission> parentMap = new HashMap<String, TbAdminPermission>();
        int listSize=0;
        TbAdminPermission menu = null;
        listSize = AdminPermissionList.size();

        for (int i = 0; i < listSize; i++) {
            // for(Map<String, Object> tempList :AdminPermissionList){
            menu = AdminPermissionList.get(i);
            String childNode = String.valueOf(menu.getChildNode());
            // 父节点
            if (CHILD_NODE.equals(childNode)) {
                parentList.add(menu);
                parentMap.put(String.valueOf(menu.getId()), menu);
                // 在原来的查询结果上除去父节点
                AdminPermissionList.remove(i--);
                listSize--;
            }
        }
        menu = null;

        // 只剩下子节点
        for (TbAdminPermission map : AdminPermissionList) {
            menu = parentMap.get(StringUtil.trim(String.valueOf(map.getParentNode())));
            if (menu == null) {
                continue;
            }
            menu.addChild(map);
        }
        parentMap.clear();
        AdminPermissionList.clear();
        return parentList;
    }
    
    @RequestMapping("/license")
    public String license(){
    	return "index/license";
    }

    @Override
    protected void init(PageDto page, HttpServletRequest request, HttpServletResponse response, Model model) {

        // TODO Auto-generated method stub

    }

    @Override
    public String doSearchResult() {

        // TODO Auto-generated method stub
        return null;
    }
}
