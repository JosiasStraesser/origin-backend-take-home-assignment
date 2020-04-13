package com.challenge.models.insurances

import com.challenge.models.AnalysisData
import com.challenge.models.insurances.types.Insurance

object InsuranceService {
    fun checkEligibility(
            insurance: Insurance,
            analysisData: AnalysisData
    ): InsurancePlan {

        return if (insurance.isNotEligibleBy(analysisData))
            InsurancePlan.Ineligible
        else
            elect(insurance.riskCriteria, analysisData)
    }

    private fun elect(criteria: List<RiskCriteria>, analysisData: AnalysisData): InsurancePlan {
        val riskPoints = analysisData.scoreBase + criteria.sumBy { criteria -> criteria.evaluate(analysisData) }

        return when {
            riskPoints <= 0 -> InsurancePlan.Economic
            riskPoints in 1..2 -> InsurancePlan.Regular
            else -> InsurancePlan.Responsible
        }
    }


}
