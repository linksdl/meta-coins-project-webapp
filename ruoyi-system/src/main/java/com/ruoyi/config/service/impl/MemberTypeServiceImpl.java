package com.ruoyi.config.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.stereotype.Service;
import com.ruoyi.config.mapper.MemberTypeMapper;
import com.ruoyi.config.domain.MemberType;
import com.ruoyi.config.service.IMemberTypeService;

import javax.annotation.Resource;

/**
 * 成员类型Service业务层处理
 * 
 * @author metacoin
 * @date 2022-09-29
 */
@Service
public class MemberTypeServiceImpl implements IMemberTypeService 
{
    @Resource
    private MemberTypeMapper memberTypeMapper;

    /**
     * 查询成员类型
     * 
     * @param memberTypeId 成员类型主键
     * @return 成员类型
     */
    @Override
    public MemberType selectMemberTypeByMemberTypeId(Long memberTypeId)
    {
        return memberTypeMapper.selectMemberTypeByMemberTypeId(memberTypeId);
    }

    /**
     * 查询成员类型列表
     * 
     * @param memberType 成员类型
     * @return 成员类型
     */
    @Override
    public List<MemberType> selectMemberTypeList(MemberType memberType)
    {
        return memberTypeMapper.selectMemberTypeList(memberType);
    }

    /**
     * 新增成员类型
     * 
     * @param memberType 成员类型
     * @return 结果
     */
    @Override
    public int insertMemberType(MemberType memberType)
    {
        memberType.setCreateTime(DateUtils.getNowDate());
        memberType.setUpdateTime(DateUtils.getNowDate());
        return memberTypeMapper.insertMemberType(memberType);
    }

    /**
     * 修改成员类型
     * 
     * @param memberType 成员类型
     * @return 结果
     */
    @Override
    public int updateMemberType(MemberType memberType)
    {
        memberType.setUpdateTime(DateUtils.getNowDate());
        return memberTypeMapper.updateMemberType(memberType);
    }

    /**
     * 批量删除成员类型
     * 
     * @param memberTypeIds 需要删除的成员类型主键
     * @return 结果
     */
    @Override
    public int deleteMemberTypeByMemberTypeIds(Long[] memberTypeIds)
    {
        return memberTypeMapper.deleteMemberTypeByMemberTypeIds(memberTypeIds);
    }

    /**
     * 删除成员类型信息
     * 
     * @param memberTypeId 成员类型主键
     * @return 结果
     */
    @Override
    public int deleteMemberTypeByMemberTypeId(Long memberTypeId)
    {
        return memberTypeMapper.deleteMemberTypeByMemberTypeId(memberTypeId);
    }

    @Override
    public List<MemberType> selectMemberTypeAll() {
        return memberTypeMapper.selectMemberTypeAll();
    }
}
