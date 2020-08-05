package com.gu.business.gateway.error;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.reactive.WebFluxAutoConfiguration;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.server.*;
import org.springframework.web.reactive.result.view.ViewResolver;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 自定义异常处理
 */
@Configuration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
@ConditionalOnClass(WebFluxConfigurer.class)
@AutoConfigureBefore(WebFluxAutoConfiguration.class)
@EnableConfigurationProperties({ServerProperties.class, ResourceProperties.class})
public class CustomErrorWebFluxAutoConfiguration {
    private final ServerProperties serverProperties;

    private final ApplicationContext applicationContext;

    private final ResourceProperties resourceProperties;

    private final List<ViewResolver> viewResolvers;

    private final ServerCodecConfigurer serverCodecConfigurer;

    public CustomErrorWebFluxAutoConfiguration(ServerProperties serverProperties,
                                               ResourceProperties resourceProperties,
                                               ObjectProvider<List<ViewResolver>> viewResolversProvider,
                                               ServerCodecConfigurer serverCodecConfigurer,
                                               ApplicationContext applicationContext) {
        this.serverProperties = serverProperties;
        this.applicationContext = applicationContext;
        this.resourceProperties = resourceProperties;
        this.viewResolvers = viewResolversProvider.getIfAvailable(Collections::emptyList);
        this.serverCodecConfigurer = serverCodecConfigurer;
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public ErrorWebExceptionHandler errorWebExceptionHandler(ErrorAttributes errorAttributes) {
        JsonErrorWebExceptionHandler exceptionHandler = new JsonErrorWebExceptionHandler(
                errorAttributes,
                this.resourceProperties,
                this.serverProperties.getError(),
                this.applicationContext);
        exceptionHandler.setViewResolvers(this.viewResolvers);
        exceptionHandler.setMessageWriters(this.serverCodecConfigurer.getWriters());
        exceptionHandler.setMessageReaders(this.serverCodecConfigurer.getReaders());
        return exceptionHandler;
    }

    public class JsonErrorWebExceptionHandler extends DefaultErrorWebExceptionHandler {
        public JsonErrorWebExceptionHandler(ErrorAttributes errorAttributes, ResourceProperties resourceProperties,
                                            ErrorProperties errorProperties, ApplicationContext applicationContext) {
            super(errorAttributes, resourceProperties, errorProperties, applicationContext);
        }

        /**
         * 获取异常属性
         */
        @Override
        protected Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace) {
            int code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            Throwable error = super.getError(request);
            if (error instanceof org.springframework.cloud.gateway.support.NotFoundException) {
                code = HttpStatus.NOT_FOUND.value();
            }
            return response(code, this.buildMessage(request, error));
        }

        /**
         * 指定响应处理方法为JSON处理的方法
         *
         * @param errorAttributes
         */
        @Override
        protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
            return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
        }


        /**
         * 根据code获取对应的HttpStatus
         * @param errorAttributes
         */
        @Override
        protected int getHttpStatus(Map<String, Object> errorAttributes) {
            return HttpStatus.INTERNAL_SERVER_ERROR.value();
        }

        /**
         * 构建异常信息
         *
         * @param request
         * @param ex
         * @return
         */
        private String buildMessage(ServerRequest request, Throwable ex) {
            StringBuilder message = new StringBuilder("Failed to handle request [");
            message.append(request.methodName());
            message.append(" ");
            message.append(request.uri());
            message.append("]");
            if (ex != null) {
                message.append(": ");
                message.append(ex.getMessage());
            }
            return message.toString();
        }

        /**
         * 构建返回的JSON数据格式
         *
         * @param status       状态码
         * @param errorMessage 异常信息
         * @return
         */
        public Map<String, Object> response(int status, String errorMessage) {
            Map<String, Object> map = new HashMap<>();
            map.put("status", status);
            map.put("message", errorMessage);
            map.put("data", null);
            return map;
        }

    }
}
