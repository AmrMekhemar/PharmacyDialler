package com.team.myapplication

data class EditPasswordRequest(
    val oldpassword: String,
    val password: String,
    val confirmPassword: String
)