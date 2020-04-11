package com.challenge.controllers

import com.challenge.ChallengeApplication
import com.challenge.JsonComponent
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.Test
import org.junit.jupiter.api.AfterEach
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.util.*

@RunWith(SpringRunner::class)
@SpringBootTest(classes = [ChallengeApplication::class])
@AutoConfigureMockMvc
class RiskControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var jsonComponent: JsonComponent

    @Autowired
    private lateinit var mapper: ObjectMapper

    @Test
    fun `When receiving a call, should return result`() {
        val requestJson = jsonComponent.jsonObject(
                "request/risk/analysis-default.json"
        )

        mockMvc.perform(
                MockMvcRequestBuilders.post("/risk/analysis")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.auto").value("regular"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.disability").value("regular"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.home").value("regular"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.life").value("regular"))
    }

    @Test
    fun `When receiving negative age, should not continue`() {
        val requestJson = jsonComponent.jsonObject(
                "request/risk/analysis-default.json"
        ).put("age", -1)

        mockMvc.perform(
                MockMvcRequestBuilders.post("/risk/analysis")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson.toString()))
                .andExpect(MockMvcResultMatchers.status().isBadRequest)
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0]").value("age: must be greater than or equal to 0"))
    }

    @Test
    fun `When receiving negative dependents, should not continue`() {
        val requestJson = jsonComponent.jsonObject(
                "request/risk/analysis-default.json"
        ).put("dependents", -1)

        mockMvc.perform(
                MockMvcRequestBuilders.post("/risk/analysis")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson.toString()))
                .andExpect(MockMvcResultMatchers.status().isBadRequest)
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0]").value("dependents: must be greater than or equal to 0"))
    }

    @Test
    fun `When receiving negative income, should not continue`() {
        val requestJson = jsonComponent.jsonObject(
                "request/risk/analysis-default.json"
        ).put("income", -1)

        mockMvc.perform(
                MockMvcRequestBuilders.post("/risk/analysis")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson.toString()))
                .andExpect(MockMvcResultMatchers.status().isBadRequest)
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0]").value("income: must be greater than or equal to 0"))
    }

    @Test
    fun `When receiving more than 3 risk questions, should not continue`() {
        val requestJson = jsonComponent.jsonObject(
                "request/risk/analysis-default.json"
        )
        val riskQuestions = requestJson.getJSONArray("risk_questions").put( true)
        requestJson.put("risk_questions", riskQuestions)

        mockMvc.perform(
                MockMvcRequestBuilders.post("/risk/analysis")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson.toString()))
                .andExpect(MockMvcResultMatchers.status().isBadRequest)
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0]").value("riskQuestions: 3 risk questions must be informed"))
    }

    @Test
    fun `When receiving less than 3 risk questions, should not continue`() {
        val requestJson = jsonComponent.jsonObject(
                "request/risk/analysis-default.json"
        )
        val riskQuestions = requestJson.getJSONArray("risk_questions")
        riskQuestions.remove(0)
        requestJson.put("risk_questions", riskQuestions)

        mockMvc.perform(
                MockMvcRequestBuilders.post("/risk/analysis")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson.toString()))
                .andExpect(MockMvcResultMatchers.status().isBadRequest)
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0]").value("riskQuestions: 3 risk questions must be informed"))
    }

    @Test
    fun `When the user doesn't have income, should return ineligible for disability insurance`() {
        val requestJson = jsonComponent.jsonObject(
                "request/risk/analysis-default.json"
        ).put("income", "0")

        mockMvc.perform(
                MockMvcRequestBuilders.post("/risk/analysis")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.disability").value("ineligible"))
    }

    @Test
    fun `When the user doesn't have vehicles, should return ineligible for auto insurance`() {
        val requestJson = jsonComponent.jsonObject(
                "request/risk/analysis-default.json"
        ).put("vehicle", null)

        mockMvc.perform(
                MockMvcRequestBuilders.post("/risk/analysis")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.auto").value("ineligible"))
    }

    @Test
    fun `When the user doesn't have houses, should return ineligible for home insurance`() {
        val requestJson = jsonComponent.jsonObject(
                "request/risk/analysis-default.json"
        ).put("house", null)

        mockMvc.perform(
                MockMvcRequestBuilders.post("/risk/analysis")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.home").value("ineligible"))
    }

    @Test
    fun `When the user is over 60 years old, should return ineligible for disability and life insurance`() {
        val requestJson = jsonComponent.jsonObject(
                "request/risk/analysis-default.json"
        ).put("age", "61")

        mockMvc.perform(
                MockMvcRequestBuilders.post("/risk/analysis")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.disability").value("ineligible"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.life").value("ineligible"))
    }

    @Test
    fun `When the user is under 30 years old, should deduct 2 risk points from all lines of insurance`() {
        val requestJson = jsonComponent.jsonObject(
                "request/risk/analysis-default.json"
        ).put("age", "29")


        mockMvc.perform(
                MockMvcRequestBuilders.post("/risk/analysis")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.auto").value("economic"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.disability").value("economic"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.home").value("economic"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.life").value("economic"))
    }

    @Test
    fun `When the user is between 30 and 40 years old, should deduct 1 risk point from all lines of insurance`() {
        val requestJson = jsonComponent.jsonObject(
                "request/risk/analysis-default.json"
        )
                .put("age", "35")

        mockMvc.perform(
                MockMvcRequestBuilders.post("/risk/analysis")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.auto").value("economic"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.disability").value("economic"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.home").value("economic"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.life").value("economic"))
    }

    @Test
    fun `When the user's income is above $200k, should deduct 1 risk point from all lines of insurance`() {
        val requestJson = jsonComponent.jsonObject(
                "request/risk/analysis-default.json"
        ).put("income", "200001")


        mockMvc.perform(
                MockMvcRequestBuilders.post("/risk/analysis")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.auto").value("economic"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.disability").value("economic"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.home").value("economic"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.life").value("economic"))
    }

    @Test
    fun `When the user's house is mortgaged, should add 1 risk point to her home and disability score`() {
        val requestJson = jsonComponent.jsonObject(
                "request/risk/analysis-default.json"
        )
        val house = requestJson.getJSONObject("house").put("ownership_status", "mortgaged")
        requestJson.put("house", house)
        val riskQuestions = requestJson.getJSONArray("risk_questions").put(0, true)
        requestJson.put("risk_questions", riskQuestions)


        mockMvc.perform(
                MockMvcRequestBuilders.post("/risk/analysis")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.auto").value("regular"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.disability").value("responsible"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.home").value("responsible"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.life").value("regular"))
    }

    @Test
    fun `When the user has dependents, should add 1 risk point disability and life score`() {
        val requestJson = jsonComponent.jsonObject(
                "request/risk/analysis-default.json"
        )
        requestJson.put("dependents", 1)
        val riskQuestions = requestJson.getJSONArray("risk_questions").put(0, true)
        requestJson.put("risk_questions", riskQuestions)


        mockMvc.perform(
                MockMvcRequestBuilders.post("/risk/analysis")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.auto").value("regular"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.disability").value("responsible"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.home").value("regular"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.life").value("responsible"))
    }

    @Test
    fun `When the user is married, should add 1 risk point to life score`() {
        val requestJson = jsonComponent.jsonObject(
                "request/risk/analysis-default.json"
        )
        requestJson.put("marital_status", "married")
        val riskQuestions = requestJson.getJSONArray("risk_questions").put(0, true)
        requestJson.put("risk_questions", riskQuestions)


        mockMvc.perform(
                MockMvcRequestBuilders.post("/risk/analysis")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.auto").value("regular"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.disability").value("regular"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.home").value("regular"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.life").value("responsible"))
    }

    @Test
    fun `When the user is married, should remove 1 risk point from disability score`() {
        val requestJson = jsonComponent.jsonObject(
                "request/risk/analysis-default.json"
        )
        requestJson.put("marital_status", "married")


        mockMvc.perform(
                MockMvcRequestBuilders.post("/risk/analysis")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.auto").value("regular"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.disability").value("economic"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.home").value("regular"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.life").value("regular"))
    }

    @Test
    fun `When the user's vehicle was produced in the last 5 years, should add 1 risk point for auto score`() {
        val requestJson = jsonComponent.jsonObject(
                "request/risk/analysis-default.json"
        )
        val house = requestJson.getJSONObject("vehicle").put("year", Calendar.getInstance().get(Calendar.YEAR))
        requestJson.put("vehicle", house)
        val riskQuestions = requestJson.getJSONArray("risk_questions").put(0, true)
        requestJson.put("risk_questions", riskQuestions)


        mockMvc.perform(
                MockMvcRequestBuilders.post("/risk/analysis")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.auto").value("responsible"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.disability").value("regular"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.home").value("regular"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.life").value("regular"))
    }
}