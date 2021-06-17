package com.cvc.schedule.api.repository;

import java.util.List;

import com.cvc.schedule.api.dto.FilterFinancialScheduleDTO;
import com.cvc.schedule.api.entity.FinancialScheduleEntity;

public interface FinancialScheduleRepositoryCustom {

	public List<FinancialScheduleEntity> filterScheduleTransfer(final FilterFinancialScheduleDTO filter);
}
