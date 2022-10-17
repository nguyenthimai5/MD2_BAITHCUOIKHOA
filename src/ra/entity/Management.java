package ra.entity;

import java.util.*;

public class Management {
    static List<Authors> listAuthor = new ArrayList<>();
    static List<Book> listBook = new ArrayList<>();
    static int  countBook=0;
    static int countAuthor=0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Book bookGet = new Book();
        bookGet.getData(null, listBook);
        Authors authorGet = new Authors();
        authorGet.getData(listAuthor, null);
        do {
            System.out.println("**************QUAN LY CUA HANG SACH****************");
            System.out.println("1. Quan ly tac gia");
            System.out.println("2. Quan ly sach");
            System.out.println("3. Thoat");
            System.out.print("Su lua chon cua ban: ");
            int choice=0;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            }catch (NumberFormatException e){
                System.err.println("Vui long nhap dang so!");
            }
            switch (choice) {
                case 1:
                    Management.displayMenuAuthor(scanner);
                    break;
                case 2:
                    Management.displayMenuBook(scanner);
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.err.println("Vui long chon tu 1-3");
            }
        } while (true);
    }

    public static void displayMenuAuthor(Scanner scanner) {
        boolean existAuthor = true;
        do {
            System.out.println("*******************QUAN LY TAC GIA*******************");
            System.out.println("1. Danh sach tac gia");
            System.out.println("2. Them cac tac gia");
            System.out.println("3. Cap nhat thong tin tac gia");
            System.out.println("4. Cap nhat trang thai tac gia");
            System.out.println("5. Thoat");
            int choiceAuthor=0;
            try {
                choiceAuthor = Integer.parseInt(scanner.nextLine());
            }catch (NumberFormatException e){
                System.err.println("Vui long nhap dang so!");
            }
            switch (choiceAuthor) {
                case 1:
                    Management.displayListAuthor();
                    break;
                case 2:
                    Management.inputListAuthor(scanner);
                    break;
                case 3:
                    Management.updateAuthor(scanner);
                    break;
                case 4:
                    Management.updateAuthorStatus(scanner);
                    break;
                case 5:
                    existAuthor = false;
                    break;
                default:
                    System.err.println("Vui long chon tu 1-5");
            }
        } while (existAuthor);
    }

    public static void displayListAuthor() {
        System.out.printf("%-10s%-50s%-15s\n", "Ma tac gia", "Ten tac gia", "Trang thai");
        for (Authors author : listAuthor) {
            author.displayData();
        }
    }

    public static void inputListAuthor(Scanner scanner) {
        System.out.println("Vui long nhap vao so tac gia can nhap thong tin: ");
        try {
            countAuthor = Integer.parseInt(scanner.nextLine());
        }catch (NumberFormatException e){
            System.err.println("Vui long nhap dang so!");
        }
        for (int i = 0; i < countAuthor; i++) {
            Authors author = new Authors();
            author.inputData(scanner, listAuthor, null);
            listAuthor.add(author);
            author.insertData(listAuthor);
        }
    }

    public static void updateAuthor(Scanner scanner) {
        System.out.println("Nhap vao ma tac gia can cap nhat thong tin: ");
        int authorIdUpdate = Integer.parseInt(scanner.nextLine());
        boolean checkExist = false;
        for (Authors author : listAuthor) {
            if (author.getAuthorId() == authorIdUpdate) {
                //cap nhat thong tin
                checkExist = true;
                System.out.println("Nhap vao ten tac gia: ");
                String authorName = scanner.nextLine();
                if (authorName.trim() != "" && authorName.trim().length() != 0) {
                    do {
                        if (authorName.length() >= 6 && authorName.length() <= 50) {
                            author.setAuthorName(authorName);
                            break;
                        }
                    } while (true);
                }
                System.out.println("Chon trang thai tac gia: ");
                System.out.println("1. Hoat dong");
                System.out.println("2. Khong hoat dong");
                System.out.println("3. Khong cap nhat");
                System.out.print("Su lua chon cua ban: ");
                int choice=0;
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                }catch (NumberFormatException e){
                    System.err.println("Vui long nhap dang so!");
                }

                switch (choice) {
                    case 1:
                        author.setAuthorStatus(true);
                        break;
                    case 2:
                        author.setAuthorStatus(false);
                        break;
                }
                author.insertData(listAuthor);
                break;
            }
        }
        if (!checkExist) {
            System.err.println("Khong tim thay tac gia co ma nhu vay");
        }
    }


    public static void updateAuthorStatus(Scanner sc) {
        System.out.println("Nhap vao ma tac gia can cap nhat thong tin: ");
        int authorIdUdpate = Integer.parseInt(sc.nextLine());
        for (Authors author : listAuthor) {
            if (author.getAuthorId() == authorIdUdpate) {
                author.setAuthorStatus(!author.isAuthorStatus());
                author.insertData(listAuthor);
                break;
            }
        }

    }

    public static void displayMenuBook(Scanner sc) {
        boolean existBook = true;
        do {
            System.out.println("******************QUAN LY SACH*******************");
            System.out.println("1. Danh sach sach");
            System.out.println("2. Them cac sach");
            System.out.println("3. Cap nhat thong tin sach");
            System.out.println("4. Cap nhat trang thai sach");
            System.out.println("5. Tinh loi nhuan sach");
            System.out.println("6. Sap xep sach theo gia tang dan");
            System.out.println("7. Tim kiem sach theo ten sach, ten tac gia");
            System.out.println("8. Ban sach");
            System.out.println("9. Thoat");
            System.out.print("Su lua chon cua ban: ");
            int choice=0;
            try {
                choice = Integer.parseInt(sc.nextLine());
            }catch (NumberFormatException e){
                System.err.println("Vui ong nhap dang so");
            }

            switch (choice) {
                case 1:
                    Management.displayListBook();
                    break;
                case 2:
                    Management.inputListBook(sc);
                    break;
                case 3:
                    Management.updateBook(sc);
                    break;
                case 4:
                    Management.updateStatus(sc);
                    break;
                case 5:
                    Management.profitBook();
                    break;
                case 6:
                    Management.sapXepSach();
                    break;
                case 7:
                    Management.searchBook(sc);
                    break;
                case 8:
                    Management.sellBook(sc);
                    break;
                case 9:
                    existBook = false;
                    break;
                default:
                    System.err.println("Vui long chon tu 1-9");
            }
        } while (existBook);
    }

    public static void displayListBook() {
        System.out.printf("%-10s%-50s%-10s%-10s%-30s%-30s%-15s\n", "Ma sach", "Ten sach", "Gia ban", "So luong", "Tieu de", "Nha xuat ban", "Trang thai");
        for (Book book : listBook) {
            book.displayData();
        }
    }

    public static void inputListBook(Scanner scanner){
        System.out.println("Nhap vao so luong sach can nhap thong tin: ");
        do {
            try {
                countBook = Integer.parseInt(scanner.nextLine());
                break;
            }catch (NumberFormatException e){
                System.err.println("Vui long nhap dang so!");
            }
        }while (true);

        for (int i = 0; i < countBook; i++) {
            Book book = new Book();
            book.inputData(scanner,listAuthor,listBook);
            listBook.add(book);
            book.insertData(listBook);
        }
    }
    public static void profitBook() {
        Book book = new Book();
        for (int i = 0; i < countBook; i++) {
            book.calProfit();
        }
        System.out.println("Đã tính xong lợi nhuận cho tất cả sach");

    }

    public static void sapXepSach() {
        Collections.sort(listBook, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return (int) o1.getExportPrice() - (int) o2.getExportPrice();
            }
        });
    }

    public static void searchBook(Scanner scanner) {
        System.out.println("Vui lòng nhập tên tác giả hoặc tên sách mà bạn muốn tìm:");
        scanner.nextLine();
        Book book = new Book();
        boolean check = false;
        String stringNameNew = scanner.nextLine();
        for (Authors athourNameSearch : listAuthor) {
            for (Book bookSearchName : listBook) {
                if (athourNameSearch.getAuthorName().trim().contains(stringNameNew) || bookSearchName.getBookName().trim().contains(stringNameNew)) {
                    book.displayData();
                    check = true;
                    break;
                }
            }
        }
        if (!check) {
            System.out.printf("%s Ten Sach hoac ten tác giả bạn tìm không có trong dữ liệu", stringNameNew);
        }
    }

    public static void sellBook(Scanner scanner) {
        String spBan = "";
        System.out.println("Bạn muốn bán sản phẩm nào");
        scanner.nextLine();
        spBan = scanner.nextLine();
        System.out.println("Bán bao nhiêu:");
        boolean check = false;
        int ban = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < countBook; i++) {
            if (listBook.get(i).getBookName().equals(spBan)) {
                if (listBook.get(i).getQuantity() > ban) {
                    int sell = listBook.get(i).getQuantity();
                    listBook.get(i).setQuantity(sell -= ban);
                    System.out.println("Da ban xong san pham");
                } else {
                    System.out.println("Sản phẩm không có đủ hàng trong kho để bán cho bạn");
                }
                check = true;
                break;
            }
            System.out.println("Đã bán xong sách!");

        }
        if (!check) {
            System.out.printf("Sách %s bạn muốn bán không có trong dữ liệu", spBan);
        }
    }
    public static void updateStatus(Scanner scanner) {
        System.out.println("Bạn muốn cập nhật trạng thái cho sản phẩm nào:");
        scanner.nextLine();
        String updateStatusBookName = scanner.nextLine();
        boolean check = false;
        for (int i = 0; i < countBook; i++) {
            if (listBook.get(i).getBookName().trim().equals(updateStatusBookName)) {
                System.out.println("Nhập trạng thái bạn muốn cập nhật:");
                String upDateBookStatus = scanner.nextLine();
                if (upDateBookStatus != "" || upDateBookStatus.length() != 0) {
                    listBook.get(i).setBookStatus(Boolean.parseBoolean(upDateBookStatus));
                    check = true;
                    break;
                }
            }
        }
        if (!check) {
            System.out.printf("%s sản phẩm không có trong dữ liêu!", updateStatusBookName);
        } else {
            System.out.println("Đã cập nhật xong trạng thái của sách!");
        }
    }
    public static void updateBook(Scanner scanner) {
        System.out.println("Vui lòng nhập tên sách cần cập nhật:");
        String stringName = scanner.nextLine();
        for (int i = 0; i < countBook; i++) {
            if (stringName.trim().equals(listBook.get(i).getBookName())) {
                System.out.println("Nhap ma cua sach");
                do {
                    String bookId = scanner.nextLine();
                    boolean check = false;
                    if (bookId.trim().length() != 0 && bookId != "") {
                        if (bookId.trim().length() == 5) {
                            if (bookId.charAt(0) == 'B') {
                                if (!bookId.equals(listBook.get(i).getBookId())) {
                                    listBook.get(i).setBookId(bookId);
                                    check = true;
                                    break;
                                }

                                if (!check) {
                                    System.err.println("Mã sách đã có trong dữ liệu!");
                                }

                            } else {
                                System.err.println("Vui long nhap ma sach bat dau bang B!");
                            }
                        } else {
                            System.err.println("Vui long nhap ma sach gon dung 5 ky tu!");
                        }
                    } else {
                        break;
                    }
                } while (true);
                System.out.println("Nhap ten sach: ");
                do {
                    boolean check = false;
                    String bookName = scanner.nextLine();
                    if (bookName.trim().length() > 10) {
                        if (!bookName.trim().equals(listBook.get(i).getBookName())) {
                            if (bookName != "" && bookName.length() != 0) {
                                listBook.get(i).setBookName(bookName);
                                check = true;
                                break;
                            } else {
                                break;
                            }
                        }
                    } else {
                        System.err.println("Vui long nhap ten sach co tren 10 ky tu!");
                    }
                } while (true);
                System.out.println("Gia nhap cua sach:");
                do {
                    float importPrice = 0;
                    try {
                        importPrice = Float.parseFloat(scanner.nextLine());
                    } catch (NumberFormatException exception) {
                        System.err.println("Vui long nhap dang so!");
                    }
                    if (importPrice > 0) {
                        listBook.get(i).setImportPrice(importPrice);
                        break;
                    } else {
                        System.err.println("Vui long nhap gia sach co gia tri hon khong 0!");
                    }
                } while (true);
                System.out.println("Nhap gia ban cua sach:");
                do {
                    float exportPrice = 0;
                    try {
                        exportPrice = Float.parseFloat(scanner.nextLine());
                    } catch (NumberFormatException exception) {
                        System.err.println("Vui long nhap dang so!");
                    }
                    if (exportPrice >= (exportPrice * 20 / 100) + exportPrice) {
                        listBook.get(i).setImportPrice(exportPrice);
                        break;
                    } else {
                        System.err.println("Vui long nhap gia bán có giá trị lớn hơn ít nhất 20% so với giá nhập ");
                    }
                } while (true);
                System.out.println("So luong  cua sach:");
                String quantity = scanner.nextLine();
                if (quantity.length() != 0 && quantity == "") {
                    listBook.get(i).setQuantity(Integer.parseInt(quantity));
                } else {
                    break;
                }
                System.out.println("Nhap tieu de cua sach:");
                do {
                    String title = scanner.nextLine();
                    if (title.length() != 0 && title == "") {
                        if (title.length() >= 20) {
                            listBook.get(i).setTitle(title);
                            break;
                        } else {
                            System.err.println("Vui long nhap ten tieu de cua sach lon hon 20 chu:");
                        }
                    } else {
                        break;
                    }
                } while (true);
                System.out.println("Nha xuat ban cua sach:");
                String publishingCompany = scanner.nextLine();
                if (publishingCompany.length() != 0 && publishingCompany == "") {
                    listBook.get(i).setPublisher(publishingCompany);
                } else {
                    break;
                }
                System.out.println("Trang thai cua sach:");
                String bookStatus = scanner.nextLine();
                if (bookStatus.length() != 0 && bookStatus == "") {
                    listBook.get(i).setBookStatus(Boolean.parseBoolean(bookStatus));
                    if (bookStatus == "true") {
                        bookStatus = "Dang con sach!";
                    } else {
                        bookStatus = "Sach da het!";
                    }
                } else {
                    break;
                }

            }
        }

    }
}
