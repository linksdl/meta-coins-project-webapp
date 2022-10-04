package com.ruoyi.config.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.TreeEntity;

/**
 * 账户管理对象 config_account
 * 
 * @author metacoin
 * @date 2022-10-04
 */
public class Account extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long accountId;

    /** 账户名称 */
    @Excel(name = "账户名称")
    private String accountName;

    /** 父类ID */
    @Excel(name = "父类ID")
    private Long accountParentId;

    /** 父类账户 */
    @Excel(name = "父类账户")
    private String accountParentName;

    /** 层次 */
    @Excel(name = "层次")
    private String accountLevel;

    /** 功能范围 */
    @Excel(name = "功能范围")
    private String accountScope;

    /** 类型 */
    @Excel(name = "类型")
    private String accountType;

    /** 排序 */
    @Excel(name = "排序")
    private String accountSort;

    /** 类别 */
    @Excel(name = "类别")
    private String accountClass;

    /** 描述 */
    @Excel(name = "描述")
    private String accountDesc;

    /** 权重 */
    @Excel(name = "权重")
    private Long weight;

    /** 图标 */
    @Excel(name = "图标")
    private String icon;

    /** 是否可用 */
    @Excel(name = "是否可用")
    private Long enableStatus;

    /** 排序 */
    @Excel(name = "排序")
    private Long orderSort;

    /** 账本ID */
    private Long bookId;

    /** 账本名称 */
    private String bookName;

    /** 用户ID */
    private Long userId;

    /** 用户名 */
    private String userName;

    public void setAccountId(Long accountId) 
    {
        this.accountId = accountId;
    }

    public Long getAccountId() 
    {
        return accountId;
    }
    public void setAccountName(String accountName) 
    {
        this.accountName = accountName;
    }

    public String getAccountName() 
    {
        return accountName;
    }
    public void setAccountParentId(Long accountParentId) 
    {
        this.accountParentId = accountParentId;
    }

    public Long getAccountParentId() 
    {
        return accountParentId;
    }
    public void setAccountParentName(String accountParentName)
    {
        this.accountParentName = accountParentName;
    }

    public String getAccountParentName()
    {
        return accountParentName;
    }
    public void setAccountLevel(String accountLevel) 
    {
        this.accountLevel = accountLevel;
    }

    public String getAccountLevel() 
    {
        return accountLevel;
    }
    public void setAccountScope(String accountScope) 
    {
        this.accountScope = accountScope;
    }

    public String getAccountScope() 
    {
        return accountScope;
    }
    public void setAccountType(String accountType) 
    {
        this.accountType = accountType;
    }

    public String getAccountType() 
    {
        return accountType;
    }
    public void setAccountSort(String accountSort) 
    {
        this.accountSort = accountSort;
    }

    public String getAccountSort() 
    {
        return accountSort;
    }
    public void setAccountClass(String accountClass) 
    {
        this.accountClass = accountClass;
    }

    public String getAccountClass() 
    {
        return accountClass;
    }
    public void setAccountDesc(String accountDesc) 
    {
        this.accountDesc = accountDesc;
    }

    public String getAccountDesc() 
    {
        return accountDesc;
    }
    public void setWeight(Long weight) 
    {
        this.weight = weight;
    }

    public Long getWeight() 
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
    public void setEnableStatus(Long enableStatus) 
    {
        this.enableStatus = enableStatus;
    }

    public Long getEnableStatus() 
    {
        return enableStatus;
    }
    public void setOrderSort(Long orderSort) 
    {
        this.orderSort = orderSort;
    }

    public Long getOrderSort() 
    {
        return orderSort;
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
            .append("accountId", getAccountId())
            .append("accountName", getAccountName())
            .append("accountParentId", getAccountParentId())
            .append("accountLevel", getAccountLevel())
            .append("accountScope", getAccountScope())
            .append("accountType", getAccountType())
            .append("accountSort", getAccountSort())
            .append("accountClass", getAccountClass())
            .append("accountDesc", getAccountDesc())
            .append("weight", getWeight())
            .append("icon", getIcon())
            .append("enableStatus", getEnableStatus())
            .append("orderSort", getOrderSort())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("bookId", getBookId())
            .append("bookName", getBookName())
            .append("userId", getUserId())
            .append("userName", getUserName())
            .toString();
    }
}
