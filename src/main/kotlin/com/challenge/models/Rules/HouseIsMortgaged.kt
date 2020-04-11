package com.challenge.models.Rules

import com.challenge.models.AnalysisData

object HouseIsMortgaged : Rule {
    override fun isSatisfiedBy(data: AnalysisData) =
        data.house != null && data.house.ownershipStatus == AnalysisData.HouseData.OwnershipStatus.mortgaged
}