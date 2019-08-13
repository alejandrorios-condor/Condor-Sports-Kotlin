package com.alejandrorios.condorsports.data.base

import io.mockk.MockKAnnotations
import org.junit.Before

/**
 * Created by Alejandro Rios on 2019-08-12
 */
interface MockableTest {

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }
}

