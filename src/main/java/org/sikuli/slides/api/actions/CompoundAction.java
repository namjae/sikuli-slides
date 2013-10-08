package org.sikuli.slides.api.actions;


import java.util.List;

import org.sikuli.slides.api.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

public class CompoundAction implements Action {

	Logger logger = LoggerFactory.getLogger(getClass());

	private List<Action> actions = Lists.newArrayList();

	public void addChild(Action child){
		actions.add(child);
	}

	public void removeAllChildren(){
		actions.clear();
	}

	public Action getChild(int index){
		if (index >= 0 && index < actions.size()){
			return actions.get(index);
		}else{
			return null;
		}
	}

	public Action hasChild(int index){
		return actions.get(index);
	}


	public List<Action> getChildren(){
		return Lists.newArrayList(actions);
	}

	@Override
	public void execute(Context context) throws ActionExecutionException{

	}

	@Override
	public void stop(){
		for (Action action : getChildren()){
			action.stop();
		}
	}

}

