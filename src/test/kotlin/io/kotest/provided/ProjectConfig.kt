package io.kotest.provided

import com.example.common.TestContainersMySQLProjectListener
import io.kotest.core.config.AbstractProjectConfig
import io.micronaut.test.extensions.kotest5.MicronautKotest5Extension

object ProjectConfig : AbstractProjectConfig() {
    override fun extensions() = listOf(
        MicronautKotest5Extension,
        TestContainersMySQLProjectListener
    )
}
