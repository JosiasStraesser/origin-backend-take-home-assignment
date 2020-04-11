package challenge.model.insurance

import challenge.models.insurances.InsurancePlan
import challenge.models.RiskCriteria
import challenge.models.RiskData
import com.challenge.models.insurances.types.Insurance

object InsuranceService {
    fun checkElegibility(
            insurance: Insurance,
            data: RiskData
    ): InsurancePlan {
        return if (insurance.elegibilityRules.any { !it.isSatisfiedBy(data) })
            InsurancePlan.Ineligible
        else
            processPlan(insurance.riskCriterias, data)
    }

    private fun processPlan(criterias: List<RiskCriteria>, data: RiskData): InsurancePlan {
        val riskPoints = data.scoreBase + criterias.sumBy { it.calculate(data) }

        return when {
            riskPoints <= 0 -> InsurancePlan.Economic
            riskPoints in 1..2 -> InsurancePlan.Regular
            else -> InsurancePlan.Responsible
        }
    }
}
