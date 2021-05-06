package com.lyd.stream;

import com.sun.deploy.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
class User{
    private Integer id;
    private String username;
    private int age;

}
/**
 * @Author Liuyunda
 * @Date 2021/5/6 21:09
 * @Email man021436@163.com
 * @Description: 请按照给出数据没找出同时满足一下条件的用户，也即以下条件全部满足
 *               偶数ID且年龄大于24且用户名转为大写且用户名字母倒排序
 *               只输出一个用户名字
 */
public class StreamDemo {
    public static void main(String[] args) {
        User user = new User(11,"a",23);
        User user1 = new User(12,"b",24);
        User user2 = new User(13,"c",22);
        User user3 = new User(14,"d",28);
        User user4 = new User(15,"e",26);
        List<User> users = Arrays.asList(user, user1, user2, user3, user4);
        // 函数型接口
        Function<String ,Integer> function = s->{return s.length();};
        System.out.println(function.apply("abc"));
        // 断定型接口
        Predicate<String> predicate = s -> {return s.isEmpty(); };
        System.out.println(predicate.test("abc"));
        // 消费型接口
        Consumer<String> consumer = s->{ System.out.println(s); };
        consumer.accept("abc");
        // 供给型接口
        Supplier<String> supplier = ()->{return "abc";};
        System.out.println(supplier.get());

    }
}
