package net.twonky.functionaljava;

import fj.Ord;
import fj.data.List;
import fj.data.TreeMap;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static fj.data.List.list;
import static java.util.stream.Collectors.groupingBy;
import static org.junit.Assert.assertEquals;

public class ListTest {

    private List<Letter> fjLetters;
    private java.util.List<Letter> javaLetters;

    @Before
    public void setUp() {
        Letter a = new Letter("a");
        Letter b = new Letter("b");
        Letter C = new Letter("C");
        Letter d = new Letter("d");
        Letter E = new Letter("E");
        Letter f = new Letter("f");
        fjLetters = list(a, b, C, d, E, f);
        javaLetters = fjLetters.toJavaList();
    }

    @Test
    public void ListGroupBy() {
        TreeMap<Letter.Phone, List<Letter>> map = fjLetters.groupBy(Letter::getPhone, Ord.comparableOrd());

        assertEquals(2, map.get(Letter.Phone.VOWEL).some().length());
        assertEquals(4, map.get(Letter.Phone.CONSONANT).some().length());
    }

    @Test
    public void collectorsGroupingBy() {
        Map<Letter.Phone, java.util.List<Letter>> map = javaLetters.stream().collect(groupingBy(Letter::getPhone));

        assertEquals(2, map.get(Letter.Phone.VOWEL).size());
        assertEquals(4, map.get(Letter.Phone.CONSONANT).size());
    }

    static class Letter implements Comparable<Letter> {
        final private String letter;
        final private Phone phone;

        @Override
        public int compareTo(Letter letter) {
            return this.letter.compareTo(letter.getLetter());
        }

        private enum Phone {
            VOWEL, CONSONANT
        }

        Letter(String letter) {
            this.letter = letter;
            this.phone = letter.matches("[AaEeIiOoUuYy]") ? Phone.VOWEL : Phone.CONSONANT;
        }

        String getLetter() {
            return letter;
        }

        Phone getPhone() {
            return phone;
        }
    }
}
