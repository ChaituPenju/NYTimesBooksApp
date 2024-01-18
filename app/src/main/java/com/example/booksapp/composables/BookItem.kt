package com.example.booksapp.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.booksapp.R
import com.example.booksapp.models.Book

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BookItem(
    book: Book,
    isChecked: Boolean,
    onBookSelected: (selected: Book, isChecked: Boolean) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(8.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = book.bookImage),
                contentDescription = null,
                modifier = Modifier
                    .size(88.dp)
                    .background(Color.Blue.copy(alpha = 0.1f))
                    .weight(1.5f)
            )


            Spacer(modifier = Modifier.width(8.dp))

            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.weight(3f)
            ) {
                Text(text = book.title, modifier = Modifier.basicMarquee())
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = book.author, modifier = Modifier.basicMarquee())
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = book.price)
                Spacer(modifier = Modifier.height(12.dp))
            }

            Checkbox(
                checked = isChecked,
                onCheckedChange = {
                    onBookSelected(book, it)
                },
                modifier = Modifier.weight(0.5f),
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BookItemPreview() {
    BookItem(
        book = Book(
            price = "24",
            author = "Abcd",
            title = "Android book",
            bookImage = "url",
            isChecked = false,
        ),
        isChecked = true,
        onBookSelected = { book, ischecked ->

        }
    )
}