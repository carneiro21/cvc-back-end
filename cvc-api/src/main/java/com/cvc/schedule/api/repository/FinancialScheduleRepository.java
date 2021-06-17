package com.cvc.schedule.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cvc.schedule.api.entity.FinancialScheduleEntity;

public interface FinancialScheduleRepository extends JpaRepository<FinancialScheduleEntity, Long>, FinancialScheduleRepositoryCustom{

}
