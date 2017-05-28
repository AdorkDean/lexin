/**
 * Created by liujie on 2017/3/1.
 */
public class VolatileTest {

    public static volatile int number;

    public static void doSomething(){

        for (int i = 0 ; i < 10; i ++) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    VolatileTest.count();

                }
            }).start();

//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    ++ number ;
//                }
//            }).start();

            System.out.println(number + "xunhuan ");
        }



    }

    private static synchronized void count() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        number ++ ;
    }

    public int getNumber() {
        return number;
    }



    public static void main(String[] args) {

        int count = 0;
        VolatileTest volatileTest = new VolatileTest();
//        int number = volatileTest.getNumber();

        while (true){


            VolatileTest.doSomething();

//            System.out.println(number + "number");number

            if(number == 10){
                break;
            }
            number = 0;
            count ++;
        }


        System.out.println("------------");
        System.out.println(number);
        System.out.println(count);

    }
}
