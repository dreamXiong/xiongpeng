/*
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */
package com.liguo.hgl.service.impl;

import org.springframework.stereotype.Service;

import com.liguo.hgl.ceche.ProgramCeche;
import com.liguo.hgl.service.IManagerService;

/**
 * 管理<br>
 * 
 * @filename ManagerService.java<br>
 * @author 张勇<br>
 * @date 2016-1-11<br>
 * @Language java<br>
 */
@Service
public class ManagerService implements IManagerService {

    /**
     * 投票是否开启
     * 
     * @return
     */
    @Override
    public boolean isStartPraise() {

        return ProgramCeche.isStartPraise();
    }

    /**
     * 投票是否结束
     * 
     * @return
     */
    @Override
    public boolean isEndPraise() {

        return ProgramCeche.isEndPraise();
    }

    /**
     * 投票排行是否显示投票统计
     * 
     * @return
     */
    @Override
    public boolean isShowPraiseNum() {

        return ProgramCeche.isShowPraiseNum();
    }

    /**
     * 签到是否开启
     * 
     * @return
     */
    @Override
    public boolean isOpenSign() {

        return ProgramCeche.isOpenSign();
    }

    /**
     * 弹幕是否开启
     * 
     * @return
     */
    @Override
    public boolean isOpenInteract() {

        return ProgramCeche.isOpenInteract();
    }

    /**
     * 开启签到
     */
    @Override
    public void openSign() {

        ProgramCeche.setOpenSign(true);
    }

    /**
     * 关闭签到
     */
    @Override
    public void closeSign() {

        ProgramCeche.setOpenSign(false);
    }

    /**
     * 开启弹幕
     */
    @Override
    public void openInteract() {

        ProgramCeche.setOpenInteract(true);
    }

    /**
     * 开启弹幕
     */
    @Override
    public void closeInteract() {

        ProgramCeche.setOpenInteract(false);
    }

    /**
     * 隐藏投票数据
     */
    @Override
    public void hideProarseNum() {

        ProgramCeche.setShowPraiseNum(false);
    }

    /**
     * 显示投票数据
     */
    @Override
    public void showProarseNum() {

        ProgramCeche.setShowPraiseNum(true);
    }

    /**
     * 开始投票
     */
    @Override
    public void startPraise() {

        ProgramCeche.setStartPraise(true);
        ProgramCeche.setEndPraise(false);
    }

    /**
     * 结束投票
     */
    @Override
    public void endPraise() {

        ProgramCeche.setEndPraise(true);
    }

    @Override
    public void refeshCeche() {

        ProgramCeche.refeshCeche();
    }

    @Override
    public boolean isOpenErnie() {

        return ProgramCeche.isStartErnie();
    }

    /**
     * 开启摇奖
     */
    @Override
    public void startErnie() {

        ProgramCeche.setStartErnie(true);
    }

    /**
     * 关闭摇奖
     */
    @Override
    public void closeErnie() {

        ProgramCeche.setStartErnie(false);
    }
}
