package com.example.demo.refundAlgorithm;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName:LoanUtil  
 * @Description:TODO(息费计算相关的工具类)       
 */
public class LoanUtil {

	public static Map<Short, BigDecimal> overDueFeeRate = new HashMap<Short, BigDecimal>();
	public static DecimalFormat FORMAT = new DecimalFormat("#.00");
	private final static int YEAR_MONTH_COUNT = 12;
	private final static BigDecimal N_12 = new BigDecimal(YEAR_MONTH_COUNT);
	private final static BigDecimal N_360 = new BigDecimal(360);
	private final static BigDecimal N_100 = new BigDecimal(100);
	private static BigDecimal interestRate;// 月利率

	private static LoanUtil instance = new LoanUtil();

	private LoanUtil() {
	}

	public static LoanUtil getInstance() {
		return instance;
	}

	
	/**
	 * 计算“融资收益率”，按照“等额本息法”计算
	 * 
	 * @param investAmount
	 *            融资金额
	 * @param interest
	 *            利息金额
	 * @param termCount
	 *            还款期限（月数）
	 * @return
	 * @return BigDecimal 年利率
	 * 
	 */
	public static BigDecimal getYearlyInterestRate(BigDecimal investAmount,
			BigDecimal interest, int termCount) {

		// 近似利率,保留小数点后100位
		BigDecimal approximateInterestRate = interest.divide(investAmount, 100,
				BigDecimal.ROUND_HALF_UP);

		BigDecimal count = new BigDecimal(termCount);

		// 每期所得本息（实际）
		BigDecimal termReturnAmount = (investAmount.add(interest)).divide(
				count, 100, BigDecimal.ROUND_HALF_UP);

		// 利率（以近似利率为起始点，循环递增或递减，夹逼）
		interestRate = approximateInterestRate;

		BigDecimal monthlyReturnAmount = BigDecimal.ZERO;
		BigDecimal preMonthlyReturnAmount = BigDecimal.ZERO;

		BigDecimal monthlyABS = BigDecimal.ZERO;
		BigDecimal preMonthlyABS = BigDecimal.ZERO;
		monthlyReturnAmount = getMonthlyReturnAmount(investAmount,
				interestRate, termCount);

		int flag = monthlyReturnAmount.compareTo(termReturnAmount);

		if (flag == 0) {
			return interestRate.multiply(new BigDecimal(YEAR_MONTH_COUNT))
					.setScale(4, BigDecimal.ROUND_HALF_UP);
		} else if (flag == -1) {
			while (monthlyReturnAmount.compareTo(termReturnAmount) == -1) {
				interestRate = interestRate.add(new BigDecimal(0.00001));
				monthlyReturnAmount = getMonthlyReturnAmount(investAmount,
						interestRate, termCount);
				if (monthlyReturnAmount.compareTo(termReturnAmount) != -1) {

					interestRate = interestRate
							.subtract(new BigDecimal(0.00001));
					preMonthlyReturnAmount = getMonthlyReturnAmount(
							investAmount, interestRate, termCount);
					monthlyABS = monthlyReturnAmount.subtract(termReturnAmount)
							.abs();
					preMonthlyABS = preMonthlyABS.subtract(termReturnAmount)
							.abs();

					if (monthlyABS.compareTo(preMonthlyABS) != 1) {
						interestRate = interestRate
								.add(new BigDecimal(0.00001));
					}
					break;
				}
			}
		} else if (flag == 1) {
			while (monthlyReturnAmount.compareTo(termReturnAmount) == 1) {
				interestRate = interestRate.subtract(new BigDecimal(0.00001));
				monthlyReturnAmount = getMonthlyReturnAmount(investAmount,
						interestRate, termCount);
				if (monthlyReturnAmount.compareTo(termReturnAmount) != 1) {

					interestRate = interestRate.add(new BigDecimal(0.00001));
					preMonthlyReturnAmount = getMonthlyReturnAmount(
							investAmount, interestRate, termCount);
					monthlyABS = monthlyReturnAmount.subtract(termReturnAmount)
							.abs();
					preMonthlyABS = preMonthlyReturnAmount.subtract(
							termReturnAmount).abs();

					if (monthlyABS.compareTo(preMonthlyABS) != 1) {
						interestRate = interestRate.subtract(new BigDecimal(
								0.00001));
					}
					break;
				}
			}
		}

		return interestRate.setScale(6, BigDecimal.ROUND_HALF_UP)
				.multiply(new BigDecimal(YEAR_MONTH_COUNT))
				.setScale(4, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 根据利率算出每期所得本息
	 * 
	 * @param investAmount
	 *            融资额
	 * @param interestRate
	 *            利率
	 * @param termCount
	 *            还款期数
	 * @return
	 * @return BigDecimal 每期所得本息
	 * 
	 */
	private static BigDecimal getMonthlyReturnAmount(BigDecimal investAmount,
			BigDecimal interestRate, int termCount) {

		// 每期所得本息（计算）
		BigDecimal monthlyReturnAmount = BigDecimal.ZERO;

		// (1+interestRate)^termCount
		BigDecimal pow = BigDecimal.valueOf(Math.pow(
				interestRate.add(BigDecimal.ONE).doubleValue(),
				(double) termCount));

		// 每月还款额=[贷款本金×月利率×（1+月利率）^还款月数]÷[（1+月利率）^还款月数－1]
		monthlyReturnAmount = investAmount
				.multiply(interestRate)
				.multiply(pow)
				.divide((pow.subtract(BigDecimal.ONE)), 100,
						BigDecimal.ROUND_HALF_UP);

		return monthlyReturnAmount;
	}

	/**
	 * 按<b>月</b>计算等额本息还款法中，每月应还本息总额
	 * 
	 * @param months
	 *            月数
	 * @param amount
	 *            借款额
	 * @param annualInterestRate
	 *            年化利率，0.15表示15%
	 * @return
	 */
	public static BigDecimal calculateAverageCapitalPlusInterest(int months,
			BigDecimal amount, BigDecimal annualInterestRate) {

		BigDecimal monthlyInterest = annualInterestRate.divide(N_12, 8,
				BigDecimal.ROUND_HALF_EVEN);

		BigInteger INTEGER_ZERO = BigInteger.ZERO;
		// 每个变量都设为4位小数，初始值为0.00000000
		BigDecimal step1 = new BigDecimal(INTEGER_ZERO, 8);
		BigDecimal step2 = new BigDecimal(INTEGER_ZERO, 8);
		BigDecimal step3 = new BigDecimal(INTEGER_ZERO, 8);
		BigDecimal step4 = new BigDecimal(INTEGER_ZERO, 8);

		step1 = amount.multiply(monthlyInterest);
		step2 = (monthlyInterest.add(BigDecimal.ONE)).pow(months);
		step3 = step1.multiply(step2);

		step4 = ((monthlyInterest.add(BigDecimal.ONE)).pow(months))
				.subtract(BigDecimal.ONE);

		return step3.divide(step4, 2, BigDecimal.ROUND_HALF_EVEN);
	}

	/**
	 * 计算一次性还本付息方式下，利息和本金所得
	 * termUnit = 1	天利率：本金*(年利率/360)*天数+本金
	 * termUnit = 3	月利率：本金*(年利率/12)*月数+本金
	 * @param termCount
	 * @param termUnit
	 * @param amount
	 * @param annualInterestRate
	 * @return
	 * @throws Exception 
	 */
	public static BigDecimal calculateOnetimeRepayInterest(int termCount,
			int termUnit, BigDecimal amount,
			BigDecimal annualInterestRate) throws Exception
			{
		if (termUnit == 1) {
			BigDecimal dailyInterest = annualInterestRate.divide(N_360, 8,
					BigDecimal.ROUND_HALF_EVEN);
			return amount.multiply(dailyInterest)
					.multiply(new BigDecimal(termCount)).add(amount)
					.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		} else if (termUnit == 3) {
			BigDecimal monthlyInterest = annualInterestRate.divide(N_12, 8,
					BigDecimal.ROUND_HALF_EVEN);
			return amount.multiply(monthlyInterest)
					.multiply(new BigDecimal(termCount)).add(amount)
					.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		} else {
			throw new Exception("不支持的时间单位: " + termUnit);
		}
	}

	
	/**
	 * 按天借款的方式 返款时间的计算
	 * 
	 * @param releaseTime
	 *            放款时间
	 * @param termCount
	 *            借款天数
	 * @return 还款日期
	 */
	public static Date getDayRepayDate(Date releaseTime, int termCount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(releaseTime);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.add(Calendar.SECOND, -1);
		calendar.add(Calendar.DAY_OF_MONTH, termCount);
		return calendar.getTime();
	}

	/**
	 * 根据年利率获取天利率
	 * 
	 * @param yearInterest
	 * @return
	 */
	public static BigDecimal getDayInterestByYear(BigDecimal yearInterest) {
		return yearInterest.divide(new BigDecimal(100)).divide(N_360, 8,
				BigDecimal.ROUND_HALF_EVEN);
	}

	/**
	 * 根据年利率获取月利率
	 * 
	 * @param yearInterest
	 * @return
	 */
	public static BigDecimal getMonthInterestByYear(BigDecimal yearInterest) {
		return yearInterest.divide(new BigDecimal(100)).divide(N_12, 8,
				BigDecimal.ROUND_HALF_EVEN);
	}

	

	/**
     * @Title: calculateInterest 利息总计 = 本金*利率*数
     * @Description: TODO(利随本清利息计算)
     * @param @param amount 本金
     * @param @param annualInterestRate 年利率
     * @param @param termUnit 1为日利率  3为月利率
     * @param @param termCount 日数，月数
     * @param @return 利随本清方式利息总计
     * @param @throws Exception参数  
     * @return BigDecimal 返回类型
     * @throws
	 *
     */
    public static BigDecimal calculateInterest(BigDecimal amount,
			BigDecimal annualInterestRate, int termUnit, int termCount) throws Exception
			 {
			annualInterestRate = annualInterestRate.multiply(new BigDecimal(100));
		if (termUnit == 1) {
			BigDecimal dailyInterest = LoanUtil
					.getDayInterestByYear(annualInterestRate);
			return amount.multiply(dailyInterest)
					.multiply(new BigDecimal(termCount))
					.setScale(2,
					BigDecimal.ROUND_HALF_EVEN);
		} else if (termUnit == 3) {
			BigDecimal monthlyInterest = LoanUtil
					.getMonthInterestByYear(annualInterestRate);
			return amount.multiply(monthlyInterest)
					.multiply(new BigDecimal(termCount))
					.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		} else {
			throw new Exception("不支持的时间单位: " + termUnit);
		}
		
			 }
    
    
    
    /**
     * 等额本金计算获取还款方式为等额本金的每月偿还本金和利息
     * 
     * 公式：每月偿还本金=(贷款本金÷还款月数)+(贷款本金-已归还本金累计额)×月利率
     * 
     * @param invest
     *            总借款额（贷款本金）
     * @param yearRate
     *            年利率
     * @param totalMonth
     *            还款总月数
     * @return 每月偿还本金和利息,不四舍五入，直接截取小数点最后两位
     */
    public static Map<Integer, Double> getPerMonthPrincipalInterest(double invest, double yearRate, int totalMonth) {
        Map<Integer, Double> map = new HashMap<Integer, Double>();
        // 每月本金
        double monthPri = getPerMonthPrincipal(invest, totalMonth);
        // 获取月利率
        double monthRate = yearRate / 12;
        monthRate = new BigDecimal(monthRate).setScale(6, BigDecimal.ROUND_DOWN).doubleValue();
        for (int i = 1; i <= totalMonth; i++) {
            double monthRes = monthPri + (invest - monthPri * (i - 1)) * monthRate;
            monthRes = new BigDecimal(monthRes).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
            map.put(i, monthRes);
        }
        return map;
    }

    /**
     * 等额本金计算获取还款方式为等额本金的每月偿还本金
     * 
     * 公式：每月应还本金=贷款本金÷还款月数
     * 
     * @param invest
     *            总借款额（贷款本金）
     * @param totalMonth
     *            还款总月数
     * @return 每月偿还本金
     */
    public static double getPerMonthPrincipal(double invest, int totalMonth) {
        BigDecimal monthIncome = new BigDecimal(invest).divide(new BigDecimal(totalMonth), 2, BigDecimal.ROUND_DOWN);
        return monthIncome.doubleValue();
    }
 
    /**
     * 等额本金计算获取还款方式为等额本金的总利息
     * 
     * @param invest
     *            总借款额（贷款本金）
     * @param yearRate
     *            年利率
     * @param totalMonth
     *            还款总月数
     * @return 总利息
     */
    public static double getInterestCount(double invest, double yearRate, int totalMonth) {
        BigDecimal count = new BigDecimal(0);
        Map<Integer, Double> mapInterest = getPerMonthInterest(invest, yearRate, totalMonth);
 
        for (Map.Entry<Integer, Double> entry : mapInterest.entrySet()) {
            count = count.add(new BigDecimal(entry.getValue()));
        }
        return count.doubleValue();
    }
    
    /**
     * 等额本金计算获取还款方式为等额本金的每月偿还利息
     * 
     * 公式：每月应还利息=剩余本金×月利率=(贷款本金-已归还本金累计额)×月利率
     * 
     * @param invest
     *            总借款额（贷款本金）
     * @param yearRate
     *            年利率
     * @param totalMonth
     *            还款总月数
     * @return 每月偿还利息
     */
    public static Map<Integer, Double> getPerMonthInterest(double invest, double yearRate, int totalMonth) {
        Map<Integer, Double> inMap = new HashMap<Integer, Double>();
        double principal = getPerMonthPrincipal(invest, totalMonth);
        Map<Integer, Double> map = getPerMonthPrincipalInterest(invest, yearRate, totalMonth);
        for (Map.Entry<Integer, Double> entry : map.entrySet()) {
            BigDecimal principalBigDecimal = new BigDecimal(principal);
            BigDecimal principalInterestBigDecimal = new BigDecimal(entry.getValue());
            BigDecimal interestBigDecimal = principalInterestBigDecimal.subtract(principalBigDecimal);
            interestBigDecimal = interestBigDecimal.setScale(2, BigDecimal.ROUND_DOWN);
            inMap.put(entry.getKey(), interestBigDecimal.doubleValue());
        }
        return inMap;
    }

    
    
    /**
     * 按月付息，到期还本-计算获取还款方式为按月付息，到期还本的每月偿还利息
     * 
     * 公式：每月应还利息=总借款额*年利率÷还款月数
	 *
     * 
     * @param invest
     *            总借款额（贷款本金）
     * @param yearRate
     *            年利率
     * @param totalMonth
     *            还款总月数
     * @return 每月偿还利息
     */
    public static double getPerMonthInterests(double invest,double yearRate, int totalMonth) {
        BigDecimal monthIncome = new BigDecimal(invest).multiply(new BigDecimal(yearRate)).divide(new BigDecimal(totalMonth), 2, BigDecimal.ROUND_DOWN);
        return monthIncome.doubleValue();
    }
	
    
    /**
     * 按月付息，到期还本计算获取还款方式为按月付息，到期还本的每月偿还利息，最后一个月归还利息+本金
     * 
     * 公式：每月偿还利息=(总借款额*年利率÷还款月数)
     * 
     * @param invest
     *            总借款额（贷款本金）
     * @param yearRate
     *            年利率
     * @param totalMonth
     *            还款总月数
     * @return 每月偿还利息,不四舍五入，直接截取小数点最后两位
     */
    public static Map<Integer, Double> getPerMonthPrincipalInterests(double invest, double yearRate, int totalMonth) {
        Map<Integer, Double> map = new HashMap<Integer, Double>();
        // 每月利息
        double monthPri = getPerMonthInterests(invest,yearRate, totalMonth);
        for (int i = 1; i <= totalMonth; i++) {
        	if(i == totalMonth){
        		//最后一期 利息+ 本金
        		monthPri = monthPri + invest ;
        	}
            map.put(i, monthPri);
        }
        return map;
    }

	/** 随借随还
	 * @param amount 本金
	 * @param annualInterestRate 年利率
	 * @param termCount 还款期限（天数）
	 * @return 还款金额 = 本金 + 本金*日利率*天数
	 * @throws Exception
	 */
	public static BigDecimal payOff(BigDecimal amount, BigDecimal annualInterestRate, int termCount) throws Exception {
		annualInterestRate = annualInterestRate.multiply(new BigDecimal(100));
		BigDecimal dailyInterest = LoanUtil.getDayInterestByYear(annualInterestRate);
		BigDecimal multiply = amount.multiply(dailyInterest).multiply(new BigDecimal(termCount));
		return multiply.add(amount).setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}

	/**
	 * 展期费用=本金*费率
	 * @param amount 本金
	 * @param continuationRate 展期利率
	 * @return
	 * @throws Exception
	 */
	public static BigDecimal getContinuationAmount(BigDecimal amount, BigDecimal continuationRate) throws Exception {
		return amount.multiply(continuationRate).setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}

	/**
	 * 逾期费用=本金*逾期费率*时间（天）
	 * @param amount 本金
	 * @param overdueRate 利率
	 * @param days days
	 * @return
	 * @throws Exception
	 */
	public static BigDecimal getOverdueAmount(BigDecimal amount, BigDecimal overdueRate ,BigDecimal days) throws Exception {
		return amount.multiply(overdueRate).multiply(days).setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}
	
    public static BigDecimal getBigDecimal(Object value) {
        BigDecimal ret = null;
        if (value != null) {
            if (value instanceof BigDecimal) {
                ret = (BigDecimal) value;
            } else if (value instanceof String) {
                ret = new BigDecimal((String) value);
            } else if (value instanceof BigInteger) {
                ret = new BigDecimal((BigInteger) value);
            } else if (value instanceof Number) {
                ret = new BigDecimal(((Number) value).doubleValue());
            } else {
                throw new ClassCastException("Not possible to coerce [" + value + "] from class " + value.getClass() + " into a BigDecimal.");
            }
        }
        return ret;
    }

	public static void main(String[] args) {
		int m = 360;
		BigDecimal a = new BigDecimal(1000000);
		BigDecimal r = new BigDecimal(0.049);

		BigDecimal l = null;
		try {

			//等额本息每月还款金额
//			 l = calculateAverageCapitalPlusInterest(360,new BigDecimal(1000000),new BigDecimal(0.049));
			//等额本金每月还款金额
//			Map<Integer, Double> perMonthPrincipalInterest = getPerMonthPrincipalInterest(1000000, 0.049, 360);
//			for (Integer integer : perMonthPrincipalInterest.keySet()) {
//				System.out.println(integer+"--"+perMonthPrincipalInterest.get(integer));
//			}
			//等额本金总利息（存在差异60元）
			double interestCount = getInterestCount(1000000, 0.049, 360);
			System.out.println(interestCount);
			//等额本金每月利息
//			Map<Integer, Double> perMonthPrincipalInterest = getPerMonthInterest(100000, 0.049, 60);
//			for (Integer integer : perMonthPrincipalInterest.keySet()) {
//				System.out.println(integer+"--"+perMonthPrincipalInterest.get(integer));
//			}
			//一次性还本付息,利息和本金所得
//			l = calculateOnetimeRepayInterest(m,1, a,new BigDecimal("0.05"));
			//利随本清方式利息总计
//			l = calculateInterest(new BigDecimal(1000000), new BigDecimal(0.049), 1, 2);
			//按月付息到期还本的每月利息
//			double perMonthInterests = getPerMonthInterests(100, 0.049, 12);
//			System.out.println(perMonthInterests);
			//按月付息到期还本的每月还款金额
//			Map<Integer, Double> perMonthPrincipalInterests = getPerMonthPrincipalInterests(100, 0.049, 12);
//			for (Integer integer : perMonthPrincipalInterests.keySet()) {
//				System.out.println(integer+"--"+perMonthPrincipalInterests.get(integer));
//			}
			//随借随还，偿还的金额
//			l = payOff(new BigDecimal(100), new BigDecimal(0.049), 30);
			//展期总费用
//			BigDecimal continuationAmount = getContinuationAmount(new BigDecimal(100000), new BigDecimal(0.049));
//			System.out.println("展期总费用"+continuationAmount);
			//逾期总费用
//			BigDecimal overdueAmount = getOverdueAmount(new BigDecimal(100000), new BigDecimal(0.049),new BigDecimal(10));
//			System.out.println("逾期总费用"+overdueAmount);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(l);
	}
}
