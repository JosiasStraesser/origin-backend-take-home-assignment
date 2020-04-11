package com.challenge.models.insurances.types

import com.challenge.models.Rules.Rule
import com.challenge.models.insurances.RiskCriteria

interface Insurance {
    val elegibilityRules: List<Rule>
    val riskCriterias: List<RiskCriteria>
}