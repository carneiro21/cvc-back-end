package com.cvc.schedule.api.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class QueryPageDTO<T> {

	protected Integer page;

	private String orderBy;

	protected Long records;
	protected Integer pages;

	protected Integer recordsPage;
	
	private List<T> list = new ArrayList<>();

	public Integer getPage() {
		if (page == null) {
			return 0;
		}
		return page;
	}

	public Integer getRecordsPage() {
		if (recordsPage == null) {
			return 10;
		}
		return recordsPage;
	}

	public void setRecords(Long records) {
		this.records = records;
		if (this.records != null) {
			this.pages = (int) (records + getRecordsPage() - 1) / getRecordsPage();
		}
	}

	public QueryPageDTO<T> page(Integer page) {
		this.page = page;
		return this;
	}

	public QueryPageDTO<T> list(List<T> list) {
		this.list = list;
		return this;
	}

	public QueryPageDTO<T> pages(Integer pages) {
		this.pages = pages;
		return this;
	}

	public QueryPageDTO<T> records(Long records) {
		this.records = records;
		return this;
	}
}
