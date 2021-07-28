package com.lautung.javastreamapi;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class MyClass {
    public static List<Person> persons = Arrays.asList(
            new Person("CJK", 19, "女"),
            new Person("BODUO", 20, "女"),
            new Person("JZ", 21, "女"),
            new Person("anglebabby", 18, "女"),
            new Person("huangxiaoming", 5, "男"),
            new Person("ROY", 18, "男")
    );


    @Test
    public void test1() {
        Stream<Person> personStream = persons.stream();
        personStream.filter(x -> x.getAge() >= 18)
                .map(Person::getName)
                .sorted()
                .forEach(System.out::println);
    }


    @Test
    public void test2() {
        //1. Collection 提供了两个方法  stream() 与 parallelStream()
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream(); //获取一个顺序流
        Stream<String> parallelStream = list.parallelStream(); //获取一个并行流

        //2. 通过 Arrays 中的 stream() 获取一个数组流
        Integer[] nums = new Integer[10];
        Stream<Integer> stream1 = Arrays.stream(nums);
        stream1.forEach(System.out::println);

        //3. 通过 Stream 类中静态方法 of()
        Stream<Integer> stream2 = Stream.of(1, 2, 3, 4, 5, 6);
        stream2.forEach(System.out::println);

        //4. 创建无限流
        //迭代
        Stream<Integer> stream3 = Stream.iterate(0, (x) -> x + 2).limit(10);
        stream3.forEach(System.out::println);

        //生成
        Stream<Double> stream4 = Stream.generate(Math::random).limit(2);
        stream4.forEach(System.out::println);
    }


    //取出中间的第三到第五个元素
    @Test
    public void test3() {
        Integer[] ary = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        List<Integer> collect = Arrays.stream(ary).skip(2).limit(3).collect(Collectors.toList());
        collect.forEach(System.out::println);
    }


    //取出里面的偶数，并去除重复
    @Test
    public void test4() {
        Integer[] ary = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println("方法1");
        List<Integer> list = Arrays.stream(ary).filter(x -> x % 2 == 0).distinct().collect(Collectors.toList());
        list.forEach(System.out::println);
        System.out.println("-------------");
        System.out.println("方法2");
        Set<Integer> integerSet = Arrays.stream(ary).filter(x -> x % 2 == 0).collect(Collectors.toSet());
        integerSet.forEach(System.out::println);
    }

    //有个二维数组，要求把数组组合成一个一维数组，并排序（1，2，3，4，5……12）
    @Test
    public void test5() {
        Integer[][] ary = {{3, 8, 4, 7, 5}, {9, 1, 6, 2}, {0, 10, 12, 11}};
        Arrays.stream(ary)
                .flatMap(Arrays::stream)
                .sorted()
                .forEach(i -> System.out.print(i + " "));
    }


}