package ru.dusheba.testapplication

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.dusheba.testapplication.MainActivityPage.changeScreenOrientation
import ru.dusheba.testapplication.MainActivityPage.checkButton
import ru.dusheba.testapplication.MainActivityPage.checkEditedText
import ru.dusheba.testapplication.MainActivityPage.checkEditedTextNotEmpty
import ru.dusheba.testapplication.MainActivityPage.checkFieldTextIsEmpty
import ru.dusheba.testapplication.MainActivityPage.checkInputFieldError
import ru.dusheba.testapplication.MainActivityPage.insertAndEdit
import ru.dusheba.testapplication.MainActivityPage.insertAnythingAndEdit
import ru.dusheba.testapplication.MainActivityPage.scrollView


@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {
    companion object{
        private val source = ('A'..'Z') +('a'..'z') + ('0'..'9')
        val longString = (1..500).map { source.random() }.joinToString("")
        val mediumString = (1..50).map { source.random() }.joinToString("")
        const val emptyString = ""
        const val specialSymbolString = "☕☺π☕√©☺™☕✓¶☺∆☕☺☕"
    }

    @get:Rule
    var scenarioRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    /**
     * Тест с короткой строкой (50 символов)
     */
    @Test
    fun mediumStringTest(){
        insertAndEdit(mediumString)
        checkEditedText(mediumString)
    }

    /**
     * Тест со вставкой длинной строки (500 символов)
     * Проверка отображения кнопки
     */
    @Test
    fun longStringTest(){
        insertAndEdit(longString)
        checkEditedText(longString)
        checkButton()
    }

    /**
     * Тест с пустой строкой
     * Проверка, что лейбл не пустой и в поле ввода отображается ошибка
     */
    @Test
    fun emptyStringTest(){
        insertAndEdit(emptyString)
        checkEditedTextNotEmpty()
        checkInputFieldError()
    }

    /**
     * Тест на вставку строки со смайликами и спец. символами
     */
    @Test
    fun smileStringTest(){
        insertAnythingAndEdit(specialSymbolString)
        checkEditedText(specialSymbolString)
    }

    /**
     * Тест на скролл длинного текста
     */
    @Test
    fun scrollTest(){
        longStringTest()
        scrollView()
    }

    /**
     * Проверка, что поле ввода пустое после нажатия кнопки
     */
    @Test
    fun emptyInsertTest(){
        insertAndEdit(emptyString)
        checkFieldTextIsEmpty()
    }

    /**
     * Проверка текста в лейбле после изменения ориентации экрана
     */
    @Test
    fun screenOrientationTest(){
        insertAndEdit(mediumString)
        changeScreenOrientation(scenarioRule)
        checkEditedText(mediumString)
    }
}