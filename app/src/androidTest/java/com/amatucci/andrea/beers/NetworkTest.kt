package com.amatucci.andrea.beers

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.amatucci.andrea.beers.data.repository.BeersRepository
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.koin.test.get

@RunWith(AndroidJUnit4::class)
class NetworkTest : KoinTest {

    @Test
    fun components() {
        val repository = get<BeersRepository>()
        assertNotNull(repository)
    }

    @Test
    fun fetchFirstPageNoFilters(){
        val repository = get<BeersRepository>()
        assertNotNull(repository)
        runBlocking {
            val beers = repository.getBeers(1)
            assertNotNull(beers)
            assert(beers.size == 25)
            val first = beers[0]
            assertEquals(1, first.id)
        }
    }

    @Test
    fun fetchFirstPageWithFilters(){
        val repository = get<BeersRepository>()
        assertNotNull(repository)
        runBlocking {
            val beers = repository.getBeers(1, "01-2009", "12-2020")
            assertNotNull(beers)
            assert(beers.size == 25)
            val first = beers[0]
            assertEquals(3, first.id)
        }
    }



}