package edu.nccu.aps.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;

import edu.nccu.aps.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Morder implements Serializable {
	//此為串聯資料庫欄位的class : entity
	private static final long serialVersionUID = -5408473259275097318L;

	@Id
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
	
	protected Date preSDate;
	
	protected Date preEDate;
	
	protected Date crDate;
	
	protected String flow;
	
	protected String note;
	
	protected String categoryId;
	
	protected int category;
	
	
}