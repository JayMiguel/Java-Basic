package com.miguel.annotation.basic;

import com.miguel.annotation.basic.annotation.MyAnnotation;

public class Test {

    @MyAnnotation(value = "aaa", param = {"aa","bb","cc"})
    public void test() {

    }

}
