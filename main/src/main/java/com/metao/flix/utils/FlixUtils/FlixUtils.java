package com.metao.flix.utils.FlixUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by matio on 01/02/18.
 */

public class FlixUtils {

    public static String timestampToTime(Long timestamp) {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date(timestamp * 1000);
        return dateFormat.format(date);
    }
}
