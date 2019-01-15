package com.spanning.atm

import grails.gorm.transactions.Transactional

@Transactional
class AccountService {

    double balance(String username) {
        AccountDetails accountDetails = AccountDetails.findByUsername(username)
        accountDetails.balance
    }

    def deposit(String username, double amount) {
        if (amount < 0.0) {
            return
        }
        amount = amount.round(2)
        AccountDetails accountDetails = AccountDetails.findByUsername(username)
        accountDetails.balance += amount
        accountDetails.save(flush: true)
    }

    def withdrawl(String username, double amount) {
        if (amount < 0.0) {
            return
        }
        amount = amount.round(2)
        AccountDetails accountDetails = AccountDetails.findByUsername(username)
        Date date = new Date().clearTime()
        DailyLimitDetails dailyLimitDetails = DailyLimitDetails.findByUsernameAndWithdrawlDate(username, date) ?: new DailyLimitDetails(username: username, withdrawlDate: date, amount: 0.0)
        if (amount > accountDetails.dailyLimit) {
            throw new DailyLimitException()
        } else if (amount + dailyLimitDetails.amount > accountDetails.dailyLimit) {
            throw new DailyLimitException()
        } else if (amount > accountDetails.balance) {
            throw new AccountBalanceException()
        } else {
            accountDetails.balance -= amount
            accountDetails.save(flush: true)
            dailyLimitDetails.amount += amount
            dailyLimitDetails.save(flush: true)
        }
    }
}
