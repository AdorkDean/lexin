package com.caipiao.data.open.crawler;

import com.caipiao.utils.CharSetUtil;
import com.caipiao.utils.Log;
import com.caipiao.utils.TimeUtil;
import com.sysbcjzh.utils.StringUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetOpenNumber
{
	public static void main(String[] args)
	{
//		HashMap getH = GetGd11x5(2);
//		System.out.println(getH);
//		System.out.println(GetJxssc(2));
		System.out.println(GetCqssc(2));
//		System.out.println(GetGd11x5(2));
//		HashMap hashMap = GetHnssc(0);


//		String html = D_HtmlCrawler.get360Html("http://www.63hc.com/lottery/Buy!Hnssc.jzh" );
//		if ((StringUtils.isNotBlank(html)))
//		{
//			String today = TimeUtil.getToday("yyyy");
//			Elements select = Jsoup.parse(html).select(".index_table right_t_kj");
//
//			Element r_open = Jsoup.parse(html).getElementById("r_open");
//
//
//
//			for (Iterator iterator = select.iterator(); iterator.hasNext(); )
//			{
//				Element e = (Element)iterator.next();
//				String qihao = e.select("td:eq(0)").html();
//				String haoma = e.select("td:eq(1)").text();
//				if ((!StringUtils.isNotBlank(haoma)) || (!StringUtils.isNotBlank(qihao)) || (haoma.length() < 14))
//					continue;
//				haoma = haoma.trim();
//				if(haoma.equals("---")){
//					System.out.println(today + qihao + ":开奖中。。。");
//					continue;
//				}
//				qihao = qihao.trim();
//				System.out.println(today + qihao + "----" + haoma.replace(" ", ","));
//			}
//		}

	}


	/**
	 * 重庆时时彩
	 * @param i
	 * @return
     */
	public static HashMap GetCqssc(int i)
	{
		HashMap Result = new HashMap();
		long currentTimeMillis = System.currentTimeMillis();

		String html_caipiaokong = HtmlCrawler.getCaipiaokongYnssc("http://api.kaijiangtong.com/lottery/?name=cqssc&format=json&uid=475733&token=afc528e8ebfa3e95ab1e6cc632b55d1998f728cf");
		System.out.println("彩票控网站重庆时时彩的数据：" + html_caipiaokong);
		if (StringUtils.isNotBlank(html_caipiaokong))
		{

			processCaipiaokongData(Result, html_caipiaokong);

			return Result;

		}


		String html = HtmlCrawler.getWangyi163Html("http://caipiao.163.com/award/getAwardNumberInfo.html?gameEn=ssc&periodNum=10&cache=" + currentTimeMillis);
		if (StringUtils.isNotBlank(html))
		{
				process163JsonWinningNumber(Result, html);
		}
		else
		{
			html = D_HtmlCrawler.get360Html("http://cp.360.cn/ssccq/?&cache=" + currentTimeMillis);
			if ((StringUtils.isNotBlank(html)))
			{
				String today = TimeUtil.getToday("yyyyMMdd");
				Elements select = Jsoup.parse(html).select(".kpkjcode tr");
				for (Iterator iterator = select.iterator(); iterator.hasNext(); )
				{
					Element e = (Element)iterator.next();
					String qihao = e.select("td:eq(0)").html();
					String haoma = e.select("td:eq(1)").text();
					if ((!StringUtils.isNotBlank(haoma)) || (!StringUtils.isNotBlank(qihao)) || (haoma.length() < 9))
						continue;
					haoma = haoma.trim();
					qihao = qihao.trim();
					Result.put(today + qihao, haoma.replace(" ", ","));
				}

			}
			else {
				Log.ShowErr("重庆时时彩爬虫抓取号码错误！");
			}
		}
		return Result;
	}

	private static void processCaipiaokongData(HashMap result, String html_caipiaokong) {
		JSONObject jsonObject = JSONObject.fromObject(html_caipiaokong);
		JSONArray names = jsonObject.names();
		Iterator iterator = names.iterator();

		while (iterator.hasNext()){
            String qihao = iterator.next().toString();
            Object o = jsonObject.get(qihao);
            JSONObject haoma = JSONObject.fromObject(o);
            String number = haoma.get("number").toString();
            result.put(qihao,number);
            System.out.println(qihao + ":" + number);
        }
	}

	/**
	 *
	 * 官网已经停止开奖
	 * 江西时时彩
	 * @param i
	 * @return
     */
	public static HashMap GetJxssc(int i)
	{
		HashMap Result = new HashMap();
		long currentTimeMillis = System.currentTimeMillis();
		String html = D_HtmlCrawler.getHtml("http://cp.360.cn/sscjx/?&cache=" + currentTimeMillis);
		if ((StringUtils.isNotBlank(html)) && (i % 2 == 0))
		{
			String today = TimeUtil.getToday("yyyyMMdd");
			Elements select = Jsoup.parse(html).select(".kpkjcode tr");
			for (Iterator iterator = select.iterator(); iterator.hasNext(); )
			{
				Element e = (Element)iterator.next();
				String qihao = e.select("td:eq(0)").html();
				String haoma = e.select("td:eq(1)").text();
				if ((!StringUtils.isNotBlank(haoma)) || (!StringUtils.isNotBlank(qihao)) || (haoma.length() < 9))
					continue;
				haoma = haoma.trim();
				qihao = qihao.trim();
				Result.put(today + "0" + qihao, haoma.replace(" ", ","));
			}

		}
		else
		{
			html = D_HtmlCrawler.getHtml("http://caipiao.163.com/award/getAwardNumberInfo.html?gameEn=jxssc&periodNum=10&cache=" + currentTimeMillis);
			if (StringUtils.isNotBlank(html))
			{
				for (Matcher matcher = Pattern.compile("period.*?xingTai").matcher(html); matcher.find(); )
				{
					String finds = matcher.group();
					Matcher qihao = Pattern.compile("[0-9]{11}").matcher(finds);
					Matcher haoma = Pattern.compile("[0-9]\\s[0-9]\\s[0-9]\\s[0-9]\\s[0-9]").matcher(finds);
					if ((qihao.find()) && (haoma.find())) {
						Result.put(qihao.group(), haoma.group().replace(" ", ","));
					}
				}
			}
			else {
				Log.ShowErr("江西时时彩爬虫抓取号码错误！");
			}
		}
		return Result;
	}

	/**
	 * 河内五分彩
	 * @param i
	 * @return
     */
	public static HashMap GetHnssc(int i)
	{
		HashMap Result = new HashMap();
		long currentTimeMillis = System.currentTimeMillis();

		String html = HtmlCrawler.getCaipiaokongYnssc("http://api.kaijiangtong.com/lottery/?name=hnkw&format=json&uid=475733&token=afc528e8ebfa3e95ab1e6cc632b55d1998f728cf");
		System.out.println("彩票控网站河内五分彩的数据：" + html);
		if (StringUtils.isNotBlank(html))
		{

			processCaipiaokongData(Result, html);

		}

		else
		{
			html = D_HtmlCrawler.getHtml("http://draw.vietlotto.org/others/draw.php?v=" + currentTimeMillis);
			System.out.println("河内五分彩GetHnssc网站draw.vietlotto.org数据：" + html);
			if ((StringUtils.isNotBlank(html)) )
			{
				processSSC(Result, html);
			}
			else
			{
				Log.ShowErr("河内五分彩GetHnssc爬虫抓取号码错误！");
			}
		}
		return Result;
	}

	private static void processSSC(HashMap result, String html) {
		for (Matcher matcher = Pattern.compile("\\{\"num\":.+?\"\\}").matcher(html); matcher.find(); )
        {
            String finds = matcher.group();
            Matcher qihao = Pattern.compile("[0-9]{8}-[0-9]{1,3}").matcher(finds);
            Matcher haoma = Pattern.compile("[0-9],[0-9],[0-9],[0-9],[0-9]").matcher(finds);
            if ((!qihao.find()) || (!haoma.find()))
                continue;
            String qh = qihao.group().replace("-", "");
            if (10 == qh.length()) {
                qh = qh.substring(0, 8) + "0" + qh.substring(8);
            }
            else if (9 == qh.length())
                qh = qh.substring(0, 8) + "00" + qh.substring(8);
            result.put(qh, haoma.group());
        }
	}

	/**
	 * 印尼五分彩
	 * @param i
	 * @return
     */
	public static HashMap GetYnssc(int i)
	{
		HashMap Result = new HashMap();
		long currentTimeMillis = System.currentTimeMillis();
//		String html = D_HtmlCrawler.getHtml("http://123.57.212.200:8080/yini.jsp?v=" + currentTimeMillis);
//		if ((StringUtils.isNotBlank(html)) && (i % 2 == 0))
//		{
//			processSSC(Result, html);
//
//		}
//		else
//		{
			String html = D_HtmlCrawler.getHtml("http://draw.indonesia-lottery.org/others/draw.php?v=" + currentTimeMillis);
			if (StringUtils.isNotBlank(html))
			{
				System.out.println("印尼五分彩GetYnssc draw.indonesia-lottery.org数据：" + html);
				processSSC(Result, html);

			}
			else
			{
				Log.ShowErr("印尼五分彩GetYnssc爬虫抓取号码错误！");
			}
//		}
		return Result;
	}

	/**
	 * 山东11选5  就是十一夺运金
	 * @param i
	 * @return
     */
	public static HashMap GetSd11x5(int i)
	{
		HashMap Result = new HashMap();
		long currentTimeMillis = System.currentTimeMillis();
		String html = D_HtmlCrawler.get360Html("http://cp.360.cn/yun11/?&cache=" + currentTimeMillis);
		if ((StringUtils.isNotBlank(html)) && (i % 2 == 0))
		{
			process360cp11x5(Result,html);
		}
		else
		{
			html = D_HtmlCrawler.getHtml("http://aaxx254.w286-e1.ezwebtest.com/lecai.asp?l=11yun&v=" + currentTimeMillis);
			if (StringUtils.isNotBlank(html))
			{
				for (Matcher matcher = Pattern.compile("<tr>(.+?)</tr>").matcher(html); matcher.find(); )
				{
					String finds = matcher.group();
					Matcher qihao = Pattern.compile("[0-9]{8}").matcher(finds);
					Matcher haoma = Pattern.compile("[0-9]{2},[0-9]{2},[0-9]{2},[0-9]{2},[0-9]{2}").matcher(finds);
					if ((qihao.find()) && (haoma.find())) {
						Result.put("" + qihao.group(), haoma.group());
					}
				}
			}
			else {
				Log.ShowErr("山东11选5爬虫抓取号码错误！");
			}
		}
		return Result;
	}

	private static void process360cp11x5(HashMap result, String html) {

		String today = TimeUtil.getToday("yyyy");
		Elements select = Jsoup.parse(html).select(".kpkjcode tr");

		for (Iterator iterator = select.iterator(); iterator.hasNext(); )
        {
            Element e = (Element)iterator.next();
            String qihao = e.select("td.issgray").html();
            String haoma = e.select("td:eq(1)").text();
            if ((!StringUtils.isNotBlank(haoma)) || (!StringUtils.isNotBlank(qihao)) || (haoma.length() < 14))
                continue;
            haoma = haoma.trim();
			if(haoma.equals("---")){
				System.out.println(today + qihao + ":开奖中。。。");
				continue;
			}
            qihao = qihao.trim();
            result.put(today + qihao, haoma.replace(" ", ","));
        }
	}

	/**
	 * 江西11选5
	 * @param i
	 * @return
     */
	public static HashMap GetJx11x5(int i)
	{
		HashMap Result = new HashMap();
		long currentTimeMillis = System.currentTimeMillis();
		String html = D_HtmlCrawler.get360Html("http://cp.360.cn/dlcjx/?menu&r_a=nuIBry&cache=" + currentTimeMillis);
		if ((StringUtils.isNotBlank(html)) && (i % 2 == 0))
		{
			process360cp11x5(Result, html);
		}
		else
		{
			html = D_HtmlCrawler.getWangyi163Html("http://caipiao.163.com/award/getAwardNumberInfo.html?gameEn=jxd11&periodNum=10&cache=" + currentTimeMillis);
			if (StringUtils.isNotBlank(html))
			{
				for (Matcher matcher = Pattern.compile("period.*?xingTai").matcher(html); matcher.find(); )
				{
					String finds = matcher.group();
					Matcher qihao = Pattern.compile("[0-9]{8}").matcher(finds);
					Matcher haoma = Pattern.compile("[0-1][0-9]\\s[0-1][0-9]\\s[0-1][0-9]\\s[0-1][0-9]\\s[0-1][0-9]").matcher(finds);
					if ((qihao.find()) && (haoma.find())) {
						Result.put("20" + qihao.group(), haoma.group().replace(" ", ","));
					}
				}
			}
			else {
				Log.ShowErr("江西11选5爬虫抓取号码错误！");
			}
		}
		return Result;
	}

	/**
	 * 广东11选5
	 * @param i
	 * @return
     */
	public static HashMap GetGd11x5(int i)
	{
		HashMap Result = new HashMap();
		long currentTimeMillis = System.currentTimeMillis();
		String html = HtmlCrawler.getWangyi163Html("http://caipiao.163.com/award/getAwardNumberInfo.html?gameEn=gdd11&periodNum=10&cache=" + System.currentTimeMillis());
		if (StringUtils.isNotBlank(html))
		{
			process163JsonWinningNumber(Result, html);
		}
		else
		{
			html = D_HtmlCrawler.get360Html("http://cp.360.cn/gd11/?r_a=a6RFrm&cache=" + currentTimeMillis);
			if ((StringUtils.isNotBlank(html)) && (i % 2 == 0))
			{
				process360cp11x5(Result,html);
			}
//			html = D_HtmlCrawler.getHtml("http://aaxx254.w286-e1.ezwebtest.com/lecai.asp?l=Gd11x5&v=" + currentTimeMillis);
//			if (StringUtils.isNotBlank(html))
//			{
//				for (Matcher matcher = Pattern.compile("<tr>(.+?)</tr>").matcher(html); matcher.find(); )
//				{
//					String finds = matcher.group();
//					Matcher qihao = Pattern.compile("[0-9]{8}").matcher(finds);
//					Matcher haoma = Pattern.compile("[0-9]{2},[0-9]{2},[0-9]{2},[0-9]{2},[0-9]{2}").matcher(finds);
//					if ((qihao.find()) && (haoma.find())) {
//						Result.put("20" + qihao.group(), haoma.group());
//					}
//				}
//			}
			else {
				Log.ShowErr("广东11选5爬虫抓取号码错误！");
			}
		}
		return Result;
	}

	/**
	 * 对网易彩票的json数据新的解析
	 * @param result
	 * @param html
     */
	private static void process163JsonWinningNumber(HashMap result, String html) {
		JSONObject jsonObject = JSONObject.fromObject(html);
		JSONArray awardNumberInfoList = jsonObject.getJSONArray("awardNumberInfoList");
		for (Object object : awardNumberInfoList) {
            JSONObject awardNumberInfo = JSONObject.fromObject(object);
            Object period = awardNumberInfo.get("period");
            Object winningNumber = awardNumberInfo.get("winningNumber");

            if(period != null && winningNumber != null){

				if(("等待开奖中").equals(winningNumber)){
					System.out.println("20"+period.toString() + "等待开奖中");
				}else{
					result.put("20"+period.toString(), winningNumber.toString().replace(" ", ","));
				}

            }
        }
	}

	/**
	 * 重庆11选5    官网已经不再开奖
	 * @param i
	 * @return
     */
	public static HashMap GetCq11x5(int i)
	{
		HashMap Result = new HashMap();
		long currentTimeMillis = System.currentTimeMillis();
		String html = D_HtmlCrawler.get360Html("http://cp.360.cn/sh11/??v=" + currentTimeMillis);
		if ((StringUtils.isNotBlank(html)) && (i % 2 == 0))
		{
			process360cp11x5(Result,html);
		}
		else
		{
			html = D_HtmlCrawler.getHtml("http://trade.11x5w.com/static/public/dsh/xml/newlyopenlist.xml");
			if (StringUtils.isNotBlank(html))
			{
				process11x5(Result, html);
			}
			else {
				Log.ShowErr("重庆11选5爬虫抓取号码错误！");
			}
		}
		return Result;
	}

	private static void process11x5(HashMap result, String html) {
		for (Matcher matcher = Pattern.compile("<row[^/]*/>").matcher(html); matcher.find(); )
        {
            String finds = matcher.group();
            Matcher qihao = Pattern.compile("[0-9]{8}").matcher(finds);
            Matcher haoma = Pattern.compile("[0-9]{2},[0-9]{2},[0-9]{2},[0-9]{2},[0-9]{2}").matcher(finds);
            if ((qihao.find()) && (haoma.find())) {
                result.put("20" + qihao.group(), haoma.group());
            }
        }
	}

	/**
	 * 上海11选5  网站没有上线
	 *
	 * @param i
	 * @return
     */
	public static HashMap GetSh11x5(int i)
	{
		HashMap Result = new HashMap();
		long currentTimeMillis = System.currentTimeMillis();
		String html = D_HtmlCrawler.get360Html("http://cp.360.cn/sh11/??v=" + currentTimeMillis);
		if ((StringUtils.isNotBlank(html)) && (i % 2 == 0))
		{
			process360cp11x5(Result, html);
		}
		else
		{
			html = D_HtmlCrawler.getHtml("http://trade.11x5w.com/static/public/dsh/xml/newlyopenlist.xml");
			if (StringUtils.isNotBlank(html))
			{
				process11x5(Result, html);
			}
			else {
				Log.ShowErr("上海11选5爬虫抓取号码错误！");
			}
		}
		return Result;
	}

	/**
	 * 江苏快3   网站没有上线
	 * @param i
	 * @return
     */
	public static HashMap GetJsk3(int i)
	{
		HashMap Result = new HashMap();
		long currentTimeMillis = System.currentTimeMillis();
		String html = D_HtmlCrawler.getHtml("http://cp.360.cn/k3js/?&cache=" + currentTimeMillis);
		if ((StringUtils.isNotBlank(html)) && (i % 2 == 0))
		{
			String today = TimeUtil.getToday("yyyyMMdd");
			Elements select = Jsoup.parse(html).select(".kpkjcode tr");
			processK3(Result, today, select);

		}
		else
		{
			html = D_HtmlCrawler.getHtml("http://caipiao.163.com/order/preBet_moreAwardNumberInfoForKuai3.html?gameId=2012112609YX00000002&cache=" + currentTimeMillis);
			if (StringUtils.isNotBlank(html))
			{
				for (Matcher matcher = Pattern.compile("period.*?winningNumberForm").matcher(html); matcher.find(); )
				{
					String finds = matcher.group();
					Matcher qihao = Pattern.compile("[0-9]{9}").matcher(finds);
					Matcher haoma = Pattern.compile("[0-9]{1}\\s[0-9]{1}\\s[0-9]{1}").matcher(finds);
					if ((qihao.find()) && (haoma.find())) {
						Result.put("20" + qihao.group(), haoma.group().replace(" ", ","));
					}
				}
			}
			else {
				Log.ShowErr("江苏快3爬虫抓取号码错误！");
			}
		}
		return Result;
	}

	private static void processK3(HashMap result, String today, Elements select) {
		for (Iterator iterator = select.iterator(); iterator.hasNext(); )
        {
            Element e = (Element)iterator.next();
            String qihao = e.select("td:eq(0)").html();
            String haoma = e.select("td:eq(1)").text();
            if ((!StringUtils.isNotBlank(haoma)) || (!StringUtils.isNotBlank(qihao)) || (haoma.length() < 5))
                continue;
            haoma = haoma.trim();
            qihao = qihao.trim();
            result.put(today + "0" + qihao.substring(4), haoma.replace(" ", ","));
        }
	}

	/**
	 * 吉林快3     网站没有上线
	 * @param i
	 * @return
     */
	public static HashMap GetJlk3(int i)
	{
		HashMap Result = new HashMap();
		long currentTimeMillis = System.currentTimeMillis();
		String html = D_HtmlCrawler.getHtml("http://www.cailele.com/static/jlk3/newlyopenlist.html?v=" + currentTimeMillis);
		if ((StringUtils.isNotBlank(html)) && (i % 2 == 0))
		{
			String today = TimeUtil.getToday("yyyy");
			for (Matcher matcher = Pattern.compile("<tr>(.+?)</tr>").matcher(html); matcher.find(); )
			{
				String finds = matcher.group();
				Matcher qihao = Pattern.compile("[0-9]{7}").matcher(finds);
				Matcher haoma = Pattern.compile("[0-9]{1},[0-9]{1},[0-9]{1}").matcher(finds);
				if ((qihao.find()) && (haoma.find()))
					Result.put(today + qihao.group(), haoma.group());
			}
		}
		else
		{
			html = D_HtmlCrawler.getHtml("http://caipiao.163.com/order/preBet_kuai3PeriodTime.html?cache=" + currentTimeMillis);
			if (StringUtils.isNotBlank(html))
			{
				for (Matcher matcher = Pattern.compile("period.*?winningNumberForm").matcher(html); matcher.find(); )
				{
					String finds = matcher.group();
					Matcher qihao = Pattern.compile("[0-9]{9}").matcher(finds);
					Matcher haoma = Pattern.compile("[0-9]{1}\\s[0-9]{1}\\s[0-9]{1}").matcher(finds);
					if ((qihao.find()) && (haoma.find())) {
						Result.put("20" + qihao.group(), haoma.group().replace(" ", ","));
					}
				}
			}
			else {
				Log.ShowErr("吉林快3爬虫抓取号码错误！");
			}
		}
		return Result;
	}

	/**
	 * 广西快3   网站暂时没有上线
	 * @param i
	 * @return
     */
	public static HashMap GetGxk3(int i)
	{
		HashMap Result = new HashMap();
		long currentTimeMillis = System.currentTimeMillis();
		String html = D_HtmlCrawler.getHtml("http://caipiao.163.com/order/preBet_gxkuai3PeriodTime.html?cache=" + currentTimeMillis);
		if ((StringUtils.isNotBlank(html)) && (i % 2 == 0))
		{
			for (Matcher matcher = Pattern.compile("period.*?winningNumberForm").matcher(html); matcher.find(); )
			{
				String finds = matcher.group();
				Matcher qihao = Pattern.compile("[0-9]{11}").matcher(finds);
				Matcher haoma = Pattern.compile("[0-9]{1}\\s[0-9]{1}\\s[0-9]{1}").matcher(finds);
				if ((qihao.find()) && (haoma.find()))
					Result.put(qihao.group(), haoma.group().replace(" ", ","));
			}
		}
		else
		{
			html = D_HtmlCrawler.getHtml("http://cp.360.cn/k3gx/?r_a=nmMzQz&cache=" + currentTimeMillis);
			if (StringUtils.isNotBlank(html))
			{
				String today = TimeUtil.getToday("yyyyMMdd");
				Elements select = Jsoup.parse(html).select(".kpkjcode tr");
				processK3(Result, today, select);

			}
			else
			{
				Log.ShowErr("广西快3爬虫抓取号码错误！");
			}
		}
		return Result;
	}

	/**
	 * 安徽快3    网站暂时没有上线
	 * @param i
	 * @return
     */
	public static HashMap GetAhk3(int i)
	{
		HashMap Result = new HashMap();
		long currentTimeMillis = System.currentTimeMillis();
		String html = D_HtmlCrawler.getHtml("http://caipiao.163.com/order/preBet_moreAwardNumberInfoForKuai3.html?gameId=2015020610YX17964203&cache=" + currentTimeMillis);
		if ((StringUtils.isNotBlank(html)) && (i % 2 == 0))
		{
			for (Matcher matcher = Pattern.compile("period.*?winningNumberForm").matcher(html); matcher.find(); )
			{
				String finds = matcher.group();
				Matcher qihao = Pattern.compile("[0-9]{9}").matcher(finds);
				Matcher haoma = Pattern.compile("[0-9]{1}\\s[0-9]{1}\\s[0-9]{1}").matcher(finds);
				if ((qihao.find()) && (haoma.find()))
					Result.put("20" + qihao.group(), haoma.group().replace(" ", ","));
			}
		}
		else
		{
			html = D_HtmlCrawler.getHtml("http://www.wozhongla.com/sp2/act/data.resultsscTen.htm?type=024&d=" + currentTimeMillis);
			if (StringUtils.isNotBlank(html))
			{
				for (Matcher matcher = Pattern.compile("(\\{[^{}]*\\})+").matcher(html); matcher.find(); )
				{
					String finds = matcher.group();
					Matcher qihao = Pattern.compile("[0-9]{8}").matcher(finds);
					Matcher haoma = Pattern.compile("[0-9]{1},[0-9]{1},[0-9]{1}").matcher(finds);
					if ((!qihao.find()) || (!haoma.find()))
						continue;
					String group = qihao.group();
					group = group.substring(0, 6) + "0" + group.substring(6);
					Result.put("20" + group, haoma.group());
				}

			}
			else
			{
				Log.ShowErr("安徽快3爬虫抓取号码错误！");
			}
		}
		return Result;
	}

	/**
	 * 快乐8   网站没有上线
	 * @param i
	 * @return
     */
	public static HashMap GetKl8(int i)
	{
		HashMap Result = new HashMap();
		long currentTimeMillis = System.currentTimeMillis();
		String html = D_HtmlCrawler.getHtml("http://caipiao.163.com/award/award_kl8Json.html?cache=" + currentTimeMillis);
		if ((StringUtils.isNotBlank(html)) && (i % 2 == 0))
		{
			for (Matcher matcher = Pattern.compile("(\\{[^{}]*\\})+").matcher(html); matcher.find(); )
			{
				String finds = matcher.group();
				Matcher qihao = Pattern.compile("[0-9]{6}").matcher(finds);
				Matcher feipan = Pattern.compile("fp:[0-9]{1}").matcher(finds);
				Matcher haoma = Pattern.compile("(\"[0-9]{2}\",){19}\"[0-9]{2}\"").matcher(finds);
				if ((qihao.find()) && (haoma.find()) && (feipan.find()))
					Result.put(qihao.group(), haoma.group().replace("\"", "") + "#" + feipan.group().replace("fp:", ""));
			}
		}
		else
		{
			html = D_HtmlCrawler.getHtml("http://www.huacai.com/html_cn/js/lot_award_161_i_20.js");
			if (StringUtils.isNotBlank(html))
			{
				for (Matcher matcher = Pattern.compile("content.*?lottery_id").matcher(html); matcher.find(); )
				{
					String finds = matcher.group();
					Matcher qihao = Pattern.compile("\"[0-9]{6}\"").matcher(finds);
					Matcher haoma = Pattern.compile("\"([0-9]{2},){19}[0-9]{2}#[0-9]\"").matcher(finds);
					if ((qihao.find()) && (haoma.find())) {
						Result.put(qihao.group().replace("\"", ""), haoma.group().replace("\"", ""));
					}
				}
			}
			else {
				Log.ShowErr("快乐8爬虫抓取号码错误！");
			}
		}
		return Result;
	}

	/**
	 * 动物总动员  网站没有上线
	 * @param i
	 * @return
     */
	public static HashMap GetDwzdy(int i)
	{
		HashMap Result = new HashMap();
		long currentTimeMillis = System.currentTimeMillis();
		String html = D_HtmlCrawler.getHtml("http://www.cailele.com/static/hnklsf/newlyopenlist.html?v=" + currentTimeMillis);
		if ((StringUtils.isNotBlank(html)) && (i % 2 == 0))
		{
			for (Matcher matcher = Pattern.compile("<tr>(.+?)</tr>").matcher(html); matcher.find(); )
			{
				String finds = matcher.group();
				Matcher qihao = Pattern.compile("[0-9]{9}").matcher(finds);
				Matcher haoma = Pattern.compile("([0-9]{2},){7}[0-9]{2}").matcher(finds);
				if ((qihao.find()) && (haoma.find()))
					Result.put("20" + qihao.group(), haoma.group());
			}
		}
		else
		{
			html = D_HtmlCrawler.getHtml("http://www.hnflcp.com/dwzdy_inc/dwzdy_kjlist.asp");
			if (StringUtils.isNotBlank(html))
			{
				String today = TimeUtil.getToday("yyyy");
				Document parse = Jsoup.parse(html);
				Elements select = parse.select("table[bgcolor=d7d7d7] > tbody > tr");
				int num = 0;
				for (Iterator iterator = select.iterator(); iterator.hasNext(); )
				{
					Element e = (Element)iterator.next();
					String qihao = "";
					String haoma = "";
					if (num == 0)
					{
						qihao = e.select("td:eq(1)").first().html();
						Elements stds = e.select("td tbody td");
						for (Iterator iterator1 = stds.iterator(); iterator1.hasNext(); )
						{
							Element td = (Element)iterator1.next();
							String attr = td.attr("background");
							if (attr.length() > 23) {
								haoma = haoma + attr.substring(20, 22) + ",";
							}
						}
						haoma = haoma.substring(0, haoma.length() - 1);
					}
					else {
						qihao = e.select("td:eq(1)").first().html();
						haoma = e.select("td tbody span").text();
					}
					if ((StringUtils.isNotBlank(haoma)) && (StringUtils.isNotBlank(qihao)))
						Result.put(today + qihao, haoma.replace(" ", ","));
					num++;
				}
			}
			else
			{
				Log.ShowErr("动物总动员爬虫抓取号码错误！");
			}
		}
		return Result;
	}

	/**
	 * 幸运农场 网站没有上线
	 * @param i
	 * @return
     */
	public static HashMap GetXync(int i)
	{
		HashMap Result = new HashMap();
		long currentTimeMillis = System.currentTimeMillis();
		String html = D_HtmlCrawler.getHtml("http://www.cailele.com/static/cqklsf/newlyopenlist.html?v=" + currentTimeMillis);
		if ((StringUtils.isNotBlank(html)) && (i % 2 == 0))
		{
			processXync(Result, html);

		}
		else
		{
			html = D_HtmlCrawler.getHtml("http://www.16cp.com/Game/GetNum.aspx?iType=1");
			if (StringUtils.isNotBlank(html))
			{
				Document parse = Jsoup.parse(html);
				Elements select = parse.select("ul");
				for (Iterator iterator = select.iterator(); iterator.hasNext(); )
				{
					Element e = (Element)iterator.next();
					String qihao = e.select("li:eq(0)").html();
					String haoma = e.select("li:eq(1)").text();
					if ((StringUtils.isNotBlank(haoma)) && (StringUtils.isNotBlank(qihao)))
						Result.put("20" + qihao, haoma);
				}
			}
			else
			{
				Log.ShowErr("幸运农场爬虫抓取号码错误！");
			}
		}
		return Result;
	}

	private static void processXync(HashMap result, String html) {
		for (Matcher matcher = Pattern.compile("<tr>(.+?)</tr>").matcher(html); matcher.find(); )
        {
            String finds = matcher.group();
            Matcher qihao = Pattern.compile("[0-9]{8}").matcher(finds);
            Matcher haoma = Pattern.compile("([0-9]{2},){7}[0-9]{2}").matcher(finds);
            if ((!qihao.find()) || (!haoma.find()))
                continue;
            String group = qihao.group();
            group = group.substring(0, 6) + "0" + group.substring(6);
            result.put("20" + group, haoma.group());
        }
	}

	/**
	 * 广东快乐十分   网站没有上线
	 * @param i
	 * @return
     */
	public static HashMap GetGdklsf(int i)
	{
		HashMap Result = new HashMap();
		long currentTimeMillis = System.currentTimeMillis();
		String html = D_HtmlCrawler.getHtml("http://www.cailele.com/static/klsf/newlyopenlist.html?v=" + currentTimeMillis);
		if ((StringUtils.isNotBlank(html)) && (i % 2 == 0))
		{
			processXync(Result, html);

		}
		else
		{
			html = D_HtmlCrawler.getHtml("http://video.shishicai.cn/gdkl10/");
			if (StringUtils.isNotBlank(html))
			{
				Matcher matcherT = Pattern.compile("kkVideo.initialize(.+?)</script>").matcher(html);
				if (matcherT.find())
					html = matcherT.group();
				for (Matcher matcher = Pattern.compile("(\\{[^{}]*\\})+").matcher(html); matcher.find(); )
				{
					String finds = matcher.group();
					Matcher qihao = Pattern.compile("[0-9]{8}-[0-9]{3}").matcher(finds);
					Matcher haoma = Pattern.compile("([0-9]{2},){7}[0-9]{2}").matcher(finds);
					if ((qihao.find()) && (haoma.find()))
						Result.put(qihao.group().replace("-", ""), haoma.group());
				}
			}
			else
			{
				Log.ShowErr("广东快乐十分爬虫抓取号码错误！");
			}
		}
		return Result;
	}

	/**
	 * 幸运赛车   网站没有上线
	 * @param i
	 * @return
     */
	public static HashMap GetXysc(int i)
	{
		HashMap Result = new HashMap();
		long currentTimeMillis = System.currentTimeMillis();
		String html = D_HtmlCrawler.getHtml("http://jk.trade.500.com/static/public/xysc/xml/newlyopenlist.xml?_=" + currentTimeMillis);
		if ((StringUtils.isNotBlank(html)) && (i % 2 == 0))
		{
			for (Matcher matcher = Pattern.compile("<row[^/]*/>").matcher(html); matcher.find(); )
			{
				String finds = matcher.group();
				Matcher qihao = Pattern.compile("expect=\"[0-9]{8}").matcher(finds);
				Matcher haoma = Pattern.compile("[0-9]{2},[0-9]{2},[0-9]{2}").matcher(finds);
				if ((qihao.find()) && (haoma.find()))
					Result.put("20" + qihao.group().replace("expect=\"", ""), haoma.group());
			}
		}
		else
		{
			html = D_HtmlCrawler.getHtml("http://sc.cpdyj.com/staticdata/lotteryinfo/xysc/xml/opencode.xml?_=" + currentTimeMillis);
			if (StringUtils.isNotBlank(html))
			{
				for (Matcher matcher = Pattern.compile("<row[^/]*/>").matcher(html); matcher.find(); )
				{
					String finds = matcher.group();
					Matcher qihao = Pattern.compile("[0-9]{8}").matcher(finds);
					Matcher haoma = Pattern.compile("[0-9]{2},[0-9]{2},[0-9]{2}").matcher(finds);
					if ((qihao.find()) && (haoma.find())) {
						Result.put("20" + qihao.group(), haoma.group());
					}
				}
			}
			else {
				Log.ShowErr("幸运赛车爬虫抓取号码错误！");
			}
		}
		return Result;
	}

	/**
	 * 山东群英会  网站没有上线
	 * @param i
	 * @return
     */
	public static HashMap GetQyh(int i)
	{
		HashMap Result = new HashMap();
		long currentTimeMillis = System.currentTimeMillis();
		String html = D_HtmlCrawler.getHtml("http://www.cailele.com/static/qyh/newlyopenlist.html?v=" + currentTimeMillis);
		if ((StringUtils.isNotBlank(html)) && (i % 2 == 0))
		{
			String today = TimeUtil.getToday("yyyy");
			for (Matcher matcher = Pattern.compile("<tr>(.+?)</tr>").matcher(html); matcher.find(); )
			{
				String finds = matcher.group();
				Matcher qihao = Pattern.compile("[0-9]{4}K[0-9]{3}").matcher(finds);
				Matcher haoma = Pattern.compile("([0-9]{2},){4}[0-9]{2}").matcher(finds);
				if ((qihao.find()) && (haoma.find()))
					Result.put(today + qihao.group(), haoma.group());
			}
		}
		else
		{
			String today = TimeUtil.getToday("yyyyMMdd");
			html = D_HtmlCrawler.getHtml("http://kaijiang.500.com/static/info/kaijiang/xml/qyh/" + today + ".xml?_A=WAUFJSIC" + currentTimeMillis);
			if (StringUtils.isNotBlank(html))
			{
				for (Matcher matcher = Pattern.compile("<row[^/]*/>").matcher(html); matcher.find(); )
				{
					String finds = matcher.group();
					Matcher qihao = Pattern.compile("[0-9]{6}K[0-9]{3}").matcher(finds);
					Matcher haoma = Pattern.compile("([0-9]{2},){4}[0-9]{2}").matcher(finds);
					if ((qihao.find()) && (haoma.find())) {
						Result.put("20" + qihao.group(), haoma.group());
					}
				}
			}
			else {
				Log.ShowErr("山东群英会 爬虫抓取号码错误！");
			}
		}
		return Result;
	}

	/**
	 * 双色球 网站没有上线
	 * @param i
	 * @return
     */
	public static HashMap GetSsq(int i)
	{
		HashMap Result = new HashMap();
		long currentTimeMillis = System.currentTimeMillis();
		String html = D_HtmlCrawler.getHtml("http://ssq.cpdyj.com/staticdata/guoguan/ssq/expect.xml?_=" + currentTimeMillis);
		if ((StringUtils.isNotBlank(html)) && (i % 2 == 0))
		{
			for (Matcher matcher = Pattern.compile("<row[^/]*/>").matcher(html); matcher.find(); )
			{
				String finds = matcher.group();
				Matcher qihao = Pattern.compile("expect=\"[0-9]{7}").matcher(finds);
				Matcher haoma = Pattern.compile("([0-9]{2}\\s){6}\\+\\s[0-9]{2}").matcher(finds);
				if ((!qihao.find()) || (!haoma.find()))
					continue;
				String hm = haoma.group().replace(" + ", "+");
				Result.put(qihao.group().replace("expect=\"", ""), hm.replace(" ", ","));
			}

		}
		else
		{
			html = D_HtmlCrawler.getHtml("http://www.wozhongla.com/sp2/act/data.resultTen.htm?type=001");
			if (StringUtils.isNotBlank(html))
			{
				Matcher qihao = Pattern.compile("issueNumber\":\"\\d*").matcher(html);
				Matcher haoma = Pattern.compile("([0-9]{2},){5}[0-9]{2}\\+[0-9]{2}").matcher(html);

				Matcher jianchi = Pattern.compile("\"bonusAmount\":\\d*").matcher(html);
				do {
					String hm = haoma.group();
					String qh = qihao.group();

					Result.put(qh.substring(14), hm); if ((!qihao.find()) || (!jianchi.find())) break;
				}while (haoma.find());
			}
			else
			{
				Log.ShowErr("双色球爬虫抓取号码错误！");
			}
		}
		return Result;
	}

	/**
	 * 大乐透 网站没有上线
	 * @param i
	 * @return
     */
	public static HashMap GetDlt(int i)
	{
		HashMap Result = new HashMap();
		long currentTimeMillis = System.currentTimeMillis();
		String html = D_HtmlCrawler.getHtml("http://dlt.cpdyj.com/staticdata/guoguan/clt/expect.xml?_=" + currentTimeMillis);
		if ((StringUtils.isNotBlank(html)) && (i % 2 == 0))
		{
			for (Matcher matcher = Pattern.compile("<row[^/]*/>").matcher(html); matcher.find(); )
			{
				String finds = matcher.group();
				Matcher qihao = Pattern.compile("expect=\"[0-9]{7}").matcher(finds);
				Matcher haoma = Pattern.compile("([0-9]{2}\\s){5}\\+(\\s[0-9]{2}){2}").matcher(finds);
				if ((!qihao.find()) || (!haoma.find()))
					continue;
				String hm = haoma.group().replace(" + ", "+");
				Result.put(qihao.group().replace("expect=\"", ""), hm.replace(" ", ","));
			}

		}
		else
		{
			html = D_HtmlCrawler.getHtml("http://www.wozhongla.com/sp2/act/data.resultTen.htm?type=113");
			if (StringUtils.isNotBlank(html))
			{
				for (Matcher matcher = Pattern.compile("resultNumber.*?creationTime").matcher(html); matcher.find(); )
				{
					String finds = matcher.group();
					Matcher qihao = Pattern.compile("issueNumber\":\"\\d{5}").matcher(finds);
					Matcher haoma = Pattern.compile("([0-9]{2},){4}[0-9]{2}\\+[0-9]{2},[0-9]{2}").matcher(finds);
					Matcher jianchi = Pattern.compile("bonusAmount\":\\d*").matcher(finds);
					if ((qihao.find()) && (haoma.find()) && (jianchi.find())) {
						Result.put("20" + qihao.group().substring(14), haoma.group().replace("|", "+"));
					}
				}
			}
			else {
				Log.ShowErr("大乐透爬虫抓取号码错误！");
			}
		}
		return Result;
	}

	/**
	 * 福彩3D
	 * @param i
	 * @return
     */
	public static HashMap GetFc3d(int i)
	{
		HashMap Result = new HashMap();
		long currentTimeMillis = System.currentTimeMillis();
		String html = D_HtmlCrawler.getHtml("http://www.500wan.com/static/info/kaijiang/xml/sd/list10.xml");
		Log.ShowInfo("福彩3D爬虫抓取号码from 500wan,返回结果大小：" +  html != null ? ""+html.length():"0");
		if (StringUtils.isNotBlank(html))
		{
			for (Matcher matcher = Pattern.compile("<row[^/]*/>").matcher(html); matcher.find(); )
			{
				String finds = matcher.group();
				Matcher qihao = Pattern.compile("[0-9]{7}").matcher(finds);
				Matcher haoma = Pattern.compile("[0-9]{1},[0-9]{1},[0-9]{1}").matcher(finds);
				if ((qihao.find()) && (haoma.find()))
					Result.put(qihao.group(), haoma.group());
			}
		}
		else
		{
//			html = D_HtmlCrawler.getHtml("http://cailele.jiuding360.com/caiji.asp?l=fc3d&v=" + currentTimeMillis);
//			if (StringUtils.isNotBlank(html))
//			{
//				for (Matcher matcher = Pattern.compile("<tr>(.+?)</tr>").matcher(html); matcher.find(); )
//				{
//					String finds = matcher.group();
//					Matcher qihao = Pattern.compile("[0-9]{7}").matcher(finds);
//					Matcher haoma = Pattern.compile("[0-9]{1},[0-9]{1},[0-9]{1}").matcher(finds);
//					if ((qihao.find()) && (haoma.find())) {
//						Result.put(qihao.group(), haoma.group());
//					}
//				}
//			}
//			else {
				Log.ShowErr("福彩3D爬虫抓取号码错误！");
//			}
		}
		return Result;
	}

	/**
	 * 排列5 暂时没有上线
	 * @param i
	 * @return
     */
	public static HashMap GetPl5(int i)
	{
		HashMap Result = new HashMap();
		long currentTimeMillis = System.currentTimeMillis();
		String html = D_HtmlCrawler.getHtml("http://www.500wan.com/static/info/kaijiang/xml/plw/list10.xml");
		if ((StringUtils.isNotBlank(html)) && (i % 2 == 0))
		{
			for (Matcher matcher = Pattern.compile("<row[^/]*/>").matcher(html); matcher.find(); )
			{
				String finds = matcher.group();
				Matcher qihao = Pattern.compile("[0-9]{5}").matcher(finds);
				Matcher haoma = Pattern.compile("([0-9]{1},){4}[0-9]{1}").matcher(finds);
				if ((qihao.find()) && (haoma.find()))
					Result.put("20" + qihao.group(), haoma.group());
			}
		}
		else
		{
			html = D_HtmlCrawler.getHtml("http://www.cailele.com/static/pw/newlyopenlist.html?v=" + currentTimeMillis);
			if (StringUtils.isNotBlank(html))
			{
				for (Matcher matcher = Pattern.compile("<tr>(.+?)</tr>").matcher(html); matcher.find(); )
				{
					String finds = matcher.group();
					Matcher qihao = Pattern.compile("[0-9]{5}").matcher(finds);
					Matcher haoma = Pattern.compile("([0-9]{1},){4}[0-9]{1}").matcher(finds);
					if ((qihao.find()) && (haoma.find())) {
						Result.put("20" + qihao.group(), haoma.group());
					}
				}
			}
			else {
				Log.ShowErr("排列五爬虫抓取号码错误！");
			}
		}
		return Result;
	}

	/**
	 * 排列3
	 * @param i
	 * @return
     */
	public static HashMap GetPl3(int i)
	{
		HashMap Result = new HashMap();
		long currentTimeMillis = System.currentTimeMillis();
		String html = D_HtmlCrawler.getHtml("http://www.500wan.com/static/info/kaijiang/xml/plw/list10.xml");
		if ((StringUtils.isNotBlank(html)) && (i % 2 == 0))
		{
			for (Matcher matcher = Pattern.compile("<row[^/]*/>").matcher(html); matcher.find(); )
			{
				String finds = matcher.group();
				Matcher qihao = Pattern.compile("[0-9]{5}").matcher(finds);
				Matcher haoma = Pattern.compile("([0-9]{1},){4}[0-9]{1}").matcher(finds);
				if ((qihao.find()) && (haoma.find()))
					Result.put("20" + qihao.group(), haoma.group().substring(0, 5));
			}
		}
		else
		{
			html = D_HtmlCrawler.getHtml("http://cailele.jiuding360.com/code.asp?l=pl3&v=" + currentTimeMillis);
			if (StringUtils.isNotBlank(html))
			{
				for (Matcher matcher = Pattern.compile("<tr>(.+?)</tr>").matcher(html); matcher.find(); )
				{
					String finds = matcher.group();
					Matcher qihao = Pattern.compile("[0-9]{5}").matcher(finds);
					Matcher haoma = Pattern.compile("([0-9]{1},){4}[0-9]{1}").matcher(finds);
					if ((qihao.find()) && (haoma.find())) {
						Result.put("20" + qihao.group(), haoma.group().substring(0, 5));
					}
				}
			}
			else {
				Log.ShowErr("排列三爬虫抓取号码错误！");
			}
		}
		return Result;
	}

	/**
	 * 七星彩  网站没有上线
	 * @param i
	 * @return
     */
	public static HashMap GetQxc(int i)
	{
		HashMap Result = new HashMap();
		long currentTimeMillis = System.currentTimeMillis();
		String html = D_HtmlCrawler.getHtml("http://qxc.cpdyj.com/staticdata/lotteryinfo/opencode/10022.xml?t=" + currentTimeMillis);
		if ((StringUtils.isNotBlank(html)) && (i % 2 == 0))
		{
			for (Matcher matcher = Pattern.compile("<row[^/]*/>").matcher(html); matcher.find(); )
			{
				String finds = matcher.group();
				Matcher qihao = Pattern.compile("lotissue=\"[0-9]{7}").matcher(finds);
				Matcher haoma = Pattern.compile("BaseCode=\"[0-9]{7}").matcher(finds);
				if ((!qihao.find()) || (!haoma.find()))
					continue;
				String haomas = haoma.group().replace("BaseCode=\"", "").replace("", ",");
				Result.put(qihao.group().replace("lotissue=\"", ""), haomas.substring(1, haomas.length() - 1));
			}

		}
		else
		{
			html = D_HtmlCrawler.getHtml("http://www.wozhongla.com/sp2/act/data.resultTen.htm?type=110&d=" + currentTimeMillis);
			if (StringUtils.isNotBlank(html))
			{
				for (Matcher matcher = Pattern.compile("resultNumber.*?creationTime").matcher(html); matcher.find(); )
				{
					String finds = matcher.group();
					Matcher qihao = Pattern.compile("issueNumber\":\"\\d{5}").matcher(finds);
					Matcher haoma = Pattern.compile("([0-9]{1},){6}[0-9]{1}").matcher(finds);
					if ((qihao.find()) && (haoma.find())) {
						Result.put("20" + qihao.group().substring(14), haoma.group());
					}
				}
			}
			else {
				Log.ShowErr("七星彩爬虫抓取号码错误！");
			}
		}
		return Result;
	}

	public static HashMap GetQlc(int i)
	{
		HashMap Result = new HashMap();
		long currentTimeMillis = System.currentTimeMillis();
		String html = D_HtmlCrawler.getHtml("http://qlc.cpdyj.com/staticdata/guoguan/qlc/expect.xml?_=" + currentTimeMillis);
		if ((StringUtils.isNotBlank(html)) && (i % 2 == 0))
		{
			for (Matcher matcher = Pattern.compile("<row[^/]*/>").matcher(html); matcher.find(); )
			{
				String finds = matcher.group();
				Matcher qihao = Pattern.compile("expect=\"[0-9]{7}").matcher(finds);
				Matcher haoma = Pattern.compile("([0-9]{2}\\s){7}\\+\\s[0-9]{2}").matcher(finds);
				if ((!qihao.find()) || (!haoma.find()))
					continue;
				String hm = haoma.group().replace(" + ", "+");
				Result.put(qihao.group().replace("expect=\"", ""), hm.replace(" ", ","));
			}

		}
		else
		{
			html = D_HtmlCrawler.getHtml("http://www.wozhongla.com/sp2/act/data.resultTen.htm?type=003&d=" + currentTimeMillis);
			if (StringUtils.isNotBlank(html))
			{
				for (Matcher matcher = Pattern.compile("resultNumber.*?creationTime").matcher(html); matcher.find(); )
				{
					String finds = matcher.group();
					Matcher qihao = Pattern.compile("issueNumber\":\"\\d{7}").matcher(finds);
					Matcher haoma = Pattern.compile("([0-9]{2},){6}[0-9]{2}\\+[0-9]{2}").matcher(finds);
					if ((qihao.find()) && (haoma.find())) {
						Result.put(qihao.group().substring(14), haoma.group());
					}
				}
			}
			else {
				Log.ShowErr("七乐彩爬虫抓取号码错误！");
			}
		}
		return Result;
	}

	public static HashMap GetHd15x5(int i)
	{
		HashMap Result = new HashMap();
		long currentTimeMillis = System.currentTimeMillis();
		String html = D_HtmlCrawler.getHtml("http://kaijiang.500.com/static/info/kaijiang/xml/hdswxw/list10.xml?_A=HFPORQNL1389160252495?_=" + currentTimeMillis);
		if ((StringUtils.isNotBlank(html)) && (i % 2 == 0))
		{
			for (Matcher matcher = Pattern.compile("<row[^/]*/>").matcher(html); matcher.find(); )
			{
				String finds = matcher.group();
				Matcher qihao = Pattern.compile("[0-9]{7}").matcher(finds);
				Matcher haoma = Pattern.compile("([0-9]{2},){4}[0-9]{2}").matcher(finds);
				if ((qihao.find()) && (haoma.find()))
					Result.put(qihao.group(), haoma.group());
			}
		}
		else
		{
			html = D_HtmlCrawler.getHtml("http://www.cailele.com/static/15x5/newlyopenlist.html?v=" + currentTimeMillis);
			if (StringUtils.isNotBlank(html))
			{
				for (Matcher matcher = Pattern.compile("<tr>(.+?)</tr>").matcher(html); matcher.find(); )
				{
					String finds = matcher.group();
					Matcher qihao = Pattern.compile("[0-9]{7}").matcher(finds);
					Matcher haoma = Pattern.compile("([0-9]{2},){4}[0-9]{2}").matcher(finds);
					if ((qihao.find()) && (haoma.find())) {
						Result.put(qihao.group(), haoma.group());
					}
				}
			}
			else {
				Log.ShowErr("华东15选5爬虫抓取号码错误！");
			}
		}
		return Result;
	}

	public static HashMap GetHcy(int i)
	{
		HashMap Result = new HashMap();
		long currentTimeMillis = System.currentTimeMillis();
		String html = D_HtmlCrawler.getHtml("http://www.cailele.com/static/hc1/newlyopenlist.html?v=" + currentTimeMillis);
		if ((StringUtils.isNotBlank(html)) && (i % 2 == 0))
		{
			for (Matcher matcher = Pattern.compile("<tr>(.+?)</tr>").matcher(html); matcher.find(); )
			{
				String finds = matcher.group();
				Matcher qihao = Pattern.compile("[0-9]{7}").matcher(finds);
				Matcher haoma = Pattern.compile("[0-9]{2},[一-龥]{1},[一-龥]{1},[一-龥]{1}").matcher(finds);
				if ((qihao.find()) && (haoma.find()))
					Result.put(qihao.group(), haoma.group());
			}
		}
		else
		{
			html = D_HtmlCrawler.getHtml("http://kaijiang.500.com/static/info/kaijiang/xml/gdhc1/list10.xml?_" + currentTimeMillis);
			try
			{
				html = CharSetUtil.changeCharset(html, "ISO-8859-1", "UTF-8");
			}
			catch (UnsupportedEncodingException e)
			{
				e.printStackTrace();
			}
			if (StringUtils.isNotBlank(html))
			{
				for (Matcher matcher = Pattern.compile("<row[^/]*/>").matcher(html); matcher.find(); )
				{
					String finds = matcher.group();
					Matcher qihao = Pattern.compile("[0-9]{7}").matcher(finds);
					Matcher haoma = Pattern.compile("[0-9]{2},[一-龥]{1},[一-龥]{1},[一-龥]{1}").matcher(finds);
					if ((qihao.find()) && (haoma.find())) {
						Result.put(qihao.group(), haoma.group());
					}
				}
			}
			else {
				Log.ShowErr("好彩一爬虫抓取号码错误！");
			}
		}
		return Result;
	}

	public static HashMap GetHdljy(int i)
	{
		HashMap Result = new HashMap();
		long currentTimeMillis = System.currentTimeMillis();
		String html = D_HtmlCrawler.getHtml("http://www.wozhongla.com/sp2/act/data.resultTen.htm?type=004&d=" + currentTimeMillis);
		if ((StringUtils.isNotBlank(html)) && (i % 2 == 0))
		{
			for (Matcher matcher = Pattern.compile("resultNumber.*?creationTime").matcher(html); matcher.find(); )
			{
				String finds = matcher.group();
				Matcher qihao = Pattern.compile("issueNumber\":\"\\d{7}").matcher(finds);
				Matcher haoma = Pattern.compile("([0-9]{1},){5}[0-9]{1}\\+[一-龥]{1}").matcher(finds);
				if ((qihao.find()) && (haoma.find()))
					Result.put(qihao.group().substring(14), haoma.group());
			}
		}
		else
		{
			html = D_HtmlCrawler.getHtml("http://kaijiang.500.com/static/info/kaijiang/xml/df6j1/list.xml?_A=HKLPNRQO" + currentTimeMillis);
			try
			{
				html = CharSetUtil.changeCharset(html, "ISO-8859-1", "UTF-8");
			}
			catch (UnsupportedEncodingException e)
			{
				e.printStackTrace();
			}
			if (StringUtils.isNotBlank(html))
			{
				for (Matcher matcher = Pattern.compile("<row[^/]*/>").matcher(html); matcher.find(); )
				{
					String finds = matcher.group();
					Matcher qihao = Pattern.compile("[0-9]{7}").matcher(finds);
					Matcher haoma = Pattern.compile("([0-9]{1},){5}[0-9]{1}\\|[一-龥]{1}").matcher(finds);
					if ((qihao.find()) && (haoma.find())) {
						Result.put(qihao.group(), haoma.group().replace("|", "+"));
					}
				}
			}
			else {
				Log.ShowErr("华东6+1爬虫抓取号码错误！");
			}
		}
		return Result;
	}
}