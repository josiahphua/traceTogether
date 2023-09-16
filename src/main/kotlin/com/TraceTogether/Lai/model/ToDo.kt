package com.TraceTogether.Lai.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.PrePersist
import jakarta.persistence.PreUpdate
import jakarta.persistence.Table
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
        @Enumerated(EnumType.STRING)
        // EnumType.ORDINAL, creates the enum in order,
        // if add new status in the middle, can disrupt existing data.
        var status: ToDoStatus = ToDoStatus.PENDING,

        @Column(name = "created_at")
        var createdAt: LocalDateTime = LocalDateTime.now(),

        @Column(name = "updated_at")
        var updatedAt: LocalDateTime? = LocalDateTime.now(),

        @Column(name = "todo_start_date")
        var todoStartDate: LocalDateTime? = null,

        @Column(name = "todo_end_date")
        var todoEndDate: LocalDateTime? = null,

        @Column(name = "description")
        var description: String? = null,

        @Column(name = "email")
        var email: String? = null,

        @Column(name = "created_by")
        var createdBy: String? = null,

        @Column(name = "priority")
        var priority: String? = null
) {
        @PrePersist
        fun onCreate() {
                assignLocalDateTimeNow(todoStartDate)
        }

        @PreUpdate
        fun onUpdat() {
                assignLocalDateTimeNow(updatedAt)
        }
}

enum class ToDoStatus(val status: String) {
        PENDING("Pending"),
        IN_PROGRESS("In Progress"),
        COMPLETED("Completed")
}

fun assignLocalDateTimeNow(dataAttribute: LocalDateTime?): LocalDateTime {
        // Elvis operator "?:" takes the left side, if it's non-null
        /* initial code
        if ( dataAttribute == null ) {
                dataAttribute = LocalDateTime.now()
        }
        return dataAttribute
         */
        return dataAttribute ?: LocalDateTime.now()
}