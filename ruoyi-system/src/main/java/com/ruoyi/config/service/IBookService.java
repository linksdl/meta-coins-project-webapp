package com.ruoyi.config.service;

import java.util.List;
import com.ruoyi.config.domain.Book;

/**
 * 个人账本Service接口
 * 
 * @author metacoin
 * @date 2022-09-24
 */
public interface IBookService 
{
    /**
     * 查询个人账本
     * 
     * @param bookId 个人账本主键
     * @return 个人账本
     */
    public Book selectBookByBookId(Long bookId);

    /**
     * 查询个人账本列表
     * 
     * @param book 个人账本
     * @return 个人账本集合
     */
    public List<Book> selectBookList(Book book);

    /**
     * 新增个人账本
     * 
     * @param book 个人账本
     * @return 结果
     */
    public int insertBook(Book book);

    /**
     * 修改个人账本
     * 
     * @param book 个人账本
     * @return 结果
     */
    public int updateBook(Book book);

    /**
     * 批量删除个人账本
     * 
     * @param bookIds 需要删除的个人账本主键集合
     * @return 结果
     */
    public int deleteBookByBookIds(Long[] bookIds);

    /**
     * 删除个人账本信息
     * 
     * @param bookId 个人账本主键
     * @return 结果
     */
    public int deleteBookByBookId(Long bookId);

    /**
     * 获取所有个人账本列表
     *
     * @return 结果
     */
    public List<Book> selectBookAll(Book params);
}
