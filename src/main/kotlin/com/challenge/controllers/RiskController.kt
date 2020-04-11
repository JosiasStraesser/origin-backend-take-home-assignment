package com.challenge.controllers

import challenge.controllers.insurance.AutoInsurance
import challenge.controllers.insurance.DisabilityInsurance
import challenge.controllers.insurance.HomeInsurance
import challenge.controllers.insurance.LifeInsurance
import challenge.model.insurance.InsuranceService
import challenge.models.RiskData
import challenge.models.RiskResult
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/risk")
class RiskController {

    @PostMapping("/analysis")
    fun analysis(@RequestBody riskData: RiskData): RiskResult {
        val result = RiskResult(
                auto = InsuranceService.checkElegibility(AutoInsurance(), riskData),
                home = InsuranceService.checkElegibility(HomeInsurance(), riskData),
                disability = InsuranceService.checkElegibility(DisabilityInsurance(), riskData),
                life = InsuranceService.checkElegibility(LifeInsurance(), riskData)
        )
        return result
    }

    @GetMapping
    fun analysis() {
    }
}


