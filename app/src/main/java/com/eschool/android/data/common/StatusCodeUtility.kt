package com.eschool.android.data.common

import com.eschool.android.R

fun getMessageStringResource(code: Int) =
    when (code) {
        /* OK */
        0 -> R.string.ok

        /* Invalid signature */
        1 -> R.string.invalid_request

        /* Invalid body */
        2 -> R.string.invalid_request

        /* Internal server error */
        3 -> R.string.internal_server_error

        /* Requested data wasn't found */
        4 -> R.string.not_found

        /* Too many requests */
        5 -> R.string.too_many_requests

        /* Already activated */
        6 -> R.string.already_activated

        /* Expired */
        7 -> R.string.expired

        /* Bad auth */
        8 -> R.string.bad_auth

        /* Bad body */
        9 -> R.string.invalid_request

        /* Account was banned */
        10 -> R.string.account_banned

        /* Account was deleted */
        11 -> R.string.account_deleted

        /* Unknown error (see logs) */
        else -> R.string.unknown_error
    }