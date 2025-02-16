package com.guji.book.service;

import java.util.List;
import com.guji.book.domain.AncientBookRecord;

/**
 * 阅读记录Service接口
 * 
 * @author ruoyi
 * @date 2025-02-16
 */
public interface IAncientBookRecordService 
{
    /**
     * 查询阅读记录
     * 
     * @param recordId 阅读记录主键
     * @return 阅读记录
     */
    public AncientBookRecord selectAncientBookRecordByRecordId(Long recordId);

//    添加当前阅读记录，有了就更新
    public Boolean insertByBookRecordOnUpdate(AncientBookRecord ancientBookRecord);

    /**
     * 查询阅读记录列表
     * 
     * @param ancientBookRecord 阅读记录
     * @return 阅读记录集合
     */
    public List<AncientBookRecord> selectAncientBookRecordList(AncientBookRecord ancientBookRecord);
    public AncientBookRecord selectAncientBookBookmarksByUserIdAndAncientBookId(Long userId, Long ancientBookId);

    /**
     * 新增阅读记录
     * 
     * @param ancientBookRecord 阅读记录
     * @return 结果
     */
    public int insertAncientBookRecord(AncientBookRecord ancientBookRecord);

    /**
     * 修改阅读记录
     * 
     * @param ancientBookRecord 阅读记录
     * @return 结果
     */
    public int updateAncientBookRecord(AncientBookRecord ancientBookRecord);

    /**
     * 批量删除阅读记录
     * 
     * @param recordIds 需要删除的阅读记录主键集合
     * @return 结果
     */
    public int deleteAncientBookRecordByRecordIds(Long[] recordIds);

    /**
     * 删除阅读记录信息
     * 
     * @param recordId 阅读记录主键
     * @return 结果
     */
    public int deleteAncientBookRecordByRecordId(Long recordId);
}
