package edu.nccu.aps.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;

public class FileUtil {
	public static OutputStream convertDataToExcel(OutputStream pOutputStream, String pSheetName, List<List<String>> pData) throws FileNotFoundException, IOException {

		Workbook tWorkbook = new HSSFWorkbook();
		Sheet tSheet = tWorkbook.createSheet(pSheetName);

		CellStyle tCellStyle = tWorkbook.createCellStyle();
		tCellStyle.setAlignment(HorizontalAlignment.CENTER);
		tCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		setBorder(tCellStyle);

		CellStyle tTitleCellStyle = tWorkbook.createCellStyle();
		tTitleCellStyle.setAlignment(HorizontalAlignment.CENTER);
		tTitleCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		tTitleCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		tTitleCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		setBorder(tTitleCellStyle);

		for (int i = 0; i < pData.size(); i++) {
			Row tCellRow = tSheet.createRow(i);
			List<String> tDataRow = pData.get(i);
			for (int j = 0; j < tDataRow.size(); j++) {
				Cell tCell = tCellRow.createCell(j);
				tCell.setCellValue(tDataRow.get(j));
				if (i == 0) {
					tCell.setCellStyle(tTitleCellStyle);
				} else {
					tCell.setCellStyle(tCellStyle);
				}
			}
		}
		tWorkbook.write(pOutputStream);
		return pOutputStream;
	}

	private static void setBorder(CellStyle tCellStyle) {
//		tCellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		tCellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
//		tCellStyle.setBorderLeft(CellStyle.BORDER_THIN);
		tCellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
//		tCellStyle.setBorderRight(CellStyle.BORDER_THIN);
		tCellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
//		tCellStyle.setBorderTop(CellStyle.BORDER_THIN);
		tCellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
	}

	/**
	 * 副檔名
	 * 
	 * @param pName
	 * @return
	 */
	public static String getSubName(String pName) {
		if (pName.indexOf(".") > 0) {
			return pName.substring(pName.lastIndexOf("."));
		}
		return "";
	}

	/**
	 * 檔名
	 * 
	 * @param pName
	 * @return
	 */
	public static String getName(String pName) {
		if (pName.indexOf(".") > 0) {
			return pName.substring(0, pName.lastIndexOf("."));
		}
		return pName;
	}

	/**
	 * 複製檔案
	 * 
	 * @param pSrcFile
	 * @param pDestFile
	 * @throws IOException
	 */
	public static void copyFile(File pSrcFile, File pDestFile) throws IOException {
		FileUtils.copyFile(pSrcFile, pDestFile);
	}

	public static void wirteStringToFile(File pFile, String pData) throws IOException {
		FileUtils.writeStringToFile(pFile, pData);
	}
}