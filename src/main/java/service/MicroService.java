package service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

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

import bean.Assignee;
import bean.Chalan;
import bean.PopUpChallan;
import dao.DLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import ui.MultiScreenFramework;
import utility.UTable;

public class MicroService {
	final static Logger logger = Logger.getLogger(MultiScreenFramework.class);
	public int assigneeIDRetrieve(String name) {
		ObservableList<Assignee> assigneelist = DLoader.assigneelist;
		int assigneeid = 0;
		for (Assignee al : assigneelist) {
			System.out.println(al.getFullname());
			if (al.getFullname().equals(name)) {
				assigneeid = al.getAssigneeid();
				break;
			}
		}
		return assigneeid;
	}
	
	public ObservableList<String> assigneeIDRetrieveFullName(String name) throws SQLException, IOException {
		ObservableList<Assignee> assigneelist = DLoader.getSingeletonInstanceOfLoader().getAssigneeList();
		ObservableList<String> assigneeandgstin = FXCollections.observableArrayList();
		int assigneeid = 0;
		String gstin="";
		for (Assignee al : assigneelist) {
			System.out.println(al.getFullname());
			if (al.getFullname().equals(name)) {
				assigneeid = al.getAssigneeid();
				gstin = al.getGstin();
				assigneeandgstin.add(String.valueOf(assigneeid));
				assigneeandgstin.add(gstin);
				break;
			}
		}
		return assigneeandgstin;
	}

	public int getTotalReceiveFromPopUp(ObservableList<PopUpChallan> challanlist) {
		int totalreceive = 0;
		for (PopUpChallan c : challanlist) {
			totalreceive += c.getCurrentreceive();
		}
		return totalreceive;
	}

//	public int getTotalPaidFromPopUp(ObservableList<PopUpChallan> challanlist) {
//		int totalpaid = 0;
//		for (PopUpChallan c : challanlist) {
//			totalpaid += c.getCurrentpaid();
//		}
//		return totalpaid;
//	}

	public int getTotalDueFromPopUp(ObservableList<PopUpChallan> chalanlist) {
		int totaldue = 0;
		for (PopUpChallan c : chalanlist) {
			totaldue += c.getPastreceivedue();
		}
		return totaldue;
	}

	public static int sumReceiveFromPopUp(int pastreceive, int currentreceive) {
		return pastreceive + currentreceive;
	}

	public static int sumPaidFromPopUp(int pastpaid, int currentpaid) {
		return pastpaid + currentpaid;
	}

	public static int sumDueFromPopUp(int issue, int totalreceive) {
		return issue - totalreceive;
	}

	public static ObservableList<PopUpChallan> updatePopUpTableView(ObservableList<PopUpChallan> popuptable,
			int newreceive, int challanid) {
		ObservableList<PopUpChallan> newchalan = FXCollections.observableArrayList();
		for (PopUpChallan c : popuptable) {
			if (c.getChallanid() == challanid) {
				// int issue = c.getIssue();
				// int olddue = c.getDue();
				// int due = issue - newreceive;
				// if (newreceive > issue) {
				// Notification.popupWindowInvalidReceiveValueGreaterThenIssue();
				// due = c.getDue();
				// newreceive = c.getReceive();
				// } else if (due < 0) {
				// Notification.popupWindowInvalidValueLessThenZero();
				// due = c.getDue();
				// newreceive = c.getReceive();
				// }
				// c.setDue(due);
				// c.setReceive(newreceive);
				// newchalan.add(c);
			} else {
				// newchalan.add(c);
			}
		}
		return newchalan;
	}

	public static ObservableList<PopUpChallan> paidUpdatePopUpTableView(ObservableList<PopUpChallan> popuptable,
			int newpaid, int challanid) {
		ObservableList<PopUpChallan> newchalan = FXCollections.observableArrayList();
		for (PopUpChallan c : popuptable) {
			if (c.getChallanid() == challanid) {
				// int oldpaid = c.getPaid();
				// if(newpaid>c.getReceive())
				// {
				// newpaid = oldpaid;
				// Notification.popupWindowInvalidPaidValueGreaterThenReceive();
				// }
				// c.setPaid(newpaid);
				// newchalan.add(c);
			} else {
				// newchalan.add(c);
				// System.out.println(
				// "else" + c.getChallanid() + ":" + c.getIssue() + ":" +
				// c.getReceive() + ":" + c.getDue());
			}
		}
		return newchalan;
	}

	public static void createPdfss(String date){
		TableView<Chalan> newchalantable = UTable.getMainpagetableview();
		long aggregatechallanid = UTable.getAggregatechallanid();
		String dest = "C:\\Program Files\\IManage\\";
		dest =dest+aggregatechallanid+".pdf";
		
		String challanidtext="Challan ID: ",assigneenametext="To: ",amountpaidtext="Amount Paid: ",billdate="Date: ";
		
		challanidtext = challanidtext + aggregatechallanid;
		assigneenametext = assigneenametext+UTable.getAssigneename();
		amountpaidtext = amountpaidtext + UTable.getAmountpaid();
		billdate = billdate + date;
		
		float left = 30;
	    float right = 30;
	    float top = 60;
	    float bottom = 0;
	    Document document = new Document(PageSize.A5, left, right, top, bottom);
	    try {
			PdfWriter.getInstance(document, new FileOutputStream(dest));
		} catch (FileNotFoundException | DocumentException e) {
			logger.error("pdf generation",e);
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
	    
	    Paragraph companyaddress = new Paragraph("4235, Koolwal Bhawan, Near ICICI Bank, Surajpole Bazar, Jaipur", new Font(FontFamily.HELVETICA, 9));
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
	    amountpaid.setAlignment(Element.ALIGN_CENTER);
	    
	    Paragraph spacelabel = new Paragraph("  ", new Font(FontFamily.HELVETICA, 11));
	    spacelabel.setAlignment(Element.ALIGN_CENTER);
	    
	    Paragraph signature = new Paragraph("Sign:  ................................", new Font(FontFamily.HELVETICA, 9));
	    signature.setAlignment(Element.ALIGN_RIGHT);

	    Paragraph parentcompany = new Paragraph("A Unit of Khandelwal Saree Fashion", new Font(FontFamily.HELVETICA, 6));
	    parentcompany.setAlignment(Element.ALIGN_LEFT);
	    
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
	    
//	    for(int aw = 0; aw < 16; aw++){
//	        table.addCell("hi");
//	    }
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
	    document.add(spacelabel);
	    document.add(signature);
		document.add(parentcompany);
	    document.close();
	    } catch (DocumentException e) {
			logger.error("create pdf", e);
		}
	}
}
