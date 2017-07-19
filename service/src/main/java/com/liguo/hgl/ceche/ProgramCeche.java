/*
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */
package com.liguo.hgl.ceche;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liguo.hgl.proxydao.dto.TbProgramInfoDto;
import com.liguo.hgl.proxydao.model.TbWeixinPassUser;
import com.liguo.hgl.service.IErnieService;
import com.liguo.hgl.service.IProgramService;

/**
 * 节目点赞缓存<br>
 * 
 * @filename ProgramCeche.java<br>
 * @author 张勇<br>
 * @date 2016-1-8<br>
 * @Language java<br>
 */
public class ProgramCeche {
    /** 节目信息 */
    private static Map<Integer, TbProgramInfoDto> ceche = new HashMap<Integer, TbProgramInfoDto>();

    /** 总投票数 */
    private static Integer sum = 0;

    /** 同步锁key */
    private final static Object key = new Object();

    /** 投票开启 */
    private static boolean isStartPraise = false;

    /** 投票结束 */
    private static boolean isEndPraise = false;

    /** 是否显示节目单的获赞数 */
    private static boolean showPraiseNum = true;

    /** 是否开启签到 */
    private static boolean isOpenSign = false;

    /** 是否开启弹幕 */
    private static boolean isOpenInteract = false;
    /** 目前为第几个节目 */
    private static int index = 1;
    /** 每次查询的节目量 */
    private static int size = 7;

    /** 摇奖开启 */
    private static boolean isStartErnie = false;

    /**
     * 摇一摇一级结果缓存
     */
    private static Map<String, Integer> ernieMap = new HashMap<String, Integer>();
    /**
     * 摇一摇二级结果缓存
     */
    private static Map<String, Integer> ernieWinMap = new HashMap<String, Integer>();
    /**
     * 摇奖阶段摇一摇的用户
     * 
     */
    private static Map<String, String> shakeMap = new HashMap<String, String>();

    /**
     * 新增的摇一摇的用户
     * 
     */
    private static Map<String, String> newShakeMap = new HashMap<String, String>();
    /**
     * 签到了的用户
     * 
     */
    private static Map<String, TbWeixinPassUser> signInMap = new HashMap<String, TbWeixinPassUser>();
    /**
     * 签到了的用户
     * 
     */
    private static List<TbWeixinPassUser> signInList = new ArrayList<TbWeixinPassUser>();

    /**
     * 节目是否开启投票
     */
    private static Map<Integer, Integer> praiseFlagMap = new HashMap<Integer, Integer>();

    static {
        refeshCeche();
    }

    public static void changPraiseFlag(Integer id, Integer praiseFlag) {

        if (praiseFlagMap.get(id) != null) {
            praiseFlagMap.put(id, praiseFlag);
        }
    }

    public static Integer getPraiseFlag(Integer id) {

        return praiseFlagMap.get(id);
    }

    public static void addSignInUser(TbWeixinPassUser tbWeixinPassUser) {

        if (signInMap.get(tbWeixinPassUser.getOpenId()) == null) {
            signInList.add(tbWeixinPassUser);
        }
        signInMap.put(tbWeixinPassUser.getOpenId(), tbWeixinPassUser);
    }

    public static TbWeixinPassUser querySignInUser(String openId) {

        return signInMap.get(openId);
    }

    public static List<TbWeixinPassUser> querySignInUser() {

        return signInList;
    }

    public static TbWeixinPassUser querySignInUserByNum(Integer stateNum) {

        if (stateNum < signInList.size()) {
            return signInList.get(stateNum);
        }
        return null;
    }

    public static Map<String, String> queryNewShakeMap() {

        return newShakeMap;
    }

    public static void addNewShakeMap(String openId) {

        newShakeMap.put(openId, openId);
    }

    public static void cleanNewShakeMap() {

        newShakeMap.clear();
    }

    public static void addShakeMap(String openId) {

        shakeMap.put(openId, openId);
    }

    public static void cleanShakeMap() {

        shakeMap.clear();
    }

    public static Map<String, String> queryShakeMap() {

        return shakeMap;
    }

    public static boolean queryIsStrateErnie() {

        return ernieMap.isEmpty();
    }

    public static void endErnie() {

        isStartErnie = false;
        shakeMap.clear();
        ernieMap.clear();
    }

    public static void addErnie(List<TbWeixinPassUser> queryRandeUserList, Integer winGrade) {

        ernieMap.clear();
        for (TbWeixinPassUser tbWeixinPassUser : queryRandeUserList) {
            ernieMap.put(tbWeixinPassUser.getOpenId(), winGrade);
        }
    }

