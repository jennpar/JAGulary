package activities;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.FileController;
import jagulary.JAGgui;

public class Bingo {
	
	private static final boolean RIGHT_TO_LEFT = false;
	private static ArrayList<String> array = null;
    private static ArrayList<String> definitions = new ArrayList<String>();
    private static ArrayList<String> words = new ArrayList<String>();
    private static int index = 0;
	private static int defIndex = 0;
	private static int row2 = 0, row3 = 0, row4 = 0, row5 = 0, row6 = 0;
	private static int col1 = 0, col2 = 0, col3 = 0, col4 = 0, col5 = 0;
	private static int diag1 = 0, diag2 = 0;
	private static int maxNum = 5;
	private static String getToken = "C:\\Users\\jennp\\eclipse-workspace\\JAGulary\\JagToken.png";
//    public static String library = "";
//    public static String chapter = "";
	

	public static JPanel addComponentsToBingoAuto(String library, String chapter) throws FileNotFoundException {
		JPanel pane = new JPanel();
		if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }
		pane.setBackground(new Color(0, 32, 91));
   	 	JButton backButton, nextButton;
        JLabel defLabel, cat1Label;
        pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		index = 0;
		
		array = FileController.scanFileToArray(library + "\\" + chapter);
		definitions = FileController.getDefinition(array);
		words = FileController.getWords(array);
		defIndex = 0;
		int max = definitions.size()-1;
		int numbersNeeded = 25;
		if (max < numbersNeeded)
		{
		    throw new IllegalArgumentException("Can't ask for more numbers than are available");
		}
		Random rng = new Random(); // Ideally just create one instance globally
		// Note: use LinkedHashSet to maintain insertion order
		Set<Integer> generated = new LinkedHashSet<Integer>();
		while (generated.size() < numbersNeeded)
		{
		    Integer next = rng.nextInt(max) + 1;
		    // As we're adding to a set, this will automatically do a containment check
		    generated.add(next);
		}
		 			
		ArrayList <Integer> defNumbers = new ArrayList<Integer>();
		defNumbers.addAll(0, generated);
		
//			String theWord = words.get(defIndex);				
//			JLabel wordLabel = new JLabel(theWord);
		
		String def = definitions.get(defNumbers.get(0));
		defLabel = new JLabel("<html><center>" + def);
		defLabel.setHorizontalAlignment(JLabel.CENTER);
		defLabel.setFont(new Font("Snap ITC", Font.PLAIN, 30));
		defLabel.setForeground(Color.WHITE);
		defLabel.setBackground(new Color(191, 13, 62));
		c.fill = GridBagConstraints.HORIZONTAL; //makes label horizontal
		//c.ipady = 10;      //makes label taller
		//c.ipadx = 200;		//makes label wider
		c.gridx = 1;	
		c.gridy = 9;	//row 1
		c.gridwidth =  5;
		c.gridheight = 1;
		c.weightx = 1.0;
	  	c.weighty = 1.0;
		pane.add(defLabel, c); 
		
		ArrayList <Integer> wordNumbers = new ArrayList<Integer>();
		wordNumbers.addAll(0, generated);
		Collections.sort(wordNumbers);
					
		backButton = new JButton("Back");
  		createRedButton(backButton);
  		//c.ipady = 30;       //reset to default
  		c.gridy = 10;       //row 2
  		c.gridx = 1;
  		c.gridwidth =  1;
		c.gridheight = 1;
  		c.weightx = 1.0;
  		c.weighty = 1.0;
  		pane.add(backButton, c);
  		
  		nextButton = new JButton("Next");
  		createRedButton(nextButton);
  		//c.ipady = 30;       //reset to default
  		c.gridy = 10;       //row 2
  		c.gridx = 5;
  		c.gridwidth =  1;
		c.gridheight = 1;
  		c.weightx = 1.0;
  		c.weighty = 1.0;
  		pane.add(nextButton, c);
  		
  		nextButton.addActionListener( new ActionListener()
		{
		    @Override
		    public void actionPerformed(ActionEvent ae)
		    {
		    	index += 1;
		    	defLabel.setText("<html><center>" + definitions.get(defNumbers.get(index)));
		    }
		});
  		
  		backButton.addActionListener( new ActionListener()
		{
		    @Override
		    public void actionPerformed(ActionEvent e)
		    {	    	
			    JAGgui.frame.setContentPane(JAGgui.addComponentsToClassPane());
		    	JAGgui.frame.revalidate();
		    	
		    }
		});
		
