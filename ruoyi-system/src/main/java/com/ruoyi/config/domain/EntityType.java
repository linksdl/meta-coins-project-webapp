package com.ruoyi.config.domain;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import javax.persistence.*;
import javax.persistence.Entity;

/**
 * 商家类型对象 config_entity_type
 * 
 * @author metacoin
 * @date 2022-10-04
 */
@Table(name="config_entity_type")
@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntityType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="entity_type_id")
    private Long entityTypeId;

    /** 商家类型 */
    @Excel(name = "商家类型")
    @Column(name="entity_type_name")
    private String entityTypeName;

    /** 描述 */
    @Excel(name = "描述")
    @Column(name="entity_type_desc")
    private String entityTypeDesc;

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
    public void setEntityTypeDesc(String entityTypeDesc) 
    {
        this.entityTypeDesc = entityTypeDesc;
    }

    public String getEntityTypeDesc() 
    {
        return entityTypeDesc;
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
            .append("entityTypeId", getEntityTypeId())
            .append("entityTypeName", getEntityTypeName())
            .append("entityTypeDesc", getEntityTypeDesc())
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
