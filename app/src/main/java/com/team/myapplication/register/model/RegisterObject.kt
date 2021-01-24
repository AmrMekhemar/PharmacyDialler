package com.team.myapplication.register.model

data class RegisterObject(

	val name: String,
	val email: String,
	val password: String,
	val confirmPassword: String,
	val phones: String,
	val locationAsAddress: String,
	val locationAsCoordinates: LocationAsCoordinates
)