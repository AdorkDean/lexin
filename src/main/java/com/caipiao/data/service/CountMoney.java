
package com.caipiao.data.service;

import com.caipiao.utils.PlayType;
import com.caipiao.utils.TryStatic;
import com.sysbcjzh.utils.CheckUtil;
import org.apache.commons.lang.ArrayUtils;

/**
 * 计算资金
 */
public class CountMoney
{

	static int ZLHZ[] = {
			0, 0, 0, 1, 1, 2, 3, 4, 5, 7,
			8, 9, 10, 10, 10, 10, 9, 8, 7, 5,
			4, 3, 2, 1, 1
	};
	static int ZSHZ[] = {
			0, 1, 2, 1, 3, 3, 3, 4, 5, 4,
			5, 5, 4, 5, 5, 4, 5, 5, 4, 5,
			4, 3, 3, 3, 1, 2, 1
	};
	static int EXHZ[] = {
			1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
			9, 8, 7, 6, 5, 4, 3, 2, 1
	};
	static int EZBD[] = {
			1, 1, 2, 2, 3, 3, 4, 4, 5, 5,
			5, 4, 4, 3, 3, 2, 2, 1, 1
	};
	static int SXHZ[] = {
			1, 3, 6, 10, 15, 21, 28, 36, 45, 55,
			63, 69, 73, 75, 75, 73, 69, 63, 55, 45,
			36, 28, 21, 15, 10, 6, 3, 1
	};
	static int SZBD[] = {
			1, 1, 2, 3, 4, 5, 7, 8, 10, 12,
			13, 14, 15, 15, 15, 15, 14, 13, 12, 10,
			8, 7, 5, 4, 3, 2, 1, 1
	};

	public CountMoney()
	{
	}

	public static Double getAllMoney(int zhushu, int[] beishu)
	{
		Double result = Double.valueOf(2.0D);
		int allbeishu = 0;
		for (int i : beishu) {
			allbeishu += i;
		}
		return Double.valueOf(result.doubleValue() * zhushu * allbeishu);
	}

	public static int getAllZhushu(String codes, String lot)
	{
		int zhushu = 0;
		String[] split = codes.split("#");
		for (String str : split) {
			int one = getZhushu(str, lot);
			if (one > 0)
				zhushu += one;
			else {
				return 0;
			}
		}
		return zhushu;
	}

	private static int getZhushu(String code, String lot)
	{
		int zs = 0;
		if ("Cqssc".equals(lot) || "Jxssc".equals(lot) || "Hnssc".equals(lot) || "Ynssc".equals(lot))
			zs = _Ssc(code);
		else
		if ("Sd11x5".equals(lot) || "Jx11x5".equals(lot) || "Gd11x5".equals(lot) || "Cq11x5".equals(lot) || "Sh11x5".equals(lot))
			zs = _11x5(code);
		else
		if ("Ssq".equals(lot) || "Dlt".equals(lot) || "Pl5".equals(lot) || "Fc3d".equals(lot) || "Pl3".equals(lot))
			zs = _Def(code);
		else
		if ("Jsk3".equals(lot) || "Gxk3".equals(lot) || "Ahk3".equals(lot))
			zs = _Kuai3(code);
		return zs;
	}

