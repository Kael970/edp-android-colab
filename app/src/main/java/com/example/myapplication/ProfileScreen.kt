package com.example.myapplication

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MainProfileScreen(vm: UserProfileViewModel = viewModel()) {
    val uiState by vm.state.collectAsStateWithLifecycle()

    if (uiState.isViewingPreview) {
        PreviewProfileContent(
            data = uiState,
            onGoBack = { vm.togglePreviewMode(show = false) },
        )
    } else {
        EditProfileContent(
            data = uiState,
            handler = vm
        )
    }
}

@Composable
fun EditProfileContent(data: UserProfileState, handler: UserProfileViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text("Profile Setup", fontSize = 26.sp, fontWeight = FontWeight.ExtraBold)
        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = data.name,
            onValueChange = { handler.updateName(it) },
            label = { Text("Complete Name") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = data.email,
            onValueChange = { handler.updateEmail(it) },
            label = { Text("Mail Address") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = data.mobile,
            onValueChange = { handler.updateMobile(it) },
            label = { Text("Contact Number") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = data.address,
            onValueChange = { handler.updateAddress(it) },
            label = { Text("Physical Address") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = data.username,
            onValueChange = { handler.updateUsername(it) },
            label = { Text("User Identity") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(20.dp))
        Text("Competencies", fontWeight = FontWeight.SemiBold)

        Row(
            modifier = Modifier.padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = data.skillInput,
                onValueChange = { handler.updateSkillInput(it) },
                label = { Text("New Competency") },
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.width(8.dp))
            Button(onClick = { handler.confirmAddSkill() }) {
                Text("Add")
            }
        }

        data.skillList.forEach { item ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("• $item", modifier = Modifier.weight(1f))
                IconButton(onClick = { handler.removeExistingSkill(item) }) {
                    Text("Delete", color = MaterialTheme.colorScheme.error, fontSize = 12.sp)
                }
            }
        }

        Spacer(Modifier.height(24.dp))
        
        Row(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = { handler.togglePreviewMode(true) },
                modifier = Modifier.weight(1f),
                enabled = data.name.isNotBlank() && data.email.isNotBlank()
            ) {
                Text("Switch to Preview")
            }
            Spacer(Modifier.width(8.dp))
            OutlinedButton(onClick = { handler.resetProfileForm() }) {
                Text("Clear")
            }
        }
    }
}

@Composable
fun PreviewProfileContent(data: UserProfileState, onGoBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text("Your Profile Summary", fontSize = 26.sp, fontWeight = FontWeight.ExtraBold)
        Spacer(Modifier.height(16.dp))

        ProfileDetail("Name", data.name)
        ProfileDetail("Email", data.email)
        ProfileDetail("Contact", data.mobile)
        ProfileDetail("Address", data.address)
        ProfileDetail("Username", data.username)

        Spacer(Modifier.height(16.dp))
        Text("Skills:", fontWeight = FontWeight.SemiBold)
        if (data.skillList.isEmpty()) {
            Text("List is currently empty.")
        } else {
            data.skillList.forEach { Text("➤ $it") }
        }

        Spacer(Modifier.height(32.dp))
        Button(onClick = onGoBack, modifier = Modifier.fillMaxWidth()) {
            Text("Return to Form")
        }
    }
}

@Composable
fun ProfileDetail(label: String, info: String) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(label, fontSize = 12.sp, color = MaterialTheme.colorScheme.secondary)
        Text(info.ifBlank { "Not provided" }, fontSize = 18.sp)
    }
}
