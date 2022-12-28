package com.eschool.android.data.common

import android.util.Log
import okhttp3.Interceptor
import okhttp3.RequestBody
import okhttp3.Response
import okio.Buffer
import org.apache.commons.codec.binary.Base64
import org.apache.commons.codec.digest.HmacAlgorithms
import org.apache.commons.codec.digest.HmacUtils

class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()

        if (request.method == "POST") {
            val hmac = HmacUtils(HmacAlgorithms.HMAC_SHA_256, "f48c764b79433bef4115a31f094009d2")

            val digest = hmac.hmac(getBodyBytes(request.body!!))
            val sB64 = Base64.encodeBase64(digest)

            Log.d("RequestInterceptor", "SIG: ${String(sB64)}")

            builder.addHeader("Signature", String(sB64))
        }

        return chain.proceed(builder.build())
    }

    private fun getBodyBytes(body: RequestBody): ByteArray {
        val buffer = Buffer()
        body.writeTo(buffer)

        val bodyBytes = buffer.readByteArray()
        buffer.close()

        Log.d("RequestInterceptor", "Body: ${String(bodyBytes)}")

        return bodyBytes
    }
}