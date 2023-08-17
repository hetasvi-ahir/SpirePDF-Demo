package com.pdf.controller;

import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pdf.form.CreateFillableFormFields;
import com.pdf.service.FormService;

/**
 * 
 * @author Hetasvi.Ahir
 *
 */
@RestController
public class MainController {

	@Autowired
	private FormService service;

	/**
	 * 
	 * @return Returns a PDF form
	 * @throws Exception
	 */
	@GetMapping("/generateform")
	public String create() throws Exception {
		CreateFillableFormFields c = new CreateFillableFormFields();
		c.createpdf();
		return "PDF Form generated successfully and stored to your local device";
	}

	@RequestMapping("/createpdf")
	public String createPdf() {
		return "Created";
	}

	/**
	 * 
	 * @return Generates PDF and store data to database By User Input
	 * @throws IOException
	 */
	@RequestMapping("/storedata")
	public String store(@RequestBody byte[] pdfFile) throws IOException {

		service.storeData(pdfFile);
		return "Stored";

	}
}
