package com.pdf.service.impl;

import java.awt.Color;
import java.awt.Font;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSString;
import org.apache.pdfbox.pdmodel.fdf.FDFDocument;
import org.apache.pdfbox.pdmodel.fdf.FDFField;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdf.entity.Form;
import com.pdf.repository.FormRepository;
import com.pdf.service.FormService;
import com.spire.pdf.PdfBorders;
import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfPaddings;
import com.spire.pdf.PdfPageBase;
import com.spire.pdf.PdfPageSize;
import com.spire.pdf.PdfPageTemplateElement;
import com.spire.pdf.automaticfields.PdfAutomaticField;
import com.spire.pdf.automaticfields.PdfCompositeField;
import com.spire.pdf.automaticfields.PdfPageCountField;
import com.spire.pdf.automaticfields.PdfPageNumberField;
import com.spire.pdf.graphics.PdfBrushes;
import com.spire.pdf.graphics.PdfImage;
import com.spire.pdf.graphics.PdfLayoutType;
import com.spire.pdf.graphics.PdfMargins;
import com.spire.pdf.graphics.PdfPen;
import com.spire.pdf.graphics.PdfRGBColor;
import com.spire.pdf.graphics.PdfSolidBrush;
import com.spire.pdf.graphics.PdfStringFormat;
import com.spire.pdf.graphics.PdfTextAlignment;
import com.spire.pdf.graphics.PdfTextLayout;
import com.spire.pdf.graphics.PdfTrueTypeFont;
import com.spire.pdf.graphics.PdfVerticalAlignment;
import com.spire.pdf.grid.PdfGrid;
import com.spire.pdf.grid.PdfGridRow;
import com.spire.pdf.tables.BeginRowLayoutEventArgs;

/**
 * 
 * @author Hetasvi.Ahir
 *
 */
@Service
public class FormServiceImpl implements FormService {
	@Autowired
	private FormRepository formRepo;

	/**
	 * Store Data to Database
	 */
	@Override
	public String storeData(byte[] pdfFile) throws IOException {
		FDFDocument fdfDoc = FDFDocument.load(new ByteArrayInputStream(pdfFile));
		// Get all the fields
		List<FDFField> fields = fdfDoc.getCatalog().getFDF().getFields();

		// Create a JSON object to store the field values
		JSONObject jsonObject = new JSONObject();

		// Iterate over the fields and extract the values
		for (FDFField field : fields) {
			String firstname = field.getPartialFieldName();
			Object value = field.getValue();

			if (value instanceof COSString) {
				COSString cosString = (COSString) value;
				jsonObject.put(firstname, cosString.getString());
			} else if (value instanceof COSBase) {
				COSBase cosBase = (COSBase) value;
				jsonObject.put(firstname, cosBase.toString());
			} else {
				jsonObject.put(firstname, value.toString());
			}
		}

		// Store the field values in the database

		String firstname = jsonObject.getString("firstname");
		String lastname = jsonObject.getString("lastname");
		String number = jsonObject.getString("number");
		String email = jsonObject.getString("email");
		String gender = jsonObject.getString("gender");
		String city = jsonObject.getString("city");
		String state = jsonObject.getString("state");
		String taluka = jsonObject.getString("taluka");
		String address1 = jsonObject.getString("address1");
		String address2 = jsonObject.getString("address2");
		String district = jsonObject.getString("district");
		String department = jsonObject.getString("department");

		Form form = new Form();
		form.setFirstname(firstname);
		form.setLastname(lastname);
		form.setNumber(number);
		form.setEmail(email);
		form.setCity(city);
		form.setDepartment(department);
		form.setGender(gender);
		form.setAddress1(address1);
		form.setAddress2(address2);
		form.setDistrict(district);
		form.setState(state);
		form.setTaluka(taluka);

		formRepo.save(form);

		createPdf(firstname, lastname, number, email, gender, address1, address2, city, taluka, district, state,
				department);
		return "Hello";
	}

