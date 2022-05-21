package ru.vsu.cs.course1.hash.demo;

import ru.vsu.cs.course1.hash.SimpleHashMap;

import java.util.HashMap;


public class Program {

    /**
     * Основная функция программы
     *
     * @param args Параметры командной строки
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        MapTest.correctTest(new SimpleHashMap<Integer, Integer>(1000), false);

        class Test {
            int a, b;
            public Test(int a, int b) {
                this.a = a;
                this.b = b;
            }

            @Override
            public int hashCode() {
                return a + b;
            }

            @Override
            public boolean equals(Object other) {
                if (!(other instanceof Test)) {
                    return false;
                }
                Test testOther = (Test) other;
                return this.a == testOther.a && this.b == testOther.b;
            }
        }

        Test t1 = new Test(3, 5);
        Test t2 = new Test(3, 5);
        System.out.println(t1.hashCode());
        System.out.println(t2.hashCode());
        HashMap<Test, Integer> hm = new HashMap<>();
        hm.put(t1, 3);
        System.out.println("t2 -> " + hm.get(t2));

        String s1 = "abc";
        String s2 = "ab";
        s2 += "c";
        System.out.println(s1.hashCode());
        System.out.println(s2.hashCode());
        System.out.println("ivanov".hashCode());
    }
}
