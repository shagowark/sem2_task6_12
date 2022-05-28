import ru.vsu.cs.course1.hash.SimpleHashMap;
import ru.vsu.cs.course1.tree.bst.avl.AVLTreeMap;

import java.util.*;

/* классы, которые брал из примеров Дмитрия Ивановича, находятся в ru.vsu.cs*/
public class Logic {

    /* эти списки используются для хранения точек, в которых увеличивается время работы с Map,
     чтобы потом по ним потсроить график. В elems хранятся порядковые номера точек, в timeValues -
     значения времени в каждой точке.
     */
    static List<Integer> elemsCountHash = new ArrayList<>();
    static List<Integer> timeValuesHash = new ArrayList<>();
    static List<Integer> elemsCountTree = new ArrayList<>();
    static List<Integer> timeValuesTree = new ArrayList<>();

    public static void main(String[] args) {
        new Frame();
        clearStaticFields();
    }

    /**
     * Создает список случаных ключей
     * @param length длина ключей
     * @param size кол-во ключей
     * @return
     */
    public static List<String> generateRandomKeys(int length, int size){
        List<String> keys = new ArrayList<>();

        for (int i = 0; i < size; i++){
            keys.add(generateRandomStringWithFixedLength(length));
        }

        return keys;
    }

    /**
     * Создает случайную строку (ключ) фиксированной длины
     * @param targetStringLength длина строки
     * @return
     */
    public static String generateRandomStringWithFixedLength(int targetStringLength) {

        String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            buffer.append(letters.charAt(random.nextInt(letters.length())));
        }

