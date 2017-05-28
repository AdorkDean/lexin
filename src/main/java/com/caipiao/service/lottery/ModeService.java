package com.caipiao.service.lottery;

import com.caipiao.entity.Bc_buyuser;
import com.caipiao.entity.out.Phb;
import com.caipiao.intface.Bc_buyuserIntface;
import com.caipiao.intface.Bc_phbIntface;
import com.caipiao.intfaceImpl.BuyuserIntfaceImpl;
import com.caipiao.intfaceImpl.PhbIntfaceImpl;
import java.util.List;

public class ModeService
{
	private static final String cache = ModeService.class.toString();
	Bc_phbIntface phbdao = new PhbIntfaceImpl();
	Bc_buyuserIntface buyuserdao = new BuyuserIntfaceImpl();

	public List<Phb> findphb(int User_id, int type, String lot)
	{
		String select = "*";
		switch (type) { case 0:
			select = "Phb_total"; break;
			case 1:
				select = "Phb_all"; break;
			case 2:
				select = "Phb_hmall"; break;
			case 3:
				select = "Phb_month"; break;
			case 4:
				select = "Phb_hmmonth"; break;
			case 5:
				select = "Phb_week"; break;
			case 6:
				select = "Phb_hmweek"; break;
			case 7:
				select = "Phb_day"; break;
			case 8:
				select = "Phb_hmday"; break;
		}

		lot = lot != null ? lot : "all";
		return this.phbdao.finds(User_id, select, lot, 0, 10);
	}

	public List<Bc_buyuser> findNewWin(String lot, int num)
	{
		return this.buyuserdao.findNewWin(lot, num);
	}

	public List<Bc_buyuser> findNewWin(int num)
	{
		return this.buyuserdao.findNewWin(num);
	}
}