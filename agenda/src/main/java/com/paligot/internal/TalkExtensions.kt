package com.paligot.internal

import com.paligot.agenda.R
import com.paligot.shared.repositories.Format
import com.paligot.shared.repositories.Level
import com.paligot.shared.repositories.Room
import com.paligot.shared.repositories.Talk

val Talk.roomDisplayName
    get() = when (room) {
        Room.ACTION -> R.string.agenda_room_action
        Room.COMEDY -> R.string.agenda_room_comedy
        Room.SCIENCE_FICTION -> R.string.agenda_room_sf
        Room.THRILLER -> R.string.agenda_room_thriller
        Room.UNKNOWN -> R.string.agenda_room_unknown
    }

val Talk.levelDisplayName
    get() = when (level) {
        Level.BEGINNER -> R.string.agenda_level_beginner
        Level.INTERMEDIATE -> R.string.agenda_level_intermediate
        Level.UNKNOWN -> R.string.agenda_level_unknown
    }

val Talk.formatDisplayName
    get() = when (format) {
        Format.CONFERENCE -> R.string.agenda_format_conference
        Format.QUICKIE -> R.string.agenda_format_quickie
        Format.PAUSE -> R.string.agenda_pause
    }