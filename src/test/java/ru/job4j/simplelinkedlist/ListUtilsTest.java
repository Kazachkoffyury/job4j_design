package ru.job4j.simplelinkedlist;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

class ListUtilsTest {

    private List<Integer> input;
    private List<Integer> list;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
        list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenRemoveIf() {
        ListUtils.removeIf(list, e -> e < 4);
        assertThat(list).hasSize(3).containsSequence(4, 5, 6);
    }

    @Test
    void whenReplaceIf() {
        ListUtils.replaceIf(list, e -> e > 3, 0);
        assertThat(list).hasSize(6).containsSequence(1, 2, 3);
    }

    @Test
    void whenRemoveAll() {
        List<Integer> list2 = new ArrayList<>(Arrays.asList(1, 4));
        ListUtils.removeAll(list, list2);
        assertThat(list).hasSize(4).containsSequence(2, 3, 5, 6);
    }
}