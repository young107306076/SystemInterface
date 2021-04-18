package edu.nccu.aps.dom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MorderDetail {
	protected String id;
	protected String preSDate;
	protected String preEDate;
	protected String billNo;
	protected String note;
	protected String wstNo;
}