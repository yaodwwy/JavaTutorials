/**
 * Created by Adam Yao on 2017/6/13.
 * 原型模式
 * @Link https://en.wikipedia.org/wiki/Prototype_pattern
 */
/** Prototype Class **/
class Cookie implements Cloneable {

    public Object clone() throws CloneNotSupportedException
    {
        //In an actual implementation of this pattern you would now attach references to
        //the expensive to produce parts from the copies that are held inside the prototype.
        return (Cookie) super.clone();
    }
}

/** Concrete Prototypes to clone **/
class CoconutCookie extends Cookie { }

/** Client Class**/
public class PrototypePattern
{

    private Cookie cookie;//cookie必须是可复制的

    public PrototypePattern(Cookie cookie) {
        this.cookie = cookie;
    }

    public Cookie makeCookie()
    {
        try
        {
            return (Cookie) cookie.clone();
        } catch (CloneNotSupportedException e)
        {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String args[]){
        Cookie tempCookie =  null;
        Cookie prot = new CoconutCookie();
        PrototypePattern cm = new PrototypePattern(prot); //设置原型
        for(int i=0; i<100; i++)
            tempCookie = cm.makeCookie();//通过复制原型返回多个cookie
    }
}