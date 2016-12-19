package amazon;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class SolutionTest {

    @Test
    public void shouldRetrunProperMovie2(){
        //given
        Solution.Movie movie1 = new Solution.Movie(1, 1.2f);
        Solution.Movie movie2 = new Solution.Movie(2, 3.6f);
        Solution.Movie movie3 = new Solution.Movie(3, 2.4f);
        Solution.Movie movie4 = new Solution.Movie(4, 4.8f);

        movie1.addSimilarMovie(movie2);
        movie1.addSimilarMovie(movie3);
        movie2.addSimilarMovie(movie4);
        movie3.addSimilarMovie(movie4);

        //when
        List<Solution.Movie> movieRecommendations = Solution.getMovieRecommendations(movie1, 2);

        //then
        assertThat(movieRecommendations.size()).isEqualTo(2);
        assertThat(movieRecommendations.contains(movie2)).isTrue();
        assertThat(movieRecommendations.contains(movie4)).isTrue();
    }

    @Test
    public void shouldRetrunProperMovie4(){
        //given
        Solution.Movie movie1 = new Solution.Movie(1, 1.2f);
        Solution.Movie movie2 = new Solution.Movie(2, 3.6f);
        Solution.Movie movie3 = new Solution.Movie(3, 2.4f);
        Solution.Movie movie4 = new Solution.Movie(4, 4.8f);

        movie1.addSimilarMovie(movie2);
        movie1.addSimilarMovie(movie3);
        movie2.addSimilarMovie(movie4);
        movie3.addSimilarMovie(movie4);

        //when
        List<Solution.Movie> movieRecommendations = Solution.getMovieRecommendations(movie1, 4);

        //then
        assertThat(movieRecommendations.size()).isEqualTo(3);
        assertThat(movieRecommendations.contains(movie2)).isTrue();
        assertThat(movieRecommendations.contains(movie3)).isTrue();
        assertThat(movieRecommendations.contains(movie4)).isTrue();
    }

    @Test
    public void shouldRetrunProperMovie1(){
        //given
        Solution.Movie movie1 = new Solution.Movie(1, 1.2f);
        Solution.Movie movie2 = new Solution.Movie(2, 3.6f);
        Solution.Movie movie3 = new Solution.Movie(3, 2.4f);
        Solution.Movie movie4 = new Solution.Movie(4, 4.8f);

        movie1.addSimilarMovie(movie2);
        movie1.addSimilarMovie(movie3);
        movie2.addSimilarMovie(movie4);
        movie3.addSimilarMovie(movie4);

        //when
        List<Solution.Movie> movieRecommendations = Solution.getMovieRecommendations(movie1, 1);

        //then
        assertThat(movieRecommendations.size()).isEqualTo(1);
        assertThat(movieRecommendations.contains(movie4)).isTrue();
    }

    @Test
    public void shouldRetrunProperMovie0(){
        //given
        Solution.Movie movie1 = new Solution.Movie(1, 1.2f);
        Solution.Movie movie2 = new Solution.Movie(2, 3.6f);
        Solution.Movie movie3 = new Solution.Movie(3, 2.4f);
        Solution.Movie movie4 = new Solution.Movie(4, 4.8f);

        movie1.addSimilarMovie(movie2);
        movie1.addSimilarMovie(movie3);
        movie2.addSimilarMovie(movie4);
        movie3.addSimilarMovie(movie4);

        //when
        List<Solution.Movie> movieRecommendations = Solution.getMovieRecommendations(movie1, 0);

        //then
        assertThat(movieRecommendations.size()).isEqualTo(0);
    }

    @Test
    public void shouldRetrunProperMovie0ForOneMovie(){
        //given
        Solution.Movie movie1 = new Solution.Movie(1, 1.2f);


        //when
        List<Solution.Movie> movieRecommendations = Solution.getMovieRecommendations(movie1, 2);

        //then
        assertThat(movieRecommendations.size()).isEqualTo(0);
    }

    @Test
    public void shouldRetrunProperMovie2For2Movies(){
        //given
        Solution.Movie movie1 = new Solution.Movie(1, 1.2f);
        Solution.Movie movie2 = new Solution.Movie(2, 3.6f);

        movie1.addSimilarMovie(movie2);

        //when
        List<Solution.Movie> movieRecommendations = Solution.getMovieRecommendations(movie1, 2);

        //then
        assertThat(movieRecommendations.size()).isEqualTo(1);
        assertThat(movieRecommendations.contains(movie2)).isTrue();
    }

    @Test
    public void shouldRetrunProperMovie2For1MoviesReferingToItself(){
        //given
        Solution.Movie movie1 = new Solution.Movie(1, 1.2f);

        movie1.addSimilarMovie(movie1);

        //when
        List<Solution.Movie> movieRecommendations = Solution.getMovieRecommendations(movie1, 2);

        //then
        assertThat(movieRecommendations.size()).isEqualTo(0);
    }


    @Test
    public void shouldRetrunProperMovie2Complex(){
        //given
        Solution.Movie movie1 = new Solution.Movie(1, 1.2f);
        Solution.Movie movie2 = new Solution.Movie(2, 3.6f);
        Solution.Movie movie3 = new Solution.Movie(3, 2.4f);
        Solution.Movie movie4 = new Solution.Movie(4, 4.8f);

        movie1.addSimilarMovie(movie2);
        movie1.addSimilarMovie(movie3);
        movie2.addSimilarMovie(movie4);
        movie3.addSimilarMovie(movie4);


        Solution.Movie movie21 = new Solution.Movie(5, 4.6f);
        Solution.Movie movie31 = new Solution.Movie(6, 4.9f);

        movie1.addSimilarMovie(movie21);
        movie1.addSimilarMovie(movie31);

        //when
        List<Solution.Movie> movieRecommendations = Solution.getMovieRecommendations(movie1, 2);

        //then
        assertThat(movieRecommendations.size()).isEqualTo(2);
        assertThat(movieRecommendations.contains(movie4)).isTrue();
        assertThat(movieRecommendations.contains(movie31)).isTrue();
    }
}