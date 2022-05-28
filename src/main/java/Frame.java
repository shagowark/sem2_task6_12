import ru.vsu.cs.course1.hash.SimpleHashMap;
import ru.vsu.cs.course1.tree.bst.avl.AVLTreeMap;

import javax.swing.*;
import java.util.*;
import java.util.List;

public class Frame extends JFrame {
    private JButton buttonJavaCheckPut;
    private JButton buttonJavaCheckGet;
    private JButton buttonJavaCheckPutAndRemove;
    private JButton buttonMyCheckPut;
    private JButton buttonMyCheckGet;
    private JButton buttonMyCheckRemove;
    private JButton buttonMyCheckPutAndRemove;
    private JButton buttonJavaCheckRemove;
    private JPanel panelMain;
    private JTextField textFieldSize;
    private JTextField textFieldLength;
    private JTextField textFieldResult;
    private JButton buttonGraphs;

    public Frame() {
        this.setTitle("Task6(12)");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panelMain);
        this.pack();


        this.setVisible(true);
        this.setSize(510, 350);

        /**
         * Отрисовка графиков
         **/
        buttonGraphs.addActionListener(e -> {
            LineChart.main();
        });

        /**
         * Кнопка сранения времени работы HashMap и TreeMap (Java-реализация)
         * для вставки случайных элементов
         */
        // Остальные кнопки работают аналогично.
        buttonJavaCheckPut.addActionListener(e -> {
            try {
                int length = Integer.parseInt(textFieldLength.getText()); // длина ключей
                int size = Integer.parseInt(textFieldSize.getText()); // кол-во ключей, элементов
                List<String> keys = Logic.generateRandomKeys(length, size);
                List<Integer> values = new ArrayList<>();
                // генерирует случайные значения элементов
                Random random = new Random();
                for (int i = 0; i < keys.size(); i++) {
                    values.add(random.nextInt());
                }

                Logic.clearStaticFields(); /* очищаю static поля класса Logic (т.е. списки точек для
                                              графика), чтобы новые графики отрисовались корректно*/
                long hashTime = Logic.checkHashPut(keys, values); // время работы c HashMap
                long treeTime = Logic.checkTreePut(keys, values); // время работы с TreeMap
                long diff = hashTime - treeTime;

                if (diff > 0) {
                    textFieldResult.setText("HashMap работает дольше на " + diff + " миллисекунд");
                } else if (diff == 0) {
                    textFieldResult.setText("Одинаково");
                } else {
                    textFieldResult.setText("TreeMap работает дольше на " + Math.abs(diff) + " миллисекунд");
                }
            } catch (Exception exception) {
                displayError("Ошибка");
            }
        });

        buttonJavaCheckGet.addActionListener(e -> {
            try {
                HashMap<String, Integer> testHashMap = new HashMap<>();
                TreeMap<String, Integer> testTreeMap = new TreeMap<>();
                int length = Integer.parseInt(textFieldLength.getText());
                int size = Integer.parseInt(textFieldSize.getText());
                List<String> keys = Logic.generateRandomKeys(length, size);
                List<Integer> values = new ArrayList<>();
                for (int i = 0; i < keys.size(); i++) {
                    Random random = new Random();
                    values.add(random.nextInt());
                }
                for (int i = 0; i < keys.size(); i++) {
                    testHashMap.put(keys.get(i), values.get(i));
                    testTreeMap.put(keys.get(i), values.get(i));
                }

                //Logic.shuffleKeys(keys); // перемешиваю ключи, т.к. по условию требуется поиск случайных элементов
                Logic.clearStaticFields();
                long hashTime = Logic.checkHashGet(testHashMap, keys);
                long treeTime = Logic.checkTreeGet(testTreeMap, keys);
                long diff = hashTime - treeTime;

                if (diff > 0) {
                    textFieldResult.setText("HashMap работает дольше на " + diff + " миллисекунд");
                } else if (diff == 0) {
                    textFieldResult.setText("Одинаково");
                } else {
                    textFieldResult.setText("TreeMap работает дольше на " + Math.abs(diff) + " миллисекунд");
                }
            } catch (Exception exception) {
                displayError("Ошибка");
            }
        });

