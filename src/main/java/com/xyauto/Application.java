package com.xyauto;

import com.xyauto.qa.core.conver.CategoryConver;
import com.xyauto.qa.core.conver.MyConver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.util.Map;

/**
 * Created by shiqm on 2017/3/21.
 */

@Configuration
@SpringBootApplication
@ServletComponentScan
@EnableDiscoveryClient
@EnableScheduling
@EnableFeignClients
@MapperScan(basePackages = {"com.xyauto.qa.mapper", "com.xyauto.bi.mapper","com.xyauto.system.mapper"})
@EnableTransactionManagement
public class Application extends SpringBootServletInitializer {
//public class Application  {


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    	setRegisterErrorPageFilter(false);
        return builder.sources(Application.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }


//    /**
//     * 文件上传配置
//     *
//     * @return
//     */
//    @Bean
//    public MultipartConfigElement multipartConfigElement() {
//        MultipartConfigFactory factory = new MultipartConfigFactory();
//        //文件最大
//        factory.setMaxFileSize("10240KB"); //KB,MB
//        /// 设置总上传数据总大小
//        factory.setMaxRequestSize("102400KB");
//        return factory.createMultipartConfig();
//    }

    /**
     * 增加静态地址
     *
     * @return
     */
    @Bean
    public CommandLineRunner customFreemarker(FreeMarkerViewResolver resolver) {
        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {
                //添加自定义解析器
                Map map = resolver.getAttributesMap();
                map.put("conver", new MyConver());
                map.put("cateConver", intiCategoryConver());

            }
        };
    }
    
    @Bean
    public CategoryConver intiCategoryConver(){
    	return new CategoryConver();
    }

}
