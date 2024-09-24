//A -> I = E
//E -> P O P | P
//O -> + | - | * | / | **
//P -> I | L | UI | UL | (E)
//U -> + | - | !
//I -> C | CI
//C -> a | b | ... | y | z
//L -> D | DL
//D -> 0 | 1 | ... | 8 | 9

#include <fstream>
#include <iostream>
#include <string>

using std::endl;
using std::ifstream;
using std::ios;
using std::ofstream;
using std::string;

bool A();
bool E();
bool O();
bool P();
bool U();
bool I();
bool C(); 
bool L();
bool D();

std::string s;
int i;

bool A() {
    if(I()) {//I
        if(i < s.length() && (s[i] == '=')) {// =
            ++i;
            if(E()) {//E
                return true;//I = E
            }
        }
    }
    return false;
}

bool E() {
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

bool O() {
    if(i < s.length()) {
        if(s[i] == '+' || s[i] == '-' || s[i] == '*' || s[i] == '/') { // + | - | * | /
            ++i;
            if(i < s.length() && s[i - 1] == '*' && s[i] == '*') { // **
                ++i;
            }  
            return true;
        }
    }
    return false;
} 

bool P() {
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
    if(i < s.length() && s[i] == '(') {// (
        ++i;
        if(E()) {// E
            if(i < s.length() && s[i] == ')') {// )
                ++i;
                return true; //if (E)
            }
        }
    }   
    return false;
}

bool U() {
    if(i < s.length() && (s[i] == '+' || s[i] == '-' || s[i] == '!')) {//+ | - | !
        ++i; //check next char
        return true;
    }
    return false;
}

bool I() {
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

bool C() {
    if(i < s.length() && (s[i] >= 'a' && s[i] <= 'z')) {//a-z
        ++i;//check next char
        return true;
    }
    return false;
}

bool L() {
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

bool D() {
    if(i < s.length() && (s[i] >= '0' && s[i] <= '9')) {//0-9
        ++i; //check the next char
        return true;
    }
    return false;
}

int main(int argc, char *argv[])
{
    ifstream fin("input.txt");
    ofstream fout("output.txt", ios::out | ios::app);
    string buf;
    
    while (getline(fin, buf))
    {
        s = buf;
        i = 0;

        bool isValid = A() && i == s.length();
        std::cout << (isValid ? "Valid" : "Invalid") << ":" << buf << endl;
        //use fout instead of cout to paste to output.txt
    }
    fout.close();
    fin.close();
    return 0;
}