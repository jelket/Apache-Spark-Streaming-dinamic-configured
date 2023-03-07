package org.example;

public class Parser {

    static Integer[] myatoi(String s)
    {
        int i = 0;
        int L = 0;
        int b = 0;
        int cond = 0;
        Integer[] condition = {0, 0, 0};
        Integer[] ans = new Integer[8];
        Integer[] real = new Integer[7];
        Boolean flag = false;
        while (i != s.length() - 1) {
            if (s.charAt(i) == ' ') {
                if (flag){
                    flag = false;
                    L++;}
                else
                    continue;
            }
            else if (s.charAt(i) == '('
                    || s.charAt(i) == '=' || s.charAt(i) == ')') {
                flag = true;
            }

            else if (s.charAt(i) == '|') {
                condition[cond] = 1;
                cond++;
                flag = true;
            }
            else if (s.charAt(i) == '&') {
                condition[cond] = 2;
                cond++;
                flag = true;
            }

            else if (s.charAt(i) - '0' >= 0
                    && s.charAt(i) - '0' < 10) {
                flag = true;
                if(ans[L] == null){
                    ans[L] = (s.charAt(i) - '0');
                } else{
                    ans[L] = ans[L] * 10 + (s.charAt(i) - '0');
                }
            }
            i++;
        }
        System.arraycopy(condition, 0, real, 0, condition.length);
        for (Integer an : ans) {
            if (an != null) {
                real[b + 3] = an;
                b++;
            }
        }
        return real;
    }

    static Integer[] myatoi2(String s)
    {
        int i = 0;
        int L = 0;
        int b = 0;
        int cond = 0;
        Integer[] ans = new Integer[8];
        Integer[] real = new Integer[7];
        Boolean flag = false;
        while (i != s.length() - 1) {
            if (s.charAt(i) == ' ') {
                if (flag){
                    flag = false;
                    L++;}
                else
                    continue;
            }
            else if (s.charAt(i) == '('
                    || s.charAt(i) == '=' || s.charAt(i) == ')' || s.charAt(i) == '&') {
                flag = true;
            }

            else if (s.charAt(i) - '0' >= 0
                    && s.charAt(i) - '0' < 10) {
                flag = true;
                if(ans[L] == null){
                    ans[L] = (s.charAt(i) - '0');
                } else{
                    ans[L] = ans[L] * 10 + (s.charAt(i) - '0');
                }
            }
            i++;
        }
        for (Integer an : ans) {
            if (an != null) {
                real[b] = an;
                b++;
            }
        }
        return real;
    }
}
