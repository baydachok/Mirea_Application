package com.example.mirea_application;

public class ListClock {
    private String name;
    private int flagResource;

    ListClock(String name, int resource) {
        this.flagResource = resource;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFlagResource() {
        return this.flagResource;
    }

    public void setFlagResource(int flagResource) {
        this.flagResource = flagResource;
    }
}
