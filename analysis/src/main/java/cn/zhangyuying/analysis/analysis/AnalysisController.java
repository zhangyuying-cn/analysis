package cn.zhangyuying.analysis.analysis;

import cn.zhangyuying.analysis.analysis.bean.AnalysisResult;
import cn.zhangyuying.analysis.analysis.bean.BarAnalysisResult;
import cn.zhangyuying.analysis.common.AnalysisException;
import cn.zhangyuying.analysis.common.response.ApiResponse;
import cn.zhangyuying.analysis.common.response.ResponseStatus;
import cn.zhangyuying.analysis.common.response.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zyy.
 */
@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class AnalysisController {
    private final AnalysisService analysisService;

    public AnalysisController(AnalysisService analysisService) {
        this.analysisService = analysisService;
    }

//    @GetMapping(path = "analysis/pie/{analysisType}")
//    public String getAnalysisResult(@PathVariable String analysisType) {
//        List<AnalysisResult> analysisResults = analysisService.getPieAnalysisData(analysisType);
//        return ResponseUtil.toString(new ApiResponse<List>(ResponseStatus.RESPONSE_2XX_OK, analysisResults));
//    }

    @GetMapping(path = "analysis/{analysisType}")
    public String getBarAnalysisResult(@PathVariable String analysisType, @RequestParam(required = false) String xAxis, @RequestParam String type) {
        switch (type) {
            case "pie":
                List<AnalysisResult> analysisResults = analysisService.getPieAnalysisData(analysisType);
                return ResponseUtil.toString(new ApiResponse<List>(ResponseStatus.RESPONSE_2XX_OK, analysisResults));
            case "bar":
                List<BarAnalysisResult> barAnalysisResults = analysisService.getBarAnalysisData(analysisType, xAxis);
                return ResponseUtil.toString(new ApiResponse<List>(ResponseStatus.RESPONSE_2XX_OK, barAnalysisResults));
            default:
                throw new AnalysisException("不合法的type参数");
        }

    }
}
