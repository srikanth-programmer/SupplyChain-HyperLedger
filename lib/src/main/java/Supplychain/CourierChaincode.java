package Supplychain;

import java.util.ArrayList;
import java.util.List;

import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.Contract;
import org.hyperledger.fabric.contract.annotation.Default;
import org.hyperledger.fabric.contract.annotation.Info;
import org.hyperledger.fabric.contract.annotation.Transaction;
import org.hyperledger.fabric.shim.ChaincodeException;
import org.hyperledger.fabric.shim.ChaincodeStub;
import org.hyperledger.fabric.shim.ledger.QueryResultsIterator;
import org.hyperledger.fabric.shim.ledger.KeyValue;
import com.owlike.genson.Genson;
 

@Contract(name = "Supplychain", info = @Info(title = "Supplychain contract", description = "A  Courier Service", version = "0.0.1-SNAPSHOT"))



@Default
public class CourierChaincode implements ContractInterface  {
	private final Genson genson = new Genson();

	private enum Errors {
		PARCEL_NOT_FOUND
	}

	@Transaction()
	public void initLedger(final Context ctx) {

	}

	@Transaction()
	public void addParcel(final Context ctx, String parcelId, String sender, String receiver, String from, String to,

			String endDate, String charges, String modeOfDelivery, String deliveredBy, String parcelWeight,
			String parcelWorth, String status) {
		ChaincodeStub stub = ctx.getStub();

		Parcel parcel = new Parcel(parcelId, sender, receiver, from, to, endDate, charges, modeOfDelivery,
				parcelWeight, parcelWorth, status);
		stub.putStringState(parcelId, genson.serialize(parcel));
	}

	@Transaction()
	public void updateParcelStatus(final Context ctx, String parcelId, String newStatus) {
		ChaincodeStub stub = ctx.getStub();

		String parcelData = stub.getStringState(parcelId);
		if (parcelData == null || parcelData.isEmpty()) {
			String errorMessage = "Parcel is not found";
			throw new ChaincodeException(errorMessage, Errors.PARCEL_NOT_FOUND.toString());
		 
		}

		Parcel parcel = genson.deserialize(parcelData, Parcel.class);
		parcel.setStatus(newStatus);

		stub.putStringState(parcelId, genson.serialize(parcel));
	}

	 
	@Transaction()
	public Parcel queryParcel(final Context ctx, String parcelId) {
		ChaincodeStub stub = ctx.getStub();
        String parcelData = stub.getStringState(parcelId);
        if (parcelData == null || parcelData.isEmpty()) {
            throw new ChaincodeException("Parcel not found: " + parcelId, Errors.PARCEL_NOT_FOUND.toString());
        }
//        String Parcel = genson.serialize(newCar);
        Parcel Parcel = genson.deserialize(parcelData, Parcel.class);
        return Parcel;
	}

	 
	@Transaction()
	public void updateDeliveryDetails(final Context ctx, String parcelId, String deliveredBy, String deliveryDate) {
		ChaincodeStub stub = ctx.getStub();

		String parcelData = stub.getStringState(parcelId);
		if (parcelData == null || parcelData.isEmpty()) {
			String errorMessage = "Parcel is not found";
			throw new ChaincodeException(errorMessage, Errors.PARCEL_NOT_FOUND.toString());
		 
		}

		Parcel parcel = genson.deserialize(parcelData, Parcel.class);
		parcel.setDeliveredBy(deliveredBy);
		parcel.setEndDate(deliveryDate); // Assuming endDate is the delivery date

		stub.putStringState(parcelId, genson.serialize(parcel));
	}

	 
	@Transaction()
    public String listParcels(final Context ctx) {
        ChaincodeStub stub = ctx.getStub();
        List<Parcel> parcels = new ArrayList<>();

        // Use a range query to get all parcels
        QueryResultsIterator<KeyValue> results = stub.getStateByRange("", "");

        for (KeyValue kv : results) {
            String parcelData = kv.getStringValue();
            if (parcelData != null && !parcelData.isEmpty()) {
              Parcel serializedParcel =  genson.deserialize(parcelData, Parcel.class);
              parcels.add(serializedParcel);
            }
        }
        String result = genson.serialize(parcels);
        return result;
    }

	 @Transaction()
	    public void deliverParcel(final Context ctx, String parcelId, String deliveryHub) {
	        ChaincodeStub stub = ctx.getStub();

	        // Retrieve the parcel
	        String parcelData = stub.getStringState(parcelId);
	        if (parcelData == null || parcelData.isEmpty()) {
	        	String errorMessage = "Parcel is not found";
				throw new ChaincodeException(errorMessage, Errors.PARCEL_NOT_FOUND.toString());
	        }

	        Parcel parcel = genson.deserialize(parcelData, Parcel.class);
	        
	        // Update the parcel status to "In Transit" or "Delivered"
	        parcel.setStatus("In Transit"); // Update to reflect current status
	        parcel.addDeliveryHubLocation(deliveryHub); // Add the new delivery hub location

	        // Update the parcel state
	        stub.putStringState(parcelId, genson.serialize(parcel));
	    }




}
