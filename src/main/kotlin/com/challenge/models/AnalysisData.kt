package com.challenge.models

import java.util.*
import javax.validation.constraints.Min
import javax.validation.constraints.Size

data class AnalysisData(
        @field:Min(0)
        val age: Int,

        @field:Min(0)
        val dependents: Int,

        @field:Min(0)
        val income: Int,

        val maritalStatus: MaritalStatus,

        @field:Size(min = 3, max = 3, message = "3 risk questions must be informed")
        val riskQuestions: List<Boolean>,

        val house: HouseData?,

        val vehicle: VehicleData?
) {
    val scoreBase = riskQuestions.count { riskQuestionResult -> riskQuestionResult.equals(true) }

    enum class MaritalStatus { Single, Married }

    class HouseData(val ownershipStatus: OwnershipStatus) {
        enum class OwnershipStatus { Owned, Mortgaged }
    }

    class VehicleData(val year: Long) {
        val age = Calendar.getInstance().get(Calendar.YEAR) - year
    }
}