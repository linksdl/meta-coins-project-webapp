package com.ruoyi.config.mapper;

import java.util.List;
import com.ruoyi.config.domain.Member;

/**
 * 成员管理Mapper接口
 * 
 * @author metacoin
 * @date 2022-09-29
 */
public interface MemberMapper 
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
     * 删除成员管理
     * 
     * @param memberId 成员管理主键
     * @return 结果
     */
    public int deleteMemberByMemberId(Long memberId);

    /**
     * 批量删除成员管理
     * 
     * @param memberIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMemberByMemberIds(Long[] memberIds);

    /**
     * 获取所有成员管理列表
     *
     * @return 成员管理集合信息
     */
    public List<Member> selectMemberAll();
}
