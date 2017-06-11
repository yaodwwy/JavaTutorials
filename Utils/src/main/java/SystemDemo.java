public class SystemDemo {

// Clone方法
        //要克隆对象，必须先做两步:1.

        //覆盖对象的clone()方法; 2.实现空的Cloneable接口

        public class Clone1 implements Cloneable {
            public Object clone() throws CloneNotSupportedException {
                return super.clone();
            }
        }

    public static void main(String[] args) throws Exception{
        // Finalize方法
        Object f = new Object() {
            public void finalize() {
                System.out.println("Running finalize()");
            }
        };
        Runtime.getRuntime().

                addShutdownHook(new Thread() {
                    public void run () {
                        System.out.println("Running Shutdown Hook");
                    }
                });
        //在调用System.exit(0);的时候，这两个方法将被执行

    }
}
