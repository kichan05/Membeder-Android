package com.heechan.membeder.model.data

import com.heechan.membeder.model.data.auth.User

object SingletonObject {
    private var _userData: User? = null
    var userData : User?
        get() = _userData
        set(value) {
            _userData = value
        }


    private var _token: String? = null
    var token : String
        get() = _token!!
        set(v) {
            _token = v
        }
}