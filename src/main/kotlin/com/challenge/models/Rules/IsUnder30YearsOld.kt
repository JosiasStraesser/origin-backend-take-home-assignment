package com.challenge.models.Rules

import com.challenge.models.AnalysisData

object IsUnder30YearsOld : Rule {
    override fun isSatisfiedBy(data: AnalysisData) = data.age < 30
}