package mx.devlabs.zmovies

import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService

class AppExecutors {

    companion object{
        private var instance: AppExecutors? = null

        fun get(): AppExecutors {
            return (instance ?: AppExecutors())
        }
    }

    private val mNetworkIO = Executors.newScheduledThreadPool(3)

    fun networkIO(): ScheduledExecutorService {
        return mNetworkIO
    }
}














