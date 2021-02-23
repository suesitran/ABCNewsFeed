package com.suesi.abcnewsfeed.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.suesi.abcnewsfeed.retrofit.NewsClient
import com.suesi.abcnewsfeed.retrofit.response.EnclosureResponse
import com.suesi.abcnewsfeed.retrofit.response.FeedItemResponse
import com.suesi.abcnewsfeed.retrofit.response.FeedResponse
import com.suesi.abcnewsfeed.retrofit.response.RssFeedsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExecutorCoroutineDispatcher
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import retrofit2.Response
import org.mockito.Mockito.`when` as whenever

class RetrieveNewsFeedFromServerUseCaseImplTest {
    @get:Rule
    var instantExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var mainThreadSurrogate: ExecutorCoroutineDispatcher

    lateinit var testee: RetrieveNewsFeedFromServerUseCase

    @Mock
    lateinit var mockedNewsClient: NewsClient

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)

        testee = RetrieveNewsFeedFromServerUseCaseImpl(mockedNewsClient)

        mainThreadSurrogate = newSingleThreadContext("UI thread")
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun givenNewsClientLoadNewsSuccess_whenRetrieveNewsFeed_thenReturnSuccess() {
        runBlockingTest {
            // given
            whenever(mockedNewsClient.loadNews()).thenReturn(
                Response.success(
                    createRssFeedsResponse()
                )
            )

            // when
            val result = testee.retrieveNewsFeed()

            // then
            assertTrue(result is RetrieveNewsFeedFromServerUseCase.Result.Success)

        }
    }

    @Test
    fun givenNewsClientLoadNewsFail_whenRetrieveNewsFeed_thenReturnError() {
        runBlockingTest {
            // given
            whenever(mockedNewsClient.loadNews()).thenReturn(
                Response.error(500, ResponseBody.create(MediaType.get("text/html"), "Failed to load data"))
            )

            // when
            val result = testee.retrieveNewsFeed()

            // then
            assertTrue(result is RetrieveNewsFeedFromServerUseCase.Result.Error)
        }
    }

    private fun createRssFeedsResponse() = RssFeedsResponse(
        status = "status",
        feed = FeedResponse(
            url = "url",
            title = "title",
            author = "author",
            description = "description",
            image = "image",
            link = "link"
        ),
        items = arrayListOf(
            FeedItemResponse(
                title = "title",
                pubDate = "2021-12-23 12:34:45",
                link = "link",
                guid = "guid",
                author = "author",
                thumbnail = "thumbnail",
                description = "description",
                content = "content",
                enclosure = EnclosureResponse(
                    link = "link",
                    thumbnail = "thumbnail",
                    type = "image/jpeg"
                ),
                categories = arrayListOf("Courts and Trials", "Law, Crime and Justice", "Police", "Police Sieges", "Crime", "Sexual Offences")
            )
        )
    )
}