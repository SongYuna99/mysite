package com.poscodx.mysite.web.mvc.board;

import com.poscodx.web.mvc.Action;
import com.poscodx.web.mvc.ActionFactory;

public class BoardActionFactory implements ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;

		if ("writeform".equals(actionName)) {
			action = new WriteFormAction();
		} else if ("write".equals(actionName)) {
			action = new WriteAction();
		} else if ("view".equals(actionName)) {
			action = new ViewAction();
		}  else if ("delete".equals(actionName)) {
			action = new DeleteAction();
		} else {
			action = new BoardAction();
		}

		return action;
	}

}