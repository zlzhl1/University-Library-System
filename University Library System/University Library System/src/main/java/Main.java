import java.io.IOException;
import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;
import static java.lang.System.exit;

public class Main {
    //info
    private static void displatInfo() {
        System.out.println("------------------------------------");
        System.out.println("    Assignment 2 Semester 1 2022");
        System.out.println("Submitted by: Zhaohui Liang 21012755");
        System.out.println("------------------------------------");
    }

    //Calculate the due date based on real-time time
    static String duedate_item(String type) {
        Calendar cal = Calendar.getInstance();
        Date date = new Date();
        cal.setTime(date);
        if (Objects.equals(type, "Movie")) {
            cal.set(Calendar.DATE, cal.get(Calendar.DATE) + 7);
        } else if (Objects.equals(type, "Journal")) {
            cal.set(Calendar.DATE, cal.get(Calendar.DATE) + 14);
        } else if (Objects.equals(type, "Book")) {
            cal.set(Calendar.DATE, cal.get(Calendar.DATE) + 28);
        }
        String strDateFormat = "yyyy/MM/dd";                        //format of date
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
        return sdf.format(cal.getTime());

    }

    //search id among items
    static Item search_id(Item[] items,int id){
        for (Item i : items){
            if (i.get_id() == id){
                System.out.println("ID: " + id + "\t\t" + "Type: " + i.get_type() + "\t\t" + "Title: " + i.get_title());
                System.out.println();
                return i;
            }
        }
        return null;
    }

    //borrow and review method
    static void borrow_review_item(int i, Item[] it) {
        Scanner scanner = new Scanner(System.in);
        String el;
        while (Objects.equals(it[i].get_status(), "available")){
            System.out.println("Enter 'b' to borrow the item, enter 'a' to rate the item, or enter any other key to restart");
            el = scanner.nextLine();
            if (Objects.equals(el, "b")) {
                System.out.print("This item's due data is ");
                it[i].set_due_data(duedate_item(it[i].get_type()));
                System.out.println(duedate_item(it[i].get_type()));         //output date
                System.out.println();
                it[i].set_status("on loan");                        //exchange statu
                System.out.println("Selected item is:");
                it[i].display_deadline();
                if (return_item(i, it, scanner)) return;
            }
            // have review
            else if (Objects.equals(el, "a")){
                System.out.println("Please enter you rating (0-10)");
                double rate=scanner.nextInt();
                scanner.nextLine();
                it[i].set_numofreview(1);
                it[i].set_aver_rating(rate);

                System.out.println("This item's new average rating is "+it[i].get_aver_rating());
                System.out.println();
                System.out.println("Selected item is:");
                it[i].display_details();
            }
            else {
                System.out.println("Enter 'q' to quit,");
                System.out.println("or enter 's' to sort (first by average rating and then by id) and display all items,");
                System.out.println("or enter 'i' to search by ID,");
                System.out.println("or enter any other key to search by phrase in title");
                return;
            }
        }
        //go to return_item
        if (return_item(i, it, scanner))   return;
    }

    private static boolean return_item(int i, Item[] it, Scanner scanner) {
        String e;
        while(Objects.equals(it[i].get_status(), "on loan")){
            System.out.println("Enter 'r' to return the item, enter 'a' to rate the item, or enter any other key to restart");
            e = scanner.nextLine();
            if (Objects.equals(e, "r")) {
                System.out.println("This item is returned");
                it[i].set_status("available");
                System.out.println("Selected item is:");
                it[i].display_details();
            } else if (Objects.equals(e, "a")) {
                System.out.println("Please enter you rating (0-10)");
                double rate = scanner.nextInt();
                scanner.nextLine();
                it[i].set_numofreview(1);
                it[i].set_aver_rating(rate);
                System.out.println("This item's new average rating is " + it[i].get_aver_rating());
                System.out.println();
                System.out.println("Selected item is:");
                it[i].display_details();
            } else {
                System.out.println("Enter 'q' to quit,");
                System.out.println("or enter 's' to sort (first by average rating and then by id) and display all items,");
                System.out.println("or enter 'i' to search by ID,");
                System.out.println("or enter any other key to search by phrase in title");
                return true;
            }
        }
        return false;
    }

    //search phrase in item
    static Vector<Item> searchphrase(Item[] items,int index,String ele) {
        Vector<Item> p_item = new Vector<>();
        for (Item i: items) {
            if (i.get_title().toLowerCase().contains(ele.toLowerCase())) {
                index++;
                i.set_idx_item(index);
                p_item.add(i);
                System.out.println("* " + index + ":");
                System.out.println("ID: " + i.get_id() + "\t\t" + "Type: " + i.get_type() + "\t\t" + "Title: " + i.get_title());
                System.out.println();
            }
        }
        return p_item;
    }

