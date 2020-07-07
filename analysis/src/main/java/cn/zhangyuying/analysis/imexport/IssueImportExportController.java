package cn.zhangyuying.analysis.imexport;

import cn.zhangyuying.analysis.common.AnalysisException;
import cn.zhangyuying.analysis.common.response.ApiResponse;
import cn.zhangyuying.analysis.common.response.ResponseStatus;
import cn.zhangyuying.analysis.common.response.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * TODO
 * @author zyy.
 */
@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class IssueImportExportController {
    /** logger */
    private static Logger logger = LoggerFactory.getLogger(IssueImportExportController.class);
    private final ImexportService imexportService;

    public IssueImportExportController(ImexportService imexportService) {
        this.imexportService = imexportService;
    }

    @PostMapping(path = "/import/excel")
    public String importExcelData(@RequestParam("file") MultipartFile excelFile) {
        if (null == excelFile) {
            throw new AnalysisException(ResponseStatus.RESPONSE_4XX_INVALID_REQUEST);
        }
        try {
            imexportService.importExcelOrganData(excelFile);
            return ResponseUtil.toString(new ApiResponse(ResponseStatus.RESPONSE_2XX_CREATE));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseUtil.toString(new ApiResponse(500, e.getMessage()));
        }
    }
}
