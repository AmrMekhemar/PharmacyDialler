package com.team.myapplication

data class ReportProblemRequest(
    val email: String,
    val name: String,
    val phone: String,
    val problem: String,
    val role: String,
    val token: String
)