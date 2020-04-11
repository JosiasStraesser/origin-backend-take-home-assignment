package com.challenge.models.Rules

import com.challenge.models.AnalysisData

object IsUnder60YearsOld : Rule {
    override fun isSatisfiedBy(data: AnalysisData) = data.age < 60
}