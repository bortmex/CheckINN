package com.project.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class TestData {
    static final String STATUSNULL = "Налогоплательщик зарегистрирован в ЕГРН и имел статус действующего в указанную дату";
    static final String STATUSFIRST = "Налогоплательщик зарегистрирован в ЕГРН, но не имел статус действующего в указанную дату";
    static final String STATUSECOND = "Налогоплательщик зарегистрирован в ЕГРН";
    static final String STATUSTHREE = "Налогоплательщик с указанным ИНН зарегистрирован в ЕГРН, КПП не соответствует ИНН или не указан";
    static final String STATUSTHREE_A ="Налогоплательщик с указанным ИНН зарегистрирован в ЕГРН";
    static final String STATUSFOUR = "Налогоплательщик с указанным ИНН не зарегистрирован в ЕГРН";
    static final String STATUS_INCORRECT_INN_YUL = "некорректный ИНН ЮЛ";
    static final String STATUS_INCORRECT_INN_FL = "некорректный ИНН ФЛ";
    static final String STATUS_INVALID_NUMBER_OF_INN_CHARACTERS = "недопустимое количество символов ИНН";
    static final String STATUS_CONTENT_OF_ONLY_9_DIGITS = "Поле КПП должно содержать код из 9 цифр";
    static final String STATUS_DATE_IN_THE_FIRMAT_DDMMYYYY = "Поле Дата сделки должно содержать дату в формате ДД.ММ.ГГГГ";
    static final String STATUS_DONT_MORE_NOW_DATE = "Поле Дата сделки должно содержать дату не позднее " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.uuuu"));
    static final String STATUS_NOT_EARLIER_1991 = "Поле Дата сделки должно содержать дату не ранее 01.01.1991";
}
