package com.amatucci.andrea.beers

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.amatucci.andrea.beers.util.BeerColor

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.koin.test.KoinTest

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.amatucci.andrea.beers", appContext.packageName)
    }

    @Test
    fun beerColor(){
        val colorResourceId = BeerColor.getColorResourceId(26.0)
        assertEquals(R.color.beer_srm_25, colorResourceId)

        val darkestColor = BeerColor.getColorResourceId(41.0)
        assertEquals(R.color.beer_srm_40, darkestColor)
    }

    @Test
    fun beerList(){

    }
}