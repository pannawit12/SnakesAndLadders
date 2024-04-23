package org.example;

public class Blank implements Square { //todo: เปลี่ยนชื่อ Blank อาจจะตัดออก
    @Override
    public int getNumber() {
        return 0;
    }

    @Override
    public String getName(){
        return "";
    }
}
