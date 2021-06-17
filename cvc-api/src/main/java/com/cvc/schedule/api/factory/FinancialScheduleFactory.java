package com.cvc.schedule.api.factory;

import java.util.ArrayList;
import java.util.List;

import com.cvc.schedule.api.dto.FinancialScheduleDTO;
import com.cvc.schedule.api.entity.FinancialScheduleEntity;

public class FinancialScheduleFactory {

	public static List<FinancialScheduleDTO> toFinancialScheduleDTO(List<FinancialScheduleEntity> lisFinancialSchedule) {

		List<FinancialScheduleDTO> financialScheduleDTO = new ArrayList<FinancialScheduleDTO>();

		for (FinancialScheduleEntity financialSchedule : lisFinancialSchedule) {
			financialScheduleDTO.add(toFinancialScheduleDTO(financialSchedule));
		}

		return financialScheduleDTO;
	}

	public static FinancialScheduleDTO toFinancialScheduleDTO(FinancialScheduleEntity financialScheduleEntity) {

		if (financialScheduleEntity == null) {
			return null;
		}

		return FinancialScheduleDTO.builder()
				.id(financialScheduleEntity.getId())
				.originAccount(financialScheduleEntity.getOriginAccount())
				.destinationAccount(financialScheduleEntity.getDestinationAccount())
				.rate(financialScheduleEntity.getRate())
				.schedulingDate(financialScheduleEntity.getSchedulingDate())
				.transferData(financialScheduleEntity.getTransferData())
				.transferValue(financialScheduleEntity.getTransferValue())
				.build();
	}

	public static List<FinancialScheduleEntity> toFinancialScheduleEntity(List<FinancialScheduleDTO> listFinancialScheduleDTO) {

		List<FinancialScheduleEntity> listFinancialScheduleEntity = new ArrayList<FinancialScheduleEntity>();

		for (FinancialScheduleDTO financialScheduleDTO : listFinancialScheduleDTO) {
			listFinancialScheduleEntity.add(toFinancialScheduleEntity(financialScheduleDTO));
		}

		return listFinancialScheduleEntity;
	}

	public static FinancialScheduleEntity toFinancialScheduleEntity(FinancialScheduleDTO financialScheduleDTO) {

		if (financialScheduleDTO == null) {
			return null;
		}

		return FinancialScheduleEntity.builder()
				.id(financialScheduleDTO.getId())
				.originAccount(financialScheduleDTO.getOriginAccount())
				.destinationAccount(financialScheduleDTO.getDestinationAccount())
				.rate(financialScheduleDTO.getRate())
				.schedulingDate(financialScheduleDTO.getSchedulingDate())
				.transferData(financialScheduleDTO.getTransferData())
				.transferValue(financialScheduleDTO.getTransferValue())
				.build();
	}

}
