package com.challenge.models.specifications

import com.challenge.models.AnalysisData

object IsMarried : Specification {
    override fun isSatisfiedBy(data: AnalysisData) =
        data.house != null && data.maritalStatus == AnalysisData.MaritalStatus.Married
}