package org.sikuli.slides.api.interpreters;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.sikuli.api.robot.desktop.DesktopScreen;
import org.sikuli.slides.api.Context;
import org.sikuli.slides.api.actions.Action;
import org.sikuli.slides.api.actions.ActionExecutionException;
import org.sikuli.slides.api.io.PPTXSlidesReader;
import org.sikuli.slides.api.io.SlidesReader;
import org.sikuli.slides.api.models.Slide;

public class ConfigInterpreterTest {

	private Slide slide;
	private ConfigInterpreter interpreter;
	private Context context;

	@Before
	public void setUp() throws IOException{
		slide = new Slide();
		interpreter = new ConfigInterpreter(); 
		context = new Context();
	}
	
	private Slide readSlide(String fileName, int index) throws IOException{
		SlidesReader reader = new PPTXSlidesReader();		
		List<Slide> slides;		
		slides = reader.read(getClass().getResource(fileName));					
		return slides.get(index);
	}

	@Test
	public void testCanConfigTwoOptions() throws IOException, ActionExecutionException{
		
		slide = readSlide("ConfigTwoOptions.pptx",0);
		Action action = interpreter.interpret(slide);
		
		assertThat(action, notNullValue());
		
		action.execute(context);
		assertThat((double) context.getMinScore(), closeTo(0.5,0.01));

	}
	
	@Test
	public void testConfigRange() throws IOException, ActionExecutionException{
		
		slide = readSlide("ConfigRange.pptx",0);		
		Action action = interpreter.interpret(slide);
		
		assertThat(action, notNullValue());
		
//		action.execute(context);
//		assertThat((double) context.getMinScore(), closeTo(0.5,0.01));
	}
	
	@Test
	public void testConfigMinScore() throws IOException, ActionExecutionException{
		
		SlidesReader reader = new PPTXSlidesReader();		
		List<Slide> slides;		
		slides = reader.read(getClass().getResource("ConfigMinScore.pptx"));	
		
		System.out.println(slides.get(0));
		
		slide = slides.get(0);
		Action action = interpreter.interpret(slide);
		
		assertThat(action, notNullValue());
		
		action.execute(context);
		assertThat((double) context.getMinScore(), closeTo(0.5,0.01));

	}

	@Test
	public void testConfigScreen() throws IOException, ActionExecutionException{
		
		SlidesReader reader = new PPTXSlidesReader();		
		List<Slide> slides;		
		slides = reader.read(getClass().getResource("ConfigScreen.pptx"));				
		slide = slides.get(0);
		
		Action action = interpreter.interpret(slide);
		
		assertThat(action, notNullValue());		
		action.execute(context);
		
		int id = ((DesktopScreen) context.getScreenRegion().getScreen()).getId();
		assertThat(id, equalTo(1));

	}

}