		String [] array = {"B", "I", "N", "G", "O"};
		 int x = 1;
		 for (int i = 0; i < array.length; i++)
		 {
			 cat1Label = new JLabel(array[i]);
			 	cat1Label.setSize(100,50);
				createLabel(cat1Label);
				c.gridy = 1;       //row 2
				c.gridx = x;
				c.gridwidth =  1;
				c.gridheight = 1;
				c.weightx = 1.0;
		  		c.weighty = 1.0;
				pane.add(cat1Label, c);
				x++;
		 }
		int z = 0;
		int wordIndex = 0;
		for (int row = 2; row < 7; row++ )
		{
		 for (int column = 1; column < 6; column++)
		 {
			 if (wordIndex < words.size())
			 {
				 String word = words.get(wordNumbers.get(wordIndex));
				 //JButton btn = new JButton(Integer.toString(number));
				 JButton button = new JButton(word);	
				 //JButton btn = new JButton("Contingency perspective");
//					 btn.setSize(100, 100);
//					 createBingoButton(btn, 0);
				 button.setName("" + z);
				 button.setFont(new Font("Snap ITC", Font.PLAIN, 20));
				 button.setBackground(Color.WHITE);
				 button.setForeground(new Color(191, 13, 62));
				 button.setBorder(BorderFactory.createLineBorder(new Color(191, 13, 62)));
				 button.setPreferredSize(new Dimension(100, 100));
				 button.setMaximumSize(new Dimension(100, 100));
				 button.setMinimumSize(new Dimension (100, 100));
				 //c.fill = GridBagConstraints.HORIZONTAL;
				 //c.ipady = 40;       //reset to default
														 
				 c.gridy = row;
				 c.gridx = column;
				 c.gridwidth =  1;
				 c.gridheight = 1;
				 c.weightx = 1.0;
			  	 c.weighty = 1.0;
				 pane.add(button, c);
				 wordIndex++;
				 z++;
				 
//					 if(compare == 1)
//					 {
				 button.addActionListener( new ActionListener()
				 {
					   @Override
					   public void actionPerformed(ActionEvent e)
					   {	
						   JButton source = (JButton) e.getSource();
						   if(button.getText().compareToIgnoreCase(words.get(defNumbers.get(index)))==0) {
							   int num = Integer.parseInt(button.getName());
							   button.setIcon(new ImageIcon(getToken));
//							   System.out.println("Correct");
							   index += 1;
							   defLabel.setText("<html><center>" + definitions.get(defNumbers.get(index)));
							   int bingo = detectBingo(num);
							   if (bingo == 1) {
								   JAGgui.frame.setContentPane(winBingo());
				    		    	JAGgui.frame.revalidate();
							   }
//							   System.out.println(num);
//							   System.out.println(source);
						   }
						   else {
//							   System.out.println("WRONG");
							   index += 1;
							   defLabel.setText("<html><center>" + definitions.get(defNumbers.get(index)));
						   }
								 
							   	
					   }
				 });
			 }
			}
		}
		return pane;			
	}
   
	public static void createRedButton(JButton button) {	   
		
		GridBagConstraints c = new GridBagConstraints(); 
		    	 
		button.setFont(new Font("Snap ITC", Font.PLAIN, 25));
		button.setForeground(Color.WHITE);
		button.setBackground(new Color(191, 13, 62));
		button.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		button.setPreferredSize(new Dimension(200, 100));
		    	 
     }
	
	public static void createLabel(JLabel label) {
   	 
	   	 GridBagConstraints c = new GridBagConstraints(); 
	   	 label.setHorizontalAlignment(JLabel.CENTER);
		 label.setFont(new Font("Snap ITC", Font.PLAIN, 40));
		 label.setForeground(new Color(191, 13, 62));
		 label.setPreferredSize(new Dimension(200, 100));

    }
	
	public static int detectBingo(int x) {
		
		switch (x){
		case 0: col1 +=1;
				row2 +=1;
				diag1 +=1;
				if (col1 == maxNum||row2 == maxNum||diag1 == maxNum) {
					return 1;
				}break;
		case 1: col2 ++;
				row2 ++;
				if (col2 == maxNum||row2 == maxNum) {
					return 1;
				}break;
		case 2: col3 ++;
				row2 ++;
				if (col3 == maxNum||row2 == maxNum) {
					return 1;
				}break;
		case 3: col4 ++;
				row2 ++;
				if (col4 == maxNum||row2 == maxNum) {
					return 1;
				}break;
		case 4: col5 ++;
				row2 ++;
				diag2 ++;
				if (col5 == maxNum||row2 == maxNum||diag2 == maxNum) {
					return 1;
				}break;
				
		case 5: col1 ++;
				row3 ++;
				if (col1 == maxNum||row3 == maxNum) {
					return 1;
				}break;
		case 6: col2 ++;
				row3 ++;
				diag1 ++;
				if (col2 == maxNum||row3 == maxNum||diag1 == maxNum) {
					return 1;
				}break;
		case 7: col3 ++;
				row3 ++;
				if (col3 == maxNum||row3 == maxNum) {
					return 1;
				}break;
		case 8: col4 ++;
				row3 ++;
				diag2 ++;
				if (col4 == maxNum||row3 == maxNum||diag2 == maxNum) {
					return 1;
				}break;
		case 9: col5 ++;
				row3 ++;
				if (col5 == maxNum||row3 == maxNum) {
					return 1;
				}break;
				
		case 10: col1 ++;
				 row4 ++;
				 if (col1 == maxNum||row4 == maxNum) {
						return 1;
					}break;
		case 11:col2 ++;
				row4 ++;
				if (col2 == maxNum||row4 == maxNum) {
					return 1;
				}break; 
		case 12:col3 ++;
				row4 ++;
				diag1 ++;
				diag2 ++;
				if (col3 == maxNum||row4 == maxNum||diag1 == maxNum||diag2 == maxNum) {
					return 1;
				}break;
		case 13:col4 ++;
				row4 ++;
				if (col4 == maxNum||row4 == maxNum) {
					return 1;
				}break;
		case 14:col5 ++;
				row4 ++;
				if (col5 == maxNum||row4 == maxNum) {
					return 1;
				}break;
				
		case 15:col1 ++;
				row5 ++;
				if (col1 == maxNum||row5 == maxNum) {
					return 1;
				}break;
		case 16:col2 ++;
				row5 ++;
				diag2 ++;
				if (col2 == maxNum||row5 == maxNum||diag2 == maxNum) {
					return 1;
				}break;
		case 17:col3 ++;
				row5 ++;
				if (col3 == maxNum||row5 == maxNum) {
					return 1;
				}break;
		case 18:col4 ++;
				row5 ++;
				diag1 ++;
				if (col4 == maxNum||row5 == maxNum||diag1 == maxNum) {
					return 1;
				}break;
		case 19:col5 ++;
				row5 ++;
				if (col5 == maxNum||row5 == maxNum) {
					return 1;
				}break;
				
		case 20:col1 ++;
				row6 ++;
				diag2 ++;
				if (col1 == maxNum||row6 == maxNum||diag2 == maxNum) {
					return 1;
				}break;
		case 21:col2 ++;
				row6 ++;
				if (col2 == maxNum||row6 == maxNum) {
					return 1;
				}break;
		case 22:col3 ++;
				row6 ++;
				if (col3 == maxNum||row6 == maxNum) {
					return 1;
				}break;
		case 23:col4 ++;
				row6 ++;
				if (col4 == maxNum||row6 == maxNum) {
					return 1;
				}break;
		case 24:col5 ++;
				row6 ++;
				if (col5 == maxNum||row6 == maxNum||diag1 == maxNum) {
					return 1;
				}break;
		default: return 0;
		}
		return maxNum;
		
	}
	
	public static JPanel winBingo() {
		
		JPanel pane = new JPanel();
        if (RIGHT_TO_LEFT) 
        {
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }
        pane.setBackground(new Color(0, 32, 91));
    	pane.setLayout(new GridBagLayout());
    	GridBagConstraints c = new GridBagConstraints();
    	
    	JButton backButton = new JButton("Back");
  		createRedButton(backButton);
//  		c.ipady = 30;       //reset to default
  		c.gridy = 3;       //row 2
  		c.gridx = 2;
  		c.weightx = 1.0;
  		c.weighty = 1.0;
  		pane.add(backButton, c);
  		
  		backButton.addActionListener( new ActionListener()
		{
		    @Override
		    public void actionPerformed(ActionEvent e)
		    {	    	
		    	JAGgui.frame.setContentPane(JAGgui.addComponentsToClassPane());
		    	JAGgui.frame.revalidate();
		    }
		});
  		
  		JLabel quizResultsLabel = new JLabel("BINGO!!!");
		createLabel(quizResultsLabel);
    	quizResultsLabel.setFont(new Font("SNAP ITC", Font.PLAIN, 60));
		c.ipady = 200;      //makes label taller
		c.ipadx = 300;		//makes label wider
		c.gridx = 1;	
		c.gridy = 0;
		c.weightx = 1.0;
  		c.weighty = 1.0;
		pane.add(quizResultsLabel, c);
		
		JLabel correctQuizQuestionLabel = new JLabel("You win!");
		createLabel(correctQuizQuestionLabel);
    	correctQuizQuestionLabel.setFont(new Font("SNAP ITC", Font.PLAIN, 30));
		correctQuizQuestionLabel.setForeground(Color.WHITE);
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 1.0;
  		c.weighty = 1.0;
		pane.add(correctQuizQuestionLabel, c);
		
		JLabel correctQuizResultLabel = new JLabel("Congrats");
		createLabel(correctQuizResultLabel);
    	correctQuizResultLabel.setFont(new Font("SNAP ITC", Font.PLAIN, 30));
		correctQuizResultLabel.setForeground(Color.WHITE);
		c.gridx = 1;
		c.gridy = 2;
		c.weightx = 1.0;
  		c.weighty = 1.0;
		pane.add(correctQuizResultLabel, c);
		
		return pane;
		
	}
}