	private static int _Def(String code)
	{
		int zs = 0;
		String co[] = code.split(":");
		String type = co[0];
		String split[] = co[1].split(",");
		if (ArrayUtils.contains(PlayType._Def_ZuXuan, type))
		{
			if (CheckUtil.Regex("[0-9](,[0-9]){1,9}", co[1]))
			{
				int len = split.length;
				int base = 0;
				int ddbb = 1;
				if (type.equals(PlayType.T114))
				{
					ddbb = 2;
					base = 2;
				} else
				if (type.equals(PlayType.T117))
					base = 3;
				zs = Combination(len, base) * ddbb;
			}
			return zs;
		}
		if (ArrayUtils.contains(PlayType._Def_HeZhi, type))
		{
			for (int i = 0; i < split.length; i++)
			{
				int s = TryStatic.StrToInt(split[i], 0);
				if (type.equals(PlayType.T113))
					zs += SXHZ[s];
				else
				if (type.equals(PlayType.T115))
					zs += ZSHZ[s];
				else
				if (type.equals(PlayType.T118))
					zs += ZLHZ[s];
			}

			return zs;
		}
		if (ArrayUtils.contains(PlayType._Def_DanTuo, type))
		{
			if (CheckUtil.Regex("[0-9](,[0-9]){0,1}[$][0-9](,[0-9]){1,9}", co[1]))
			{
				String cod[] = co[1].split("\\$");
				int l0 = cod[0].split(",").length;
				int l1 = 0;
				try
				{
					l1 = cod[1].split(",").length;
				}
				catch (Exception exception) { }
				int ddmp = 0;
				int numbase = 0;
				if (type.equals(PlayType.T116))
				{
					if (l0 == 1)
					{
						ddmp = 2;
						numbase = 2;
					}
				} else
				if (type.equals(PlayType.T119) && (l0 == 1 || l0 == 2))
				{
					ddmp = 1;
					numbase = 3;
				}
				zs = Combination(l1, numbase - l0) * ddmp;
			}
			return zs;
		}
		zs = 1;
		String as[];
		int k = (as = split).length;
		for (int j = 0; j < k; j++)
		{
			String str = as[j];
			zs *= str.length();
		}

		return zs;
	}

	private static int _Ssc(String code)
	{
		int zs = 0;
		String co[] = code.split(":");
		String type = co[0];
		String split[] = co[1].split(",");
		if (ArrayUtils.contains(PlayType._Ssc_HeZhi, type))
		{
			for (int i = 0; i < split.length; i++)
			{
				int s = TryStatic.StrToInt(split[i], 0);
				if (type.equals(PlayType.T303))
					zs += EXHZ[s];
				else
				if (type.equals(PlayType.T306))
					zs += EZBD[s];
				else
				if (type.equals(PlayType.T309))
					zs += SXHZ[s];
				else
				if (type.equals(PlayType.T312))
					zs += SZBD[s];
			}

			return zs;
		}
		if (ArrayUtils.contains(PlayType._Ssc_ZuXuan, type))
		{
			if (CheckUtil.Regex("[0-9](,[0-9]){1,9}", co[1]))
			{
				int len = split.length;
				int base = 0;
				int ddbb = 1;
				if (type.equals(PlayType.T304))
				{
					base = 2;
					if (len < 2 || len > 7)
						return 0;
				} else
				if (type.equals(PlayType.T310))
				{
					ddbb = 2;
					base = 2;
				} else
				if (type.equals(PlayType.T311))
					base = 3;
				else
				if (type.equals(PlayType.T314))
				{
					base = 3;
					ddbb = 6;
					if (len < 3)
						return 0;
				}
				zs = Combination(len, base) * ddbb;
			}
			return zs;
		}
		if (ArrayUtils.contains(PlayType._Ssc_ChangDu, type))
		{
			if (CheckUtil.Regex("[0-9](,[0-9]){0,9}", co[1]))
			{
				int len = split.length;
				if (type.equals(PlayType.T307))
					zs = len * 10;
				else
				if (type.equals(PlayType.T313))
					zs = len * 55;
			}
			return zs;
		}
		if (type.equals(PlayType.T318) || type.equals(PlayType.T319) || type.equals(PlayType.T301))
		{
			int l0 = split[0].replace("-", "").length();
			int l1 = split[1].replace("-", "").length();
			int l2 = split[2].replace("-", "").length();
			int l3 = split[3].replace("-", "").length();
			int l4 = split[4].replace("-", "").length();
			if (type.equals(PlayType.T318) || type.equals(PlayType.T301))
				zs = l0 + l1 + l2 + l3 + l4;
			else
			if (type.equals(PlayType.T319))
				zs = l0 * (l1 + l2 + l3 + l4) + l1 * (l2 + l3 + l4) + l2 * (l3 + l4) + l3 * l4;
			return zs;
		}
		if (ArrayUtils.contains(PlayType._Ssc_DanTuo, type))
		{
			if (CheckUtil.Regex("[0-9](,[0-9]){0,1}[$][0-9](,[0-9]){1,9}", co[1]))
			{
				String cod[] = co[1].split("\\$");
				int l0 = cod[0].split(",").length;
				int l1 = 0;
				try
				{
					l1 = cod[1].split(",").length;
				}
				catch (Exception exception) { }
				int ddmp = 0;
				int numbase = 0;
				if (type.equals(PlayType.T320))
				{
					if (l0 == 1)
					{
						ddmp = 2;
						numbase = 2;
					}
				} else
				if (type.equals(PlayType.T321) && (l0 == 1 || l0 == 2))
				{
					ddmp = 1;
					numbase = 3;
				}
				zs = Combination(l1, numbase - l0) * ddmp;
			}
			return zs;
		}
		zs = 1;
		String as[];
		int k = (as = split).length;
		for (int j = 0; j < k; j++)
		{
			String str = as[j];
			zs *= str.length();
		}

		return zs;
	}

