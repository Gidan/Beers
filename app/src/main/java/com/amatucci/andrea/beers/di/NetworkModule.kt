package com.amatucci.andrea.beers.di

import com.amatucci.andrea.beers.data.network.BeersApi
import com.amatucci.andrea.beers.data.repository.BeersRepository
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {
    single { provideRetrofit() }
    factory { provideBeersService(get()) }
    single { BeersRepository(get()) }
}

fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://api.punkapi.com/v2/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
}

fun provideBeersService(retrofit: Retrofit) : BeersApi {
    return retrofit.create(BeersApi::class.java)
}