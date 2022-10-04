package com.ruoyi.config.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.config.mapper.AccountMapper;
import com.ruoyi.config.domain.Account;
import com.ruoyi.config.service.IAccountService;

/**
 * 账户管理Service业务层处理
 * 
 * @author metacoin
 * @date 2022-10-04
 */
@Service
public class AccountServiceImpl implements IAccountService 
{
    @Autowired
    private AccountMapper accountMapper;

    /**
     * 查询账户管理
     * 
     * @param accountId 账户管理主键
     * @return 账户管理
     */
    @Override
    public Account selectAccountByAccountId(Long accountId)
    {
        return accountMapper.selectAccountByAccountId(accountId);
    }

    /**
     * 查询账户管理列表
     * 
     * @param account 账户管理
     * @return 账户管理
     */
    @Override
    public List<Account> selectAccountList(Account account)
    {
        return accountMapper.selectAccountList(account);
    }

    /**
     * 新增账户管理
     * 
     * @param account 账户管理
     * @return 结果
     */
    @Override
    public int insertAccount(Account account)
    {
        account.setCreateTime(DateUtils.getNowDate());
        account.setUpdateTime(DateUtils.getNowDate());
        return accountMapper.insertAccount(account);
    }

    /**
     * 修改账户管理
     * 
     * @param account 账户管理
     * @return 结果
     */
    @Override
    public int updateAccount(Account account)
    {
        account.setUpdateTime(DateUtils.getNowDate());
        return accountMapper.updateAccount(account);
    }

    /**
     * 批量删除账户管理
     * 
     * @param accountIds 需要删除的账户管理主键
     * @return 结果
     */
    @Override
    public int deleteAccountByAccountIds(Long[] accountIds)
    {
        return accountMapper.deleteAccountByAccountIds(accountIds);
    }

    /**
     * 删除账户管理信息
     * 
     * @param accountId 账户管理主键
     * @return 结果
     */
    @Override
    public int deleteAccountByAccountId(Long accountId)
    {
        return accountMapper.deleteAccountByAccountId(accountId);
    }

    @Override
    public List<Account> selectAccountAll() {
        return accountMapper.selectAccountAll();
    }
}
