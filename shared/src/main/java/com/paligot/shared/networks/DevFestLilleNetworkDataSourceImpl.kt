package com.paligot.shared.networks

import com.paligot.shared.repositories.*
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import java.util.*

class DevFestLilleNetworkDataSourceImpl(private val service: DevFestLilleService) : DevFestLilleNetworkDataSource {
    override fun talks(): Single<List<Talk>> {
        return service.getTalks().map { it.values }.singleOrError().zipWith(
            service.getSpeakers().map { it.values }.singleOrError(),
            BiFunction<Collection<TalkNetwork>, Collection<SpeakerNetwork>, List<Talk>> { talks, speakers ->
                return@BiFunction talks.map { talk ->
                    return@map Talk(
                        talk.title,
                        talk.abstract,
                        talk.level.toLevel(),
                        talk.format,
                        talk.category,
                        Date(talk.hour.timestamp * 1000),
                        talk.room.toRoom(),
                        speakers
                            .filter { speaker -> talk.speakers.find { it == speaker.displayName } != null }
                            .map {
                                Speaker(
                                    it.displayName,
                                    it.role,
                                    it.photoURL,
                                    it.bio,
                                    if (it.company == "-") null else it.company,
                                    it.github,
                                    it.twitter
                                )
                            }
                    )
                } + arrayListOf(
                    createPause(10, 50),
                    createPause(12, 0),
                    createPause(14, 50),
                    createPause(17, 0)
                )
            })
            .map { it.sortedBy { it.hour.time } }
    }

    private fun createPause(hour: Int, minutes: Int): Talk {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, 2019)
        calendar.set(Calendar.MONTH, Calendar.APRIL)
        calendar.set(Calendar.DAY_OF_MONTH, 14)
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minutes)
        return Talk(BREAK, "", Level.UNKNOWN, "", "", Date(calendar.timeInMillis), Room.UNKNOWN, emptyList())
    }
}