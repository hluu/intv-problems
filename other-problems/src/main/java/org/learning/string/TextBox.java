package org.learning.string;


import java.util.ArrayList;
import java.util.List;

/**
 * Create a class to print out a box surround a given string decorated with
 * a provided character.
 *
 * It has the following APIs
 * - show()
 * - addRightPadding()
 *   - this should not alter the state of the current instance of TextBox
 * - addBelowPadding()
 *   - this should not alter the state of the current instance of TextBox
 *
 * Example:
 *   new TextBox("apple", "-").show()
 *   ---------
 *   - apple -
 *   ---------
 *
 *   new TextBox("apple", "-").addRightPadding(3).show()
 *   ------------
 *   - apple    -
 *   ------------
 *
 *   new TextBox("apple", "-").addBelowPadding(2).show()
 *   ---------
 *   - apple -
 *   -       -
 *   -       -
 *   ---------
 *
 */
public class TextBox {
    public static void main(String[] args) {
        System.out.println(TextBox.class.getName());

        new TextBox("apple", '-').show();;
        new TextBox("apple", '*').show();;
        new TextBox("apple", '*').addRightPadding(2).show();;
        new TextBox("apple", '*').addBottomPadding(2).show();;
    }

    private String content;
    private char border;
    private int rightPadding = 1;
    private int bottomPadding = 0;

    private String header;
    private String body;
    protected List<String> bottomPaddingList;

    public TextBox(String content, char borderChar) {
        this(content, borderChar, 1);
    }

    public TextBox(String content, char borderChar, int rightPadding) {
        this(content, borderChar, rightPadding, 0);
    }

    public TextBox(String content, char borderChar, int rightPadding, int bottomPadding) {
        this.content = content;
        this.border = borderChar;
        this.rightPadding = rightPadding;
        this.bottomPadding = bottomPadding;
        init();
    }

    private void init() {
        int lineLen = 2 + content.length() + rightPadding + 1;
        StringBuilder headerBuf = new StringBuilder();
        for (int i = 0; i < lineLen; i++) {
            headerBuf.append(border);
        }

        header = headerBuf.toString();

        StringBuilder bodyBuf = new StringBuilder();
        bodyBuf.append(border).append(" ");
        bodyBuf.append(content);
        for (int i = 0; i < rightPadding;i++) {
            bodyBuf.append(" ");
        }
        bodyBuf.append(border);
        body = bodyBuf.toString();

        // bottom padding
        if (bottomPadding > 0) {
            StringBuilder bottomBuf = new StringBuilder();
            bottomBuf.append(border);
            int numOfSpaces = lineLen - 2;
            for (int i = 0; i < numOfSpaces; i++) {
                bottomBuf.append(" ");
            }
            bottomBuf.append(border);

            String bottomStr = bottomBuf.toString();

            bottomPaddingList = new ArrayList<>(bottomPadding);
            for (int i = 0; i < bottomPadding;i++) {
                bottomPaddingList.add(bottomStr);
            }
        }

    }

    public void show() {
        String header = buildHeader();
        String body = buildBody();

        String footer = buildFooter();

        System.out.println(header);
        System.out.println(body);
        if (bottomPaddingList != null) {
            for (String line : bottomPaddingList) {
                System.out.println(line);
            }
        }
        System.out.println(footer);
    }

    public TextBox addRightPadding(int rightPadding) {
        return new TextBox(content, border, rightPadding + 1);
    }

    public TextBox addBottomPadding(int amount) {
        return new TextBox(content, border, rightPadding + 1, amount);
    }

    private String buildHeader() {
        return header;
    }

    private String buildFooter() {
        return header;
    }

    private String buildBody() {
        return body;
    }


}
