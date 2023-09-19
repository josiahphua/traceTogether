package com.TraceTogether.Lai.auth.config

import com.TraceTogether.Lai.JwtTokenUtil
import com.TraceTogether.Lai.auth.filters.JwtAuthenticationFilter
import com.TraceTogether.Lai.auth.filters.JwtAuthorizationFilter
import com.TraceTogether.Lai.service.UserDetailsService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain


@Configuration
class SecurityConfig(
        private val userDetailsService: UserDetailsService
) {
    private val jwtTokenUtil = JwtTokenUtil()

    private fun authManager(httpSecurity: HttpSecurity): AuthenticationManager {
        val authenticationManagerBuilder = httpSecurity.getSharedObject(
                AuthenticationManagerBuilder::class.java
        )
        authenticationManagerBuilder.userDetailsService(userDetailsService)
        return authenticationManagerBuilder.build()
    }

    @Bean
    open fun securityFilterChain(http: HttpSecurity): SecurityFilterChain? {
        val authenticationManager = authManager(http)
        http
            .authorizeHttpRequests(Customizer { authorize ->
                authorize
                        .anyRequest().authenticated()

            }
        ).authenticationManager(authenticationManager)
                .addFilter(JwtAuthenticationFilter(jwtTokenUtil, authenticationManager))
                .addFilter(JwtAuthorizationFilter(jwtTokenUtil, userDetailsService, authenticationManager))
                // TODO: Unsure of subsequent 2, do testing
                .formLogin{}
                .httpBasic{}
        return http.build()
    }

    @Bean
    open fun bCryptPasswordEncoder(): BCryptPasswordEncoder = BCryptPasswordEncoder()

//    Can look into having multiple filters.
//    @Order(1)
//    @Bean
//    open fun apiFilterChain(http: HttpSecurity): SecurityFilterChain {
//        http {
//            securityMatchers()
//            authorizeHttpRequests(
//                    { authorize ->
//                        authorize.anyRequest().hasRole("ADMIN")
//                    }
//            )
//        }
//    }
}
