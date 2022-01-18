import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Main implements ActionListener
//implements ActionListener inherits all properties of ActionListener
{
	JFrame frame;
	JTextField textfield; 
	//Difference between JLabel and JTextField is JTextField can also take input, JLabel just shows messages
	JButton[] numberButtons=new JButton[10]; //Array of 10 number button(0-9)
	JButton[] functionButtons=new JButton[9];
	JButton addButton, subButton, mulButton, divButton;
	JButton decimalButton, equalsButton, delButton, clearButton, negButton;
	JPanel panel;
	
	Font myFont=new Font("MV Boli", Font.BOLD, 25); 
	//Font class from java.awt.Font stores the font can be reused for all button
	
	double num1=0, num2=0, result=0;
	char operator; //Hold the function on the operands
	int count=0;
	
	public Main()
	{
		frame=new JFrame("Calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420, 550);
		frame.setResizable(false);
		frame.setLayout(null);
		//When you use null layout you can specify exact location of each element
		
		textfield=new JTextField();
		textfield.setBounds(50,25,300,50);
		//(x position, y position, width, height)- arguments for setBounds
		textfield.setFont(myFont);
		//Reusing font appearing in textfield
		textfield.setEditable(false);
		//Prevents user from typing in it
		textfield.setText(String.valueOf(num1));
		textfield.setBackground(new Color(220,220,220));

		addButton=new JButton("+"); //Parameter in JButton setsText
		subButton=new JButton("-");
		mulButton=new JButton("*");
		divButton=new JButton("/");
		decimalButton=new JButton(".");
		equalsButton=new JButton("=");
		delButton=new JButton("Del");
		clearButton=new JButton("C");
		negButton=new JButton("(-)");
		
		functionButtons[0]=addButton;
		functionButtons[1]=subButton;
		functionButtons[2]=mulButton;
		functionButtons[3]=divButton;
		functionButtons[4]=decimalButton;
		functionButtons[5]=equalsButton;
		functionButtons[6]=delButton;
		functionButtons[7]=clearButton;
		functionButtons[8]=negButton;
		
		for(int i=0; i<9; i++)
		{
			functionButtons[i].addActionListener(this);
			functionButtons[i].setFont(myFont);
			functionButtons[i].setFocusable(false);
		}
		

		for(int i=0; i<10; i++)
		{
			numberButtons[i]=new JButton(String.valueOf(i));
			//If I would put 'i' it would print i thats why String.valueOf takes value of 'i' and puts it as a String
			numberButtons[i].addActionListener(this);
			numberButtons[i].setFont(myFont);
			numberButtons[i].setFocusable(false);
		}
		
		delButton.setBounds(50,430,90,50);
		negButton.setBounds(150,430,93,50); 
		clearButton.setBounds(250,430,100,50); 
		//Clear button just shifts horizontally hence x only changes
		
		panel=new JPanel();
		panel.setBounds(50,100,300,300); //300 by 300 so its a square
		//panel is holding all number buttons in grid layout
		panel.setLayout(new GridLayout(4,4,10,10));
		//4,4 is the number of buttons and 10, 10 is 10 pixels on each side of the button

		//First row
		panel.add(numberButtons[1]);
		panel.add(numberButtons[2]);
		panel.add(numberButtons[3]);
		panel.add(addButton);
		
		//Second row
		panel.add(numberButtons[4]);
		panel.add(numberButtons[5]);
		panel.add(numberButtons[6]);
		panel.add(subButton);
		
		panel.add(numberButtons[7]);
		panel.add(numberButtons[8]);
		panel.add(numberButtons[9]);
		panel.add(mulButton);
		
		panel.add(decimalButton);
		panel.add(numberButtons[0]);
		panel.add(equalsButton);
		panel.add(divButton);
		
		frame.add(panel);
		frame.add(delButton);
		frame.add(negButton);
		frame.add(clearButton);			
		frame.add(textfield);
		frame.setVisible(true);
	}
	public static void main(String[] args) 
	{
		@SuppressWarnings("unused")
		Main obj=new Main();
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(count==0)
		{
			textfield.setText("");
			count++;
		}
		for(int i=0;i<10; i++)
		{
			if(e.getSource()==numberButtons[i])
			{
				textfield.setText(textfield.getText().concat(String.valueOf(i)));
				//It gets the text of the textfield(if present) and adds the value of i to it and sets that Text back
			}
		}
		if(e.getSource()==decimalButton)
			textfield.setText(textfield.getText().concat("."));
		if(e.getSource()==addButton)
		{
			num1=Double.parseDouble(textfield.getText());
			operator='+';
			textfield.setText("");
		}
		if(e.getSource()==subButton)
		{
			num1=Double.parseDouble(textfield.getText());
			operator='-';
			textfield.setText("");
		}
		if(e.getSource()==mulButton)
		{
			num1=Double.parseDouble(textfield.getText());
			operator='*';
			textfield.setText("");
		}
		if(e.getSource()==divButton)
		{
			num1=Double.parseDouble(textfield.getText());
			operator='/';
			textfield.setText("");
		}
		if(e.getSource()==equalsButton)
		{
			num2=Double.parseDouble(textfield.getText());
			switch(operator)
			{
				case '+':
					result=num1+num2;
					break;
				case '-':
					result=num1-num2;
					break;
				case '*':
					result=num1*num2;
					break;
				case '/':
					result=num1/num2;
					break;
			}
			textfield.setText(String.valueOf(result));
			//This should be inside the equalsButton case otherwise it will set the result before clicking equals
			num1=result; //To continue if we want to reuse the same number
		}
		if(e.getSource()==clearButton)
		{
			count=0;
			num1=0; num2=0; //If i don't do this and only setText to empty it will be empty but will still remember value if a function button was pressed
			textfield.setText(String.valueOf(num1));
		}
		if(e.getSource()==delButton)
		{
			String string=textfield.getText();
			textfield.setText("");
			for(int i=0; i<string.length()-1; i++)
			//Doesn't count the last element and sets the text for the rest
			{
				textfield.setText(textfield.getText().concat(String.valueOf(string.charAt(i))));
			}
		}
		if(e.getSource()==negButton)
			textfield.setText("-");			
	}
}
