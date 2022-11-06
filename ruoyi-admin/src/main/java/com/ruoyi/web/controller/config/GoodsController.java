package com.ruoyi.web.controller.config;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.config.domain.Book;
import com.ruoyi.config.service.IBookService;
import com.ruoyi.config.domain.GoodsType;
import com.ruoyi.config.service.IGoodsTypeService;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.config.domain.Goods;
import com.ruoyi.config.service.IGoodsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商品管理Controller
 * 
 * @author metacoin
 * @date 2022-10-04
 */
@RestController
@RequestMapping("/config/goods")
public class GoodsController extends BaseController
{
    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private IGoodsTypeService goodsTypeService;

    @Resource
    private IBookService bookService;

    @Resource
    private ISysUserService userService;

    /**
     * 分页查询商品管理列表
     */
    @PreAuthorize("@ss.hasPermi('config:goods:list')")
    @GetMapping("/list")
    public TableDataInfo list(Goods goods)
    {
        startPage();
        goods.setUserId(getUserId());
        List<Goods> list = goodsService.selectGoodsList(goods);
        return getDataTable(list);
    }

    /**
     * 导出商品管理列表
     */
    @PreAuthorize("@ss.hasPermi('config:goods:export')")
    @Log(title = "商品管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Goods goods)
    {
        List<Goods> list = goodsService.selectGoodsList(goods);
        ExcelUtil<Goods> util = new ExcelUtil<Goods>(Goods.class);
        util.exportExcel(response, list, "商品管理数据");
    }

    /**
     * 获取商品管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('config:goods:query')")
    @GetMapping(value = "/{goodsId}")
    public AjaxResult getInfo(@PathVariable("goodsId") Long goodsId)
    {
        return AjaxResult.success(goodsService.selectGoodsByGoodsId(goodsId));
    }

    /**
     * 新增商品管理
     */
    @PreAuthorize("@ss.hasPermi('config:goods:add')")
    @Log(title = "商品管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Goods goods)
    {
        GoodsType goodsType = goodsTypeService.selectGoodsTypeByGoodsTypeId(goods.getGoodsTypeId());
        goods.setGoodsTypeName(goodsType.getGoodsTypeName());

        Book param = new Book();
        param.setUserId(getUserId());
        param.setBookDefault(1);
        List<Book> books = bookService.selectBookList(param);
        goods.setCreateBy(getUsername());
        goods.setUpdateBy(getUsername());
        goods.setUserId(getUserId());
        goods.setUserName(userService.selectUserById(getUserId()).getNickName()+"("+getUsername()+")");
        if (books.size() != 1)
        {
            return AjaxResult.error("请选择一个默认的账本！！！");
        }
        goods.setBookId(books.size() == 1 ? books.get(0).getBookId() : null);
        goods.setBookName(books.size() == 1 ? books.get(0).getBookName() : null);
        return toAjax(goodsService.insertGoods(goods));
    }

    /**
     * 修改商品管理
     */
    @PreAuthorize("@ss.hasPermi('config:goods:edit')")
    @Log(title = "商品管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Goods goods)
    {
        GoodsType goodsType = goodsTypeService.selectGoodsTypeByGoodsTypeId(goods.getGoodsTypeId());
        goods.setGoodsTypeName(goodsType.getGoodsTypeName());
        goods.setUpdateBy(getUsername());
        goods.setUserId(getUserId());
        goods.setUserName(userService.selectUserById(getUserId()).getNickName()+"("+getUsername()+")");
        Book param = new Book();
        param.setUserId(getUserId());
        param.setBookDefault(1);
        List<Book> books = bookService.selectBookList(param);
        if (books.size() != 1)
        {
            return AjaxResult.error("请选择一个默认的账本！！！");
        }
        goods.setBookId(books.size() == 1 ? books.get(0).getBookId() : null);
        goods.setBookName(books.size() == 1 ? books.get(0).getBookName() : null);
        return toAjax(goodsService.updateGoods(goods));
    }

    /**
     * 删除商品管理
     */
    @PreAuthorize("@ss.hasPermi('config:goods:remove')")
    @Log(title = "商品管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{goodsIds}")
    public AjaxResult remove(@PathVariable Long[] goodsIds)
    {
        return toAjax(goodsService.deleteGoodsByGoodsIds(goodsIds));
    }

    /**
     * 获取商品管理下拉框列表
     */
    @GetMapping("/select")
    public AjaxResult select(Goods params)
    {
        List<Goods> list = goodsService.selectGoodsAll();
        return AjaxResult.success(list);
    }

}
