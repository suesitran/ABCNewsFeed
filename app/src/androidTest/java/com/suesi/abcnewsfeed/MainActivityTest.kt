package com.suesi.abcnewsfeed

import android.text.Html
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.squareup.okhttp.mockwebserver.MockWebServer
import org.hamcrest.CoreMatchers.not
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.TimeUnit

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
        onView(withId(R.id.rv_details_list)).check(matches(isDisplayed()))
        onView(withText("Failed to load feed from server. Please try again later."))
            .check(matches(not(isDisplayed())))
    }

    @Test
    fun givenLoadFeedsError_thenShowError_hideNewsList() {
        // given
        server.setDispatcher(ResponseDispatcher(ResponseType.ERROR))

        // when
        activityTestRule.launchActivity(null)

        // then
        onView(withId(R.id.srl_pull_to_refresh)).check(matches(isDisplayed()))
        onView(withText("Failed to load feed from server. Please try again later."))
            .check(matches(not(isDisplayed())))
    }

    @Test
    fun givenLoadFeedSuccess_whenClickOnAFeed_thenShowContentWithSelectedFeedOnTop() {
        // given
        server.setDispatcher(ResponseDispatcher(ResponseType.SUCCESS))

        // when
        activityTestRule.launchActivity(null)

        // click on first row content
        onView(RecyclerViewMatcher.recyclerViewWithId(R.id.rv_details_list).itemViewAtIndex(0))
            .perform(click())

        // then
        // title is selected title - display in full
        onView(RecyclerViewMatcher.recyclerViewWithId(R.id.rv_details_list).viewHolderViewAtPosition(0, R.id.tv_title))
            .check(matches(withText("Former NSW Labor official admits to exploiting vulnerable children, pleads guilty to dozens of offences")))
        // content is selected content
        onView(RecyclerViewMatcher.recyclerViewWithId(R.id.rv_details_list).viewHolderViewAtPosition(0, R.id.tv_content))
            .check(matches(withText(Html.fromHtml("<p>Peter Hansen, a former NSW Labor Party branch secretary and Catholic priest in Melbourne, pleads guilty to more than 30 offences including sex with children in Asian countries.</p>").toString())))
    }
}