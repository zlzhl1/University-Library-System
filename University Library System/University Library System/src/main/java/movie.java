public class movie extends Item {                           //derived classes
    private String director;
    private final int b_time=7;

    public movie(String type, int id, String title, int year, String director){
        super(type, id, title, year);
        this.director = director;
    }

    public void display(){
        System.out.println("Director: " + director);
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
