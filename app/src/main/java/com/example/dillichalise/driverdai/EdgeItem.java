package com.example.dillichalise.driverdai;

public class EdgeItem extends AbstractItem {


    public EdgeItem(String label, boolean is_booked, int id) {
        super(label, is_booked, id);
    }



    @Override
    public int getType() {
        return TYPE_EDGE;
    }

}
