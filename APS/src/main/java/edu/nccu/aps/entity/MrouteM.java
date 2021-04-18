package edu.nccu.aps.entity;

import java.io.Serializable;

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
public class MrouteM implements Serializable {
	private static final long serialVersionUID = -5408473259275097318L;

	@Id
	protected String id;

	protected String billNo;
	protected String stateId;
	protected String state;
	protected String planNo;
	protected String planId;
	protected String gdId;
	protected String gdNo;
	protected String note;
	
}