package kr.tr.commom.expection

/**
 * TravelBProject
 * Created by NEXT2023
 * Date: 2023-07-27
 * Time: 오후 1:10
 */
sealed class Failure(var msg: String?, var retryAction: () -> Unit) : Throwable() {

    class Api(msg: String? = null) : Failure(msg, {})

    class Timeout(msg: String? = null) : Failure(msg, {})

    class NoInternet(msg: String? = null) : Failure(msg, {})

    class Unknown(msg: String? = null) : Failure(msg, {})

}