	private static int _11x5(String code)
	{
		int zs = 0;
		String co[] = code.split(":");
		String type = co[0];
		String split[] = co[1].split(",");
		if (ArrayUtils.contains(PlayType._11x5_ZuXuan, type))
		{
			if (CheckUtil.Regex("[0-1][0-9](,[0-1][0-9]){1,10}", co[1]))
			{
				int len = split.length;
				int base = 0;
				int ddbb = 1;
				if (type.equals(PlayType.T355) || type.equals(PlayType.T352))
					base = 2;
				else
				if (type.equals(PlayType.T356) || type.equals(PlayType.T354))
					base = 3;
				else
				if (type.equals(PlayType.T357))
					base = 4;
				else
				if (type.equals(PlayType.T358))
					base = 5;
				else
				if (type.equals(PlayType.T359))
					base = 6;
				else
				if (type.equals(PlayType.T360))
					base = 7;
				else
				if (type.equals(PlayType.T361))
					base = 8;
				zs = Combination(len, base) * ddbb;
			}
			return zs;
		}
		if (ArrayUtils.contains(PlayType._11x5_DanTuo, type))
		{
			if (CheckUtil.Regex("[0-1][0-9](,[0-1][0-9]){0,6}[$][0-1][0-9](,[0-1][0-9]){0,10}", co[1]))
			{
				String cod[] = co[1].split("\\$");
				int l0 = cod[0].split(",").length;
				int l1 = 0;
				try
				{
					l1 = cod[1].split(",").length;
				}
				catch (Exception exception) { }
				int max = 0;
				if (type.equals(PlayType.T364) || type.equals(PlayType.T362))
					max = 2;
				else
				if (type.equals(PlayType.T365) || type.equals(PlayType.T363))
					max = 3;
				else
				if (type.equals(PlayType.T366))
					max = 4;
				else
				if (type.equals(PlayType.T367))
					max = 5;
				else
				if (type.equals(PlayType.T368))
					max = 6;
				else
				if (type.equals(PlayType.T369))
					max = 7;
				else
				if (type.equals(PlayType.T370))
					max = 8;
				if (l0 < max && l0 > 0 && l0 + l1 <= 11)
					zs = Combination(l1, max - l0);
			}
			return zs;
		}
		if (type.equals(PlayType.T350))
		{
			if (CheckUtil.Regex("[0-1][0-9](\\s[0-1][0-9]){0,10}", co[1]))
			{
				String split2[] = co[1].split(" ");
				zs = split2.length;
			}
			return zs;
		}
		if (type.equals(PlayType.T351))
		{
			zs = 0;
			if (CheckUtil.Regex("([0-1][0-9](\\s[0-1][0-9]){0,10}),([0-1][0-9](\\s[0-1][0-9]){0,10})", co[1]) && split.length == 2)
			{
				String tt1[] = split[0].split(" ");
				String tt2[] = split[1].split(" ");
				for (int i = 0; i < tt1.length; i++)
					if (tt1[i] != " ")
					{
						for (int k = 0; k < tt2.length; k++)
							if (tt2[k] != " " && !tt1[i].equals(tt2[k]))
								zs++;

					}

			}
			return zs;
		}
		if (type.equals(PlayType.T353))
		{
			zs = 0;
			if (CheckUtil.Regex("([0-1][0-9](\\s[0-1][0-9]){0,10}),([0-1][0-9](\\s[0-1][0-9]){0,10}),([0-1][0-9](\\s[0-1][0-9]){0,10})", co[1]) && split.length == 3)
			{
				String tt1[] = split[0].split(" ");
				String tt2[] = split[1].split(" ");
				String tt3[] = split[2].split(" ");
				for (int i = 0; i < tt1.length; i++)
					if (tt1[i] != " ")
					{
						for (int k = 0; k < tt2.length; k++)
							if (tt2[k] != " ")
							{
								for (int n = 0; n < tt3.length; n++)
									if (tt3[n] != " " && !tt1[i].equals(tt3[n]) && !tt2[k].equals(tt3[n]) && !tt1[i].equals(tt2[k]))
										zs++;

							}

					}

			}
			return zs;
		} else
		{
			return 0;
		}
	}

