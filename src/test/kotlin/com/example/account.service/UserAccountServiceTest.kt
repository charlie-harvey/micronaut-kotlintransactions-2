package com.example.account.service

import com.example.account.domain.CreateUserAccountObject
import com.example.account.entities.UserAccount
import com.example.common.createUserAccountObjectArb
import com.example.common.userAccountEntityArb
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.arbitrary.*
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import io.mockk.clearAllMocks
import kotlinx.coroutines.runBlocking

@MicronautTest(startApplication = false, transactional = false)
open class UserAccountServiceTest(
    private val userAccountService: UserAccountService
) : AnnotationSpec() {

    private lateinit var createUserAccountObject: CreateUserAccountObject
    private lateinit var validUserAccount: UserAccount

    private val siteId = 10000

    @BeforeEach
    open fun beforeEach() = runBlocking {
        println(" **** beforeEach")
        createUserAccountObject = createUserAccountObjectArb(siteId = siteId, grade = "4").single()
        println(" **** beforeEach: delete userAccount")
        userAccountService.deleteByUsernameAndSiteId(createUserAccountObject.username, createUserAccountObject.siteId)
        println(" **** beforeEach: create userAccount")
        validUserAccount = userAccountService.createUserAccount(createUserAccountObject)
        println(" **** beforeEach done")
    }

    @AfterEach
    open fun afterEach() = runBlocking {
        println(" **** afterEach: delete userAccount")
        userAccountService.deleteByUsernameAndSiteId(createUserAccountObject.username, createUserAccountObject.siteId)
        clearAllMocks()
        println(" **** afterEach done")
    }

    @Test
    open suspend fun findByUsername() {
        val ua = userAccountEntityArb(
            siteId = validUserAccount.siteId,
            tobedeleted = false,
            active = true,
            username = "pants"
        ).next()
        userAccountService.save(ua)

        userAccountService.findBySiteIdAndUsername(
            siteId = validUserAccount.siteId,
            username = "pants"
        ).shouldBe(ua)

        userAccountService.deleteByUsernameAndSiteId(ua.username, ua.siteId)
    }
}
