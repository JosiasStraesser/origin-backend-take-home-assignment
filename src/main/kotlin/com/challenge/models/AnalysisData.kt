package com.challenge.models

import java.util.*
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
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
    val scoreBase
        get() = riskQuestions.count { riskQuestionResult -> riskQuestionResult.equals(true) }

    enum class MaritalStatus { single, married }

    class HouseData(val ownershipStatus: OwnershipStatus) {
        enum class OwnershipStatus { owned, mortgaged }
    }

    class VehicleData(val year: Long) {
        val age = Calendar.getInstance().get(Calendar.YEAR) - year
    }
}