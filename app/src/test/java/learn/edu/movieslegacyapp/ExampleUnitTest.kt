package learn.edu.movieslegacyapp

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun when_collected_flow_multiple_time_then_return_same_values() = runBlocking {
        val coldStream = flow {
            for (i in 1..5) {
                delay(100L)
                emit(i)
            }
        }
        val collect1 = buildString {
            coldStream.collect { append(it).append(", ") }
        }.removeSuffix(", ")
        val collect2 = buildString {
            coldStream.collect { append(it).append(", ") }
        }.removeSuffix(", ")
        assertEquals("1, 2, 3, 4, 5", collect1)
        assertEquals("1, 2, 3, 4, 5", collect2)
    }
}