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
		} else if ("delete".equals(actionName)) {
			action = new DeleteAction();
		} else if ("modifyform".equals(actionName)) {
			action = new ModifyFormAction();
		} else if ("modify".equals(actionName)) {
			action = new ModifyAction();
		} else if ("replyform".equals(actionName)) {
			action = new ReplyFormAction();
		} else if ("reply".equals(actionName)) {
			action = new ReplyAction();
		} else if ("getpage".equals(actionName)) {
			action = new GetPageAction();
		} else if ("previouspage".equals(actionName)) {
			action = new PreviousPageAction();
		} else if ("nextpage".equals(actionName)) {
			action = new NextPageAction();
		} else if ("search".equals(actionName)) {
			action = new SearchAction();
		} else {
			action = new BoardAction();
		}

		return action;
	}

}
