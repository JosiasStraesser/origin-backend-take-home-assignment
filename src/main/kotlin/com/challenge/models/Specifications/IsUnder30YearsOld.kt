package com.challenge.models.Specifications

import com.challenge.models.AnalysisData

object IsUnder30YearsOld : Specification {
    override fun isSatisfiedBy(data: AnalysisData) = data.age < 30
}