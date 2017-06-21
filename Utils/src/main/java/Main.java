import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Adam_Yao on 2017/6/20.
 */
public class Main {
    public static void main(String[] args) {
        Integer size = 10;
        System.out.print(size + "道门 :");
        Map<Integer, String> nums = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            nums.put(i + 1, i + 1 + "");
        }

        Integer car = (int) (Math.random() * size) + 1;
        System.out.println("汽车所在位置: " + car);
        nums.put(car, "汽车");

        Integer choose = (int) (Math.random() * size) + 1;
        System.out.println("首次选择位置: " + choose);

        nums.remove(choose);
        System.out.println(nums);

        int count = 0;
        Integer nextOpen = (int) (Math.random() * size) + 1;
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (nums.get(nextOpen) != null && nums.get(nextOpen).equals("汽车")) {
                System.out.println("未抽中次数：" + count);
                System.out.println("很遗憾！");
                return;
            } else {
                //删除已打开
                nums.remove(nextOpen);
                System.out.println(nums);
                //更新选择
                boolean c = true;
                if (nums.get(choose) != null) {
                    c = nums.get(choose).equals("汽车");
                }
                if (!c) {
                    nums.put(choose, choose + "");
                }
                choose = (int) (Math.random() * size) + 1;
                nums.remove(choose);
                System.out.println(nums);
                count++;
            }
        }


    }
}
