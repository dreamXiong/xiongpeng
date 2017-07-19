package com.linkon.hgl.web.action;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.liguo.hgl.ceche.ProgramCeche;
import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.model.TbWeixinPassUser;
import com.liguo.hgl.proxydao.util.StringUtil;
import com.liguo.hgl.service.IProgramService;

@Controller
@RequestMapping("/win")
public class ShakeController {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static int loadnum = -1;

    @Autowired
    private IProgramService programService;

    @RequestMapping(value = "")
    public String index(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {

        logger.info("Shake strate ");
        String code = request.getParameter("code");
        logger.info("授权code码：" + code);
        if (StringUtil.isBlank(code)) {
            return "redirect:" + HglContants.WEIXIN_SHAKE_URL;
        }
        request.getSession().setAttribute("code", code);

        return "redirect:/win/shake";
    }

    /**
     * 投票节目列表
     * 
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/shake")
    public String shake(Model model, HttpServletRequest request) {

        String code = getCodeBySession(request);
        if (code == null) {
            return "redirect:" + HglContants.WEIXIN_PRAISE_URL;
        }
        String openId = getOpenIdBySession(request);
        if (openId == null) {
            try {
                openId = programService.queryUserOpenId(code.toString());
                boolean flag = programService.isSubscribe(openId);
                logger.info("用户是否关注了公众号：" + flag);
                if (flag) {
                    return "redirect:" + HglContants.CSKNXH_WEIXIN_URL;
                }
                request.getSession().setAttribute("openId", openId);
            } catch (Exception e) {
                logger.info("请求用户OpenId", e);
            }
        }
        logger.info("openId：" + openId);
        return "shake/shake";
    }

    /**
     * 投票节目列表
     * 
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/test")
    public String test(Model model, HttpServletRequest request) {

        request.getSession().setAttribute("openId", "87464");
        return "shake/shake";
    }

    /**
     * 摇一摇事件处理
     * @param model
     * @param request
     * @return
     * @throws IOException 
     */
    @RequestMapping(value = "/yaoyiyao")
    public void yaoyiyao(String openId, HttpServletRequest request, HttpServletResponse response) throws IOException {

        logger.info("yaoyiyao：" + openId);
        TbWeixinPassUser querySignInUser = ProgramCeche.querySignInUser(openId);
        if (querySignInUser == null) {
            makeResponse(response, "text/josn", "请先签到！");
            return;
        }
        ProgramCeche.addNewShakeMap(openId);
        Integer queryErnieGrade = ProgramCeche.queryErnieGrade(openId);
        if (!ProgramCeche.isStartErnie()) {
            makeResponse(response, "text/josn", "摇奖还未开始！");
            return;
        } else {
            ProgramCeche.addShakeMap(openId);
            if (!ProgramCeche.queryIsStrateErnie()) {
                makeResponse(response, "text/josn", "大奖即将揭晓！");
                return;
            }
        }

        Integer queryErnieWinGrade = ProgramCeche.queryErnieWinGrade(openId);
        if (queryErnieWinGrade != null && queryErnieWinGrade > 0) {
            makeResponse(response, "text/josn", "您已经中奖了！");
            return;
        }

        if (queryErnieGrade != null && queryErnieGrade < 0) {
            makeResponse(response, "text/josn", "您离中奖只差一步！");
            return;
        }
        makeResponse(response, "text/josn", "你未中奖，据说发弹幕会中奖哦！");
    }

    /**
     * 将内容从响应输出流中发送给请求方。
     * @param resp 响应对象
     * @param contentType 设置格式
     * @param string 响应的内容
     * @throws IOException
     */
    private void makeResponse(HttpServletResponse resp, String contentType, String string) throws IOException {

        resp.setContentType(contentType);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(resp.getOutputStream(), "utf-8"));
        out.write(string);
        out.flush();
        out.close();
    }

    private String getOpenIdBySession(HttpServletRequest request) {

        Object obj = request.getSession().getAttribute("openId");
        return obj == null ? null : obj.toString();
    }

    private String getCodeBySession(HttpServletRequest request) {

        Object code = request.getSession().getAttribute("code");
        return code == null ? null : code.toString();
    }
}
