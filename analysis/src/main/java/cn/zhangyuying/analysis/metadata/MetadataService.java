package cn.zhangyuying.analysis.metadata;

import cn.zhangyuying.analysis.common.CommonUtil;
import cn.zhangyuying.analysis.metadata.bean.Metadata;
import cn.zhangyuying.analysis.metadata.bean.MetadataDTO;
import cn.zhangyuying.analysis.metadata.dao.MetadataDao;
import cn.zhangyuying.analysis.record.bean.PageParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zyy.
 */
@Service
public class MetadataService {
    private final MetadataDao metadataDao;

    public MetadataService(MetadataDao metadataDao) {
        this.metadataDao = metadataDao;
    }

    public Metadata getMetadataById(String id) {
        return metadataDao.selectMetadataById(id);
    }

    public List<Metadata> getAllMetadata(PageParam pageParam) {
        if(pageParam==null){
            return metadataDao.getAllMetadata(null,null);
        }
        Integer offset = pageParam.getLimit() * (pageParam.getPage() - 1);
        return metadataDao.getAllMetadata(pageParam.getLimit(), offset);
    }

    public void insertMetadata(Metadata metadata) {
        if(StringUtils.isBlank(metadata.getId())){
            metadata.setId(CommonUtil.getUuid());
        }
        metadataDao.insertMetadata(metadata);
    }

    public void insertMetadatas(List<Metadata> metadatas) {
        metadataDao.insertMetadatas(metadatas);
    }

    public void updateMetadata(Metadata metadata) {
        metadataDao.updateMetadata(metadata);
    }

    public void deleteMetadata(String id) {
        metadataDao.deleteMetadata(id);
    }
}
