package org.sikuli.slides.api.actions;

import org.sikuli.slides.api.Context;

public class SlideAction extends ChainedAction {
	
	@Override
	public void execute(Context context) throws ActionExecutionException {
		Action child = getChild();
		if (child != null){
			child.execute(context);
		}
	}
	
}
