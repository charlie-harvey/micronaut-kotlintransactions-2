package com.example.account.service

import com.example.common.userAccountEntityArb
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.arbitrary.next
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest

@MicronautTest(startApplication = false, transactional = false)
open class UserAccountServiceTest(
    private val userAccountService: UserAccountService
) : AnnotationSpec() {

    private val siteId = 10000

    @Test
    suspend fun findByUsername() {
        println(" **** findByUsername")
        val ua = userAccountEntityArb(
            siteId = siteId,
            tobedeleted = false,
            active = true,
            username = "pants"
        ).next()
        println(" **** findByUsername: saving user")
        userAccountService.save(ua)
        println(" **** findByUsername: user saved")

        println(" **** findByUsername: find user")
        userAccountService.findBySiteIdAndUsername(
            siteId = siteId,
            username = "pants"
        ).shouldBe(ua)
        println(" **** findByUsername: user found")

        println(" **** findByUsername: deleting user")
        userAccountService.deleteByUsernameAndSiteId(ua.username, ua.siteId)
        println(" **** findByUsername: user deleted")
    }

    @Test
    suspend fun findByUserIdsIn() {
        println(" **** findByUserIdsIn")
        val ua = userAccountEntityArb(
            siteId = siteId,
            tobedeleted = false,
            active = true
        ).next()
        println(" **** findByUserIdsIn: saving user")
        userAccountService.save(ua)
        println(" **** findByUserIdsIn: user saved")

        println(" **** findByUserIdsIn: find users")
        userAccountService.findByIdsIn(
            siteId = siteId,
            ids = listOf(ua.id.uuid)
        ).shouldBe(ua)
        println(" **** findByUserIdsIn: users found")

        println(" **** findByUsername: deleting user")
        userAccountService.deleteByUsernameAndSiteId(ua.username, ua.siteId)
        println(" **** findByUsername: user deleted")
    }
}
