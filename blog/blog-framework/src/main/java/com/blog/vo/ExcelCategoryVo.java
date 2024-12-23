package com.blog.vo;


import com.alibaba.excel.annotation.ExcelProperty;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "导出分类EXCEL-VO")
public class ExcelCategoryVo {
    @ExcelProperty("分类名")
    private String name;
    // 描述
    @ExcelProperty("描述")
    private String description;

    // 状态0:正常,1禁用
    @ExcelProperty("状态0:正常,1禁用")
    private String status;
}
