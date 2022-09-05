package project;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class GUI extends JFrame {
    private ReadFile model;
    private JPanel panelMid;
    private JPanel panelBottom;
    private JPanel IDPanel;
    private JPanel namePanel;
    private JPanel aboutPanel;
    private JPanel authorPanel;
    private JPanel pricePanel;
    private JPanel listPanel;
    private JButton addButton;
    private JButton deleteButton;
    private JButton searchButton;
    private JButton viewAllButton;
    private JButton recoverButton;
    private JButton cancelButton;
    private JLabel nameLabel;
    private JLabel aboutLabel;
    private JLabel authorLabel;
    private JLabel priceLabel;
    private JLabel searchLabel;
    public JTextField nameText, aboutText, authorText, priceText, IDText, searchText;
    public JTable bookTable;
    private static DefaultTableModel tableModel;
    protected static ArrayList<Book> books = new ArrayList<>();
    public int id = 1;
    public int counter = 1;

    public GUI() {
        ReadFile.readBookFromFile();
        this.form();
        this.setID();
        this.setVisible(true);
    }

    public void form() {
        this.setTitle("Library");
        this.setSize(800, 700);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new GridLayout(3, 1));

        JPanel panelTop = new JPanel();
        panelTop.setLayout(null);
        panelMid = new JPanel();
        panelMid.setLayout(new BorderLayout());
        panelBottom = new JPanel();

        addButton = new JButton("Add");
        JButton editButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        recoverButton = new JButton("Recover");
        cancelButton = new JButton("Cancel");



        Controller controller = new Controller(this);
        addButton.addActionListener(controller);
        deleteButton.addActionListener(controller);
        editButton.addActionListener(controller);
        recoverButton.addActionListener(controller);
        cancelButton.addActionListener(controller);


        JLabel IDLabel = new JLabel("ID:");
        IDLabel.setBounds(10, 2, 120, 30);
        nameLabel = new JLabel("Name book:");
        nameLabel.setBounds(10, 2, 120, 30);
        aboutLabel = new JLabel("About:");
        aboutLabel.setBounds(10, 2, 120, 30);
        authorLabel = new JLabel("Author:");
        authorLabel.setBounds(10, 2, 120, 30);
        priceLabel = new JLabel("Price:");
        priceLabel.setBounds(10, 2, 120, 30);

        IDPanel = new JPanel();
        namePanel = new JPanel();
        aboutPanel = new JPanel();
        authorPanel = new JPanel();
        pricePanel = new JPanel();
        listPanel = new JPanel(new FlowLayout());

        IDPanel.setBounds(0, 0, 400, 60);
        IDPanel.setLayout(null);
        namePanel.setBounds(400, 0, 400, 60);
        namePanel.setLayout(null);
        aboutPanel.setBounds(0, 60, 400, 60);
        aboutPanel.setLayout(null);
        authorPanel.setBounds(400, 60, 400, 60);
        authorPanel.setLayout(null);
        pricePanel.setBounds(0, 120, 400, 60);
        pricePanel.setLayout(null);
        listPanel.setBounds(0, 182, 800, 150);

        IDText = new JTextField();
        IDText.setBounds(120, 2, 250, 30);
        IDText.setEditable(false);
        nameText = new JTextField();
        nameText.setBounds(120, 2, 250, 30);
        aboutText = new JTextField();
        aboutText.setBounds(120, 2, 250, 30);
        authorText = new JTextField();
        authorText.setBounds(120, 2, 250, 30);
        priceText = new JTextField();
        priceText.setBounds(120, 2, 250, 30);


        panelBottom.setLayout(null);
        panelBottom.add(IDPanel);
        panelBottom.add(namePanel);
        panelBottom.add(aboutPanel);
        panelBottom.add(authorPanel);
        panelBottom.add(pricePanel);
        panelBottom.add(listPanel);

        IDPanel.add(IDLabel);
        IDPanel.add(IDText);
        namePanel.add(nameLabel);
        namePanel.add(nameText);
        aboutPanel.add(aboutLabel);
        aboutPanel.add(aboutText);
        authorPanel.add(authorLabel);
        authorPanel.add(authorText);
        pricePanel.add(priceLabel);
        pricePanel.add(priceText);
        listPanel.add(addButton);
        listPanel.add(editButton);
        listPanel.add(recoverButton);
        listPanel.add(deleteButton);
        listPanel.add(cancelButton);

        tableModel = new DefaultTableModel();
        headerTable();
        bookTable = new JTable(tableModel);
        setTableValues();
        bookTable.setBounds(0, 0, 100, 300);
        JScrollPane sp = new JScrollPane(bookTable);
        panelMid.add(sp, BorderLayout.CENTER);
        this.add(panelTop);
        this.add(panelMid);
        this.add(panelBottom);

        searchLabel = new JLabel("Name of searching book:");
        searchLabel.setBounds(50, 80, 200, 30);
        searchText = new JTextField();
        searchText.setBounds(300, 80, 300, 30);

        searchButton = new JButton("Search");
        searchButton.setBounds(200, 150, 80, 30);
        searchButton.addActionListener(controller);

        viewAllButton = new JButton("View ALL");
        viewAllButton.setBounds(400, 150, 100, 30);
        viewAllButton.addActionListener(controller);

        panelTop.add(searchLabel);
        panelTop.add(searchText);
        panelTop.add(searchButton);
        panelTop.add(viewAllButton);

    }

    public void search() {
        Object[] data = new Object[5];
        tableModel.setRowCount(0);
        String search = this.searchText.getText();
        for (Book book : books) {
            if (book.bookName.contains(search)) {
                data[0] = book.bookID;
                data[1] = book.bookName;
                data[2] = book.about;
                data[3] = book.author;
                data[4] = book.price;
                tableModel.addRow(data);
            }
        }
    }
    public void deleteBook() {
        int row = bookTable.getSelectedRow();
        if (row != -1) {
            int choice = JOptionPane.showConfirmDialog(this, "Are you sure delete");
            if (choice == JOptionPane.YES_OPTION) {
                this.books.remove(books.get(row));
                ReadFile.addBookToFIle();
                tableModel.removeRow(row);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Choose book from table to delete");
        }
    }

    public void recoverBook() {
        int row = bookTable.getSelectedRow();
        IDText.setText(tableModel.getValueAt(row, 0) + "");
        nameText.setText(tableModel.getValueAt(row, 1) + "");
        aboutText.setText(tableModel.getValueAt(row, 2) + "");
        authorText.setText(tableModel.getValueAt(row, 3) + "");
        priceText.setText(tableModel.getValueAt(row, 4) + "");
    }
    public void setEmptyText() {
        nameText.setText("");
        aboutText.setText("");
        authorText.setText("");
        priceText.setText("");
    }
    public void setUnEnable() {
        addButton.setEnabled(false);
        deleteButton.setEnabled(false);
    }

    public void setEnable() {
        addButton.setEnabled(true);
        deleteButton.setEnabled(true);
    }
    public void setID() {
        try {
            id = (books.get(books.size() - 1).bookID + 1);
            this.IDText.setText(String.valueOf(id));
        } catch (IndexOutOfBoundsException e) {
            this.IDText.setText(String.valueOf(id));
        }
    }

    public void checkID(int ID) {
        for (int i = 0; i < books.size(); ) {
            if (ID == books.get(i).bookID) {
                counter = i;
                break;
            }
            i++;
        }
    }
    public Boolean isNull() {
        if (nameText.getText().equals("") || authorText.getText().equals("") || aboutText.getText().equals("")
                || priceText.getText().equals("")) {
            return true;
        }
        return false;
    }

    public boolean isNumber(String fieldText) {
        for (int i = 0; i < fieldText.length(); i++) {
            if (fieldText.charAt(i) <'0' || fieldText.charAt(i) >'9') {
                return false;
            }
        }
        return true;
    }

    public void headerTable() {
        tableModel.addColumn("ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("About");
        tableModel.addColumn("Author");
        tableModel.addColumn("Price");
    }

    public Book getValuesFromForm() {
        int ID = Integer.parseInt(IDText.getText());
        String name = nameText.getText();
        String about = aboutText.getText();
        String author = authorText.getText();
        String price = priceText.getText();
        Book book = new Book(ID, name, about, author, price);
        return book;
    }

    public void setTableValues() {
        Object[] data = new Object[6];
        tableModel.setRowCount(0);
            for (Book book : books) {
                data[0] = book.bookID;
                data[1] = book.bookName;
                data[2] = book.about;
                data[3] = book.author;
                data[4] = book.price;
                tableModel.addRow(data);
            }

    }
}



