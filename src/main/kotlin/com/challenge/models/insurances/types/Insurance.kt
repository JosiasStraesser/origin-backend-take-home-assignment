package com.challenge.models.insurances.types

import challenge.controllers.Rules.HasHouse
import challenge.controllers.Rules.Rule
import challenge.models.RiskCriteria

interface Insurance {
    val elegibilityRules: List<Rule>
    val riskCriterias: List<RiskCriteria>
}