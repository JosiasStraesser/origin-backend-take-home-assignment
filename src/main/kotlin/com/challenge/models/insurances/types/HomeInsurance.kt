package com.challenge.models.insurances.types

import com.challenge.controllers.Rules.*
import com.challenge.models.Specifications.*
import com.challenge.models.insurances.RiskCriteria

class HomeInsurance(
        val elegibilitySpecifications: List<Specification> = listOf(HasHouse),
        override val riskCriterias: List<RiskCriteria> = listOf(
                RiskCriteria(IsUnder30YearsOld, -2),
                RiskCriteria(IsBetween30And40YearsOld, -1),
                RiskCriteria(IncomeIsOver200k, -1),
                RiskCriteria(HouseIsMortgaged, 1)
        )
) : Insurance