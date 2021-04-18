package edu.nccu.aps.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import edu.nccu.aps.dom.MorderDetail;
import edu.nccu.aps.entity.Morder;
import edu.nccu.aps.entity.MrouteM;
import edu.nccu.aps.repository.MorderRepository;
import edu.nccu.aps.repository.MrouteMRepository;
import edu.nccu.aps.util.DateUtil;
import edu.nccu.aps.repository.MrouteDRepository;

@Service
public class MorderServiceImpl implements MorderService {

	//此為寫資料運用邏輯的地方，以題目為例通常是呼叫 repository 的 JPA 對資料進行搜查的 method，還沒有複雜的運算
	
	@Autowired
	MorderRepository morderRepository;
	@Autowired
	MrouteMRepository mrouteMRepository;
	@Autowired
	EntityManagerFactory entityManagerFactory;
	@Autowired
	MrouteDRepository mrouteDRepository;

	@Override
	public void importMorder(MultipartFile file, int index) {
		Workbook tWorkbook;
		try {
			tWorkbook = WorkbookFactory.create(file.getInputStream());
			Sheet tXSSFSheet = tWorkbook.getSheetAt(index);
			Row tRow;
			Cell tCell;
			if (tXSSFSheet != null) {
				switch (index) {
				case 1:
					for (int i = 2; i < tXSSFSheet.getLastRowNum() + 1; i++) {
						tRow = tXSSFSheet.getRow(i);
						if (tRow != null) {
							Morder tMorder = new Morder();
							tCell = tRow.getCell(0);
							if (tCell.getCellType() == CellType.NUMERIC) {
								tMorder.setId(String.valueOf((int) tCell.getNumericCellValue()));
							} else if (tCell.getCellType() == CellType.STRING) {
								tMorder.setId(tCell.getStringCellValue());
							}
							tCell = tRow.getCell(1);
							if (tCell.getCellType() == CellType.NUMERIC) {
								tMorder.setPlanNo(String.valueOf((int) tCell.getNumericCellValue()));
							} else if (tCell.getCellType() == CellType.STRING) {
								tMorder.setPlanNo(tCell.getStringCellValue());
							}

							tCell = tRow.getCell(2);
							tMorder.setPreSDate(tCell.getDateCellValue());
							tCell = tRow.getCell(3);
							tMorder.setPreEDate(tCell.getDateCellValue());
							tCell = tRow.getCell(4);
							tMorder.setQuan((int) tCell.getNumericCellValue());
							tCell = tRow.getCell(5);
							tMorder.setStateId(tCell.getStringCellValue());
							tCell = tRow.getCell(6);
							tMorder.setState((int) tCell.getNumericCellValue());
							tCell = tRow.getCell(7);
							tMorder.setMkTypeId(tCell.getStringCellValue());
							tCell = tRow.getCell(8);
							tMorder.setMkType((int) tCell.getNumericCellValue());
							tCell = tRow.getCell(9);
							tMorder.setCategoryId(tCell.getStringCellValue());
							tCell = tRow.getCell(10);
							tMorder.setCategory((int) tCell.getNumericCellValue());
							tCell = tRow.getCell(11);
							tMorder.setGdId(tCell.getStringCellValue());
							tCell = tRow.getCell(12);
							tMorder.setGdNo(tCell.getStringCellValue());
							morderRepository.save(tMorder);
						}
					}
					break;
				case 2:
					for (int i = 2; i < tXSSFSheet.getLastRowNum() + 1; i++) {
						tRow = tXSSFSheet.getRow(i);
						if (tRow != null) {
							MrouteM tMrouteM = new MrouteM();
							tCell = tRow.getCell(0);
							if (tCell.getCellType() == CellType.NUMERIC) {
								tMrouteM.setId(String.valueOf((int) tCell.getNumericCellValue()));
							} else if (tCell.getCellType() == CellType.STRING) {
								tMrouteM.setId(tCell.getStringCellValue());
							}
							tCell = tRow.getCell(1);
							tMrouteM.setBillNo(tCell.getStringCellValue());
							tCell = tRow.getCell(2);
							tMrouteM.setStateId(tCell.getStringCellValue());
							tCell = tRow.getCell(3);
							if (tCell.getCellType() == CellType.NUMERIC) {
								tMrouteM.setState(String.valueOf((int) tCell.getNumericCellValue()));
							} else if (tCell.getCellType() == CellType.STRING) {
								tMrouteM.setState(tCell.getStringCellValue());
							}
							tCell = tRow.getCell(4);
							if (tCell.getCellType() == CellType.NUMERIC) {
								tMrouteM.setPlanNo(String.valueOf((int) tCell.getNumericCellValue()));
							} else if (tCell.getCellType() == CellType.STRING) {
								tMrouteM.setPlanNo(tCell.getStringCellValue());
							}
							tCell = tRow.getCell(5);
							if (tCell.getCellType() == CellType.NUMERIC) {
								tMrouteM.setPlanId(String.valueOf((int) tCell.getNumericCellValue()));
							} else if (tCell.getCellType() == CellType.STRING) {
								tMrouteM.setPlanId(tCell.getStringCellValue());
							}
							tCell = tRow.getCell(6);
							tMrouteM.setGdId(tCell.getStringCellValue());
							tCell = tRow.getCell(7);
							tMrouteM.setGdNo(tCell.getStringCellValue());
							tCell = tRow.getCell(8);
							tMrouteM.setNote(tCell.getStringCellValue());
							mrouteMRepository.save(tMrouteM);
						}
					}
					break;
				case 3:
					break;
				default:
					break;
				}

			}
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//上方會有 override 的原因是因為習慣在 MorderService 這個 Interface 定義好 method，在到 lmpl 這邊做改寫
	//此為儲存動作
	@Override
	public Morder saveMorder(Morder morder) {
		return morderRepository.save(morder);
	}

	//此為移除動作
	@Override
	public void removeMorder(Morder morder) {
		morderRepository.delete(morder);
	}
	
	//此為根據特定ID查詢動作
	@Override
	public Morder queryMorderById(String morderId) {
		System.out.println("Hello"+morderRepository.findById(morderId));
		return morderRepository.findById(morderId);
		
	}
	
	//此為查詢動作
	@Override
	public List<Morder> queryAllMorder() {
		List<Morder> tMorderList = new ArrayList<Morder>();
		morderRepository.findAll().forEach(tMorderList::add);
		System.out.println(tMorderList);
//		EntityManager tEntityManager = entityManagerFactory.createEntityManager();
//		Query tQuery = tEntityManager.createQuery("Select id, planNo, stateId from Morder");
//
//		@SuppressWarnings("unchecked")
//		List<Object[]> tMorderDetailList = tQuery.getResultList();
//		System.out.println(tMorderDetailList);
//		tEntityManager.close();
//		List<Morder> tMorderDetailNewList = new ArrayList<Morder>();
//		for (Object[] o : tMorderDetailList) {
//			Morder tMorderDetail = new Morder();
//			tMorderDetail.setId((String) o[0]);
//			tMorderDetail.setPlanNo((String) o[1]);
//			tMorderDetail.setStateId((String)o[2]);
//			tMorderDetailNewList.add(tMorderDetail);
//		}
//		return tMorderDetailNewList;
		return tMorderList;
	}
	
	
	@Override
	public List<Morder> queryMorderWithPageAndSort(Pageable pageRequest) {
		Page<Morder> tMorderPage = morderRepository.findAll(pageRequest);
		return tMorderPage.getContent();
	}

	@Override
	public List<Morder> queryMorderByStartAndEndWithPageAndSort(Date preSDate, Date preEDate, Pageable pageRequest) {
		Page<Morder> tMorderPage = morderRepository.findByPreSDateAfterAndPreEDateBefore(preSDate, preEDate, pageRequest);
		return tMorderPage.getContent();
	}
	
	//此為主要運行 Inner Join 的地方 ( Inner Join 特殊的地方就是直接在 Service 層定義，因為 Repository 主要是一個 Class 對一個 Entity，他們預設的 method 不能交雜
	@Override
	public List<MorderDetail> queryMorderDetail() {
		EntityManager tEntityManager = entityManagerFactory.createEntityManager();
		Query tQuery = tEntityManager.createQuery("Select mo.id,mo.preSDate,mo.preEDate,mrm.billNo,mrm.note,mrd.wstNo from Morder mo inner join MrouteM mrm on mo.id=mrm.planId inner join MrouteD mrd on mrm.id=mrd.billId order by mrd.wstNo DESC");
		@SuppressWarnings("unchecked")
		List<Object[]> tMorderDetailList = tQuery.getResultList();
		tEntityManager.close();
		List<MorderDetail> tMorderDetailNewList = new ArrayList<MorderDetail>();
		for (Object[] o : tMorderDetailList) {
			MorderDetail tMorderDetail = new MorderDetail();
			tMorderDetail.setId((String) o[0]);
			tMorderDetail.setPreSDate(DateUtil.getjQueryStrDate((Date) o[1]));
			tMorderDetail.setPreEDate(DateUtil.getjQueryStrDate((Date) o[2]));
			tMorderDetail.setBillNo((String) o[3]);
			tMorderDetail.setNote((String) o[4]);
			tMorderDetail.setWstNo((String)o[5]);
			tMorderDetailNewList.add(tMorderDetail);
		}
		return tMorderDetailNewList;
	}
	

	@Override
	public Morder queryMorderByStateId(String stateId) {
		return morderRepository.findByStateId(stateId);
	}
}