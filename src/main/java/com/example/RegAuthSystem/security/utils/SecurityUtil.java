package com.example.RegAuthSystem.security.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.RegAuthSystem.security.dto.SecurityUserDetails;
import com.example.common.config.CodeImpl;
import com.example.common.config.MessageCode;
import com.example.common.exception.ApiException;


@Component
public class SecurityUtil {

	@Autowired
    private ConfigurableApplicationContext ctx;

	@Autowired
	private MessageSource messageSource;
	
    /**
     * 取得系統用戶名稱
     *
     * @return 系統用戶名稱
     */
    public String getCurrentUsername() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
//           throw new ApiException(new CodeImpl(MessageCode.UNAUTHORIZED.getCode(),
//   				messageSource.getMessage("err.auth.token_timeout", null, null)));
        	return "system";

        }
        if (authentication.getPrincipal() instanceof SecurityUserDetails) {
            SecurityUserDetails userDetails = (SecurityUserDetails) authentication.getPrincipal();
            return userDetails.getUsername();
        }
        throw new ApiException(new CodeImpl(MessageCode.UNAUTHORIZED.getCode(),
        				messageSource.getMessage("err.auth.token_not_found", null, null)));

    }
}
