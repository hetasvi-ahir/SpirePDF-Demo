package com.pdf.form;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import com.spire.pdf.FileFormat;
import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfPageBase;
import com.spire.pdf.actions.PdfSubmitAction;
import com.spire.pdf.fields.PdfButtonField;
import com.spire.pdf.fields.PdfComboBoxField;
import com.spire.pdf.fields.PdfListFieldItem;
import com.spire.pdf.fields.PdfRadioButtonListField;
import com.spire.pdf.fields.PdfRadioButtonListItem;
import com.spire.pdf.fields.PdfTextBoxField;
import com.spire.pdf.graphics.PdfFont;
import com.spire.pdf.graphics.PdfFontFamily;
import com.spire.pdf.graphics.PdfFontStyle;
import com.spire.pdf.graphics.PdfRGBColor;
import com.spire.pdf.graphics.PdfSolidBrush;

/**
 * 
 * @author Hetasvi.Ahir
 *
 */
public class CreateFillableFormFields {
	public String createpdf() throws Exception {

		// Create a PdfDocument object
		PdfDocument doc = new PdfDocument();

		// Add a page
		PdfPageBase page = doc.getPages().add();

		// Initialize x and y coordinates
		float baseX = 220;
		float baseY = 50;

		// Create two brush objects
		PdfSolidBrush brush1 = new PdfSolidBrush(new PdfRGBColor(Color.black));

		// Create a font
		PdfFont font = new PdfFont(PdfFontFamily.Times_Roman, 12f, PdfFontStyle.Regular);
		PdfFont font2 = new PdfFont(PdfFontFamily.Times_Roman, 14f, PdfFontStyle.Bold);

		PdfFont font3 = new PdfFont(PdfFontFamily.Times_Roman, 32f, PdfFontStyle.Bold);

		// Title
		page.getCanvas().drawString("User registration form ", font3, brush1, new Point2D.Float(80, 2));

		// Add a textbox
		page.getCanvas().drawString("First Name:", font2, brush1, new Point2D.Float(110, baseY));
		Rectangle2D.Float tbxBounds = new Rectangle2D.Float(baseX, baseY, 150, 15);
		PdfTextBoxField textBox = new PdfTextBoxField(page, "firstname");
		textBox.setBounds(tbxBounds);
		textBox.setFont(font);
		textBox.setRequired(true);
		textBox.setMaxLength(10);
		doc.getForm().getFields().add(textBox);
		baseY += 30;

		// Add a textbox
		page.getCanvas().drawString("Last Name:", font2, brush1, new Point2D.Float(110, baseY));
		Rectangle2D.Float tbx2Bounds = new Rectangle2D.Float(baseX, baseY, 150, 15);
		PdfTextBoxField textBox2 = new PdfTextBoxField(page, "lastname");
		textBox2.setBounds(tbx2Bounds);
		textBox2.setFont(font);
		textBox2.setRequired(true);
		textBox2.setMaxLength(10);
		doc.getForm().getFields().add(textBox2);
		baseY += 30;

		page.getCanvas().drawString("Mobile Number:", font2, brush1, new Point2D.Float(110, baseY));
		Rectangle2D.Float tbx3Bounds = new Rectangle2D.Float(baseX, baseY, 150, 15);
		PdfTextBoxField textBox3 = new PdfTextBoxField(page, "number");
		textBox3.setBounds(tbx3Bounds);
		textBox3.setFont(font);
		textBox3.setRequired(true);
		textBox3.setMaxLength(10);
		doc.getForm().getFields().add(textBox3);
		baseY += 30;

		page.getCanvas().drawString("Email:", font2, brush1, new Point2D.Float(110, baseY));
		Rectangle2D.Float tbx4Bounds = new Rectangle2D.Float(baseX, baseY, 150, 15);
		PdfTextBoxField textBox4 = new PdfTextBoxField(page, "email");
		textBox4.setBounds(tbx4Bounds);
		textBox4.setFont(font);
		textBox4.setRequired(true);

		doc.getForm().getFields().add(textBox4);
		baseY += 30;

		page.getCanvas().drawString("Address Line 1 :", font2, brush1, new Point2D.Float(110, baseY));
		Rectangle2D.Float tbx5Bounds = new Rectangle2D.Float(baseX, baseY, 150, 15);
		PdfTextBoxField textBox5 = new PdfTextBoxField(page, "address1");
		textBox5.setBounds(tbx5Bounds);
		textBox5.setFont(font);
		textBox5.setRequired(true);

		doc.getForm().getFields().add(textBox5);
		baseY += 30;

		page.getCanvas().drawString("Address Line 2 :", font2, brush1, new Point2D.Float(110, baseY));
		Rectangle2D.Float tbx6Bounds = new Rectangle2D.Float(baseX, baseY, 150, 15);
		PdfTextBoxField textBox6 = new PdfTextBoxField(page, "address2");
		textBox6.setBounds(tbx6Bounds);
		textBox6.setFont(font);
		textBox6.setRequired(true);

		doc.getForm().getFields().add(textBox6);
		baseY += 30;

		page.getCanvas().drawString("Taluka :", font2, brush1, new Point2D.Float(110, baseY));
		Rectangle2D.Float tbx7Bounds = new Rectangle2D.Float(baseX, baseY, 150, 15);
		PdfTextBoxField textBox7 = new PdfTextBoxField(page, "taluka");
		textBox7.setBounds(tbx7Bounds);
		textBox7.setFont(font);
		textBox7.setRequired(true);

		doc.getForm().getFields().add(textBox7);
		baseY += 30;

		page.getCanvas().drawString("State :", font2, brush1, new Point2D.Float(110, baseY));
		Rectangle2D.Float tbx8Bounds = new Rectangle2D.Float(baseX, baseY, 150, 15);
		PdfTextBoxField textBox8 = new PdfTextBoxField(page, "state");
		textBox8.setBounds(tbx8Bounds);
		textBox8.setFont(font);
		textBox8.setRequired(true);

		doc.getForm().getFields().add(textBox8);
		baseY += 30;

		page.getCanvas().drawString("District :", font2, brush1, new Point2D.Float(110, baseY));
		Rectangle2D.Float tbx9Bounds = new Rectangle2D.Float(baseX, baseY, 150, 15);
		PdfTextBoxField textBox9 = new PdfTextBoxField(page, "district");
		textBox9.setBounds(tbx9Bounds);
		textBox9.setFont(font);
		textBox9.setRequired(true);

		doc.getForm().getFields().add(textBox9);
		baseY += 30;

		// Add two radio buttons
		page.getCanvas().drawString("Gender:", font2, brush1, new Point2D.Float(110, baseY));
		PdfRadioButtonListField radioButtonListField = new PdfRadioButtonListField(page, "gender");
		PdfRadioButtonListItem radioItem1 = new PdfRadioButtonListItem("Male");
		Rectangle2D.Float radioBound1 = new Rectangle2D.Float(baseX, baseY, 15, 15);
		radioItem1.setBounds(radioBound1);
		page.getCanvas().drawString("Male", font, brush1, new Point2D.Float(baseX + 20, baseY));

		PdfRadioButtonListItem radioItem2 = new PdfRadioButtonListItem("Female");
		Rectangle2D.Float radioBound2 = new Rectangle2D.Float(baseX + 70, baseY, 15, 15);
		radioItem2.setBounds(radioBound2);
		page.getCanvas().drawString("Female", font, brush1, new Point2D.Float(baseX + 90, baseY));
		radioButtonListField.getItems().add(radioItem1);
		radioButtonListField.getItems().add(radioItem2);
		radioButtonListField.setSelectedIndex(0);
		doc.getForm().getFields().add(radioButtonListField);
		baseY += 30;

		// Add a combobox
		page.getCanvas().drawString("City:", font2, brush1, new Point2D.Float(110, baseY));
		Rectangle2D.Float cmbBounds = new Rectangle2D.Float(baseX, baseY, 150, 15);
		PdfComboBoxField comboBoxField = new PdfComboBoxField(page, "city");
		comboBoxField.setBounds(cmbBounds);
		comboBoxField.getItems().add(new PdfListFieldItem("Ahmedabad", "Ahmedabad"));
		comboBoxField.getItems().add(new PdfListFieldItem("Gandhinagar", "Gandhinagar"));
		comboBoxField.getItems().add(new PdfListFieldItem("Rajkot", "Rajkot"));
		comboBoxField.getItems().add(new PdfListFieldItem("Surat", "Surat"));
		comboBoxField.setSelectedIndex(0);
		comboBoxField.setFont(font);
		doc.getForm().getFields().add(comboBoxField);
		baseY += 30;

		// Add a combobox
		page.getCanvas().drawString("Department:", font2, brush1, new Point2D.Float(110, baseY));
		Rectangle2D.Float cmbBounds2 = new Rectangle2D.Float(baseX, baseY, 150, 15);
		PdfComboBoxField comboBoxField2 = new PdfComboBoxField(page, "department");
		comboBoxField2.setBounds(cmbBounds2);
		comboBoxField2.getItems().add(new PdfListFieldItem("Java", "Java"));
		comboBoxField2.getItems().add(new PdfListFieldItem("Full stack", "Full stack"));
		comboBoxField2.getItems().add(new PdfListFieldItem("Sharepoint", "Sharepoint"));
		comboBoxField2.getItems().add(new PdfListFieldItem("DotNet", "DotNet"));
		comboBoxField2.setSelectedIndex(0);
		comboBoxField2.setFont(font);
		doc.getForm().getFields().add(comboBoxField2);
		baseY += 30;

		// Add a button
		Rectangle2D.Float btnBounds = new Rectangle2D.Float(200, baseY, 50, 15);

		PdfSubmitAction submitAction = new PdfSubmitAction("http://localhost:8080/storedata");
		PdfButtonField buttonField = new PdfButtonField(page, "button");
		buttonField.setBounds(btnBounds);
		buttonField.setText("Submit");
		buttonField.setFont(font);
		buttonField.getActions().setMouseUp(submitAction);
		doc.getForm().getFields().add(buttonField);

		double randomValue = Math.random();
		int randomNum = (int) (randomValue * Math.pow(10, 5));
		String filename = "fileForm_" + randomNum + ".pdf";
		// Save to file
		doc.saveToFile(filename, FileFormat.PDF);
		doc.close();
		return "created";
	}

}
