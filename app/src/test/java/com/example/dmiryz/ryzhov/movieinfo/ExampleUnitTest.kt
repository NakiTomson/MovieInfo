package com.example.dmiryz.ryzhov.movieinfo

import org.junit.*

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    //Проинициализировать то что мы тестируем
    //Сделать некоторое воздействие
    //Результат совпадает с тем что должно быть

//    @BeforeClass - для всех тестов один раз
//    @Before - роцессы перед каждым тестом
//    @Test - проверка условия
//    @After - После каждого теста
//    @AfterClass - после всего короче

    lateinit var string:String

    @Before
    fun initData(){
        string = "Hello"
    }


    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test //(expected = RuntimeException::class.)
    fun getNumber(){
//        assertEquals("Hello",string)
//        Assert.assertEquals("Wrong epta","Hello",string)
//        Assert.assertFalse("hello",string.isEmpty())
        assertNotNull("The car should be null", string);
//        Assert.assertThat("hello",1,1)
    }


}
