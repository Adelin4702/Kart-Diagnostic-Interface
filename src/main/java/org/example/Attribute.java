package org.example;

public class Attribute {
    private String attributeName;
    private int attributeLength;


    public Attribute() {
    }

    public Attribute(String attributeName, int attributeLength) {
        this.attributeName = attributeName;
        this.attributeLength = attributeLength;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public int getAttributeLength() {
        return attributeLength;
    }

    public void setAttributeLength(int attributeLength) {
        this.attributeLength = attributeLength;
    }
}
