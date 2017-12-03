package service;

import bean.Chalan;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import utility.UTable;

//public class EditingCell {
//
//}
public class EditingCell extends TableCell<Chalan, Integer> {

	private TextField textField;

	public EditingCell() {
	}
int challanid=0;
	@Override
	public void startEdit() {
		super.startEdit();
		if (textField == null) {
			createTextField();
		}
		TableRow row = this.getTableRow();
		Chalan chalanfrompopuptable = (Chalan) row.getItem();
		challanid = chalanfrompopuptable.getChallanid();
		System.out.println("edit row data challand id & issue "+challanid+"::"+chalanfrompopuptable.getIssue());
		
		setGraphic(textField);
		setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
		textField.selectAll();
	}

	@Override
	public void cancelEdit() {
		super.cancelEdit();
		setText(String.valueOf(getItem()));
		setContentDisplay(ContentDisplay.TEXT_ONLY);
	}

	@Override
	public void updateItem(Integer item, boolean empty) {
		super.updateItem(item, empty);

		if (empty) {
			setText(null);
			setGraphic(null);
		} else {
			if (isEditing()) {
				if (textField != null) {
					textField.setText(getString());
				}
				setGraphic(textField);
				setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
			} else {
				setText(getString());
				setContentDisplay(ContentDisplay.TEXT_ONLY);
			}
		}
	}

	private void createTextField() {
		textField = new TextField(getString());
		textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
		textField.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent t) {
				if (t.getCode() == KeyCode.ENTER) {
					int newreceive = Integer.parseInt(textField.getText());
					commitEdit(Integer.parseInt(textField.getText()));
					TableView<Chalan> tableview = UTable.getPopuptableview();
					ObservableList<Chalan> receivetablelist = tableview.getItems();

					ObservableList<Chalan> updatereceivetablelist = MicroService.updatePopUpTableView(receivetablelist,newreceive,challanid);
					
					receivetablelist.removeAll(receivetablelist);
					updatereceivetablelist.forEach(uc -> {receivetablelist.add(uc);});
				} else if (t.getCode() == KeyCode.ESCAPE) {
					cancelEdit();
				}
			}
		});
	}

	private String getString() {
		return getItem() == null ? "" : getItem().toString();
	}
}