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

data class Talk(
    val title: String,
    val abstract: String,
    val level: Level,
    val format: Format,
    val category: String,
    val startTime: Date,
    val endTime: Date,
    val room: Room,
    val speakers: List<Speaker>
)

data class Speaker(
    val displayName: String,
    val role: String,
    val photoURL: String,
    val bio: String,
    val company: String?,
    val github: String?,
    val twitter: String?
)

enum class Format(val minutes: Int) {
    CONFERENCE(50),
    QUICKIE(30),
    PAUSE(20)
}

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

fun String.toFormat(): Format = when (this) {
    "Quickies" -> Format.QUICKIE
    "Conférence" -> Format.CONFERENCE
    else -> Format.CONFERENCE
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