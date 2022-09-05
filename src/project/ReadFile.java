package project;

import java.io.*;
public class ReadFile {
    public static void addBookToFIle() {
        try {
            File outFile = new File("data.txt");
            FileOutputStream outputStream = new FileOutputStream(outFile);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            for (Book book : GUI.books) {
                objectOutputStream.writeObject(book);
            }
            objectOutputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void readBookFromFile() {
        try {
            File infile = new File("data.txt");
            FileInputStream inputStream = new FileInputStream(infile);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            while (true) {
                Book book = (Book) objectInputStream.readObject();
                GUI.books.add(book);
                if (book == null) {
                    break;
                }
            }
            objectInputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


}

