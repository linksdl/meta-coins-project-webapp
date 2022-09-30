package com.ruoyi.config.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import javax.persistence.Transient;

/**
 * 商品类型对象 config_goods_type
 * 
 * @author metacoin
 * @date 2022-10-04
 */
public class GoodsType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long goodsTypeId;

    /** 类型名称 */
    @Excel(name = "类型名称")
    private String goodsTypeName;

    /** 描述 */
    @Excel(name = "描述")
    private String goodsTypeDesc;

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

    public void setGoodsTypeId(Long goodsTypeId) 
    {
        this.goodsTypeId = goodsTypeId;
    }

    public Long getGoodsTypeId() 
    {
        return goodsTypeId;
    }
    public void setGoodsTypeName(String goodsTypeName) 
    {
        this.goodsTypeName = goodsTypeName;
    }

    public String getGoodsTypeName() 
    {
        return goodsTypeName;
    }
    public void setGoodsTypeDesc(String goodsTypeDesc) 
    {
        this.goodsTypeDesc = goodsTypeDesc;
    }

    public String getGoodsTypeDesc() 
    {
        return goodsTypeDesc;
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
            .append("goodsTypeId", getGoodsTypeId())
            .append("goodsTypeName", getGoodsTypeName())
            .append("goodsTypeDesc", getGoodsTypeDesc())
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
