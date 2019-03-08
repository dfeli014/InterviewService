package com.revature.utils;

import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class ListToPage {
	public static PageImpl getPage(List<?> L, Pageable page) {
		int start = page.getPageNumber()*page.getPageSize();
		int end = ((page.getPageNumber()+1)*page.getPageSize());
		if(end>=L.size()) {
			end=L.size();
		}
		return new PageImpl(L.subList(start, end), page, L.size());
	}
}
