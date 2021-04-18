package edu.nccu.aps.bean;

import java.util.Date;

import javax.persistence.Column;

import edu.nccu.aps.entity.Morder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MorderBean {
//	private static final long serialVersionUID = -4610217049599444775L;

	protected String preSDateString;
	protected String preEDateString;
	protected String[] removeId;
	protected String id;
	protected String planNo;
	protected String stateId;
	protected int state;
	protected String gdId;
	protected String gdNo;
	protected int quan;
	protected String pUnit;
	protected String mkTypeId;
	protected int mkType;
	protected String crDateString;
	protected Date crDate;
	protected String flow;
	protected String note;
	protected String categoryId;
	protected int category;

	public MorderBean(Morder morder) {
	}
}