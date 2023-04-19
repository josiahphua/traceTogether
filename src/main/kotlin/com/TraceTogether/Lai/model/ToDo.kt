package com.TraceTogether.Lai.model

import jakarta.persistence.Column
import jakarta.persistence.Table
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "todo")
data class ToDo(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,

        @Column(name = "title")
        var title: String,

        @Column(name = "status")
        var status: String,

        @Column(name = "created_at")
        var createdAt: LocalDateTime = LocalDateTime.now(),

        @Column(name = "todo_start_date")
        var todoStartDate: LocalDate? = LocalDate.now(),

        @Column(name = "todo_end_date")
        var todoEndDate: LocalDate? = LocalDate.now(),

        @Column(name = "description")
        var description: String? = null,

        @Column(name = "email")
        var email: String? = null,

        @Column(name = "created_by")
        var createdBy: String? = null,

        @Column(name = "priority")
        var priority: String? = null
)

//enum class ToDoStatus(val status: String) {
//    Active("Active"),
//    Completed("Completed")
//}