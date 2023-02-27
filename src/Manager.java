import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Manager extends Personal {
    public Manager(int id, String name, String password, String login) {
        super(id, "Manager", name, password, login);
    }


    public <T> void see(ArrayList<T> data){
        for (T element: data) {
            System.out.println(element.toString());
        }
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    @Override
    public String getRole() {
        return super.getRole();
    }

    @Override
    public void setRole(String role) {
        super.setRole(role);
    }

    @Override
    public Logger getLog() {
        return super.getLog();
    }

    @Override
    public void setLog(Logger log) {
        super.setLog(log);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
