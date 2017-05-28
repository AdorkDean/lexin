
//    Bc_comm.java

package com.caipiao.entity;


public class Bc_comm
{

	private int Comm_id;
	private int User_id;
	private String User_name;
	private double Ssq;
	private double Dlt;
	private double Fc3d;
	private double Pl3;
	private double Pl5;
	private double Cqssc;
	private double Jxssc;
	private double Sd11x5;
	private double Jx11x5;
	private double Gd11x5;
	private double Cq11x5;
	private double Sh11x5;
	private double Hnssc;
	private double Jsk3;

	public Bc_comm()
	{
	}

	public void setAll(double comm)
	{
		Ssq = Dlt = Fc3d = Pl3 = Pl5 = Cqssc = Jxssc = Sd11x5 = Jx11x5 = Gd11x5 = Cq11x5 = Sh11x5 = Hnssc = Jsk3 = comm;
	}

	public double getAll(String lot)
	{
		double comm = 0.0D;
		if ("Cqssc".equals(lot))
			comm = Cqssc;
		else
		if ("Jxssc".equals(lot))
			comm = Jxssc;
		else
		if ("Ssq".equals(lot))
			comm = Ssq;
		else
		if ("Fc3d".equals(lot))
			comm = Fc3d;
		else
		if ("Dlt".equals(lot))
			comm = Dlt;
		else
		if ("Pl3".equals(lot))
			comm = Pl3;
		else
		if ("Pl5".equals(lot))
			comm = Pl5;
		else
		if ("Sd11x5".equals(lot))
			comm = Sd11x5;
		else
		if ("Jx11x5".equals(lot))
			comm = Jx11x5;
		else
		if ("Gd11x5".equals(lot))
			comm = Gd11x5;
		else
		if ("Cq11x5".equals(lot))
			comm = Cq11x5;
		else
		if ("Sh11x5".equals(lot))
			comm = Sh11x5;
		else
		if ("Hnssc".equals(lot))
			comm = Hnssc;
		else
		if ("Jsk3".equals(lot))
			comm = Jsk3;
		return comm;
	}

	public double getHnssc()
	{
		return Hnssc;
	}

	public void setHnssc(double hnssc)
	{
		Hnssc = hnssc;
	}

	public double getSh11x5()
	{
		return Sh11x5;
	}

	public void setSh11x5(double sh11x5)
	{
		Sh11x5 = sh11x5;
	}

	public double getPl5()
	{
		return Pl5;
	}

	public void setPl5(double pl5)
	{
		Pl5 = pl5;
	}

	public String getUser_name()
	{
		return User_name;
	}

	public void setUser_name(String user_name)
	{
		User_name = user_name;
	}

	public int getComm_id()
	{
		return Comm_id;
	}

	public void setComm_id(int comm_id)
	{
		Comm_id = comm_id;
	}

	public int getUser_id()
	{
		return User_id;
	}

	public void setUser_id(int user_id)
	{
		User_id = user_id;
	}

	public double getSsq()
	{
		return Ssq;
	}

	public void setSsq(double ssq)
	{
		Ssq = ssq;
	}

	public double getDlt()
	{
		return Dlt;
	}

	public void setDlt(double dlt)
	{
		Dlt = dlt;
	}

	public double getFc3d()
	{
		return Fc3d;
	}

	public void setFc3d(double fc3d)
	{
		Fc3d = fc3d;
	}

	public double getPl3()
	{
		return Pl3;
	}

	public void setPl3(double pl3)
	{
		Pl3 = pl3;
	}

	public double getCqssc()
	{
		return Cqssc;
	}

	public void setCqssc(double cqssc)
	{
		Cqssc = cqssc;
	}

	public double getJxssc()
	{
		return Jxssc;
	}

	public void setJxssc(double jxssc)
	{
		Jxssc = jxssc;
	}

	public double getSd11x5()
	{
		return Sd11x5;
	}

	public void setSd11x5(double sd11x5)
	{
		Sd11x5 = sd11x5;
	}

	public double getJx11x5()
	{
		return Jx11x5;
	}

	public void setJx11x5(double jx11x5)
	{
		Jx11x5 = jx11x5;
	}

	public double getGd11x5()
	{
		return Gd11x5;
	}

	public void setGd11x5(double gd11x5)
	{
		Gd11x5 = gd11x5;
	}

	public double getCq11x5()
	{
		return Cq11x5;
	}

	public void setCq11x5(double cq11x5)
	{
		Cq11x5 = cq11x5;
	}

	public double getJsk3()
	{
		return Jsk3;
	}

	public void setJsk3(double jsk3)
	{
		Jsk3 = jsk3;
	}
}
