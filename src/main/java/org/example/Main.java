package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public String main(String[] args) {
        if(isLarger(5, 3))
            return "yup, it's larger";
        return null;
    }

    public boolean isLarger(int x, int y) {
        return x > y;
    }
}

