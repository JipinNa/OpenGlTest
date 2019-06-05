package cn.com.chioy.opengltest;

public class HelloJNI {
    static {
        System.loadLibrary("HelloJNI");
    }

    private native String sayHello(String name);

    public static void main(String[] args) {
       String rs =  new HelloJNI().sayHello("World");
       System.out.println("Java类收到来自JNI的返回:" + rs);
    }
}
