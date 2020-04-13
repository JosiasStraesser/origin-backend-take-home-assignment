package com.challenge.models.specifications

import com.challenge.models.AnalysisData

object HouseIsMortgaged : Specification {
    override fun isSatisfiedBy(data: AnalysisData) =
        data.house != null && data.house.ownershipStatus == AnalysisData.HouseData.OwnershipStatus.Mortgaged
}