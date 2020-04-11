package com.challenge.models.insurances.types

import com.challenge.controllers.Rules.*
import com.challenge.models.Specifications.*
import com.challenge.models.insurances.RiskCriteria

class AutoInsurance(
        val elegibilitySpecifications: List<Specification> = listOf(HasVehicle),
        override val riskCriterias: List<RiskCriteria> = listOf(
                RiskCriteria(IsUnder30YearsOld, -2),
                RiskCriteria(IsBetween30And40YearsOld, -1),
                RiskCriteria(IncomeIsOver200k, -1),
                RiskCriteria(VehicleWasProducedInLast5Years, 1)
        )
) : Insurance