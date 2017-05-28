package com.caipiao.utils;

import com.caipiao.entity.out.Draw;
import com.caipiao.entity.out.Rech;
import com.caipiao.entity.out.UserOut;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class XlsOutUtil
{
	public static void SaveXls(String name, String url, List<Object> list, Object obj)
			throws Exception
	{
		HSSFWorkbook wb = new HSSFWorkbook();

		HSSFSheet sheet = wb.createSheet(name);

		HSSFRow row = sheet.createRow(0);

		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment((short) 2);
		HSSFCell cell = row.createCell(0);
		Field[] fields = obj.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			cell.setCellValue(fields[i].getName()); cell.setCellStyle(style);
			cell = row.createCell(i + 1);
		}
		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow(i + 1);
			Object object = list.get(i);
			Class clazz = object.getClass();
			Field[] fieldsdata = clazz.getDeclaredFields();

			for (int j = 0; j < fieldsdata.length; j++) {
				fieldsdata[j].setAccessible(true);
				if (fieldsdata[j].getType().getName().equals(Integer.class.getName()))
					row.createCell(j).setCellValue(fieldsdata[j].getInt(object));
				else if (fieldsdata[j].getType().getName().equals(Double.class.getName()))
					row.createCell(j).setCellValue(fieldsdata[j].getDouble(object));
				else {
					row.createCell(j).setCellValue(fieldsdata[j].get(object).toString());
				}
			}
		}
		try
		{
			FileOutputStream fout = new FileOutputStream(url);
			wb.write(fout);
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String SaveRechXLS(List<Rech> data)
	{
		String fileurl = "C:/rech.xls";

		HSSFWorkbook wb = new HSSFWorkbook();

		HSSFSheet sheet = wb.createSheet("充值数据");

		HSSFRow row = sheet.createRow(0);

		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment((short) 2);
		HSSFCell cell = row.createCell(0);
		cell.setCellValue("编号"); cell.setCellStyle(style); cell = row.createCell(1);
		cell.setCellValue("用户id"); cell.setCellStyle(style); cell = row.createCell(2);
		cell.setCellValue("用户名"); cell.setCellStyle(style); cell = row.createCell(3);
		cell.setCellValue("订单号"); cell.setCellStyle(style); cell = row.createCell(4);
		cell.setCellValue("下单时间"); cell.setCellStyle(style); cell = row.createCell(5);
		cell.setCellValue("充值金额"); cell.setCellStyle(style); cell = row.createCell(6);
		cell.setCellValue("赠送金额"); cell.setCellStyle(style); cell = row.createCell(7);
		cell.setCellValue("充值方式"); cell.setCellStyle(style); cell = row.createCell(8);
		cell.setCellValue("充值状态"); cell.setCellStyle(style); cell = row.createCell(9);
		cell.setCellValue("备注"); cell.setCellStyle(style); cell = row.createCell(10);
		cell.setCellValue("处理员"); cell.setCellStyle(style); cell = row.createCell(11);
		cell.setCellValue("处理时间"); cell.setCellStyle(style); cell = row.createCell(12);
		cell.setCellValue("用户类型"); cell.setCellStyle(style); cell = row.createCell(13);
		cell.setCellValue("上级"); cell.setCellStyle(style); cell = row.createCell(14);

		for (int i = 0; i < data.size(); i++) {
			row = sheet.createRow(i + 1);
			Rech rech = (Rech)data.get(i);
			row = SaveRechXLS(row, rech);
		}
		try
		{
			FileOutputStream fout = new FileOutputStream(fileurl);
			wb.write(fout);
			fout.close();
			return fileurl;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	private static HSSFRow SaveRechXLS(HSSFRow row, Rech data) {
		String rech_type = "--";
		int retype = data.getRech_type();
		if (retype == 1)
			rech_type = "网银在线";
		else if (retype == 2)
			rech_type = "易宝支付";
		else if (retype == 3)
			rech_type = "支付宝";
		else if (retype == 0)
			rech_type = "手工充值";
		else if (retype == 5) {
			rech_type = "汇潮充值";
		}
		row.createCell(0).setCellValue(data.getRech_id());
		row.createCell(1).setCellValue(data.getUser_id());
		row.createCell(2).setCellValue(data.getUser_name());
		row.createCell(3).setCellValue(data.getRech_item());
		row.createCell(4).setCellValue(data.getRech_time());
		row.createCell(5).setCellValue(data.getRech_money());
		row.createCell(6).setCellValue(data.getRech_give());
		row.createCell(7).setCellValue(rech_type);
		row.createCell(8).setCellValue(data.getRech_status() == 1 ? "已付款" : "未付款");
		row.createCell(9).setCellValue(data.getRech_desc());
		row.createCell(10).setCellValue(data.getRech_douser());
		row.createCell(11).setCellValue(data.getRech_dotime());
		row.createCell(12).setCellValue(data.getUser_type() == 0 ? "普通" : "其他");
		row.createCell(13).setCellValue(data.getUser_upname() == null ? "" : data.getUser_upname());
		return row;
	}

	public static String SaveDrawXLS(List<Draw> data)
	{
		String fileurl = "C:/draw.xls";

		HSSFWorkbook wb = new HSSFWorkbook();

		HSSFSheet sheet = wb.createSheet("提款数据");

		HSSFRow row = sheet.createRow(0);

		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment((short) 2);
		HSSFCell cell = row.createCell(0);
		cell.setCellValue("编号"); cell.setCellStyle(style); cell = row.createCell(1);
		cell.setCellValue("用户id"); cell.setCellStyle(style); cell = row.createCell(2);
		cell.setCellValue("用户名"); cell.setCellStyle(style); cell = row.createCell(3);
		cell.setCellValue("订单号"); cell.setCellStyle(style); cell = row.createCell(4);
		cell.setCellValue("下单时间"); cell.setCellStyle(style); cell = row.createCell(5);
		cell.setCellValue("提款金额"); cell.setCellStyle(style); cell = row.createCell(6);
		cell.setCellValue("手续费"); cell.setCellStyle(style); cell = row.createCell(7);
		cell.setCellValue("处理时间"); cell.setCellStyle(style); cell = row.createCell(8);
		cell.setCellValue("处理员"); cell.setCellStyle(style); cell = row.createCell(9);
		cell.setCellValue("备注"); cell.setCellStyle(style); cell = row.createCell(10);
		cell.setCellValue("提款类型"); cell.setCellStyle(style); cell = row.createCell(11);
		cell.setCellValue("提款类型id"); cell.setCellStyle(style); cell = row.createCell(12);
		cell.setCellValue("状态"); cell.setCellStyle(style); cell = row.createCell(13);
		cell.setCellValue("用户类型"); cell.setCellStyle(style); cell = row.createCell(14);
		cell.setCellValue("上级"); cell.setCellStyle(style); cell = row.createCell(15);

		for (int i = 0; i < data.size(); i++) {
			row = sheet.createRow(i + 1);
			Draw draw = (Draw)data.get(i);
			row = SaveDrawXLS(row, draw);
		}
		try
		{
			FileOutputStream fout = new FileOutputStream(fileurl);
			wb.write(fout);
			fout.close();
			return fileurl;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	private static HSSFRow SaveDrawXLS(HSSFRow row, Draw data) {
		row.createCell(0).setCellValue(data.getDraw_id());
		row.createCell(1).setCellValue(data.getUser_id());
		row.createCell(2).setCellValue(data.getUser_name());
		row.createCell(3).setCellValue(data.getDraw_item());
		row.createCell(4).setCellValue(data.getDraw_time());
		row.createCell(5).setCellValue(data.getDraw_money());
		row.createCell(6).setCellValue(data.getDraw_surgery());
		row.createCell(7).setCellValue(data.getDraw_dotime());
		row.createCell(8).setCellValue(data.getDraw_douser());
		row.createCell(9).setCellValue(data.getDraw_desc());
		row.createCell(10).setCellValue(data.getDraw_type() == 0 ? "银行卡" : "其他");
		row.createCell(11).setCellValue(data.getPay_id());
		String status = "其他";
		int ds = data.getDraw_status();
		if (ds == 0) status = "申请中"; else if (1 == ds) status = "处理中"; else if (2 == ds) status = "已成功"; else if (3 == ds) status = "用户撤销"; else if (4 == ds) status = "拒绝";
		row.createCell(12).setCellValue(status);
		row.createCell(13).setCellValue(data.getUser_type() == 0 ? "普通" : "其他");
		row.createCell(14).setCellValue(data.getUser_upname() == null ? "" : data.getUser_upname());
		return row;
	}

	public static String SaveUserXLS(List<UserOut> data)
	{
		String fileurl = "C:/user.xls";

		HSSFWorkbook wb = new HSSFWorkbook();

		HSSFSheet sheet = wb.createSheet("用户余额");

		HSSFRow row = sheet.createRow(0);

		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment((short) 2);
		HSSFCell cell = row.createCell(0);
		cell.setCellValue("用户名"); cell.setCellStyle(style); cell = row.createCell(1);
		cell.setCellValue("余额"); cell.setCellStyle(style); cell = row.createCell(2);
		cell.setCellValue("上级"); cell.setCellStyle(style); cell = row.createCell(3);

		for (int i = 0; i < data.size(); i++) {
			row = sheet.createRow(i + 1);
			UserOut user = (UserOut)data.get(i);
			row = SaveUserXLS(row, user);
		}
		try
		{
			FileOutputStream fout = new FileOutputStream(fileurl);
			wb.write(fout);
			fout.close();
			return fileurl;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	private static HSSFRow SaveUserXLS(HSSFRow row, UserOut data) {
		row.createCell(0).setCellValue(data.getUser_name());
		row.createCell(1).setCellValue(data.getUser_money());
		row.createCell(2).setCellValue(data.getUser_upname());
		return row;
	}
}