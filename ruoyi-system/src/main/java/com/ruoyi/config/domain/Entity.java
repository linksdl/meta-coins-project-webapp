package com.ruoyi.config.domain;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import javax.persistence.*;

/**
 * 商家管理对象 config_entity
 * 
 * @author metacoin
 * @date 2022-10-04
 */
@Table(name="config_entity")
@javax.persistence.Entity
@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Entity extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="entity_id")
    private Long entityId;

    /** 商家名称 */
    @Excel(name = "商家名称")
    @Column(name="entity_name")
    private String entityName;

    /** 类型ID */
    @Excel(name = "类型ID")
    @Column(name="entity_type_id")
    private Long entityTypeId;

    /** 类型名称 */
    @Excel(name = "类型名称")
    @Column(name="entity_type_name")
    private String entityTypeName;

    /** 商家地址 */
    @Excel(name = "商家地址")
    @Column(name="entity_address")
    private String entityAddress;

    /** 描述 */
    @Excel(name = "描述")
    @Column(name="entity_desc")
    private String entityDesc;

    /** 图片 */
    @Excel(name = "图片")
    @Column(name="entity_imgs")
    private String entityImgs;

    /** 地图地址 */
    @Excel(name = "地图地址")
    @Column(name="entity_map_location")
    private String entityMapLocation;

    /** 功能范围 */
    @Excel(name = "功能范围")
    @Column(name="entity_scope")
    private String entityScope;

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

    /** 账本ID */
    @Column(name="book_id")
    private Long bookId;

    /** 账本名称 */
    @Column(name="book_name")
    private String bookName;

    /** 用户ID */
    @Column(name="user_id")
    private Long userId;

    /** 用户名 */
    @Column(name="user_name")
    private String userName;

    public void setEntityId(Long entityId) 
    {
        this.entityId = entityId;
    }

    public Long getEntityId() 
    {
        return entityId;
    }
    public void setEntityName(String entityName) 
    {
        this.entityName = entityName;
    }

    public String getEntityName() 
    {
        return entityName;
    }
    public void setEntityAddress(String entityAddress) 
    {
        this.entityAddress = entityAddress;
    }

    public String getEntityAddress() 
    {
        return entityAddress;
    }
    public void setEntityDesc(String entityDesc) 
    {
        this.entityDesc = entityDesc;
    }

    public String getEntityDesc() 
    {
        return entityDesc;
    }
    public void setEntityImgs(String entityImgs) 
    {
        this.entityImgs = entityImgs;
    }

    public String getEntityImgs() 
    {
        return entityImgs;
    }
    public void setEntityMapLocation(String entityMapLocation) 
    {
        this.entityMapLocation = entityMapLocation;
    }

    public String getEntityMapLocation() 
    {
        return entityMapLocation;
    }
    public void setEntityScope(String entityScope) 
    {
        this.entityScope = entityScope;
    }

    public String getEntityScope() 
    {
        return entityScope;
    }
    public void setEntityTypeId(Long entityTypeId) 
    {
        this.entityTypeId = entityTypeId;
    }

    public Long getEntityTypeId() 
    {
        return entityTypeId;
    }
    public void setEntityTypeName(String entityTypeName) 
    {
        this.entityTypeName = entityTypeName;
    }

    public String getEntityTypeName() 
    {
        return entityTypeName;
    }
    public void setEnableStatus(Long enableStatus) 
    {
        this.enableStatus = enableStatus;
    }

    public Long getEnableStatus() 
    {
        return enableStatus;
    }
    public void setIcon(String icon) 
    {
        this.icon = icon;
    }

    public String getIcon() 
    {
        return icon;
    }
    public void setOrderSort(Long orderSort) 
    {
        this.orderSort = orderSort;
    }

    public Long getOrderSort() 
    {
        return orderSort;
    }
    public void setWeight(Long weight) 
    {
        this.weight = weight;
    }

    public Long getWeight() 
    {
        return weight;
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
            .append("entityId", getEntityId())
            .append("entityName", getEntityName())
            .append("entityAddress", getEntityAddress())
            .append("entityDesc", getEntityDesc())
            .append("entityImgs", getEntityImgs())
            .append("entityMapLocation", getEntityMapLocation())
            .append("entityScope", getEntityScope())
            .append("entityTypeId", getEntityTypeId())
            .append("entityTypeName", getEntityTypeName())
            .append("enableStatus", getEnableStatus())
            .append("icon", getIcon())
            .append("orderSort", getOrderSort())
            .append("remark", getRemark())
            .append("weight", getWeight())
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
