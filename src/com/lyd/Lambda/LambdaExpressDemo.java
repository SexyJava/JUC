package com.lyd.Lambda;

/**
 * @Author Liuyunda
 * @Date 2021/4/19 20:47
 * @Email man021436@163.com
 * @Description: lambda express
 * 1. 拷贝小括号，写死右箭头，落地大括号
 * 2. @FunctionalInterface
 * 3. default
 * 4. 静态方法实现
 */
@FunctionalInterface
interface Foo {
    // public void sayHello();
    public int add(int x, int y);

    default int div(int x, int y){
        System.out.println("default");
        return x/y;
    }
    default int div2(int x, int y){
        System.out.println("default");
        return x/y;
    }
    public static int mv(int x, int y) {
        return x * y;
    }
    public static int mv2(int x, int y) {
        return x * y;
    }
}
public class LambdaExpressDemo {
    public static void main(String[] args) {
        /*Foo foo = new Foo() {
            @Override
            public void sayHello() {
                System.out.println("hello java01");
            }
        };
        foo.sayHello();*/

        // 如果接口中只有一个方法，那么他是函数式接口可以直接使用lambda表达式
        // Foo foo = () -> {
        //     System.out.println("hello lambda java01");
        // };
        // foo.sayHello();
        Foo foo = (int x, int y) -> {
            return x + y;
        };
        System.out.println(foo.add(1, 1));
        System.out.println(foo.div(10,5));

        System.out.println(Foo.mv(3, 5));

    }

}
