package za.ac.cput.gui.item;

//Na'eem Mahoney
//218190751
//ADP3
//Group 13
//Capstone - Front-End
//ReadItemGui

//Imports
import za.ac.cput.entity.medication.Item;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReadItemGUI implements ActionListener {
    //Initializing Components
    private JFrame ItemFrame;
    private JPanel panelNorth, panelSouth, panelEast, panelWest, panelCenter;
    private JLabel lblHeading;
    private JLabel lblItemName, lblItemType, lblItemPrice, lblItemStock, lbItemID;
    private JTextField txtItemName, txtItemType,txtItemPrice, txtItemStock, txtItemID;
    private JButton btnRead, btnDelete, btnExit, btnClear;
    private JLabel Filler1, Filler2, Filler3, Filler4, Filler5;
    private Font headingFont;

    //Setting Up GUI Components
    public ReadItemGUI(){
        //Font
        headingFont = new Font("Arial", Font.BOLD, 18);

        //Panels
        ItemFrame = new JFrame("Item: ");
        panelNorth = new JPanel();
        panelSouth = new JPanel();
        panelEast = new JPanel();
        panelWest = new JPanel();
        panelCenter = new JPanel();
        panelNorth.setBackground(Color.LIGHT_GRAY);
        panelEast.setBackground(Color.LIGHT_GRAY);
        panelSouth.setBackground(Color.LIGHT_GRAY);
        panelWest.setBackground(Color.LIGHT_GRAY);
        panelCenter.setBackground(Color.LIGHT_GRAY);

        //Heading
        lblHeading = new JLabel("2. Enter Item ID",JLabel.CENTER);

        //Labels
        lblItemName = new JLabel("Name:");
        lblItemPrice = new JLabel("Price:");
        lblItemType = new JLabel("Type:");
        lblItemStock = new JLabel("Stock:");

        //TextFields
        txtItemName = new JTextField();
        txtItemPrice = new JTextField();
        txtItemType = new JTextField();
        txtItemStock = new JTextField();

        //TextFields
        txtItemID = new JTextField();

        //Fillers:
        Filler1 = new JLabel("===========");
        Filler1.setForeground(Color.LIGHT_GRAY);
        Filler2 = new JLabel("===========");
        Filler2.setForeground(Color.LIGHT_GRAY);
        Filler3 = new JLabel("================================");
        Filler3.setForeground(Color.LIGHT_GRAY);
        Filler4 = new JLabel("================================");
        Filler4.setForeground(Color.LIGHT_GRAY);
        Filler5 = new JLabel("================================");
        Filler5.setForeground(Color.LIGHT_GRAY);

        //Buttons:
        btnRead = new JButton("Read");
        btnDelete = new JButton("Delete");
        btnClear = new JButton("Clear");
        btnExit = new JButton("Exit");
    }

    //Setting GUI Layout
    public void setGUI() {
        //Panel Grids
        panelNorth.setLayout(new GridLayout(3, 1));
        panelEast.setLayout(new GridLayout(8, 1));
        panelSouth.setLayout(new GridLayout(1, 3));
        panelWest.setLayout(new GridLayout(8, 1));
        panelCenter.setLayout(new GridLayout(9, 1));
        panelEast.setLayout(new GridLayout(8, 1));

        //Adding the components to the panels:
        //Panel North:
        panelNorth.add(Filler5);
        panelNorth.add(lblHeading);
        panelNorth.add(txtItemID);

        //Panel West:
        panelWest.add(Filler1);

        //Panel Center:
        panelCenter.add(lblItemName);
        panelCenter.add(txtItemName);
        panelCenter.add(lblItemType);
        panelCenter.add(txtItemType);
        panelCenter.add(lblItemPrice);
        panelCenter.add(txtItemPrice);
        panelCenter.add(lblItemStock);
        panelCenter.add(txtItemStock);
        panelCenter.add(Filler4);

        //Panel East
        panelEast.add(Filler2);

        //Panel South:
        panelSouth.add(btnRead);
        panelSouth.add(btnDelete);
        panelSouth.add(btnClear);
        panelSouth.add(btnExit);

        //Heading
        lblHeading.setFont(headingFont);

        //Adding panels to Customer Frame:
        ItemFrame.add(panelNorth, BorderLayout.NORTH);
        ItemFrame.add(panelSouth, BorderLayout.SOUTH);
        ItemFrame.add(panelEast, BorderLayout.EAST);
        ItemFrame.add(panelCenter, BorderLayout.CENTER);
        ItemFrame.add(panelWest, BorderLayout.WEST);

        //Telling compiler to listen for actions from the buttons:
        btnRead.addActionListener(this);
        btnDelete.addActionListener(this);
        btnClear.addActionListener(this);
        btnExit.addActionListener(this);

        //Set GUI:
        ItemFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ItemFrame.pack();
        ItemFrame.setSize(400, 400);
        ItemFrame.setLocationRelativeTo(null);
        ItemFrame.setVisible(true);
    }


    public void actionPerformed(ActionEvent e) {
        //When Delete Button is clicked
        if(e.getActionCommand().equals("Read")){

            //Store textfield text in string
            String id = txtItemID.getText();
            httpmethods httpmethods = new httpmethods();

            //Use String as parameter in http read method and assign result to a Item
            Item it = httpmethods.findItem(id);
            System.out.println(it);

            //Item parameters are assigned to textfields
            txtItemName.setText(it.getItemName());
            txtItemType.setText(it.getItemType());
            String price = Double.toString(it.getItemPrice());
            String stock = Double.toString(it.getItemStock());
            txtItemPrice.setText(price);
            txtItemStock.setText(stock);
        }

        //When Delete Button is clicked
        if(e.getActionCommand().equals("Delete")){
            //Store textfield text in string
            String id = txtItemID.getText();
            httpmethods httpmethods = new httpmethods();

            //Use String as parameter in http method
            httpmethods.deleteItem(id);

            //Show message when successfully completed
            JOptionPane.showMessageDialog(null, "Item Deleted");
            txtItemID.setText("");
            txtItemName.setText("");
            txtItemType.setText("");
            txtItemPrice.setText("");
            txtItemStock.setText("");
        }

        //When Clear Button is clicked
        if(e.getActionCommand().equals("Clear")){
            txtItemID.setText("");
            txtItemName.setText("");
            txtItemType.setText("");
            txtItemPrice.setText("");
            txtItemStock.setText("");
        }

        //When Exit Button is clicked
        if(e.getActionCommand().equals("Exit")){
            ItemFrame.dispose();
        }
    }
}