	private static int _Kuai3(String code)
	{
		int zs = 0;
		String co[] = code.split(":");
		String type = co[0];
		String split[] = co[1].split(",");
		if (type.equals(PlayType.T400) || type.equals(PlayType.T402) || type.equals(PlayType.T401) || type.equals(PlayType.T405) || type.equals(PlayType.T406))
		{
			int lth = split.length;
			if (CheckUtil.Regex("(3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18)(,(3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18)){0,15}", co[1]) && type.equals(PlayType.T400))
				return lth;
			if (type.equals(PlayType.T402) && CheckUtil.Regex("(111|222|333|444|555|666)(,(111|222|333|444|555|666)){0,5}", co[1]))
				return lth;
			if (type.equals(PlayType.T401) && co[1].equals("三同号通选"))
				return 1;
			if (type.equals(PlayType.T405) && co[1].equals("三连号通选"))
				return 1;
			if (type.equals(PlayType.T406) && CheckUtil.Regex("(11|22|33|44|55|66)(,(11|22|33|44|55|66)){0,5}", co[1]))
				return lth;
			else
				return 0;
		}
		if (type.equals(PlayType.T403) || type.equals(PlayType.T408))
		{
			if (!CheckUtil.Regex("[1-6](,[1-6]){1,5}", co[1]))
				return 0;
			int n = split.length;
			int ddbase = 2;
			if (type.equals(PlayType.T403))
				ddbase = 3;
			return Combination(n, ddbase);
		}
		if (type.equals(PlayType.T410) || type.equals(PlayType.T409))
		{
			if (!CheckUtil.Regex("[1-6](,[1-6]){0,1}[$][1-6](,[1-6]){0,5}", co[1]))
				return 0;
			String cod[] = co[1].split("\\$");
			int l0 = cod[0].split(",").length;
			int l1 = cod[1].split(",").length;
			if (type.equals(PlayType.T409))
			{
				if (l0 == 1)
				{
					int numbase = 2;
					zs = Combination(l1, numbase - l0);
				}
			} else
			if (type.equals(PlayType.T410) && (l0 == 1 || l0 == 2))
			{
				int numbase = 3;
				zs = Combination(l1, numbase - l0);
			}
			return zs;
		}
		if (type.equals(PlayType.T407))
		{
			if (!CheckUtil.Regex("(11|22|33|44|55|66)(,(11|22|33|44|55|66)){0,4}[$][1-6](,[1-6]){0,4}", co[1]))
				return 0;
			split = co[1].split("\\$");
			zs = 0;
			if (split.length == 2)
			{
				String tt1[] = split[0].split(",");
				String tt2[] = split[1].split(",");
				for (int i = 0; i < tt1.length; i++)
					if (tt1[i].trim() != "")
					{
						for (int k = 0; k < tt2.length; k++)
							if (tt2[k].trim() != "" && !tt1[i].equals((new StringBuilder(String.valueOf(tt2[k]))).append(tt2[k]).toString()))
								zs++;

					}

			}
			return zs;
		} else
		{
			return zs;
		}
	}

	private static int Combination(int n, int m)
	{
		int n1 = 1;
		int n2 = 1;
		int i = n;
		for (int j = 1; j <= m;)
		{
			n1 *= i--;
			n2 *= j++;
		}

		return n1 / n2;
	}

}
