package com.linkon.admin.action;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.github.junrar.Archive;
import com.github.junrar.rarfile.FileHeader;
import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.dto.ProductDto;
import com.liguo.hgl.proxydao.dto.TbProductDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.ProductInfoDto;
import com.liguo.hgl.proxydao.model.TbAlbumSpace;
import com.liguo.hgl.proxydao.model.TbProduct;
import com.liguo.hgl.proxydao.model.TbProductInventory;
import com.liguo.hgl.proxydao.model.TbProductType;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.IProductTypeService;
import com.liguo.hgl.service.TbBrandService;
import com.liguo.hgl.service.TbProductInventoryService;
import com.liguo.hgl.service.TbProductService;
import com.liguo.hgl.util.BeanCopyUtil;
import com.liguo.hgl.util.ImageUtil;
import com.liguo.hgl.util.StringUtil;

/***
 * *
 * 
 * <产品批量模板上传><br>
 * 
 * @FileName ProductImportUploadController.java。<br>
 * @Language java。
 * @date 2016年6月17日
 * @author wzt
 */
@Controller
@RequestMapping("productImportUpload")
public class ProductImportUploadController{
    
    @Autowired
    private IProductTypeService productTypeService;
    
    private static final String PRODUCT_IMPORT_UPLOAD = "productImportUpload/productImportUpload";
    
    /***
     * 
     * <模板上传>
     * @param model
     * @param request
     * @param response
     * @return
     * @author wzt
     * @since   2016年7月16日
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String imageUpload(Model model, HttpServletRequest request,HttpServletResponse response) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile imageData = multipartRequest.getFile("file");
        if(imageData.isEmpty()){
            model.addAttribute("success", "2");
            model.addAttribute("message", "请勿上传空文件");
            return PRODUCT_IMPORT_UPLOAD;
        }
        
        String fileName = imageData.getOriginalFilename();
        String[] arr = fileName.split("\\.");
        String fileType = arr[arr.length - 1].toLowerCase();
        Map<String,String> map  = new HashMap<String,String>();
        model.addAttribute("success", "1");
        model.addAttribute("message", "上传成功");
        
        //上传到相册目录
        try {
            productTypeService.uploadForm(imageData, HglContants.PRODUCT_BATCH_DOWNLOAD_NAME, HglContants.PRODUCT_BATCH_DOWNLOAD_PATH);
        }
        catch (Exception e) {
            model.addAttribute("success", "2");
            model.addAttribute("message", "上传失败"); 
        }
        return PRODUCT_IMPORT_UPLOAD;
    }
    /***
     * 
     * <展示上传按钮页面>
     * @param model
     * @return
     * @author wzt
     * @since   2016年7月16日
     */
    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(Model model) {
        model.addAttribute("success", "3");//3为显示上传按钮页面的标示
        return PRODUCT_IMPORT_UPLOAD;
    }
}