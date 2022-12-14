package ru.job4j;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;


import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
        System.out.println(Arrays.toString(array));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(list).isNotEmpty()
                .contains("first")
                .hasSize(5)
                .containsAnyOf("1","2","four")
                .doesNotContain("1","2","3")
                .startsWith("first")
                .endsWith("five");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first", "second", "three", "four", "five","five","five");
        assertThat(set).isNotEmpty()
                .contains("five")
                .hasSize(5)
                .doesNotContain("six");
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String,Integer> map = simpleConvert.toMap("first", "second","three","four","five");
        assertThat(map).isNotEmpty()
                .hasSize(5)
                .containsKey("first")
                .containsValue(3)
                .doesNotContainValue(5)
                .containsEntry("first",0);
    }
}