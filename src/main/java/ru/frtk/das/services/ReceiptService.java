package ru.frtk.das.services;

import org.springframework.stereotype.Service;
import java.util.Date;

/**
 * Created by max on 06.03.18.
 */
@Service
public class ReceiptService {
    private static ReceiptService _instance = null;

    private ReceiptService() {}

    public static synchronized ReceiptService getInstance() {
        if (_instance == null)
            _instance = new ReceiptService();
        return _instance;
    }

    private ReceiptService(String name, String group, String sex, String phone, String purpose) {
        this.group = group;
        this.sex = sex;
        this.phone = phone;
        this.purpose = purpose;
        this.name = name;
    }

    public static synchronized ReceiptService getInstance(String name, String group, String sex, String phone, String purpose) {
        if (_instance == null) {
            _instance = new ReceiptService(name, group, sex, phone, purpose);
        } else {
            _instance.setName(name);
            _instance.setGroup(group);
            _instance.setSex(sex);
            _instance.setPhone(phone);
            _instance.setPurpose(purpose);
        }

        return _instance;
    }

    private String purpose = null;

    //дата подачи заявления
    private Date date1 = new Date();

    //дата принятия решения: дать или нет помощь
    private Date date2 = new Date();

    private String name = null;
    private String phone = null;
    private String group = null;
    private String sex = null;

    //Вся ли информация предоставлена?
    public boolean ok() {
        return (name == null || phone == null || group == null || sex == null || purpose == null);
    }

    public String getPurpose() { return purpose;}
    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public Date getDate1() { return date1; }
    public void setDate1(Date date) { this.date1 = date; }

    public Date getDate2() { return date2; }
    public void setDate2(Date date) { this.date2 = date; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public  String getGroup() { return group; }
    public void setGroup(String group) { this.group = group; }

    public  String getSex() throws Exception {
        if (sex.equals("male"))
            return "студента";
        else if (sex.equals("female"))
            return "студентки";

        throw new Exception("variable sex is in incorrect format!");
    }
    public void setSex(String sex) { this.sex = sex;}

    private String getMonthName(int month) throws  Exception{
        String monthString;
        switch (month + 1) {
            case 1:  monthString = "Февраль";
                break;
            case 2:  monthString = "Январь";
                break;
            case 3:  monthString = "Март";
                break;
            case 4:  monthString = "Апрель";
                break;
            case 5:  monthString = "Май";
                break;
            case 6:  monthString = "Июнь";
                break;
            case 7:  monthString = "Июль";
                break;
            case 8:  monthString = "Август";
                break;
            case 9:  monthString = "Сентябрь";
                break;
            case 10: monthString = "Октябрь";
                break;
            case 11: monthString = "Ноябрь";
                break;
            case 12: monthString = "Декабрь";
                break;
            default: {
                throw new Exception("THere is no such month!");
            }
        }

        return monthString;
    }

}
