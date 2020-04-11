package com.challenge.models.insurances.types

import com.challenge.models.Specifications.*
import com.challenge.models.insurances.RiskCriteria

object DisabilityInsurance : Insurance {
    override val elegibilityRules = listOf(HasIncome, IsUnder60YearsOld)
    override val riskCriterias = listOf(
            RiskCriteria(IsUnder30YearsOld, -2),
            RiskCriteria(IsBetween30And40YearsOld, -1),
            RiskCriteria(IncomeIsOver200k, -1),
            RiskCriteria(HouseIsMortgaged, 1),
            RiskCriteria(HasDependents, 1),
            RiskCriteria(IsMarried, -1)
    )
}