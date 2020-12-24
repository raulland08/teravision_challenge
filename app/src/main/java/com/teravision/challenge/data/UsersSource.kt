package com.teravision.challenge.data

import com.teravision.challenge.data.api.UserRaw
import com.teravision.challenge.domain.entity.AddressEntity
import com.teravision.challenge.domain.entity.CompanyEntity
import com.teravision.challenge.domain.entity.GeoEntity
import com.teravision.challenge.domain.entity.UserEntity

class UsersSource {

    fun mapUsers(usersRaw: List<UserRaw>): List<UserEntity> {

        val userEntityList = mutableListOf<UserEntity>()

        usersRaw.forEach { userRaw ->
            val geoEntity = GeoEntity(
                lat = userRaw.address.geo.lat,
                lng = userRaw.address.geo.lng
            )

            val companyEntity = CompanyEntity(
                name = userRaw.company.name,
                catchPhrase = userRaw.company.catchPhrase,
                bs = userRaw.company.bs
            )

            val addressEntity = AddressEntity(
                userRaw.address.street,
                userRaw.address.suite,
                userRaw.address.city,
                userRaw.address.zipcode,
                geoEntity
            )

            userEntityList.add(
                UserEntity(
                    id = userRaw.id,
                    name = userRaw.name,
                    username = userRaw.username,
                    email = userRaw.email,
                    address = addressEntity,
                    phone = userRaw.phone,
                    website = userRaw.website,
                    company = companyEntity
                )
            )
        }

        return userEntityList
    }
}