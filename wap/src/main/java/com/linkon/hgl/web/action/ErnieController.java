package com.linkon.hgl.web.action;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.liguo.hgl.ceche.ProgramCeche;
import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.exceptions.WapServiceException;
import com.liguo.hgl.proxydao.model.TbWeixinPassUser;
import com.liguo.hgl.service.IErnieService;
import com.liguo.hgl.service.IMessageConvert;

@Controller
@RequestMapping("/ernie")
public class ErnieController {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static int loadnum = -1;//进入一次大转盘以后 将取消继续参加摇奖资格

    @Autowired
    protected IErnieService ernieService;
    @Autowired
    protected IMessageConvert messageConvert;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {

        logger.info("ernie strate ");

        String userArray = queryErnieUser();
        model.addAttribute("userArray", userArray);
        return "ernie/ernie";
    }

    @RequestMapping(value = "/reLoadUser")
    public void reLoadUser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String userArray = queryErnieUser();
        makeResponse(response, "text/josn", userArray);
    }

    @RequestMapping(value = "/userWin")
    public void userWin(TbWeixinPassUser tbWeixinPassUser, HttpServletRequest request, HttpServletResponse response) throws IOException, WapServiceException {

        ernieService.updateWinUserByPrimary(tbWeixinPassUser.getWinGrade(), tbWeixinPassUser.getId());
        makeResponse(response, "text/josn", "");
    }

    @RequestMapping(value = "/endErnie")
    public void endErnie(HttpServletResponse response) throws IOException, WapServiceException {

        ProgramCeche.endErnie();
        makeResponse(response, "text/josn", "");
    }

    /**
     * 获得抽奖用户 并取消取得用户继续抽奖的资格
     * @return
     * @throws IOException
     */
    private String queryErnieUser() throws IOException {

        List<TbWeixinPassUser> queryRandeUserList = ernieService.queryRandeUserList(HglContants.GET_RAND_USER_COUNT, ProgramCeche.queryShakeMap());
        if (queryRandeUserList.size() < 12) {
            queryRandeUserList = ernieService.queryRandeUserList(HglContants.GET_RAND_USER_COUNT, null);
            if (queryRandeUserList.size() < 12) {
                ernieService.updateWinUserByWinGrade(0);
                queryRandeUserList = ernieService.queryRandeUserList(HglContants.GET_RAND_USER_COUNT, null);
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        String userArray = mapper.writeValueAsString(queryRandeUserList);
        //取消取得用户继续抽奖的资格 并 将用户加入中奖一级缓存
        ernieService.updateUserByPrimaryKeyArray(loadnum, queryRandeUserList);

        loadnum--;
        return userArray;
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
