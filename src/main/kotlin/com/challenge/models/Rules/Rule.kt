package com.challenge.models.Rules

import com.challenge.models.AnalysisData

interface Rule {
    fun isSatisfiedBy(data: AnalysisData): Boolean
}
