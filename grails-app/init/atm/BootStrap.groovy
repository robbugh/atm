package atm

import com.spanning.atm.User
import com.spanning.atm.Role
import com.spanning.atm.UserRole
import com.spanning.atm.AccountDetails

class BootStrap {

    def init = { servletContext ->
        def userRole = new Role(authority: 'ROLE_USER').save()
        def testUser = new User(username: '1234', password: '1234').save()
        def accountDetails = new AccountDetails(username: '1234', dailyLimit: 250.00, balance: 500.00).save()
        def testUser1 = new User(username: '5678', password: '5678').save()
        def accountDetails1 = new AccountDetails(username: '5678', dailyLimit: 500.00, balance: 100.00).save()

        UserRole.create testUser, userRole
        UserRole.create testUser1, userRole

        UserRole.withSession {
            it.flush()
            it.clear()
        }
    }
    def destroy = {
    }
}
