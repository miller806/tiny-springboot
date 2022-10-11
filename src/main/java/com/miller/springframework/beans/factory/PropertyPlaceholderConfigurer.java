package com.miller.springframework.beans.factory;

import com.miller.springframework.beans.PropertyValue;
import com.miller.springframework.beans.PropertyValues;
import com.miller.springframework.beans.factory.config.BeanDefinition;
import com.miller.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.miller.springframework.core.io.DefaultResourceLoader;
import com.miller.springframework.core.io.Resource;
import com.miller.springframework.utils.StringValueResolver;

import java.io.IOException;
import java.util.Properties;

/**
 * @author miller
 * @version 1.0.0
 */
public class PropertyPlaceholderConfigurer implements BeanFactoryPostProcessor {
    public static String DEFAULT_PLACEHOLDER_PREFIX = "${";

    public static final String DEFAULT_PLACEHOLDER_SUFFIX = "}";

    private String location;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        try {
            //加载属性文件
            DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
            Resource resource = resourceLoader.getResource(location);
            //占位符替换属性值、设置属性值
            Properties properties = new Properties();
            properties.load(resource.getInputStream());
            String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
            for (String beanDefinitionName : beanDefinitionNames) {
                BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanDefinitionName);
                PropertyValues propertyValues = beanDefinition.getPropertyValues();
                for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                    Object value = propertyValue.getValue();
                    if (!(value instanceof String)) {
                        continue;
                    }
                    String strVal = (String) value;
                    StringBuilder builder = new StringBuilder(strVal);
                    int startIdx = strVal.indexOf(DEFAULT_PLACEHOLDER_PREFIX);
                    int stopIdx = strVal.indexOf(DEFAULT_PLACEHOLDER_SUFFIX);
                    if (startIdx != -1 && stopIdx != -1 && startIdx < stopIdx) {
                        String propKey = strVal.substring(startIdx + 2, stopIdx);
                        String propVal = (String) properties.get(propKey);
                        builder.replace(startIdx, stopIdx + 1, propVal);
                        propertyValues.addPropertyValue(new PropertyValue(propertyValue.getName(), builder.toString()));
                    }
                }
            }
            //向容器中添加字符串解析器,供分析@Value使用
            StringValueResolver valueResolver = new PlaceholderResolvingStringValueResolver(properties);
            beanFactory.addEmbeddedValueResolver(valueResolver);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private class PlaceholderResolvingStringValueResolver implements StringValueResolver {
        private final Properties properties;

        public PlaceholderResolvingStringValueResolver(Properties properties) {
            this.properties = properties;
        }

        @Override
        public String resolveStringValue(String strVal) {
            return PropertyPlaceholderConfigurer.this.resolvePlaceholder(strVal, properties);
        }
    }

    private String resolvePlaceholder(String strVal, Properties properties) {
        String value = strVal;
        StringBuilder builder = new StringBuilder(value);
        int startIdx = strVal.indexOf(DEFAULT_PLACEHOLDER_PREFIX);
        int stopIdx = strVal.indexOf(DEFAULT_PLACEHOLDER_SUFFIX);
        if (startIdx != -1 && stopIdx != -1 && startIdx < stopIdx) {
            String propKey = strVal.substring(startIdx + 2, stopIdx);
            String propVal = properties.getProperty(propKey);
            builder.replace(startIdx, stopIdx + 1, propVal);
        }
        return builder.toString();
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
