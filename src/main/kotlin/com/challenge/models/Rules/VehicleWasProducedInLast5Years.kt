package com.challenge.models.Rules

import com.challenge.models.AnalysisData

object VehicleWasProducedInLast5Years : Rule {
    override fun isSatisfiedBy(data: AnalysisData) = data.vehicle != null && data.vehicle.age <= 5
}