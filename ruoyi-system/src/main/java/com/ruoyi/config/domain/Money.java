package com.ruoyi.config.domain;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import javax.persistence.*;
import javax.persistence.Entity;

/**
 * 币种管理对象 config_money
 *
 * @author metacoin
 * @date 2022-10-02
 */
@Table(name="config_money")
@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Money extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="money_id")
    private Long moneyId;

    /** 币种 */
    @Excel(name = "币种")
    @Column(name="money_cname")
    private String moneyCname;

    /** 英文名 */
    @Excel(name = "英文名")
    @Column(name="money_ename")
    private String moneyEname;

    /** 描述 */
    @Excel(name = "描述")
    @Column(name="money_desc")
    private String moneyDesc;

    /** 汇率 */
    @Excel(name = "汇率")
    @Column(name="money_rate")
    private Double moneyRate;

    /** 功能类型 */
    @Excel(name = "功能类型")
    @Column(name="money_type")
    private String moneyType;

    /** 功能范围 */
    @Excel(name = "功能范围")
    @Column(name="money_scope")
    private String moneyScope;

    /** 类型ID */
    @Column(name="money_type_id")
    private Long moneyTypeId;

    /** 权重 */
    @Excel(name = "权重")
    @Column(name="weight")
    private Long weight;

    /** 类型名称 */
    @Excel(name = "类型名称")
    @Column(name="money_type_name")
    private String moneyTypeName;

    /** 排序 */
    @Excel(name = "排序")
    @Column(name="order_sort")
    private Long orderSort;

    /** 图标 */
    @Column(name="icon")
    private String icon;

    /** 是否可用 */
    @Excel(name = "是否可用")
    @Column(name="enable_status")
    private Long enableStatus;

    /** 账本ID */
    @Column(name="book_id")
    private Long bookId;

    /** 账本名称 */
    @Excel(name = "账本名称")
    @Column(name="book_name")
    private String bookName;

    /** 用户ID */
    @Column(name="user_id")
    private Long userId;

    /** 用户名称 */
    @Column(name="user_name")
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

    public String getMoneyType() {
        return moneyType;
    }

    public void setMoneyType(String moneyType) {
        this.moneyType = moneyType;
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
            .append("moneyType", getMoneyType())
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
