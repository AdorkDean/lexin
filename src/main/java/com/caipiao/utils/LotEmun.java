package com.caipiao.utils;

public enum LotEmun
{
	Cqssc("Cqssc", 1, "重庆时时彩"),
	Jxssc("Jxssc", 1, "江西时时彩"),
	Hnssc("Hnssc", 1, "河内五分彩"),
	Ynssc("Ynssc", 1, "印尼五分彩"),
	Sd11x5("Sd11x5", 1, "十一运夺金"),
	Jx11x5("Jx11x5", 1, "江西11选5"),
	Gd11x5("Gd11x5", 1, "广东11选5"),
	Cq11x5("Cq11x5", 1, "重庆11选5"),
	Sh11x5("Sh11x5", 1, "上海11选5"),
	Jsk3("Jsk3", 1, "江苏快三"),
	Jlk3("Jlk3", 1, "吉林快3"),
	Gxk3("Gxk3", 1, "广西快3"),
	Ahk3("Ahk3", 1, "安徽快3"),

	Ssq("Ssq", 0, "双色球"),
	Dlt("Dlt", 0, "大乐透"),
	Fc3d("Fc3d", 0, "福彩3D"),
	Pl3("Pl3", 0, "排列三"),
	Pl5("Pl5", 0, "排列五");

	public String name;
	public int type;
	public String namestr;

	private LotEmun(String name, int type, String namestr)
	{
		this.name = name;
		this.type = type;
		this.namestr = namestr;
	}
}