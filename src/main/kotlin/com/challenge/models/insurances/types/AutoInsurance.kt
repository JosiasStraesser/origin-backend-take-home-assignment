package com.challenge.models.insurances.types

import com.challenge.models.Specifications.*
import com.challenge.models.insurances.RiskCriteria

object AutoInsurance : Insurance {
    override val elegibilityRules = listOf(HasVehicle)
    override val riskCriterias = listOf(
            RiskCriteria(IsUnder30YearsOld, -2),
            RiskCriteria(IsBetween30And40YearsOld, -1),
            RiskCriteria(IncomeIsOver200k, -1),
            RiskCriteria(VehicleWasProducedInLast5Years, 1)
    )
}