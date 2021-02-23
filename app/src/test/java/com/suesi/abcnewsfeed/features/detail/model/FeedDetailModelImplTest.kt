package com.suesi.abcnewsfeed.features.detail.model

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

class FeedDetailModelImplTest {
    @get:Rule
    var instantExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var mainThreadSurrogate : ExecutorCoroutineDispatcher

    lateinit var testee: FeedDetailModel

    @Mock
    lateinit var mockedRetrieveNewsFeedFromServerUseCase : RetrieveNewsFeedFromServerUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)


        mainThreadSurrogate = newSingleThreadContext("UI thread")
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun givenRetrieveNewsFeedFromServerUseCaseSuccessWithSelectedPosition0_whenReloadNews_thenListOrderNotChanged() {
        val position = 0
        val list = create7Feeds()

        testee = FeedDetailModelImpl(mockedRetrieveNewsFeedFromServerUseCase, position)

        runBlockingTest {
            // given
            whenever(mockedRetrieveNewsFeedFromServerUseCase.retrieveNewsFeed()).thenReturn(
                RetrieveNewsFeedFromServerUseCase.Result.Success(
                    list
                )
            )

            // when
            testee.reloadFeeds()

            // then
            testee.feeds.observeForever {
                assertNotNull(it)
                assertEquals(7, it.size)
                assertEquals(list, it)
            }
        }
    }

    @Test
    fun givenRetrieveNewsFeedFromServerUseCaseSuccessWithSelectedPositionInMiddelOfList_whenReloadNews_thenListOrderChanged() {
        val position = 3
        val list = create7Feeds()

        testee = FeedDetailModelImpl(mockedRetrieveNewsFeedFromServerUseCase, position)

        runBlockingTest {
            // given
            whenever(mockedRetrieveNewsFeedFromServerUseCase.retrieveNewsFeed()).thenReturn(
                RetrieveNewsFeedFromServerUseCase.Result.Success(
                    list
                )
            )

            // when
            testee.reloadFeeds()

            // then
            testee.feeds.observeForever {
                assertNotNull(it)
                assertEquals(7, it.size)
                assertNotEquals(list, it)
            }
        }
    }

    @Test
    fun givenRetrieveNewsFeedFromServerUseCaseSuccessWithSelectedPositionLastOfList_whenReloadNews_thenListOrderChanged() {
        val list = create7Feeds()
        val position = 6

        testee = FeedDetailModelImpl(mockedRetrieveNewsFeedFromServerUseCase, position)

        runBlockingTest {
            // given
            whenever(mockedRetrieveNewsFeedFromServerUseCase.retrieveNewsFeed()).thenReturn(
                RetrieveNewsFeedFromServerUseCase.Result.Success(
                    list
                )
            )

            // when
            testee.reloadFeeds()

            // then
            testee.feeds.observeForever {
                assertNotNull(it)
                assertEquals(7, it.size)
                assertNotEquals(list, it)
            }
        }
    }

    private fun create7Feeds() = arrayListOf(
        FeedDisplayable(
            "title 1",
            "thumbnail 1",
            "enclosureLink 1",
            "pubDate 1",
            "content 1"
        ),
        FeedDisplayable(
            "title 2",
            "thumbnail 2",
            "enclosureLink 2",
            "pubDate 2",
            "content 2"
        ),
        FeedDisplayable(
            "title 3",
            "thumbnail 3",
            "enclosureLink 3",
            "pubDate 3",
            "content 3"
        ),
        FeedDisplayable(
            "title 4",
            "thumbnail 4",
            "enclosureLink 4",
            "pubDate 4",
            "content 4"
        ),
        FeedDisplayable(
            "title 5",
            "thumbnail 5",
            "enclosureLink 5",
            "pubDate 5",
            "content 5"
        ),
        FeedDisplayable(
            "title 6",
            "thumbnail 6",
            "enclosureLink 6",
            "pubDate 6",
            "content 6"
        ),
        FeedDisplayable(
            "title 7",
            "thumbnail 7",
            "enclosureLink 7",
            "pubDate 7",
            "content 7"
        )
    )
}