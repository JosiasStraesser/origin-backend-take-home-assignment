package com.challenge.models.specifications

import com.challenge.models.AnalysisData

object HasVehicle : Specification {
    override fun isSatisfiedBy(data: AnalysisData) = data.vehicle != null
}