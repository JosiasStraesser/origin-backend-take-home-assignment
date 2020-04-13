package com.challenge.models.specifications

import com.challenge.models.AnalysisData

object VehicleWasProducedInLast5Years : Specification {
    override fun isSatisfiedBy(data: AnalysisData) = data.vehicle != null && data.vehicle.age <= 5
}