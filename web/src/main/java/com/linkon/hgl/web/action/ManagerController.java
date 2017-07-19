/*
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */
package com.linkon.hgl.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liguo.hgl.ceche.ProgramCeche;
import com.liguo.hgl.common.ResultDto;
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
@RequestMapping("manager")
public class ManagerController {
    private static final String PROGRAM_MANAGER = "/manager/manager";

    @Autowired
    private IProgramService programService;

    @Autowired
    private IManagerService managerService;

    @RequestMapping(value = "/main")
    public String index(Model model) {

        model.addAttribute("isStartPraise", managerService.isStartPraise());
        model.addAttribute("isEndPraise", managerService.isEndPraise());
        model.addAttribute("isShowPraiseNum", managerService.isShowPraiseNum());
        model.addAttribute("isOpenSign", managerService.isOpenSign());
        model.addAttribute("isOpenInteract", managerService.isOpenInteract());
        model.addAttribute("isOpenErnie", managerService.isOpenErnie());
        model.addAttribute("index", ProgramCeche.getIndex());
        model.addAttribute("size", ProgramCeche.getSize());
        return PROGRAM_MANAGER;
    }

    @RequestMapping(value = "/changeIndex")
    @ResponseBody
    public ResultDto changeIndex(HttpServletRequest request) {

        String index = request.getParameter("index");
        if (StringUtil.isNotBlank(index)) {
            ProgramCeche.setIndex(Integer.parseInt(index));
        }
        return new ResultDto(0, "修改成功");
    }

    @RequestMapping(value = "/changeSize")
    @ResponseBody
    public ResultDto changeSize(HttpServletRequest request) {

        String size = request.getParameter("size");
        if (StringUtil.isNotBlank(size)) {
            ProgramCeche.setSize(Integer.parseInt(size));
        }
        return new ResultDto(0, "修改成功");
    }

    /**
     * 开启签到
     * 
     * @param response
     */
    @RequestMapping(value = "/openSign")
    @ResponseBody
    public ResultDto openSign(HttpServletResponse response) {

        managerService.openSign();
        return new ResultDto(0, "开启签到成功");
    }

    /**
     * 关闭签到
     * 
     * @param response
     */
    @RequestMapping(value = "/closeSign")
    @ResponseBody
    public ResultDto closeSign(HttpServletResponse response) {

        managerService.closeSign();
        return new ResultDto(0, "关闭签到成功");
    }

    /**
     * 开启弹幕
     * 
     * @param response
     */
    @RequestMapping(value = "/openInteract")
    @ResponseBody
    public ResultDto openInteract(HttpServletResponse response) {

        managerService.openInteract();
        return new ResultDto(0, "开启弹幕成功");
    }

    /**
     * 关闭弹幕
     * 
     * @param response
     */
    @RequestMapping(value = "/closeInteract")
    @ResponseBody
    public ResultDto closeInteract(HttpServletResponse response) {

        managerService.closeInteract();
        return new ResultDto(0, "关闭弹幕成功");
    }

    /**
     * 投票开始
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/startPraise")
    @ResponseBody
    public ResultDto startPraise(HttpServletResponse response) {

        managerService.startPraise();
        return new ResultDto(0, "投票开始....");
    }

    /**
     * 投票排行隐藏投票统计
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/hidePraise")
    @ResponseBody
    public ResultDto hidePraise(HttpServletResponse response) {

        managerService.hideProarseNum();
        return new ResultDto(0, "投票排行隐藏投票统计....");
    }

    /**
     * 投票排行显示投票统计
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/showPraise")
    @ResponseBody
    public ResultDto showPraise(HttpServletResponse response) {

        managerService.showProarseNum();
        return new ResultDto(0, "投票排行显示投票统计....");
    }

    /**
     * 投票结束
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/endPraise")
    @ResponseBody
    public ResultDto endPraise(HttpServletResponse response) {

        managerService.endPraise();
        return new ResultDto(0, "投票已结束....");
    }

    /** 刷新节目内存数据 */
    @RequestMapping(value = "/refreshCeche")
    @ResponseBody
    public ResultDto refreshCeche(HttpServletResponse response) {

        managerService.refeshCeche();
        return new ResultDto(0, "内存数据已刷新....");
    }

    /**
     * 开启摇奖
     * 
     * @param response
     */
    @RequestMapping(value = "/openErnie")
    @ResponseBody
    public ResultDto openErnie(HttpServletResponse response) {

        managerService.startErnie();
        return new ResultDto(0, "开启摇奖成功");
    }

    /**
     * 关闭摇奖
     * 
     * @param response
     */
    @RequestMapping(value = "/closeErnie")
    @ResponseBody
    public ResultDto endErnie(HttpServletResponse response) {

        managerService.closeErnie();
        return new ResultDto(0, "关闭摇奖成功");
    }
}
