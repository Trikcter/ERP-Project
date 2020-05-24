package ru.samgtu.erp.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.samgtu.erp.model.BalanceRegistry

@Repository
interface BalanceRegistryRepository : JpaRepository<BalanceRegistry, Long> {
}