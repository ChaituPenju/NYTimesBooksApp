package com.example.booksapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksapp.repository.MainRepository
import com.example.booksapp.models.Book
import com.example.booksapp.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
): ViewModel() {
    private val _booksState: MutableStateFlow<Response<List<Book>>> = MutableStateFlow(Response.INITIAL())
    val booksState: StateFlow<Response<List<Book>>>
        get() = _booksState.asStateFlow()

    fun getBooks() {
        viewModelScope.launch {
            _booksState.update {
                Response.LOADING()
            }

            try {
                val books: List<Book> = mainRepository.getBooks()

                _booksState.update {
                    Response.SUCCESS(data = books)
                }
            } catch (e: Exception) {
                Response.ERROR<List<Book>>(message = e.localizedMessage ?: "Something Went Wrong!")
            }

        }
    }
}