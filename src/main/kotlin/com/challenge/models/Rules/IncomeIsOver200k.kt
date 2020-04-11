package com.challenge.models.Rules

import com.challenge.models.AnalysisData

object IncomeIsOver200k : Rule {
    override fun isSatisfiedBy(data: AnalysisData) = data.income > 200000
}