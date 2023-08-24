package ru.netology.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.LoginPage;


import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.SQLHelper.cleanDataBase;

public class BankLoginTest {

    @AfterAll
    static void teardown(){

        cleanDataBase();
    }

    @Test

    void shouldSuccessfulllLogin(){

        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfoTestData();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.verifyVerificationPageVisiblity();
        var verificationCode = SQLHelper.getVerificationCode();
        verificationPage.validVerify(verificationCode.getCode());
    }
}
