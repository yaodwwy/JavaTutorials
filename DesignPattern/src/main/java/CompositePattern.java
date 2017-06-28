/**
 * Created by Adam Yao on 2017/6/7.
 * 组合模式
 * @Link https://en.wikipedia.org/wiki/Composite_pattern
 */

import java.util.ArrayList;
import java.util.List;

/**
 * "Component 组成零件"
 */
interface Graphic {
    //Prints the graphic.
    public void print();
}

/**
 * "Composite 混合"
 */
class CompositeGraphic implements Graphic {
    private String name;
    //Collection of child graphics.
    private List<Graphic> childGraphics = new ArrayList<Graphic>();

    CompositeGraphic(String name) {
        this.name = name;
    }

    //Prints the graphic.
    public void print() {
        System.out.println("我是组合图形： "+name);
        for (Graphic graphic : childGraphics) {
                graphic.print();
        }
    }

    //Adds the graphic to the composition.
    public void add(Graphic graphic) {
        childGraphics.add(graphic);
    }

    //Removes the graphic from the composition.
    public void remove(Graphic graphic) {
        childGraphics.remove(graphic);
    }
}

/**
 * "Leaf 叶端"
 */
class Ellipse implements Graphic {
    private String name;

    //Prints the graphic.
    Ellipse(String name) {
        this.name = name;
    }

    public void print() {
        System.out.println("我是椭圆:"+ name);
    }
}

/**
 * Client
 */
public class CompositePattern {

    public static void main(String[] args) {

        //Initialize four ellipses 初始化四个椭圆

        Ellipse ellipse1 = new Ellipse("A");
        Ellipse ellipse2 = new Ellipse("B");
        Ellipse ellipse3 = new Ellipse("C");
        Ellipse ellipse4 = new Ellipse("D");

        //Initialize three composite graphics 初始化三个复合图形
        CompositeGraphic graphic = new CompositeGraphic("1");
        CompositeGraphic graphic1 = new CompositeGraphic("2");
        CompositeGraphic graphic2 = new CompositeGraphic("3");

        //Composes the graphics 组合图形
        graphic1.add(ellipse1);
        graphic1.add(ellipse2);
        graphic1.add(ellipse3);

        graphic2.add(ellipse4);

        graphic.add(graphic1);
        graphic.add(graphic2);

        //Prints the complete graphic (four times the string "Ellipse").
        //打印完整的图形（“椭圆”）
        graphic.print();
    }
}