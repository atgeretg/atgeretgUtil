package com.atgeretg.util.number;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 关于Dobule的计算<br>
 * BigDecimal.ROUND_UP：远离零方向舍入。向绝对值最大的方向舍入，只要舍弃位非0即进位。
 * BigDecimal.ROUND_DOWN：趋向零方向舍入。向绝对值最小的方向输入，所有的位都要舍弃，不存在进位情况。
 * BigDecimal.ROUND_CEILING：向正无穷方向舍入。向正最大方向靠拢。若是正数，舍入行为类似于ROUND_UP，若为负数，舍入行为类似于ROUND_DOWN。Math.round()方法就是使用的此模式。
 * BigDecimal.ROUND_FLOOR：向负无穷方向舍入。向负无穷方向靠拢。若是正数，舍入行为类似于ROUND_DOWN；若为负数，舍入行为类似于ROUND_UP。
 * BigDecimal.HALF_UP：最近数字舍入(5进)。这是我们最经典的四舍五入。BigDecimal.ROUND_HALF_UP
 * BigDecimal.HALF_DOWN：最近数字舍入(5舍)。在这里5是要舍弃的。
 * BigDecimal.HAIL_EVEN：银行家舍入法。
 *
 * @author atgeretg
 */
public class NumberCalc {

    private static final double ZERO = 0d;

//    public static void main(String[] args) {
//        float f = 53.51655f;
//        BigDecimal decimal = mul(f, f);
//        BigDecimal decimal1 = mul(decimal.floatValue(), f);
//        float ff = (float) (f * f);
//        System.out.println(" decimal = "+decimal + " decimal1 = " + decimal1) ;//153646.17
//        System.out.println("-------------------");
//        System.out.println("ff = " + ff + " f = " +(ff * f) + " decimal = "+decimal.floatValue() + " decimal1 = " + decimal1.floatValue()) ;//153646.17
//        System.out.println(round(9.998,2,BigDecimal.ROUND_DOWN));
//        DecimalFormat format = new DecimalFormat("0.000");
//        System.out.println(format.format(mul(999999999, 9, 3)));
//        int i = 55;
//        int ii = (int) (i * 0.3333);
//        double d = (i * 0.3333);
//        System.out.println(ii + " d = " + d + " round = "
//                + round(i * 0.3333, 0, BigDecimal.ROUND_UP));
//    }

    // BigDecimal.ROUND_UP：远离零方向舍入。向绝对值最大的方向舍入，只要舍弃位非0即进位。
    // BigDecimal.ROUND_DOWN：趋向零方向舍入。向绝对值最小的方向输入，所有的位都要舍弃，不存在进位情况。
    // BigDecimal.ROUND_CEILING：向正无穷方向舍入。向正最大方向靠拢。若是正数，舍入行为类似于ROUND_UP，若为负数，舍入行为类似于ROUND_DOWN。Math.round()方法就是使用的此模式。
    // BigDecimal.ROUND_FLOOR：向负无穷方向舍入。向负无穷方向靠拢。若是正数，舍入行为类似于ROUND_DOWN；若为负数，舍入行为类似于ROUND_UP。
    // BigDecimal.HALF_UP：最近数字舍入(5进)。这是我们最经典的四舍五入。BigDecimal.ROUND_HALF_UP
    // BigDecimal.HALF_DOWN：最近数字舍入(5舍)。在这里5是要舍弃的。
    // BigDecimal.HAIL_EVEN：银行家舍入法。

    /**
     * 对double数据进行取精度.<br>
     * ROUND_UP：远离零方向舍入。向绝对值最大的方向舍入，只要舍弃位非0即进位。<br>
     * ROUND_DOWN：趋向零方向舍入。向绝对值最小的方向输入，所有的位都要舍弃，不存在进位情况。<br>
     * ROUND_CEILING：向正无穷方向舍入。向正最大方向靠拢。若是正数，舍入行为类似于ROUND_UP，若为负数，
     * 舍入行为类似于ROUND_DOWN。Math.round()方法就是使用的此模式。<br>
     * ROUND_FLOOR：向负无穷方向舍入。向负无穷方向靠拢。若是正数，舍入行为类似于ROUND_DOWN；若为负数，舍入行为类似于ROUND_UP
     * 。<br>
     * ROUND_HALF_UP：最近数字舍入(5进)。这是我们最经典的四舍五入。 HALF_DOWN：最近数字舍入(5舍)。在这里5是要舍弃的。<br>
     * HAIL_EVEN：银行家舍入法。<br>
     *
     * @param value        double数据.
     * @param scale        精度位数(保留的小数位数).
     * @param roundingMode 精度取值方式. 如：BigDecimal.ROUND_HALF_UP四舍五入
     * @return 精度计算后的数据.
     */
    public static BigDecimal round(double value, int scale, int roundingMode) {
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(scale, roundingMode);
        return bd;
    }

