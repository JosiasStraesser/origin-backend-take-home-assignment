package com.challenge.models.Specifications

import com.challenge.models.AnalysisData

object IsBetween30And40YearsOld : Specification {
    override fun isSatisfiedBy(data: AnalysisData) = data.age in 30..40
}