        buttonJavaCheckRemove.addActionListener(e -> {
            try {
                HashMap<String, Integer> testHashMap = new HashMap<>();
                TreeMap<String, Integer> testTreeMap = new TreeMap<>();
                int length = Integer.parseInt(textFieldLength.getText());
                int size = Integer.parseInt(textFieldSize.getText());
                List<String> keys = Logic.generateRandomKeys(length, size);
                List<Integer> values = new ArrayList<>();
                for (int i = 0; i < keys.size(); i++) {
                    Random random = new Random();
                    values.add(random.nextInt());
                }
                for (int i = 0; i < keys.size(); i++) {
                    testHashMap.put(keys.get(i), values.get(i));
                    testTreeMap.put(keys.get(i), values.get(i));
                }

                //Logic.shuffleKeys(keys);
                Logic.clearStaticFields();
                long hashTime = Logic.checkHashRemove(testHashMap, keys);
                long treeTime = Logic.checkTreeRemove(testTreeMap, keys);
                long diff = hashTime - treeTime;

                if (diff > 0) {
                    textFieldResult.setText("HashMap работает дольше на " + diff + " миллисекунд");
                } else if (diff == 0) {
                    textFieldResult.setText("Одинаково");
                } else {
                    textFieldResult.setText("TreeMap работает дольше на " + Math.abs(diff) + " миллисекунд");
                }
            } catch (Exception exception) {
                displayError("Ошибка");
            }
        });

        buttonJavaCheckPutAndRemove.addActionListener(e -> {
            try {
                int length = Integer.parseInt(textFieldLength.getText());
                int size = Integer.parseInt(textFieldSize.getText());
                List<String> keys = Logic.generateRandomKeys(length, size);
                List<Integer> values = new ArrayList<>();
                Random random = new Random();
                for (int i = 0; i < keys.size(); i++) {
                    values.add(random.nextInt());
                }

                Logic.clearStaticFields();
                long hashTime = Logic.checkHashPutAndRemove(keys, values);
                long treeTime = Logic.checkTreePutAndRemove(keys, values);
                long diff = hashTime - treeTime;

                if (diff > 0) {
                    textFieldResult.setText("HashMap работает дольше на " + diff + " миллисекунд");
                } else if (diff == 0) {
                    textFieldResult.setText("Одинаково");
                } else {
                    textFieldResult.setText("TreeMap работает дольше на " + Math.abs(diff) + " миллисекунд");
                }
            } catch (Exception exception) {
                displayError("Ошибка");
            }
        });

        buttonMyCheckPut.addActionListener(e -> {
            try {
                int length = Integer.parseInt(textFieldLength.getText());
                int size = Integer.parseInt(textFieldSize.getText());
                List<String> keys = Logic.generateRandomKeys(length, size);
                List<Integer> values = new ArrayList<>();
                for (int i = 0; i < keys.size(); i++) {
                    Random random = new Random();
                    values.add(random.nextInt());
                }

                Logic.clearStaticFields();
                long hashTime = Logic.checkMyHashPut(keys, values);
                long treeTime = Logic.checkMyTreePut(keys, values);
                long diff = hashTime - treeTime;

                if (diff > 0) {
                    textFieldResult.setText("HashMap работает дольше на " + diff + " миллисекунд");
                } else if (diff == 0) {
                    textFieldResult.setText("Одинаково");
                } else {
                    textFieldResult.setText("TreeMap работает дольше на " + Math.abs(diff) + " миллисекунд");
                }
            } catch (Exception exception) {
                displayError("Ошибка");
            }
        });

