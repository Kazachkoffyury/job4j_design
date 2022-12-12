package ru.job4j;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 5);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Tetrahedron");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 5);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube");
    }

    @Test
    void isThisUnknownObject() {
        Box box = new Box(10, 5);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object");
    }

    @Test
    void getNumberVerticesUnknownObjectTest() {
        Box box = new Box(3, 5);
        int number = box.getNumberOfVertices();
        assertThat(number).isEqualTo(-1);
    }

    @Test
    void getNumberVerticesUnknownTest() {
        Box box = new Box(4, 5);
        int number = box.getNumberOfVertices();
        assertThat(number).isEqualTo(4);
    }

    @Test
    void isExistTest() {
        Box box = new Box(4, 5);
        boolean isExist = box.isExist();
        assertThat(isExist).isTrue();
    }

    @Test
    void getAreaTest() {
       Box box = new Box(8, 2);
       double area = box.getArea();
       assertThat(area).isEqualTo(24);
    }




}