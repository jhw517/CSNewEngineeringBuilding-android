package com.csnewengineeringbuilding.singleton;

/**
 * Created by dn2757 on 2016. 11. 2..
 */

public class User {
    private volatile static User single;
    private String name;
    private String studentNumber;

    private User() {
    }

    public static User getInstance() {
        if (single == null) {
            synchronized (User.class) {
                if (single == null) {
                    single = new User();
                }
            }
        }
        return single;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }
}
