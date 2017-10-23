package br.com.greenmile.ponto_api.common.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class DateUtilTests {

    @Test
    public void testDatasNulas() {
        Date date = null;
        Date dateMin = null;
        Date dateMax = null;
        Boolean withinRange = DateUtil.isWithinRange(dateMin, dateMax, date);

        assertFalse(withinRange);
    }

    @Test
    public void testDataNullNaFaixaDeDatas() {
        Date date = null;
        Date dateMin = DateUtil.convertStringToDate("2017-01-01");
        Date dateMax = DateUtil.convertStringToDate("2017-12-31");
        Boolean withinRange = DateUtil.isWithinRange(dateMin, dateMax, date);

        assertFalse(withinRange);
    }

    @Test
    public void testDataNaFaixaDeDatasComDateMinNull() {
        Date date = DateUtil.convertStringToDate("2017-03-01");
        Date dateMin = null;
        Date dateMax = DateUtil.convertStringToDate("2017-12-31");
        Boolean withinRange = DateUtil.isWithinRange(dateMin, dateMax, date);

        assertFalse(withinRange);
    }

    @Test
    public void testDataNaFaixaDeDatasComDateMaxNull() {
        Date date = DateUtil.convertStringToDate("2017-03-01");
        Date dateMin = DateUtil.convertStringToDate("2017-01-01");
        Date dateMax = null;
        Boolean withinRange = DateUtil.isWithinRange(dateMin, dateMax, date);

        assertFalse(withinRange);
    }

    @Test
    public void testDataDentroDaFaixaDeDatas() {
        Date date = DateUtil.convertStringToDate("2017-03-01");
        Date dateMin = DateUtil.convertStringToDate("2017-01-01");
        Date dateMax = DateUtil.convertStringToDate("2017-12-31");
        Boolean withinRange = DateUtil.isWithinRange(dateMin, dateMax, date);

        assertTrue(withinRange);
    }

    @Test
    public void testDataEHoraDentroDaFaixaDeDatas() {
        Date date = DateUtil.convertStringToDateAndHour("2017-03-01 23:59:59");
        Date dateMin = DateUtil.convertStringToDateAndHour("2017-03-01 00:00:00");
        Date dateMax = DateUtil.convertStringToDateAndHour("2017-03-01 23:59:59");
        Boolean withinRange = DateUtil.isWithinRange(dateMin, dateMax, date);

        assertTrue(withinRange);
    }

    @Test
    public void testDataIgualDataMinDentroDaFaixaDeDatas() {
        Date date = DateUtil.convertStringToDate("2017-01-01");
        Date dateMin = DateUtil.convertStringToDate("2017-01-01");
        Date dateMax = DateUtil.convertStringToDate("2017-12-31");
        Boolean withinRange = DateUtil.isWithinRange(dateMin, dateMax, date);

        assertTrue(withinRange);
    }

    @Test
    public void testDataIgualDataMaxDentroDaFaixaDeDatas() {
        Date date = DateUtil.convertStringToDate("2017-12-31");
        Date dateMin = DateUtil.convertStringToDate("2017-03-01");
        Date dateMax = DateUtil.convertStringToDate("2017-12-31");
        Boolean withinRange = DateUtil.isWithinRange(dateMin, dateMax, date);

        assertTrue(withinRange);
    }

    @Test
    public void testDatasIguaisComDateMax() {
        Date date = DateUtil.convertStringToDate("2017-12-31");
        Date dateMin = DateUtil.convertStringToDate("2017-12-31");
        Date dateMax = DateUtil.convertStringToDate("2017-12-31");
        Boolean withinRange = DateUtil.isWithinRange(dateMin, dateMax, date);

        assertTrue(withinRange);
    }

    @Test
    public void testDatasIguaisComDateMin() {
        Date date = DateUtil.convertStringToDate("2017-01-01");
        Date dateMin = DateUtil.convertStringToDate("2017-01-01");
        Date dateMax = DateUtil.convertStringToDate("2017-01-01");
        Boolean withinRange = DateUtil.isWithinRange(dateMin, dateMax, date);

        assertTrue(withinRange);
    }

    @Test
    public void testDataForaDoRangeDateMinDateMax() {
        Date date = DateUtil.convertStringToDate("2018-01-01");
        Date dateMin = DateUtil.convertStringToDate("2017-01-01");
        Date dateMax = DateUtil.convertStringToDate("2017-12-31");
        Boolean withinRange = DateUtil.isWithinRange(dateMin, dateMax, date);

        assertFalse(withinRange);
    }

    @Test
    public void testDataEHoraForaDoRangeDateMinDateMax() {
        Date date = DateUtil.convertStringToDateAndHour("2018-01-01 00:00:00");
        Date dateMin = DateUtil.convertStringToDateAndHour("2017-12-31 23:59:58");
        Date dateMax = DateUtil.convertStringToDateAndHour("2017-12-31 23:59:59");
        Boolean withinRange = DateUtil.isWithinRange(dateMin, dateMax, date);

        assertFalse(withinRange);
    }
}
