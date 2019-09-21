package me.reolcharm.ad.util;

import me.reolcharm.ad.exception.AdException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.time.DateUtils;

import java.text.ParseException;
import java.util.Date;

/**
 * @author K1
 * @date 2019/9/20 11:40
 * Description: RT
 */
public class CommonUtils {
    private static String[] parsePatterns = {"yy-MM-dd", "yy/MM/dd", "yy.MM.dd", "yy MM dd", "yy年MM月dd日"};

    /**
     * <p>@Description: JUST A DEMO, should receive a token
     * from Authentication and Authorization System</p>
     *
     * @param value Str need to be encrypted
     * @return user token <br>
     * @author K1
     * @date 2019/9/21 10:52
     */
    public static String md5(String value) {
        return DigestUtils.md5Hex(value).toUpperCase();
    }

    /**
     * <b>Desc</b>: parse date which is String type from client or other.
     *
     * @param strDate to be parsed
     * @return Date - the string after parsed
     * @throws AdException
     * @author K1
     * @date 2019/9/21 10:49
     */
    public static Date parseStringDate(String strDate) throws AdException {
        try {
            return DateUtils.parseDate(strDate, parsePatterns);
        } catch (ParseException e) {
            throw new AdException(e.getMessage());
        }
    }
}
