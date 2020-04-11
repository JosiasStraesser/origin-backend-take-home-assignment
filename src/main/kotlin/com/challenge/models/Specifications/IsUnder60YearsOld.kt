package com.challenge.models.Specifications

import com.challenge.models.AnalysisData

object IsUnder60YearsOld : Specification {
    override fun isSatisfiedBy(data: AnalysisData) = data.age < 60
}