package com.manriquetavi.jetmovieapp.data

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.manriquetavi.jetmovieapp.domain.model.Movie
import com.manriquetavi.jetmovieapp.domain.model.Response
import com.manriquetavi.jetmovieapp.domain.repository.FirestoreDataSource
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class FirestoreDataImpl @Inject constructor(
    private val firestore: FirebaseFirestore
): FirestoreDataSource{
    override fun getAllMovies(): Flow<Response<List<Movie>?>> = callbackFlow {
        val snapshotListener = firestore
            .collection("movies")
            .orderBy("stars", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, e ->
                val response =
                    if (snapshot != null) {
                        val movies = snapshot.toObjects(Movie::class.java)
                        Response.Success(movies)
                    } else {
                        Response.Error(e?.message ?: e.toString())
                    }
                trySend(response).isSuccess
            }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override fun searchMovies(query: String): Flow<Response<List<Movie>?>> = callbackFlow {
        val snapshotListener = firestore
            .collection("movies")
            .orderBy("title")
            .startAt(query)
            .endAt(query + "\uf8ff")
            .addSnapshotListener { snapshot, e ->
                val response =
                    if (snapshot != null) {
                        val movies = snapshot.toObjects(Movie::class.java)
                        Response.Success(movies)
                    } else {
                        Response.Error(e?.message ?: e.toString())
                    }
                trySend(response).isSuccess
            }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override fun getSelectedMovie(movieId: String): Flow<Response<Movie?>> = callbackFlow {
        val document = firestore
            .collection("movies")
            .document(movieId)
            .get()
            .addOnSuccessListener { document ->
                val response =
                    if (document != null) {
                        val movie = document.toObject(Movie::class.java)
                        Response.Success(movie)
                    } else {
                        Response.Success(null)
                    }
                trySend(response).isSuccess
            }
            .addOnFailureListener { e ->
                trySend(Response.Error(e.message ?: e.toString())).isFailure
            }
        awaitClose {
            document.isCanceled
        }
    }
}