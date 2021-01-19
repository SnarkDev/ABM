package ch.trumpf.abm;

import java.util.Date;

public class Contact {
    private int m_id;
    private String m_Name;
    private String m_PhoneNumber;
    private Date m_Birthdate;

    public Contact(int id, String name, String phoneNumber, Date birthdate)
    {
        m_id = id;
        m_Name = name;
        m_PhoneNumber = phoneNumber;
        m_Birthdate = birthdate;
    }

    public String getName() {
        return m_Name;
    }

    public void setName(String Name) {
        this.m_Name = Name;
    }

    public String getPhoneNumber() {
        return m_PhoneNumber;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.m_PhoneNumber = PhoneNumber;
    }

    public Date getBirthdate() {
        return m_Birthdate;
    }

    public void setBirthdate(Date Birthdate) {
        this.m_Birthdate = Birthdate;
    }

    public int getId() {
        return m_id;
    }

    public void setId(int Id) {
        this.m_id = Id;
    }
}
