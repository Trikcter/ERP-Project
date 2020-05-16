package ru.samgtu.erp.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import ru.samgtu.erp.model.Address
import java.util.*

@Repository
interface AddressRepository : JpaRepository<Address, Long> {
    fun findByTitle(@Param("title") title: String): Optional<Address>
}