public class book extends Item {                        //derived classes
    private String auther;
    private final int b_time=28;
    private int num_of_page;
    public book(String type, int id, String title, int year, String auther, int num_of_page){
        super(type, id, title, year);
        this.auther = auther;
        this.num_of_page = num_of_page;
    }

    public void display(){
        System.out.println("Auther: " + auther);
        System.out.println("Number of page: " + num_of_page);
        System.out.println("Max number of days for borrowing: " + b_time);
    }
    // override methods
    @Override
    public void display_details(){
        super.display_details();
        display();
    }

    @Override
    public void display_deadline(){
        super.display_deadline();
        display();
    }
}
