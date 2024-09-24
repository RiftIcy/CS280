//E => T + F | T - F | T
//T => F * T | F / T | F
//F => I | D | (E)
//I => a | ... | z
//D => 0 | ... | 9
public class language {

    private static String s;
    private static int i;
    
    public static boolean D() {
        if(i < s.length() && '0' <= s.charAt(i) && s.charAt(i) <= '9') {
            ++i;
            return true;
        }
        return false;
    }
    public static boolean I() {
        if(i < s.length() && 'a' <= s.charAt(i) && s.charAt(i) <= 'z') {
            ++i;
            return true;
        }
        return false;
    }
    public static boolean F() {
        if(I()) {
            return true;
        }
        else if(D()) {
            return true;
        }
        else if(i < s.length() && s.charAt(i) == '(') {
            ++i;
            if(E()) {
                if(i < s.length() && s.charAt(i) == ')') {
                    ++i;
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean T() {
        if(F()) {
            if(i < s.length() && (s.charAt(i) == '*' || s.charAt(i) == '/')) {
                ++i;
                if(T()) {
                    return true;
                }
                return false;
            }
            return true; 
        }
        return false;
    }
    public static boolean E() {
        if(T()) {
            if(i < s.length() && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
                ++i;
                if(E()) {
                    return true;
                }
                return false;
            }
            return true;
        }
        return false;
    }
    public static void main(String[] args) {
        s = args.length == 1 ? args[0] : "";

        if(E() && i == s.length()) {
            System.out.println("The string \"" + args[0] + "\" is in the language");
        }
        else {
            System.out.println("The string \"" + args[0] + "\" is not in the language");
        }
    }
    
}