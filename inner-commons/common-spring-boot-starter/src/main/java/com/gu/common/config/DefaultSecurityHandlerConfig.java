package com.gu.common.config;

/**
 * @author FastG
 * @date 2020/5/28 17:59
 */
public class DefaultSecurityHandlerConfig {
//
//    @Resource
//    private ObjectMapper objectMapper;
//
//    /**
//     * 未登录，返回401
//     *
//     * @return
//     */
//    @Bean
//    public AuthenticationEntryPoint authenticationEntryPoint() {
//        return (request, response, authException) -> ResponseUtil.responseFailed(objectMapper, response, authException.getMessage());
//    }
//
//    @Bean
//    public OAuth2WebSecurityExpressionHandler oAuth2WebSecurityExpressionHandler(ApplicationContext applicationContext) {
//        OAuth2WebSecurityExpressionHandler expressionHandler = new OAuth2WebSecurityExpressionHandler();
//        expressionHandler.setApplicationContext(applicationContext);
//        return expressionHandler;
//    }
//
//    /**
//     * 处理spring security oauth 处理失败返回消息格式
//     */
//    @Bean
//    public OAuth2AccessDeniedHandler oAuth2AccessDeniedHandler() {
//        return new OAuth2AccessDeniedHandler() {
//
//            @Override
//            public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException authException) throws IOException, ServletException {
//                ResponseUtil.responseFailed(objectMapper, response, authException.getMessage());
//            }
//        };
//    }
}
