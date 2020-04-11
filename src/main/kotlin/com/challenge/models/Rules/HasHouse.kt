package com.challenge.models.Rules

import com.challenge.models.AnalysisData

object HasHouse : Rule {
    override fun isSatisfiedBy(data: AnalysisData) = data.house != null
}