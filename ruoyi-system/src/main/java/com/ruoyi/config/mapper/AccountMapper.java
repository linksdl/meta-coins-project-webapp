package com.ruoyi.config.mapper;

import java.util.List;
import com.ruoyi.config.domain.Account;

/**
 * 账户管理Mapper接口
 * 
 * @author metacoin
 * @date 2022-10-04
 */
public interface AccountMapper 
{
    /**
     * 查询账户管理
     * 
     * @param accountId 账户管理主键
     * @return 账户管理
     */
    public Account selectAccountByAccountId(Long accountId);

    /**
     * 查询账户管理列表
     * 
     * @param account 账户管理
     * @return 账户管理集合
     */
    public List<Account> selectAccountList(Account account);

    /**
     * 新增账户管理
     * 
     * @param account 账户管理
     * @return 结果
     */
    public int insertAccount(Account account);

    /**
     * 修改账户管理
     * 
     * @param account 账户管理
     * @return 结果
     */
    public int updateAccount(Account account);

    /**
     * 删除账户管理
     * 
     * @param accountId 账户管理主键
     * @return 结果
     */
    public int deleteAccountByAccountId(Long accountId);

    /**
     * 批量删除账户管理
     * 
     * @param accountIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAccountByAccountIds(Long[] accountIds);

    /**
     * 获取所有账户管理列表
     *
     * @return 账户管理集合信息
     */
    public List<Account> selectAccountAll();
}
