package amazon;

import java.util.*;

public class Solution {

    public static class Movie {
        private final int movieId;
        private final float rating;
        private List<Movie> similarMovies; // Similarity is bidirectional

        public Movie(int movieId, float rating) {
            this.movieId = movieId;
            this.rating = rating;
            similarMovies = new ArrayList<Movie>();
        }

        public int getId() {
            return movieId;
        }

        public float getRating() {
            return rating;
        }

        public void addSimilarMovie(Movie movie) {
            similarMovies.add(movie);
            movie.similarMovies.add(this);
        }

        @Override
        public String toString() {
            return "Movie{" +
                    "movieId=" + movieId +
                    ", rating=" + rating +
                    '}';
        }

        public List<Movie> getSimilarMovies() {
            return similarMovies;
        }
    }

    /*
      * @param movie Current movie.
      * @param numTopRatedSimilarMovies the maximum number of recommended movies to return.
      * @return List of top rated similar movies.
      *
      * Assumptions I made: According to specification, solution should be designed as as library, therefore whenever structure
       * of movies (similar movies) is accessed all code operates on defencive copy of list structure, to not modify client object,
       * and do not make assumption that while iterating over that collection, client will not modify that list from other Thread.
        * Operating on defencive copy prevent some nasty concurrent exceptions.
      *
      * Description of my approach: Most important method is flatMap movies that goes recursively through all similar movies, using
      * visited Set, to store all already visited movies. Method return all similar movies for movie argument.
      * Method is executed for all similarMovie of movie argument, then is collected using java 8 stream API.
      * Current movie is removed from result set, then result set is sorted, and top N elements are returned as List<Movie>
      *
      * Runtime complexity of my approach: log n * n - iterating over root elements, and recursive call, for not yet visited elements,
      * sorting resulting elements
      *
      * Justification of runtime complexity: thank to using recursive algorithm, only not yet visited elements are iterating over
      *
      */

    public static List<Movie> getMovieRecommendations(Movie movie, int numTopRatedSimilarMovies) {

        Set<Movie> visited = getAllMovies(movie);
        visited.remove(movie); //remove current movie from result set

        List<Movie> result = sortMoviesByRatingDescending(visited);
        System.out.println(result);

        return returnTopNResults(numTopRatedSimilarMovies, result);
    }

    private static Set<Movie> getAllMovies(Movie movie) {
        Set<Movie> visited = new HashSet<>();
        DFS(movie, visited);
        return visited;
    }

    private static void DFS(Movie movie, Set<Movie> alreadyVisited) {
        alreadyVisited.add(movie);

        for (Movie neighbour : movie.getSimilarMovies()) {
            if (!alreadyVisited.contains(neighbour)) {
                DFS(neighbour, alreadyVisited);
            }
        }
    }

    private static void BFS(Movie movie, Set<Movie> alreadyVisited){
        Queue<Movie> queue = new LinkedList<>();
        alreadyVisited.add(movie);
        queue.add(movie);

        while(!queue.isEmpty()){
            Movie currentNode = queue.poll();
            for(Movie neighbour: currentNode.getSimilarMovies()){
                if(!alreadyVisited.contains(neighbour)){
                    alreadyVisited.add(neighbour);
                    queue.add(neighbour);
                }
            }
        }

    }

    private static List<Movie> returnTopNResults(int numTopRatedSimilarMovies, List<Movie> result) {
        if (numTopRatedSimilarMovies >= result.size()) return result;
        return result.subList(0, numTopRatedSimilarMovies);
    }

    private static LinkedList<Movie> sortMoviesByRatingDescending(Set<Movie> visited) {
        LinkedList<Movie> result = new LinkedList<>();
        result.addAll(visited);
        result.sort(Comparator.comparing(Movie::getRating).reversed());
        return result;
    }


    public static void main(String[] args) {
    }
}

