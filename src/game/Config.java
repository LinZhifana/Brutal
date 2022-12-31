package game;

import java.util.HashMap;

public interface Config {

    final int NUM_OF_STU = 20;          // 学生总数
    final int NUM_OF_RES = 5;           // 预备役人数
    // 每种学生人数
    final int[] NUM_OF_EACH_STU_KIND = {15, 4, 1};

    final int MAX_POINT = 10;           // 最大点数
    final int TOTAL_POINT = 400;        // 总分配点数
    // 不同种类学生初始数值
    final int[][] INITIAL_VALUES = {{0, 0}, {1, 5}, {2, 10}};

    final int VICTORY_CONDITIONS = 3;   // 胜利条件

    final HashMap<String,Integer> attribute = new HashMap<String, Integer>() {{
        put("isr", 0);
        put("str", 1);
        put("f", 2);
        put("d", 3);
        put("r", 4);
        put("c", 5);
        put("i", 6);
        put("z", 7);
    }};
}
