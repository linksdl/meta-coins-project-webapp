package com.ruoyi.config.domain;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import javax.persistence.*;

/**
 * 城市对象 config_city
 * 
 * @author metacoin
 * @date 2022-09-29
 */
@Table(name="config_city")
@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class City extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="city_id")
    private Long cityId;

    /** 中文 */
    @Excel(name = "中文")
    @Column(name="city_cname")
    private String cityCname;

    /** 英文 */
    @Excel(name = "英文")
    @Column(name="city_ename")
    private String cityEname;

    /** 描述 */
    @Excel(name = "描述")
    @Column(name="city_desc")
    private String cityDesc;

    /** 国家 */
    @Excel(name = "国家")
    @Column(name="city_country")
    private String cityCountry;

    /** 省份 */
    @Excel(name = "省份")
    @Column(name="city_province")
    private String cityProvince;

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

    public void setCityId(Long cityId) 
    {
        this.cityId = cityId;
    }

    public Long getCityId() 
    {
        return cityId;
    }
    public void setCityCname(String cityCname) 
    {
        this.cityCname = cityCname;
    }

    public String getCityCname() 
    {
        return cityCname;
    }
    public void setCityEname(String cityEname) 
    {
        this.cityEname = cityEname;
    }

    public String getCityEname() 
    {
        return cityEname;
    }
    public void setCityDesc(String cityDesc) 
    {
        this.cityDesc = cityDesc;
    }

    public String getCityDesc() 
    {
        return cityDesc;
    }
    public void setCityCountry(String cityCountry) 
    {
        this.cityCountry = cityCountry;
    }

    public String getCityCountry() 
    {
        return cityCountry;
    }
    public void setCityProvince(String cityProvince)
    {
        this.cityProvince = cityProvince;
    }

    public String getCityProvince()
    {
        return cityProvince;
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
            .append("cityId", getCityId())
            .append("cityCname", getCityCname())
            .append("cityEname", getCityEname())
            .append("cityDesc", getCityDesc())
            .append("cityCountry", getCityCountry())
            .append("cityProvince", getCityProvince())
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
