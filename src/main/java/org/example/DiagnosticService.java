package org.example;

import java.util.ArrayList;

public class DiagnosticService {
    private String diagnosticServiceName;
    private short diagnosticServiceCode;
    private ArrayList<Attribute> diagnosticServiceAttributes;

    public DiagnosticService() {
    }

    public DiagnosticService(String diagnosticServiceName, short diagnosticServiceCode, ArrayList<Attribute> diagnosticServiceAttributes) {
        this.diagnosticServiceName = diagnosticServiceName;
        this.diagnosticServiceAttributes = diagnosticServiceAttributes;
        this.diagnosticServiceCode = diagnosticServiceCode;
    }

    public String getDiagnosticServiceName() {
        return diagnosticServiceName;
    }

    public void setDiagnosticServiceName(String diagnosticServiceName) {
        this.diagnosticServiceName = diagnosticServiceName;
    }

    public short getDiagnosticServiceCode() {
        return diagnosticServiceCode;
    }

    public void setDiagnosticServiceCode(short diagnosticServiceCode) {
        this.diagnosticServiceCode = diagnosticServiceCode;
    }

    public ArrayList<Attribute> getDiagnosticServiceAttributes() {
        return diagnosticServiceAttributes;
    }

    public void setDiagnosticServiceAttributes(ArrayList<Attribute> diagnosticServiceAttributes) {
        this.diagnosticServiceAttributes = diagnosticServiceAttributes;
    }
}
