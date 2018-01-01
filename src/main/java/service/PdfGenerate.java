package service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;

import bean.Chalan;
import bean.ChallanDetailBean;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import ui.MultiScreenFramework;
import utility.LoginVariable;
import utility.UTable;

public class PdfGenerate {
	
	public static File pdfFileLocationForSave(String billdateforpdf){
		FileChooser fileChooser = new FileChooser();
		  
        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF Files (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(extFilter);
        
        //Show save file dialog
        File file = fileChooser.showSaveDialog(UTable.getPrimarystage());
        logger.info("Path of the file saved: "+file.toString());
        if(file != null){
        	//createPdfss(billdateforpdf, file);
        	return file;
        }
        return null;
    }
//	String date,String referchallanid,String assigneenamelabel,
//	TableView<ChallanDetailBean> challandetailtableview,File file
	public static void createPdfss(String date,String assigneenamelabel,String description,TableView<Chalan> newchalantable,File file) {
	//	TableView<Chalan> newchalantable = UTable.getMainpagetableview();
		long aggregatechallanid = UTable.getAggregatechallanid();
		String dest = LoginVariable.getFilestoreaddress();
		dest = dest + aggregatechallanid + ".pdf";

		String challanidtext = "Challan ID: ", assigneenametext = "To: ", amountpaidtext = "Amount Paid: ",
				billdate = "Date: ";

		challanidtext = challanidtext + aggregatechallanid;
		assigneenametext = assigneenametext + assigneenamelabel;
		amountpaidtext = amountpaidtext + UTable.getAmountpaid();
		billdate = billdate + date;
		String descriptionlabel="Description: "; 
		descriptionlabel=descriptionlabel+description;
		
		float left = 15;
		float right = 15;
		float top = 30;
		float bottom = 0;
		Document document = new Document(PageSize.A5, left, right, top, bottom);
		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
			writer.setPageEvent(new WatermarkPageEvent());
		} catch (FileNotFoundException | DocumentException e) {
			logger.error("pdf generation", e);
		}
		
		Chunk glue = new Chunk(new VerticalPositionMark());
		document.open();
		document.setMargins(left, right, 0, bottom);
		document.addSubject("New Challan Export");
		document.addTitle("Challan");
		document.addCreationDate();
		document.addAuthor("IManage");

		Paragraph gstin = new Paragraph("GSTIN:08BXEPK4093F1ZE", new Font(FontFamily.HELVETICA, 6));
		gstin.setAlignment(Element.ALIGN_LEFT);
		gstin.add(new Chunk(glue));
		gstin.add("Phone no. 9529833222");

		Paragraph companyname = new Paragraph("Woomaniyaa", new Font(FontFamily.HELVETICA, 22));
		companyname.setAlignment(Element.ALIGN_CENTER);

		Paragraph companyaddress = new Paragraph("4235, Koolwal Bhawan, Near ICICI Bank, Surajpole Bazar, Jaipur",
				new Font(FontFamily.HELVETICA, 9));
		companyaddress.setAlignment(Element.ALIGN_CENTER);

		Paragraph challanidlabel = new Paragraph(challanidtext, new Font(FontFamily.HELVETICA, 11));
		challanidlabel.setAlignment(Element.ALIGN_LEFT);
		challanidlabel.add(new Chunk(glue));
		challanidlabel.add(billdate);

		Paragraph assigneename = new Paragraph(assigneenametext, new Font(FontFamily.HELVETICA, 11));
		assigneename.setAlignment(Element.ALIGN_LEFT);
		DottedLineSeparator dottedline = new DottedLineSeparator();
		dottedline.setOffset(-2);
		dottedline.setGap(2f);
		assigneename.add(dottedline);

		Paragraph amountpaid = new Paragraph(amountpaidtext, new Font(FontFamily.HELVETICA, 11));
		amountpaid.setAlignment(Element.ALIGN_LEFT);
		
		Paragraph descriptionparagraph = new Paragraph(descriptionlabel, new Font(FontFamily.HELVETICA, 9));
		descriptionparagraph.setAlignment(Element.ALIGN_LEFT);

		Paragraph spacelabel = new Paragraph("  ", new Font(FontFamily.HELVETICA, 11));
		spacelabel.setAlignment(Element.ALIGN_CENTER);

		Paragraph signature = new Paragraph("Sign:  ................................",
				new Font(FontFamily.HELVETICA, 9));
		signature.setAlignment(Element.ALIGN_RIGHT);

		Paragraph parentcompany = new Paragraph("A Unit of Khandelwal Saree Fashion",
				new Font(FontFamily.HELVETICA, 6));
		parentcompany.setAlignment(Element.ALIGN_LEFT);

		Paragraph applicationname = new Paragraph("Generated By: IManage Software",
				new Font(FontFamily.COURIER, 6));
		parentcompany.setAlignment(Element.ALIGN_RIGHT);

		
		try {

			document.add(gstin);
			document.add(companyname);
			document.add(companyaddress);
			document.add(spacelabel);
			document.add(challanidlabel);
			document.add(assigneename);
			document.add(spacelabel);
		} catch (DocumentException e) {
			logger.error("create pdf", e);
		}
		PdfPTable table = new PdfPTable(3);


		table.addCell("Product ID");
		table.addCell("Quantity Issued");
		table.addCell("Quantity Received");
		table.setHeaderRows(1);

		for (int i = 0; i < newchalantable.getItems().size(); i++) {
			for (int j = 0; j < newchalantable.getColumns().size(); j++) {
				if (newchalantable.getColumns().get(j).getCellData(i) != null) {
					table.addCell(newchalantable.getColumns().get(j).getCellData(i).toString());
				} else {
					table.addCell("");
				}
			}
		}

		try {
			document.add(table);
			document.add(amountpaid);
			document.add(descriptionparagraph);
			document.add(spacelabel);
			document.add(signature);
			document.add(parentcompany);
			document.add(applicationname);
			document.close();
		} catch (DocumentException e) {
			logger.error("create pdf", e);
		}
	}

