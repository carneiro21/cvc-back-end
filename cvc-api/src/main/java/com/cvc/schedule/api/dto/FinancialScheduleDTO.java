package com.cvc.schedule.api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FinancialScheduleDTO {
	
	private Long id;
	
	private String originAccount;
	
	private String destinationAccount;
	
	private BigDecimal transferValue;
	
	private BigDecimal rate;
	
	private LocalDate transferData;
	
	private LocalDate schedulingDate;

}
