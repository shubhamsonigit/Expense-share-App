package com.example.database

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.double
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object DBAuditTable: Table<DBAuditEntity>("audit"){
    val userid1 = int("userid1").bindTo{it.userid1}
    val userid2 = int("userid2").bindTo{it.userid2}
    val amount = double("amount").bindTo{it.amount}

}

interface DBAuditEntity: Entity<DBAuditEntity> {
    companion object : Entity.Factory<DBAuditEntity>()

    val userid1: Int
    val userid2: Int
    val amount: Double
}
