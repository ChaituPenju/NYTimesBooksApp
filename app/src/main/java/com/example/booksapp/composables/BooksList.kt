package com.example.booksapp.composables

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.booksapp.models.Book

@Composable
fun BooksList(books: List<Book>, modifier: Modifier = Modifier) {
    LazyColumn {
        items(books.size) { bookIndex ->
            var bookIsSelected by remember { mutableStateOf(books[bookIndex].isChecked) }

            BookItem(
                book = books[bookIndex],
                bookIsSelected,
                onBookSelected = { book, isChecked ->
                    book.isChecked = isChecked
                    bookIsSelected = isChecked

                    // print book data upon checking the 'checkbox'
                    Log.d("Book", book.toString())
                }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BooksListPreview() {
    BooksList(
        books = listOf(
            Book(
                price = "24",
                author = "Abcd",
                title = "Android book",
                bookImage = "url",
                isChecked = false,
            ),
            Book(
                price = "43",
                author = "ijkl",
                title = "Ios book",
                bookImage = "url",
                isChecked = true,
            ),
            Book(
                price = "12",
                author = "efgh",
                title = "Cross platform book",
                bookImage = "url",
                isChecked = false,
            ),
        )
    )

}