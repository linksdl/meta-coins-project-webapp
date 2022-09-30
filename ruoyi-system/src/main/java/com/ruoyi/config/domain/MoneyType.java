package com.ruoyi.config.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import javax.persistence.*;

/**
 * 币种类型对象 config_money_type
 * 
 * @author metacoin
 * @date 2022-10-02
 */
public class MoneyType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 类型ID */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="money_type_id")
    private Long moneyTypeId;

    /** 类型名称 */
    @Excel(name = "类型名称")
    @Column(name="money_type_cname")
    private String moneyTypeCname;

    /** 英文名称 */
    @Excel(name = "英文名称")
    @Column(name="money_type_ename")
    private String moneyTypeEname;

    /** 描述 */
    @Excel(name = "描述")
    @Column(name="money_type_desc")
    private String moneyTypeDesc;

    /** 权重 */
    @Excel(name = "权重")
    @Column(name="weight")
    private Long weight;

    /** 排序 */
    @Excel(name = "排序")
    @Column(name="order_sort")
    private Long orderSort;

    /** 图标 */
    @Excel(name = "图标")
    @Column(name="icon")
    private String icon;

    /** 是否可用 */
    @Excel(name = "是否可用")
    @Column(name="enable_status")
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

    public void setMoneyTypeId(Long moneyTypeId) 
    {
        this.moneyTypeId = moneyTypeId;
    }

    public Long getMoneyTypeId() 
    {
        return moneyTypeId;
    }
    public void setMoneyTypeCname(String moneyTypeCname) 
    {
        this.moneyTypeCname = moneyTypeCname;
    }

    public String getMoneyTypeCname() 
    {
        return moneyTypeCname;
    }
    public void setMoneyTypeEname(String moneyTypeEname) 
    {
        this.moneyTypeEname = moneyTypeEname;
    }

    public String getMoneyTypeEname() 
    {
        return moneyTypeEname;
    }
    public void setMoneyTypeDesc(String moneyTypeDesc) 
    {
        this.moneyTypeDesc = moneyTypeDesc;
    }

    public String getMoneyTypeDesc() 
    {
        return moneyTypeDesc;
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
            .append("moneyTypeId", getMoneyTypeId())
            .append("moneyTypeCname", getMoneyTypeCname())
            .append("moneyTypeEname", getMoneyTypeEname())
            .append("moneyTypeDesc", getMoneyTypeDesc())
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
