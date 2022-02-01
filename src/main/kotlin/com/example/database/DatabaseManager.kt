package com.example.database

import com.example.entities.AuditDraft
import com.example.entities.GroupDraft
import com.example.entities.User
import com.example.entities.UserDraft
import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.ktorm.entity.*

class DatabaseManager {
    //config

    private val hostname = "localhost"
    private val databaseName = "Expense-sharing-App"
    private val username = "root"
    private val password = "Swastik108@"

    //database
    private val ktormDatabase: Database

    init{
        val jdbcUrl = "jdbc:mysql://$hostname:3306/$databaseName?user=$username&password=$password&useSSL=false"
        ktormDatabase = Database.connect(jdbcUrl)
    }

    fun getAllUsers(): List<DBUserEntity>{
        return ktormDatabase.sequenceOf(DBUserTable).toList()
    }

    fun getUser(id: Int): DBUserEntity?{
        return ktormDatabase.sequenceOf(DBUserTable).firstOrNull{it.id eq id}
    }

    fun addUser(draft: UserDraft): User {
        val userid = ktormDatabase.insertAndGenerateKey(DBUserTable){
            set(DBUserTable.name,draft.name)
            set(DBUserTable.email,draft.email)
            set(DBUserTable.phone,draft.phone)
        } as Int

        val users = ktormDatabase.sequenceOf(DBUserTable)
        for(user in users){
            if(user.id == userid)
                continue

            ktormDatabase.insert(DBAuditTable) {
                set(DBAuditTable.userid1, user.id)
                set(DBAuditTable.userid2, userid)
                set(DBAuditTable.amount,0)
            }
            ktormDatabase.insert(DBAuditTable) {
                set(DBAuditTable.userid1, userid)
                set(DBAuditTable.userid2, user.id)
                set(DBAuditTable.amount,0)
            }
        }

        ktormDatabase.insertAndGenerateKey(DBGroupUserTable){
            set(DBGroupUserTable.groupid, draft.groupno)
            set(DBGroupUserTable.userid, userid)
        }

        return User(userid,draft.name,draft.email,draft.phone)
    }

    fun addTransaction(auditDraft: AuditDraft): Boolean{
        val numberOfUsers = ktormDatabase.sequenceOf(DBGroupUserTable)
            .filter {
                it.groupid eq auditDraft.groupid
            }
            .aggregateColumns {
                count()
            } as Int

        val equalamount = auditDraft.amount/numberOfUsers
        val users =
            ktormDatabase.sequenceOf(DBGroupUserTable)
                .filter {
                    it.groupid eq auditDraft.groupid
                }

        for(user in users){
            ktormDatabase.update(DBAuditTable){
                val oldamount = it.amount
                val newamount = oldamount.plus(equalamount)

                set(it.amount, newamount)
                where { it.userid2 eq auditDraft.userid}
                where { it.userid1 eq user.id}
            }
        }

        return true
    }

    fun addGroup(groupDraft: GroupDraft){

    }
    fun getAllTransactionForUser(id: Int){

    }
    fun getAllTransactionForGroup(id: Int){

    }
}