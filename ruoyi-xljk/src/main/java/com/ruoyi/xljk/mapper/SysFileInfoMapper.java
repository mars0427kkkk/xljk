package com.ruoyi.xljk.mapper;

import com.ruoyi.xljk.domain.SysFileInfo;

import java.util.List;

/**
 * 文件信息Mapper接口
 * 
 * @author ruoyi
 * @date 2023-09-24
 */
public interface SysFileInfoMapper 
{
    /**
     * 查询文件信息
     * 
     * @param fileId 文件信息主键
     * @return 文件信息
     */
    public SysFileInfo selectSysFileInfoByFileId(Long fileId);

    /**
     * 查询文件信息列表
     * 
     * @param sysFileInfo 文件信息
     * @return 文件信息集合
     */
    public List<SysFileInfo> selectSysFileInfoList(SysFileInfo sysFileInfo);

    /**
     * 新增文件信息
     * 
     * @param sysFileInfo 文件信息
     * @return 结果
     */
    public int insertSysFileInfo(SysFileInfo sysFileInfo);

    /**
     * 修改文件信息
     * 
     * @param sysFileInfo 文件信息
     * @return 结果
     */
    public int updateSysFileInfo(SysFileInfo sysFileInfo);

    /**
     * 删除文件信息
     * 
     * @param fileId 文件信息主键
     * @return 结果
     */
    public int deleteSysFileInfoByFileId(Long fileId);

    /**
     * 批量删除文件信息
     * 
     * @param fileIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysFileInfoByFileIds(Long[] fileIds);

   public int deleteSysFileInfoByFilepath(String filePath);
}
