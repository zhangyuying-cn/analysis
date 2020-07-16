package cn.zhangyuying.analysis.metadata.dao;

import cn.zhangyuying.analysis.metadata.bean.Metadata;
import cn.zhangyuying.analysis.metadata.dao.mybatis.MetadataMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zyy.
 */
@Repository
public class MetadataDao {
    
    private final MetadataMapper metadataMapper;

    public MetadataDao(MetadataMapper metadataMapper) {
        this.metadataMapper = metadataMapper;
    }

    public Metadata selectMetadataById(String id) {
        return metadataMapper.selectMetadataById(id);
    }

    public List<Metadata> getAllMetadata(Integer limit, Integer offset) {
        return metadataMapper.getAllMetadata(limit, offset);
    }

    public void insertMetadata(Metadata metadata) {
        metadataMapper.insertMetadata(metadata);
    }

    public void insertMetadatas(List<Metadata> metadatas) {
        metadataMapper.insertMetadatas(metadatas);
    }

    public void updateMetadata(Metadata metadata) {
        metadataMapper.updateMetadata(metadata);
    }

    public void deleteMetadata(String id) {
        metadataMapper.deleteMetadata(id);
    }
}
