package palindrome;

public class PalindromeFinder {

    public static boolean isPalindrome(String s) {
        if (s.length() <= 1)
            return true;
        else
            return (s.charAt(0) == s.charAt(s.length() - 1)) && isPalindrome(s.substring(1, s.length() - 1));
    }
}
