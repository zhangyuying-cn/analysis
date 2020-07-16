package cn.zhangyuying.analysis.configuration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@MapperScan(basePackages = {
    "cn.zhangyuying.analysis"
})
public class MybatisConfiguration {
}
