package com.team.myapplication

data class EditPasswordRequest(
    val confirmPassword: String,
    val oldpassword: String,
    val password: String
)