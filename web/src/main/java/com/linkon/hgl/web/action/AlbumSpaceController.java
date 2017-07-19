package com.linkon.hgl.web.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.github.junrar.Archive;
import com.github.junrar.rarfile.FileHeader;
import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dto.WebUserDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbAlbumSpace;
import com.liguo.hgl.proxydao.model.TbSystemConfig;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.proxydao.util.StringUtil;
import com.liguo.hgl.service.IProductTypeService;
import com.liguo.hgl.service.TbAlbumSpaceService;
import com.liguo.hgl.service.TbSystemConfigService;
import com.liguo.hgl.util.ImageUtil;
import com.liguo.hgl.util.PicCompressUtil;

/***
 * *
 * 
 * <相册空间，主要是针对用户上传的图片，进行集中管理，已经图片显示><br>
 * 
 * @FileName AlbumSpaceController.java。<br>
 * @Language java。
 * @date 2016年5月31日
 * @author wzt
 */
@Controller
@RequestMapping("/albumspace")
public class AlbumSpaceController extends IBaseController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String ALBUM_SPACE_UPLOAD = "albumspace/upload";
    
    private static final String ALBUM_SPACE = "albumspace/albumspace";
    
    private static final String  PRODUCT_ALBUM_LIST= "product/productAlbumList";
    
    private static final String ALBUM_SPACE_ADD = "albumspace/albumspaceAdd";
    
    private static final String ALBUM_SPACE_List = "albumspace/albumspaceList";
    

    private static final String IMG_MIME_TYPES = "bmp,jpg,gif,tga,png";

    private static final String FILE_XML = "xml";

    @Autowired
    @Qualifier("productTypeService")
    private IProductTypeService productTypeService;
    
    @Autowired
    private TbAlbumSpaceService tbAlbumSpaceService;
    
    @Autowired
    private TbSystemConfigService tbSystemConfigService;

    @Override
    protected void init(PageDto page, HttpServletRequest request, HttpServletResponse response, Model model) {


    }

    @Override
    public String doSearchResult() {

        return null;
    }
    /****
     * 
     * <上传图片或压缩包>
     * @param model
     * @param request
     * @param response
     * @return
     * @author wzt
     * @since   2016年6月3日
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> imageUpload(Model model, HttpServletRequest request,HttpServletResponse response) {
        long startTime =Calendar.getInstance().getTimeInMillis();
        logger.info("开始时间:"+startTime);
        Map<String,String> map = new HashMap<String,String>();
        WebUserDto webUserDto =getLoginUser();
        tbAlbumSpaceService.albumSpaceUpload(webUserDto, request, map);
        long endTime = Calendar.getInstance().getTimeInMillis()-startTime;
        logger.info("间隔时间:"+endTime);
        return map;
    }

    
    
    @RequestMapping(value="/getMyAlbumspaceList")
    public String getMyAlbumspaceList(Model model){
        Integer userId =getLoginUserId();
        Criteria criteria = new Criteria();
        criteria.put("imageUrl", userId);
        List<TbAlbumSpace> tbAlbumSpaceList =tbAlbumSpaceService.selectByObject(criteria);
        model.addAttribute("tbAlbumSpaceList", tbAlbumSpaceList);
        return ALBUM_SPACE;
    }
    
    @RequestMapping(value="/getMyAlbumspaceListToPro")
    public String getMyAlbumspaceListToPro(Model model){
        Integer userId =getLoginUserId();
        Criteria criteria = new Criteria();
        criteria.put("imageUrl", userId);
        List<TbAlbumSpace> tbAlbumSpaceList =tbAlbumSpaceService.selectByObject(criteria);
        model.addAttribute("tbAlbumSpaceList", tbAlbumSpaceList);
        return PRODUCT_ALBUM_LIST;
    }
    
    @RequestMapping(value="/albumspaceAdd")
    public String albumspaceAdd(Model model){
        return ALBUM_SPACE_ADD;
    }
    
    /**
     * 显示产品图片
     * @param model
     * @param id
     * @param imgName
     * @param response
     * @return
     */
     @RequestMapping("/generateAlbumspaceImage")
        public String generateAlbumspaceImage(Model model,String id,String imageNameNow,HttpServletResponse response) {
             String userId =String.valueOf(getLoginUserId());
            ImageUtil.showImageAlbumspace(userId,imageNameNow,response);
            return null;
        }
     
     /**
      * 修改相册空间图片名称
      * @param model
      * @param id
      * @param imgName
      * @param response
      * @return
      */
     @RequestMapping(value="/alterImageName_ajax",method=RequestMethod.POST)
     public String alterImageName(Model model,TbAlbumSpace tbAlbumSpace ,HttpServletResponse response) {
         if(null==tbAlbumSpace||null ==tbAlbumSpace.getId()){
             return ALBUM_SPACE_List;
         }
         Criteria criteria = new Criteria();
         criteria.put("imageNameOld", tbAlbumSpace.getImageNameOld());
         List<TbAlbumSpace> tbAlbumSpaceList =tbAlbumSpaceService.selectByObject(criteria);
         if(!CollectionUtils.isEmpty(tbAlbumSpaceList)){
             getMyAlbumspaceList(model);
             model.addAttribute("errorTips", "0");
             return ALBUM_SPACE_List;
         }
         WebUserDto webUserDto =getLoginUser();
         tbAlbumSpaceService.alterImageName(tbAlbumSpace, webUserDto);
         getMyAlbumspaceList(model);
         return ALBUM_SPACE_List;
     }
     
     /**
      * 删除修改相册空间图片名称
      * @param model
      * @param id
      * @param imgName
      * @param response
      * @return
      */
     @RequestMapping(value="/deleteImageName_ajax",method=RequestMethod.POST)
     public String deleteImageName(Model model,Integer id ,HttpServletResponse response) {
         if(StringUtil.isBlank(String.valueOf(id))){
             return ALBUM_SPACE_List;
         }
         tbAlbumSpaceService.deleteByPrimaryKey(id);
         getMyAlbumspaceList(model);
         return ALBUM_SPACE_List;
     }
    
     
     @RequestMapping(value = "/searchImage", method = RequestMethod.POST)
     public String searchImage(Model model,String likeKey ){
         Integer userId =getLoginUserId();
         Criteria criteria = new Criteria();
         criteria.put("likeKey", likeKey);
         criteria.put("userId", userId);
         List<TbAlbumSpace> tbAlbumSpaceList =tbAlbumSpaceService.searchImage(criteria);
         model.addAttribute("tbAlbumSpaceList", tbAlbumSpaceList);
         return ALBUM_SPACE_List;
     }
     @RequestMapping(value = "/searchImageToPro", method = RequestMethod.POST)
     public String searchImageToPro(Model model,String likeKey ){
         Integer userId =getLoginUserId();
         Criteria criteria = new Criteria();
         criteria.put("likeKey", likeKey);
         criteria.put("userId", userId);
         List<TbAlbumSpace> tbAlbumSpaceList =tbAlbumSpaceService.searchImage(criteria);
         model.addAttribute("tbAlbumSpaceList", tbAlbumSpaceList);
         return PRODUCT_ALBUM_LIST;
     }
     
     
     public static void main(String[] args){
         String s  ="IMG_1655.JPG";
         String [] ss =s.split("\\.");
         for(int i =0;i<ss.length;i++){
             System.out.println(ss[i]);
         }
     }
     
     
     
}
