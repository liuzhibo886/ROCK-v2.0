package com.lzb.rock.test.ms.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.lzb.rock.base.util.UtilFile;

public class test {

	public static void main(String[] args) {


		Set<String> set = new HashSet<String>();
		Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
		List<String> list = UtilFile.input("F:\\", "name.txt");
		for (String str : list) {
			String[] arr = str.split("");
			for (String string : arr) {
				Matcher m = p.matcher(string);
				if (m.find()) {
					set.add(string);
				}
			}

		}
		StringBuffer sb=new StringBuffer();
		for (String string : set) {
			sb.append(string);
		}
		System.out.println(sb.toString());
	

	}

}
