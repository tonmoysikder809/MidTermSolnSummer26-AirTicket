package cse213.midtermsolnsummer26sec4_airticket;

import java.time.LocalDate;

public class AirTicket {

    private String flightNo, PNR, originCode, destinationCode, seatType, flightTime;
    private LocalDate travelDate;
    private float price;
    private boolean isRefundable;

    public AirTicket(String flightNo, String PNR, String originCode, String destinationCode, String seatType, String flightTime, LocalDate travelDate, float price, boolean isRefundable) {
        this.flightNo = flightNo;
        this.PNR = PNR;
        this.originCode = originCode;
        this.destinationCode = destinationCode;
        this.seatType = seatType;
        this.flightTime = flightTime;
        this.travelDate = travelDate;
        this.price = price;
        this.isRefundable = isRefundable;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public String getPNR() {
        return PNR;
    }

    public void setPNR(String PNR) {
        this.PNR = PNR;
    }

    public String getOriginCode() {
        return originCode;
    }

    public void setOriginCode(String originCode) {
        this.originCode = originCode;
    }

    public String getDestinationCode() {
        return destinationCode;
    }

    public void setDestinationCode(String destinationCode) {
        this.destinationCode = destinationCode;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public String getFlightTime() {
        return flightTime;
    }

    public void setFlightTime(String flightTime) {
        this.flightTime = flightTime;
    }

    public LocalDate getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(LocalDate travelDate) {
        this.travelDate = travelDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isRefundable() {
        return isRefundable;
    }

    public void setRefundable(boolean refundable) {
        isRefundable = refundable;
    }

    @Override
    public String toString() {
        return "AirTicket{" +
                "flightNo='" + flightNo + '\'' +
                ", PNR='" + PNR + '\'' +
                ", originCode='" + originCode + '\'' +
                ", destinationCode='" + destinationCode + '\'' +
                ", seatType='" + seatType + '\'' +
                ", flightTime='" + flightTime + '\'' +
                ", travelDate=" + travelDate +
                ", price=" + price +
                ", isRefundable=" + isRefundable +
                '}';
    }

    public void printRefundAmount(){
        if(!isRefundable){
            System.out.println("Non Refundable");
        }
        if (isRefundable && seatType.equals("Economy")) {
            System.out.println("Refund Amount" + price*0.5);
        }
        if (isRefundable && seatType.equals("Business")) {
            System.out.println("Refund Amount" + price*0.6);
        }
        if (isRefundable && seatType.equals("First")) {
            System.out.println("Refund Amount" + price*0.7);
        }

    }
}

