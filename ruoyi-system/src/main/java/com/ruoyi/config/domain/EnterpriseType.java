package com.ruoyi.config.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import javax.persistence.Transient;

/**
 * 商家类型对象 config_enterprise_type
 * 
 * @author metacoin
 * @date 2022-10-04
 */
public class EnterpriseType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long enterpriseTypeId;

    /** 商家类型 */
    @Excel(name = "商家类型")
    private String enterpriseTypeName;

    /** 描述 */
    @Excel(name = "描述")
    private String enterpriseTypeDesc;

    /** 权重 */
    @Excel(name = "权重")
    private Long weight;

    /** 排序 */
    @Excel(name = "排序")
    private Long orderSort;

    /** 图标 */
    @Excel(name = "图标")
    private String icon;

    /** 是否可用 */
    @Excel(name = "是否可用")
    private Long enableStatus;

    /** 是否可以下拉*/
    @Transient
    private boolean disabled;

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public void setEnterpriseTypeId(Long enterpriseTypeId) 
    {
        this.enterpriseTypeId = enterpriseTypeId;
    }

    public Long getEnterpriseTypeId() 
    {
        return enterpriseTypeId;
    }
    public void setEnterpriseTypeName(String enterpriseTypeName) 
    {
        this.enterpriseTypeName = enterpriseTypeName;
    }

    public String getEnterpriseTypeName() 
    {
        return enterpriseTypeName;
    }
    public void setEnterpriseTypeDesc(String enterpriseTypeDesc) 
    {
        this.enterpriseTypeDesc = enterpriseTypeDesc;
    }

    public String getEnterpriseTypeDesc() 
    {
        return enterpriseTypeDesc;
    }
    public void setWeight(Long weight) 
    {
        this.weight = weight;
    }

    public Long getWeight() 
    {
        return weight;
    }
    public void setOrderSort(Long orderSort) 
    {
        this.orderSort = orderSort;
    }

    public Long getOrderSort() 
    {
        return orderSort;
    }
    public void setIcon(String icon) 
    {
        this.icon = icon;
    }

    public String getIcon() 
    {
        return icon;
    }
    public void setEnableStatus(Long enableStatus) 
    {
        this.enableStatus = enableStatus;
    }

    public Long getEnableStatus() 
    {
        return enableStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("enterpriseTypeId", getEnterpriseTypeId())
            .append("enterpriseTypeName", getEnterpriseTypeName())
            .append("enterpriseTypeDesc", getEnterpriseTypeDesc())
            .append("weight", getWeight())
            .append("remark", getRemark())
            .append("orderSort", getOrderSort())
            .append("icon", getIcon())
            .append("enableStatus", getEnableStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
