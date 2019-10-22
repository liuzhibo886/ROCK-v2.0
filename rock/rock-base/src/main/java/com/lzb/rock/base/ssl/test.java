package com.lzb.rock.base.ssl;

import java.util.Calendar;

public class test {

	public static void main(String[] args) {
		Calendar cale = Calendar.getInstance();
		cale.add(Calendar.MONTH, 0);
		cale.set(Calendar.DAY_OF_MONTH, 1);
		
		System.out.println(cale.getTime().getYear()+":"+cale.getTime().getMonth());
		

	}

}
