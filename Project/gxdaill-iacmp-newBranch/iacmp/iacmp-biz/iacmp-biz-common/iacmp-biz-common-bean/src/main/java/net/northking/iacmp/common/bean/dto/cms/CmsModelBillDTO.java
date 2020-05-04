package net.northking.iacmp.common.bean.dto.cms;

import lombok.Data;
import net.northking.iacmp.annotation.Excel;

import java.io.Serializable;

@Data
public class CmsModelBillDTO implements Serializable {

    @Excel(name = "模型名称")
    private String cmsModeName;

    @Excel(name = "模型编码")
    private String cmsModeCode;

    @Excel(name = "分类名称")
    private String cmsBillName;

    @Excel(name = "分类编码")
    private String cmaBillCode;
}
