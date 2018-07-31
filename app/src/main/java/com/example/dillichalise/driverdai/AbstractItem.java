package com.example.dillichalise.driverdai;

public abstract class AbstractItem {

    public static final int TYPE_CENTER = 0;
    public static final int TYPE_EDGE = 1;
    public static final int TYPE_EMPTY = 2;

    private String label;
    public boolean is_booked;
    public int id;

    public AbstractItem(String label, boolean is_booked, int id) {
        this.label = label;
        this.is_booked = is_booked;
        this.id = id;
    }


    public String getLabel() {
        return label;
    }

    abstract public int getType();




}
