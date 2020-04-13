package com.challenge.models.specifications

import com.challenge.models.AnalysisData

object IsUnder60YearsOld : Specification {
    override fun isSatisfiedBy(data: AnalysisData) = data.age < 60
}