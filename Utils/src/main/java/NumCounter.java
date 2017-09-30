/**
 * Created by Adam_Yao on 2017/9/30.
 * 字符串中的数字计算器实现
 */
public class NumCounter {

    private int num;
    private int sum;
    // 是否当前是个完整的数字
    private boolean isWholeNum;

    public NumCounter(int num, int sum, boolean isWholeNum) {
        this.num = num;
        this.sum = sum;
        this.isWholeNum = isWholeNum;
    }

    public NumCounter accumulate(Character c){
        if (Character.isDigit(c)){
            return isWholeNum ? new NumCounter(Integer.parseInt("" + c),sum + num, false) :
                    new NumCounter(Integer.parseInt("" + num + c), sum, false);
        }else {
            return new NumCounter(0, sum + num, true);
        }
    }

    public NumCounter combine(NumCounter numCounter){
        return new NumCounter(numCounter.num, this.getSum() + numCounter.getSum(), numCounter.isWholeNum);
    }

    public int getSum() {
        return sum + num;
    }

    @Override
    public String toString() {
        return "NumCounter{" +
                "num=" + num +
                ", sum=" + sum +
                ", isWholeNum=" + isWholeNum +
                '}';
    }
}