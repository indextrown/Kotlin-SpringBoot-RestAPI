package com.board.util

object Logger {

    enum class LogType(val icon: String) {
        DEBUG("🟢"),
        WARNING("🟡"),
        ERROR("🔴")
    }

    fun d(message: String) = log(LogType.DEBUG, message)
    fun w(message: String) = log(LogType.WARNING, message)
    fun e(message: String) = log(LogType.ERROR, message)

    private fun log(type: LogType, message: String): String {
        val stackTrace = Thread.currentThread().stackTrace

        // ✅ com.board로 시작하지만 Logger는 제외
        val element = stackTrace.firstOrNull {
            it.className.startsWith("com.board") &&
                    !it.className.contains("Logger")
        }

        val fileName = element?.fileName?.substringBeforeLast(".") ?: "UnknownFile"
        val line = element?.lineNumber ?: -1
        val function = element?.methodName ?: "UnknownFunc"

        val logMessage = "[${type.icon}] [$fileName:$line] $function — $message"
        println(logMessage)
        return logMessage
    }
}
