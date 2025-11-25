package com.example.core.model.data

data class Program(val id: String, val title: String)

data class PaginatedProgramList(val list: List<Program>, val lastCursor: String? = null, val pageSize: Int = 10)
