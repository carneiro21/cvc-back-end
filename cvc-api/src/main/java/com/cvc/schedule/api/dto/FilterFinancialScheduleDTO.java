package com.cvc.schedule.api.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FilterFinancialScheduleDTO extends QueryPageDTO<FinancialScheduleDTO>{

	private Long id;
	
	private String originAccount;
	
	private String destinationAccount;
	
	private BigDecimal transferValue;
	
	private String transferDataInit;
	
	private String transferDataFinal;
	
	private String schedulingDateInit;
	
	private String schedulingDateFinal;
}
