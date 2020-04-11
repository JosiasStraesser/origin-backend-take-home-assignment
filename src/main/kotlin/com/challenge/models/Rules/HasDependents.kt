package com.challenge.models.Rules

import com.challenge.models.AnalysisData

object HasDependents : Rule {
    override fun isSatisfiedBy(data: AnalysisData) = data.house != null && data.dependents > 0
}