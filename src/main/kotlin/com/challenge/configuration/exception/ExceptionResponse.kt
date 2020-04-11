package com.challenge.configuration.exception

import java.util.Date

data class ExceptionResponse(
    val timestamp: Date,
    val message: String,
    val details: String,
    val httpMethod: String,
    val status: String,
    val errors: List<String>
)
