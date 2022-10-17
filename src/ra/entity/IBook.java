package ra.entity;

import ra.entity.Authors;
import ra.entity.Book;

import java.util.List;
import java.util.Scanner;

public interface IBook{
    String PATH_AUTHOR = "author.txt";
    String PATH_BOOK = "book.txt";
    void inputData(Scanner scanner, List<Authors> listAuthor, List<Book> listBook);
    void displayData();
    void insertData(Object object);
    void getData(List<Authors> listAuthor, List<Book> listBook);
}