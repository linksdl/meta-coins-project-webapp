package com.ruoyi.config.book.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.config.book.mapper.BookTypeMapper;
import com.ruoyi.config.book.domain.BookType;
import com.ruoyi.config.book.service.IBookTypeService;

import javax.annotation.Resource;

/**
 * 账本类型Service业务层处理
 *
 * @author metacoin
 * @date 2022-09-21
 */
@Service
public class BookTypeServiceImpl implements IBookTypeService
{
    @Resource
    private BookTypeMapper bookTypeMapper;

    /**
     * 查询账本类型
     *
     * @param bookTypeId 账本类型主键
     * @return 账本类型
     */
    @Override
    public BookType selectBookTypeByBookTypeId(Long bookTypeId)
    {
        return bookTypeMapper.selectBookTypeByBookTypeId(bookTypeId);
    }

    /**
     * 查询账本类型列表
     *
     * @param bookType 账本类型
     * @return 账本类型
     */
    @Override
    public List<BookType> selectBookTypeList(BookType bookType)
    {
        return bookTypeMapper.selectBookTypeList(bookType);
    }

    /**
     * 新增账本类型
     *
     * @param bookType 账本类型
     * @return 结果
     */
    @Override
    public int insertBookType(BookType bookType)
    {
        bookType.setCreateTime(DateUtils.getNowDate());
        bookType.setUpdateTime(DateUtils.getNowDate());
        return bookTypeMapper.insertBookType(bookType);
    }

    /**
     * 修改账本类型
     *
     * @param bookType 账本类型
     * @return 结果
     */
    @Override
    public int updateBookType(BookType bookType)
    {
        bookType.setUpdateTime(DateUtils.getNowDate());
        return bookTypeMapper.updateBookType(bookType);
    }

    /**
     * 批量删除账本类型
     *
     * @param bookTypeIds 需要删除的账本类型主键
     * @return 结果
     */
    @Override
    public int deleteBookTypeByBookTypeIds(Long[] bookTypeIds)
    {
        return bookTypeMapper.deleteBookTypeByBookTypeIds(bookTypeIds);
    }

    /**
     * 删除账本类型信息
     *
     * @param bookTypeId 账本类型主键
     * @return 结果
     */
    @Override
    public int deleteBookTypeByBookTypeId(Long bookTypeId)
    {
        return bookTypeMapper.deleteBookTypeByBookTypeId(bookTypeId);
    }
}
