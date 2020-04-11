package com.challenge.models.Specifications

import com.challenge.models.AnalysisData

object HouseIsMortgaged : Specification {
    override fun isSatisfiedBy(data: AnalysisData) =
        data.house != null && data.house.ownershipStatus == AnalysisData.HouseData.OwnershipStatus.mortgaged
}