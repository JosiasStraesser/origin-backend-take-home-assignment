package com.challenge.models

import com.challenge.models.insurances.InsurancePlan

data class RiskResult(
        val auto: InsurancePlan,
        val disability: InsurancePlan,
        val home: InsurancePlan,
        val life: InsurancePlan
)