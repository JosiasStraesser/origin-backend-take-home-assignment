package com.challenge.models.specifications

import com.challenge.models.AnalysisData

interface Specification {
    fun isSatisfiedBy(data: AnalysisData): Boolean
}
