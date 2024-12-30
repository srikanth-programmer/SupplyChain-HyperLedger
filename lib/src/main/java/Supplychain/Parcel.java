
package Supplychain;

import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;
import com.owlike.genson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@DataType()
public final class Parcel {

    @Property
    private final String parcelId;

    @Property
    private final String sender;

    @Property
    private final String receiver;

    @Property
    private final String from;

    @Property
    private final String to;

    @Property
    private final String startDate;

    @Property
    private  String endDate;

    @Property
    private final String charges;

    @Property
    private final String modeOfDelivery;

    @Property
    private  String deliveredBy;

    @Property
    private final String parcelWeight;

    @Property
    private final String parcelWorth;

    @Property
    private String status;

    @Property
    private List<String> deliveryHubLocation; // New variable to track delivery locations

    // Constructor with @JsonProperty annotations
    public Parcel(
            @JsonProperty("parcelId") String parcelId,
            @JsonProperty("sender") String sender,
            @JsonProperty("receiver") String receiver,
            @JsonProperty("from") String from,
            @JsonProperty("to") String to,
           
            @JsonProperty("endDate") String endDate,
            @JsonProperty("charges") String charges,
            @JsonProperty("modeOfDelivery") String modeOfDelivery,
         
            @JsonProperty("parcelWeight") String parcelWeight,
            @JsonProperty("parcelWorth") String parcelWorth,
            @JsonProperty("status") String status) {
        this.parcelId = parcelId;
        this.sender = sender;
        this.receiver = receiver;
        this.from = from;
        this.to = to;
        this.startDate = LocalDate.now().toString(); 
        this.endDate = endDate;
        this.charges = charges;
        this.modeOfDelivery = modeOfDelivery;
        this.deliveredBy = "";
        this.parcelWeight = parcelWeight;
        this.parcelWorth = parcelWorth;
        this.status = status;
        this.deliveryHubLocation = new ArrayList<>();  
    }

    // Getter and setter methods
    public String getParcelId() {
        return parcelId;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getCharges() {
        return charges;
    }

    public String getModeOfDelivery() {
        return modeOfDelivery;
    }

    public String getDeliveredBy() {
        return deliveredBy;
    }

    public String getParcelWeight() {
        return parcelWeight;
    }

    public String getParcelWorth() {
        return parcelWorth;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getDeliveryHubLocation() {
        return deliveryHubLocation;
    }

    public void addDeliveryHubLocation(String location) {
        this.deliveryHubLocation.add(location); // Method to add a new delivery hub location
    }
    
    public void setEndDate(String endDate) {
    	this.endDate = endDate;
    }
    
    public void setDeliveredBy(String personName) {
    	this.deliveredBy = personName;
    }
    
    // toString method
    @Override
    public String toString() {
        return "Parcel{" +
                "parcelId='" + parcelId + '\'' +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", charges='" + charges + '\'' +
                ", modeOfDelivery='" + modeOfDelivery + '\'' +
                ", deliveredBy='" + deliveredBy + '\'' +
                ", parcelWeight='" + parcelWeight + '\'' +
                ", parcelWorth='" + parcelWorth + '\'' +
                ", status='" + status + '\'' +
                ", deliveryHubLocation=" + deliveryHubLocation +
                '}';
    }
}

