package com.selfmybatis.session;


import com.selfmybatis.binding.MapperMethod;
import com.selfmybatis.binding.MapperRegistry;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 读取xml文件 加载到内存中  mybatis/mybatis-config.xml
 * */
/*
 * 解析xml文件  常见技术
 * 1. DOM4J(Document Object Model for Java)
 * 2. StAX(Streaming API for XML)  等等
 * */
public class Configuration {

    private InputStream inputStream;

    MapperRegistry mapperRegistry = new MapperRegistry();

    /*
     * 通过dom4j读取配置文件中的信息
     * */
    public void loadConfigurations() throws IOException {
        try {
            Document document = new SAXReader().read(inputStream);
            Element root = document.getRootElement();
            List<Element> mappers = root.element("mappers").elements("mapper");
            for (Element mapper : mappers) {
                if (mapper.attribute("resource") != null) {
                    mapperRegistry.setKnownMappers(loadXMLConfiguration(mapper.attribute("resource").getText()));
                }
                if (mapper.attribute("class") != null) {

                }
            }


        } catch (DocumentException e) {
            System.out.println("读取配置文件错误");

        }

    }

    /*
     * dom4j  解析xml
     * 读取mapper.xml 中的信息
     * */
    private Map<String, MapperMethod> loadXMLConfiguration(String resource) {
        Map<String, MapperMethod> map = new HashMap<String, MapperMethod>();
        InputStream is = null;
        try {
            is = this.getClass().getClassLoader().getResourceAsStream(resource);
            Document document = new SAXReader().read(is);
            Element root = document.getRootElement();
            if (root.getName().equalsIgnoreCase("mapper")) {
                String namespace = root.attribute("namespace").getText();
                for (Element select : (List<Element>) root.elements("select")) {
                    MapperMethod mapperModel = new MapperMethod();
                    mapperModel.setSql(select.getText().trim());
                    mapperModel.setType(Class.forName(select.attribute("resultType").getText()));
                    map.put(namespace + "." + select.attribute("id").getText(), mapperModel);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                is.close();
            } catch (IOException e1) {
                e.printStackTrace();
            }
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public MapperRegistry getMapperRegistry() {
        return mapperRegistry;
    }

    public void setMapperRegistry(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }
}
