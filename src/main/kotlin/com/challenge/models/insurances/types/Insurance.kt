package com.challenge.models.insurances.types

import com.challenge.models.Specifications.Specification
import com.challenge.models.insurances.RiskCriteria

interface Insurance {
    val elegibilityRules: List<Specification>
    val riskCriterias: List<RiskCriteria>
}