package rmi;

import java.io.Serializable;

public class Student implements Serializable {

    private static final long serialVersionUID = 771652260758459933L;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
