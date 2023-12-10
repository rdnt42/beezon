package com.beezon.couriers_tracking.controller

import com.beezon.couriers_tracking.controller.error.ResourceNotFoundException
import com.beezon.couriers_tracking.controller.request.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import javax.naming.AuthenticationException


@ControllerAdvice
class AdviceController {
    @ExceptionHandler(ResourceNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleResourceNotFound() {}

//    @ExceptionHandler(AuthenticationException::class)
//    fun handleAuth(e: AuthenticationException): ResponseEntity<ErrorResponse> {
//        return ResponseEntity<ErrorResponse>(ErrorResponse("Credentials are invalid"), HttpStatus.FORBIDDEN)
//    }
}