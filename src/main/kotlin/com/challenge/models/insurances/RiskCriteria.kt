package com.challenge.models.insurances

import com.challenge.models.AnalysisData
import com.challenge.models.specifications.Specification

data class RiskCriteria(private val specification: Specification, private val points: Int) {
    fun evaluate(data: AnalysisData) = if (specification.isSatisfiedBy(data)) points else 0
}