package com.challenge.controllers

import com.challenge.models.insurances.types.AutoInsurance
import com.challenge.models.insurances.types.DisabilityInsurance
import com.challenge.models.insurances.types.HomeInsurance
import com.challenge.models.insurances.types.LifeInsurance
import com.challenge.models.insurances.InsuranceService
import com.challenge.models.AnalysisData
import com.challenge.models.RiskResult
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/risk")
class RiskController {

    @PostMapping("/analysis")
    fun analysis(@Valid @RequestBody analysisData: AnalysisData): RiskResult {
        return RiskResult(
                auto = InsuranceService.checkEligibility(AutoInsurance, analysisData),
                home = InsuranceService.checkEligibility(HomeInsurance, analysisData),
                disability = InsuranceService.checkEligibility(DisabilityInsurance, analysisData),
                life = InsuranceService.checkEligibility(LifeInsurance, analysisData)
        )
    }
}


