package com.ruoyi.config.service;

import java.util.List;
import com.ruoyi.config.domain.Member;

/**
 * 成员管理Service接口
 * 
 * @author metacoin
 * @date 2022-09-29
 */
public interface IMemberService 
{
    /**
     * 查询成员管理
     * 
     * @param memberId 成员管理主键
     * @return 成员管理
     */
    public Member selectMemberByMemberId(Long memberId);

    /**
     * 查询成员管理列表
     * 
     * @param member 成员管理
     * @return 成员管理集合
     */
    public List<Member> selectMemberList(Member member);

    /**
     * 新增成员管理
     * 
     * @param member 成员管理
     * @return 结果
     */
    public int insertMember(Member member);

    /**
     * 修改成员管理
     * 
     * @param member 成员管理
     * @return 结果
     */
    public int updateMember(Member member);

    /**
     * 批量删除成员管理
     * 
     * @param memberIds 需要删除的成员管理主键集合
     * @return 结果
     */
    public int deleteMemberByMemberIds(Long[] memberIds);

    /**
     * 删除成员管理信息
     * 
     * @param memberId 成员管理主键
     * @return 结果
     */
    public int deleteMemberByMemberId(Long memberId);

    /**
     * 获取所有成员管理列表
     *
     * @return 结果
     */
    public List<Member> selectMemberAll(Member params);



}
