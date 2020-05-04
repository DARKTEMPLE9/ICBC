package net.northking.iacmp.config;

import org.apache.ibatis.io.VFS;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.ClassUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

/**
 * @Author：Yanqingyu
 * @ClassName:MyBatisConfig
 * @Description：mybtis实现正则扫描java bean包
 * @Date：Create in 10:38 AM2019/10/10
 * @Modified by:
 * @Version:1.0
 */
@Configuration
public class MyBatisConfig extends AbstractMyBatisConfig {

    @Value("${mybatis.typeAliasesPackage: }")
    private String typeAliasesPackage;
    @Value("${mybatis.mapperLocations: }")
    private String mapperLocation;

    @Value("${mybatis.ignoreTypeAliasesPackage: }")
    private String ignoreTypeAliasesPackage;
    @Value("${mybatis.ignoreMapperLocations: }")
    private String ignoreMapperLocations;


    private static final HashSet<Resource> resourceSet = new HashSet<>();
    private static Logger logger = LoggerFactory.getLogger(MyBatisConfig.class);

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource)
            throws Exception {
        System.out
                .println(">>>>>>>>>>>配置[typeAliasesPackage,mapperLocations]START>>>>>>>>>>>>>>");

        VFS.addImplClass(SpringBootVFS.class);
        typeAliasesPackage = getRealPath(typeAliasesPackage, ignoreTypeAliasesPackage, new String());
        Resource[] mapperLocations = getResource(AbstractMyBatisConfig.resolver, mapperLocation, ignoreMapperLocations);
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setVfs(SpringBootVFS.class);
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setTypeAliasesPackage(typeAliasesPackage);
        sessionFactory.setMapperLocations(mapperLocations);
        System.out
                .println(">>>>>>>>>>>配置[typeAliasesPackage,mapperLocations]END>>>>>>>>>>>>>>");
        return sessionFactory.getObject();
    }

    @Override
    public Resource[] getResource(ResourcePatternResolver resolver, String path, String ignorePath) {
        if (resourceSet.size() > 0) {
            resourceSet.clear();
        }

        String[] paths = path.split(",");
        String[] ignorePaths = ignorePath.split(",");
        execute(resolver, paths, 0);
        execute(resolver, ignorePaths, 1);
        return resourceSet.toArray(new Resource[resourceSet.size()]);
    }


    /**
     * @param resolver
     * @param path
     * @param operationalCode(0-新增，1-删除)
     */
    private void execute(ResourcePatternResolver resolver, String[] path, int operationalCode) {
        Arrays.asList(path).stream().forEach(typeAliasesPackage -> {
            if (!typeAliasesPackage.contains(ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX)) {
                typeAliasesPackage = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
                        + ClassUtils.convertClassNameToResourcePath(typeAliasesPackage)
                        + "/" + DEFAULT_RESOURCE_PATTERN;
            }
            try {
                Resource[] resources = resolver.getResources(typeAliasesPackage);
                if (resources != null && resources.length > 0 && operationalCode == 0) {
                    resourceSet.addAll(Arrays.asList(resources));
                } else {
                    resourceSet.removeAll(Arrays.asList(resources));
                }
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        });
    }

}
