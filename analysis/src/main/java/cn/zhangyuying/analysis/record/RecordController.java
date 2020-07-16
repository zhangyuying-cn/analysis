package cn.zhangyuying.analysis.record;

import cn.zhangyuying.analysis.common.CommonUtil;
import cn.zhangyuying.analysis.common.response.ApiResponse;
import cn.zhangyuying.analysis.common.response.ResponseStatus;
import cn.zhangyuying.analysis.common.response.ResponseUtil;
import cn.zhangyuying.analysis.record.bean.Record;
import cn.zhangyuying.analysis.record.bean.PageParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zyy.
 */
@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class RecordController {
    private final RecordService recordService;

    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    @GetMapping(path = "records")
    public String getAllRecord(@RequestParam Integer limit, @RequestParam Integer page) {
        PageParam pageParam = new PageParam(page, limit);
        List<Record> records = recordService.getAllRecord(pageParam);
        return ResponseUtil.toString(new ApiResponse<List>(ResponseStatus.RESPONSE_2XX_CREATE, records));
    }

    @PutMapping(path = "records")
    public String addOrUpdateRecord(@RequestBody List<Record> records) {
        List<Record> addRecords = new ArrayList<>();
        List<Record> updateRecords = new ArrayList<>();
        for (Record record : records) {
            if (record.getRecordData().size() == 0) {
                continue;
            }
            //TODO 不要循环查库
            Record dbRecord = recordService.selectRecordById(record.getId());
            if (StringUtils.isBlank(record.getId()) || dbRecord == null) {
                if (StringUtils.isBlank(record.getId())) {
                    record.setId(CommonUtil.getUuid());
                }
                addRecords.add(record);
            } else {
                updateRecords.add(record);
            }
        }
        if (!CollectionUtils.isEmpty(updateRecords)) {
            recordService.updateRecords(updateRecords);
        }
        if (!CollectionUtils.isEmpty(addRecords)) {
            recordService.insertRecords(addRecords);
        }
        return ResponseUtil.toString(new ApiResponse<List>(ResponseStatus.RESPONSE_2XX_UPDATED, records));

    }

    @DeleteMapping(path = "records/{id}")
    public String deleteRecord(@PathVariable String id) {
        recordService.deleteRecord(id);
        return ResponseUtil.toString(new ApiResponse<>(ResponseStatus.RESPONSE_2XX_NO_CONTENT));
    }

}
