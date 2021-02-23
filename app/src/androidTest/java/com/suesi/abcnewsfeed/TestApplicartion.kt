package com.suesi.abcnewsfeed

class TestApplication : NewsApplication() {
    override fun getBaseUrl(): String = "http://127.0.0.1:8080"
}
