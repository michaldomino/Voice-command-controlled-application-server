package com.example.voicecommands

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class VoiceCommandsApplication

fun main(args: Array<String>) {
    runApplication<VoiceCommandsApplication>(*args)
}
