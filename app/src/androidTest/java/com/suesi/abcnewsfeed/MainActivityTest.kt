package com.suesi.abcnewsfeed

import android.text.Html
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.squareup.okhttp.mockwebserver.MockWebServer
import kotlinx.coroutines.android.awaitFrame
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    @JvmField
    @Rule
    var activityTestRule = ActivityTestRule(MainActivity::class.java, true, false)

    var server: MockWebServer = MockWebServer()
    val PORT =  8080

    @Before
    fun setup() {
        server.start(PORT)
    }

    @After
    fun cleanup() {
        server.shutdown()
    }

    @Test
    fun givenLoadFeedsSuccess_thenHideError_showNewsList() {
        // given
        server.setDispatcher(ResponseDispatcher(ResponseType.SUCCESS))

        // when
        activityTestRule.launchActivity(null)

        // then
        runBlocking {
            // wait for frame update
            awaitFrame()

            onView(withId(R.id.rv_details_list)).check(matches(isDisplayed()))
            onView(withText("Failed to load feed from server. Please try again later."))
                .check(matches(not(isDisplayed())))
        }
    }

    @Test
    fun givenLoadFeedsError_thenShowError_hideNewsList() {
        // given
        server.setDispatcher(ResponseDispatcher(ResponseType.ERROR))

        // when
        activityTestRule.launchActivity(null)

        // then
        runBlocking {
            // wait for frame update
            awaitFrame()

            onView(withId(R.id.srl_pull_to_refresh)).check(matches(not(isDisplayed())))
            onView(withText("Failed to load feed from server. Please try again later."))
                .check(matches(isDisplayed()))
        }
    }

    @Test
    fun givenLoadFeedSuccess_whenClickOnAFeed_thenShowContentWithSelectedFeedOnTop() {
        // given
        server.setDispatcher(ResponseDispatcher(ResponseType.SUCCESS))

        // when
        activityTestRule.launchActivity(null)

        runBlocking {
            // wait for frame update before performing UI interaction
            awaitFrame()

            // click on second row content
            onView(RecyclerViewMatcher.recyclerViewWithId(R.id.rv_details_list).itemViewAtIndex(1))
                .perform(click())

            // then
            // title is selected title - display in full
            onView(RecyclerViewMatcher.recyclerViewWithId(R.id.rv_details_list).viewHolderViewAtPosition(0, R.id.tv_title))
                .check(matches(withText("Coroner examining Danny Frawley's death urges AFL players to donate brains to science")))
            // content is selected content
            onView(RecyclerViewMatcher.recyclerViewWithId(R.id.rv_details_list).viewHolderViewAtPosition(0, R.id.tv_content))
                .check(matches(withText(Html.fromHtml("<p>It is impossible to tell how much the brain disease CTE contributed to the death of AFL legend Danny Frawley, a Victorian coroner says, as she calls for more research into the condition.</p>").toString())))
        }
    }
}