package com.challenge.models.specifications

import com.challenge.models.AnalysisData

object HasIncome : Specification {
    override fun isSatisfiedBy(data: AnalysisData) = data.income > 0
}