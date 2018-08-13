package com.spanning.atm

import grails.plugin.springsecurity.annotation.Secured

@Secured('ROLE_USER')
class AccountController {

    def springSecurityService
    def accountService
    def messageSource

    def index() {
    }

    def balance() {
        User user = springSecurityService.currentUser
        double balance = accountService.balance(user.username)
        render(view:'balance', model: [balance: balance])
    }

    def deposit() {
    }

    def doDeposit() {
        User user = springSecurityService.currentUser
        double amount = params.double('amount') ?: 0.00
        accountService.deposit(user.username, amount)
        render(view:'index')
    }

    def withdrawl() {
    }

    def doWithdrawl() {
        User user = springSecurityService.currentUser
        double amount = params.double('amount') ?: 0.00
        try {
            accountService.withdrawl(user.username, amount)
            render(view: 'index')
        } catch (DailyLimitException dle) {
            String errMsg = messageSource.getMessage('default.daily.limit.exceeded', [] as Object[], request.locale)
            render(view: 'withdrawl', model: [errorMsg : errMsg])
        } catch (AccountBalanceException abe) {
            String errMsg = messageSource.getMessage('default.account.balance.exceeded', [] as Object[], request.locale)
            render(view: 'withdrawl', model: [errorMsg : errMsg])
        }
    }
}
