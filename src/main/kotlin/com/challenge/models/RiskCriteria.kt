package challenge.models

import challenge.controllers.Rules.Rule

data class RiskCriteria(private val rule: Rule, private val points: Int) {
    fun calculate(data: RiskData) = if (rule.isSatisfiedBy(data)) points else 0
}