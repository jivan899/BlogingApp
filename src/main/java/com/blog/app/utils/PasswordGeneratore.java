package com.blog.app.utils;

import java.util.ArrayList;
import java.util.List;

public class PasswordGeneratore {

	public static void main(String[] args) {
		System.out.println("hi");
		List<String> str = new ArrayList<>();
		str.add("a");
		str.add("a");
		str.add("a");
		str.add("a");
		for(int i=1; i<=str.size();i++) {
			System.out.println("i= "+i +"str size"+str.size());
		}
	}
}