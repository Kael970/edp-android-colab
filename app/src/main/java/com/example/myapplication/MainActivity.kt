package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                BusinessCard()
            }
        }
    }
}

@Composable
fun BusinessCard() {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        visible = true
    }

    // High-energy Cyberpunk / Synthwave Color Palette
    val bgStart = Color(0xFF09071A)      // Deep Space Indigo
    val bgEnd = Color(0xFF020105)        // Midnight Black
    val neonCyan = Color(0xFF00F2FE)     // Electric Teal/Cyan
    val neonMagenta = Color(0xFFF355DA)  // Hot Pink/Magenta
    val cardBg = Color(0xEC0E101D)       // Premium dark glassmorphism (translucent)
    val textMuted = Color(0xFFA5B4FC)    // Soft pastel violet-grey

    // Vibrant Dual-Color Gradient for Borders
    val vibrantGradient = Brush.linearGradient(
        colors = listOf(neonCyan, neonMagenta)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(bgStart, bgEnd)
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        // Glowing Neon Background Blob 1 (Cyan Glow)
        Box(
            modifier = Modifier
                .size(350.dp)
                .offset(x = (-80).dp, y = (-200).dp)
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            Color(0x2200F2FE),
                            Color.Transparent
                        )
                    )
                )
        )

        // Glowing Neon Background Blob 2 (Magenta Glow)
        Box(
            modifier = Modifier
                .size(350.dp)
                .offset(x = 80.dp, y = 200.dp)
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            Color(0x22F355DA),
                            Color.Transparent
                        )
                    )
                )
        )

        AnimatedVisibility(
            visible = visible,
            enter = fadeIn() + slideInVertically(initialOffsetY = { 300 })
        ) {
            Card(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth()
                    // Vibrant gradient border wrapping the card
                    .border(
                        width = 1.5.dp,
                        brush = vibrantGradient,
                        shape = RoundedCornerShape(28.dp)
                    ),
                shape = RoundedCornerShape(28.dp),
                colors = CardDefaults.cardColors(containerColor = cardBg),
                elevation = CardDefaults.cardElevation(30.dp)
            ) {
                Column(
                    modifier = Modifier.padding(vertical = 40.dp, horizontal = 28.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Profile Image with a thick glowing gradient border
                    Image(
                        painter = painterResource(id = R.drawable.gwapoko),
                        contentDescription = "Profile",
                        modifier = Modifier
                            .size(140.dp)
                            .clip(CircleShape)
                            .border(4.dp, vibrantGradient, CircleShape)
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "John Lloyd Valmoria",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White,
                        fontFamily = FontFamily.SansSerif
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Information Technology Student",
                        fontSize = 15.sp,
                        color = textMuted,
                        fontWeight = FontWeight.Medium
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // Developer Tag in Electric Cyan
                    Text(
                        text = "SOFTWARE DEVELOPER",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Black,
                        color = neonCyan,
                        letterSpacing = 2.5.sp
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    // Contact row 1 with Cyan highlights
                    ContactRow(
                        icon = "☎",
                        text = "+63 912 345 6789",
                        iconColor = neonCyan
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    // Contact row 2 with Magenta highlights
                    ContactRow(
                        icon = "✉",
                        text = "jlvalmoria03@gmail.com",
                        iconColor = neonMagenta
                    )
                }
            }
        }
    }
}

@Composable
fun ContactRow(icon: String, text: String, iconColor: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Color(0xFF141529), // Deep cosmic purple-dark container
                RoundedCornerShape(14.dp)
            )
            .border(
                width = 1.dp,
                brush = Brush.horizontalGradient(
                    colors = listOf(Color(0x1AFFFFFF), Color(0x05FFFFFF))
                ),
                shape = RoundedCornerShape(14.dp)
            )
            .padding(horizontal = 20.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = icon,
            fontSize = 22.sp,
            color = iconColor
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = text,
            fontSize = 15.sp,
            color = Color.White,
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BusinessCardPreview() {
    BusinessCard()
}