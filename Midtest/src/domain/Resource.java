package domain;

import java.time.LocalDateTime;

public abstract class Resource {
    public Resource(String id, LocalDateTime expiration) {
        this.id = id;
        this.expiration = expiration;
    }

    protected String id;
    protected LocalDateTime expiration;

    public abstract double getUtilizationScore();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getExpdate() {
        return expiration;
    }

    public void setExpdate(LocalDateTime expdate) {
        this.expiration = expdate;
    }
}
