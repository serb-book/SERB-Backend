/**
 * Offer related operation, this doesn't deal with books directly
 * For book specific operations see BookDAO
 * 
 * @date Apr17
 * @author abdullah
 */
package com.serb_backend.dal;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.serb_backend.dto.ExchangeDTO;
import com.serb_backend.dto.RentDTO;
import com.serb_backend.dto.SellDTO;

public interface OfferDAO {
	/**
	 * @param sellingOffer
	 * @return if operation was done successfully
	 */
	boolean addSellingOffer(SellDTO sellingOffer);
	
	/**
	 * @param rentingOffer
	 * @return if operation was done successfully
	 */
	boolean addRentingOffer(RentDTO rentingOffer);
	
	/**
	 * @param exchangeOffer
	 * @return if operation was done successfully
	 */
	boolean addExchangingOffer(ExchangeDTO exchangeOffer);
	
	
	/**
	 * takes an excel file containing offer data then start 
	 * storing them
	 * @param excelFile
	 * @return
	 */
	boolean addOffersFromExcelFile(XSSFWorkbook excelFile);
}
