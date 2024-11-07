package com.sist.web.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class MainRestController {
	@GetMapping("/names")
	public List<String> mainNames() {
		List<String> list=new ArrayList<String>();
		list.add("name1");
		list.add("name2");
		list.add("name3");
		list.add("name4");
		list.add("name5");
		list.add("name6");
		list.add("name7");
		list.add("name8");
		return list;
	}
	
}
