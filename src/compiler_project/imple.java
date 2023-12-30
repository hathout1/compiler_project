package compiler_project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class imple {
    static String s;
    static int i;

    public static void main(String[] args) {
        System.out.println("Given grammar is ");
        System.out.println("S -> aBb/ccA");
        System.out.println("A -> b/c");
        System.out.println("B -> a/b");
        System.out.println("Enter the string: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            s = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        i = 0;

        S();

        if (i == s.length())
            System.out.println("String is valid");
        else
            System.out.println("String is invalid");

        String line;
        List<String> operators = Arrays.asList("=","+","<",">","*","-","%");
        List<String> separators = Arrays.asList(",","{","}","[","]","(",")");
        BufferedReader fileReader;
        try {
            fileReader = new BufferedReader(new FileReader("Input.txt"));
            while ((line = fileReader.readLine()) != null) {
                for (int j = 0; j < line.length(); j++) {
                    char ch = line.charAt(j);
                    if (operators.contains(Character.toString(ch))) {
                        System.out.println(ch + " is an operator");
                    } else if (separators.contains(Character.toString(ch))) {
                        System.out.println(ch + " is a separator");
                    }
                }
                String[] words = line.split("\\s+");
                for (String word : words) {
                    if (isKeyword(word))
                        System.out.println(word + " is a keyword");
                    else
                        System.out.println(word + " is an identifier");
                }
            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isKeyword(String word) {
        List<String> keywords = Arrays.asList("break", "case", "char", "const","default", "do", "double", "else","float", "for",
                "if", "int", "long", "return", "short", "sizeof", "static", "struct", "switch", "typedef", "unsigned", "void", "volatile", "while" , "print");

        return keywords.contains(word);
    }

    public static void S() {
        if (s.charAt(i) == 'a') {
            i++;
            B();
            if (s.charAt(i) == 'b')
                i++;
            else
                error();
        } else if (s.charAt(i) == 'c') {
            i++;
            if (s.charAt(i) == 'c') {
                i++;
                A();
            } else
                error();
        }
    }

    public static void A() {
        if (s.charAt(i) == 'b' || s.charAt(i) == 'c')
            i++;
        else
            error();
    }

    public static void B() {
        if (s.charAt(i) == 'a' || s.charAt(i) == 'b')
            i++;
        else
            error();
    }

    public static void disp() {
        System.out.println("String is valid");
    }

    public static void error() {
        System.out.println("String is invalid");
    }
}