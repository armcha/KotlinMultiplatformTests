package domain.mapper

import domain.models.Movie
import domain.models.response.OmdbResponse


/**
 *
 * Created by Arman Chatikyan on 17 Oct 2018
 *
 */

internal object MovieMapper {

    private fun omdbResponseToMovie(omdbResponse: OmdbResponse): Movie {
        return with(omdbResponse) {
            Movie(imdbID, Title, Year, Released, Runtime, Genre, Director, Writer, Actors, Plot, Poster)
        }
    }

    fun omdbResponseListToMovieList(omdbResponseList: List<OmdbResponse>): List<Movie> {
        return omdbResponseList.map {
            omdbResponseToMovie(it)
        }
    }
}