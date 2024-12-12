package domain;

import java.time.LocalDateTime;

public class Storage extends Resource{
    @Override
    public String toString() {
        return "Storage{" +
                "capacity=" + capacity +
                ", type='" + type + '\'' +
                ", id='" + id + '\'' +
                ", expiration=" + expiration + ", score: " + getUtilizationScore() +
                '}';
    }

    private int capacity;
    private String type;

    public Storage(String id, LocalDateTime expdate, int capacity, String type) {
        super(id, expdate);
        this.capacity = capacity;
        this.type = type;
    }

    @Override
    public double getUtilizationScore() {
        if (type.equals("HDD")) return 70.0;
        if (type.equals("SSD")) return 90.0;
        if (type.equals("Hybrid")) return 80.0;
        return 0;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
