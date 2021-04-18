package edu.nccu.aps.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

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
public class MrouteD implements Serializable {
	private static final long serialVersionUID = -5408473259275097318L;

	@Id
	protected String id;

	protected String billId;
	protected int line_no;
	protected String wstNo;
	protected int mkType;
	protected String mcmId;
	protected String mcmNo;
	protected double waitHour;
	protected double machHour;
	protected double manHour;
	protected double mignHour;
	protected Date begDate;
	protected Date endDate;
	protected int stopIf;
	protected Date stopDate;
	protected int mkBase;
	protected int hourRate;
	protected double quan;
	protected String moldNo;
	protected double acQuan;
	protected double factHour;
	protected Date realBegDate;
	protected Date realEndDate;
	protected Date planbDate;
	protected Date planeDate;
}