        buttonMyCheckGet.addActionListener(e -> {
            try {
                int length = Integer.parseInt(textFieldLength.getText());
                int size = Integer.parseInt(textFieldSize.getText());
                List<String> keys = Logic.generateRandomKeys(length, size);
                List<Integer> values = new ArrayList<>();
                SimpleHashMap<String, Integer> testHashMap = new SimpleHashMap<>(keys.size());
                AVLTreeMap<String, Integer> testTreeMap = new AVLTreeMap<>();
                for (int i = 0; i < keys.size(); i++) {
                    Random random = new Random();
                    values.add(random.nextInt());
                }
                for (int i = 0; i < keys.size(); i++) {
                    testHashMap.put(keys.get(i), values.get(i));
                    testTreeMap.put(keys.get(i), values.get(i));
                }

                //Logic.shuffleKeys(keys);
                Logic.clearStaticFields();
                long hashTime = Logic.checkMyHashGet(testHashMap, keys);
                long treeTime = Logic.checkMyTreeGet(testTreeMap, keys);
                long diff = hashTime - treeTime;

                if (diff > 0) {
                    textFieldResult.setText("HashMap работает дольше на " + diff + " миллисекунд");
                } else if (diff == 0) {
                    textFieldResult.setText("Одинаково");
                } else {
                    textFieldResult.setText("TreeMap работает дольше на " + Math.abs(diff) + " миллисекунд");
                }
            } catch (Exception exception) {
                displayError("Ошибка");
            }
        });

        buttonMyCheckRemove.addActionListener(e -> {
            try {
                int length = Integer.parseInt(textFieldLength.getText());
                int size = Integer.parseInt(textFieldSize.getText());
                List<String> keys = Logic.generateRandomKeys(length, size);
                List<Integer> values = new ArrayList<>();
                SimpleHashMap<String, Integer> testHashMap = new SimpleHashMap<>(keys.size());
                AVLTreeMap<String, Integer> testTreeMap = new AVLTreeMap<>();
                for (int i = 0; i < keys.size(); i++) {
                    Random random = new Random();
                    values.add(random.nextInt());
                }
                for (int i = 0; i < keys.size(); i++) {
                    testHashMap.put(keys.get(i), values.get(i));
                    testTreeMap.put(keys.get(i), values.get(i));
                }

                //Logic.shuffleKeys(keys);
                Logic.clearStaticFields();
                long hashTime = Logic.checkMyHashRemove(testHashMap, keys);
                long treeTime = Logic.checkMyTreeRemove(testTreeMap, keys);
                long diff = hashTime - treeTime;

                if (diff > 0) {
                    textFieldResult.setText("HashMap работает дольше на " + diff + " миллисекунд");
                } else if (diff == 0) {
                    textFieldResult.setText("Одинаково");
                } else {
                    textFieldResult.setText("TreeMap работает дольше на " + Math.abs(diff) + " миллисекунд");
                }
            } catch (Exception exception) {
                displayError("Ошибка");
            }
        });

        buttonMyCheckPutAndRemove.addActionListener(e -> {
            try {
                int length = Integer.parseInt(textFieldLength.getText());
                int size = Integer.parseInt(textFieldSize.getText());
                List<String> keys = Logic.generateRandomKeys(length, size);
                List<Integer> values = new ArrayList<>();
                for (int i = 0; i < keys.size(); i++) {
                    Random random = new Random();
                    values.add(random.nextInt());
                }

                Logic.clearStaticFields();
                long hashTime = Logic.checkMyHashPutAndRemove(keys, values);
                long treeTime = Logic.checkMyTreePutAndRemove(keys, values);
                long diff = hashTime - treeTime;

                if (diff > 0) {
                    textFieldResult.setText("HashMap работает дольше на " + diff + " миллисекунд");
                } else if (diff == 0) {
                    textFieldResult.setText("Одинаково");
                } else {
                    textFieldResult.setText("TreeMap работает дольше на " + Math.abs(diff) + " миллисекунд");
                }
            } catch (Exception exception) {
                displayError("Ошибка");
            }
        });

    }

    private void displayError(String errorText) {
        JOptionPane.showMessageDialog(this, errorText,
                "Ошибка", JOptionPane.ERROR_MESSAGE);
    }

}



