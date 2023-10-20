public class journal extends Item {                         //derived classes
    private final int b_time=14;
    private int volume;
    private int number;

    public journal(String type, int id, String title, int year, int volume, int number){
        super(type, id, title, year);
        this.volume = volume;
        this.number = number;
    }

    public void display(){
        System.out.println("Volume: " + volume);
        System.out.println("Number: " + number);
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