    public static void addWinErnie(String openId, Integer winGrade) {

        ernieWinMap.put(openId, winGrade);
    }

    public static Integer queryErnieGrade(String openId) {

        System.out.println(ernieMap.keySet());

        return ernieMap.get(openId);
    }

    public static Integer queryErnieWinGrade(String openId) {

        return ernieWinMap.get(openId);
    }

    /**
     * 刷新内存数据
     */
    public static void refeshCeche() {

        synchronized (key) {
            IProgramService programService = (IProgramService) SpringContextUtils.getBeanById("programService");
            List<TbProgramInfoDto> list = programService.queryProgramSortList();
            ceche.clear();
            for (TbProgramInfoDto dto : list) {
                ceche.put(dto.getId(), dto);
                sum += dto.getPraise();
                praiseFlagMap.put(dto.getId(), dto.getPraiseFlag());
            }
            signInMap.clear();
            signInList.clear();

            ernieWinMap.clear();
            IErnieService ernieService = (IErnieService) SpringContextUtils.getBeanById("ernieService");
            List<TbWeixinPassUser> queryRandeUserList = ernieService.selectSignInUser(10000);
            for (TbWeixinPassUser tbWeixinPassUser : queryRandeUserList) {
                signInMap.put(tbWeixinPassUser.getOpenId(), tbWeixinPassUser);
                signInList.add(tbWeixinPassUser);
                if (tbWeixinPassUser.getWinGrade() > 0) {
                    ernieWinMap.put(tbWeixinPassUser.getOpenId(), tbWeixinPassUser.getWinGrade());
                }
            }
        }
    }

    /**
     * 添加节目单信息
     * 
     * @param programInfo
     */
    public static void addProgram(TbProgramInfoDto programInfo) {

        synchronized (key) {
            if (!ceche.containsKey(programInfo.getId())) {
                ceche.put(programInfo.getId(), programInfo);
                sum += programInfo.getPraise();
            }
        }
    }

    /**
     * 加点赞数
     * 
     * @param programId
     * @param num
     */
    public static void addProgramPraiseNum(Integer programId, Integer num) {

        synchronized (key) {
            TbProgramInfoDto programInfo = ceche.get(programId);
            if (programInfo != null) {
                programInfo.setPraise(programInfo.getPraise() + num);
                sum += num;
                ceche.put(programId, programInfo);
            }
        }
    }

    /**
     * 根据内存中点赞数降序排序
     * 
     * @return
     */
    public static List<TbProgramInfoDto> getProgramSortList() {

        List<TbProgramInfoDto> list = new ArrayList<TbProgramInfoDto>();
        list.addAll(ceche.values());
        Collections.sort(list);
        return list.subList(0, size);
    }

    public static boolean isStartPraise() {

        return isStartPraise;
    }

    public static boolean isEndPraise() {

        return isEndPraise;
    }

    public static boolean isShowPraiseNum() {

        return showPraiseNum;
    }

    public static boolean isOpenSign() {

        return isOpenSign;
    }

    public static boolean isOpenInteract() {

        return isOpenInteract;
    }

    public static Integer getSum() {

        return sum;
    }

    public static void setStartPraise(boolean isStartPraise) {

        ProgramCeche.isStartPraise = isStartPraise;
    }

    public static void setEndPraise(boolean isEndPraise) {

        ProgramCeche.isEndPraise = isEndPraise;
    }

    public static void setShowPraiseNum(boolean showPraiseNum) {

        ProgramCeche.showPraiseNum = showPraiseNum;
    }

    public static void setOpenSign(boolean isOpenSign) {

        ProgramCeche.isOpenSign = isOpenSign;
    }

    public static void setOpenInteract(boolean isOpenInteract) {

        ProgramCeche.isOpenInteract = isOpenInteract;
    }

    public static int getIndex() {

        return index;
    }

    public static void setIndex(int index) {

        ProgramCeche.index = index;
    }

    public static int getSize() {

        return size;
    }

    public static void setSize(int size) {

        ProgramCeche.size = size;
    }

    public static boolean isStartErnie() {

        return isStartErnie;
    }

    public static void setStartErnie(boolean isStartErnie) {

        ProgramCeche.isStartErnie = isStartErnie;
        if (!isStartErnie) {
            ernieMap.clear();
            shakeMap.clear();
        }
    }

}
