package com.ruoyi.config.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.config.mapper.EnterpriseMapper;
import com.ruoyi.config.domain.Enterprise;
import com.ruoyi.config.service.IEnterpriseService;

/**
 * 商家管理Service业务层处理
 * 
 * @author metacoin
 * @date 2022-10-04
 */
@Service
public class EnterpriseServiceImpl implements IEnterpriseService 
{
    @Autowired
    private EnterpriseMapper enterpriseMapper;

    /**
     * 查询商家管理
     * 
     * @param enterpriseId 商家管理主键
     * @return 商家管理
     */
    @Override
    public Enterprise selectEnterpriseByEnterpriseId(Long enterpriseId)
    {
        return enterpriseMapper.selectEnterpriseByEnterpriseId(enterpriseId);
    }

    /**
     * 查询商家管理列表
     * 
     * @param enterprise 商家管理
     * @return 商家管理
     */
    @Override
    public List<Enterprise> selectEnterpriseList(Enterprise enterprise)
    {
        return enterpriseMapper.selectEnterpriseList(enterprise);
    }

    /**
     * 新增商家管理
     * 
     * @param enterprise 商家管理
     * @return 结果
     */
    @Override
    public int insertEnterprise(Enterprise enterprise)
    {
        enterprise.setCreateTime(DateUtils.getNowDate());
        enterprise.setUpdateTime(DateUtils.getNowDate());
        return enterpriseMapper.insertEnterprise(enterprise);
    }

    /**
     * 修改商家管理
     * 
     * @param enterprise 商家管理
     * @return 结果
     */
    @Override
    public int updateEnterprise(Enterprise enterprise)
    {
        enterprise.setUpdateTime(DateUtils.getNowDate());
        return enterpriseMapper.updateEnterprise(enterprise);
    }

    /**
     * 批量删除商家管理
     * 
     * @param enterpriseIds 需要删除的商家管理主键
     * @return 结果
     */
    @Override
    public int deleteEnterpriseByEnterpriseIds(Long[] enterpriseIds)
    {
        return enterpriseMapper.deleteEnterpriseByEnterpriseIds(enterpriseIds);
    }

    /**
     * 删除商家管理信息
     * 
     * @param enterpriseId 商家管理主键
     * @return 结果
     */
    @Override
    public int deleteEnterpriseByEnterpriseId(Long enterpriseId)
    {
        return enterpriseMapper.deleteEnterpriseByEnterpriseId(enterpriseId);
    }

    @Override
    public List<Enterprise> selectEnterpriseAll(Enterprise params) {
        return enterpriseMapper.selectEnterpriseAll(params);
    }
}
