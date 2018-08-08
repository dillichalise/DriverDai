package com.example.dillichalise.driverdai;

public class CenterItem extends AbstractItem {

    public CenterItem(String label, boolean is_booked, int id,String booked_by,String location) {
        super(label, is_booked, id,booked_by,location);
    }


    @Override
    public int getType() {
        return TYPE_CENTER;
    }

}
