package com.ruoyi.web.controller.config;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.config.domain.Emotion;
import com.ruoyi.config.service.IEmotionService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 心情管理Controller
 * 
 * @author metacoin
 * @date 2022-10-02
 */
@RestController
@RequestMapping("/config/emotion")
public class EmotionController extends BaseController
{
    @Autowired
    private IEmotionService emotionService;

    /**
     * 分页查询心情管理列表
     */
    @PreAuthorize("@ss.hasPermi('config:emotion:list')")
    @GetMapping("/list")
    public TableDataInfo list(Emotion emotion)
    {
        startPage();
        List<Emotion> list = emotionService.selectEmotionList(emotion);
        return getDataTable(list);
    }

    /**
     * 导出心情管理列表
     */
    @PreAuthorize("@ss.hasPermi('config:emotion:export')")
    @Log(title = "心情管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Emotion emotion)
    {
        List<Emotion> list = emotionService.selectEmotionList(emotion);
        ExcelUtil<Emotion> util = new ExcelUtil<Emotion>(Emotion.class);
        util.exportExcel(response, list, "心情管理数据");
    }

    /**
     * 获取心情管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('config:emotion:query')")
    @GetMapping(value = "/{emotionId}")
    public AjaxResult getInfo(@PathVariable("emotionId") Long emotionId)
    {
        return AjaxResult.success(emotionService.selectEmotionByEmotionId(emotionId));
    }

    /**
     * 新增心情管理
     */
    @PreAuthorize("@ss.hasPermi('config:emotion:add')")
    @Log(title = "心情管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Emotion emotion)
    {
        emotion.setCreateBy(getUsername());
        emotion.setUpdateBy(getUsername());
        return toAjax(emotionService.insertEmotion(emotion));
    }

    /**
     * 修改心情管理
     */
    @PreAuthorize("@ss.hasPermi('config:emotion:edit')")
    @Log(title = "心情管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Emotion emotion)
    {
        emotion.setUpdateBy(getUsername());
        return toAjax(emotionService.updateEmotion(emotion));
    }

    /**
     * 删除心情管理
     */
    @PreAuthorize("@ss.hasPermi('config:emotion:remove')")
    @Log(title = "心情管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{emotionIds}")
    public AjaxResult remove(@PathVariable Long[] emotionIds)
    {
        return toAjax(emotionService.deleteEmotionByEmotionIds(emotionIds));
    }

    /**
     * 获取心情管理下拉框列表
     */
    @GetMapping("/select")
    public AjaxResult select()
    {
        List<Emotion> list = emotionService.selectEmotionAll();
        return AjaxResult.success(list);
    }

}
