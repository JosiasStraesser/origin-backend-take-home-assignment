package com.challenge.models.Specifications

import com.challenge.models.AnalysisData

object HasHouse : Specification {
    override fun isSatisfiedBy(data: AnalysisData) = data.house != null
}