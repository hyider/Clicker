package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Stack;

public class Test extends JFrame {
    public static void main(String[] args) {
        Stack stack = new Stack();

        stack.push("0");
        stack.push("1");

        while(!stack.empty()) {
            System.out.println(stack.pop());
        }

    }
}
