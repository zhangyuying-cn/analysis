package cn.zhangyuying.analysis.metadata.dao.mybatis;

import cn.zhangyuying.analysis.metadata.bean.Metadata;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zyy.
 */
public interface MetadataMapper {

    Metadata selectMetadataById(@Param("id") String id);

    List<Metadata> getAllMetadata(@Param("limit") Integer limit, @Param("offset") Integer offset);

    void insertMetadata(@Param("metadata") Metadata metadata);

    void insertMetadatas(@Param("metadatas") List<Metadata> metadatas);

    void updateMetadata(@Param("metadata") Metadata metadata);

    void deleteMetadata(@Param("id") String id);

}
