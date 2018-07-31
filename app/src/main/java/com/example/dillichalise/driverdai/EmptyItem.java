package com.example.dillichalise.driverdai;

public class EmptyItem extends AbstractItem {

    public EmptyItem(String label, boolean is_booked, int id) {
        super(label, is_booked, id);
    }


    @Override
    public int getType() {
        return TYPE_EMPTY;
    }

}
