package com.challenge.controllers

import com.challenge.models.insurances.types.AutoInsurance
import com.challenge.models.insurances.types.DisabilityInsurance
import com.challenge.models.insurances.types.HomeInsurance
import com.challenge.models.insurances.types.LifeInsurance
import com.challenge.models.insurances.InsuranceService
import com.challenge.models.AnalysisData
import com.challenge.models.RiskResult
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/risk")
class RiskController {

    @PostMapping("/analysis")
    fun analysis(@RequestBody operationData: AnalysisData): RiskResult {
        return RiskResult(
                auto = InsuranceService.checkElegibility(AutoInsurance, operationData),
                home = InsuranceService.checkElegibility(HomeInsurance, operationData),
                disability = InsuranceService.checkElegibility(DisabilityInsurance, operationData),
                life = InsuranceService.checkElegibility(LifeInsurance, operationData)
        )
    }
}