        return buffer.toString();
    }

    /**
     * Возвращает время вставки (put) случайных элементов в HashMap. В процессе работы
     * заполняет статические поля-списки elemsCountHash и timeValuesHash точками, по которым потом
     * будет строится график
     * @param keys список ключей
     * @param values список значений
     * @return
     */

    /* Ниже идут похожие методы для проверки get, remove и put+remove для реализаций HashMap и TreeMap
    (Java реализация), а так же SimpleHashMap и AVLTreeMap (своя реализация классов,
    взята из проекта-примера Дмитрия Ивановича).
    Действия в тех методах происходят аналогичные checkHashMap, поэтому комментарии написал только тут,
    надеюсь, этого будет достаточно
     */
    public static long checkHashPut(List<String> keys, List<Integer> values){
        HashMap<String, Integer> testMap = new HashMap<>();

        long overallTime = 0; //время обработки всех ключей
        for (int i = 0; i < keys.size(); i++){
            Random random = new Random();
            String key = keys.get(random.nextInt(keys.size()));
            Integer value = values.get(i);
            /* в условии просят учитывать только время работы со словарем,
            поэтому в startTime получаю время перед операцией put, в endTime - после,
            нахожу разницу (т.е. время на один текущий шаг) и записываю в overallTime.
             */
            long startTime = System.currentTimeMillis();
            testMap.put(key, value);
            long endTime = System.currentTimeMillis();
            overallTime += endTime - startTime;
            if ((endTime - startTime) > 0){ /* если на шаг затрачено больше 0 мс, т.е. время изменилось,
                                              то записываю точку в timeValuesHash и elemsCountHash, чтобы
                                              потом использовать в построении графика*/
                timeValuesHash.add((int) overallTime);
                elemsCountHash.add(i);
            }
        }

        return overallTime;
    }
    public static long checkHashGet(HashMap<String, Integer> testMap, List<String> keys){
        long overallTime = 0;
        for (int i = 0; i < keys.size(); i++){
            Random random = new Random();
            String key = keys.get(random.nextInt(keys.size()));
            long startTime = System.currentTimeMillis();
            testMap.get(key);
            long endTime = System.currentTimeMillis();
            overallTime += endTime - startTime;
            if ((endTime - startTime) > 0){
                timeValuesHash.add((int) overallTime);
                elemsCountHash.add(i);
            }
        }

        return overallTime;
    }
   public static long checkHashRemove(HashMap<String, Integer> testMap, List<String> keys){
       long overallTime = 0;
       for (int i = 0; i < keys.size(); i++){
           Random random = new Random();
           String key = keys.get(random.nextInt(keys.size()));
           long startTime = System.currentTimeMillis();
           testMap.remove(key);
           long endTime = System.currentTimeMillis();
           overallTime += endTime - startTime;
           if ((endTime - startTime) > 0){
               timeValuesHash.add((int) overallTime);
               elemsCountHash.add(i);
           }
       }

       return overallTime;
   }
   public static long checkHashPutAndRemove(List<String> keys, List<Integer> values){
       HashMap<String, Integer> testMap = new HashMap<>();

       long overallTime = 0;
       for (int i = 0; i < keys.size(); i++){
           Random random = new Random();
           String key = keys.get(random.nextInt(keys.size()));
           Integer value = values.get(i);
           long startTime = System.currentTimeMillis();
           testMap.put(key, value);
           long endTime = System.currentTimeMillis();
           overallTime += endTime - startTime;
           if ((endTime - startTime) > 0){
               timeValuesHash.add((int) overallTime);
               elemsCountHash.add(i);
           }
       }

       for (int i = 0; i < keys.size(); i++){
           Random random = new Random();
           String key = keys.get(random.nextInt(keys.size()));
           long startTime = System.currentTimeMillis();
           testMap.remove(key);
           long endTime = System.currentTimeMillis();
           overallTime += endTime - startTime;
           if ((endTime - startTime) > 0){
               timeValuesHash.add((int) overallTime);
               elemsCountHash.add(i + keys.size());
           }
       }


       return overallTime;
   }

    public static long checkTreePut(List<String> keys, List<Integer> values){
        TreeMap<String, Integer> testMap = new TreeMap<>();
        long overallTime = 0;
        for (int i = 0; i < keys.size(); i++){
            Random random = new Random();
            String key = keys.get(random.nextInt(keys.size()));
            Integer value = values.get(i);
            long startTime = System.currentTimeMillis();
            testMap.put(key, value);
            long endTime = System.currentTimeMillis();
            overallTime += endTime - startTime;
            if ((endTime - startTime) > 0){
                timeValuesTree.add((int) overallTime);
                elemsCountTree.add(i);
            }
        }


        return overallTime;
    }
    public static long checkTreeGet(TreeMap<String, Integer> testMap, List<String> keys){
        long overallTime = 0;
        for (int i = 0; i < keys.size(); i++){
            Random random = new Random();
            String key = keys.get(random.nextInt(keys.size()));
            long startTime = System.currentTimeMillis();
            testMap.get(key);
            long endTime = System.currentTimeMillis();
            overallTime += endTime - startTime;
            if ((endTime - startTime) > 0){
                timeValuesTree.add((int) overallTime);
                elemsCountTree.add(i);
            }
        }

        return overallTime;
    }
    public static long checkTreeRemove(TreeMap<String, Integer> testMap, List<String> keys){
        long overallTime = 0;
        for (int i = 0; i < keys.size(); i++){
            Random random = new Random();
            String key = keys.get(random.nextInt(keys.size()));
            long startTime = System.currentTimeMillis();
            testMap.remove(key);
            long endTime = System.currentTimeMillis();
            overallTime += endTime - startTime;
            if ((endTime - startTime) > 0){
                timeValuesTree.add((int) overallTime);
                elemsCountTree.add(i);
            }
        }

        return overallTime;
    }
    public static long checkTreePutAndRemove(List<String> keys, List<Integer> values){
        TreeMap<String, Integer> testMap = new TreeMap<>();

        long overallTime = 0;
        for (int i = 0; i < keys.size(); i++){
            Random random = new Random();
            String key = keys.get(random.nextInt(keys.size()));
            Integer value = values.get(i);
            long startTime = System.currentTimeMillis();
            testMap.put(key, value);
            long endTime = System.currentTimeMillis();
            overallTime += endTime - startTime;
            if ((endTime - startTime) > 0){
                timeValuesTree.add((int) overallTime);
                elemsCountTree.add(i);
            }
        }

        for (int i = 0; i < keys.size(); i++){
            Random random = new Random();
            String key = keys.get(random.nextInt(keys.size()));
            long startTime = System.currentTimeMillis();
            testMap.remove(key);
            long endTime = System.currentTimeMillis();
            overallTime += endTime - startTime;
            if ((endTime - startTime) > 0){
                timeValuesTree.add((int) overallTime);
                elemsCountTree.add(i + keys.size());
            }
        }


        return overallTime;
    }

    public static long checkMyHashPut(List<String> keys, List<Integer> values){
        SimpleHashMap<String, Integer> testMap = new SimpleHashMap<>(keys.size());

        long overallTime = 0;
        for (int i = 0; i < keys.size(); i++){
            Random random = new Random();
            String key = keys.get(random.nextInt(keys.size()));
            Integer value = values.get(i);
            long startTime = System.currentTimeMillis();
            testMap.put(key, value);
            long endTime = System.currentTimeMillis();
            overallTime += endTime - startTime;
            if ((endTime - startTime) > 0){
                timeValuesHash.add((int) overallTime);
                elemsCountHash.add(i);
            }
        }

        return overallTime;
    }
    public static long checkMyHashGet(SimpleHashMap<String, Integer> testMap, List<String> keys){
        long overallTime = 0;
        for (int i = 0; i < keys.size(); i++){
            Random random = new Random();
            String key = keys.get(random.nextInt(keys.size()));
            long startTime = System.currentTimeMillis();
            testMap.get(key);
            long endTime = System.currentTimeMillis();
            overallTime += endTime - startTime;
            if ((endTime - startTime) > 0){
                timeValuesHash.add((int) overallTime);
                elemsCountHash.add(i);
            }
        }

        return overallTime;
    }
    public static long checkMyHashRemove(SimpleHashMap<String, Integer> testMap, List<String> keys){
        long overallTime = 0;
        for (int i = 0; i < keys.size(); i++){
            Random random = new Random();
            String key = keys.get(random.nextInt(keys.size()));
            long startTime = System.currentTimeMillis();
            testMap.remove(key);
            long endTime = System.currentTimeMillis();
            overallTime += endTime - startTime;
            if ((endTime - startTime) > 0){
                timeValuesHash.add((int) overallTime);
                elemsCountHash.add(i);
            }
        }

        return overallTime;
    }
    public static long checkMyHashPutAndRemove(List<String> keys, List<Integer> values){
        SimpleHashMap<String, Integer> testMap = new SimpleHashMap<>(keys.size());

        long overallTime = 0;
        for (int i = 0; i < keys.size(); i++){
            Random random = new Random();
            String key = keys.get(random.nextInt(keys.size()));
            Integer value = values.get(i);
            long startTime = System.currentTimeMillis();
            testMap.put(key, value);
            long endTime = System.currentTimeMillis();
            overallTime += endTime - startTime;
            if ((endTime - startTime) > 0){
                timeValuesHash.add((int) overallTime);
                elemsCountHash.add(i);
            }
        }

        for (int i = 0; i < keys.size(); i++){
            Random random = new Random();
            String key = keys.get(random.nextInt(keys.size()));
            long startTime = System.currentTimeMillis();
            testMap.remove(key);
            long endTime = System.currentTimeMillis();
            overallTime += endTime - startTime;
            if ((endTime - startTime) > 0){
                timeValuesHash.add((int) overallTime);
                elemsCountHash.add(i + keys.size());
            }
        }


        return overallTime;
    }

    public static long checkMyTreePut(List<String> keys, List<Integer> values){
        AVLTreeMap<String, Integer> testMap = new AVLTreeMap<>();
        long overallTime = 0;
        for (int i = 0; i < keys.size(); i++){
            Random random = new Random();
            String key = keys.get(random.nextInt(keys.size()));
            Integer value = values.get(i);
            long startTime = System.currentTimeMillis();
            testMap.put(key, value);
            long endTime = System.currentTimeMillis();
            overallTime += endTime - startTime;
            if ((endTime - startTime) > 0){
                timeValuesTree.add((int) overallTime);
                elemsCountTree.add(i);
            }
        }


        return overallTime;
    }
    public static long checkMyTreeGet(AVLTreeMap<String, Integer> testMap, List<String> keys){
        long overallTime = 0;
        for (int i = 0; i < keys.size(); i++){
            Random random = new Random();
            String key = keys.get(random.nextInt(keys.size()));
            long startTime = System.currentTimeMillis();
            testMap.get(key);
            long endTime = System.currentTimeMillis();
            overallTime += endTime - startTime;
            if ((endTime - startTime) > 0){
                timeValuesTree.add((int) overallTime);
                elemsCountTree.add(i);
            }
        }

        return overallTime;
    }
    public static long checkMyTreeRemove(AVLTreeMap<String, Integer> testMap, List<String> keys){
        long overallTime = 0;
        for (int i = 0; i < keys.size(); i++){
            Random random = new Random();
            String key = keys.get(random.nextInt(keys.size()));
            long startTime = System.currentTimeMillis();
            testMap.remove(key);
            long endTime = System.currentTimeMillis();
            overallTime += endTime - startTime;
            if ((endTime - startTime) > 0){
                timeValuesTree.add((int) overallTime);
                elemsCountTree.add(i);
            }
        }

        return overallTime;
    }
    public static long checkMyTreePutAndRemove(List<String> keys, List<Integer> values){
        AVLTreeMap<String, Integer> testMap = new AVLTreeMap<>();

        long overallTime = 0;
        for (int i = 0; i < keys.size(); i++){
            Random random = new Random();
            String key = keys.get(random.nextInt(keys.size()));
            Integer value = values.get(i);
            long startTime = System.currentTimeMillis();
            testMap.put(key, value);
            long endTime = System.currentTimeMillis();
            overallTime += endTime - startTime;
            if ((endTime - startTime) > 0){
                timeValuesTree.add((int) overallTime);
                elemsCountTree.add(i);
            }
        }

        for (int i = 0; i < keys.size(); i++){
            Random random = new Random();
            String key = keys.get(random.nextInt(keys.size()));
            long startTime = System.currentTimeMillis();
            testMap.remove(key);
            long endTime = System.currentTimeMillis();
            overallTime += endTime - startTime;
            if ((endTime - startTime) > 0){
                timeValuesTree.add((int) overallTime);
                elemsCountTree.add(i + keys.size());
            }
        }


        return overallTime;
    }

    /**
     * Используется для обнуления статических полей, вызывается для обеспечения
     * корректной работы программы
     */
    public static void clearStaticFields(){
        while(elemsCountHash.size()>0){
            elemsCountHash.remove(0);
        }
        while(elemsCountTree.size()>0){
            elemsCountTree.remove(0);
        }
        while(timeValuesHash.size()>0){
            timeValuesHash.remove(0);
        }
        while(timeValuesTree.size()>0){
            timeValuesTree.remove(0);
        }
    }
}
