package net.northking.iacmp.server.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import net.northking.iacmp.framework.config.properties.DruidProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Hive数据源
 *
 * @Author: wei.chen
 * @Date Created: in 2019/9/20 10:18
 */
@Configuration
public class HiveConfig {

    /**
     * 创建Hive数据源
     *
     * @param druidProperties
     * @return
     */
    @Bean(name = "hiveDataSource")
    @Qualifier("hiveDataSource")
    @ConfigurationProperties("spring.datasource.druid.hive")
    @ConditionalOnProperty(prefix = "spring.datasource.druid.hive", name = "enabled", havingValue = "true")
    public DataSource hiveDataSource(DruidProperties druidProperties) {
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        return druidProperties.dataSource(dataSource);
    }

    @Bean(name = "hiveJdbcTemplate")
    public JdbcTemplate hiveJdbcTemplate(
            @Qualifier("hiveDataSource") DataSource hiveDataSource) {
        return new JdbcTemplate(hiveDataSource);
    }
}
