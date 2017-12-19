package service;

import bean.PopUpChallan;
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

public class EditingPaid extends TableCell<PopUpChallan, Integer> {

	private TextField textField;

	public EditingPaid() {
	}

	int challanid = 0;
	int paiddue;
	@Override
	public void startEdit() {
		super.startEdit();
		if (textField == null) {
			createTextField();
		}
		TableRow row = this.getTableRow();
		PopUpChallan chalanfrompopuptable = (PopUpChallan) row.getItem();
		challanid = chalanfrompopuptable.getChallanid();
		paiddue = chalanfrompopuptable.getPastpaiddue();
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
		this.setStyle("-fx-border-color:  #000000");
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
					int newpaid = Integer.parseInt(textField.getText());
					//**//
					if(newpaid<0){
						Notification.popupWindowInvalidValueLessThenZero("Value Cannot be Less then 0",
								"Value entered in paid column is less then zero. Please Enter a value greater"
								+ "then zero");
						return;
					}else if(newpaid>paiddue)
					{
						Notification.popupWindowInvalidGreaterValue("Value entered in paid column is invalid",
								"Paid value cannot be greater then Paid due value. Please enter the correct value");
						return;
					}
					commitEdit(Integer.parseInt(textField.getText()));
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