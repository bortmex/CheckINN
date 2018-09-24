package com.project.task;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static com.project.task.Main.getStatus;
import static com.project.task.TestData.*;

public class MainTest {

    @Test
    public void getStatusOne() throws IOException {
        Assert.assertEquals(STATUSNULL, getStatus("3664069397", "366401001", "24.09.2018"));
    }

    @Test
    public void getStatusFirst() throws IOException {
        Assert.assertEquals(STATUSFIRST, getStatus("3664069397", "366401001", "24.09.2001"));
    }

    @Test
    public void getStatusSecond() throws IOException {
        Assert.assertEquals(STATUSECOND, getStatus("3664069397", "366401001", ""));
    }

    @Test
    public void getStatusThree() throws IOException {
        Assert.assertEquals(STATUSTHREE, getStatus("3664069414", "366401001", ""));
    }

    @Test
    public void getStatusThreeA() throws IOException {
        Assert.assertEquals(STATUSTHREE_A, getStatus("3664069414", "", ""));
    }

    @Test
    public void getStatusFour() throws IOException {
        Assert.assertEquals(STATUSFOUR, getStatus("3664069407", "366401001", ""));
    }

    @Test
    public void getStatusIncorrectInnYUL() throws IOException {
        Assert.assertEquals(STATUS_INCORRECT_INN_YUL, getStatus("3664069427", "", ""));
    }

    @Test
    public void getStatusIncorrectInnFL() throws IOException {
        Assert.assertEquals(STATUS_INCORRECT_INN_FL, getStatus("366406942712", "", ""));
    }

    @Test
    public void getStatusInvalidnumberofINNcharacters() throws IOException {
        Assert.assertEquals(STATUS_INVALID_NUMBER_OF_INN_CHARACTERS, getStatus("36640694272323423", "", ""));
    }

    @Test
    public void getStatusContainACodeOf9Digits() throws IOException {
        Assert.assertEquals(STATUS_CONTENT_OF_ONLY_9_DIGITS, getStatus("3664069397", "366401001232", ""));
    }

    @Test
    public void getStatusFormatDate() throws IOException {
        Assert.assertEquals(STATUS_DATE_IN_THE_FIRMAT_DDMMYYYY, getStatus("3664069397", "366401001", "24.13.2018"));
    }

    @Test
    public void getStatusDontMoreNowDate() throws IOException {
        Assert.assertEquals(STATUS_DONT_MORE_NOW_DATE, getStatus("3664069397", "366401001", "24.12.2025"));
    }

    @Test
    public void getStatusNotEarlier1991() throws IOException {
        Assert.assertEquals(STATUS_NOT_EARLIER_1991, getStatus("3664069397", "366401001", "24.12.1985"));
    }
}