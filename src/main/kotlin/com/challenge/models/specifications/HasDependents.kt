package com.challenge.models.specifications

import com.challenge.models.AnalysisData

object HasDependents : Specification {
    override fun isSatisfiedBy(data: AnalysisData) = data.house != null && data.dependents > 0
}