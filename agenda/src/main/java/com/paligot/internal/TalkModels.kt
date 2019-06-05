package com.paligot.internal

import android.os.Parcelable
import androidx.annotation.StringRes
import com.paligot.shared.networks.DevFestLilleNetworkDataSource.Companion.BREAK
import com.paligot.shared.repositories.Speaker
import com.paligot.shared.repositories.Talk
import kotlinx.android.parcel.Parcelize
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
data class TalkUi(
    val title: String,
    val abstract: String,
    @StringRes val level: Int,
    @StringRes val format: Int,
    val category: String,
    val startTime: String,
    val endTime: String,
    val scheduleDisplay: String,
    @StringRes val room: Int,
    val speakers: List<SpeakerUi>
) : Parcelable {

    val isBreak: Boolean
        get() = title == BREAK

    companion object {
        fun fromRepo(talk: Talk): TalkUi {
            val longFormat = SimpleDateFormat("EEEE, d MMMM, HH:mm", Locale.getDefault())
            val shortFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
            val shortEndTime = shortFormat.format(talk.endTime)
            return TalkUi(
                talk.title,
                talk.abstract,
                talk.levelDisplayName,
                talk.formatDisplayName,
                talk.category,
                shortFormat.format(talk.startTime),
                shortEndTime,
                "${longFormat.format(talk.startTime)} - $shortEndTime".capitalize(),
                talk.roomDisplayName,
                talk.speakers.map { SpeakerUi.fromRepo(it) }
            )
        }
    }
}

@Parcelize
data class SpeakerUi(
    val displayName: String,
    val role: String,
    val photoURL: String,
    val bio: String,
    val company: String?,
    val githubUrl: String?,
    val twitterUrl: String?
) : Parcelable {
    companion object {
        fun fromRepo(speaker: Speaker): SpeakerUi = SpeakerUi(
            speaker.displayName,
            speaker.role,
            speaker.photoURL,
            speaker.bio,
            speaker.company,
            if (speaker.github != null) "https://github.com/${speaker.github}" else null,
            if (speaker.twitter != null) "https://twitter.com/${speaker.twitter}" else null
        )
    }
}