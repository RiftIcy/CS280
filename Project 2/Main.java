//A -> I = E
//E -> P O P | P
//O -> + | - | * | / | **
//P -> I | L | UI | UL | (E)
//U -> + | - | !
//I -> C | CI
//C -> a | b | ... | y | z
//L -> D | DL
//D -> 0 | 1 | ... | 8 | 9

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    private static String s;
    private static int i;

    public static boolean A() {
        if(I()) {//I
            if(i < s.length() && (s.charAt(i) == '=')) {// =
                ++i;
                if(E()) {//E
                    return true;//I = E
                }
            }
        }
        return false;
    }
    
    public static boolean E() {
        int startPos = i;
        if(P()) { //P
            if(O()) {//O
                if(P()) {//P
                    return true;//P O P
                }
            }
        }
        i = startPos;//go back to P
        if(P()) {//P
            return true;
        }
        return false;
    }
    
    public static boolean O() {
        if(i < s.length()) {
            if(s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '*' || s.charAt(i) == '/') { // + | - | * | /
                ++i;
                if(i < s.length() && s.charAt(i - 1) == '*' && s.charAt(i) == '*') { // **
                    ++i;
                }
                return true;
            }
        }
        return false;
    }

    public static boolean P() {
        int startPos = i;
        if(I()) { //I
            return true;
        }
        i = startPos;
        if(L()) {//L
            return true;    
        }
        i = startPos;
        if(U() && I()) {//UI
            return true;
        }
        i = startPos;
        if(U() && L()) {//UL
            return true;
        }
        i = startPos;
        if(i < s.length() && s.charAt(i) == '(') {// (
            ++i;
            if(E()) {// E
                if(i < s.length() && s.charAt(i) == ')') {// )
                    ++i;
                    return true; //if (E)
                }
            }
        }   
        return false;
    }

    public static boolean U() {
        if(i < s.length() && (s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '!')) {//+ | - | !
            ++i; //check next char
            return true;
        }
        return false;
    }

    public static boolean I() {
        int startPos = i;
        if(C()) {//C
            if(I()) {//CI
                return true;
            }
            return true;
        }
        i = startPos;
        return false;
    }

    public static boolean C() {
        if(i < s.length() && (s.charAt(i) >= 'a' && s.charAt(i) <= 'z')) {//a-z
            ++i;//check next char
            return true;
        }
        return false;
    }

    public static boolean L() {
        int startPos = i;
        if(D()) {//D
            if(L()) {//DL
                return true;
            }
            return true;// if its a D
        }
        i = startPos;
        return false;
    }

    public static boolean D() {
        if(i < s.length() && (s.charAt(i) >= '0' && s.charAt(i) <= '9')) {//0-9
            ++i; //check the next char
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        File in = new File("input.txt");
        File out = new File("output.txt");
        
        try (Scanner scan = new Scanner(in);
            PrintWriter pw = new PrintWriter(new FileWriter(out, true))) {
                while (scan.hasNextLine()) {//reads one line at a time
                    String line = scan.nextLine();
                    s = line.trim();//trims the whitespaces
                    i = 0;

                    if (A() && i == s.length()) {
                        System.out.println("The string \"" + s + "\" is in the language.");
                    } else {
                        System.out.println("The string \"" + s + "\" is not in the language.");
                    }
                }
        }
        
        catch (IOException e) {
            System.out.println("Error reading file: input.txt");
            e.printStackTrace();
            System.exit(0);
        }
    }
}
