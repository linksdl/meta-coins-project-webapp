package com.ruoyi.config.service;

import java.util.List;
import com.ruoyi.config.domain.BookType;

/**
 * 账本类型Service接口
 * 
 * @author metacoin
 * @date 2022-10-04
 */
public interface IBookTypeService 
{
    /**
     * 查询账本类型
     * 
     * @param bookTypeId 账本类型主键
     * @return 账本类型
     */
    public BookType selectBookTypeByBookTypeId(Long bookTypeId);

    /**
     * 查询账本类型列表
     * 
     * @param bookType 账本类型
     * @return 账本类型集合
     */
    public List<BookType> selectBookTypeList(BookType bookType);

    /**
     * 新增账本类型
     * 
     * @param bookType 账本类型
     * @return 结果
     */
    public int insertBookType(BookType bookType);

    /**
     * 修改账本类型
     * 
     * @param bookType 账本类型
     * @return 结果
     */
    public int updateBookType(BookType bookType);

    /**
     * 批量删除账本类型
     * 
     * @param bookTypeIds 需要删除的账本类型主键集合
     * @return 结果
     */
    public int deleteBookTypeByBookTypeIds(Long[] bookTypeIds);

    /**
     * 删除账本类型信息
     * 
     * @param bookTypeId 账本类型主键
     * @return 结果
     */
    public int deleteBookTypeByBookTypeId(Long bookTypeId);

    /**
     * 获取所有账本类型列表
     *
     * @return 结果
     */
    public List<BookType> selectBookTypeAll(BookType params);



}
