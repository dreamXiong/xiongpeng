package com.linkon.hgl.web.action;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.liguo.hgl.ceche.ProgramCeche;
import com.liguo.hgl.proxydao.model.TbWeixinPassUser;
import com.liguo.hgl.service.IErnieService;

@Controller
@RequestMapping("/signInUser")
public class SignInUserController {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected IErnieService ernieService;

    @RequestMapping(value = "")
    public String index(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {

        logger.info("signInUser strate ");//CanErnieCount
        Map<String, String> queryShakeMap = ProgramCeche.queryShakeMap();
        Integer queryCanSignUserNum = 0;
        if (queryShakeMap != null && !queryShakeMap.isEmpty()) {
            queryCanSignUserNum = ernieService.queryCanSignUserNum(ProgramCeche.queryShakeMap());
        }
        List<TbWeixinPassUser> querySignInUser = ProgramCeche.querySignInUser();
        model.addAttribute("canErnieCount", queryCanSignUserNum);
        model.addAttribute("shakeUserNum", ProgramCeche.queryShakeMap().size());
        model.addAttribute("signUserNum", querySignInUser.size());

        ObjectMapper mapper = new ObjectMapper();
        String signInUserStr = mapper.writeValueAsString(querySignInUser);
        model.addAttribute("signInUserStr", signInUserStr);

        return "signinuser/userList";
    }

    @RequestMapping(value = "refreshShake")
    public String refreshShake(HttpServletRequest request, HttpServletResponse response) throws IOException {

        logger.info("refreshShake strate ");
        Collection<String> queryShakeMap = ProgramCeche.queryShakeMap().values();
        ObjectMapper mapper = new ObjectMapper();
        String shake = mapper.writeValueAsString(queryShakeMap);
        makeResponse(response, "text/josn", shake);
        logger.info(shake);
        ProgramCeche.cleanNewShakeMap();
        return null;
    }

    @RequestMapping(value = "refreshNewShake")
    public String refreshNewShake(HttpServletRequest request, HttpServletResponse response) throws IOException {

        logger.info("refreshShake strate ");
        Collection<String> queryNewShakeMap = ProgramCeche.queryNewShakeMap().values();
        ObjectMapper mapper = new ObjectMapper();
        String newShake = mapper.writeValueAsString(queryNewShakeMap);
        makeResponse(response, "text/josn", newShake);
        logger.info(newShake);
        ProgramCeche.cleanNewShakeMap();
        return null;
    }

    @RequestMapping(value = "refreshUser")
    public String refreshUser(Integer stateNum, HttpServletRequest request, HttpServletResponse response) throws IOException {

        logger.info(" refreshUser signInUser strate " + stateNum);
        TbWeixinPassUser signInUser = ProgramCeche.querySignInUserByNum(stateNum - 1);
        ObjectMapper mapper = new ObjectMapper();
        String signInUserStr = mapper.writeValueAsString(signInUser);

        Map<String, String> queryShakeMap = ProgramCeche.queryShakeMap();
        Integer queryCanSignUserNum = 0;
        if (queryShakeMap != null && !queryShakeMap.isEmpty()) {
            queryCanSignUserNum = ernieService.queryCanSignUserNum(ProgramCeche.queryShakeMap());
        }
        List<TbWeixinPassUser> querySignInUser = ProgramCeche.querySignInUser();
        String returnStr = "[" + signInUserStr + ",{canErnieCount:" + queryCanSignUserNum + ",shakeUserNum:" + ProgramCeche.queryShakeMap().size() + ",signUserNum:" + querySignInUser.size() + "}]";
        logger.info("refreshUser return string " + returnStr);
        makeResponse(response, "text/josn", returnStr);
        return null;
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
}
