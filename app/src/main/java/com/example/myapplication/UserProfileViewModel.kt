package com.example.myapplication

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class UserProfileViewModel : ViewModel() {

    private val _state = MutableStateFlow(UserProfileState())
    val state: StateFlow<UserProfileState> = _state.asStateFlow()

    fun updateName(newValue: String) = _state.update { it.copy(name = newValue) }
    fun updateEmail(newValue: String) = _state.update { it.copy(email = newValue) }
    fun updateMobile(newValue: String) = _state.update { it.copy(mobile = newValue) }
    fun updateAddress(newValue: String) = _state.update { it.copy(address = newValue) }
    fun updateUsername(newValue: String) = _state.update { it.copy(username = newValue) }
    fun updateSkillInput(newValue: String) = _state.update { it.copy(skillInput = newValue) }

    fun confirmAddSkill() {
        val entry = _state.value.skillInput.trim()
        if (entry.isNotEmpty() && (entry !in _state.value.skillList)) {
            _state.update { current ->
                current.copy(
                    skillList = current.skillList + entry,
                    skillInput = "",
                )
            }
        }
    }

    fun removeExistingSkill(skill: String) {
        _state.update { current ->
            current.copy(skillList = current.skillList - skill)
        }
    }

    fun togglePreviewMode(show: Boolean) = _state.update { it.copy(isViewingPreview = show) }
    
    fun resetProfileForm() = _state.update { UserProfileState() }
}
