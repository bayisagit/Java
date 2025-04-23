class Car{
    String make;
    String model;
    int year;
    public Car(String make,String model,int year){
        this.make=make;
        this.model=model;
        this.year=year;
    }
    public void start(){
        System.out.println(make + " "+ model + " is starting");
    }
    public void stop(){
        System.out.println(make + " " + model + " is stopped!");
    }
}
class ElectricCar extends Car{
    int battery_size;
    public ElectricCar(String make,String model,int year,int battery_size){
        super(make, model, year);
        this.battery_size=battery_size;
    }
    public void charge(){
        System.out.println(make + " " + model +" is charging");
    }
}
class SportsCar extends Car{
    int topspeed;
    public SportsCar(String make,String model,int year,int topspeed){
        super(make, model, year);
        this.topspeed=topspeed;
    }
    public void accelarate(){
        System.out.println(make + " " + model + " is accelarating "+topspeed+" mph!");
    }

}
class Suv extends Car{
    boolean fourwhealdrive;
    public Suv(String make,String model,int year,boolean fourwhealdrive){
        super(make, model, year);
        this.fourwhealdrive=fourwhealdrive;
    }
    public void offrode(){
        if(fourwhealdrive){
            System.out.println(make + " " + model + " is going off road!");
        }
        else{
            System.out.println(make + " " + model + " is not going off road!");
        }
    }

}
public class Forinheritance {
    public static void main(String arg[]){
        ElectricCar elctricCar=new ElectricCar("Tesla", "model S", 2020, 75);
        elctricCar.start();
        elctricCar.charge();
        elctricCar.stop();
        elctricCar.charge();
        SportsCar sprCar = new SportsCar("Ferrari", "488", 2019, 211);
        sprCar.accelarate();
        Suv mys=new Suv("Jeep", "wrangler", 2021, true);
        mys.start();
        mys.offrode();
         
    }
}
