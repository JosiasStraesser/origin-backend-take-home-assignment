package com.challenge.models.specifications

import com.challenge.models.AnalysisData

object IsUnder30YearsOld : Specification {
    override fun isSatisfiedBy(data: AnalysisData) = data.age < 30
}