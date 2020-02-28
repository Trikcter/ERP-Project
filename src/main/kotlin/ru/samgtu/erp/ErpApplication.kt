package ru.samgtu.erp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ErpApplication

fun main(args: Array<String>) {
	runApplication<ErpApplication>(*args)
}
