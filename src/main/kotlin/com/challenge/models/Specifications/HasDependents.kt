package com.challenge.models.Specifications

import com.challenge.models.AnalysisData

object HasDependents : Specification {
    override fun isSatisfiedBy(data: AnalysisData) = data.house != null && data.dependents > 0
}