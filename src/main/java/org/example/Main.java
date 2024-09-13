package org.example;

import java.util.ArrayList;
import java.util.Comparator;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {


        ArrayList<DiagnosticService> serviceArrayList = initServices();
        ArrayList<String> exampleArrayList = initExamples();

        new MainFrame(serviceArrayList, exampleArrayList);

    }

    public static ArrayList<DiagnosticService> initServices(){
        ArrayList<DiagnosticService> serviceArrayList = new ArrayList<>();

        Attribute didMSB = new Attribute("Did MSB", 1);
        Attribute didLSB = new Attribute("Did LSB", 1);
        Attribute writeData = new Attribute("Data to be written", 5);
        Attribute resetType = new Attribute("Reset Type", 1);
        Attribute diagnosticSession = new Attribute("Diagnostic Session", 1);
        Attribute groupOfDTCH = new Attribute("Group of DTC High Byte", 1);
        Attribute groupOfDTCM = new Attribute("Group of DTC Middle Byte", 1);
        Attribute groupOfDTCL = new Attribute("Group of DTC Low Byte", 1);

        ArrayList<Attribute> attributes22 = new ArrayList<>();
        attributes22.add(didMSB);
        attributes22.add(didLSB);
        DiagnosticService readDataByIdentifier = new DiagnosticService("Read Data By Identifier", (short) 0x22, attributes22);
        serviceArrayList.add(readDataByIdentifier);

        ArrayList<Attribute> attributes2E = new ArrayList<>();
        attributes2E.add(didMSB);
        attributes2E.add(didLSB);
        attributes2E.add(writeData);
        DiagnosticService writeDataByIdentifier = new DiagnosticService("Write Data By Identifier", (short) 0x2E, attributes2E);
        serviceArrayList.add(writeDataByIdentifier);

        ArrayList<Attribute> attributes11 = new ArrayList<>();
        attributes11.add(resetType);
        DiagnosticService ecuReset = new DiagnosticService("ECU Reset", (short)0x11, attributes11);
        serviceArrayList.add(ecuReset);

        ArrayList<Attribute> attribute10 = new ArrayList<>();
        attribute10.add(diagnosticSession);
        DiagnosticService diagnosticSessionControl = new DiagnosticService("Diagnostic Session Control", (short)0x10, attribute10);
        serviceArrayList.add(diagnosticSessionControl);

        ArrayList<Attribute> attribute14 = new ArrayList<>();
        attribute14.add(groupOfDTCH);
        attribute14.add(groupOfDTCM);
        attribute14.add(groupOfDTCL);
        DiagnosticService clearDiagnosticInformation = new DiagnosticService("Clear Diagnostic Information", (short)0x14, attribute14);
        serviceArrayList.add(clearDiagnosticInformation);

        serviceArrayList.sort(Comparator.comparing(DiagnosticService::getDiagnosticServiceName));
        return serviceArrayList;
    }

    public static ArrayList<String> initExamples(){
        ArrayList<String> examples = new ArrayList<>();

        examples.add("\n    REQUEST: 0x11 0x03 \n\n" +
                "    RESPONSE: 0x51 0x03\n\n\n" +
                "    Request:\n" +
                "\tFirst byte: ECU Reset service(0x11)\n" +
                "\tSecond byte: Reset type - Soft reset(0x03)\n\n" +
                "    Response:\n" +
                "\tFirst byte: Positive Response SID for service 0x11\n" +
                "\tSecond byte: the reset type performed(Soft reset)");


        examples.add("\n    REQUEST: 0x11 0x01 \n\n" +
                "    RESPONSE: 0x7F 0x11 0x22\n\n\n" +
                "    Request:\n" +
                "\tFirst byte: ECU Reset service(0x11)\n" +
                "\tSecond byte: Reset type - Hard reset(0x01)\n\n" +
                "    Response:\n" +
                "\tFirst byte: Negative Response SID \n" +
                "\tSecond byte: the requested service(Ecu Reset)\n" +
                "\tThird byte: the Negative Response Code - Conditions\n\t       Not Correct(0x22)");


        examples.add("\n    REQUEST: 0x22 0x20 0x10  \n\n" +
                "    RESPONSE: 0x62 0x20 0x10 0x1B 0x3C 0x70\n\n\n" +
                "    Request:\n" +
                "\tFirst byte: Read Data by Identifier(0x22)\n" +
                "\tSecond byte: Did High byte\n" +
                "\tThird byte: Did Low byte\n\n" +
                "    Response:\n" +
                "\tFirst byte: Positive Response SID for service 0x22\n" +
                "\tSecond byte: Did High byte\n" +
                "\tThird byte: Did Low byte\n" +
                "\tBytes 4 - 6: the data stored in did");


        examples.add("\n    REQUEST: 0x2E 0x20 0x10 0x1B 0x3C 0x70 \n\n" +
                "    RESPONSE: 0x6E 0x20 0x10\n\n\n" +
                "    Request:\n" +
                "\tFirst byte: Write Data by Identifier(0x2E)\n" +
                "\tSecond byte: Did High byte\n" +
                "\tThird byte: Did Low byte\n" +
                "\tBytes 4 - 6: the data to be written in did\n\n"+
                "    Response:\n" +
                "\tFirst byte: Positive Response SID for service 0x2E\n" +
                "\tSecond byte: Did High byte\n" +
                "\tThird byte: Did Low byte\n" );


        examples.add("\n    REQUEST: 0x2E 0x02 0x50 0x00 0x01 0x02 0x50 \n\n" +
                "    RESPONSE: 0x7F 0x2E 0x33\n\n\n" +
                "    Request:\n" +
                "\tFirst byte: Write Data by Identifier(0x2E)\n" +
                "\tSecond byte: Did High byte\n" +
                "\tThird byte: Did Low byte\n" +
                "\tBytes 4 - 6: the data to be written in did\n\n"+
                "    Response:\n" +
                "\tFirst byte: Negative Response SID\n" +
                "\tSecond byte: the requested service(Write data by\n\t       identifier)\n" +
                "\tThird byte: Negative Response Code - Security Access\n\t       Denied(0x33)\n" );


        examples.add("\n    REQUEST: 0x10 0x03 \n\n" +
                "    RESPONSE: 0x50 0x03 0x00 0x32 0x01 0xF4\n\n\n" +
                "    Request:\n" +
                "\tFirst byte: Diagnostic Session Control(0x10)\n" +
                "\tSecond byte: Diagnostic Session Type - Extended S.\n\n" +
                "    Response:\n" +
                "\tFirst byte: Positive Response SID for service 0x10\n" +
                "\tSecond byte: the Diagnostic Session Type(0x03)\n" +
                "\tBytes 3 - 6: the Session Parameter Record\n" );


        examples.add("\n    REQUEST: 0x10 0x02 \n\n" +
                "    RESPONSE: 0x7F 0x10 0x22\n\n\n" +
                "\tFirst byte: Diagnostic Session Control(0x10)\n" +
                "\tSecond byte: Diagnostic Session Type - Programming\n\t       Session\n\n" +
                "    Response:\n" +
                "\tFirst byte: Negative Response SID\n" +
                "\tSecond byte: the requested service(Diagnostic\n\t       Session Control)\n" +
                "\tThird byte: Negative Response Code - Conditions not\n\t       correct(0x22)\n" );

        return examples;
    }
}