package com.challenge.configuration.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.ServletWebRequest
import java.util.*
import javax.validation.ValidationException


@ControllerAdvice
class ExceptionHandler() {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun invalidInput(ex: MethodArgumentNotValidException, request: ServletWebRequest):
            ResponseEntity<ExceptionResponse> {

        var errorsList = ex.bindingResult.fieldErrors.map { fieldError ->
            fieldError.defaultMessage?.let { message -> "${fieldError.field}: $message" }.orEmpty()
        }

        val exceptionResponse = ExceptionResponse(
                Date(),
                "Validation failed",
                request.getDescription(false),
                request.httpMethod.toString(),
                HttpStatus.BAD_REQUEST.toString(),
                errorsList
        )
        return ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun messageNotReadable(ex: HttpMessageNotReadableException, request: ServletWebRequest):
            ResponseEntity<ExceptionResponse> {

        val exceptionResponse = ExceptionResponse(
                Date(),
                "Validation failed",
                request.getDescription(false),
                request.httpMethod.toString(),
                HttpStatus.BAD_REQUEST.toString(),
                listOf(ex.message!!)
        )
        return ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST)
    }
}