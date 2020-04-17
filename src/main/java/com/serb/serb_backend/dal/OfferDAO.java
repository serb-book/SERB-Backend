/**
 * Offer related operation, this doesn't deal with books directly
 * For book specific operations see BookDAO
 * 
 * @date Apr17
 * @author abdullah
 */
package com.serb.serb_backend.dal;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.serb.serb_backend.dto.OfferDTO;

public interface OfferDAO {
	/**
	 * @param offer
	 * @return if operation was done successfully
	 */
	boolean addOffer(OfferDTO offer);
	
	/**
	 * takes an excel file containing offer data then start 
	 * storing them
	 * @param excelFile
	 * @return
	 */
	boolean addOffersFromExcelFile(XSSFWorkbook excelFile);
}
