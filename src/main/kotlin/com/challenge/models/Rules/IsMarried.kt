package com.challenge.models.Rules

import com.challenge.models.AnalysisData

object IsMarried : Rule {
    override fun isSatisfiedBy(data: AnalysisData) =
        data.house != null && data.maritalStatus == AnalysisData.MaritalStatus.married
}