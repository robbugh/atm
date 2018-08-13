package com.spanning.atm

import grails.plugin.springsecurity.SpringSecurityService
import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification

class AccountControllerSpec extends Specification implements ControllerUnitTest<AccountController> {

    def setup() {
    }

    def cleanup() {
    }

    void "test balance"() {
        given:
        AccountService accountServiceMock = Mock(AccountService)
        accountServiceMock.balance(_) >> { 42.00 }
        controller.accountService = accountServiceMock

        User user = new User(username: '1234', password: '1234')

        // Make user the authenticated user
        def springSecurityServiceMock=Mock(SpringSecurityService)
        springSecurityServiceMock.isLoggedIn() >> true
        springSecurityServiceMock.getCurrentUser() >> user
        controller.springSecurityService = springSecurityServiceMock

        when:
        controller.balance()

        then:
        status == 200
        model.balance == 42.00
    }

    void "test deposit"() {
        when:
        controller.deposit()

        then:
        status == 200
    }

    void "test doDeposit"() {
        when:
        controller.deposit()

        then:
        status == 200
    }

    void "test withdrawl"() {
        when:
        controller.deposit()

        then:
        status == 200
    }

    void "test doWithdrawl"() {
        given:
        AccountService accountServiceMock = Mock(AccountService)
        accountServiceMock.withdrawl(_, _) >> { }
        controller.accountService = accountServiceMock

        User user = new User(username: '1234', password: '1234')

        // Make user the authenticated user
        def springSecurityServiceMock=Mock(SpringSecurityService)
        springSecurityServiceMock.isLoggedIn() >> true
        springSecurityServiceMock.getCurrentUser() >> user
        controller.springSecurityService = springSecurityServiceMock

        when:
        controller.doWithdrawl()

        then:
        status == 200
    }

    void "test doWithdrawl when balance exceeded"() {
        given:
        AccountService accountServiceMock = Mock(AccountService)
        accountServiceMock.withdrawl(_, _) >> {
            throw new AccountBalanceException()
        }
        controller.accountService = accountServiceMock

        User user = new User(username: '1234', password: '1234')

        // Make user the authenticated user
        def springSecurityServiceMock=Mock(SpringSecurityService)
        springSecurityServiceMock.isLoggedIn() >> true
        springSecurityServiceMock.getCurrentUser() >> user
        controller.springSecurityService = springSecurityServiceMock

        messageSource.addMessage("default.account.balance.exceeded", request.locale, "balance exceeded")

        when:
        controller.doWithdrawl()

        then:
        status == 200
        view == '/account/withdrawl'
        model.errorMsg == "balance exceeded"
    }

    void "test doWithdrawl when daily limit exceeded"() {
        given:
        AccountService accountServiceMock = Mock(AccountService)
        accountServiceMock.withdrawl(_, _) >> {
            throw new AccountBalanceException()
        }
        controller.accountService = accountServiceMock

        User user = new User(username: '1234', password: '1234')

        // Make user the authenticated user
        def springSecurityServiceMock=Mock(SpringSecurityService)
        springSecurityServiceMock.isLoggedIn() >> true
        springSecurityServiceMock.getCurrentUser() >> user
        controller.springSecurityService = springSecurityServiceMock

        messageSource.addMessage("default.account.balance.exceeded", request.locale, "daily limit exceeded")

        when:
        controller.doWithdrawl()

        then:
        status == 200
        view == '/account/withdrawl'
        model.errorMsg == "daily limit exceeded"
    }
}
