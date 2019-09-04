package com.example.medicalcenterapp.models;

public class PharmacyModal {

    private String name;
    private String address;
    private String contact;

    public PharmacyModal(String name, String address, String contact) {
        this.name = name;
        this.address = address;
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getContact() {
        return contact;
    }

    public static final String[] PHARMACY_NAMES = {"Supermed Pharmacy - Bradfield", "3rd Avenue Pharmacy", "Hillside Pharmacy",
    "37 Baines Avenue Pharmacy", "Chitungwiza Pharmacy", "Civic Westgate", "Apex Pharmacy Gweru",
    "Greenwood Pharmacy - Kwekwe"};

    public static final String[] PHARMACY_ADDRESSES = {"Shop 2 Zonkezizwe Complex, Burnside Road, Bradfiel, Bulawayo",
            "49 Lobengula Street, Bulawayo", "Hillside Shopping Centre, 2 Cnr Ashurt & Stafford Avenue, Bulawayo",
    "37 Baines Avenue, Harare", "223 Makoni Shopping Centre, Chitungwiza", "Shop Mp7 Westgate Shopping Centre, Lamagundi Street, Westgate",
    "Shop 3 Elizabeth Masion, 65 Sixth Street, Gweru", "Shop 7 First Mutual Complex, Robert Mugabe Way, Kwekwe"};

    public static final String[] PHARMACY_CONTACT = {"+263 9 733 88", "+263 9 649 35", "+263 9 242 334", "+263 4 794 257",
    "+263 70 210 33", "+263 4 332 043", "+263 54 222 8168", "+263 55 252 0047"};
}
