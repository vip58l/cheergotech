package com.cheergotech;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void abc() {
        List<String> list = new ArrayList<>();
        list.add("https://lanhuapp.com/");
        list.add("https://lanhuapp.com/");
        list.add("https://lanhuapp.com/");

        String[] arr = list.toArray(new String[list.size()]);
        System.out.println(arr);




    }
}