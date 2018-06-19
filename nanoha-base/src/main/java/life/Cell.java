package life;

/**
 * 细胞
 * 
 * @Title:
 * @Description:
 * @Author:Mr.LiMo
 * @Since:2017年1月12日
 * @Version:1.0
 */
public class Cell {
    String key;
    int x;
    int y;
    boolean living;
    String show;

    Cell(int x, int y, boolean living) {
        this.x = x;
        this.y = y;
        this.key = x + "" + y;
        this.living = living;
        this.show = living ? "o" : "+";
    }
}
