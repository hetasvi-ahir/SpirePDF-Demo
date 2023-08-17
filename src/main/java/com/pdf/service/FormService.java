package com.pdf.service;

import java.io.IOException;

/**
 * 
 * @author Hetasvi.Ahir
 *
 */
public interface FormService 
{
	/**
	 * 
	 * @param pdfFile
	 * @return {@summary Store data to database and generate PDF}
	 * @throws IOException
	 */
	public String storeData(byte[] pdfFile)  throws IOException;

}