	/**
	 * 
	 * @param firstname
	 * @param lastname
	 * @param number
	 * @param email
	 * @param gender
	 * @param city
	 * @param department
	 * @return {@summary Create PDF using the user input}
	 */
	public String createPdf(String firstname, String lastname, String number, String email, String gender,
			String address1, String address2, String city, String taluka, String district, String state,
			String department) {

		// Create a PdfDocument object
		PdfDocument doc = new PdfDocument();

		// Add a page
		PdfPageBase page = doc.getPages().add(PdfPageSize.A4);

		// Set margins
		PdfMargins margin = new PdfMargins(40, 40, 20, 60);

		// Call the method addHeaderAndFooter() to add header and footer
		addHeaderAndFooter(doc, PdfPageSize.A4, margin);

		// Specify title text
		String titleText = "Welcome to Rysun  ";

		// Load an image
		PdfImage image = PdfImage.fromFile("D:\\Goals\\pdf-demo\\src\\main\\resources\\image\\main-logo-1.png");

		// Specify the width and height of the image area on the page
		float width = image.getWidth() * 0.50f;
		float height = image.getHeight() * 0.50f;

		// Specify the X and Y coordinates to start drawing the image
		float x = 40f;
		float y = 2f;

		// Draw the image at a specified location on the page
		page.getCanvas().drawImage(image, x, y, width, height);

		// Create solid brushes
		PdfSolidBrush titleBrush = new PdfSolidBrush(new PdfRGBColor(Color.orange));
		PdfSolidBrush paraBrush = new PdfSolidBrush(new PdfRGBColor(Color.BLACK));

		// Create true type fonts
		PdfTrueTypeFont titleFont = new PdfTrueTypeFont(new Font("Times New Roman", Font.BOLD, 20));
		PdfTrueTypeFont paraFont2 = new PdfTrueTypeFont(new Font("Times New Roman", Font.PLAIN, 16));
		PdfTrueTypeFont paraFont = new PdfTrueTypeFont(new Font("Times New Roman", Font.PLAIN, 12));

		// Set the text alignment via PdfStringFormat class
		PdfStringFormat format = new PdfStringFormat();
		format.setAlignment(PdfTextAlignment.Center);

		PdfStringFormat format2 = new PdfStringFormat();
		format2.setAlignment(PdfTextAlignment.Left);

		// Draw title on the page
		page.getCanvas().drawString(titleText + firstname, titleFont, titleBrush, new Point2D.Float(250, 2), format2);

		// Create a PdfGrid
		PdfGrid grid = new PdfGrid();

		// Set cell padding
		grid.getStyle().setCellPadding(new PdfPaddings(1, 1, 1, 1));

		// Set font
		grid.getStyle().setFont(new PdfTrueTypeFont(new Font("Times New Roman", Font.PLAIN, 13), true));

		// Add rows and columns
		PdfGridRow row1 = grid.getRows().add();
		PdfGridRow row2 = grid.getRows().add();
		PdfGridRow row3 = grid.getRows().add();
		PdfGridRow row4 = grid.getRows().add();

		PdfGridRow row5 = grid.getRows().add();
		PdfGridRow row6 = grid.getRows().add();
		PdfGridRow row7 = grid.getRows().add();

		PdfGridRow row8 = grid.getRows().add();
		PdfGridRow row9 = grid.getRows().add();
		PdfGridRow row10 = grid.getRows().add();

		PdfGridRow row11 = grid.getRows().add();
		PdfGridRow row12 = grid.getRows().add();
		PdfGridRow row13 = grid.getRows().add();

		grid.getColumns().add(2);

		// Set column width
		for (int i = 0; i < grid.getColumns().getCount(); i++) {

			grid.getColumns().get(i).setWidth(120);
		}

		// Write data into specific cells
		row1.getCells().get(0).setValue("Registered Details");
		row2.getCells().get(0).setValue("First Name ");
		row2.getCells().get(1).setValue(firstname);

		row3.getCells().get(0).setValue("Last Name ");
		row3.getCells().get(1).setValue(lastname);

		row4.getCells().get(0).setValue("Mobile Number");
		row4.getCells().get(1).setValue(number);

		row5.getCells().get(0).setValue("Email");
		row5.getCells().get(1).setValue(email);

		row6.getCells().get(0).setValue("Gender");
		row6.getCells().get(1).setValue(gender);

		row7.getCells().get(0).setValue("City");
		row7.getCells().get(1).setValue(city);

		row8.getCells().get(0).setValue("Address Line 1 ");
		row8.getCells().get(1).setValue(address1);

		row9.getCells().get(0).setValue("Address Line 2");
		row9.getCells().get(1).setValue(address2);

		row10.getCells().get(0).setValue("Taluka");
		row10.getCells().get(1).setValue(taluka);

		row11.getCells().get(0).setValue("State");
		row11.getCells().get(1).setValue(state);

		row12.getCells().get(0).setValue("District");
		row12.getCells().get(1).setValue(district);

		row13.getCells().get(0).setValue("Department");
		row13.getCells().get(1).setValue(department);

		// Span cell across columns
		row1.getCells().get(0).setColumnSpan(2);

		// Set text alignment of specific cells
		row1.getCells().get(0).setStringFormat(new PdfStringFormat(PdfTextAlignment.Center));
		row3.getCells().get(1).setStringFormat(new PdfStringFormat(PdfTextAlignment.Left, PdfVerticalAlignment.Middle));

		// Set background color of specific cells
		row1.getCells().get(0).getStyle().setBackgroundBrush(PdfBrushes.getOrange());
		row4.getCells().get(1).setStringFormat(new PdfStringFormat(PdfTextAlignment.Right));

		// Format cell border
		PdfBorders borders = new PdfBorders();
		borders.setAll(new PdfPen(new PdfRGBColor(Color.ORANGE), 0.8f));

		grid.getRows().setCapacity(13);
		for (int i = 0; i < grid.getRows().getCapacity(); i++) {

			PdfGridRow gridRow = grid.getRows().get(i);
			gridRow.setHeight(20f);

			for (int j = 0; j < gridRow.getCells().getCount(); j++) {
				gridRow.getCells().get(j).getStyle().setBorders(borders);
			}

		}

		// Draw table on the page
		grid.draw(page, new Point2D.Float(150, 50));

		Integer canvasx = 150;
		Integer canvasy = 350;

		page.getCanvas().drawString("You have entered the following details :", paraFont2, paraBrush,
				new Point2D.Float(200, 320), format);

		page.getCanvas().drawString("Firstname : " + firstname, paraFont, paraBrush,
				new Point2D.Float(canvasx, canvasy), format2);
		page.getCanvas().drawString("Lastname : " + lastname, paraFont, paraBrush,
				new Point2D.Float(canvasx, canvasy += 20), format2);
		page.getCanvas().drawString("Mobile Number : " + number, paraFont, paraBrush,
				new Point2D.Float(canvasx, canvasy += 20), format2);
		page.getCanvas().drawString("Email : " + email, paraFont, paraBrush, new Point2D.Float(canvasx, canvasy += 20),
				format2);
		page.getCanvas().drawString("Gender : " + gender, paraFont, paraBrush,
				new Point2D.Float(canvasx, canvasy += 20), format2);

		page.getCanvas().drawString("City : " + city, paraFont, paraBrush, new Point2D.Float(canvasx, canvasy += 20),
				format2);
		page.getCanvas().drawString("Department : " + department, paraFont, paraBrush,
				new Point2D.Float(canvasx, canvasy += 20), format2);

		page.getCanvas().drawString("Firstname : " + firstname, paraFont, paraBrush,
				new Point2D.Float(canvasx, canvasy += 20), format2);
		page.getCanvas().drawString("Lastname : " + lastname, paraFont, paraBrush,
				new Point2D.Float(canvasx, canvasy += 20), format2);
		page.getCanvas().drawString("Mobile Number : " + number, paraFont, paraBrush,
				new Point2D.Float(canvasx, canvasy += 20), format2);
		page.getCanvas().drawString("Email : " + email, paraFont, paraBrush, new Point2D.Float(canvasx, canvasy += 20),
				format2);
		page.getCanvas().drawString("Gender : " + gender, paraFont, paraBrush,
				new Point2D.Float(canvasx, canvasy += 20), format2);

		page.getCanvas().drawString("City : " + city, paraFont, paraBrush, new Point2D.Float(canvasx, canvasy += 20),
				format2);
		page.getCanvas().drawString("Department : " + department, paraFont, paraBrush,
				new Point2D.Float(canvasx, canvasy += 20), format2);

		// Page 2
		PdfPageBase page2 = doc.getPages().add(PdfPageSize.A4);

		Integer canvasx2 = 150;
		Integer canvasy2 = 100;

		page2.getCanvas().drawString("You have entered the following details :", paraFont2, paraBrush,
				new Point2D.Float(200, 80), format);

		page2.getCanvas().drawString("Firstname : " + firstname, paraFont, paraBrush,
				new Point2D.Float(canvasx2, canvasy2 += 20), format2);
		page2.getCanvas().drawString("Lastname : " + lastname, paraFont, paraBrush,
				new Point2D.Float(canvasx2, canvasy2 += 20), format2);
		page2.getCanvas().drawString("Mobile Number : " + number, paraFont, paraBrush,
				new Point2D.Float(canvasx2, canvasy2 += 20), format2);
		page2.getCanvas().drawString("Email : " + email, paraFont, paraBrush,
				new Point2D.Float(canvasx2, canvasy2 += 20), format2);
		page2.getCanvas().drawString("Gender : " + gender, paraFont, paraBrush,
				new Point2D.Float(canvasx2, canvasy2 += 20), format2);

		page2.getCanvas().drawString("City : " + city, paraFont, paraBrush, new Point2D.Float(canvasx2, canvasy2 += 20),
				format2);
		page2.getCanvas().drawString("Department : " + department, paraFont, paraBrush,
				new Point2D.Float(canvasx2, canvasy2 += 20), format2);

		page2.getCanvas().drawString("Firstname : " + firstname, paraFont, paraBrush,
				new Point2D.Float(canvasx2, canvasy2 += 20), format2);
		page2.getCanvas().drawString("Lastname : " + lastname, paraFont, paraBrush,
				new Point2D.Float(canvasx2, canvasy2 += 20), format2);
		page2.getCanvas().drawString("Mobile Number : " + number, paraFont, paraBrush,
				new Point2D.Float(canvasx2, canvasy2 += 20), format2);
		page2.getCanvas().drawString("Email : " + email, paraFont, paraBrush,
				new Point2D.Float(canvasx2, canvasy2 += 20), format2);
		page2.getCanvas().drawString("Gender : " + gender, paraFont, paraBrush,
				new Point2D.Float(canvasx2, canvasy2 += 20), format2);

		page2.getCanvas().drawString("City : " + city, paraFont, paraBrush, new Point2D.Float(canvasx2, canvasy2 += 20),
				format2);
		page2.getCanvas().drawString("Department : " + department, paraFont, paraBrush,
				new Point2D.Float(canvasx2, canvasy2 += 20), format2);
		page2.getCanvas().drawString("Firstname : " + firstname, paraFont, paraBrush,
				new Point2D.Float(canvasx2, canvasy2 += 20), format2);
		page2.getCanvas().drawString("Lastname : " + lastname, paraFont, paraBrush,
				new Point2D.Float(canvasx2, canvasy2 += 20), format2);
		page2.getCanvas().drawString("Mobile Number : " + number, paraFont, paraBrush,
				new Point2D.Float(canvasx2, canvasy2 += 20), format2);
		page2.getCanvas().drawString("Email : " + email, paraFont, paraBrush,
				new Point2D.Float(canvasx, canvasy2 += 20), format2);
		page2.getCanvas().drawString("Gender : " + gender, paraFont, paraBrush,
				new Point2D.Float(canvasx2, canvasy2 += 20), format2);

		page2.getCanvas().drawString("City : " + city, paraFont, paraBrush, new Point2D.Float(canvasx2, canvasy2 += 20),
				format2);
		page2.getCanvas().drawString("Department : " + department, paraFont, paraBrush,
				new Point2D.Float(canvasx2, canvasy2 += 20), format2);
		page2.getCanvas().drawString("Firstname : " + firstname, paraFont, paraBrush,
				new Point2D.Float(canvasx2, canvasy2 += 20), format2);
		page2.getCanvas().drawString("Lastname : " + lastname, paraFont, paraBrush,
				new Point2D.Float(canvasx2, canvasy2 += 20), format2);
		page2.getCanvas().drawString("Mobile Number : " + number, paraFont, paraBrush,
				new Point2D.Float(canvasx2, canvasy2 += 20), format2);
		page2.getCanvas().drawString("Email : " + email, paraFont, paraBrush,
				new Point2D.Float(canvasx2, canvasy2 += 20), format2);
		page2.getCanvas().drawString("Gender : " + gender, paraFont, paraBrush,
				new Point2D.Float(canvasx2, canvasy2 += 20), format2);

		page2.getCanvas().drawString("City : " + city, paraFont, paraBrush, new Point2D.Float(canvasy2, canvasy += 20),
				format2);
		page2.getCanvas().drawString("Department : " + department, paraFont, paraBrush,
				new Point2D.Float(canvasx2, canvasy2 += 20), format2);

		// Set the PdfLayoutType to Paginate to make the content paginated automatically
		PdfTextLayout layout = new PdfTextLayout();
		layout.setLayout(PdfLayoutType.Paginate);

		String filename = "file_" + firstname + ".pdf";
		// Save to file
		doc.saveToFile("output/" + filename);
		doc.dispose();

		return "PDF Generated";
	}

