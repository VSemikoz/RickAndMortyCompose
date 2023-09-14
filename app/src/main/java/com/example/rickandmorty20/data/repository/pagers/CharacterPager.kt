package com.example.rickandmorty20.data.repository.pagers

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmorty20.data.repository.CharacterRepository
import com.example.rickandmorty20.domain.entity.CharacterEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterPager @Inject constructor(
    private val characterRepository: CharacterRepository,
) : PagingSource<Int, CharacterEntity>() {

    val pager by lazy {
        Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { this },
        )
    }


    override fun getRefreshKey(state: PagingState<Int, CharacterEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterEntity> {
        return try {
            val page = params.key ?: 1
            val response = characterRepository.getCharactersFromNetwork(page)

            val characterList: MutableList<CharacterEntity> =
                (response?.characterList ?: listOf()).toMutableList()
            val characterInfo = response?.characterInfo

            val prevKey = if (page == 1) null else page.minus(1)
            val nextKey =
                if (characterInfo == null || characterInfo.pages <= page) null else page.plus(1)

            LoadResult.Page(
                data = characterList,
                prevKey = prevKey,
                nextKey = nextKey,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}