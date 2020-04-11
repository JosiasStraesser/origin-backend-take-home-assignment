package com.challenge.models

import java.util.*

data class AnalysisData(
        val age: Long,
        val dependents: Long,
        val house: HouseData?,
        val income: Long,
        val maritalStatus: MaritalStatus,
        val riskQuestions: List<Boolean>,//[0, 1, 0],
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