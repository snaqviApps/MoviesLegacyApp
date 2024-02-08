package learn.edu.movieslegacyapp.playground

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

//fun main(){
//    println("Hi from funda-practice\n\n")
//    routine(1, 500)
//    routine(2, 300)
//}

fun main() = runBlocking {
//fun main()  {
    println("main thread started, Hi from funda-practice\n")
    joinAll (
        async { coRoutine(1, 550)},
        async { coRoutine(2, 300) }
    )

    /**
    val twoAsync: Deferred<Unit> = async {
        coRoutine(4, 550)
        coRoutine(5, 200)
    }
    twoAsync.start()
    delay(100)
    twoAsync.cancel()

    println("\nmain thread ends")

    */
}


fun routine(num:Int, delay:Long){
    println("$num, started")
    Thread.sleep(delay)
    Thread.sleep(delay)
    println("$num, ended")
}

suspend fun coRoutine(num:Int, delay:Long){
        println("#$num coRoutine started")
        Thread.sleep(delay)
    println("#$num coRoutine ended")


}