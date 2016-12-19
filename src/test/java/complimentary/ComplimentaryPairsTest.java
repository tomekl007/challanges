package complimentary;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class ComplimentaryPairsTest {
    @Test
    public void shouldFindComplimentaryPairs() {
        //when
        int res = ComplimentaryPairs.findKComplementary(new int[]{2, 5, -1, 6, 10, -2}, 4);

        //then
        assertThat(res).isEqualTo(5);

    }

    @Test
    public void shouldNotFindComplimentaryPairsInEmpty() {
        //when
        int res = ComplimentaryPairs.findKComplementary(new int[]{}, 4);

        //then
        assertThat(res).isEqualTo(0);

    }

    @Test
    public void shouldNotFindComplimentaryPairsInOneElem() {
        //when
        int res = ComplimentaryPairs.findKComplementary(new int[]{1}, 4);

        //then
        assertThat(res).isEqualTo(0);

    }

    @Test
    public void shouldFindComplimentaryPairsWhenKIsHigh() {
        //when
        int res = ComplimentaryPairs.findKComplementary(new int[]{2, 5, -1, 6, 10, -2}, 20);

        //then
        assertThat(res).isEqualTo(1);

    }

    @Test
    public void shouldFindComplimentaryPairsDuplicate() {
        //when
        int res = ComplimentaryPairs.findKComplementary(new int[]{2, 2, 5, -1, 6, 10, -2}, 4);

        //then
        assertThat(res).isEqualTo(8);

    }

}