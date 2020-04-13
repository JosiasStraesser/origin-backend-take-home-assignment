package com.challenge.models.insurances.types

import com.challenge.models.AnalysisData
import com.challenge.models.Specifications.Specification
import com.challenge.models.insurances.RiskCriteria

interface Insurance {
    val eligibilityRules: List<Specification>
    val riskCriteria: List<RiskCriteria>

    fun isNotEligibleBy(analysisData: AnalysisData) =
            eligibilityRules.any { specification -> !specification.isSatisfiedBy(analysisData) }
}