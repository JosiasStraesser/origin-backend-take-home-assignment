package challenge.controllers.Rules

import challenge.models.RiskData

interface Rule {
    fun isSatisfiedBy(data: RiskData): Boolean
}

object HasIncome : Rule {
    override fun isSatisfiedBy(data: RiskData) = data.income > 0
}

object HasVehicle : Rule {
    override fun isSatisfiedBy(data: RiskData) = data.vehicle != null
}

object HasHouse : Rule {
    override fun isSatisfiedBy(data: RiskData) = data.house != null
}

object IsUnder60YearsOld : Rule {
    override fun isSatisfiedBy(data: RiskData) = data.age < 60
}

object IsUnder30YearsOld : Rule {
    override fun isSatisfiedBy(data: RiskData) = data.age < 30
}

object IsBetween30And40YearsOld : Rule {
    override fun isSatisfiedBy(data: RiskData) = data.age in 30..40
}

object IncomeIsOver200k : Rule {
    override fun isSatisfiedBy(data: RiskData) = data.income > 200000
}

object VehicleWasProducedInLast5Years : Rule {
    override fun isSatisfiedBy(data: RiskData) = data.vehicle != null && data.vehicle.age <= 5
}

object HouseIsMortgaged : Rule {
    override fun isSatisfiedBy(data: RiskData) =
        data.house != null && data.house.ownershipStatus == RiskData.HouseData.OwnershipStatus.mortgaged
}

object HasDependents : Rule {
    override fun isSatisfiedBy(data: RiskData) = data.house != null && data.dependents > 0
}

object IsMarried : Rule {
    override fun isSatisfiedBy(data: RiskData) =
        data.house != null && data.maritalStatus == RiskData.MaritalStatus.married
}
