package com.ruoyi.config.service;

import java.util.List;
import com.ruoyi.config.domain.Enterprise;

/**
 * 商家管理Service接口
 * 
 * @author metacoin
 * @date 2022-10-04
 */
public interface IEnterpriseService 
{
    /**
     * 查询商家管理
     * 
     * @param enterpriseId 商家管理主键
     * @return 商家管理
     */
    public Enterprise selectEnterpriseByEnterpriseId(Long enterpriseId);

    /**
     * 查询商家管理列表
     * 
     * @param enterprise 商家管理
     * @return 商家管理集合
     */
    public List<Enterprise> selectEnterpriseList(Enterprise enterprise);

    /**
     * 新增商家管理
     * 
     * @param enterprise 商家管理
     * @return 结果
     */
    public int insertEnterprise(Enterprise enterprise);

    /**
     * 修改商家管理
     * 
     * @param enterprise 商家管理
     * @return 结果
     */
    public int updateEnterprise(Enterprise enterprise);

    /**
     * 批量删除商家管理
     * 
     * @param enterpriseIds 需要删除的商家管理主键集合
     * @return 结果
     */
    public int deleteEnterpriseByEnterpriseIds(Long[] enterpriseIds);

    /**
     * 删除商家管理信息
     * 
     * @param enterpriseId 商家管理主键
     * @return 结果
     */
    public int deleteEnterpriseByEnterpriseId(Long enterpriseId);

    /**
     * 获取所有商家管理列表
     *
     * @return 结果
     */
    public List<Enterprise> selectEnterpriseAll(Enterprise params);



}
