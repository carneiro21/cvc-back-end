package com.cvc.schedule.api.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FINANCIAL_SCHEDULE")
public class FinancialScheduleEntity  implements java.io.Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID_FINANCIAL_SCHEDULE", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "ORIGIN_ACCOUNT", unique = true, nullable = false, length = 200)
	private String originAccount;
	
	@Column(name = "DESTINATION_ACCOUNT", unique = true, nullable = false, length = 200)
	private String destinationAccount;
	
	@Column(name = "TRANSFER_VALUE", nullable = false, precision = 10, scale = 4)
	private BigDecimal transferValue;
	
	@Column(name = "RATE", nullable = false, precision = 10, scale = 4)
	private BigDecimal rate;
	
	@Column(name = "TRANSFER_DATA", nullable = false, length = 7)
	private LocalDate transferData;
	
	@Column(name = "SCHEDULING_DATE", nullable = false, length = 7)
	private LocalDate schedulingDate;

}
