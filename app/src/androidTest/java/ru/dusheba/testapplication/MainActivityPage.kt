package ru.dusheba.testapplication

import android.content.pm.ActivityInfo
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.Matchers.not

object MainActivityPage : Page<MainActivityPage>() {
    private const val inputTextField = R.id.inputTextField
    private const val editTextButton = R.id.editTextButton
    private const val editedTextView = R.id.editedTextView
    private const val scrollView = R.id.scrollView
    private const val emptyInputFieldError = R.string.empty_field_error

    /**
     * Вставляет текст в поле и нажимает кнопку
     */
    fun insertAndEdit(inputText: String) {
        onView(withId(inputTextField)).perform(typeText(inputText), closeSoftKeyboard())
        onView(withId(editTextButton)).perform(click())
    }

    /**
     * Вставляет текст с любыми символами в поле и нажимает кнопку
     */
    fun insertAnythingAndEdit(inputText: String) {
        onView(withId(inputTextField)).perform(replaceText(inputText), closeSoftKeyboard())
        onView(withId(editTextButton)).perform(click())
    }

    /**
     * Проверяет отображение кнопки
     */
    fun checkButton(){
        onView(withId(editTextButton)).check(matches(isDisplayed()))
    }

    /**
     * Сравнивает введенный текст с текстом в лейбле
     */
    fun checkEditedText(checkText: String){
        onView(withId(editedTextView)).check(matches(withText(checkText)))
    }

    /**
     * Проверяет, что в поле ввода отображается ошибка
     */
    fun checkInputFieldError(){
        onView(withId(inputTextField)).check(matches(withHint(emptyInputFieldError)))
    }

    /**
     * Проверяет, что текст лейбла не пустой
     */
    fun checkEditedTextNotEmpty(){
        onView(withId(editedTextView)).check(matches(not(withText(""))))
    }

    /**
     * Проверяет, очистилось ли текстовое поле после ввода текста и нажатия кнопки
     */
    fun checkFieldTextIsEmpty(){
        onView(withId(inputTextField)).check(matches(withText("")))
    }

    /**
     * Поворот экрана
     */
    fun changeScreenOrientation(
        activityRule:
        ActivityScenarioRule<MainActivity> =
            ActivityScenarioRule(MainActivity::class.java)
    ){
        activityRule.scenario.onActivity { it.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
    }

    /**
     * Проверка скролла длинного текста
     */
    fun scrollView(){
        onView(ViewMatchers.withId(scrollView)).perform(ViewActions.swipeUp())
    }
}