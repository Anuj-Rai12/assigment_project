package com.example.mytestingproject.model


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("Bike") val bike: List<Bike>,
    @SerializedName("Bike_Slots") val bikeSlots: String,
    @SerializedName("Bus") val bus: List<Bu>,
    @SerializedName("Bus_Slots") val busSlots: String,
    @SerializedName("Capacity") val capacity: String,
    @SerializedName("Car") val car: List<Car>,
    @SerializedName("Car_Slots") val carSlots: String,
    @SerializedName("Commercial_Auto") val commercialAuto: List<CommercialAuto>,
    @SerializedName("Commercial_Auto_Slots") val commercialAutoSlots: String,
    @SerializedName("Commission") val commission: String,
    @SerializedName("Commission_Type") val commissionType: String,
    @SerializedName("_Contractor_Id") val contractorId: String,
    @SerializedName("Google_Address") val googleAddress: String,
    @SerializedName("Member_Types") val memberTypes: List<String>,
    @SerializedName("Mini_Truck") val miniTruck: List<MiniTruck>,
    @SerializedName("Mini_Truck_Slots") val miniTruckSlots: String,
    @SerializedName("Operator_Id") val operatorId: String,
    @SerializedName("Operator_Mobile") val operatorMobile: String,
    @SerializedName("Operator_Name") val operatorName: String,
    @SerializedName("Operator_Type") val operatorType: String,
    @SerializedName("Owner_Id") val ownerId: String,
    @SerializedName("Owner_Name") val ownerName: String,
    @SerializedName("Parking_Address") val parkingAddress: String,
    @SerializedName("_Parking_Id") val _parkingId: String,
    @SerializedName("Parking_Id") val parkingId: String,
    @SerializedName("Society_Address") val societyAddress: String,
    @SerializedName("_Society_Id") val societyId: String,
    @SerializedName("Society_Id") val _societyId: String,
    @SerializedName("Society_Name") val societyName: String,
    @SerializedName("Society_Photo") val societyPhoto: String,
    @SerializedName("Time_Interval") val timeInterval: String,
    @SerializedName("Today_Visitors") val todayVisitors: String,
    @SerializedName("Truck") val truck: List<Truck>,
    @SerializedName("Truck_Slots") val truckSlots: String,
    @SerializedName("Visitors") val visitors: List<Any>
)