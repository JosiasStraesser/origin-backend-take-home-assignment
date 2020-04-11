package challenge.models.insurances

import com.fasterxml.jackson.annotation.JsonProperty

enum class InsurancePlan {
    @JsonProperty("ineligible")
    Ineligible,
    @JsonProperty("economic")
    Economic,
    @JsonProperty("regular")
    Regular,
    @JsonProperty("responsible")
    Responsible
}
