package com.serb_backend.dal.util;

public class Pagination {
	
	public static String paginateQuery(String query,int rowNum ,int pageIndex) {
		int start = ((pageIndex-1)*rowNum+1);
		return "SELECT TEMP.* "
				+ "FROM ( select ROWNUM rn , T.* FROM ("+ query +") T) TEMP"
				+ " where TEMP.rn between "+ start +" and "+ (start+rowNum-1) ;
	}
	
	public static String paginateQuery(String query) {
		int pageIndex = 1;
		int rowNum = 10;
		int start = ((pageIndex-1)*rowNum+1);
		return "SELECT TEMP.* "
		+ "FROM ( select ROWNUM rn , T.* FROM ("+ query +") T) TEMP"
		+ " where TEMP.rn between "+ start +" and "+ (start+rowNum-1) ;
	}
}
