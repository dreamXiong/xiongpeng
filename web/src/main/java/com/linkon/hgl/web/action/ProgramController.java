/*
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */
package com.linkon.hgl.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liguo.hgl.ceche.ProgramCeche;
import com.liguo.hgl.common.ResultDto;
import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dto.TbProgramInfoDto;
import com.liguo.hgl.proxydao.model.TbProgramInfo;
import com.liguo.hgl.proxydao.model.TbProgramPraise;
import com.liguo.hgl.proxydao.util.StringUtil;
import com.liguo.hgl.service.IManagerService;
import com.liguo.hgl.service.IProgramService;

/**
 * 界面列表<br>
 * 
 * @filename ProgramController.java<br>
 * @author 张勇<br>
 * @date 2016-1-8<br>
 * @Language java<br>
 */
@Controller
@RequestMapping("vote")
public class ProgramController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String PROGRAM_DATA_LIST = "/program/dataList";
    private static final String PROGRAM_PRAISE_LIST = "/program/praiseList";
    private static final String PROGRAM_SORT_LIST = "/program/sortList";
    private static final String PROGRAM_SORT_DATA = "/program/sortData";

    private static final String PROGRAM_AUTH_LIST = "/program/authList";

    @Autowired
    private IProgramService programService;
    @Autowired
    private IManagerService managerService;

    @RequestMapping(value = "")
    public String index(HttpServletRequest request) {

        String code = request.getParameter("code");
        logger.info("授权code码：" + code);
        if (StringUtil.isBlank(code)) {
            return "redirect:" + HglContants.WEIXIN_PRAISE_URL;
        }
        request.getSession().setAttribute("code", code);
        return "redirect:/vote/praiseList";
    }

    /**
     * 节目列表显示
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/dataList")
    public String datalist(Model model, HttpServletRequest request) {

        List<TbProgramInfoDto> list = programService.queryProgramList(null, true);
        model.addAttribute("programList", list);
        return PROGRAM_DATA_LIST;
    }

    /**
     * 投票节目列表
     * 
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/praiseList")
    public String praiseList(Model model, HttpServletRequest request) {

        String code = getCodeBySession(request);
        if (code == null) {
            return "redirect:" + HglContants.WEIXIN_PRAISE_URL;
        }
        String openId = getOpenIdBySession(request);
        if (openId == null) {
            try {
                openId = programService.queryUserOpenId(code.toString());
                boolean flag = programService.isSubscribe(openId);
                logger.info("用户是否未关注了公众号：" + flag);
                if (flag) {
                    return "redirect:" + HglContants.CSKNXH_WEIXIN_URL;
                }
                request.getSession().setAttribute("openId", openId);
            } catch (Exception e) {
                logger.info("请求用户OpenId", e);
            }
        }
        logger.info("openId：" + openId);
        List<TbProgramInfoDto> list = programService.queryProgramList(openId, false);
        model.addAttribute("programList", list);
        return PROGRAM_PRAISE_LIST;
    }

    /**
     * 投票排行
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/sortList")
    public String sortList(Model model) {

        model.addAttribute("programList", ProgramCeche.getProgramSortList());
        model.addAttribute("isShow", managerService.isShowPraiseNum());
        return PROGRAM_SORT_LIST;
    }

    /**
     * 投票排行
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/sortDataPage")
    public String sortDataPage(Model model) {

        model.addAttribute("programList", ProgramCeche.getProgramSortList());
        model.addAttribute("isShow", managerService.isShowPraiseNum());
        return PROGRAM_SORT_DATA;
    }

    /**
     * 投票保存
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/praiseSave")
    @ResponseBody
    public ResultDto praiseSave(Model model, TbProgramPraise praise, HttpServletRequest request) {

        String openId = getOpenIdBySession(request);
        logger.info("openId：" + openId);
        if (openId != null) {
            praise.setOpenId(openId.toString());
        } else {
            return new ResultDto(100, "授权已期，请重新进入");
        }
        return programService.savePraise(praise);
    }

    private String getOpenIdBySession(HttpServletRequest request) {

        Object obj = request.getSession().getAttribute("openId");
        return obj==null?request.getParameter("openId"):obj.toString();
    }

    private String getCodeBySession(HttpServletRequest request) {

        Object code = request.getSession().getAttribute("code");
        return code == null ? request.getParameter("code") : code.toString();
    }

    /**
     * 节目列表显示
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/authList")
    public String authlist(Model model, HttpServletRequest request) {

        List<TbProgramInfoDto> list = programService.queryProgramList(null, true);
        model.addAttribute("programList", list);
        return PROGRAM_AUTH_LIST;
    }

    @RequestMapping(value = "/authOpt")
    @ResponseBody
    public Map<String, Object> refuseOpt(String id) {

        Map<String, Object> map = new HashMap<String, Object>();

        try {
            TbProgramInfo data = programService.queryProgramById(Integer.parseInt(id));
            if (null != data) {

                data.setPraiseFlag(1);
                //修改缓存数据
                ProgramCeche.changPraiseFlag(data.getId(), data.getPraiseFlag());
                programService.updateProgramPraiseFlagById(data);
                map.put("code", "0000");
            } else {
                map.put("code", "0001");
            }
        } catch (Exception e) {
            logger.error("审核出错：" + e.getMessage());
            map.put("code", "-1");
            e.printStackTrace();
        }

        return map;
    }

}
