package com.liguo.hgl.service.impl;

/*
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.junrar.Archive;
import com.github.junrar.rarfile.FileHeader;
import com.liguo.hgl.base.AbstractService;
import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dao.TbBrandMapper;
import com.liguo.hgl.proxydao.dao.TbProductTypeMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.ProductImport;
import com.liguo.hgl.proxydao.model.TbBrand;
import com.liguo.hgl.proxydao.model.TbProductType;
import com.liguo.hgl.service.IProductTypeService;
import com.liguo.hgl.util.PicCompressUtil;

@Service("productTypeService")
public class ProductTypeService extends AbstractService implements IProductTypeService {

    @Autowired
    protected TbProductTypeMapper tbProductTypeMapper;

    @Autowired
    protected TbBrandMapper tbBrandMapper;

    /**
     * 入金记录统计查询
     */
    @Override
    public int insert(String name, String describe, Integer parentId, Integer mainId, Integer userId, boolean isAdmin) {
        TbProductType record = new TbProductType();
        record.setName(name);
        record.setDescribes(describe);
        record.setParentId(parentId);
        record.setMainId(mainId);
        record.setCreateBy(userId);
        if(isAdmin){
        	record.setType(HglContants.PRODUCT_TYPE_DEFAULT_ADMIN);
        }else{
        	record.setType(HglContants.PRODUCT_TYPE_DEFAULT_WEB);
        }
        return tbProductTypeMapper.insert(record);
    }

    @Override
    public List<TbProductType> selectByTbProductType(TbProductType tbProductType) {
        Criteria example = new Criteria();
        example.put("name", tbProductType.getName());
        example.put("parent_id", tbProductType.getParentId());
        example.put("main_id", tbProductType.getMainId());
        return tbProductTypeMapper.selectByExample(example);
    }

    @Override
    public TbProductType selectByPrimaryKey(Integer id) {

        return tbProductTypeMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(TbProductType tbProductType) {
        tbProductType.setIcon("");
        return tbProductTypeMapper.updateByPrimaryKey(tbProductType);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return tbProductTypeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateProductTyppeByPrimaryKeyArray(Integer mainId, List<TbProductType> listP) {
        Criteria criteria = new Criteria();
        criteria.put("mainId", mainId);
        criteria.put("thirdList", listP);
        int ret = tbProductTypeMapper.updateProductTyppeByPrimaryKeyArray(criteria);
        return ret;
    }

    /**
     * @author zhoushangtao 查询商品分类
     */
    @Override
    public List<TbProductType> getProductType() {
        Criteria criteria = new Criteria();
        criteria.put("parent_id", 0);
        criteria.put("main_id", 0);
        List<TbProductType> list = tbProductTypeMapper.selectByExample(criteria);

        for (int i = 0; i < list.size(); i++) {
            TbProductType productType = list.get(i);
            Integer productTypeId = productType.getId();
            criteria = new Criteria();
            criteria.put("producttypeId", productTypeId);
            criteria.setOrderByClause("id desc");
            List<TbBrand> brands = tbBrandMapper.selectByObject(criteria);
            productType.setBrands(brands);

            list.set(i, productType);
        }
        return list;
    }

    /*
     * destinationDir:存放路径file：文件对象filename：文件名字
     */
    @Override
    public boolean uploadForm(MultipartFile file, String filename) throws Exception {
        String pathDir = System.getProperty("user.home") + File.separator + "shopLogo" + File.separator;
        logger.info(pathDir);
        File tmpFile = new File(pathDir);
        if (!tmpFile.exists()) {
            tmpFile.mkdirs();
        }
        uploadFile(pathDir, file, filename);

        return false;
    }

    /*
     * destinationDir:存放路径file：文件对象filename：文件名字
     */
    @Override
    public boolean uploadForm(MultipartFile file, String filename, String path) throws Exception {
        String pathDir = path;
        logger.info(pathDir);
        File tmpFile = new File(pathDir);
        if (!tmpFile.exists()) {
            tmpFile.mkdirs();
        }
        uploadFile(pathDir, file, filename);
        return false;
    }
    
    @Override
    public boolean uploadForm(MultipartFile file, String filename, String path, String suffix, String minImageSavePath) throws Exception {
        String pathDir = path;
        logger.info(pathDir);
        File tmpFile = new File(pathDir);
        if (!tmpFile.exists()) {
            tmpFile.mkdirs();
        }
        
        File tmpFile1 = new File(minImageSavePath);
        if (!tmpFile1.exists()) {
            tmpFile.mkdirs();
        }
        
        uploadFile(pathDir, file, filename);
        return false;
    }

    private boolean uploadFile(String destinationDir, MultipartFile file, String filename) throws Exception {
        logger.info("文件长度: " + file.getSize());
        logger.info("文件类型: " + file.getContentType());
        logger.info("文件名称: " + file.getName());
        logger.info("文件原名: " + file.getOriginalFilename());
        logger.info("========================================");
        try {
            SaveFileFromInputStream(file.getInputStream(), destinationDir, filename);
        }
        catch (IOException e) {
            logger.info(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * 保存ZIP内的文件
     * 
     * @param stream
     *            zip流
     * @param path
     *            保存在服务器上的路径
     * @param filename
     *            保存的文件名称
     * @throws IOException
     */
    public void SaveFileFromZipInputStream(ZipInputStream stream, String path, String filename) throws IOException {
        File tmpFile = new File(path);
        if (!tmpFile.exists()) {
            tmpFile.mkdirs();
        }
        FileOutputStream outputStream = new FileOutputStream(path + "/" + filename);
        int byteCount = 0;
        byte[] bytes = new byte[1024];
        while ((byteCount = stream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, byteCount);
        }
        outputStream.close();
    }

    /**
     * 根据原始rar路径，解压到指定文件夹下.
     * 
     * @param srcRarPath
     *            原始rar路径
     * @param dstDirectoryPath
     *            解压到的文件夹
     */
    public void SaveFileFromRarInputStream(Archive a,FileHeader fh, String dstDirectoryPath,String fileName) throws IOException {
        File dstDiretory = new File(dstDirectoryPath);
        if (!dstDiretory.exists()) {// 目标目录不存在时，创建该文件夹
            dstDiretory.mkdirs();
        }
        if (fh.isDirectory()) { // 文件夹
            File fol = new File(dstDirectoryPath);
            fol.mkdirs();
        }
        else { // 文件
            File out = new File(dstDirectoryPath+fileName);
            // System.out.println(out.getAbsolutePath());
            try {// 之所以这么写try，是因为万一这里面有了异常，不影响继续解压.
                if (!out.exists()) {
                    if (!out.getParentFile().exists()) {// 相对路径可能多级，可能需要创建父目录.
                        out.getParentFile().mkdirs();
                    }
                    out.createNewFile();
                }
                FileOutputStream os = new FileOutputStream(out);
                a.extractFile(fh, os);
                os.close();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    
    }

    /**
     * 保存文件
     * 
     * @param stream
     * @param path
     * @param filename
     * @throws IOException
     */
    private void SaveFileFromInputStream(InputStream stream, String path, String filename) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(path + "/" + filename);
        int byteCount = 0;
        byte[] bytes = new byte[1024];
        while ((byteCount = stream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, byteCount);
        }
        outputStream.close();
        stream.close();
    }

    @Override
    public List<TbProductType> selectProductTypeByMinId(Integer productTypeId) {

        return tbProductTypeMapper.selectProductTypeByMinId(productTypeId);
    }

    @Override
    public List<ProductImport> findNotParentId(Criteria criteria) {
        
        return tbProductTypeMapper.findNotParentId(criteria);
    }

    @Override
    public int findNotParentIdCount() {
        
        return tbProductTypeMapper.findNotParentIdCount();
    }
    @Override
    public List<TbProductType> selectNearShopType(){
    	return tbProductTypeMapper.selectNearShopType();
    }
}