    public static void sort(Item[] items) {
        Arrays.sort(items, (i1, i2) -> {
            if (i1.get_aver_rating() != 0 || i2.get_aver_rating() != 0) {
                return -Double.compare(i1.get_aver_rating(), i2.get_aver_rating()); // from big ti small
            } else
                return Integer.compare(i1.get_id(), i2.get_id());   // from small to big
        });
    }

    public static void main(String[] args) throws IOException {
        displatInfo();
        //read file, task A
        File file = new File("library.txt");
        BufferedReader in = new BufferedReader(new FileReader(file));
        in.mark((int) file.length() + 1);
        int sum_line = 0;
        while (in.readLine() != null) {
            sum_line++;
        }
        in.reset();
        String ele;
        int index = 0;
        Item[] items = new Item[sum_line];
        while ((ele = in.readLine()) != null) {
            String[] connect = ele.split(",");
            if (Objects.equals(connect[0], "Movie")) {
                items[index] = new movie(connect[0], Integer.parseInt(connect[1]), connect[2], Integer.parseInt(connect[3]), connect[4]);
                index++;
            } else if (Objects.equals(connect[0], "Book")) {
                items[index] = new book(connect[0], Integer.parseInt(connect[1]), connect[2], Integer.parseInt(connect[3]), connect[4], Integer.parseInt(connect[5]));
                index++;
            } else {
                items[index] = new journal(connect[0], Integer.parseInt(connect[1]), connect[2], Integer.parseInt(connect[3]), Integer.parseInt(connect[4]), Integer.parseInt(connect[5]));
                index++;
            }
        }
        // task B
        System.out.println("List of all items in the library:");

        for (Item st : items)
            System.out.println("ID: " + st.get_id() + "\t\t" + st.get_type() + "\t\t" + "Title: " + st.get_title());
        System.out.println();
        // task C
        System.out.println("Enter 'q' to quit,");
        System.out.println("or enter 's' to sort (first by average rating and then by id) and display all items,");
        System.out.println("or enter 'i' to search by ID,");
        System.out.println("or enter any other key to search by phrase in title");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String judge = scanner.nextLine();
            if (Objects.equals(judge, "i")){
                System.out.println("Enter ID to start search, or enter 'b' to go back to choose search method");
                judge = scanner.nextLine();
                if (Objects.equals(judge, "b")){
                    System.out.println("Enter 'q' to quit,");
                    System.out.println("or enter 's' to sort (first by average rating and then by id) and display all items,");
                    System.out.println("or enter 'i' to search by ID,");
                    System.out.println("or enter any other key to search by phrase in title");
                }else {
                    Item temp = new Item();
                    temp = search_id(items, Integer.parseInt(judge));
                    if (temp == null){
                        System.err.println("Does not find the book with this ID");
                        System.out.println("Enter 'i' to search other item by ID");
                    }else {
                        System.out.println("Enter 'i' to search other item by ID, or enter any other key to select this item");
                        int ide = 0;
                        for (Item i : items){
                            if (i == temp){
                                break;
                            }else{
                                ide ++;
                            }
                        }
                        judge = scanner.nextLine();
                        if (Objects.equals(judge, "i")){
                            continue;
                        }else {
                            System.out.println("Selected item is:");
                            items[ide].display_details();
                            borrow_review_item(ide, items);
                        }
                    }
                }
            }
            else if (Objects.equals(judge, "s")){
                System.out.println("List of all items in the search by phrase in title");
                sort(items);
                for (Item i: items){
                    System.out.println("Average rating: "+i.get_aver_rating()+"\t\t"+"Number of reviewers: "+i.get_numofreview()+"\t\t"+"ID: "+i.get_id()+"\t\t"+"Type: "+i.get_id()+"\t\t"+"Title: "+i.get_title());
                }
                System.out.println("Enter 'q' to quit,");
                System.out.println("or enter 's' to sort (first by average rating and then by id) and display all items,");
                System.out.println("or enter 'i' to search by ID,");
                System.out.println("or enter any other key to search by phrase in title");
            }else if (Objects.equals(judge, "q")){
                exit(0);
            }else {
                System.out.println("Enter phrase in start search, or enter 'b' to go back to choose search method");
                judge = scanner.nextLine();
                Vector<Item> ptr_item = new Vector<Item>();
                ptr_item.add(items[0]);
                ptr_item = searchphrase(items, 0, judge);
                if(ptr_item.isEmpty()){
                    System.err.println("Not find");
                    System.out.println("enter any other key to search by phrase in title again");
                }else {
                    System.out.println("Enter item number to select item, or enter any other key to continue searching");
                    int x = scanner.nextInt();
                    scanner.nextLine();
                    int num = -1;
                    for(Item  k : items ){
                        num++;
                        if(k.get_idx_item() == x){
                            k.display_details();
                            break;
                        }
                        k.set_idx_item(0);
                    }
                    borrow_review_item(num, items);
                }
            }
        }
    }
}
