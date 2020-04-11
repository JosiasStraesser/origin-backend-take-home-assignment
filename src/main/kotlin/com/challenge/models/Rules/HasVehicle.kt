package com.challenge.models.Rules

import com.challenge.models.AnalysisData

object HasVehicle : Rule {
    override fun isSatisfiedBy(data: AnalysisData) = data.vehicle != null
}