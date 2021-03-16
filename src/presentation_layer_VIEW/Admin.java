package presentation_layer_VIEW;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

public class Admin extends JFrame {
    private JPanel adminPanel;
    private JButton viewMenu;
    private JButton editBP;
    private JButton createBP;
    private JTextField oldBPedit;
    private JTextField newBSedit;
    private JTextField newBPcreate;
    private JTextField priceBPcreate;
    private JTextField oldCPedit;
    private JTextField newCPedit;
    private JTextField newCPcreate;
    private JTextField CPcomponents;
    private JTextField nameBPdelete;
    private JTextField nameCPdelete;
    private JButton editCP;
    private JButton createCP;
    private JButton deleteBP;
    private JButton deleteCP;
    private JTable tableMenu;
    private JScrollPane scrollPane;

    public Admin() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(adminPanel);
        this.setLocation(10, 5);
        this.setTitle("ADMIN AREA");
        this.setVisible(true);
        this.scrollPane.setPreferredSize(new Dimension(550, 450));
        this.pack();
    }

    public String getOldBPedit() {
        if (oldBPedit.getText().equals("") ) {
            JOptionPane.showMessageDialog(this,"Insert name of the old base product","Product name warning",JOptionPane.WARNING_MESSAGE);
            return null;
        }
        Pattern patternName = Pattern.compile("^[a-z]+(([a-zA-Z ])?[a-zA-Z]*)*$");
        if (!patternName.matcher(oldBPedit.getText()).matches()) {
            JOptionPane.showMessageDialog(this,"Name of the base product must contain only letters","Product name warning",JOptionPane.WARNING_MESSAGE);
            return null;
        }

        return oldBPedit.getText();
    }

    public String getNewBSedit() {
        if (newBSedit.getText().equals("") ) {
            JOptionPane.showMessageDialog(this,"Insert name of the new base product","Product name warning",JOptionPane.WARNING_MESSAGE);
            return null;
        }
        Pattern patternName = Pattern.compile("^[a-z]+(([a-zA-Z ])?[a-zA-Z]*)*$");
        if (!patternName.matcher(newBSedit.getText()).matches()) {
            JOptionPane.showMessageDialog(this,"Name of the base product must contain only letters","Product name warning",JOptionPane.WARNING_MESSAGE);
            return null;
        }

        return newBSedit.getText();
    }

    public String getNewBPcreate() {
        if (newBPcreate.getText().equals("") ) {
            JOptionPane.showMessageDialog(this,"Insert name of the base product","Product name warning",JOptionPane.WARNING_MESSAGE);
            return null;
        }
        Pattern patternName = Pattern.compile("^[a-z]+(([a-zA-Z ])?[a-zA-Z]*)*$");
        if (!patternName.matcher(newBPcreate.getText()).matches()) {
            JOptionPane.showMessageDialog(this,"Name of the base product must contain only letters","Product name warning",JOptionPane.WARNING_MESSAGE);
            return null;
        }

        return newBPcreate.getText();
    }

    public String getPriceBPcreate() {
        if (priceBPcreate.getText().equals("")) {
            JOptionPane.showMessageDialog(this,"Insert price of the base product","Product price warning",JOptionPane.WARNING_MESSAGE);
            return null;
        }
        Pattern patternPrice = Pattern.compile("^\\d+$");
        if (!patternPrice.matcher(priceBPcreate.getText()).matches()) {
            JOptionPane.showMessageDialog(this,"Price of the base product must contain only decimal","Product price warning",JOptionPane.WARNING_MESSAGE);
            return null;
        }

        return priceBPcreate.getText();
    }

    public String getOldCPedit() {
        if (oldCPedit.getText().equals("") ) {
            JOptionPane.showMessageDialog(this,"Insert name of the composite product","Product name warning",JOptionPane.WARNING_MESSAGE);
            return null;
        }
        Pattern patternName = Pattern.compile("^[a-z]+(([a-zA-Z ])?[a-zA-Z]*)*$");
        if (!patternName.matcher(oldCPedit.getText()).matches()) {
            JOptionPane.showMessageDialog(this,"Name of the composite product must contain only letters","Product name warning",JOptionPane.WARNING_MESSAGE);
            return null;
        }

        return oldCPedit.getText();
    }

    public String getNewCPedit() {
        if (newCPedit.getText().equals("") ) {
            JOptionPane.showMessageDialog(this,"Insert name of the old base product","Product name warning",JOptionPane.WARNING_MESSAGE);
            return null;
        }
        Pattern patternName = Pattern.compile("^[a-zA-Z]+([,][\\s][a-zA-Z ][a-zA-Z]*)*$");
        if (!patternName.matcher(newCPedit.getText()).matches()) {
            JOptionPane.showMessageDialog(this,"Name of the base products must contain only letters","Product name warning",JOptionPane.WARNING_MESSAGE);
            return null;
        }

        return newCPedit.getText();
    }

    public String getNewCPcreate() {
        if (newCPcreate.getText().equals("") ) {
            JOptionPane.showMessageDialog(this,"Insert name of the composite product","Product name warning",JOptionPane.WARNING_MESSAGE);
            return null;
        }
        Pattern patternName = Pattern.compile("^[a-z]+(([a-zA-Z ])?[a-zA-Z]*)*$");
        if (!patternName.matcher(newCPcreate.getText()).matches()) {
            JOptionPane.showMessageDialog(this,"Name of the composite product must contain only letters","Product name warning",JOptionPane.WARNING_MESSAGE);
            return null;
        }

        return newCPcreate.getText();
    }

    public String getCPcomponents() {
        if (CPcomponents.getText().equals("") ) {
            JOptionPane.showMessageDialog(this,"Insert name of the composite product","Product name warning",JOptionPane.WARNING_MESSAGE);
            return null;
        }
        Pattern patternName = Pattern.compile("^[a-zA-Z]+([,][\\s][a-zA-Z ][a-zA-Z]*)*$");
        if (!patternName.matcher(CPcomponents.getText()).matches()) {
            JOptionPane.showMessageDialog(this,"Name of the base product must contain base products differentiated by ', '","Product name warning",JOptionPane.WARNING_MESSAGE);
            return null;
        }

        return CPcomponents.getText();
    }

    public String getNameBPdelete() {
        if (nameBPdelete.getText().equals("") ) {
            JOptionPane.showMessageDialog(this,"Insert name of the base product","Product name warning",JOptionPane.WARNING_MESSAGE);
            return null;
        }
        Pattern patternName = Pattern.compile("^[a-z]+(([a-zA-Z ])?[a-zA-Z]*)*$");
        if (!patternName.matcher(nameBPdelete.getText()).matches()) {
            JOptionPane.showMessageDialog(this,"Name of the base product must contain only letters","Product name warning",JOptionPane.WARNING_MESSAGE);
            return null;
        }

        return nameBPdelete.getText();
    }

    public String getNameCPdelete() {
        if (nameCPdelete.getText().equals("") ) {
            JOptionPane.showMessageDialog(this,"Insert name of the composite product","Product name warning",JOptionPane.WARNING_MESSAGE);
            return null;
        }
        Pattern patternName = Pattern.compile("^[a-z]+(([a-zA-Z ])?[a-zA-Z]*)*$");
        if (!patternName.matcher(nameCPdelete.getText()).matches()) {
            JOptionPane.showMessageDialog(this,"Name of the composite product must contain only letters","Product name warning",JOptionPane.WARNING_MESSAGE);
            return null;
        }

        return nameCPdelete.getText();
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public void viewMenuButton(ActionListener actionListener) {
        viewMenu.addActionListener(actionListener);
    }

    public void editBPbutton(ActionListener actionListener) {
        editBP.addActionListener(actionListener);
    }

    public void editCPbutton(ActionListener actionListener) {
        editCP.addActionListener(actionListener);
    }

    public void createBPbutton(ActionListener actionListener) {
        createBP.addActionListener(actionListener);
    }

    public void createCPbutton(ActionListener actionListener) {
        createCP.addActionListener(actionListener);
    }

    public void deleteBPbutton(ActionListener actionListener) {
        deleteBP.addActionListener(actionListener);
    }

    public void deleteCPbutton(ActionListener actionListener) {
        deleteCP.addActionListener(actionListener);
    }
}

