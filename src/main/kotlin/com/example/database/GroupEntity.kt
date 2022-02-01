package com.example.database

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object DBGroupTable: Table<DBGroupEntity>("group"){
    val id = int("id").primaryKey().bindTo{it.id}
    val name = varchar("name").bindTo{it.name}
}

interface DBGroupEntity: Entity<DBGroupEntity> {
    companion object : Entity.Factory<DBGroupEntity>()

    val id: Int
    val name: String
}
