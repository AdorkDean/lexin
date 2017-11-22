import com.caipiao.data.service.CountMoney;
import com.caipiao.utils.PlayType;
import com.caipiao.utils.TryStatic;
import org.apache.commons.lang.ArrayUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by liujie on 2017/3/8.
 */
public class LotteryTest {
    public static void main(String[] args) {


        String moneys = "100";
        Double money = Double.valueOf(TryStatic.StrToDouble(moneys, 0.0D));
        String buymons = "-100";
        Double buymon = Double.valueOf(TryStatic.StrToDouble(buymons, 0.0D));
        String baos = "100";
        Double bao = Double.valueOf(TryStatic.StrToDouble(baos, 0.0D));

        if (money.doubleValue() < 0 || buymon.doubleValue() < 0 || bao.doubleValue() < 0) {
            System.out.println("数据不能小于负数");

        }


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(new Date());
        System.out.println(format);


        String num = "116:02$02,03,04,05,06,07,08,09,10,11:10";
//        boolean regex = CheckUtil.Regex("[0-1][0-9](,[0-1][0-9]){0,6}[$][0-1][0-9](,[0-1][0-9]){0,10}", num);
        int gd11x5 = CountMoney.getAllZhushu(num, "Gd11x5");
        System.out.println("结果：" + gd11x5);


        String[] split = num.split("#");
        for (String str : split) {
            String type = str.split(":")[0];
            if (ArrayUtils.contains(PlayType._11x5_DanTuo, type) ||
                    ArrayUtils.contains(PlayType._Def_DanTuo, type) ||
                    ArrayUtils.contains(PlayType._Ssc_DanTuo, type)
                    ) {

                String[] $s = str.split("\\$");


                String[] split1 = $s[0].split(":");
                String[] split2 = $s[1].split(":");

                String dan = split1[1];
                String tuo = split2[0];
                if (tuo.contains(dan)) {
                    System.out.println("数据有误");
                }
            }

        }


    }
}
