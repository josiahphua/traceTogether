package com.TraceTogether.Lai.dto

import java.time.LocalDate
import java.time.LocalDateTime

data class TodoDto(
    var id: Long? = null,
    var title: String,
    var status: String,
    var createdAt: LocalDateTime = LocalDateTime.now(),
    var todoStartDate: LocalDate? = LocalDate.now(),
    var todoEndDate: LocalDate? = LocalDate.now(),
    var description: String? = null,
    var email: String? = null,
    var createdBy: String? = null,
    var priority: String? = null
)