package org.example;

import java.sql.Date;
import java.time.LocalDate;

public enum SubjectType {
    DOCUMENT("Документ", 14),
    METERING_DEVICE_VERIFICATION_CERTIFICATE("Паспорт проверки прибора учёта", 30),
    CERTIFICATE("Удостоверение", 0),
    PASSWORD("Пароль", 2);

    private String stringValue;
    private int warningDays;

    private SubjectType(String stringValue, int warningDays) {
        this.stringValue = stringValue;
        this.warningDays = warningDays;
    }

    public Date getExpirationDate() {
        LocalDate currentDate = LocalDate.now();
        LocalDate currentDatePlusWarningsDays = currentDate.plusDays(getWarningDays());

        Date fromattedDate = Date.valueOf(currentDatePlusWarningsDays);
        return fromattedDate;
    }

    public String getStringValue() {
        return stringValue;
    }

    public int getWarningDays() {
        return warningDays;
    }
}
