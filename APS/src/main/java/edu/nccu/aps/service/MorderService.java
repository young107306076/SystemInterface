package edu.nccu.aps.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import edu.nccu.aps.dom.MorderDetail;
import edu.nccu.aps.entity.Morder;

public interface MorderService {
	public void importMorder(MultipartFile file, int index);

	public Morder saveMorder(Morder morder);

	public void removeMorder(Morder morder);

	public Morder queryMorderById(String morderId);

	public List<Morder> queryAllMorder();

	public List<Morder> queryMorderWithPageAndSort(Pageable pageRequest);

	public List<Morder> queryMorderByStartAndEndWithPageAndSort(Date preSDate, Date preEDate, Pageable pageRequest);

	public List<MorderDetail> queryMorderDetail();
	
	public Morder queryMorderByStateId(String stateId);
	
}