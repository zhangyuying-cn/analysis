package cn.zhangyuying.analysis.analysis;

import cn.zhangyuying.analysis.common.response.ApiResponse;
import cn.zhangyuying.analysis.common.response.ResponseStatus;
import cn.zhangyuying.analysis.common.response.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zyy.
 */
@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class AnalysisController {
    @Autowired
    private AnalysisService analysisService;

    @GetMapping(path = "analysis/{analysisType}")
    public String getAllIssue(@PathVariable String analysisType) {
        List<AnalysisResult> analysisResults = analysisService.analysisData(analysisType);
        return ResponseUtil.toString(new ApiResponse<List>(ResponseStatus.RESPONSE_2XX_OK, analysisResults));
    }
}
