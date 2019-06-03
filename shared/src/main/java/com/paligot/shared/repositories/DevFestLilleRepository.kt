package com.paligot.shared.repositories

import com.paligot.shared.networks.DevFestLilleNetworkDataSource
import io.reactivex.Single
import java.util.*

interface DevFestLilleRepository {
    fun talks(): Single<List<Talk>>

    companion object {
        fun create(): DevFestLilleRepository = DevFestLilleRepositoryImpl(DevFestLilleNetworkDataSource.create())
    }
}

const val BREAK = "BREAK"
data class Talk(
    val title: String,
    val abstract: String,
    val level: Level,
    val format: String,
    val category: String,
    val hour: Date,
    val room: Room,
    val speakers: List<Speaker>
) {
    val isBreak: Boolean
        get() = title == BREAK
}

data class Speaker(
    val displayName: String,
    val role: String,
    val photoURL: String,
    val bio: String,
    val company: String?,
    val github: String?,
    val twitter: String?
)

enum class Level {
    BEGINNER,
    INTERMEDIATE,
    UNKNOWN;
}

enum class Room {
    ACTION,
    COMEDY,
    SCIENCE_FICTION,
    THRILLER,
    UNKNOWN;
}

fun String.toLevel(): Level = when (this) {
    "beginner" -> Level.BEGINNER
    "intermediate" -> Level.INTERMEDIATE
    else -> Level.UNKNOWN
}

fun String.toRoom(): Room = when (this) {
    "room-1" -> Room.ACTION
    "room-2" -> Room.COMEDY
    "room-3" -> Room.SCIENCE_FICTION
    "room-4" -> Room.THRILLER
    else -> Room.UNKNOWN
}