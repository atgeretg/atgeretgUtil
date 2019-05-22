package com.atgeretg.util.number;

import java.math.BigDecimal;

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
public class DoubleCalc {

	private static final double ZERO = 0d;
//	public static void main(String[] args) {
//		DecimalFormat format = new DecimalFormat("0.000");
//		System.out.println(format.format(mul(999999999,9,3)));
//		int i = 55;
//		int ii = (int) (i * 0.3333);
//		double d = (i * 0.3333);
//		System.out.println(ii + " d = " + d + " round = "
//				+ round(i * 0.3333, 0, BigDecimal.ROUND_UP));
//	}

	// ROUND_UP：远离零方向舍入。向绝对值最大的方向舍入，只要舍弃位非0即进位。
	// ROUND_DOWN：趋向零方向舍入。向绝对值最小的方向输入，所有的位都要舍弃，不存在进位情况。
	// ROUND_CEILING：向正无穷方向舍入。向正最大方向靠拢。若是正数，舍入行为类似于ROUND_UP，若为负数，舍入行为类似于ROUND_DOWN。Math.round()方法就是使用的此模式。
	// ROUND_FLOOR：向负无穷方向舍入。向负无穷方向靠拢。若是正数，舍入行为类似于ROUND_DOWN；若为负数，舍入行为类似于ROUND_UP。
	// HALF_UP：最近数字舍入(5进)。这是我们最经典的四舍五入。BigDecimal.ROUND_HALF_UP
	// HALF_DOWN：最近数字舍入(5舍)。在这里5是要舍弃的。
	// HAIL_EVEN：银行家舍入法。

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
	 * @param value
	 *            double数据.
	 * @param scale
	 *            精度位数(保留的小数位数).
	 * @param roundingMode
	 *            精度取值方式. 如：BigDecimal.ROUND_HALF_UP四舍五入
	 * @return 精度计算后的数据.
	 */
	public static double round(double value, int scale, int roundingMode) {
		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(scale, roundingMode);
		double d = bd.doubleValue();
//		bd = null;
		return d;
	}

	/**
	 * double 相加
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static double sum(double d1, double d2) {
		BigDecimal bd1 = new BigDecimal(Double.toString(d1));
		BigDecimal bd2 = new BigDecimal(Double.toString(d2));
		return bd1.add(bd2).doubleValue();
	}
	
	/**
	 * double 相加,保留几位小数点
	 * 
	 * @param d1
	 * @param d2
	 * @param scale
	 *            四舍五入精度位数(保留的小数位数).
	 * @return
	 */
	public static double sum(double d1, double d2, int scale) {
		return round(sum(d1,d2),scale,BigDecimal.ROUND_HALF_UP);
	}

	
	
	/**
	 * double 相减
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static double sub(double d1, double d2) {
		BigDecimal bd1 = new BigDecimal(Double.toString(d1));
		BigDecimal bd2 = new BigDecimal(Double.toString(d2));
		return bd1.subtract(bd2).doubleValue();
	}
	
	/**
	 * double 相减,保留几位小数点
	 * 
	 * @param d1
	 * @param d2
	 * @param scale
	 *            四舍五入精度位数(保留的小数位数).
	 * @return
	 */
	public static double sub(double d1, double d2, int scale) {
		return round(sub(d1,d2),scale,BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * double 乘法
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static double mul(double d1, double d2) {
		BigDecimal bd1 = new BigDecimal(Double.toString(d1));
		BigDecimal bd2 = new BigDecimal(Double.toString(d2));
		return bd1.multiply(bd2).doubleValue();
	}
	
	/**
	 * double 乘法,保留几位小数点
	 * 
	 * @param d1
	 * @param d2
	 * @param scale
	 *            四舍五入精度位数(保留的小数位数).
	 * @return
	 */
	public static double mul(double d1, double d2, int scale) {
		return round(mul(d1,d2),scale,BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * double 除法,保留几位小数点，除数为0返回0
	 * 
	 * @param d1 被除数（第一个除号前的数）
	 * @param d2 除数
	 * @return
	 */
	public static double div(double d1, double d2) {
		// 当然在此之前，你要判断分母是否为0，
		// 为0你可以根据实际需求做相应的处理
		if(d1 == ZERO||d2 == ZERO)
			return ZERO;
		BigDecimal bd1 = new BigDecimal(Double.toString(d1));
		BigDecimal bd2 = new BigDecimal(Double.toString(d2));
		BigDecimal divide = null;
		try {
			divide = bd1.divide(bd2);
		} catch (ArithmeticException e) {
			//结果为无限小数
			divide = bd1.divide(bd2,6,BigDecimal.ROUND_HALF_UP);
		}
		return divide.doubleValue();
//		return bd1.divide(bd2).doubleValue();
	}

	/**
	 * double 除法,保留几位小数点，除数为0返回0
	 *
	 * @param d1 被除数（第一个除号前的数）
	 * @param d2 除数
	 * @param scale
	 *            四舍五入 小数点位数
	 * @return
	 */
	public static double div(double d1, double d2, int scale) {
		return round(div(d1,d2),scale,BigDecimal.ROUND_HALF_UP);
	}
	
	
}
