package com.ijikod.lawyer_app

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.*
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToHolder
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.util.Checks
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.google.android.material.tabs.TabLayout
import com.ijikod.lawyer_app.ui.Adapter
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.ijikod.lawyer_app", appContext.packageName)
    }



    @Test
    fun tabFeaturedTitleCheck(){
        onView(matchTabTitleAtPosition("FEATURED (3)", 0))
    }

    @Test
    fun tabAllTitleCheck(){
        onView(matchTabTitleAtPosition("ALL (7)", 1))
    }

    @Test
    fun tabFavTitleCheck(){
        onView(matchTabTitleAtPosition("FAVS (2)", 2))
    }


    @Test
    fun featuredListCount(){
        onView(withIndex(withId(R.id.featured_list), 0)).check(CustomAssertions.hasItemCount(3))
    }

    @Test
    fun allListCount(){
        onView(withIndex(withId(R.id.featured_list), 1)).check(CustomAssertions.hasItemCount(7))
    }




    @Test
    fun recyclerViewItemCheck(){
        onView(withIndex(withId(R.id.featured_list), 0)).perform(scrollToHolder(withMessageSubjectInViewHolder("Michael Lawson")))
    }


    @Test
    fun recyclerViewClickCheck(){
        onView(withIndex(withId(R.id.featured_list), 0)).perform(RecyclerViewActions.actionOnItemAtPosition<Adapter.ViewHolder>(1, click()))
        onView(withText("Lindsay")).check(matches(isDisplayed()))
        onView(withText("Ferguson")).check(matches(isDisplayed()))

    }




    fun withIndex(
        matcher: Matcher<View?>,
        index: Int
    ): Matcher<View?>? {
        return object : TypeSafeMatcher<View?>() {
            var currentIndex = 0
            override fun describeTo(description: Description) {
                description.appendText("with index: ")
                description.appendValue(index)
                matcher.describeTo(description)
            }

            override fun matchesSafely(view: View?): Boolean {
                return matcher.matches(view) && currentIndex++ == index
            }
        }
    }



    fun withMessageSubjectInViewHolder(itemSubject: String): Matcher<RecyclerView.ViewHolder?>? {
        Checks.checkNotNull(itemSubject)
        return object :
            BoundedMatcher<RecyclerView.ViewHolder?, Adapter.ViewHolder>(
                Adapter.ViewHolder::class.java
            ) {
            override fun matchesSafely(holder: Adapter.ViewHolder): Boolean {
                var isMatches = false
                if (holder.name != null) {
                    isMatches = (itemSubject == holder.name.getText().toString()
                            && holder.name.getVisibility() === View.VISIBLE)
                }
                return isMatches
            }

            override fun describeTo(description: Description) {
                description.appendText("with message subject: $itemSubject")
            }
        }
    }



    fun matchTabTitleAtPosition(tabTitle: String, tabIndex: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description?) {
                description?.appendText("unable to select tab at index $tabIndex and match title with $tabTitle")
            }

            override fun matchesSafely(item: View?): Boolean {
                val tabLayout = item as TabLayout
                val tabAtIndex: TabLayout.Tab = tabLayout.getTabAt(tabIndex)
                    ?: throw PerformException.Builder()
                        .withCause(Throwable("No tab at index $tabIndex"))
                        .build()

                return tabAtIndex.text.toString().contains(tabTitle, true)
            }
        }
    }






    fun selectTabAtPosition(tabIndex: Int): ViewAction {
        return object : ViewAction {
            override fun getDescription() = "with tab at index $tabIndex"

            override fun getConstraints() = allOf(isDisplayed(), isAssignableFrom(TabLayout::class.java))

            override fun perform(uiController: UiController, view: View) {
                val tabLayout = view as TabLayout
                val tabAtIndex: TabLayout.Tab = tabLayout.getTabAt(tabIndex)
                    ?: throw PerformException.Builder()
                        .withCause(Throwable("No tab at index $tabIndex"))
                        .build()

                tabAtIndex.select()
            }
        }
    }



    class CustomAssertions {
        companion object {
            fun hasItemCount(count: Int): ViewAssertion {
                return RecyclerViewItemCountAssertion(count)
            }
        }

        private class RecyclerViewItemCountAssertion(private val count: Int) : ViewAssertion {

            override fun check(view: View, noViewFoundException: NoMatchingViewException?) {
                if (noViewFoundException != null) {
                    throw noViewFoundException
                }

                if (view !is RecyclerView) {
                    throw IllegalStateException("The asserted view is not RecyclerView")
                }

                if (view.adapter == null) {
                    throw IllegalStateException("No adapter is assigned to RecyclerView")
                }

                ViewMatchers.assertThat("RecyclerView item count", view.adapter?.itemCount, CoreMatchers.equalTo(count))
            }
        }
    }
}
