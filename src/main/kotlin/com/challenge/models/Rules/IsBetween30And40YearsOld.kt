package com.challenge.models.Rules

import com.challenge.models.AnalysisData

object IsBetween30And40YearsOld : Rule {
    override fun isSatisfiedBy(data: AnalysisData) = data.age in 30..40
}