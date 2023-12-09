package com.beezon.couriers_tracking.repository

import com.beezon.couriers_tracking.entity.User
import org.springframework.data.repository.CrudRepository

interface UserRepository: CrudRepository<User, String> {
}