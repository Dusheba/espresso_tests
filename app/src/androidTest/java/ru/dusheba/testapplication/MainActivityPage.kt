package ru.dusheba.testapplication

import android.content.pm.ActivityInfo
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.Matchers.not

object MainActivityPage : Page<MainActivityPage>() {
    private val inputTextField = withId(R.id.inputTextField)
    private val editTextButton = withId(R.id.editTextButton)
    private val editedTextView = withId(R.id.editedTextView)
    private val scrollView = withId(R.id.scrollView)
    private const val emptyInputFieldError = R.string.empty_field_error

    /**
     * Вставляет текст в поле и нажимает кнопку
     */
    fun insertAndEdit(inputText: String) {
        onView(inputTextField).perform(typeText(inputText), closeSoftKeyboard())
        onView(editTextButton).perform(click())
    }

    /**
     * Вставляет текст с любыми символами в поле и нажимает кнопку
     */
    fun insertAnythingAndEdit(inputText: String) {
        onView(inputTextField).perform(replaceText(inputText), closeSoftKeyboard())
        onView(editTextButton).perform(click())
    }

    /**
     * Проверяет отображение кнопки
     */
    fun checkButton(){
        onView(editTextButton).check(matches(isDisplayed()))
    }

    /**
     * Сравнивает введенный текст с текстом в лейбле
     */
    fun checkEditedText(checkText: String){
        onView(editedTextView).check(matches(withText(checkText)))
    }

    /**
     * Проверяет, что в поле ввода отображается ошибка
     */
    fun checkInputFieldError(){
        onView(inputTextField).check(matches(withHint(emptyInputFieldError)))
    }

    /**
     * Проверяет, что текст лейбла не пустой
     */
    fun checkEditedTextNotEmpty(){
        onView(editedTextView).check(matches(not(withText(""))))
    }

    /**
     * Проверяет, очистилось ли текстовое поле после ввода текста и нажатия кнопки
     */
    fun checkFieldTextIsEmpty(){
        onView(inputTextField).check(matches(withText("")))
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
        onView(scrollView).perform(swipeUp())
    }
}