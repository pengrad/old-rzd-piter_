package test;

/**
 * Created by IntelliJ IDEA.
 * User: Evgeniy
 * Date: 17.05.2012
 * Time: 14:00:57
 * To change this template use File | Settings | File Templates.
 */
public class Test {
    public static void main(String[] args) {
        String ff = "123 Иванов а. м";
        int cashId = 0;
        try {
            cashId=Integer.parseInt(ff.substring(0,ff.indexOf(" ")));
        } catch (Exception e) {
        }
        String fio =ff.substring(ff.indexOf(" ")+1,ff.length());
        System.out.println("ww="+cashId+"    rr="+fio);
    }
}
