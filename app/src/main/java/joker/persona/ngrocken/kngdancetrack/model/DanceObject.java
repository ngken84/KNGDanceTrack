package joker.persona.ngrocken.kngdancetrack.model;

import java.util.Calendar;
import java.util.Date;

public abstract class DanceObject {

    private long id;
    private String name;
    private boolean starred;
    private Date dateCreated;

    public DanceObject(long id, String name, Date dateCreated) {
        this.id = id;
        this.name = name;
        this.dateCreated = dateCreated;
    }

    public DanceObject(long id, String name, int dateCreated) {
        this.id = id;
        this.name = name;
        setIntDateCreated(dateCreated);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStarred() {
        return starred;
    }

    public void setStarred(boolean starred) {
        this.starred = starred;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public int getIntDateCreated() {
        if(dateCreated != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(dateCreated);
            int retVal = cal.get(Calendar.YEAR) * 10000;
            retVal += cal.get(Calendar.MONTH) * 100;
            retVal += cal.get(Calendar.DAY_OF_MONTH);
            return retVal;
        }
        return 0;
    }

    public void setIntDateCreated(int date) {
        if(date > 2000*10000) {
            int year = date/10000;
            int month = (date - year)/ 100;
            int day_of_month = (date%100);
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, year);
            cal.set(Calendar.MONTH, month);
            cal.set(Calendar.DAY_OF_MONTH, day_of_month);
            dateCreated =  new Date(cal.getTimeInMillis());
        }
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
