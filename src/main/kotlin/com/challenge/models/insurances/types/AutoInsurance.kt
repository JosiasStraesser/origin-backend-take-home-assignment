package challenge.controllers.insurance

import challenge.controllers.Rules.*
import challenge.models.RiskCriteria
import com.challenge.models.insurances.types.Insurance

class AutoInsurance(
        override val elegibilityRules: List<Rule> = listOf(HasVehicle),
        override val riskCriterias: List<RiskCriteria> = listOf(
                RiskCriteria(IsUnder30YearsOld, -2),
                RiskCriteria(IsBetween30And40YearsOld, -1),
                RiskCriteria(IncomeIsOver200k, -1),
                RiskCriteria(VehicleWasProducedInLast5Years, 1)
        )
) : Insurance