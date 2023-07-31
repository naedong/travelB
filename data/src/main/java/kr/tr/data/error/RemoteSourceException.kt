package kr.tr.data.error

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-06-30
 * Time: 오전 10:24
 */
sealed class RemoteSourceException(val messageResource: Any?) : RuntimeException() {
    class Connection(messageResource: Int) : RemoteSourceException(messageResource)
    class Unexpected(messageResource: Int) : RemoteSourceException(messageResource)
    class Timeout(messageResource: Int) : RemoteSourceException(messageResource)
    class Client(messageResource: Int) : RemoteSourceException(messageResource)
    class Server(messageResource: Any?) : RemoteSourceException(messageResource)
}
