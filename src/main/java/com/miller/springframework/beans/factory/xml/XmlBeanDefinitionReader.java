package com.miller.springframework.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import com.miller.springframework.beans.BeansException;
import com.miller.springframework.beans.PropertyValue;
import com.miller.springframework.beans.factory.config.BeanDefinition;
import com.miller.springframework.beans.factory.config.BeanReference;
import com.miller.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import com.miller.springframework.beans.factory.support.BeanDefinitionRegistry;
import com.miller.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import com.miller.springframework.core.io.Resource;
import com.miller.springframework.core.io.ResourceLoader;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author miller
 * @version 1.0.0
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {
    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) {
        try (InputStream inputStream = resource.getInputStream()) {
            doLoadBeanDefinitions(inputStream);
        } catch (IOException | ClassNotFoundException | SAXException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void loadBeanDefinitions(Resource... resources) {
        for (Resource resource : resources) {
            loadBeanDefinitions(resource);
        }
    }

    @Override
    public void loadBeanDefinitions(String location) {
        loadBeanDefinitions(getResourceLoader().getResource(location));
    }

    public void loadBeanDefinitions(String[] locations) {
        for (String location : locations) {
            loadBeanDefinitions(location);
        }
    }

    private void doLoadBeanDefinitions(InputStream inputStream) throws SAXException, ClassNotFoundException {
        SAXReader reader = new SAXReader();
        reader.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);

        Document doc = null;
        try {
            doc = reader.read(inputStream);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
        Element root = doc.getRootElement();
        List<Element> beanList = root.elements("bean");
        // 解析context:component-scan 标签，扫描包中的类并提取相关信息，用于组装BeanDefinition
        Element componentScan = root.element("component-scan");
        if (null != componentScan) {
            String scanPath = componentScan.attributeValue("base-package");
            if (StrUtil.isEmpty(scanPath)) {
                throw new BeansException("The value of base-package attribute can not be empty or null");
            }
            scanPackage(scanPath);
        }

        for (Element bean : beanList) {
            String id = bean.attributeValue("id");
            String name = bean.attributeValue("name");
            String className = bean.attributeValue("class");
            String initMethod = bean.attributeValue("init-method");
            String destroyMethodName = bean.attributeValue("destroy-method");
            String beanScope = bean.attributeValue("scope");

            //bean名称
            Class<?> clazz = Class.forName(className);
            String beanName = StrUtil.isNotEmpty(id) ? id : name;
            if (StrUtil.isEmpty(beanName)) {
                beanName = StrUtil.lowerFirst(clazz.getSimpleName());
            }

            //定义bean
            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            beanDefinition.setInitMethodName(initMethod);
            beanDefinition.setDestroyMethodName(destroyMethodName);

            if (StrUtil.isNotEmpty(beanScope)) {
                beanDefinition.setScope(beanScope);
            }

            //读取属性并填充
            List<Element> propertyList = bean.elements("property");
            for (Element property : propertyList) {
                //解析标签property
                String attrName = property.attributeValue("name");
                String attrValue = property.attributeValue("value");
                String attrRef = property.attributeValue("ref");
                //获取属性值
                Object value = StrUtil.isNotEmpty(attrRef) ? new BeanReference(attrRef) : attrValue;
                //创建属性信息
                PropertyValue propertyValue = new PropertyValue(attrName, value);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }
            if (getRegistry().containsBeanDefinition(beanName)) {
                throw new BeansException("Duplicate beanName[" + beanName + "] is not allowed");
            }
            //注册BeanDefinition
            getRegistry().registerBeanDefinition(beanName, beanDefinition);
        }
    }

    private void scanPackage(String scanPath) {
        String[] basePackages = StrUtil.splitToArray(scanPath, ',');
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(getRegistry());
        scanner.doScan(basePackages);
    }
}
