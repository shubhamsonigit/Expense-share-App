package com.example.database

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object DBUserTable: Table<DBUserEntity>("user"){
    val id = int("id").primaryKey().bindTo{it.id}
    val name = varchar("name").bindTo{it.name}
    val email = varchar("email").bindTo{it.email}
    val phone = varchar("phone").bindTo{it.phone}
}

interface DBUserEntity: Entity<DBUserEntity> {
    companion object : Entity.Factory<DBUserEntity>()

    val id: Int
    val name: String
    val email: String
    val phone:String
}
