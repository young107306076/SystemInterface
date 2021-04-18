package edu.nccu.aps.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import edu.nccu.aps.entity.Morder;

public interface MorderRepository extends PagingAndSortingRepository<Morder, Integer> {
	//這邊即是呼叫查詢資料 Interface 的放置之處，主要功能為 JPA
	public Morder findById(String id);

	public Page<Morder> findByPreSDateAfterAndPreEDateBefore(Date preSDate, Date preEDate, Pageable pageable);

	public Morder findByStateId(String stateId);
	
	
}
