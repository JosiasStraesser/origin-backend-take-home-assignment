package com.challenge.models.Specifications

import com.challenge.models.AnalysisData

interface Specification {
    fun isSatisfiedBy(data: AnalysisData): Boolean
}
