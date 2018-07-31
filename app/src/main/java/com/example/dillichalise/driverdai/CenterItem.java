package com.example.dillichalise.driverdai;

public class CenterItem extends AbstractItem {

    public CenterItem(String label, boolean is_booked, int id) {
        super(label, is_booked, id);
    }


    @Override
    public int getType() {
        return TYPE_CENTER;
    }

}
