package com.challenge.models.insurances

import com.challenge.models.AnalysisData
import com.challenge.models.Specifications.Specification
import com.challenge.models.insurances.types.Insurance

object InsuranceService {
    fun checkElegibility(
            insurance: Insurance,
            analysisData: AnalysisData
    ): InsurancePlan {

        return if (insurance.elegibilityRules.notAllAreSatisfiedBy(analysisData))
            InsurancePlan.Ineligible
        else
            processPlan(insurance.riskCriterias, analysisData)
    }

    private fun processPlan(criterias: List<RiskCriteria>, analysisData: AnalysisData): InsurancePlan {
        val riskPoints = analysisData.scoreBase + criterias.sumBy { criteria -> criteria.evaluate(analysisData) }

        return when {
            riskPoints <= 0 -> InsurancePlan.Economic
            riskPoints in 1..2 -> InsurancePlan.Regular
            else -> InsurancePlan.Responsible
        }
    }

    private fun List<Specification>.notAllAreSatisfiedBy(analysisData: AnalysisData) =
            this.any { specification -> !specification.isSatisfiedBy(analysisData) }
}
