package com.mozhimen.navigatek.navigation.test

import com.mozhimen.basick.utilk.kotlin.printlog
import com.mozhimen.navigatek.navigation.helpers.DestinationUtil
import org.junit.Test

import kotlin.math.abs

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        FirstFragment::class.java.name.toString().printlog()
        FirstFragment::class.java.canonicalName?.toString().printlog()
        DestinationUtil.getDestinationId(FirstFragment::class.java).printlog()
        abs(FirstFragment::class.java.name.hashCode()).printlog()
    }
}