package com.example.Controller

import com.example.entities.AuditDraft
import com.example.entities.GroupDraft
import com.example.entities.User
import com.example.entities.UserDraft
import com.example.repository.MySqlUserRepository
import com.example.repository.UserRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class ExpenseController {
    private val repository: UserRepository = MySqlUserRepository()
    suspend fun getAllUser(call: ApplicationCall){
        call.respond(repository.getAllUser())
    }

    suspend fun getUser(call: ApplicationCall){
        val id = call.parameters["id"]?.toIntOrNull()
        if(id == null){
            call.respond(
                HttpStatusCode.BadRequest,
                "id parameter has to be a number"
            )
            return
        }
        val user = repository.getUser(id)

        if(user == null){
            call.respond(
                HttpStatusCode.NotFound,
                "Not found"
            )
        }
        else{
            call.respond(user);
        }
    }

    suspend fun addUser(call: ApplicationCall){
        val userDraft = call.receive<UserDraft>()
        val user = repository.addUser(userDraft)
        call.respond(user)
    }

    suspend fun addGroup(call: ApplicationCall){
        val groupDraft = call.receive<GroupDraft>()
        val group = repository.addGroup(groupDraft)
        call.respond(group)
    }

    suspend fun addTransaction(call: ApplicationCall){
        val auditDraft = call.receive<AuditDraft>()
        if(repository.addTransaction(auditDraft))
            call.respond(HttpStatusCode.Accepted,"Transaction added")
        else
            call.respond(HttpStatusCode.NotAcceptable,"Some error occured")
    }

    suspend fun getAllTransactionForUser(call: ApplicationCall){
        val id = call.parameters["id"]?.toIntOrNull()
        if(id == null){
            call.respond(
                HttpStatusCode.BadRequest,
                "id parameter has to be a number"
            )
            return
        }
        call.respond(repository.getAllTransactionForUser(id))
    }

    suspend fun getAllTransactionForGroup(call: ApplicationCall){
        val id = call.parameters["id"]?.toIntOrNull()
        if(id == null){
            call.respond(
                HttpStatusCode.BadRequest,
                "id parameter has to be a number"
            )
            return
        }
        call.respond(repository.getAllTransactionForGroup(id))
    }
}