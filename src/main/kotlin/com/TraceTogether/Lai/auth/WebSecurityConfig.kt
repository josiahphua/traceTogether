package com.TraceTogether.Lai.auth;

import com.TraceTogether.Lai.service.auth.UserDetailsService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class WebSecurityConfig(
    private val userDetailsService: UserDetailsService,
) {
    private val jwtTokenProvider = JwtTokenProvider()

    private fun authManager(http: HttpSecurity): AuthenticationManager {
        val authenticationManagerBuilder = http.getSharedObject(
            AuthenticationManagerBuilder::class.java
        )
        authenticationManagerBuilder.userDetailsService(userDetailsService)
        return authenticationManagerBuilder.build()
    }

    @Bean
    @Throws(Exception::class)
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        val authenticationManager = authManager(http)

        http.authorizeHttpRequests()
            // To set a specific USER authority
//            .requestMatchers("/**")
//            .hasAuthority("USER")
            .requestMatchers("/api/todos")
            .permitAll()
            .anyRequest()
            .authenticated()

//            .formLogin { form ->
//                form
//                    .loginPage("/login")
//                    .permitAll()
//            }
//            .logout { logout -> logout.permitAll() }
        return http.build()
    }


}