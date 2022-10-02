package com.ruoyi.config.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 币种管理对象 config_money
 * 
 * @author metacoin
 * @date 2022-10-02
 */
public class Money extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long moneyId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String moneyCname;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String moneyEname;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String moneyDesc;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Double moneyRate;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String moneyScope;

    /** $column.columnComment */
    private Long moneyTypeId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long weight;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String moneyTypeName;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long orderSort;

    /** $column.columnComment */
    private String icon;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long enableStatus;

    /** $column.columnComment */
    private Long bookId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String bookName;

    /** $column.columnComment */
    private Long userId;

    /** $column.columnComment */
    private String userName;

    public void setMoneyId(Long moneyId) 
    {
        this.moneyId = moneyId;
    }

    public Long getMoneyId() 
    {
        return moneyId;
    }
    public void setMoneyCname(String moneyCname) 
    {
        this.moneyCname = moneyCname;
    }

    public String getMoneyCname() 
    {
        return moneyCname;
    }
    public void setMoneyEname(String moneyEname) 
    {
        this.moneyEname = moneyEname;
    }

    public String getMoneyEname() 
    {
        return moneyEname;
    }
    public void setMoneyDesc(String moneyDesc) 
    {
        this.moneyDesc = moneyDesc;
    }

    public String getMoneyDesc() 
    {
        return moneyDesc;
    }

    public void setMoneyRate(Double moneyRate)
    {
        this.moneyRate = moneyRate;
    }

    public Double getMoneyRate()
    {
        return moneyRate;
    }
    public void setMoneyScope(String moneyScope) 
    {
        this.moneyScope = moneyScope;
    }

    public String getMoneyScope() 
    {
        return moneyScope;
    }
    public void setMoneyTypeId(Long moneyTypeId) 
    {
        this.moneyTypeId = moneyTypeId;
    }

    public Long getMoneyTypeId() 
    {
        return moneyTypeId;
    }
    public void setWeight(Long weight) 
    {
        this.weight = weight;
    }

    public Long getWeight() 
    {
        return weight;
    }
    public void setMoneyTypeName(String moneyTypeName)
    {
        this.moneyTypeName = moneyTypeName;
    }

    public String getMoneyTypeName()
    {
        return moneyTypeName;
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
    public void setBookId(Long bookId) 
    {
        this.bookId = bookId;
    }

    public Long getBookId() 
    {
        return bookId;
    }
    public void setBookName(String bookName)
    {
        this.bookName = bookName;
    }

    public String getBookName()
    {
        return bookName;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("moneyId", getMoneyId())
            .append("moneyCname", getMoneyCname())
            .append("moneyEname", getMoneyEname())
            .append("moneyDesc", getMoneyDesc())
            .append("moneyRate", getMoneyRate())
            .append("moneyScope", getMoneyScope())
            .append("moneyTypeId", getMoneyTypeId())
            .append("weight", getWeight())
            .append("moneyTypeName", getMoneyTypeName())
            .append("remark", getRemark())
            .append("orderSort", getOrderSort())
            .append("icon", getIcon())
            .append("enableStatus", getEnableStatus())
            .append("bookId", getBookId())
            .append("bookName", getBookName())
            .append("userId", getUserId())
            .append("userName", getUserName())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
