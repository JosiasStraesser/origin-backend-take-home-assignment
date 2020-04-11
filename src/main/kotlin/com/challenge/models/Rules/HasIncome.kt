package com.challenge.models.Rules

import com.challenge.models.AnalysisData

object HasIncome : Rule {
    override fun isSatisfiedBy(data: AnalysisData) = data.income > 0
}