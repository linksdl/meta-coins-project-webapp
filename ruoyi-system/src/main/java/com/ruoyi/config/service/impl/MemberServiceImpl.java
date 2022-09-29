package com.ruoyi.config.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.config.mapper.MemberMapper;
import com.ruoyi.config.domain.Member;
import com.ruoyi.config.service.IMemberService;

import javax.annotation.Resource;

/**
 * 成员管理Service业务层处理
 * 
 * @author metacoin
 * @date 2022-09-29
 */
@Service
public class MemberServiceImpl implements IMemberService 
{
    @Resource
    private MemberMapper memberMapper;

    /**
     * 查询成员管理
     * 
     * @param memberId 成员管理主键
     * @return 成员管理
     */
    @Override
    public Member selectMemberByMemberId(Long memberId)
    {
        return memberMapper.selectMemberByMemberId(memberId);
    }

    /**
     * 查询成员管理列表
     * 
     * @param member 成员管理
     * @return 成员管理
     */
    @Override
    public List<Member> selectMemberList(Member member)
    {
        return memberMapper.selectMemberList(member);
    }

    /**
     * 新增成员管理
     * 
     * @param member 成员管理
     * @return 结果
     */
    @Override
    public int insertMember(Member member)
    {
        member.setCreateTime(DateUtils.getNowDate());
        member.setUpdateTime(DateUtils.getNowDate());
        return memberMapper.insertMember(member);
    }

    /**
     * 修改成员管理
     * 
     * @param member 成员管理
     * @return 结果
     */
    @Override
    public int updateMember(Member member)
    {
        member.setUpdateTime(DateUtils.getNowDate());
        return memberMapper.updateMember(member);
    }

    /**
     * 批量删除成员管理
     * 
     * @param memberIds 需要删除的成员管理主键
     * @return 结果
     */
    @Override
    public int deleteMemberByMemberIds(Long[] memberIds)
    {
        return memberMapper.deleteMemberByMemberIds(memberIds);
    }

    /**
     * 删除成员管理信息
     * 
     * @param memberId 成员管理主键
     * @return 结果
     */
    @Override
    public int deleteMemberByMemberId(Long memberId)
    {
        return memberMapper.deleteMemberByMemberId(memberId);
    }

    @Override
    public List<Member> selectMemberAll() {
        return memberMapper.selectMemberAll();
    }
}
