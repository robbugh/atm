package com.spanning.atm

import grails.testing.gorm.DataTest
import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

class AccountServiceSpec extends Specification implements ServiceUnitTest<AccountService>, DataTest {

    void setupSpec() {
        mockDomain AccountDetails
        mockDomain DailyLimitDetails
    }

    def setup() {
    }

    def cleanup() {
    }

    void "test balance"() {
        given:
        new AccountDetails(username: '1234', dailyLimit: 250.00, balance: 100.00).save()

        when:
        double balance = service.balance('1234')

        then:
        balance == 100.00
    }

    void "test deposit"() {
        given:
        AccountDetails accountDetails = new AccountDetails(username: '1234', dailyLimit: 250.00, balance: 100.00).save()

        when:
        service.deposit('1234', 123.00)

        then:
        accountDetails.balance == 223.00
    }

    void "test withdrawl"() {
        given:
        AccountDetails accountDetails = new AccountDetails(username: '1234', dailyLimit: 250.00, balance: 100.00).save()

        when:
        service.withdrawl('1234', 10.00)

        then:
        accountDetails.balance == 90.00
    }

    // Add more tests for dailyLimit exceeded and withdrawl exceeded balance
}
