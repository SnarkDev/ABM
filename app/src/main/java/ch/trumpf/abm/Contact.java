package ch.trumpf.abm;

import java.util.Date;

public class Contact {
    private int m_id;
    private String m_Name;
    private String m_PhoneNumber;
    private Date m_Birtdate;

    public Contact(int id, String name, String phoneNumber, Date birthdate)
    {
        m_id = id;
        m_Name = name;
        m_PhoneNumber = phoneNumber;
        m_Birtdate = birthdate;
    }

    public String getM_Name() {
        return m_Name;
    }

    public void setM_Name(String m_Name) {
        this.m_Name = m_Name;
    }

    public String getM_PhoneNumber() {
        return m_PhoneNumber;
    }

    public void setM_PhoneNumber(String m_PhoneNumber) {
        this.m_PhoneNumber = m_PhoneNumber;
    }

    public Date getM_Birtdate() {
        return m_Birtdate;
    }

    public void setM_Birtdate(Date m_Birtdate) {
        this.m_Birtdate = m_Birtdate;
    }

    public int getM_id() {
        return m_id;
    }

    public void setM_id(int m_id) {
        this.m_id = m_id;
    }
}
