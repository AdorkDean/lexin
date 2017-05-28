
//    Bc_banks.java

package com.caipiao.entity;

import com.caipiao.utils.ShowStatic;

public class Bc_banks
{

	private int Banks_id;
	private int User_id;
	private String Banks_card;
	private String Banks_bank;
	private String Banks_add;
	private String Banks_name;
	private String Banks_phone;
	private int Banks_status;

	public Bc_banks()
	{
	}

	public int getBanks_id()
	{
		return Banks_id;
	}

	public void setBanks_id(int banks_id)
	{
		Banks_id = banks_id;
	}

	public int getUser_id()
	{
		return User_id;
	}

	public void setUser_id(int user_id)
	{
		User_id = user_id;
	}

	public String getBanks_card()
	{
		return Banks_card;
	}

	public void setBanks_card(String banks_card)
	{
		Banks_card = banks_card;
	}

	public String getBanks_bank()
	{
		return Banks_bank;
	}

	public void setBanks_bank(String banks_bank)
	{
		Banks_bank = banks_bank;
	}

	public String getBanks_class()
	{
		return ShowStatic.BankLogoClass(Banks_bank);
	}

	public String getBanks_add()
	{
		return Banks_add;
	}

	public void setBanks_add(String banks_add)
	{
		Banks_add = banks_add;
	}

	public String getBanks_name()
	{
		return Banks_name;
	}

	public void setBanks_name(String banks_name)
	{
		Banks_name = banks_name;
	}

	public String getBanks_phone()
	{
		return Banks_phone;
	}

	public void setBanks_phone(String banks_phone)
	{
		Banks_phone = banks_phone;
	}

	public int getBanks_status()
	{
		return Banks_status;
	}

	public void setBanks_status(int banks_status)
	{
		Banks_status = banks_status;
	}
}
