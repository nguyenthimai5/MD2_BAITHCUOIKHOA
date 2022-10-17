package ra.entity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Book implements IBook{
    private String bookId;
    private String bookName;
    private float importPrice;
    private float exportPrice;
    private float profit;
    private int quantity;
    private List<Authors> listAuthor = new ArrayList<>();
    private String title;
    private String content;
    private String publisher;
    private boolean bookStatus;
    private static final Float RATE = 1.2F;

    public Book() {
    }

    public Book(String bookId, String bookName, float importPrice, float exportPrice, float profit, int quantity, List<Authors> listAuthor, String title, String content, String publisher, boolean bookStatus) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.profit = profit;
        this.quantity = quantity;
        this.listAuthor = listAuthor;
        this.title = title;
        this.content = content;
        this.publisher = publisher;
        this.bookStatus = bookStatus;

    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public float getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(float importPrice) {
        this.importPrice = importPrice;
    }

    public float getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(float exportPrice) {
        this.exportPrice = exportPrice;
    }

    public float getProfit() {
        return profit;
    }

    public void setProfit(float profit) {
        this.profit = profit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<Authors> getListAuthor() {
        return listAuthor;
    }

    public void setListAuthor(List<Authors> listAuthor) {
        this.listAuthor = listAuthor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public boolean isBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(boolean bookStatus) {
        this.bookStatus = bookStatus;
    }

    @Override
    public void inputData(Scanner scanner, List<Authors> listAuthor, List<Book> listBook) {
        System.out.println("Nhap ma cua sach");
        do {
            this.bookId = scanner.nextLine();
            boolean checkBook = false;
            if (this.bookId.trim().length() == 5) {
                if (this.bookId.charAt(0) == 'B') {
                    for (Book listBookId : listBook) {
                        if (this.bookId.equals(listBookId.getBookId())) {
                            checkBook = true;
                            break;
                        }
                    }
                    if (checkBook) {
                        System.err.println("Ma sach da co trong du lieu");
                    } else {
                        break;
                    }
                } else {
                    System.err.println("Vui long nhap ma sach bat dau bang B!");
                }
            } else {
                System.err.println("Vui long nhap ma sach gon dung 5 ky tu!");
            }
        } while (true);
        System.out.println("Nhap ten sach: ");
        do {
            this.bookName = scanner.nextLine();
            if (this.bookName.trim().length() > 10) {
                break;
            } else {
                System.err.println("Vui long nhap ten sach co tren 10 ky tu!");
            }
        } while (true);
        System.out.println("Gia nhap cua sach:");
        do {
            this.importPrice = 0;
            try {
                this.importPrice = Float.parseFloat(scanner.nextLine());
                if (this.importPrice > 0) {
                    break;
                } else {
                    System.err.println("Vui long nhap gia sach co gia tri hon khong 0!");
                }
            } catch (NumberFormatException exception) {
                System.err.println("Vui long nhap dang so!");
            }

        } while (true);
        System.out.println("Nhap gia ban cua sach:");
        do {
            this.exportPrice = 0;
            try {
                this.exportPrice = Float.parseFloat(scanner.nextLine());
                if (this.exportPrice >= (this.importPrice * RATE)) {
                    break;
                } else {
                    System.err.println("Vui long nhap gia bán có giá trị lớn hơn ít nhất 20% so với giá nhập ");
                }
            } catch (NumberFormatException exception) {
                System.err.println("Vui long nhap dang so!");
            }
        } while (true);
        System.out.println("So luong  cua sach:");

        do {
            try {
                this.quantity = Integer.parseInt(scanner.nextLine());
                if (this.quantity > 0) {
                    break;
                } else {
                    System.out.println("Vui long nhap so luong cua sach lon hon 1!");
                }
            } catch (NumberFormatException e) {
                System.err.println("Vui long nhap so luong sach bang danng so");
            }
        } while (true);
        System.out.println("Chon cac tac gia cua sach: ");
        do {
            int cnt = 1;
            for (Authors author : listAuthor) {
                System.out.printf("%d.%s\n", cnt, author.getAuthorName());
                cnt++;
            }
            System.out.print("Chon tac gia: ");
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= listAuthor.size()) {
                    //add tac gia chon vao danh sach tac gia cua sach
                    boolean checkAuthorExist = false;
                    for (Authors authorExist : this.listAuthor) {
                        if (authorExist.getAuthorId() == listAuthor.get(choice - 1).getAuthorId()) {
                            checkAuthorExist = true;
                        }
                    }
                    if (!checkAuthorExist) {
                        this.listAuthor.add(listAuthor.get(choice - 1));
                    } else {
                        System.err.println("Tac gia da ton tai trong danh sach tac gia cua sach");
                    }
                    System.out.println("Ban co muon chon them tac gia khong: ");
                    System.out.println("1. Co");
                    System.out.println("2. Khong");
                    System.out.print("Lua chon cua ban: ");
                    int choiceExit = Integer.parseInt(scanner.nextLine());
                    if (choiceExit != 1) {
                        break;
                    }
                } else {
                    System.err.println("Vui long chon tac gia trong danh sach");
                }
            } catch (Exception ex) {
                System.err.println("Vui long chon 1 so nguyen");
            }
        } while (true);

        System.out.println("Nhap tieu de cua sach:");
        do {
            this.title = scanner.nextLine();
            if (this.title.length() >= 20) {
                break;
            } else {
                System.err.println("Vui long nhap ten tieu de cua sach lon hon 20 chu:");
            }
        } while (true);
        System.out.println("Noi dung cua sach:");
        this.content = scanner.nextLine();
        System.out.println("Nha xuat ban cua sach:");
        this.publisher = scanner.nextLine();
        System.out.println("Chon trang thai sach: ");
        System.out.println("1. Con sach");
        System.out.println("2. Het sach");
        System.out.print("Lua chon cua ban: ");
        int choiceBookStatus = Integer.parseInt(scanner.nextLine());
        if (choiceBookStatus != 1) {
            this.bookStatus = false;
        } else {
            this.bookStatus = true;
        }
    }

    @Override
    public void displayData() {
        String status;
        if (this.bookStatus) {
            status = "Con sach";
        } else {
            status = "Het sach";
        }
        System.out.printf("%-10s%-50s%-10.0f%-10d%-30s%-30s%-15s\n", this.bookId, this.bookName, this.exportPrice,
                this.quantity, this.title, this.publisher, status);
    }


    @Override
    public void insertData(Object object) {
        File file = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            file = new File(PATH_BOOK);
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject((List<Book>) object);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException ex2) {
                ex2.printStackTrace();
            }
        }
    }

    @Override
    public void getData(List<Authors> listAuthor, List<Book> listBook) {
        File file = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            file = new File(PATH_BOOK);
            if (file.exists()) {
                fis = new FileInputStream(file);
                ois = new ObjectInputStream(fis);
                listBook.addAll((List<Book>) ois.readObject());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException ex2) {
                ex2.printStackTrace();
            }
        }
    }

    public void calProfit() {
        this.profit = this.exportPrice - this.importPrice;
    }

    public void buyBook(int numberBook) {
        if (numberBook > this.quantity) {
            System.err.println("So luong sach khong du de ban");
        } else {
            this.quantity -= numberBook;
        }
    }
    }
