package com.gu.security.authorize;

import cn.hutool.core.util.StrUtil;
import com.gu.common.properties.SecurityConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;


@Slf4j
public class DefaultLogoutHandler implements LogoutHandler {
	@Autowired
	private TokenStore tokenStore;

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		Assert.notNull(tokenStore, "tokenStore must be set");
		String token = request.getParameter("token");
		if (StrUtil.isEmpty(token)) {
			token = this.extractToken(request);
		}
		if(StrUtil.isNotEmpty(token)){
			OAuth2AccessToken existingAccessToken = tokenStore.readAccessToken(token);
			OAuth2RefreshToken refreshToken;
			if (existingAccessToken != null) {
				if (existingAccessToken.getRefreshToken() != null) {
					log.info("remove refreshToken!", existingAccessToken.getRefreshToken());
					refreshToken = existingAccessToken.getRefreshToken();
					tokenStore.removeRefreshToken(refreshToken);
				}
				log.info("remove existingAccessToken!", existingAccessToken);
				tokenStore.removeAccessToken(existingAccessToken);
			}
		}
	}

	/**
	 * 获取requet(head/param)中的token
	 * @param request
	 * @return
	 */
	public String extractToken(HttpServletRequest request) {
		String token = extractHeaderToken(request);
		if (token == null) {
			token = request.getParameter(OAuth2AccessToken.ACCESS_TOKEN);
			if (token == null) {
				log.debug("Token not found in request parameters.  Not an OAuth2 request.");
			}
		}
		return token;
	}

	/**
	 * 解析head中的token
	 * @param request
	 * @return
	 */
	private  String extractHeaderToken(HttpServletRequest request) {
		Enumeration<String> headers = request.getHeaders(SecurityConstants.TOKEN_HEADER);
		while (headers.hasMoreElements()) {
			String value = headers.nextElement();
			if ((value.startsWith(OAuth2AccessToken.BEARER_TYPE))) {
				String authHeaderValue = value.substring(OAuth2AccessToken.BEARER_TYPE.length()).trim();
				int commaIndex = authHeaderValue.indexOf(',');
				if (commaIndex > 0) {
					authHeaderValue = authHeaderValue.substring(0, commaIndex);
				}
				return authHeaderValue;
			}
		}
		return null;
	}
}
