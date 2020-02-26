package com.example.demo.annotation.test1;


/**
 * 获取类与方法上的注解值
 */
@MyAnnotation1(name = "abc")
public class Demo1 {

    @MyAnnotation1(name = "xyz")
    private Integer age;

    @MyAnnotation2(model = TranscationModel.Read)
    public void list() {
        System.out.println("list");
    }

    @MyAnnotation3(models = {TranscationModel.Read, TranscationModel.Write})
    public void edit() {
        System.out.println("edit");
    }
}


