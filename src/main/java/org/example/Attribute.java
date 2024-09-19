package org.example;

public class Attribute {
    private String attributeName;
    private int attributeLength;

    public Attribute(String attributeName, int attributeLength) {
        this.attributeName = attributeName;
        this.attributeLength = attributeLength;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public int getAttributeLength() {
        return attributeLength;
    }
}
