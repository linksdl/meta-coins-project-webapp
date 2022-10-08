package com.ruoyi.config.domain;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import javax.persistence.*;

/**
 * 个人账本对象 config_book
 * 
 * @author metacoin
 * @date 2022-09-24
 */
@Table(name="config_book")
@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 账本ID */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="book_id")
    private Long bookId;

    /** 账本名称 */
    @Excel(name = "账本名称")
    @Column(name="book_name")
    private String bookName;

    /** 账本类型 */
    @Excel(name = "账本类型")
    @Column(name="book_type_name")
    private String bookTypeName;

    /** 类型ID */
    @Excel(name = "类型ID")
    @Column(name="book_type_id")
    private Long bookTypeId;

    /**  描述 */
    @Excel(name = " 描述")
    @Column(name="book_desc")
    private String bookDesc;

    /** 是否默认 */
    @Excel(name = "是否默认")
    @Column(name="book_default")
    private Integer bookDefault;

    /** 用户ID */
    @Column(name="user_id")
    private Long userId;

    /** 用户名 */
    @Excel(name = "用户名")
    private String userName;

    /** 权重 */
    @Column(name="weight")
    private Long weight;

    /** 排序 */
    @Column(name="order_sort")
    private Long orderSort;

    /** 图标 */
    @Excel(name = "图标")
    @Column(name="icon")
    private String icon;

    /** 是否可用 */
    @Excel(name = "是否可用")
    @Column(name="enable_status")
    private Integer enableStatus;


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
    public void setBookTypeName(String bookTypeName)
    {
        this.bookTypeName = bookTypeName;
    }

    public String getBookTypeName()
    {
        return bookTypeName;
    }
    public void setBookTypeId(Long bookTypeId)
    {
        this.bookTypeId = bookTypeId;
    }

    public Long getBookTypeId()
    {
        return bookTypeId;
    }
    public void setBookDesc(String bookDesc) 
    {
        this.bookDesc = bookDesc;
    }

    public String getBookDesc() 
    {
        return bookDesc;
    }
    public void setBookDefault(Integer bookDefault)
    {
        this.bookDefault = bookDefault;
    }

    public Integer getBookDefault()
    {
        return bookDefault;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
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
    public void setEnableStatus(Integer enableStatus)
    {
        this.enableStatus = enableStatus;
    }

    public Integer getEnableStatus()
    {
        return enableStatus;
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
            .append("bookId", getBookId())
            .append("bookName", getBookName())
            .append("bookTypeName", getBookTypeName())
            .append("bookTypeId", getBookTypeId())
            .append("bookDesc", getBookDesc())
            .append("bookDefault", getBookDefault())
            .append("userId", getUserId())
            .append("weight", getWeight())
            .append("orderSort", getOrderSort())
            .append("icon", getIcon())
            .append("enableStatus", getEnableStatus())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}
