package com.example.database

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object DBGroupUserTable: Table<DBGroupUserEntity>("group_user"){
    val id = int("id").primaryKey().bindTo{it.id}
    val groupid = int("groupid").bindTo{it.groupid}
    val userid = int("userid").bindTo{it.userid}
}

interface DBGroupUserEntity: Entity<DBGroupUserEntity> {
    companion object : Entity.Factory<DBGroupUserEntity>()

    val id: Int
    val groupid: Int
    val userid: Int
}
