package com.spanning.atm

class DailyLimitDetails {
    String username
    Date withdrawlDate // Change to only record the date and not the time
    double amount

    static constraints = {
        username blank: false, unique: true
    }

    static mapping = {
    }
}
