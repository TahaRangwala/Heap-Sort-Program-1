/*Name: Taha Rangwala
 * Date: February 15, 2017
 * Purpose: The purpose of the gui class is to allow the user to have an friendly interface
 * in order to perform the tasks that this program provides. This includes input and output fields, buttons,
 * and radio buttons as well.
 */

import BreezySwing.*;//allows for more window objects

import javax.swing.*;//allows for more window objects

import java.awt.*;//allows for more colors

//GUI class header
public class GUI extends GBFrame{
	
	//Declaring private instance variables
	private Heap heapObj;//Heap class object
	//Declaring window objects
	private JLabel numberLabel;
	private IntegerField numberField;
	private JTextArea outputArea;
	private JButton Input, Reset, convertToHeap, switchNumbers, Exit;
	
	//Constructor to initialize private instance variables and window objects
	public GUI(){
		heapObj = new Heap();
		numberLabel = addLabel("Number",1,1,1,1);
		numberField = addIntegerField(0,1,2,1,1);
		Input = addButton("Input Number",2,1,1,1);
		Reset = addButton("Reset",2,2,1,1);
		outputArea = addTextArea("",3,1,2,1);
		convertToHeap = addButton("Convert To Heap",4,1,1,1);
		Exit = addButton("Exit",4,2,1,1);
		numberField.setText("");
	}
	
	/*Purpose: The purpose of this method is to detect which button the user presses and then it performs whatever task
	 * that the button is associated with
	 * @param buttonObj This is the button object which holds the value of what button the user has pressed
	 */
	public void buttonClicked(JButton buttonObj){
		if(buttonObj == Input){//Input button
			if(checkNumberField()){
				heapObj.addNumber(numberField.getNumber());
				outputArea.setText(heapObj.toString());
				numberField.setText("");
				numberField.grabFocus();
			}
			else
				JOptionPane.showMessageDialog(new JFrame(),"Number Values Are Not Valid Integers!", "Error Message", JOptionPane.ERROR_MESSAGE);
		}
		else if(buttonObj == Reset){//Reset button
			heapObj = new Heap();
			outputArea.setText("");
			numberField.setText("");
			numberField.grabFocus();
		}
		else if(buttonObj == convertToHeap){//Convert tree to a heap
			try{
				heapObj.HeapifyUp();
				outputArea.setText(outputArea.getText() + heapObj.switchNumbers());
			}
			catch(IllegalArgumentException E){
				JOptionPane.showMessageDialog(new JFrame(),E.getLocalizedMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
			}
		}
		else
			this.dispose();//closes the program
	}
	
	/*Purpose: Determines if number in number fields are valid integers
	 * @return Returns a boolean value of whether or not the numbers are valid integers
	 */
	private boolean checkNumberField(){
		return numberField.isValidNumber();
	}
	
	// Main method to set up the GUI
	public static void main (String [] args){
		GUI theMainGUI = new GUI();
		theMainGUI.setSize(300,400);//size of the GUI interface
		theMainGUI.setTitle("Taha's Heap Sort Program");//title of GUI interface
		theMainGUI.setLookAndFeel("MOTIF");//This changes the look of the GUI interface
		theMainGUI.setVisible(true);//visibility of interface
		theMainGUI.setLocationRelativeTo(null);//Location is in center of screen
		theMainGUI.getContentPane().setBackground(new Color(169,229,255));//background of GUI is light blue
	}
}
