package com.guji.book.service.impl;

import java.util.Date;
import java.util.List;

import com.guji.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.guji.book.mapper.AncientBookRecordMapper;
import com.guji.book.domain.AncientBookRecord;
import com.guji.book.service.IAncientBookRecordService;

/**
 * 阅读记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-02-16
 */
@Service
public class AncientBookRecordServiceImpl implements IAncientBookRecordService 
{
    @Autowired
    private AncientBookRecordMapper ancientBookRecordMapper;

    /**
     * 查询阅读记录
     * 
     * @param recordId 阅读记录主键
     * @return 阅读记录
     */
    @Override
    public AncientBookRecord selectAncientBookRecordByRecordId(Long recordId)
    {
        return ancientBookRecordMapper.selectAncientBookRecordByRecordId(recordId);
    }

    @Override
    public Boolean insertByBookRecordOnUpdate(AncientBookRecord ancientBookRecord) {
        System.out.println(ancientBookRecord);
        if(ancientBookRecord.getAncientBookId()==null || ancientBookRecord.getChapterId()==null){
            return false;
        }
        AncientBookRecord currentRecord = ancientBookRecordMapper.selectAncientBookBookmarksByUserIdAndAncientBookId(SecurityUtils.getUserId(),ancientBookRecord.getAncientBookId());
        if(currentRecord!=null){
            currentRecord.setChapterId(ancientBookRecord.getChapterId());
            currentRecord.setLastUpdated(new Date());
            ancientBookRecordMapper.updateAncientBookRecord(currentRecord);
            return true;
        }else{
//            代表这本书当前没有记录,那就直接插入
            ancientBookRecord.setUserId(SecurityUtils.getUserId());
            ancientBookRecord.setLastUpdated(new Date());
            ancientBookRecordMapper.insertAncientBookRecord(ancientBookRecord);
            return true;
        }
    }

    /**
     * 查询阅读记录列表
     * 
     * @param ancientBookRecord 阅读记录
     * @return 阅读记录
     */
    @Override
    public List<AncientBookRecord> selectAncientBookRecordList(AncientBookRecord ancientBookRecord)
    {
        return ancientBookRecordMapper.selectAncientBookRecordList(ancientBookRecord);
    }

    @Override
    //    获取一个用户访问当前这本书的阅读记录
    public AncientBookRecord selectAncientBookBookmarksByUserIdAndAncientBookId(Long userId, Long ancientBookId) {
        return ancientBookRecordMapper.selectAncientBookBookmarksByUserIdAndAncientBookId(userId, ancientBookId);
    }

    /**
     * 新增阅读记录
     * 
     * @param ancientBookRecord 阅读记录
     * @return 结果
     */
    @Override
    public int insertAncientBookRecord(AncientBookRecord ancientBookRecord)
    {
        return ancientBookRecordMapper.insertAncientBookRecord(ancientBookRecord);
    }

    /**
     * 修改阅读记录
     * 
     * @param ancientBookRecord 阅读记录
     * @return 结果
     */
    @Override
    public int updateAncientBookRecord(AncientBookRecord ancientBookRecord)
    {
        System.out.println(ancientBookRecord);
        return ancientBookRecordMapper.updateAncientBookRecord(ancientBookRecord);
    }

    /**
     * 批量删除阅读记录
     * 
     * @param recordIds 需要删除的阅读记录主键
     * @return 结果
     */
    @Override
    public int deleteAncientBookRecordByRecordIds(Long[] recordIds)
    {
        return ancientBookRecordMapper.deleteAncientBookRecordByRecordIds(recordIds);
    }

    /**
     * 删除阅读记录信息
     * 
     * @param recordId 阅读记录主键
     * @return 结果
     */
    @Override
    public int deleteAncientBookRecordByRecordId(Long recordId)
    {
        return ancientBookRecordMapper.deleteAncientBookRecordByRecordId(recordId);
    }
}
