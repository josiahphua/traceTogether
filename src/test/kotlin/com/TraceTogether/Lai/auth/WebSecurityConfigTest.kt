package com.TraceTogether.Lai.auth

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest
class WebSecurityConfigTest(
    @Autowired
    private val mockMvc: MockMvc
) {
    @WithMockUser(authorities =["USER"])
    @Test
    fun `endpoint when user authority then authorized`() {
        mockMvc.perform{ get("/api/todos") }
            .andExpect{ status().isOk() }
    }

    @WithMockUser
    @Test
    fun `endpoint when not user authority then forbidden`() {
        mockMvc.perform{ get("/api/todos") }
            .andExpect { status().isForbidden() }
    }

    @Test
    fun `endpoint when unauthenticated then unauthorized`() {
        mockMvc.perform { get("/api/todos") }
            .andExpect { status().isUnauthorized() }
    }
}