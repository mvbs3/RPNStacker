import java.util.Stack;
import java.io.*;


public class RPNStacker {
    public static void main(String[] args) {
      try {
      String in, out;
      String a,b;
      Stack<String> myStack = new Stack();
      FileInputStream myFile = new FileInputStream("Calc1.stk");
      BufferedReader buffer = new BufferedReader(new InputStreamReader(myFile));

      while((in = buffer.readLine()) != null){
        if(in.equals("*") || in.equals("/") || in.equals("+") || in.equals("-") || in.equals("^")){
            b = (String) myStack.pop();
            a = (String) myStack.pop();
            switch (in){
              case "*":
                  myStack.push(Double.toString(Double.parseDouble(a) * Double.parseDouble(b)));
                  break;
              case "/":
                  myStack.push(Double.toString(Double.parseDouble(a) / Double.parseDouble(b)));
                  break;
              case "+":
                  myStack.push(Double.toString(Double.parseDouble(a) + Double.parseDouble(b)));
                  break;
              case "-":
                  myStack.push(Double.toString(Double.parseDouble(a) - Double.parseDouble(b)));
                  break;
              case "^":
                  myStack.push(Double.toString(Math.pow(Double.parseDouble(a), Double.parseDouble(b))));
                  break;
                }     
        }else{
          myStack.push(in);
        }
        
        //System.out.println(in);
      }
      System.out.println(myStack.pop());     

      buffer.close();
      }catch (IOException except){
        System.out.println("File was not found.");
      }
      
    }
  }