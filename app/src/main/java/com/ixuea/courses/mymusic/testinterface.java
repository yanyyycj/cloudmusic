package com.ixuea.courses.mymusic;

public interface testinterface {
    public void test1();

    public default void test2() {
        System.out.println("test2");
    }
}
