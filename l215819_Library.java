import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.*;

public class l215819_Library {

    public interface Configuration {
        void AddItemCon();
        void EditItemCon(int id);
        void DeleteItemCon(int id);
        void ViewAllCon();
        void ViewAllItemsCon();
        void ViewItemByIdCon(int id);
    }

    public static class LibraryItem implements Configuration {
        Scanner sc = new Scanner(System.in);
        static int arraySize = 100;
        public static String[] Array = new String[arraySize];
        public static String [] arr = new String[arraySize];
        public static int Index = 0;
        private String title;
        private String author;
        private int year;
        private String publisherCompany;
        private int popularityCount;
        private int price;
        private String date;
        public void loader(){
            String fileName = "data.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null && Index < arraySize) {
                Array[Index] = line;
                Index++;
            }
            localLoader();
            System.out.println("\nLoaded items from file \""+fileName+"\"");
        } catch (IOException e) {
            e.printStackTrace();
        }
        }
        public void localLoader(){
            String s = "";
            for (int m = 0; m<Index; m++){
                String item = Array[m];
            char itemType = item.charAt(0);
            if (itemType == '1'){
                String element = Array[m];
                String[] words = element.split(",");
                title = words[1];
                author = words[2];
                words[3] = words[3].trim();
                year = Integer.parseInt(words[3]);
                words[4] = words[4].trim();
                popularityCount = Integer.parseInt(words[4]);
                words[5] = words[5].trim();
                price = Integer.parseInt(words[5]);
                s = "Book:"+title+" by"+author+"("+year+")";
            }
            else if (itemType == '2'){
                String element = Array[m];
                char[] str = element.toCharArray();
                    int a;
                    title = "";
                for (a=2; str[a]!=',';a++){
                    title = title+str[a];
                }
                a++;
                    author="";
                for (; str[a]!='.';a++){
                    author = author+str[a];
                }
                a++;
                a++;
                    publisherCompany = "";
                for (; str[a]!=',';a++){
                    publisherCompany = publisherCompany+str[a];
                }
                a++;
                    popularityCount = 0;
                    String temp = "";
                for (a++; str[a]!=',';a++){
                    temp = temp+str[a];
                }
                popularityCount = Integer.parseInt(temp);
                a++;
                    price = 0;
                    temp = "";
                for (a++; a<element.length(); a++){
                    temp = temp+str[a];
                }
                price = Integer.parseInt(temp);
                s = "Magazine:"+title+" by"+publisherCompany+" (Authors:"+author+")";
            }
            else if (itemType == '3'){
                String element = Array[m];
                String[] words = element.split(",");
                title = words[1];
                publisherCompany = words[2];
                words[3] = words[3].trim();
                popularityCount = Integer.parseInt(words[3]);
                date = words[4];
                s = "Newspaper:"+title+" by"+publisherCompany+" (Date:"+date+")";
            }
            arr[m] = s;
            }
        }
        public void loadBack(){
        //String[] data = new String[100];
        String filePath = "data.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("");

            for (int i = 0; i<Index; i++) {
                String line = Array[i];
                //System.out.println(line);
                writer.write(line);
                writer.newLine();
            }
            System.out.println("File updated.");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("An error occurred while writing to the file.");}
        }
        public void AddItem(LibraryItem item) {
            item.AddItemCon();
        }
        public void EditItem(int id) {
            EditItemCon(id);
        }
        public void DeleteItem(int id) {
            DeleteItemCon(id);
        }
        public void ViewAll(LibraryItem item) {
            item.ViewAllCon();
        }
        public void ViewAllItems() {
            ViewAllItemsCon();
        }
        public void ViewItemById(int id) {
            ViewItemByIdCon(id);
        }
        @Override
        public void ViewAllItemsCon() {
        if (Index == 0){
            System.out.println("No items found");
        }
        else {
    System.out.println("-------------------------------------------------------------------------");
        for (int i = 0; i < Index; i++) {
            System.out.println(arr[i]);
        }
    System.out.println("-------------------------------------------------------------------------");}
        }
        @Override
        public void AddItemCon() {
            System.out.println("Generic Add Item Function");
        }
        @Override
        public void EditItemCon(int id) {
            String item = Array[id-1];
            if (id == 0 || id > Index)
            System.out.println("ID not found.");
            else{
            char itemType = item.charAt(0);
            if (itemType == '1'){
                String element = Array[id-1];
                String[] words = element.split(",");
                title = words[1];
                author = words[2];
                words[3] = words[3].trim();
                year = Integer.parseInt(words[3]);
                words[4] = words[4].trim();
                popularityCount = Integer.parseInt(words[4]);
                words[5] = words[5].trim();
                price = Integer.parseInt(words[5]);
                    if (id == 0 || id > Index)
                    System.out.println("Book ID not found.");
                    else{
                        System.out.print("What do you want to edit?\n1. Book title\n2. Book author\n3. Publication year\n4. Price\nEnter: ");
                        char op = sc.next().charAt(0);
                        switch(op){
                            case '1':
                        System.out.print("Edit book title: ");
                        title = ' '+sc.next();
                            break;
                            case '2':
                        System.out.print("Edit book author: ");
                        author = ' '+sc.next();
                            break;
                            case '3':
                        System.out.print("Edit book publication year: ");
                        year = sc.nextInt();
                            break;
                            case '4':
                        System.out.print("Edit book price: ");
                        price = sc.nextInt();
                            break;
                            default:
                            break;}
                        String book = "1,"+title+","+author+", "+year+", "+popularityCount+", "+price;
                        Array[id-1] = book;
                    }
                    System.out.println(arr[id-1]+" has been edited to:"+title+" by"+author+"("+year+")");
            }
            else if (itemType == '2'){
                String element = Array[id-1];
                char[] str = element.toCharArray();
                    int a;
                    title = "";
                for (a=2; str[a]!=',';a++){
                    title = title+str[a];
                }
                a++;
                    author="";
                for (; str[a]!='.';a++){
                    author = author+str[a];
                }
                a++;
                a++;
                    publisherCompany = "";
                for (; str[a]!=',';a++){
                    publisherCompany = publisherCompany+str[a];
                }
                a++;
                    popularityCount = 0;
                    String temp = "";
                for (a++; str[a]!=',';a++){
                    temp = temp+str[a];
                }
                popularityCount = Integer.parseInt(temp);
                a++;
                    price = 0;
                    temp = "";
                for (a++; a<element.length(); a++){
                    temp = temp+str[a];
                }
                //words[5] = words[5].trim();
                price = Integer.parseInt(temp);
                    if (id == 0 || id > Index)
                    System.out.println("Magazine ID not found.");
                    else{
                        System.out.print("What do you want to edit?\n1. Magazine title\n2. Magazine authors\n3. Publisher company\n4. Price\nEnter: ");
                        char op = sc.next().charAt(0);
                        switch(op){
                            case '1':
                        System.out.print("Edit magazine title: ");
                        title = ' '+sc.next();
                            break;
                            case '2':
                        System.out.print("Edit magazine authors (comma separated): ");
                        author = ' '+sc.next();
                            break;
                            case '3':
                        System.out.print("Edit publisher company of the magazine: ");
                        publisherCompany = ' '+sc.next();
                            break;
                            case '4':
                        System.out.print("Edit price of the magazine: ");
                        price = sc.nextInt();
                            break;
                            default:
                            break;}
                        String magazine = "2,"+title+","+author+".,"+publisherCompany+", "+popularityCount+", "+price;
                        Array[id-1] = magazine;
                    }
                    System.out.println(arr[id-1]+" has been edited to:"+title+" by"+publisherCompany+" (Authors:"+author+")");
            }
            else if (itemType == '3'){
                String element = Array[id-1];
                String[] words = element.split(",");
                title = words[1];
                publisherCompany = words[2];
                words[3] = words[3].trim();
                popularityCount = Integer.parseInt(words[3]);
                date = words[4];
                    if (id == 0 || id > Index)
                    System.out.println("Newspaper ID not found.");
                    else{
                        System.out.print("What do you want to edit?\n1. Newspaper title\n2. Newspaper publisher name\n3. Date\nEnter: ");
                        char op = sc.next().charAt(0);
                        switch(op){
                            case '1':
                        System.out.print("Edit newspaper title: ");
                        title = ' '+sc.next();
                            break;
                            case '2':
                        System.out.print("Edit publisher company of the newspaper: ");
                        publisherCompany = ' '+sc.next();
                            break;
                            case '3':
                        System.out.print("Edit date: ");
                        date = ' '+sc.next();
                            break;
                            default:
                            break;}
                        String newspaper = "3,"+title+","+publisherCompany+", "+popularityCount+","+date;
                        Array[id-1] = newspaper;
                    }
                    System.out.println(arr[id-1]+" has been edited to:"+title+" by"+publisherCompany+" (Date:"+date+")");
            }
            else
            System.out.println("ItemType is invalid.");}
            localLoader();
        }
        @Override
        public void DeleteItemCon(int id) {
            String item = arr[id-1];
            if (id == 0 || id > Index)
            System.out.println("ID not found.");
            else{
                for (int l=id-1; l<Index; l++){
                Array[l] = "";
                Array[l] = Array[l+1];
                Borrower.BorrowArray[l] = Borrower.BorrowArray[l+1];}
                Index--;
                Borrower.idx--;
            }
            localLoader();
            System.out.println(item+" has been deleted");
        }
        @Override
        public void ViewAllCon() {
            System.out.println("Generic View All Items Function");
        }
        @Override
        public void ViewItemByIdCon(int id) {
            if (id == 0 || id > Index)
            System.out.println("ID not found.");
            else
            System.out.println(arr[id-1]);
        }
        public void HotPicks() {
            String [] HotPicksArray = new String[Index];
            String [] HParr = new String[Index];
            for (int p = 0; p<Index; p++){
                HParr[p] = Array[p];
            }
            String s = "";
                String title;
                String author;
                int year;
                String publisherCompany;
                int popularityCount;
                int price;
                String date;
            for (int m = 0; m<Index; m++){
                String item = Array[m];
            char itemType = item.charAt(0);
            if (itemType == '1'){
                String element = Array[m];
                String[] words = element.split(",");
                title = words[1];
                author = words[2];
                words[3] = words[3].trim();
                year = Integer.parseInt(words[3]);
                words[4] = words[4].trim();
                popularityCount = Integer.parseInt(words[4]);
                words[5] = words[5].trim();
                price = Integer.parseInt(words[5]);
                s = "Popularity Count: "+popularityCount+"\tBook:"+title+" by"+author+"("+year+")";
            }
            else if (itemType == '2'){
                String element = Array[m];
                char[] str = element.toCharArray();
                    int a;
                    title = "";
                for (a=2; str[a]!=',';a++){
                    title = title+str[a];
                }
                a++;
                    author="";
                for (; str[a]!='.';a++){
                    author = author+str[a];
                }
                a++;
                a++;
                    publisherCompany = "";
                for (; str[a]!=',';a++){
                    publisherCompany = publisherCompany+str[a];
                }
                a++;
                    popularityCount = 0;
                    String temp = "";
                for (a++; str[a]!=',';a++){
                    temp = temp+str[a];
                }
                popularityCount = Integer.parseInt(temp);
                a++;
                    price = 0;
                    temp = "";
                for (a++; a<element.length(); a++){
                    temp = temp+str[a];
                }
                price = Integer.parseInt(temp);
                s = "Popularity Count: "+popularityCount+"\tMagazine:"+title+" by"+publisherCompany+" (Authors:"+author+")";
            }
            else if (itemType == '3'){
                String element = Array[m];
                String[] words = element.split(",");
                title = words[1];
                publisherCompany = words[2];
                words[3] = words[3].trim();
                popularityCount = Integer.parseInt(words[3]);
                date = words[4];
                s = "Popularity Count: "+popularityCount+"\tNewspaper:"+title+" by"+publisherCompany+" (Date:"+date+")";
            }
            HotPicksArray[m] = s;
            }
            for (int i = 0; i<Index-1; i++) {
                for (int j = i; j<Index; j++) {
                    int popCnt1 = getPopularityCount(HParr[i]);
                    int popCnt2 = getPopularityCount(HParr[j]);
                    if (popCnt1 > popCnt2) {
                        String temp = HotPicksArray[i];
                        HotPicksArray[i] = HotPicksArray[j];
                        HotPicksArray[j] = temp;
                        temp = HParr[i];
                        HParr[i] = HParr[j];
                        HParr[j] = temp;
                    }
                }
            }
    System.out.println("-------------------------------------------------------------------------");
            for (int l = 0; l<Index; l++){
                System.out.println(HotPicksArray[l]);
            }
    System.out.println("-------------------------------------------------------------------------");
        }
        public int getPopularityCount(String item) {
            int popCnt = 0;
            char itemType = item.charAt(0);
            if (itemType == '1'){
                String[] words = item.split(",");
                words[4] = words[4].trim();
                popCnt = Integer.parseInt(words[4]);
            }
            else if (itemType == '2'){
                char[] str = item.toCharArray();
                    int a;
                    title = "";
                for (a=2; str[a]!=',';a++){
                    title = title+str[a];
                }
                a++;
                    author="";
                for (; str[a]!='.';a++){
                    author = author+str[a];
                }
                a++;
                a++;
                    publisherCompany = "";
                for (; str[a]!=',';a++){
                    publisherCompany = publisherCompany+str[a];
                }
                a++;
                    popularityCount = 0;
                    String temp = "";
                for (a++; str[a]!=',';a++){
                    temp = temp+str[a];
                }
                popCnt = Integer.parseInt(temp);
            }
            else if (itemType == '3'){
                String[] words = item.split(",");
                words[3] = words[3].trim();
                popCnt = Integer.parseInt(words[3]);
            }
            return popCnt;
        }
    }

    public static class Book extends LibraryItem {
        private int itemType;
        private String title;
        private String author;
        private int year;
        private int popularityCount;
        private int price;

        public Book() {
            itemType = 1;
            title = "";
            author = "";
            year = 0;
            popularityCount = 0;
            price = 0;
        }
        public Book(String tit, String aut, int yr, int pCount, int pr) {
            itemType = 1;
            title = tit;
            author = aut;
            year = yr;
            popularityCount = pCount;
            price = pr;
        }
        @Override
        public void AddItemCon() {
            System.out.print("Enter the title of the book: ");
            title= sc.nextLine();
            System.out.print("Enter the author of the book: ");
            author = sc.nextLine();
            System.out.print("Enter the year of publication of the book: ");
            year = sc.nextInt();
            popularityCount = 0;
            System.out.print("Enter the price of the book: ");
            price = sc.nextInt();
            String book = itemType+", "+title+", "+author+", "+year+", "+popularityCount+", "+price;
            Array[Index] = book;
            Index++;
            Borrower.BorrowArray[Borrower.idx] = "false"+", "+book;
            Borrower.idx++;
            localLoader();
            System.out.println("A new book \""+title+" by "+author+"("+year+")\" has been added to items!");
        }
        @Override
        public void ViewAllCon() {
            String [] vw = new String[Index];
            String book = "";
            int in = 0;
            for (int i = 0; i<Index; i++){
                book = Array[i];
                char it = book.charAt(0);
                if (it == '1'){
                vw[in] = arr[i];
                in++;
                }
            }
        if (in == 0){
            System.out.println("No book found");
        }
        else {
    System.out.println("-------------------------------------------------------------------------");
        for (int l = 0; l < in; l++) {
            System.out.println(vw[l]);
        }
    System.out.println("-------------------------------------------------------------------------");}
        }
    }

    public static class Magazine extends LibraryItem {
        private int itemType;
        private String title;
        private String author;
        private String publisherCompany;
        private int popularityCount;
        private int price;

        public Magazine() {
            itemType = 2;
            title = "";
            author = "";
            publisherCompany = "";
            popularityCount = 0;
            price = 0;
        }
        public Magazine(String tit, String aut, String pComp, int pCount, int pr) {
            itemType = 2;
            title = tit;
            author = aut;
            publisherCompany = pComp;
            popularityCount = pCount;
            price = pr;
        }
        @Override
        public void AddItemCon() {
            System.out.print("Enter the title of the magazine: ");
            title= sc.nextLine();
            System.out.print("Enter the authors of the magazine (comma separated): ");
            author = sc.nextLine();
            System.out.print("Enter the name of the publisher company: ");
            publisherCompany = sc.nextLine();
            popularityCount = 0;
            System.out.print("Enter the price of the magazine: ");
            price = sc.nextInt();
            String magazine = itemType+", "+title+", "+author+"., "+publisherCompany+", "+popularityCount+", "+price;
            Array[Index] = magazine;
            Index++;
            Borrower.BorrowArray[Borrower.idx] = "false"+", "+magazine;
            Borrower.idx++;
            localLoader();
            System.out.println("A new magazine \""+title+" by "+publisherCompany+" ("+author+")\" has been added to items!");
        }
        @Override
        public void ViewAllCon() {
            String [] vw = new String[Index];
            String magazine = "";
            int in = 0;
            for (int i = 0; i<Index; i++){
                magazine = Array[i];
                char it = magazine.charAt(0);
                if (it == '2'){
                vw[in] = arr[i];
                in++;
                }
            }
        if (in == 0){
            System.out.println("No magazine found");
        }
        else {
    System.out.println("-------------------------------------------------------------------------");
        for (int l = 0; l < in; l++) {
            System.out.println(vw[l]);
        }
    System.out.println("-------------------------------------------------------------------------");}
        }
    }

    public static class Newspaper extends LibraryItem {
        private int itemType;
        private String title;
        private String publisherCompany;
        private int popularityCount; 
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = sdf.format(date);

        public Newspaper() {
            itemType = 3;
            title = "";
            publisherCompany = "";
        }
        public Newspaper(String tit, String pComp) {
            itemType = 3;
            title = tit;
            publisherCompany = pComp;
        }
        @Override
        public void AddItemCon() {
            System.out.print("Enter the title of the newspaper: ");
            title= sc.nextLine();
            System.out.print("Enter the publisher company name: ");
            publisherCompany = sc.nextLine();
            popularityCount = 0;
            String newspaper = itemType+", "+title+", "+publisherCompany+", "+popularityCount+", "+formattedDate;
            Array[Index] = newspaper;
            Index++;
            Borrower.BorrowArray[Borrower.idx] = "false"+", "+newspaper;
            Borrower.idx++;
            localLoader();
            System.out.println("A newspaper \""+title+" by "+publisherCompany+"("+formattedDate+")\" has been added to items!");
        }
        @Override
        public void ViewAllCon() {
            String [] vw = new String[Index];
            String newspaper = "";
            int in = 0;
            for (int i = 0; i<Index; i++){
                newspaper = Array[i];
                char it = newspaper.charAt(0);
                if (it == '3'){
                vw[in] = arr[i];
                in++;
                }
            }
        if (in == 0){
            System.out.println("No Newspaper found");
        }
        else {
    System.out.println("-------------------------------------------------------------------------");
        for (int l = 0; l < in; l++) {
            System.out.println(vw[l]);
        }
    System.out.println("-------------------------------------------------------------------------");}
        }
    }

    public static class Borrower {
        private String borrower = "";
        private String BorrowStatus = "false";
        private double Cost;
        public static int idx = LibraryItem.Index;
        public static String [] BorrowArray = new String[100];
        public static int Bidx = 0;
        public static String [] BorrowerArray = new String[100];
        Borrower(){
        for (int i = 0; i<idx; i++){
            BorrowArray[i] = BorrowStatus+", "+LibraryItem.Array[i];
        }
        }
        public void setPopularityCount(int id) {
            String str = LibraryItem.Array[id-1];
            char itemType = str.charAt(0);
            if (itemType == '1'){
                String[] words = str.split(",");
                String title = words[1];
                String author = words[2];
                words[3] = words[3].trim();
                int year = Integer.parseInt(words[3]);
                words[4] = words[4].trim();
                int popularityCount = Integer.parseInt(words[4]);
                words[5] = words[5].trim();
                int price = Integer.parseInt(words[5]);
                popularityCount++;
                String book = "1,"+title+","+author+", "+year+", "+popularityCount+", "+price;
                LibraryItem.Array[id-1] = book;
                BorrowArray[id-1] = book;
            }
            else if (itemType == '2'){
                char[] ch = str.toCharArray();
                    int a;
                    String title = "";
                for (a=2; ch[a]!=',';a++){
                    title = title+ch[a];
                }
                a++;
                    String author="";
                for (; ch[a]!='.';a++){
                    author = author+ch[a];
                }
                a++;
                a++;
                    String publisherCompany = "";
                for (; ch[a]!=',';a++){
                    publisherCompany = publisherCompany+ch[a];
                }
                a++;
                    int popularityCount = 0;
                    String temp = "";
                for (a++; ch[a]!=',';a++){
                    temp = temp+ch[a];
                }
                popularityCount = Integer.parseInt(temp);
                a++;
                    int price = 0;
                    temp = "";
                for (a++; a<str.length(); a++){
                    temp = temp+ch[a];
                }
                price = Integer.parseInt(temp);
                popularityCount++;
                String magazine = "2,"+title+","+author+".,"+publisherCompany+", "+popularityCount+", "+price;
                LibraryItem.Array[id-1] = magazine;
                BorrowArray[id-1] = magazine;
            }
            else if (itemType == '3'){
                String[] words = str.split(",");
                String title = words[1];
                String publisherCompany = words[2];
                words[3] = words[3].trim();
                int popularityCount = Integer.parseInt(words[3]);
                String date = words[4];
                popularityCount++;
                String newspaper = "3,"+title+","+publisherCompany+", "+popularityCount+","+date;
                LibraryItem.Array[id-1] = newspaper;
                BorrowArray[id-1] = newspaper;
            }
        }
        public void BorrowItem(String s, int id){
            borrower = s;
            if (id == 0 || id > idx)
            System.out.println("ID not found.");
            else{
            String [] words = BorrowArray[id-1].split(",");
            BorrowStatus = words[0];
            if (BorrowStatus.equals("false")) {
                setPopularityCount(id);
                BorrowStatus = "true";
                BorrowArray[id-1] = BorrowStatus+", "+LibraryItem.Array[id-1];
    System.out.print("\n-------------------------------------------------------------------------");
                System.out.println("\nBorrow details\nBorrowed by: "+borrower);
                CostOfBorrowingAnItem(LibraryItem.Array[id-1]);
                BorrowerArray[Bidx] = BorrowerArray[Bidx]+"\nBorrowed by: "+borrower;
                Bidx++;        
            }
            else {
                System.out.println("Cannot borrow this item.\nThis item has already been borrowed.");
            } 
        }
        }
        public void CostOfBorrowingAnItem(String str){
            char itemType = str.charAt(0);
            if (itemType == '1'){
                String[] words = str.split(",");
                String title = words[1];
                String author = words[2];
                words[3] = words[3].trim();
                int year = Integer.parseInt(words[3]);
                words[4] = words[4].trim();
                int popularityCount = Integer.parseInt(words[4]);
                words[5] = words[5].trim();
                int price = Integer.parseInt(words[5]);
                Cost = price + (0.2 * price) + 200;
                String s = "Book Title:"+title+"\nBook Author:"+author+"\nPublishing Year: "+year+"\nPopularity Count: "+popularityCount+"\nCost (incl. Tax): Rs."+Cost;
                BorrowerArray[Bidx] = s;
                System.out.println(s);
            }
            else if (itemType == '2'){
                char[] ch = str.toCharArray();
                    int a;
                    String title = "";
                for (a=2; ch[a]!=',';a++){
                    title = title+ch[a];
                }
                a++;
                    String author="";
                for (; ch[a]!='.';a++){
                    author = author+ch[a];
                }
                a++;
                a++;
                    String publisherCompany = "";
                for (; ch[a]!=',';a++){
                    publisherCompany = publisherCompany+ch[a];
                }
                a++;
                    int popularityCount = 0;
                    String temp = "";
                for (a++; ch[a]!=',';a++){
                    temp = temp+ch[a];
                }
                popularityCount = Integer.parseInt(temp);
                a++;
                    int price = 0;
                    temp = "";
                for (a++; a<str.length(); a++){
                    temp = temp+ch[a];
                }
                price = Integer.parseInt(temp);
                Cost = price * popularityCount;
                String s = "Magazine Title:"+title+"\nMagazine Author(s):"+author+"\nPublisher Company:"+publisherCompany+"\nPopularity Count: "+popularityCount+"\nCost: Rs."+Cost;
                BorrowerArray[Bidx] = s;
                System.out.println(s);
            }
            else if (itemType == '3'){
                String[] words = str.split(",");
                String title = words[1];
                String publisherCompany = words[2];
                words[3] = words[3].trim();
                int popularityCount = Integer.parseInt(words[3]);
                String date = words[4];
                Cost = 10;
                String s = "Newspaper Title:"+title+"\nPublisher Company:"+publisherCompany+"\nDate:"+date+"\nPopularity Count: "+popularityCount+"\nCost: Rs."+Cost+" (+Rs.5 Publisher charges)";
                BorrowerArray[Bidx] = s;
                System.out.println(s);
            }
    System.out.println("-------------------------------------------------------------------------");
        }
        public void ViewBorrowersList(){
            if (Bidx == 0)
            System.out.println("No current data.");
            else {
    System.out.println("-------------------------------------------------------------------------");
            for (int i = 0; i<Bidx; i++){
                System.out.println(BorrowerArray[i]+'\n');
            }
    System.out.println("-------------------------------------------------------------------------");}
        }
    }

