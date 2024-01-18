package com.example.booksapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.booksapp.composables.BooksList
import com.example.booksapp.ui.theme.BooksAppTheme
import com.example.booksapp.utils.Response
import com.example.booksapp.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.getBooks()
            }
        }

        setContent {
            val booksResponse by mainViewModel.booksState.collectAsState()

            BooksAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    when (booksResponse) {
                        is Response.LOADING -> {
                            CircularProgressIndicator()
                        }
                        is Response.SUCCESS -> {
                            booksResponse.data?.let {
                                BooksList(books = it)
                            } ?: Text(text = "No Books Found")

                        }
                        is Response.ERROR -> {
                            Text(text = "Some Error Occurred : ${booksResponse.message}")
                        }
                        else -> Unit
                    }
                }
            }
        }
    }
}