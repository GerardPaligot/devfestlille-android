package com.paligot.shared.networks

import com.paligot.shared.repositories.Talk
import io.reactivex.Single

interface DevFestLilleNetworkDataSource {
    fun talks(): Single<List<Talk>>

    companion object {
        fun create(): DevFestLilleNetworkDataSource = DevFestLilleNetworkDataSourceImpl(DevFestLilleService.create())
    }
}