import java.util.Map;
import java.util.HashMap;
import java.util.Random;

/**
 * task
 */
public class task {

    public static void main(String[] args) {
        Map<Integer, String> map1 = new HashMap<Integer, String>();
        pill_Map(map1);
        println(map1);
        add_exclamation_mark(map1);
        println(map1);
        println("");

        Map<Integer, String> map2 = new HashMap<Integer, String>();
        pill_Map(map2);
        println(map2);
        add_exclamation_mark(map2);
        println(map2);
        println("");
        
        // я немного не понял как их объединять, в плане в первом, во втором
        // или в новом только объединеные значения оставить
        // поэтому сделал и так и так
        Map<Integer, String> merge_map1 = merge_map1(clone_Map(map1), clone_Map(map2));
        Map<Integer, String> merge_map2 = merge_map1(clone_Map(map2), clone_Map(map1));
        Map<Integer, String> merge_map3 = merge_map2(clone_Map(map1), clone_Map(map2));
        System.out.println(merge_map1);
        System.out.println(merge_map2);
        System.out.println(merge_map3);


    }

    static void println(Object o) {
        System.out.println(o);
    }

    static void pill_Map(Map<Integer, String> map) {
        int temp = 0;
        for (int i = 0; i < 6; i++) {
            temp = new Random().nextInt(1, 11);
            if (map.putIfAbsent(temp, Integer.toBinaryString(temp)) != null) {
                i--;
            }
        }
    }

    static void add_exclamation_mark(Map<Integer, String> map) {
        for (Integer key : map.keySet()) {
            map.compute(key, (k, v) -> v + "!");
        }
    }

    static Map<Integer, String> merge_map1(Map<Integer, String> map1, Map<Integer, String> map2) {
        for (Integer key : map2.keySet()) {
            map1.computeIfPresent(key, (k, v) -> v + map2.get(key));
        }
        return map1;
    }

    static Map<Integer, String> merge_map2(Map<Integer, String> map1, Map<Integer, String> map2) {
        Map<Integer, String> map = new HashMap<Integer, String>();
        for (Integer key : map1.keySet()) {
            if (map2.containsKey(key)) {
                map.put(key, map1.merge(key, map2.get(key), (v1, v2) -> v1 + v2));
            }
        }
        return map;
    }

    static Map<Integer, String> clone_Map(Map<Integer, String> map) {
        Map<Integer, String> clone_map = new HashMap<Integer, String>();
        for (Integer key : map.keySet()) {
            clone_map.put(key, map.get(key));
        }
        return clone_map;
    }
}