public static void main(String[] args) {
    System.out.println("\n-------------------------------------------------------------------------");
    System.out.println("\t\t\tLIBRARY MANAGEMENT SYSTEM");
    System.out.println("-------------------------------------------------------------------------");
    Scanner sc = new Scanner(System.in);
        LibraryItem item = new LibraryItem();
        item.loader();

        Borrower borrow = new Borrower();

        int id = 0;
        char ch = 'y';
            while(ch == 'y' || ch == 'Y'){
            int op = 0;
            System.out.print("\n1. Hot Picks!\n2. Borrow an Item\n3. Add an Item\n4. Edit an Item\n5. Delete an Item\n6. View All Items\n7. View Item by ID\n8. View Borrowers List\n9. Exit\nEnter your choice: ");
            if (sc.hasNext()){
            op = sc.nextInt();
            sc.nextLine();
            System.out.println();
            switch(op){
                case 1:
                    item.HotPicks();
                break;
                case 2:
                    System.out.print("Enter your name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter id: ");
                    id = sc.nextInt();
                    borrow.BorrowItem(name, id);
                break;
                case 3:
                    char n1 = '0';
                    System.out.print("1. Add Book\n2. Add Magazine\n3. Add Newspaper\nEnter choice: ");
                    if (sc.hasNext()){
                    n1 = sc.next().charAt(0);
                    sc.nextLine();
                    System.out.println();
                    switch(n1){
                        case '1':
                    Book book = new Book();
                    item.AddItem(book);
                        break;
                        case '2':
                    Magazine magazine = new Magazine();
                    item.AddItem(magazine);
                        break;
                        case '3':
                    Newspaper newspaper = new Newspaper();
                    item.AddItem(newspaper);
                        break;
                        default:
                    System.out.println("Invalid input");
                        break;}
                    }
                break;
                case 4:
                    System.out.print("Enter id: ");
                    id = sc.nextInt();
                    item.EditItem(id);
                break;
                case 5:
                    System.out.print("Enter id: ");
                    id = sc.nextInt();
                    item.DeleteItem(id);
                break;
                case 6:
                    char n4 = '0';
                    System.out.print("1. Books\n2. Magazines\n3. Newspapers\n4. All items\nEnter choice: ");
                    if (sc.hasNext()){
                    n4 = sc.next().charAt(0);
                    sc.nextLine();
                    System.out.println();
                    switch(n4){
                        case '1':
                    Book book = new Book();
                    item.ViewAll(book);
                        break;
                        case '2':
                    Magazine magazine = new Magazine();
                    item.ViewAll(magazine);
                        break;
                        case '3':
                    Newspaper newspaper = new Newspaper();
                    item.ViewAll(newspaper);
                        break;
                        case '4':
                    item.ViewAllItems();
                        break;
                        default:
                    System.out.println("Invalid input");
                        break;}
                    }
                break;
                case 7:
                    System.out.print("Enter id: ");
                    id = sc.nextInt();
                    item.ViewItemById(id);
                break;
                case 8:
                borrow.ViewBorrowersList();
                break;
                case 9:
                    item.loadBack();
                    System.out.println("Program Exit succesfully!\n");
                    System.exit(0);
                break;
                default:
                    System.out.println("Invalid input");
                break; 
                }
            } else {
                System.out.println("No input available.");
                break;
            }
        System.out.print("\nContinue? y/n ");
        if (sc.hasNext()) {
            ch = sc.next().charAt(0);
            sc.nextLine();
        } else {
            System.out.println("No input available.");
            break;
        }
        if (ch == 'N' || ch == 'n'){
            item.loadBack();
            System.out.println("\nApplication terminated!\n");
        break;}
    }
    sc.close();
    }
}