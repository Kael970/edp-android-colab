package com.example.myapplication

data class UserProfileState(
    val name: String = "",
    val email: String = "",
    val mobile: String = "",
    val address: String = "",
    val username: String = "",
    val skillList: List<String> = emptyList(),
    val skillInput: String = "",
    val isViewingPreview: Boolean = false
)
