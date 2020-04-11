package com.challenge.models.Specifications

import com.challenge.models.AnalysisData

object HasIncome : Specification {
    override fun isSatisfiedBy(data: AnalysisData) = data.income > 0
}