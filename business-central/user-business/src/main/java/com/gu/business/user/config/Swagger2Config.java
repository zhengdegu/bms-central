package com.gu.business.user.config;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.base.Predicates;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.data.domain.Pageable;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.schema.AlternateTypeRule;
import springfox.documentation.schema.AlternateTypeRuleConvention;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static springfox.documentation.schema.AlternateTypeRules.newRule;

/**
 * @author FastG
 * @date 2020/7/14 16:16
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    //@Value("${spring.security.header}:")
    private String tokenHeader = "Authorization";

    //@Value("${spring.security.token-start-with}")
    private String tokenStartWith = "Bearer";

    //@Value("${spring.security.swagger.enabled}")
    private Boolean enabled = true;

    @Bean
    @SuppressWarnings("all")
    public Docket createRestApi() {
        ParameterBuilder ticketPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        ticketPar.name(tokenHeader).description("token")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .defaultValue(tokenStartWith + " ")
                .required(true)
                .build();
        pars.add(ticketPar.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enabled)
                .apiInfo(apiInfo())
                .select()
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build()
                .globalOperationParameters(pars);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .description("一个简单且易上手的 Spring boot 后台管理框架")
                .title("后台管理接口文档")
                .version("2.4")
                .build();
    }

}

/**
 * 将Pageable转换展示在swagger中
 */
@Configuration
class SwaggerDataConfig {

    @Bean
    public AlternateTypeRuleConvention pageableConvention(final TypeResolver resolver) {
        return new AlternateTypeRuleConvention() {
            @Override
            public int getOrder() {
                return Ordered.HIGHEST_PRECEDENCE;
            }

            @Override
            public List<AlternateTypeRule> rules() {
                return newArrayList(newRule(resolver.resolve(Pageable.class), resolver.resolve(Page.class)));
            }
        };
    }

    @ApiModel
    @Data
    private static class Page {
        @ApiModelProperty("页码 (0..N)")
        private Integer page;

        @ApiModelProperty("每页显示的数目")
        private Integer size;

        @ApiModelProperty("以下列格式排序标准：property[,asc | desc]。 默认排序顺序为升序。 支持多种排序条件：如：id,asc")
        private List<String> sort;
    }

}
