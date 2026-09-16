package com.example.myapplication.data.network.dto

import com.example.myapplication.domain.Message
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.longOrNull

fun MessageDto.toDomain(): Message {
    // Robust date parsing for mixed types (MockAPI quirk)
    val time = when (val element = createdAt) {
        is JsonPrimitive -> element.longOrNull ?: 0L
        else -> 0L
    }
    
    return Message(
        id = id ?: "",
        sender = sender ?: "Unknown",
        text = text ?: "",
        createdAt = time
    )
}

fun List<MessageDto>.toDomain(): List<Message> =
    map { it.toDomain() }
        .sortedByDescending { it.createdAt } // ENSURE newest is ALWAYS on top
