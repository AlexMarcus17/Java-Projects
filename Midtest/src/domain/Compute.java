package domain;

import java.time.LocalDateTime;

public class Compute extends Resource {
    @Override
    public String toString() {
        return "Compute{" +
                "cpuCores=" + cpuCores +
                ", id='" + id + '\'' +
                ", expiration=" + expiration + ", score: " + getUtilizationScore() +
                '}';
    }

    private int cpuCores;

    public Compute(String id, LocalDateTime expiration, int cpuCores) {
        super(id, expiration);
        this.cpuCores = cpuCores;
    }

    public int getCpuCores() {
        return cpuCores;
    }

    public void setCpuCores(int cpuCores) {
        this.cpuCores = cpuCores;
    }

    @Override
    public double getUtilizationScore() {
        if (cpuCores<8) return 50.0;
        else return 100.0;
    }
}
