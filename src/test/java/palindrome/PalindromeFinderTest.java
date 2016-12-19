package palindrome;

import org.junit.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by tomasz.lelek on 03/08/16.
 */
public class PalindromeFinderTest {

    @Test
    public void isPalindrome() {
        //when
        boolean palindrome = PalindromeFinder.isPalindrome("1221");

        //then
        assertThat(palindrome).isTrue();

    }

    @Test
    public void isNotPalindrome() {
        //when
        boolean palindrome = PalindromeFinder.isPalindrome("1211");

        //then
        assertThat(palindrome).isFalse();

    }

    @Test
    public void isPalindromeOneElem() {
        //when
        boolean palindrome = PalindromeFinder.isPalindrome("1");

        //then
        assertThat(palindrome).isTrue();

    }

    @Test
    public void isPalindromeHugeString() {
        //when
        String a = Integer.toString(new Random().nextInt(9999999));
        String b = new StringBuilder(a).reverse().toString();

        boolean palindrome = PalindromeFinder.isPalindrome(a+b);

        //then
        assertThat(palindrome).isTrue();

    }

}