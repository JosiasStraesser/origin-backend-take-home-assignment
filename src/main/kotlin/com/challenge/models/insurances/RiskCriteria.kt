package com.challenge.models.insurances

import com.challenge.models.AnalysisData
import com.challenge.models.Rules.Rule

data class RiskCriteria(private val rule: Rule, private val points: Int) {
    fun calculate(data: AnalysisData) = if (rule.isSatisfiedBy(data)) points else 0
}