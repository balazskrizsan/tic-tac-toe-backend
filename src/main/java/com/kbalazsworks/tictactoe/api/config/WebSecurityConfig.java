package com.kbalazsworks.tictactoe.api.config;

import com.kbalazsworks.tictactoe.api.controllers.game_controller.GameConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.session.SessionManagementFilter;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        // @formatter:off
        http
            .addFilterBefore(new CorsFilterConfig(), SessionManagementFilter.class)

            .csrf().disable()

            .authorizeRequests()

            .antMatchers(HttpMethod.GET, GameConfig.CONTROLLER_URI_V1 + GameConfig.START_SECURITY_PATH).permitAll()

            .anyRequest().authenticated();
        // @formatter:on
    }
}
