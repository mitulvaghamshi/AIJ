package me.mitul.aij.free

import me.mitul.aij.BuildConfig

class AdManager {
    fun hello() {
        val currentApi = BuildConfig.API_URL

        if (BuildConfig.IS_PRO) {
//            showPremiumFeatures()
        } else {
//            showAds()
        }
    }
}