    /**
     * double 相加
     *
     * @param d1
     * @param d2
     * @return
     */
    public static BigDecimal sum(double d1, double d2) {
        BigDecimal bd1 = new BigDecimal(Double.toString(d1));
        BigDecimal bd2 = new BigDecimal(Double.toString(d2));
        return bd1.add(bd2);//.doubleValue();
    }

    /**
     * double 相加,保留几位小数点
     *
     * @param d1
     * @param d2
     * @param scale 四舍五入精度位数(保留的小数位数).
     * @return
     */
    public static BigDecimal sum(double d1, double d2, int scale) {
        return sum(d1, d2).setScale(scale, BigDecimal.ROUND_HALF_UP);
    }


    /**
     * double 相减
     *
     * @param d1
     * @param d2
     * @return
     */
    public static BigDecimal sub(double d1, double d2) {
        BigDecimal bd1 = new BigDecimal(Double.toString(d1));
        BigDecimal bd2 = new BigDecimal(Double.toString(d2));
        return bd1.subtract(bd2);//.doubleValue();
    }

    /**
     * double 相减,保留几位小数点
     *
     * @param d1
     * @param d2
     * @param scale 四舍五入精度位数(保留的小数位数).
     * @return
     */
    public static BigDecimal sub(double d1, double d2, int scale) {
        return sub(d1, d2).setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * double 乘法
     *
     * @param d1
     * @param d2
     * @return
     */
    public static BigDecimal mul(double d1, double d2) {
        BigDecimal bd1 = new BigDecimal(Double.toString(d1));
        BigDecimal bd2 = new BigDecimal(Double.toString(d2));
        return bd1.multiply(bd2);//.doubleValue();
    }

    /**
     * double 乘法,保留几位小数点
     *
     * @param d1
     * @param d2
     * @param scale 四舍五入精度位数(保留的小数位数).
     * @return
     */
    public static BigDecimal mul(double d1, double d2, int scale) {
        return mul(d1, d2).setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * double 除法,保留几位小数点，除数为0返回0
     *
     * @param d1 被除数（第一个除号前的数）
     * @param d2 除数
     * @return
     */
    public static BigDecimal div(double d1, double d2) {
        // 当然在此之前，你要判断分母是否为0，
        // 为0你可以根据实际需求做相应的处理
        if (d2 == ZERO || d1 == ZERO)//无论分子还是分母为0都不计算
            return new BigDecimal(0);
        BigDecimal bd1 = new BigDecimal(Double.toString(d1));
        BigDecimal bd2 = new BigDecimal(Double.toString(d2));
        return bd1.divide(bd2);// scale, BigDecimal.ROUND_HALF_UP);//.doubleValue();
    }

    /**
     * double 除法,保留几位小数点，除数为0返回0
     *
     * @param d1    被除数（第一个除号前的数）
     * @param d2    除数
     * @param scale 四舍五入 小数点位数
     * @return
     */
    public static BigDecimal div(double d1, double d2, int scale) {
        return div(d1, d2).setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 对double数据进行取精度.<br>
     * ROUND_UP：远离零方向舍入。向绝对值最大的方向舍入，只要舍弃位非0即进位。<br>
     * ROUND_DOWN：趋向零方向舍入。向绝对值最小的方向输入，所有的位都要舍弃，不存在进位情况。<br>
     * ROUND_CEILING：向正无穷方向舍入。向正最大方向靠拢。若是正数，舍入行为类似于ROUND_UP，若为负数，
     * 舍入行为类似于ROUND_DOWN。Math.round()方法就是使用的此模式。<br>
     * ROUND_FLOOR：向负无穷方向舍入。向负无穷方向靠拢。若是正数，舍入行为类似于ROUND_DOWN；若为负数，舍入行为类似于ROUND_UP
     * 。<br>
     * ROUND_HALF_UP：最近数字舍入(5进)。这是我们最经典的四舍五入。 HALF_DOWN：最近数字舍入(5舍)。在这里5是要舍弃的。<br>
     * HAIL_EVEN：银行家舍入法。<br>
     *
     * @param value        double数据.
     * @param scale        精度位数(保留的小数位数).
     * @param roundingMode 精度取值方式. 如：BigDecimal.ROUND_HALF_UP四舍五入
     * @return 精度计算后的数据.
     */
    public static BigDecimal round(float value, int scale, int roundingMode) {
        BigDecimal bd = new BigDecimal(Float.toString(value));
        bd = bd.setScale(scale, roundingMode);
        return bd;
    }

    /**
     * double 相加
     *
     * @param d1
     * @param d2
     * @return
     */
    public static BigDecimal sum(float d1, float d2) {
        BigDecimal bd1 = new BigDecimal(Float.toString(d1));
        BigDecimal bd2 = new BigDecimal(Float.toString(d2));
        return bd1.add(bd2);//.doubleValue();
    }

    /**
     * double 相加,保留几位小数点
     *
     * @param d1
     * @param d2
     * @param scale 四舍五入精度位数(保留的小数位数).
     * @return
     */
    public static BigDecimal sum(float d1, float d2, int scale) {
        return sum(d1, d2).setScale(scale, BigDecimal.ROUND_HALF_UP);
    }


    /**
     * double 相减
     *
     * @param d1
     * @param d2
     * @return
     */
    public static BigDecimal sub(float d1, float d2) {
        BigDecimal bd1 = new BigDecimal(Float.toString(d1));
        BigDecimal bd2 = new BigDecimal(Float.toString(d2));
        return bd1.subtract(bd2);//.doubleValue();
    }

    /**
     * double 相减,保留几位小数点
     *
     * @param d1
     * @param d2
     * @param scale 四舍五入精度位数(保留的小数位数).
     * @return
     */
    public static BigDecimal sub(float d1, float d2, int scale) {
        return sub(d1, d2).setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * double 乘法
     *
     * @param d1
     * @param d2
     * @return
     */
    public static BigDecimal mul(float d1, float d2) {
        BigDecimal bd1 = new BigDecimal(Float.toString(d1));
        BigDecimal bd2 = new BigDecimal(Float.toString(d2));
        return bd1.multiply(bd2);//.doubleValue();
    }

    /**
     * double 乘法,保留几位小数点
     *
     * @param d1
     * @param d2
     * @param scale 四舍五入精度位数(保留的小数位数).
     * @return
     */
    public static BigDecimal mul(float d1, float d2, int scale) {
        return mul(d1, d2).setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * double 除法,保留几位小数点，除数为0返回0
     *
     * @param d1 被除数（第一个除号前的数）
     * @param d2 除数
     * @return
     */
    public static BigDecimal div(float d1, float d2) {
        // 当然在此之前，你要判断分母是否为0，
        // 为0你可以根据实际需求做相应的处理
        if (d2 == ZERO || d1 == ZERO)//无论分子还是分母为0都不计算
            return new BigDecimal(0);
        BigDecimal bd1 = new BigDecimal(Float.toString(d1));
        BigDecimal bd2 = new BigDecimal(Float.toString(d2));
        BigDecimal divide = null;
        try {
             divide = bd1.divide(bd2);
        } catch (ArithmeticException e) {
            //结果为无限小数
            divide = bd1.divide(bd2,6,BigDecimal.ROUND_HALF_UP);
        }
        return divide;
    }

    /**
     * double 除法,保留几位小数点，除数为0返回0
     *
     * @param d1    被除数（第一个除号前的数）
     * @param d2    除数
     * @param scale 四舍五入 小数点位数
     * @return
     */
    public static BigDecimal div(float d1, float d2, int scale) {
        return div(d1, d2).setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

}
