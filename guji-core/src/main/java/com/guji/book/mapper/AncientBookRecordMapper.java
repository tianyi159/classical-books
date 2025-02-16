package com.guji.book.mapper;

import java.util.List;
import com.guji.book.domain.AncientBookRecord;
import org.apache.ibatis.annotations.Param;

/**
 * 阅读记录Mapper接口
 * 
 * @author ruoyi
 * @date 2025-02-16
 */
public interface AncientBookRecordMapper 
{
    /**
     * 查询阅读记录
     * 
     * @param recordId 阅读记录主键
     * @return 阅读记录
     */
    public AncientBookRecord selectAncientBookRecordByRecordId(Long recordId);

    /**
     * 查询阅读记录列表
     * 
     * @param ancientBookRecord 阅读记录
     * @return 阅读记录集合
     */
    public List<AncientBookRecord> selectAncientBookRecordList(AncientBookRecord ancientBookRecord);

    /**
     * 新增阅读记录
     * 
     * @param ancientBookRecord 阅读记录
     * @return 结果
     */
    public int insertAncientBookRecord(AncientBookRecord ancientBookRecord);

    public AncientBookRecord selectAncientBookBookmarksByUserIdAndAncientBookId(@Param("userId") Long userId,@Param("ancientBookId")  Long ancientBookId);

    /**
     * 修改阅读记录
     * 
     * @param ancientBookRecord 阅读记录
     * @return 结果
     */
    public int updateAncientBookRecord(AncientBookRecord ancientBookRecord);

    /**
     * 删除阅读记录
     * 
     * @param recordId 阅读记录主键
     * @return 结果
     */
    public int deleteAncientBookRecordByRecordId(Long recordId);

    /**
     * 批量删除阅读记录
     * 
     * @param recordIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAncientBookRecordByRecordIds(Long[] recordIds);
}
