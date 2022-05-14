import java.util.Stack;
import java.util.ArrayList;
import java.io.*;
import stacker.rpn.lexer.Token;
import stacker.rpn.lexer.TokenType;

public class RPNStacker {
  static ArrayList<Token> scanner() {
    Token token = new Token(TokenType.EOF, "");
    ArrayList<Token> tokens = new ArrayList<Token>();
    try{
      String inLocal;
      FileInputStream myFile = new FileInputStream("Calc1.stk");
      BufferedReader buffer = new BufferedReader(new InputStreamReader(myFile));

      
      
      while((inLocal = buffer.readLine()) != null){
        if(inLocal.equals("+")){
          token = new Token(TokenType.PLUS, "+");
        } else if(inLocal.equals("-")){
          token = new Token(TokenType.MINUS, "-");
        }else if(inLocal.equals("*")){
          token = new Token(TokenType.STAR, "*");
        }else if(inLocal.equals("/")){
          token = new Token(TokenType.SLASH, "/");
        }else{
          String tester = "0123456789";
          int flag =1;
          for (int i=0; i<inLocal.length(); i++) {
            char c = inLocal.charAt(i);
            if(tester.indexOf(c)==-1){
              flag=0;
              break;
            }       
          }
          if (flag==1){
            token = new Token(TokenType.NUM, inLocal);
          } else{
              System.out.println("Error: Unexpected character: " + inLocal);
              System.exit(-1);
          }
        }
        tokens.add(token);
      }
      buffer.close();
    }catch(IOException except){
      System.out.println("File was not found.");
    }
    return tokens;
  }
    public static void main(String[] args) {
      String in, out;
      String a,b,tokenAux;
      ArrayList<Token> tokenList = new ArrayList<Token>();
      Stack<String> myStack = new Stack<String>();
      
      tokenList = scanner();
      for (Token count : tokenList){
        System.out.println(count.toString());
        in = count.lexeme;
        if(in.equals("*") || in.equals("/") || in.equals("+") || in.equals("-") || in.equals("^")){
            b =  myStack.pop();
            a =  myStack.pop();
            //CHECAR SE O TOKEN EH VALIDO SE NAO FOR, SAI, SE FOR FAZ!
            switch (in){
              case "*":
                  myStack.push(Integer.toString(Integer.parseInt(a) * Integer.parseInt(b)));
                  break;
              case "/":
                  myStack.push(Integer.toString(Integer.parseInt(a) / Integer.parseInt(b)));
                  break;
              case "+":
                  myStack.push(Integer.toString(Integer.parseInt(a) + Integer.parseInt(b)));
                  break;
              case "-":
                  myStack.push(Integer.toString(Integer.parseInt(a) - Integer.parseInt(b)));
                  break;
                }     
        }else{
          myStack.push(in);
        }
        
        //System.out.println(in);
      }
      System.out.println(myStack.pop());     

      
    }
  }