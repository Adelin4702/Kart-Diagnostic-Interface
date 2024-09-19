package org.example;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class MainFrame extends JFrame{
    private static final int TextFieldHeight = 30;
    private static final int TextFieldWidth = 180;
    private static final int PADDING_X = 160;
    private static final int PADDING_Y = 50;
    private static final int LEFT_PADDING = 50;
    private static final int FAR_LEFT_PADDING = 640;

    private int selectedServiceIndex = 0;
    private final JLabel requestLabel;
    private final JComboBox<String> serviceSelector;
    private static String requestString = "**";


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public MainFrame(ArrayList<DiagnosticService> serviceArrayList, ArrayList<String> exampleArrayList){
        MainFrame mainFrame = this;

        Border border = BorderFactory.createLineBorder(Color.WHITE);
        /******************************************* LEFT SIDE ******************************************************/
        String[] items = serviceArrayList.stream().map(DiagnosticService::getDiagnosticServiceName).toArray(String[]::new);


        this.setSize(1200, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setIconImage(new ImageIcon("C:\\Users\\PRP3CLJ\\Desktop\\Kart3.jpg").getImage());
        this.setLayout(null);

        JPanel backgroundPanel = getjPanel();


        JLabel title1 = new JLabel("Generate Custom Requests");
        title1.setFont(new Font("Times new roman", Font.BOLD | Font.ITALIC, 24));
        title1.setBounds(200, 20, 300, TextFieldHeight);
        title1.setForeground(Color.WHITE);
        title1.setVisible(true);
        this.add(title1);

        JPanel panel = new JPanel();
        panel.setOpaque(false); // Ensure the panel itself is transparent

        JScrollPane jScrollPane = new JScrollPane(panel);
        jScrollPane.setBounds(LEFT_PADDING, 140, 495, 400);
        jScrollPane.setBorder(border);
        jScrollPane.setOpaque(false); // Ensure the scroll pane is transparent
        jScrollPane.getViewport().setOpaque(false); // Ensure the viewport is transparent
        this.add(jScrollPane);

        JLabel selectLabel = new JLabel("Select Diagnostic Service: ");
        selectLabel.setFont(new Font("Times new roman", Font.BOLD | Font.ITALIC, 18));
        selectLabel.setBounds(LEFT_PADDING, 100, 1200, TextFieldHeight);
        selectLabel.setForeground(Color.WHITE);
        selectLabel.setVisible(true);
        this.add(selectLabel);

        JLabel requestText = new JLabel("Request: ");
        requestText.setFont(new Font("Times new roman", Font.BOLD | Font.ITALIC, 18));
        requestText.setBounds(LEFT_PADDING, 585, 100, TextFieldHeight);
        requestText.setVisible(true);
        requestText.setForeground(Color.WHITE);
        this.add(requestText);

        serviceSelector = new JComboBox<>(items);
        serviceSelector.setBounds(LEFT_PADDING + 230, 100, 250, 30); // Set position and size
        serviceSelector.setFont(new Font("Times new roman", Font.BOLD | Font.ITALIC, 18));
        serviceSelector.setVisible(true);
        serviceSelector.addActionListener(_ -> {
            jScrollPane.removeAll();
            selectedServiceIndex = serviceSelector.getSelectedIndex();
            DiagnosticService diagnosticService = serviceArrayList.get(serviceSelector.getSelectedIndex());
            int x = 30;
            int y = 30;

            for(Attribute attribute : diagnosticService.getDiagnosticServiceAttributes()){
                JLabel jLabel = new JLabel(attribute.getAttributeName() + " (" + attribute.getAttributeLength() + (attribute.getAttributeLength() > 1 ? " bytes" : " byte") + ")");
                jLabel.setForeground(Color.WHITE);
                jLabel.setFont(new Font("Times new roman", Font.BOLD | Font.ITALIC, 15));
                jLabel.setBounds(x, y, TextFieldWidth+40, TextFieldHeight);
                jLabel.setVisible(true);
                jScrollPane.add(jLabel);

                JFormattedTextField jFormattedTextField = new JFormattedTextField();
                jFormattedTextField.setFont(new Font("Times new roman", Font.BOLD | Font.ITALIC, 15));
                jFormattedTextField.setBounds(x + PADDING_X+40, y, TextFieldWidth, TextFieldHeight);
                jFormattedTextField.setVisible(true);

                jScrollPane.add(jFormattedTextField);
                y += PADDING_Y;
            }
            panel.repaint();
            jScrollPane.repaint();
            mainFrame.repaint();
        });

        JButton jButton1 = getjButton(serviceArrayList, jScrollPane, mainFrame);

        requestLabel = new JLabel();
        requestLabel.setText(requestString);
        requestLabel.setFont(new Font("Times new roman", Font.BOLD | Font.ITALIC, 18));
        requestLabel.setBounds(LEFT_PADDING + 80, 585, 400, TextFieldHeight);
        requestLabel.setForeground(Color.WHITE);
        requestLabel.setBackground(Color.RED);
        requestLabel.setOpaque(true);
        requestLabel.setVisible(true);

        this.add(requestLabel);
        this.add(jButton1);
        this.add(serviceSelector);
        this.setVisible(true);



        /******************************************** RIGHT SIDE ******************************************************/
        JLabel title2 = new JLabel("Generate Random Examples");
        title2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 24));
        title2.setBounds(FAR_LEFT_PADDING + 100, 20, 300, TextFieldHeight);
        title2.setForeground(Color.WHITE);
        title2.setVisible(true);
        this.add(title2);

        JTextArea example = new JTextArea();
        example.setEditable(false);
        example.setBounds(FAR_LEFT_PADDING, 140, 460, 400);
        example.setText("");
        example.setFont(new Font("Times new roman", Font.BOLD | Font.ITALIC, 16));
        example.setBorder(border);
        example.setBackground(Color.WHITE);
        example.setOpaque(false);

        example.setForeground(Color.WHITE);
        this.add(example);

        JButton jButton2 = getjButton(exampleArrayList, example, mainFrame);
        this.add(jButton2);

        this.add(backgroundPanel);
    }

    private JPanel getjPanel() {
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                ImageIcon icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Kart3_1.png")));
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f)); // 80% opacity
                g2d.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
                g2d.dispose();
            }
        };
        backgroundPanel.setBounds(0, 0, 1200, 700);
        return backgroundPanel;
    }

    private static JButton getjButton(ArrayList<String> exampleArrayList, JTextArea example, MainFrame mainFrame) {
        JButton jButton2 = new JButton();
        jButton2.setBounds(FAR_LEFT_PADDING, 550, 200, 30);
        jButton2.setText("Generate new example");
        jButton2.setFont(new Font("Times new roman", Font.BOLD | Font.ITALIC, 15));
        jButton2.setBackground(Color.green);
        jButton2.setVisible(true);
        jButton2.addActionListener(_ -> {
            int x = ((int) (Math.random() * 100)) % exampleArrayList.size();
            example.setText(exampleArrayList.get(x));
            mainFrame.repaint();
        });
        return jButton2;
    }

    private JButton getjButton(ArrayList<DiagnosticService> serviceArrayList, JScrollPane jScrollPane, MainFrame mainFrame) {
        JButton jButton1 = new JButton();
        jButton1.setBounds(LEFT_PADDING, 550, 100, 30);
        jButton1.setText("Generate");
        jButton1.setFont(new Font("Times new roman", Font.BOLD | Font.ITALIC, 15));
        jButton1.setBackground(Color.green);
        jButton1.setVisible(true);
        jButton1.addActionListener(_ -> {
            String newRequestString = "";
            boolean sem = true;
            int i = 0;
            for(Component component : jScrollPane.getComponents()){
                if(component.getClass() == JFormattedTextField.class){
                    String fieldText = ((JFormattedTextField) component).getText();
                    fieldText = fieldText.replace(" ", "");

                    if((fieldText.length() % 2) == 1){
                        fieldText = "0" + fieldText;
                    }

                    if(verifyInput(fieldText, i++, serviceArrayList)) {
                        newRequestString = newRequestString + fieldText;
                    } else {
                        sem = false;
                    }
                }
            }
            if(sem){

                requestString = formatString(newRequestString, serviceArrayList);

            }else{
                requestString = "--> INVALID INPUT <--";
            }
            requestLabel.setText(requestString);
            mainFrame.repaint();
        });
        return jButton1;
    }

    public boolean verifyInput(String text, int i, ArrayList<DiagnosticService> serviceArrayList){
        return (text.length() <= (serviceArrayList.get(selectedServiceIndex).getDiagnosticServiceAttributes().get(i).getAttributeLength() * 2)
            && !text.isEmpty()
            && text.matches("[0-9A-Fa-f]*"));
    }

    public String formatString(String text, ArrayList<DiagnosticService> serviceArrayList){
        text = Integer.toHexString(serviceArrayList.get(selectedServiceIndex).getDiagnosticServiceCode()) + text;
        text = text.toUpperCase();
        for(int i = text.length() - 2; i > 0; i-= 2){
            text = text.substring(0, i) + ", 0x" + text.substring(i);
        }
        text = "0x" + text;
        return text;
    }

}
