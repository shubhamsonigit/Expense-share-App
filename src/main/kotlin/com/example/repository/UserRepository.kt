package com.example.repository

import com.example.entities.*

interface UserRepository {
    fun getAllUser():List<User>
    fun getUser(id: Int): User?
    fun addUser(draft: UserDraft): User
    fun addTransaction(auditDraft: AuditDraft) : Boolean
    fun addGroup(groupDraft: GroupDraft): Boolean
    fun getAllTransactionForUser(id: Int): List<Audit>
    fun getAllTransactionForGroup(id: Int): List<Audit>
}