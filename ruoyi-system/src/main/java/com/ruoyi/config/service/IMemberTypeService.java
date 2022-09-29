package com.ruoyi.config.service;

import java.util.List;
import com.ruoyi.config.domain.MemberType;

/**
 * 成员类型Service接口
 * 
 * @author metacoin
 * @date 2022-09-29
 */
public interface IMemberTypeService 
{
    /**
     * 查询成员类型
     * 
     * @param memberTypeId 成员类型主键
     * @return 成员类型
     */
    public MemberType selectMemberTypeByMemberTypeId(Long memberTypeId);

    /**
     * 查询成员类型列表
     * 
     * @param memberType 成员类型
     * @return 成员类型集合
     */
    public List<MemberType> selectMemberTypeList(MemberType memberType);

    /**
     * 新增成员类型
     * 
     * @param memberType 成员类型
     * @return 结果
     */
    public int insertMemberType(MemberType memberType);

    /**
     * 修改成员类型
     * 
     * @param memberType 成员类型
     * @return 结果
     */
    public int updateMemberType(MemberType memberType);

    /**
     * 批量删除成员类型
     * 
     * @param memberTypeIds 需要删除的成员类型主键集合
     * @return 结果
     */
    public int deleteMemberTypeByMemberTypeIds(Long[] memberTypeIds);

    /**
     * 删除成员类型信息
     * 
     * @param memberTypeId 成员类型主键
     * @return 结果
     */
    public int deleteMemberTypeByMemberTypeId(Long memberTypeId);

    /**
     * 获取所有成员类型列表
     *
     * @return 结果
     */
    public List<MemberType> selectMemberTypeAll();



}
