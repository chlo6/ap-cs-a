/**
 *	FirstAssignment.java
 *	Display a brief description of your summer vacation on the screen.
 *
 *	To compile Linux:	javac -cp .:mvAcm.jar FirstAssignment.java
 *	To execute Linux:	java -cp .:mvAcm.jar FirstAssignment
 *
 *	To compile MS Powershell:	javac -cp ".;mvAcm.jar" FirstAssignment.java
 *	To execute MS Powershell:	java -cp ".;mvAcm.jar" FirstAssignment
 *
 *	@author	Chloe He
 *	@since	8.23.24
 */
import java.awt.Font;

import acm.program.GraphicsProgram;
import acm.graphics.GLabel;

public class FirstAssignment extends GraphicsProgram {
    
    public void run() {
    	//	The font to be used
    	Font f = new Font("Serif", Font.BOLD, 18);
    	
    	//	Line 1
    	GLabel s1 = new GLabel("What I did on my summer vacation ...", 10, 20);
    	s1.setFont(f);
    	add(s1);
    	    	
    	// Line 2
    	GLabel s2 = new GLabel("I started off my first two weeks of summer by volunteering at a nature summer camp. In my ", 10, 40);    	
    	s2.setFont(f);
    	add(s2);
    	
    	// Line 3
    	GLabel s3 = new GLabel("first week, I worked the climbing wall, ensuring that all the kids were safe when climbing on it.", 10, 60);    	
    	s3.setFont(f);
    	add(s3);
    	
    	// Line 4
    	GLabel s4 = new GLabel("In the second week, I worked with the kids who were first generation immigrant children, so ", 10, 80);    	
    	s4.setFont(f);
    	add(s4);
    	
    	// Line 5
    	s4 = new GLabel("I was able to listen to a lot of their stories, which really shifted my perspective and broadened", 10, 100);    	
    	s4.setFont(f);
    	add(s4);
    	
    	// Line 6
    	s4 = new GLabel("my horizons.", 10, 120);    	
    	s4.setFont(f);
    	add(s4);
    	
    	// Line 7
    	s4 = new GLabel("Then I did some research stuff which was pretty cool", 10, 140);    	
    	s4.setFont(f);
    	add(s4);
    	
    	// Line 8
    	s4 = new GLabel("And then I went to Yellowstone National Park and saw a lot of animals and really nice views.  ", 10, 160);    	
    	s4.setFont(f);
    	add(s4);
    	
    	// Line 9
    	s4 = new GLabel("My favorites were the really picturesque landscapes and the pools like Morning Glory. ", 10, 180);    	
    	s4.setFont(f);
    	add(s4);
    	
    	// Line 10
    	s4 = new GLabel("After that, I spent my summer eating various things. I really like eating Haagen Dazs ice", 10, 200);    	
    	s4.setFont(f);
    	add(s4);
    	
    	// Line 11
    	s4 = new GLabel("cream, especially the vanilla popsicle with the chocolate and almond coating. It's so crunchy", 10, 220);    	
    	s4.setFont(f);
    	add(s4);
    	
    	// Line 12
    	s4 = new GLabel("and cold and sweet and all the flavors taste so insanely good together. It is truly a delectable", 10, 240);    	
    	s4.setFont(f);
    	add(s4);
    	
    	// Line 13
    	s4 = new GLabel("masterpiece! In an ideal world where stomach aches did not exist, I would be eating them every", 10, 260);    	
    	s4.setFont(f);
    	add(s4);
    	
    	// Line 14
    	s4 = new GLabel("single day.", 10, 280);    	
    	s4.setFont(f);
    	add(s4);
    	
    	// Line 15
    	s4 = new GLabel("I had a fun summer filled with tasty food!!!", 10, 300);    	
    	s4.setFont(f);
    	add(s4);
    	
    	// Line 16
    	s4 = new GLabel("I also got to spend a lot of time watching shows I usually can't watch (due to time restrictions)", 10, 320);    	
    	s4.setFont(f);
    	add(s4);
    	//	Continue adding lines until you have 12 to 15 lines
    	
    }
    
}
