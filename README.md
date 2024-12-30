# Supplychain Project

## Overview
The Supplychain project is a Java-based application designed to manage a courier service. It leverages Hyperledger Fabric for blockchain integration to ensure secure and transparent tracking of parcels.

## Project Structure
```
Supplychain/
├── .gitattributes
├── .gitignore
├── .gradle/
├── .project
├── .settings/
├── bin/
├── build/
├── build.gradle
├── gradle/
├── gradlew
├── gradlew.bat
├── lib/
│   ├── .classpath
│   ├── .project
│   ├── .settings/
│   ├── bin/
│   ├── build/
│   ├── build.gradle
│   ├── src/
├── settings.gradle
├── src/
│   ├── main/
│   ├── test/
```

## Getting Started

### Prerequisites
* Java Development Kit (JDK) 15 or higher
* Gradle 6.8 or higher
* Hyperledger Fabric

### Building the Project
To build the project, run:
```bash
./gradlew build
```

### Running Tests
To run the tests, use:
```bash
./gradlew test
```

## Project Modules

### Main Classes
* `Supplychain.CourierChain`: Implements the main contract for managing parcels
* `Supplychain.Parcel`: Represents a parcel with various properties such as sender, receiver, and delivery status

### Key Methods
* `CourierChain.addParcel`: Adds a new parcel to the ledger
* `CourierChain.queryParcel`: Queries a parcel by its ID
* `CourierChain.updateParcelStatus`: Updates the status of a parcel
* `CourierChain.deliverParcel`: Updates the delivery details of a parcel

### Example Usage
```java
// Adding a new parcel
CourierChain courierChain = new CourierChain();
courierChain.addParcel(ctx, "parcelId", "sender", "receiver", "from", "to", 
    "endDate", "charges", "modeOfDelivery", "deliveredBy", "parcelWeight", 
    "parcelWorth", "status");

// Querying a parcel
Parcel parcel = courierChain.queryParcel(ctx, "parcelId");

// Updating parcel status
courierChain.updateParcelStatus(ctx, "parcelId", "Delivered");

// Delivering a parcel
courierChain.deliverParcel(ctx, "parcelId", "deliveryHub");
```

## License
This project is licensed under the Apache License, Version 2.0. See the LICENSE file for details.

## Contributing
Contributions are welcome! Please read the CONTRIBUTING guidelines before submitting a pull request.

## Contact
For any inquiries, please contact the project maintainers.

---
This README provides a high-level overview of the Supplychain project. For more detailed documentation, please refer to the source code and comments within the project files.
