package com.example.voicecommands.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter


@Configuration
class SecurityConfiguration : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity?) {
        if (http != null) {
            http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/h2-console/**").permitAll()
            http.csrf().disable()
            http.headers().frameOptions().disable()
        }
    }
}