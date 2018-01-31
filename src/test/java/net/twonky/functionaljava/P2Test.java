package net.twonky.functionaljava;

import fj.P2;
import fj.data.List;
import org.junit.Test;

import static fj.data.List.list;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class P2Test {

    @Test
    public void partitionListIntoP2() {
        P2<List<String>, List<String>> split = list("a", "b", "c", "D", "e").partition(e -> e.equals(e.toLowerCase()));
        assertEquals(4,split._1().length());
        assertEquals(1,split._2().length());
        assertFalse(split._2().isEmpty());
        assertEquals("a", split._1().head());
        assertEquals("D", split._2().head());
    }
}
