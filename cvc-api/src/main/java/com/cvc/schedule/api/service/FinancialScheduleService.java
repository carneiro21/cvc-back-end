package com.cvc.schedule.api.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.cvc.schedule.api.dto.FilterFinancialScheduleDTO;
import com.cvc.schedule.api.dto.FinancialScheduleDTO;
import com.cvc.schedule.api.dto.QueryPageDTO;
import com.cvc.schedule.api.entity.FinancialScheduleEntity;
import com.cvc.schedule.api.exception.BusinessException;
import com.cvc.schedule.api.factory.FinancialScheduleFactory;
import com.cvc.schedule.api.repository.FinancialScheduleRepository;

@Service
public class FinancialScheduleService {

	@Autowired
	private FinancialScheduleRepository financialScheduleRepository;

	public QueryPageDTO<FinancialScheduleDTO> filterScheduleTransfer(FilterFinancialScheduleDTO filter) {
		List<FinancialScheduleDTO> listResult = FinancialScheduleFactory
				.toFinancialScheduleDTO(this.financialScheduleRepository.filterScheduleTransfer(filter));

		QueryPageDTO<FinancialScheduleDTO> queryPage = new QueryPageDTO<FinancialScheduleDTO>().page(filter.getPage())
				.records(filter.getRecords()).pages(filter.getPages()).list(listResult);
		queryPage.setRecordsPage(filter.getRecordsPage());

		return queryPage;
	}

	public FinancialScheduleDTO findId(Long id) {
		if (id == null) {
			throw new BusinessException("ID is required.", HttpStatus.BAD_REQUEST);
		}
		return FinancialScheduleFactory.toFinancialScheduleDTO(this.financialScheduleRepository.findById(id).get());
	}

	public FinancialScheduleDTO createFinancialSchedule(FinancialScheduleDTO financialScheduleDTO) {
		if (financialScheduleDTO.getOriginAccount() == null) {
			throw new BusinessException("Origin Account is required.", HttpStatus.BAD_REQUEST);
		}
		if (financialScheduleDTO.getDestinationAccount() == null) {
			throw new BusinessException("Destination Account is required.", HttpStatus.BAD_REQUEST);
		}
		if (financialScheduleDTO.getTransferData() == null) {
			throw new BusinessException("Transfer Data is required.", HttpStatus.BAD_REQUEST);
		}
		if (financialScheduleDTO.getSchedulingDate() == null) {
			throw new BusinessException("Scheduling Date is required.", HttpStatus.BAD_REQUEST);
		}
		if (financialScheduleDTO.getSchedulingDate().isBefore(LocalDate.now())) {
			throw new BusinessException("Date invalid! Scheduling Date is before Transfer Data.", HttpStatus.BAD_REQUEST);
		}
		if (financialScheduleDTO.getTransferValue() == null) {
			throw new BusinessException("Transfer Value is required.", HttpStatus.BAD_REQUEST);
		}
		
		BigDecimal rate = calculateRate(financialScheduleDTO);
		financialScheduleDTO.setRate(rate);
		BigDecimal totalTransferValue = financialScheduleDTO.getTransferValue().add(rate);
		financialScheduleDTO.setTransferValue(totalTransferValue);
		
		FinancialScheduleEntity entity = financialScheduleRepository.save(FinancialScheduleFactory.toFinancialScheduleEntity(financialScheduleDTO));
		return FinancialScheduleFactory.toFinancialScheduleDTO(entity);
	}

	public BigDecimal calculateRate(FinancialScheduleDTO financialScheduleDTO) {
		BigDecimal rate = null;
		BigDecimal percentRate = null;
		BigDecimal percent = new BigDecimal(100);
		
		int days = Period.between(financialScheduleDTO.getTransferData(), financialScheduleDTO.getSchedulingDate()).getDays();
		
		if (days == 0) {
			percentRate = new BigDecimal(3);
			rate =   financialScheduleDTO.getTransferValue().divide(percent).multiply(percentRate);
		}else if (days <= 10) {
			percentRate = new BigDecimal(12).multiply(new BigDecimal(days));
			rate =   financialScheduleDTO.getTransferValue().divide(percent).multiply(percentRate);
		}else if (days > 10 && days <= 20) {
			percentRate = new BigDecimal(8);
			rate =   financialScheduleDTO.getTransferValue().divide(percent).multiply(percentRate);
		}else if (days > 20 && days <= 30) {
			percentRate = new BigDecimal(6);
			rate =   financialScheduleDTO.getTransferValue().divide(percent).multiply(percentRate);
		}else if (days > 30 && days <= 40 && financialScheduleDTO.getTransferValue().doubleValue() > 100.000) {
			percentRate = new BigDecimal(2);
			rate =   financialScheduleDTO.getTransferValue().divide(percent).multiply(percentRate);
		}
		
		return rate;
	}

	public void deleteFinancialSchedule(FinancialScheduleDTO financialScheduleDTO) {
		this.financialScheduleRepository.delete(FinancialScheduleFactory.toFinancialScheduleEntity(financialScheduleDTO));
	}
	
}
