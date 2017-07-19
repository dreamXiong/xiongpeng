/*
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */
package com.liguo.hgl.service;

/**
 * 管理<br>
 * 
 * @filename IManagerService.java<br>
 * @author 张勇<br>
 * @date 2016-1-11<br>
 * @Language java<br>
 */
public interface IManagerService {
    /**
     * 投票是否开启
     * 
     * @return
     */
    public boolean isStartPraise();

    /**
     * 投票是否结束
     * 
     * @return
     */
    public boolean isEndPraise();

    /**
     * 投票排行是否显示投票统计
     * 
     * @return
     */
    public boolean isShowPraiseNum();

    /**
     * 签到是否开启
     * 
     * @return
     */
    public boolean isOpenSign();

    /**
     * 弹幕是否开启
     * 
     * @return
     */
    public boolean isOpenInteract();

    /**
     * 开启签到
     */
    public void openSign();

    /**
     * 关闭签到
     */
    public void closeSign();

    /**
     * 开启弹幕
     */
    public void openInteract();

    /**
     * 开启弹幕
     */
    public void closeInteract();

    /**
     * 隐藏投票数据
     */
    public void hideProarseNum();

    /**
     * 显示投票数据
     */
    public void showProarseNum();

    /**
     * 开始投票
     */
    public void startPraise();

    /**
     * 结束投票
     */
    public void endPraise();

    /**
     * 刷新内存
     */
    public void refeshCeche();

    /**
     * 是否开始摇奖
     */
    public boolean isOpenErnie();

    /**
     * 开始摇奖
     */
    public void startErnie();

    /**
     * 关闭摇奖
     */
    public void closeErnie();

}
