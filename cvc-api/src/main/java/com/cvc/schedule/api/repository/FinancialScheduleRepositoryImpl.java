package com.cvc.schedule.api.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

import com.cvc.schedule.api.dto.FilterFinancialScheduleDTO;
import com.cvc.schedule.api.entity.FinancialScheduleEntity;
import com.cvc.schedule.api.util.DateUtil;

public class FinancialScheduleRepositoryImpl implements FinancialScheduleRepositoryCustom{
	
	@Autowired
	private EntityManager manager;

	@Override
	public List<FinancialScheduleEntity> filterScheduleTransfer(FilterFinancialScheduleDTO filter) {
		StringBuilder sql = new StringBuilder();

		String orderBy = "financialSchedule.transferData desc";
		if (filter.getOrderBy() != null) {
			orderBy = filter.getOrderBy();
		}
		
		StringBuilder sqlFrom = new StringBuilder();
		sqlFrom.append("select financialSchedule from FinancialScheduleEntity financialSchedule ");
		sql.append("where (:originAccount is null or financialSchedule.originAccount like :originAccount) ");
		sql.append("and (:destinationAccount is null or financialSchedule.destinationAccount like :destinationAccount) ");
		sql.append("and (:transferValue is null or financialSchedule.transferValue = :transferValue) ");
		sql.append("and (:schedulingDateInit is null or (financialSchedule.schedulingDate between :schedulingDateInit and :schedulingDateFinal)) ");
		sql.append("and (:transferDataInit is null or (financialSchedule.transferData between :transferDataInit and :transferDataFinal)) ");
		
		if (filter.getPage() == 0) {
			filter.setPage(1);
			filter.setRecords(count(sql.toString(), filter));
		}
		
		TypedQuery<FinancialScheduleEntity> query = manager.createQuery(sqlFrom.toString() + sql.append("order by ").append(orderBy), FinancialScheduleEntity.class);
		parameters(query, filter);
		query.setMaxResults(filter.getRecordsPage());
		query.setFirstResult((filter.getPage() - 1) * filter.getRecordsPage());
		return query.getResultList();
	}
	
	public Long count(String sql, FilterFinancialScheduleDTO filter) {

		StringBuilder sqlFrom = new StringBuilder();
		sqlFrom.append("select COUNT(financialSchedule.id) from FinancialScheduleEntity financialSchedule  ");
		sqlFrom.append(sql);

		Query query = manager.createQuery(sqlFrom.toString());
		parameters(query, filter);
		return (Long) query.getSingleResult();
	}
	
	private void parameters(Query query, FilterFinancialScheduleDTO filter) {
		query.setParameter("originAccount", filter.getOriginAccount());
		query.setParameter("destinationAccount", filter.getDestinationAccount());
		query.setParameter("transferValue", filter.getTransferValue());
		query.setParameter("schedulingDateInit", DateUtil.transformStringToLocalDate(filter.getSchedulingDateInit()));
		query.setParameter("schedulingDateFinal", DateUtil.transformStringToLocalDate(filter.getSchedulingDateFinal()));
		query.setParameter("transferDataInit", DateUtil.transformStringToLocalDate(filter.getTransferDataInit()));
		query.setParameter("transferDataFinal", DateUtil.transformStringToLocalDate(filter.getTransferDataFinal()));
		
	}

}
