package lw01.unguided;

public abstract class Rental implements Chargeable{
    private String id;
    private int days;

    protected Rental(String id, int days){
        if (days <= 0){
            throw new IllegalArgumentException("Days harus leboh dari 0");
        }
        this.id = id;
        this.days = days;
    }

    public String getId(){
        return id;
    }
    public int getDays(){
        return days;
    }

    @Override
    public abstract int calculateCharge();

    public int calculateCharge(int units){
        if (units <= 0){
            throw new IllegalArgumentException("Units harus lebih dari 0");
        }
        return units * calculateCharge();
    }

    public abstract String label();

    public String summary(){
        return id + " | " + label() + " | " + calculateCharge();
    }

    public String summary(int units){
        return id + " | " + label() + " | " + calculateCharge(units);
    }
}