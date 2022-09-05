package project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Controller implements ActionListener {
    private GUI GUI;
    public Controller(GUI GUI) {
        this.GUI = GUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Add")) {
            if (GUI.isNull()) {
                JOptionPane.showMessageDialog(null, "Please fill all field text");
            } else if (!(GUI.isNumber(GUI.priceText.getText()))) {
                JOptionPane.showMessageDialog(null, "Please input a number at the price field");
            } else {
                GUI.books.add(this.GUI.getValuesFromForm());
                ReadFile.addBookToFIle();
                this.GUI.setEmptyText();
                this.GUI.setTableValues();
                this.GUI.id++;
                this.GUI.setID();
            }
        } else if (command.equals("Delete")) {
            try {
                this.GUI.deleteBook();
            } catch (IndexOutOfBoundsException exception) {
                JOptionPane.showMessageDialog(null, "Please choose a row from the book table");
            }
        } else if (command.equals("Update")) {
            if (!(GUI.isNull())) {
                int id = Integer.parseInt(this.GUI.IDText.getText());
                this.GUI.checkID(id);
                GUI.books.set(this.GUI.counter, this.GUI.getValuesFromForm());
                ReadFile.addBookToFIle();
                this.GUI.setTableValues();
                this.GUI.setEmptyText();
                this.GUI.setEnable();
                this.GUI.setID();
            } else {
                JOptionPane.showMessageDialog(null, "Please choose a row from the book table and recover data");
            }
        } else if (command.equals("Recover")) {
            try {
                this.GUI.recoverBook();
                this.GUI.setUnEnable();
            } catch (IndexOutOfBoundsException exception) {
                JOptionPane.showMessageDialog(null, "Please choose a row from the book table");
            }
        } else if (command.equals("Cancel")) {
            this.GUI.setEmptyText();
            this.GUI.setEnable();
            this.GUI.setID();
        } else if (command.equals("Search")) {
            this.GUI.search();
        } else if (command.equals("View ALL")) {
            this.GUI.setTableValues();
            this.GUI.searchText.setText("");
        }
    }
}
