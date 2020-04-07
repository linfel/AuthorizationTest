package com.gosha.authorizationtest

import android.app.Activity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage
import io.qameta.allure.android.step
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@LargeTest
class LoginTest {

    @get: Rule var activityScenarioRule = activityScenarioRule<MainActivity>()

    private fun getCurrentActivity(): Activity? {
        var currentActivity: Activity? = null
        getInstrumentation().runOnMainSync { run { currentActivity = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(
            Stage.RESUMED).elementAtOrNull(0) } }
        return currentActivity
    }

    @Test
    fun emptyFieldsEnter() {
        step("Логин с пустыми полями") {
            val mainActivity = getCurrentActivity()
            onView(withId(R.id.login_filed)).perform(typeText(""))
            onView(withId(R.id.password_field)).perform(typeText(""))
            onView(withId(R.id.enter_button)).perform(click())
            assertEquals(mainActivity, getCurrentActivity())
        }
    }

    @Test
    fun wrongLoginEnter() {
        step("Логин с неверным логином") {
            val mainActivity = getCurrentActivity()
            onView(withId(R.id.login_filed)).perform(typeText("wrongLogin"), closeSoftKeyboard())
            onView(withId(R.id.password_field)).perform(typeText("password12345"), closeSoftKeyboard())
            onView(withId(R.id.enter_button)).perform(click())
            assertEquals(mainActivity, getCurrentActivity())
        }
    }

    @Test
    fun wrongPasswordEnter() {
        step("Логин с неверным паролем") {
            val mainActivity = getCurrentActivity()
            onView(withId(R.id.login_filed)).perform(typeText("login12345"), closeSoftKeyboard())
            onView(withId(R.id.password_field)).perform(typeText("wrongPassword"), closeSoftKeyboard())
            onView(withId(R.id.enter_button)).perform(click())
            assertEquals(mainActivity, getCurrentActivity())
        }
    }

    @Test
    fun correctLogin() {
       step("Логин с корректными кредами") {
           onView(withId(R.id.login_filed)).perform(typeText("login12345"), closeSoftKeyboard())
           onView(withId(R.id.password_field)).perform(typeText("password12345"), closeSoftKeyboard())
           onView(withId(R.id.enter_button)).perform(click())
//        Intents.init()
//        intended(hasComponent(SecondActivity::class.java.name))
           assertEquals(getCurrentActivity()!!::class.java.name, SecondActivity::class.java.name)
           onView(withId(R.id.greeting_text)).check(matches(withText("Hello user, login12345")))
       }
    }


}