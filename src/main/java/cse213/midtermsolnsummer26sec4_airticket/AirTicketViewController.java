package cse213.midtermsolnsummer26sec4_airticket;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.ArrayList;

public class AirTicketViewController
{
    @javafx.fxml.FXML
    private ComboBox<String> seatTypeComboBox;
    @javafx.fxml.FXML
    private TextField flightTimeTextField;
    @javafx.fxml.FXML
    private TableColumn<AirTicket, Float> priceTableCol;
    @javafx.fxml.FXML
    private CheckBox isRefundableCheckBox;
    @javafx.fxml.FXML
    private ComboBox<String> originCodeComboBox;
    @javafx.fxml.FXML
    private ComboBox<String> filteredOriginCodeComboBox;
    @javafx.fxml.FXML
    private DatePicker filteredTravelDateDatePicker;
    @javafx.fxml.FXML
    private DatePicker travelDateDatePicker;
    @javafx.fxml.FXML
    private TableColumn<AirTicket, String> flightNoTableCol;
    @javafx.fxml.FXML
    private TableColumn<AirTicket, Boolean> isRefundableTableCol;
    @javafx.fxml.FXML
    private TableColumn<AirTicket, String> pnrTableCol;
    @javafx.fxml.FXML
    private TextField priceTextField;
    @javafx.fxml.FXML
    private Label showSeatTypeCountsLabel;
    @javafx.fxml.FXML
    private TextField flightNoTextField;
    @javafx.fxml.FXML
    private CheckBox filteredIsRefundableCheckBox;
    @javafx.fxml.FXML
    private TableColumn<AirTicket, String> originCodeTableCol;
    @javafx.fxml.FXML
    private TextField pnrTextField;
    @javafx.fxml.FXML
    private ComboBox<String> destinationCodeComboBox;
    @javafx.fxml.FXML
    private TableColumn<AirTicket, LocalDate> travelDateTableCol;
    private ArrayList<AirTicket> ticketList;
    @javafx.fxml.FXML
    private TableView<AirTicket> airTicketTableView;

    @javafx.fxml.FXML
    public void initialize() {

        flightNoTableCol.setCellValueFactory(new PropertyValueFactory<>("flightNo"));
        pnrTableCol.setCellValueFactory(new PropertyValueFactory<>("PNR"));
        originCodeTableCol.setCellValueFactory(new PropertyValueFactory<>("originCode"));
        travelDateTableCol.setCellValueFactory(new PropertyValueFactory<>("travelDate"));
        priceTableCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        isRefundableTableCol.setCellValueFactory(new PropertyValueFactory<>("isRefundable"));

        originCodeComboBox.getItems().addAll("DAC", "DXB", "MAD", "LHR", "BKK", "HKG");
        destinationCodeComboBox.getItems().addAll("DAC", "DXB", "MAD", "LHR", "BKK", "HKG");
        seatTypeComboBox.getItems().addAll("Economy", "Business", "First");

        ticketList = new ArrayList<>();
    }

    @javafx.fxml.FXML
    public void showSeatTypeCountsButtonOnAction() {

        int economyCount = 0;
        int businessCount = 0;
        int firstCount = 0;
        for (AirTicket aT: airTicketTableView.getItems()){
            if (aT.getSeatType().equals("Economy")){
                economyCount += 1;
            } else if (aT.getSeatType().equals("Business")){
                businessCount += 1;
            } else if (aT.getSeatType().equals("First")){
                firstCount += 1;
            }
        }

        showSeatTypeCountsLabel.setText("Economy Class Seat: " + economyCount + "\nBusiness Class Seat: " + businessCount + "\nFirst Class Seat " + firstCount);

    }

    @javafx.fxml.FXML
    public void filterAndLoadTableButtonOnAction() {

        airTicketTableView.getItems().clear();

        for (AirTicket airTicket: ticketList){
            if (filteredOriginCodeComboBox.getValue().equals(airTicket.getOriginCode()) &&
                    filteredTravelDateDatePicker.getValue().equals(airTicket.getTravelDate())){
                if (filteredIsRefundableCheckBox.isSelected()) {
                    if (airTicket.isRefundable()){
                        airTicketTableView.getItems().add(airTicket);
                    }
                } else {
                    airTicketTableView.getItems().add(airTicket);
                }
            }
        }
    }

    @javafx.fxml.FXML
    public void validateAndAddNewObjectToArrayListButtonOnAction() {

        String flightNo = flightNoTextField.getText();
        String pnr = pnrTextField.getText();
        String originCode = originCodeComboBox.getValue();
        String destinationCode = destinationCodeComboBox.getValue();
        String seatType = seatTypeComboBox.getValue();
        String flightTime = flightTimeTextField.getText();
        LocalDate travelDate = travelDateDatePicker.getValue();
        float price = Float.parseFloat(priceTextField.getText());
        boolean isRefundable = isRefundableCheckBox.isSelected();

        if(originCode.equals(destinationCode)){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Origin Code & Destination Code Can't Be Same!");
            a.showAndWait();
            return;
        }

        if(seatType.equals("Business") && price < 20000){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Business seat price can't be less than 20000.");
            a.showAndWait();
            return;
        }

        if (travelDate.isBefore(LocalDate.now())) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Travel Date can't be a past date.");
            a.showAndWait();
            return;
        }

        //String flightNo, String PNR, String originCode, String destinationCode, String seatType,
        // String flightTime, LocalDate travelDate, float price, boolean isRefundable

        AirTicket ticket = new AirTicket(flightNo, pnr, originCode, destinationCode, seatType,
                flightTime, travelDate, price, isRefundable);

        ticketList.add(ticket);
        airTicketTableView.getItems().clear();
        airTicketTableView.getItems().addAll(ticketList);
    }
}