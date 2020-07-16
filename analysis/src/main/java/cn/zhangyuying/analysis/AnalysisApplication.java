package cn.zhangyuying.analysis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 用于分析，具体分析何种事物，取决于用户
 * 包目录结构：
 * analysis 分析-饼图
 * history 历史数据归档
 * record 待分析问题的处理(包括问题的增加更新和删除)
 * metadata 问题的元数据
 * import 导入导出文件
 * @author zyy
 */
@SpringBootApplication
public class AnalysisApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnalysisApplication.class, args);
    }

}