	public static void createChallanDetailPdfss(String date,String referchallanid,String assigneenamelabel,
			TableView<ChallanDetailBean> challandetailtableview,File file) {
	
		String challanidtext = "Challan ID: ", assigneenametext = "To: ", amountpaidtext = "Amount Paid: ",
				billdate = "Date: ";

		challanidtext = challanidtext + referchallanid;
		assigneenametext = assigneenametext + assigneenamelabel;
		amountpaidtext = "";
		billdate = billdate + date;

		float left = 15;
		float right = 15;
		float top = 30;
		float bottom = 0;
		Document document = new Document(PageSize.A5, left, right, top, bottom);
		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
			writer.setPageEvent(new WatermarkPageEvent());
		} catch (FileNotFoundException | DocumentException e) {
			logger.error("pdf generation", e);
		}
		
		Chunk glue = new Chunk(new VerticalPositionMark());
		document.open();
		document.setMargins(left, right, 0, bottom);
		document.addSubject("New Challan Export");
		document.addTitle("Challan");
		document.addCreationDate();
		document.addAuthor("IManage");

		Paragraph gstin = new Paragraph("GSTIN:08BXEPK4093F1ZE", new Font(FontFamily.HELVETICA, 6));
		gstin.setAlignment(Element.ALIGN_LEFT);
		gstin.add(new Chunk(glue));
		gstin.add("Phone no. 9529833222");

		Paragraph companyname = new Paragraph("Woomaniyaa", new Font(FontFamily.HELVETICA, 22));
		companyname.setAlignment(Element.ALIGN_CENTER);

		Paragraph companyaddress = new Paragraph("4235, Koolwal Bhawan, Near ICICI Bank, Surajpole Bazar, Jaipur",
				new Font(FontFamily.HELVETICA, 9));
		companyaddress.setAlignment(Element.ALIGN_CENTER);

		Paragraph challanidlabel = new Paragraph(challanidtext, new Font(FontFamily.HELVETICA, 11));
		challanidlabel.setAlignment(Element.ALIGN_LEFT);
		challanidlabel.add(new Chunk(glue));
		challanidlabel.add(billdate);

		Paragraph assigneename = new Paragraph(assigneenametext, new Font(FontFamily.HELVETICA, 11));
		assigneename.setAlignment(Element.ALIGN_LEFT);
		DottedLineSeparator dottedline = new DottedLineSeparator();
		dottedline.setOffset(-2);
		dottedline.setGap(2f);
		assigneename.add(dottedline);

		Paragraph amountpaid = new Paragraph(amountpaidtext, new Font(FontFamily.HELVETICA, 11));
		amountpaid.setAlignment(Element.ALIGN_LEFT);

		Paragraph spacelabel = new Paragraph("  ", new Font(FontFamily.HELVETICA, 11));
		spacelabel.setAlignment(Element.ALIGN_CENTER);

		Paragraph signature = new Paragraph("Sign:  ................................",
				new Font(FontFamily.HELVETICA, 9));
		signature.setAlignment(Element.ALIGN_RIGHT);

		Paragraph parentcompany = new Paragraph("A Unit of Khandelwal Saree Fashion",
				new Font(FontFamily.HELVETICA, 6));
		parentcompany.setAlignment(Element.ALIGN_LEFT);

		Paragraph applicationname = new Paragraph("Generated By: IManage Software",
				new Font(FontFamily.COURIER, 6));
		parentcompany.setAlignment(Element.ALIGN_RIGHT);

		
		try {

			document.add(gstin);
			document.add(companyname);
			document.add(companyaddress);
			document.add(spacelabel);
			document.add(challanidlabel);
			document.add(assigneename);
			document.add(spacelabel);
		} catch (DocumentException e) {
			logger.error("create pdf", e);
		}
		PdfPTable table = new PdfPTable(5);


		table.addCell("Received Challan ID");
		table.addCell("Product ID");
		table.addCell("Quantity Issued");
		table.addCell("Quantity Received");
		table.addCell("Bill Date");
		table.setHeaderRows(1);

		for (int i = 0; i < challandetailtableview.getItems().size(); i++) {
			for (int j = 0; j < challandetailtableview.getColumns().size(); j++) {
				if (challandetailtableview.getColumns().get(j).getCellData(i) != null) {
					table.addCell(challandetailtableview.getColumns().get(j).getCellData(i).toString());
				} else {
					table.addCell("");
				}
			}
		}

		try {
			document.add(table);
			document.add(amountpaid);
			document.add(spacelabel);
			document.add(signature);
			document.add(parentcompany);
			document.add(applicationname);
			document.close();
		} catch (DocumentException e) {
			logger.error("create pdf", e);
		}
	}

	
	final static Logger logger = Logger.getLogger(MultiScreenFramework.class);
	
}
