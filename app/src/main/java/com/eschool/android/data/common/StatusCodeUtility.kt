package com.eschool.android.data.common

import com.eschool.android.R

fun getMessageStringResource(code: Int) =
    when (code) {
        0 -> R.string.ok
        1, 2 -> R.string.invalid_request
        3 -> R.string.internal_server_error
        4 -> R.string.not_found
        5 -> R.string.too_many_requests
        6 -> R.string.already_activated
        7 -> R.string.invalid_request
        8 -> R.string.bad_auth
        9 -> R.string.invalid_request
        else -> R.string.unknown_error
    }