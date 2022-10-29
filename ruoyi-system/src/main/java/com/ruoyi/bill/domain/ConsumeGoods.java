package com.ruoyi.bill.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 消费商品对象 bill_consume_goods
 * 
 * @author metacoin
 * @date 2022-10-29
 */
public class ConsumeGoods extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long consumeGoodsId;

    /** 消费账单ID */
    private Long consumeBillId;

    /** 商品 */
    @Excel(name = "商品")
    private Long goodsId;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String goodsCname;

    /** 商品英文 */
    @Excel(name = "商品英文")
    private String goodsEname;

    /** 单价 */
    @Excel(name = "单价")
    private Double goodsPrice;

    /** 数量 */
    @Excel(name = "数量")
    private Integer goodsTotal;

    /** 总价 */
    @Excel(name = "总价")
    private Double goodsTotalPrice;

    /** 是否可用 */
    @Excel(name = "是否可用")
    private Integer enableStatus;

    /** 权重 */
    private Integer weight;

    /** 图标 */
    private String icon;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private Integer isDeleted;

    /** 排序 */
    private Integer orderSort;

    /** 描述 */
    private String goodsDesc;

    public void setConsumeGoodsId(Long consumeGoodsId) 
    {
        this.consumeGoodsId = consumeGoodsId;
    }

    public Long getConsumeGoodsId() 
    {
        return consumeGoodsId;
    }
    public void setConsumeBillId(Long consumeBillId) 
    {
        this.consumeBillId = consumeBillId;
    }

    public Long getConsumeBillId() 
    {
        return consumeBillId;
    }
    public void setGoodsId(Long goodsId) 
    {
        this.goodsId = goodsId;
    }

    public Long getGoodsId() 
    {
        return goodsId;
    }
    public void setGoodsCname(String goodsCname) 
    {
        this.goodsCname = goodsCname;
    }

    public String getGoodsCname() 
    {
        return goodsCname;
    }
    public void setGoodsEname(String goodsEname) 
    {
        this.goodsEname = goodsEname;
    }

    public String getGoodsEname() 
    {
        return goodsEname;
    }
    public void setGoodsPrice(Double goodsPrice) 
    {
        this.goodsPrice = goodsPrice;
    }

    public Double getGoodsPrice() 
    {
        return goodsPrice;
    }
    public void setGoodsTotal(Integer goodsTotal) 
    {
        this.goodsTotal = goodsTotal;
    }

    public Integer getGoodsTotal() 
    {
        return goodsTotal;
    }
    public void setGoodsTotalPrice(Double goodsTotalPrice) 
    {
        this.goodsTotalPrice = goodsTotalPrice;
    }

    public Double getGoodsTotalPrice() 
    {
        return goodsTotalPrice;
    }
    public void setEnableStatus(Integer enableStatus) 
    {
        this.enableStatus = enableStatus;
    }

    public Integer getEnableStatus() 
    {
        return enableStatus;
    }
    public void setWeight(Integer weight) 
    {
        this.weight = weight;
    }

    public Integer getWeight() 
    {
        return weight;
    }
    public void setIcon(String icon) 
    {
        this.icon = icon;
    }

    public String getIcon() 
    {
        return icon;
    }
    public void setIsDeleted(Integer isDeleted) 
    {
        this.isDeleted = isDeleted;
    }

    public Integer getIsDeleted() 
    {
        return isDeleted;
    }
    public void setOrderSort(Integer orderSort) 
    {
        this.orderSort = orderSort;
    }

    public Integer getOrderSort() 
    {
        return orderSort;
    }
    public void setGoodsDesc(String goodsDesc) 
    {
        this.goodsDesc = goodsDesc;
    }

    public String getGoodsDesc() 
    {
        return goodsDesc;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("consumeGoodsId", getConsumeGoodsId())
            .append("consumeBillId", getConsumeBillId())
            .append("goodsId", getGoodsId())
            .append("goodsCname", getGoodsCname())
            .append("goodsEname", getGoodsEname())
            .append("goodsPrice", getGoodsPrice())
            .append("goodsTotal", getGoodsTotal())
            .append("goodsTotalPrice", getGoodsTotalPrice())
            .append("enableStatus", getEnableStatus())
            .append("weight", getWeight())
            .append("icon", getIcon())
            .append("isDeleted", getIsDeleted())
            .append("orderSort", getOrderSort())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("goodsDesc", getGoodsDesc())
            .toString();
    }
}
