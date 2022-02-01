package com.example.repository

import com.example.database.DatabaseManager
import com.example.entities.*

class MySqlUserRepository : UserRepository{
    private val database = DatabaseManager()
    override fun getAllUser(): List<User> {
        return database.getAllUsers().map{User(it.id,it.name,it.email,it.phone)}
    }

    override fun getUser(id: Int): User? {
        return database.getUser(id)?.let{User(it.id,it.name,it.email,it.phone)}
    }

    override fun addUser(draft: UserDraft): User {
        return database.addUser(draft)
    }

    override fun addTransaction(auditDraft: AuditDraft): Boolean {
        return database.addTransaction(auditDraft)
    }

    override fun addGroup(groupDraft: GroupDraft): Boolean {
        return database.addGroup(groupDraft)
    }

    override fun getAllTransactionForUser(id: Int): List<Audit> {
        return database.getAllTransactionForUser(id)
    }

    override fun getAllTransactionForGroup(id: Int): List<Audit> {
        return database.getAllTransactionForGroup(id)
    }
}