package com.example.common

import org.testcontainers.containers.MySQLContainer
import org.testcontainers.utility.DockerImageName

class TestMySQLContainer : MySQLContainer<TestMySQLContainer>(
    DockerImageName.parse("mysql:5.7.34").asCompatibleSubstituteFor("mysql")
) {

    override fun getDriverClassName(): String = "com.mysql.cj.jdbc.Driver"

    companion object {
        private lateinit var instance: TestMySQLContainer

        fun start() {
            if (!Companion::instance.isInitialized) {
                instance = TestMySQLContainer()
                    .withConfigurationOverride("db")
                    .withUsername("root")
                    .withPassword("")
                    .withEnv("MYSQL_ROOT_HOST", "%")
                    .withReuse(true)
                instance.start() // At this point we have a running instance as a Docker container
            }
            println(" *** DB_URL: ${instance.jdbcUrl}")
            System.setProperty("DB_URL", instance.jdbcUrl)
            println(" *** DB_HOST: ${instance.host}")
            System.setProperty("DB_HOST", instance.host)
            println(" *** DB_PORT: ${instance.firstMappedPort}")
            System.setProperty("DB_PORT", instance.firstMappedPort.toString())
            println(" *** DB_USERNAME: ${instance.username}")
            System.setProperty("DB_USERNAME", instance.username)
            println(" *** DB_PASSWORD: ${instance.password}")
            System.setProperty("DB_PASSWORD", instance.password)
        }

        fun stop() {
            if (Companion::instance.isInitialized) {
                instance.stop()
            }
        }
    }
}
