public class Item {                         // superclass
    protected String type;
    protected int b_time;
    protected int id;
    protected String title;
    protected int year;
    protected String status = "available";
    protected double aver_rating = 0.0;
    protected int numofreview = 0;
    protected String due_data;
    protected int idx_item = 0;

    //constructor
    public Item (){}
    public Item(String type, int id, String title, int year){
        this.type = type;
        this.id = id;
        this.title = title;
        this.year = year;
    }

    public void display_details(){
        System.out.println("Type: " + type);
        System.out.println("Title: " + title);
        System.out.println("ID: " + id);
        System.out.println("Year: " + year);
        System.out.println("Average rating: " + aver_rating);
        System.out.println("Number of reviewers: " + numofreview);
        System.out.println("Status: " + status);
    }

    public void display_deadline(){
        display_details();
        System.out.println("Due date: " + due_data);
    }

    //getters and setters
    public String get_type(){ return type; }
    public void set_type(String type){ this.type = type; }

    public int get_b_time(){ return b_time; }
    public void set_b_time(int b_time){ this.b_time = b_time; }

    public int get_id(){ return id; }
    public void set_id(int id){ this.id = id; }

    public String get_title(){ return title; }
    public void set_title(String title){ this.title = title; }

    public int get_year(){ return year; }
    public void set_year(int year){ this.year = year; }

    public String get_status(){ return status; }
    public void set_status(String status){ this.status = status; }

    public double get_aver_rating(){ return aver_rating; }
    public void set_aver_rating(double aver_rating){ this.aver_rating = (this.aver_rating + aver_rating) / numofreview; }

    public int get_numofreview(){ return numofreview; }
    public void set_numofreview(int numofreview){ this.numofreview += numofreview; }

    public String get_due_data(){ return due_data; }
    public void set_due_data(String due_data){ this.due_data = due_data; }

    public int get_idx_item(){ return idx_item; }
    public void set_idx_item(int idx_item){ this.idx_item = idx_item; }
}
