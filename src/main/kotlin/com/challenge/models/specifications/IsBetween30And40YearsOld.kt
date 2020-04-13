package com.challenge.models.specifications

import com.challenge.models.AnalysisData

object IsBetween30And40YearsOld : Specification {
    override fun isSatisfiedBy(data: AnalysisData) = data.age in 30..40
}