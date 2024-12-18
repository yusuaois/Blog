package com.blog.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.blog.service.CategoryService;
import com.blog.utils.BeanCopyUtils;
import com.blog.utils.redis.WebUtils;
import com.blog.vo.ExcelCategoryVo;
import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.blog.annotation.SystemLog;
import com.blog.common.AppHttpCodeEnum;
import com.blog.common.ResponseResult;
import com.blog.entity.Category;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



/**
 * <p>
 * 分类表 前端控制器
 * </p>
 *
 * @author ac
 * @since 2024-12-03
 */

@RestController
@RequestMapping("/content/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PreAuthorize("@ps.hasPermission('content:category:export')")
    @GetMapping("/export")
    @SystemLog(businessName = "导出分类")
    public void export(HttpServletResponse response) {
        try {
            // 设置下载文件的请求头
            WebUtils.setDownLoadHeader("分类.xlsx", response);
            // 获取需要导出的数据
            List<Category> categoryVos = categoryService.list();

            List<ExcelCategoryVo> excelCategoryVos = BeanCopyUtils.copyBeanList(categoryVos, ExcelCategoryVo.class);
            // 把数据写入到Excel中
            EasyExcel.write(response.getOutputStream(), ExcelCategoryVo.class).autoCloseStream(Boolean.FALSE)
                    .sheet("分类导出")
                    .doWrite(excelCategoryVos);

        } catch (Exception e) {
            // 如果出现异常也要响应json
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
            WebUtils.renderString(response, JSON.toJSONString(result));
        }
    }

    @RequestMapping("/listAllCategory")
    @SystemLog(businessName = "获取分类列表")
    public ResponseResult listAllCategory() {
        return categoryService.getCategoryList();
    }

    @GetMapping("/list")
    public ResponseResult listCategory(Integer pageNum, Integer pageSize, String name, String status) {
        return categoryService.listCategory(pageNum, pageSize, name, status);
    }
    
    @PostMapping()
    public ResponseResult addCategory(@RequestBody Category category) {
        return categoryService.addCategory(category);
    }
}
