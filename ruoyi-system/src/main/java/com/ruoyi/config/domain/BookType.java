package com.ruoyi.config.domain;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import javax.persistence.*;

/**
 * 账本类型对象 config_book_type
 *
 * @author metacoin
 * @date 2022-09-21
 */
@Table(name="config_book_type")
@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 账本类型ID */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="book_type_id")
    private Long bookTypeId;

    /** 图标 */
    @Excel(name = "图标")
    private String icon;

    /** 权重 */
    @Excel(name = "权重")
    private Long weight;

    /** 描述 */
    @Excel(name = "描述")
    @Column(name="book_type_desc")
    private String bookTypeDesc;

    /** 账本类型名称 */
    @Excel(name = "账本类型名称")
    @Column(name="book_type_name")
    private String bookTypeName;

    /** 排序 */
    @Excel(name = "排序")
    private Long orderSort;

    /** 数据是否可用 */
    @Excel(name = "数据是否可用")
    private Integer enableStatus;

    public void setBookTypeId(Long bookTypeId)
    {
        this.bookTypeId = bookTypeId;
    }

    public Long getBookTypeId()
    {
        return bookTypeId;
    }
    public void setIcon(String icon)
    {
        this.icon = icon;
    }

    public String getIcon()
    {
        return icon;
    }
    public void setWeight(Long weight)
    {
        this.weight = weight;
    }

    public Long getWeight()
    {
        return weight;
    }
    public void setBookTypeDesc(String bookTypeDesc)
    {
        this.bookTypeDesc = bookTypeDesc;
    }

    public String getBookTypeDesc()
    {
        return bookTypeDesc;
    }
    public void setBookTypeName(String bookTypeName)
    {
        this.bookTypeName = bookTypeName;
    }

    public String getBookTypeName()
    {
        return bookTypeName;
    }
    public void setOrderSort(Long orderSort)
    {
        this.orderSort = orderSort;
    }

    public Long getOrderSort()
    {
        return orderSort;
    }
    public void setEnableStatus(Integer enableStatus)
    {
        this.enableStatus = enableStatus;
    }

    public Integer getEnableStatus()
    {
        return enableStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("bookTypeId", getBookTypeId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("icon", getIcon())
            .append("remark", getRemark())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("weight", getWeight())
            .append("bookTypeDesc", getBookTypeDesc())
            .append("bookTypeName", getBookTypeName())
            .append("orderSort", getOrderSort())
            .append("enableStatus", getEnableStatus())
            .toString();
    }
}
