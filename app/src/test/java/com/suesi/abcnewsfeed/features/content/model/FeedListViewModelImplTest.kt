package com.suesi.abcnewsfeed.features.content.model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.suesi.abcnewsfeed.data.FeedDisplayable
import com.suesi.abcnewsfeed.usecase.RetrieveNewsFeedFromServerUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExecutorCoroutineDispatcher
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.`when` as whenever

class FeedListViewModelImplTest {
    @get:Rule
    var instantExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var mainThreadSurrogate: ExecutorCoroutineDispatcher

    lateinit var testee: FeedListViewModel

    @Mock
    lateinit var mockedRetrieveNewsFeedFromServerUseCase: RetrieveNewsFeedFromServerUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)

        testee = FeedListViewModelImpl(mockedRetrieveNewsFeedFromServerUseCase)

        mainThreadSurrogate = newSingleThreadContext("UI thread")
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun givenRetrieveNewsFeedFromServerUseCaseSuccess_whenLoadNews_thenErrorIsNotSet_andFeedListIsSet() {
        runBlockingTest {
            // given
            whenever(mockedRetrieveNewsFeedFromServerUseCase.retrieveNewsFeed()).thenReturn(
                RetrieveNewsFeedFromServerUseCase.Result.Success(
                    arrayListOf(createFeed())
                )
            )

            // when
            testee.loadNews()

            // then
            testee.isError.observeForever {
                assertNotNull(it)
                assertFalse(it)
            }

            testee.feeds.observeForever {
                assertNotNull(it)
                assertEquals(1, it.size)
            }
        }
    }

    @Test
    fun givenRetrieveNewsFeedFromServerUseCaseError_whenLoadNews_thenErrorIsSet_andFeedListIsNotSet() {
        runBlockingTest {
            // given
            whenever(mockedRetrieveNewsFeedFromServerUseCase.retrieveNewsFeed()).thenReturn(
                RetrieveNewsFeedFromServerUseCase.Result.Error
            )

            // when
            testee.loadNews()

            // then
            testee.isError.observeForever {
                assertNotNull(it)
                assertTrue(it)
            }

            testee.feeds.observeForever {
                assertNull(it)
            }
        }
    }

    private fun createFeed() = FeedDisplayable(
        "title",
        "thumbnail",
        "enclosureLink",
        "pubDate",
        "content"
    )
}