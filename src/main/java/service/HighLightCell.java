package service;

import bean.PopUpChallan;
import bean.SortAndFilterBean;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableRow;
import javafx.scene.control.TextField;
import utility.UTable;

public class HighLightCell extends TableCell<SortAndFilterBean, Integer> {
	private TextField textField;
	int challanid = 0;

	public HighLightCell() {
	}

	int receivedue = 0;

	@Override
	public void startEdit() {
		super.startEdit();
		if (textField == null) {
			createTextField();
		}
		TableRow row = this.getTableRow();
		PopUpChallan chalanfrompopuptable = (PopUpChallan) row.getItem();
		challanid = chalanfrompopuptable.getChallanid();
		receivedue = chalanfrompopuptable.getPastreceivedue();
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
		if (item != null && (item == UTable.getSortandfiltertotalissue()||item==UTable.getSortandfiltertotalreceive()
				||item==UTable.getSortandfiltertotalreceivedue())) {
			this.setStyle("-fx-background-color:#000000;-fx-text-fill:#ffffff");
		}
		
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

		// textField.setOnKeyPressed(new EventHandler<KeyEvent>() {
		// @Override
		// public void handle(KeyEvent t) {
		// if (t.getCode() == KeyCode.ENTER) {
		// int newreceive = Integer.parseInt(textField.getText());
		// if(newreceive<0){
		// Notification.popupWindowInvalidValueLessThenZero();
		// return;
		// }
		// if(newreceive>receivedue){
		// Notification.popupWindowInvalidReceiveValueGreaterThenIssue();
		// return;
		// }
		// commitEdit(Integer.parseInt(textField.getText()));
		// } else if (t.getCode() == KeyCode.ESCAPE) {
		// cancelEdit();
		// }
		// }
		// });
	}

	private String getString() {
		return getItem() == null ? "" : getItem().toString();
	}
}