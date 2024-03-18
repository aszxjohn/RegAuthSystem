package com.example.RegAuthSystem.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.example.RegAuthSystem.security.utils.SecurityUtil;
import com.example.common.config.JpaAuditorBaseConfig;



/**
 * @author  John
 * @date : 2024/03/13
 */
@Configuration
@Component("auditorAware")
public class JpaAuditorConfig extends JpaAuditorBaseConfig {

	@Autowired
	SecurityUtil securityUtil;

    @Override
    public Optional<String> getCurrentAuditor() {
        try {

            return Optional.of(securityUtil.getCurrentUsername());
        }catch (Exception ex){}
        return Optional.of("System");
    }
}
