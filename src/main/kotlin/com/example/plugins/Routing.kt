package com.example.plugins

import com.example.Controller.ExpenseController
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*

fun Application.configureRouting()
{
    // Starting point for a Ktor app:
    routing {
        val Controller : ExpenseController = ExpenseController()
        get("/") {
            call.respondText("Welcome to Expense sharing application")
        }

        get("/Users") {
            Controller.getAllUser(call)
        }

        get("/User/{id}"){
            Controller.getUser(call)
        }

        get("/getAllTransactionForUser/{id}") {
            Controller.getAllTransactionForUser(call)
        }

        get("/getAllTransactionForGroup/{id}") {
            Controller.getAllTransactionForGroup(call)
        }

        post("/addUser") {
            Controller.addUser(call)
        }

        post("/addGroup") {
            Controller.addGroup(call)
        }

        post("/addTransaction"){
            Controller.addTransaction(call)
        }
    }
}
