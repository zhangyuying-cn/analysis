package cn.zhangyuying.analysis.metadata;

import cn.zhangyuying.analysis.common.response.ApiResponse;
import cn.zhangyuying.analysis.common.response.ResponseStatus;
import cn.zhangyuying.analysis.common.response.ResponseUtil;
import cn.zhangyuying.analysis.metadata.bean.Metadata;
import cn.zhangyuying.analysis.record.bean.Record;
import cn.zhangyuying.analysis.record.bean.PageParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zyy.
 */
@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class MetadataController {
    private final MetadataService metadataService;

    public MetadataController(MetadataService metadataService) {
        this.metadataService = metadataService;
    }

    @GetMapping(path = "metadatas")
    public String getAllMetadata(@RequestParam(required = false) PageParam pageParam) {
        List<Metadata> metadataList = metadataService.getAllMetadata(pageParam);
        return ResponseUtil.toString(new ApiResponse<List>(ResponseStatus.RESPONSE_2XX_CREATE, metadataList));
    }

    @PutMapping(path = "metadatas")
    public String addOrUpdateRecord(@RequestBody Metadata metadata) {
        if (metadataService.getMetadataById(metadata.getId()) != null) {
            metadataService.updateMetadata(metadata);
            return ResponseUtil.toString(new ApiResponse<Metadata>(ResponseStatus.RESPONSE_2XX_UPDATED, metadata));
        } else {
            metadataService.insertMetadata(metadata);
            return ResponseUtil.toString(new ApiResponse<Metadata>(ResponseStatus.RESPONSE_2XX_CREATE, metadata));
        }
    }

    @DeleteMapping(path = "metadatas/{id}")
    public String deleteRecord(@PathVariable String id) {
        metadataService.deleteMetadata(id);
        return ResponseUtil.toString(new ApiResponse<>(ResponseStatus.RESPONSE_2XX_NO_CONTENT));
    }
}
