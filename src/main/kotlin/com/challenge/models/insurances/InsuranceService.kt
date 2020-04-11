package com.challenge.models.insurances

import com.challenge.models.AnalysisData
import com.challenge.models.insurances.types.Insurance

object InsuranceService {
    fun checkElegibility(
            insurance: Insurance,
            data: AnalysisData
    ): InsurancePlan {
        return if (insurance.elegibilitySpecifications.any { !it.isSatisfiedBy(data) })
            InsurancePlan.Ineligible
        else
            processPlan(insurance.riskCriterias, data)
    }

    private fun processPlan(criterias: List<RiskCriteria>, data: AnalysisData): InsurancePlan {
        val riskPoints = data.scoreBase + criterias.sumBy { it.calculate(data) }

        return when {
            riskPoints <= 0 -> InsurancePlan.Economic
            riskPoints in 1..2 -> InsurancePlan.Regular
            else -> InsurancePlan.Responsible
        }
    }
}
