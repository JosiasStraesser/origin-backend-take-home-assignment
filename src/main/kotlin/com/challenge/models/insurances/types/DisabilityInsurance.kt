package challenge.controllers.insurance

import challenge.controllers.Rules.*
import challenge.models.RiskCriteria
import com.challenge.models.insurances.types.Insurance

class DisabilityInsurance(
        override val elegibilityRules: List<Rule> = listOf(HasIncome, IsUnder60YearsOld),
        override val riskCriterias: List<RiskCriteria> = listOf(
                RiskCriteria(IsUnder30YearsOld, -2),
                RiskCriteria(IsBetween30And40YearsOld, -1),
                RiskCriteria(IncomeIsOver200k, -1),
                RiskCriteria(HouseIsMortgaged, 1),
                RiskCriteria(HasDependents, 1),
                RiskCriteria(IsMarried, -1)
        )
) : Insurance