package com.ruoyi.config.domain;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.*;

/**
 * 商品管理对象 config_goods
 * 
 * @author metacoin
 * @date 2022-10-04
 */
@Table(name="config_goods")
@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Goods extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 商品ID */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="goods_id")
    private Long goodsId;

    /** 商品名称 */
    @Excel(name = "商品名称")
    @Column(name="goods_cname")
    private String goodsCname;

    /** 类型ID */
    @Excel(name = "类型ID")
    @Column(name="goods_type_id")
    private Long goodsTypeId;

    /** 类型名称 */
    @Excel(name = "类型名称")
    @Column(name="goods_type_name")
    private String goodsTypeName;

    /** 英文名称 */
    @Excel(name = "英文名称")
    @Column(name="goods_ename")
    private String goodsEname;

    /** 价格 */
    @Excel(name = "价格")
    @Column(name="goods_price")
    private Double goodsPrice;

    /** 描述 */
    @Excel(name = "描述")
    @Column(name="goods_desc")
    private String goodsDesc;



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
    public void setGoodsDesc(String goodsDesc) 
    {
        this.goodsDesc = goodsDesc;
    }

    public String getGoodsDesc() 
    {
        return goodsDesc;
    }
    public void setGoodsTypeId(Long goodsTypeId)
    {
        this.goodsTypeId = goodsTypeId;
    }

    public Long getGoodsTypeId()
    {
        return goodsTypeId;
    }
    public void setGoodsTypeName(String goodsTypeName) 
    {
        this.goodsTypeName = goodsTypeName;
    }

    public String getGoodsTypeName() 
    {
        return goodsTypeName;
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
            .append("goodsId", getGoodsId())
            .append("goodsCname", getGoodsCname())
            .append("goodsEname", getGoodsEname())
            .append("goodsPrice", getGoodsPrice())
            .append("goodsDesc", getGoodsDesc())
            .append("goodsTypeId", getGoodsTypeId())
            .append("goodsTypeName", getGoodsTypeName())
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
