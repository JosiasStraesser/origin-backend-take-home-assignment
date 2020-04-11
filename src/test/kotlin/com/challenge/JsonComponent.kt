package com.challenge

import java.io.File
import org.json.JSONObject
import org.springframework.stereotype.Component

@Component
class JsonComponent {

    fun jsonObject(path: String): JSONObject {

        val resource = this.javaClass.classLoader.getResource(path)
        val jsonString: String = File(resource.path).readText(Charsets.UTF_8)

        return JSONObject(jsonString)
    }
}
