package utils;

import objects.table.Cell;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.*;


/**
 * Created by IntelliJ IDEA.
 * User: ЧерныхЕА
 * Date: 15.03.11
 * Time: 13:08
 * To change this template use File | Settings | File Templates.
 */
public class Utils {
    private static int key = 0;
    private static String version = "версия от 12.06.2012";

    public static String getVersion() {
        return version;
    }

    public synchronized static Integer getKey() {
        key++;
        if (key == 1000000000) key = 0;
        return key;
    }
    //

    public static String getFormatValue(Cell cell) {
        if (cell.getValue() == null)
            return "0";
        else return cell.getValue().toString();
//        } else if (cell.getValue() instanceof String || cell.getValue() instanceof Integer) {
//            return cell.getValue().toString();
//        } else if (new Double(cell.getValue().toString()).doubleValue() == new Double(cell.getValue().toString()).longValue()) {
//            return new Long(new Double(cell.getValue().toString()).longValue()).toString();
//        } else {
//            return new BigDecimal(cell.getValue().toString()).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
//        }
    }

    public static String getFormatValueForHtml(Cell cell) {
        if (cell.getValue() == null)
            return "0";
        try {
            if (Double.parseDouble(cell.getValue().toString()) < 0)
                return "<span style=\" font-size: 11px;color:red;font-size:inherit !important; font-family:inherit !important; \">" + cell.getValue().toString() + "</span>";
            else
                return cell.getValue().toString();
        } catch (Exception e) {
            return cell.getValue().toString();
        }
    }


}
