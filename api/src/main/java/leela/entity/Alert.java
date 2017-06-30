package leela.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Alert {

    @Id
    private String id;

    private String vin;

    private String reading_id;

    private String rule;
    private String priority;

    public Alert() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }


    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReading_id() {
        return reading_id;
    }

    public void setReading_id(String reading_id) {
        this.reading_id = reading_id;
    }
}
