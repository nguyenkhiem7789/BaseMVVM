package com.nguyen.basemvvm.utils

import androidx.annotation.LongDef

/**
 * Created by apple on 9/11/17.
 */
class Constant {
    companion object {
        val BASE_URL = "https://test-services.vntrip.vn/vntrip/"

        val BASE_IMAGE_URL = "https://statics.vntrip.vn/data-v2/img_origin/"

        @LongDef(REQUEST_NONE,
            REQUEST_RUNNING,
            REQUEST_SUCCEEDED,
            REQUEST_FAILED)
        @Retention(AnnotationRetention.SOURCE)
        annotation class RequestState

        const val REQUEST_NONE = 0L
        const val REQUEST_RUNNING = 1L
        const val REQUEST_SUCCEEDED = 2L
        const val REQUEST_FAILED = 3L

        //login
        val TOKEN_TYPE = "Bearer"
        val CLIENT_ID = "16GuKzV8K1@92YcLg85uR5ku;peVriRZSn!1.UTh"
        val CLIENT_SECRET = "TCuMmpT!EGz5UT7GE3D?s-ikA5i0sCV2pI7cFYqc!0c;z1oIyCeLsVb_ZDRdI7KOg4Pem7XKz4UU0yJ2K37I5;3Sp2UVw!tNK-ps4vaguqr09MopDwB_7larJWAmXHyv"
        val GRANT_PASS_TYPE = "password"
    }

    @RequestState
    private var state: Long = 0L

    fun setRequestState(@RequestState state: Long) {
        this.state = state
    }
}

class KeyCode {
    companion object {
        const val SUCCESS = "success"
    }
}