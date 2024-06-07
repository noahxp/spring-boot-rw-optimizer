package tw.noah.demo.configure;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tw.noah.demo.interceptor.CommonInterceptor;


@Log4j2
@Configuration
@ComponentScan("tw.noah.demo")
public class WebMvcConfigure implements WebMvcConfigurer {

    private CommonInterceptor commonInterceptor;

    public WebMvcConfigure(CommonInterceptor commonInterceptor) {
        this.commonInterceptor = commonInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(commonInterceptor).addPathPatterns("/**");
    }

    /** 原始 CORS Header 設定, Spring boot 2.4.0 後, 可直接使用 spring 處理
     *     response.setHeader("Access-Control-Allow-Origin", origin);
     *     response.setHeader("Access-Control-Allow-Methods", "HEAD, OPTIONS, GET, POST, PUT");
     *     response.setHeader("Access-Control-Allow-Headers", "Content-Type, " + payHmacHeader + ", " + idTokenHeader);
     *     response.setHeader("Access-Control-Allow-Credentials", "true");
     *     response.setHeader("Access-Control-Max-Age", "3600");
     *     response.setHeader("vary", "Accept-Encoding,Origin,Access-Control-Request-Headers,Access-Control-Request-Method");
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/apis/**")
            .allowedOriginPatterns("*")
            .allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "DELETE")
            .allowedHeaders("Content-Type")
            .exposedHeaders("Accept-Encoding", "Origin", "Access-Control-Request-Headers", "Access-Control-Request-Method")
            .maxAge(3600)
            .allowCredentials(true);
    }

    /**
     * rewrite default header
     */
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.ignoreAcceptHeader(true).defaultContentType(MediaType.APPLICATION_JSON);
    }
}
