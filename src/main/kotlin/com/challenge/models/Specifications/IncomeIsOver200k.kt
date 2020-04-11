package com.challenge.models.Specifications

import com.challenge.models.AnalysisData

object IncomeIsOver200k : Specification {
    override fun isSatisfiedBy(data: AnalysisData) = data.income > 200000
}