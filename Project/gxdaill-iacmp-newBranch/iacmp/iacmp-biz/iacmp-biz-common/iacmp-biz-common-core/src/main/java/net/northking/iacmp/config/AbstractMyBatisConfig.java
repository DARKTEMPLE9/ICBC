package net.northking.iacmp.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @Author：Yanqingyu
 * @ClassName:MyBatisConfig
 * @Description：mybtis实现正则扫描java bean包
 * @Date：Create in 10:38 AM2019/10/10
 * @Modified by:
 * @Version:1.0
 */
public abstract class AbstractMyBatisConfig {

    static final String DEFAULT_RESOURCE_PATTERN = "**/*.class";

    static final ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

    private static Logger logger = LoggerFactory.getLogger(AbstractMyBatisConfig.class);

    /**
     * 抽象获取源地址(将路径中删除忽略路径)
     *
     * @param path
     * @param ignorePath
     * @return
     */
    protected abstract Resource[] getResource(ResourcePatternResolver resolver, String path, String ignorePath);

    /**
     * 获取真实包路径
     *
     * @param path
     * @param ignorePath
     * @param retStr
     * @return
     */
    public String getRealPath(String path, String ignorePath, String retStr) {

        MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(
                resolver);
        Resource[] resources = getResource(resolver, path, ignorePath);
        List<String> result = new ArrayList<>();
        try {
            if (resources != null && resources.length > 0) {
                MetadataReader metadataReader;
                for (Resource resource : resources) {
                    if (resource.isReadable()) {
                        metadataReader = metadataReaderFactory
                                .getMetadataReader(resource);
                        try {
                            result.add(Class
                                    .forName(
                                            metadataReader.getClassMetadata()
                                                    .getClassName())
                                    .getPackage().getName());
                        } catch (ClassNotFoundException e) {
                            logger.error(e.getMessage());
                        }
                    }
                }
            }
            if (result.size() > 0) {
                HashSet<String> h = new HashSet<String>(result);
                result.clear();
                result.addAll(h);
                retStr = String.join(",", (String[]) result.toArray(new String[0]));
            } else {
                throw new RuntimeException(
                        "mybatis typeAliasesPackage 路径扫描错误,path:"
                                + path + "未找到任何包");
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return retStr;
    }

}
