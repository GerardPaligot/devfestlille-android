package com.paligot.shared.repositories

import com.paligot.shared.networks.DevFestLilleNetworkDataSource
import io.reactivex.Single

class DevFestLilleRepositoryImpl(private val network: DevFestLilleNetworkDataSource) : DevFestLilleRepository {
    override fun talks(): Single<List<Talk>> = network.talks()
}