package com.ruoyi.config.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.stereotype.Service;
import com.ruoyi.config.mapper.BookMapper;
import com.ruoyi.config.domain.Book;
import com.ruoyi.config.service.IBookService;

import javax.annotation.Resource;

/**
 * 个人账本Service业务层处理
 * 
 * @author metacoin
 * @date 2022-09-24
 */
@Service
public class BookServiceImpl implements IBookService 
{
    @Resource
    private BookMapper bookMapper;

    /**
     * 查询个人账本
     * 
     * @param bookId 个人账本主键
     * @return 个人账本
     */
    @Override
    public Book selectBookByBookId(Long bookId)
    {
        return bookMapper.selectBookByBookId(bookId);
    }

    /**
     * 查询个人账本列表
     * 
     * @param book 个人账本
     * @return 个人账本
     */
    @Override
    public List<Book> selectBookList(Book book)
    {
        return bookMapper.selectBookList(book);
    }

    /**
     * 新增个人账本
     * 
     * @param book 个人账本
     * @return 结果
     */
    @Override
    public int insertBook(Book book)
    {
        book.setCreateTime(DateUtils.getNowDate());
        book.setUpdateTime(DateUtils.getNowDate());
        return bookMapper.insertBook(book);
    }

    /**
     * 修改个人账本
     * 
     * @param book 个人账本
     * @return 结果
     */
    @Override
    public int updateBook(Book book)
    {
        book.setUpdateTime(DateUtils.getNowDate());
        return bookMapper.updateBook(book);
    }

    /**
     * 批量删除个人账本
     * 
     * @param bookIds 需要删除的个人账本主键
     * @return 结果
     */
    @Override
    public int deleteBookByBookIds(Long[] bookIds)
    {
        return bookMapper.deleteBookByBookIds(bookIds);
    }

    /**
     * 删除个人账本信息
     * 
     * @param bookId 个人账本主键
     * @return 结果
     */
    @Override
    public int deleteBookByBookId(Long bookId)
    {
        return bookMapper.deleteBookByBookId(bookId);
    }
}