	/**
	 * 
	 * @param doc
	 * @param pageSize
	 * @param margin   {@summary Adds Header And Footer}
	 */
	static void addHeaderAndFooter(PdfDocument doc, Dimension2D pageSize, PdfMargins margin) {

		PdfPageTemplateElement header = new PdfPageTemplateElement(margin.getLeft(), pageSize.getHeight());
		doc.getTemplate().setLeft(header);

		PdfPageTemplateElement topSpace = new PdfPageTemplateElement(pageSize.getWidth(), margin.getTop());
		topSpace.setForeground(true);
		doc.getTemplate().setTop(topSpace);

		// Draw header label
		PdfTrueTypeFont font = new PdfTrueTypeFont(new Font("Arial", Font.PLAIN, 12));

		PdfStringFormat format = new PdfStringFormat(PdfTextAlignment.Left);

		float y = topSpace.getHeight() - font.getHeight() - 1;
		PdfPen pen = new PdfPen(new PdfRGBColor(Color.black), 0.75f);
		topSpace.getGraphics().setTransparency(0.5f);
		topSpace.getGraphics().drawLine(pen, margin.getLeft(), y, pageSize.getWidth() - margin.getRight(), y);

		PdfPageTemplateElement rightSpace = new PdfPageTemplateElement(margin.getRight(), pageSize.getHeight());
		doc.getTemplate().setRight(rightSpace);

		// Draw dynamic fields as footer
		PdfPageTemplateElement footer = new PdfPageTemplateElement(pageSize.getWidth(), margin.getBottom());
		footer.setForeground(true);
		doc.getTemplate().setBottom(footer);

		y = font.getHeight() + 1;
		footer.getGraphics().setTransparency(0.5f);
		footer.getGraphics().drawLine(pen, margin.getLeft(), y, pageSize.getWidth() - margin.getRight(), y);
		y = y + 1;
		PdfPageNumberField pageNumber = new PdfPageNumberField();

		PdfPageCountField pageCount = new PdfPageCountField();

		PdfCompositeField pageNumberLabel = new PdfCompositeField();
		pageNumberLabel.setAutomaticFields(new PdfAutomaticField[] { pageNumber, pageCount });
		pageNumberLabel.setBrush(PdfBrushes.getBlack());
		pageNumberLabel.setFont(font);
		format = new PdfStringFormat(PdfTextAlignment.Right);
		pageNumberLabel.setStringFormat(format);
		pageNumberLabel.setText("page {0} of {1}" + " || This is an autogenerated receipt from Rysun");
		pageNumberLabel.setBounds(footer.getBounds());
		pageNumberLabel.draw(footer.getGraphics(), -margin.getLeft(), y);
	}

}
