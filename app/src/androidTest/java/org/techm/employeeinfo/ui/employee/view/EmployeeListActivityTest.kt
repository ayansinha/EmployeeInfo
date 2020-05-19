package org.techm.employeeinfo.ui.employee.view

import android.content.Intent
import androidx.appcompat.app.ActionBar
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.techm.employeeinfo.R

class EmployeeListActivityTest {


    @get: Rule
    val employeeActivityRule: ActivityTestRule<EmployeeListActivity> = ActivityTestRule(EmployeeListActivity::class.java , false , false)


    @Before
    fun setUp() {
        val intent = Intent()
        employeeActivityRule.launchActivity(intent)
    }


    @Test
    fun onLaunchActionBarTitleIsDisplayed() {
        val actionBar: ActionBar? = employeeActivityRule.activity.supportActionBar
        Assert.assertNotNull(actionBar?.title)
    }


    @Test
    fun appLaunchSuccessfully() {
        ActivityScenario.launch(EmployeeListActivity::class.java)
    }

    @Test
    fun recyclerViewTestScrolling() {
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }


    @Test
    fun recyclerViewTestScrollingToPositionEndIndex() {
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView)).perform(ViewActions.swipeUp())
    }

    @Test
    fun recyclerViewTestScrollingToPositionTop() {
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView)).perform(ViewActions.swipeDown())
    }

}