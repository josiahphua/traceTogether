package com.TraceTogether.Lai.repository

import com.TraceTogether.Lai.model.ToDo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TodoRepository : JpaRepository<ToDo, Long>