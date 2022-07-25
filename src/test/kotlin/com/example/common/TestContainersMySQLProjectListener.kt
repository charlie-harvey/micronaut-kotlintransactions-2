package com.example.common

import io.kotest.core.listeners.AfterProjectListener
import io.kotest.core.listeners.BeforeProjectListener

@Suppress("unused")
object TestContainersMySQLProjectListener : BeforeProjectListener, AfterProjectListener {

    override suspend fun beforeProject() {
        TestMySQLContainer.start()
    }

    override suspend fun afterProject() {
        TestMySQLContainer.stop()
    }
}
