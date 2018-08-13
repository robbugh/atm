package com.spanning.atm

class AccountDetails {

    String username
    double dailyLimit = 250.00
    double balance = 0.00

    static constraints = {
        username blank: false, unique: true
    }

    static mapping = {
    }
}
