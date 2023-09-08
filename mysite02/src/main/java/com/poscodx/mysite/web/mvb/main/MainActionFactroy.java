package com.poscodx.mysite.web.mvb.main;

import com.poscodx.web.mvc.Action;
import com.poscodx.web.mvc.ActionFactory;

public class MainActionFactroy implements ActionFactory {

	@Override
	public Action getAction(String actionName) {
		return new MainAction();
	}

}
