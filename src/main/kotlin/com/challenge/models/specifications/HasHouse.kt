package com.challenge.models.specifications

import com.challenge.models.AnalysisData

object HasHouse : Specification {
    override fun isSatisfiedBy(data: AnalysisData) = data.house != null
}