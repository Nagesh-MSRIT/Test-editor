import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;

public class TextEditor extends JFrame implements ActionListener {

                                class MyHighlightPainter extends DefaultHighlighter.DefaultHighlightPainter {

                                                                public MyHighlightPainter(Color c) {
                                                                                                super(c);

                                                                }

                                }

                                Highlighter.HighlightPainter myHighlightPainter = new MyHighlightPainter(Color.red);

                                JTextArea textArea;
                                JScrollPane scrollPane;

                                // replace frame
                                JFrame replaceFrame;
                                JButton replaceButton;
                                // menubar
                                JMenuBar menuBar;
                                JMenu fileMenu;
                                JMenu editMenu;
                                // edit
                                JMenuItem deleteItem;
                                JMenuItem cutItem;
                                JMenuItem replaceItem;
                                JMenuItem copyItem;
                                JMenuItem pasteItem;
                                JMenuItem findNextItem;
                                JMenuItem selectAllItem;
                                JMenuItem goToItem;
                                // file
                                JMenuItem openItem;
                                JMenuItem saveItem;
                                JMenuItem exitItem;

                                JTextField txt;
                                JTextField txt2;

                                // find next functionality
                                JFrame findNextFrame;
                                JButton findNextButton;
                                JTextField findTextField;

                                // find next functionality
                                JFrame goToFrame;
                                JButton goToButton;
                                JTextField goToField;

                                JLabel label1 = new JLabel("Test");

                                int pos = 0;
                                int pos1 = 0;

                                TextEditor() {
                                                                this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                                                this.setTitle("Text Editor");
                                                                this.setSize(480, 500);// Auto-generated
                                                                this.setLayout(new FlowLayout()); // constructor
                                                                this.setLocationRelativeTo(null);

                                                                textArea = new JTextArea();

                                                                textArea.setLineWrap(true);
                                                                textArea.setWrapStyleWord(true);
                                                                textArea.setFont(new Font("Arial", Font.PLAIN, 20));

                                                                scrollPane = new JScrollPane(textArea);
                                                                scrollPane.setPreferredSize(new Dimension(450, 450));
                                                                scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

                                                                // menu bar
                                                                menuBar = new JMenuBar();
                                                                fileMenu = new JMenu("File");
                                                                editMenu = new JMenu("Edit");
                                                                // edit items
                                                                deleteItem = new JMenuItem("delete");
                                                                cutItem = new JMenuItem("cut");
                                                                pasteItem = new JMenuItem("paste");
                                                                copyItem = new JMenuItem("copy");
                                                                selectAllItem = new JMenuItem("select all");
                                                                replaceItem = new JMenuItem("replace");
                                                                findNextItem = new JMenuItem("find next");
                                                                goToItem = new JMenuItem("go to");

                                                                // file items
                                                                openItem = new JMenuItem("open");
                                                                saveItem = new JMenuItem("save");
                                                                exitItem = new JMenuItem("exit");

                                                                // file
                                                                openItem.addActionListener(this);
                                                                saveItem.addActionListener(this);
                                                                exitItem.addActionListener(this);
                                                                // edit
                                                                deleteItem.addActionListener(this);
                                                                cutItem.addActionListener(this);
                                                                copyItem.addActionListener(this);
                                                                pasteItem.addActionListener(this);
                                                                replaceItem.addActionListener(this);
                                                                selectAllItem.addActionListener(this);
                                                                findNextItem.addActionListener(this);
                                                                goToItem.addActionListener(this);

                                                                // file
                                                                fileMenu.add(openItem);
                                                                fileMenu.add(saveItem);
                                                                fileMenu.add(exitItem);

                                                                // edit
                                                                editMenu.add(copyItem);
                                                                editMenu.add(pasteItem);
                                                                editMenu.add(cutItem);
                                                                editMenu.add(selectAllItem);
                                                                editMenu.add(findNextItem);
                                                                editMenu.add(goToItem);
                                                                editMenu.add(replaceItem);
                                                                editMenu.add(deleteItem);

                                                                menuBar.add(fileMenu);
                                                                menuBar.add(editMenu);

                                                                this.setJMenuBar(menuBar);
                                                                this.add(scrollPane);
                                                                this.setVisible(true);

                                }

                                public void Highlighter(JTextComponent txtComp, String pattern) {

                                                                try {
                                                                                                Highlighter highlighter = txtComp.getHighlighter();
                                                                                                Document doc = txtComp.getDocument();
                                                                                                String textString = doc.getText(0, doc.getLength());
                                                                                                int pos = 0;

                                                                                                while ((pos = textString.toUpperCase().indexOf(pattern.toUpperCase(),
                                                                                                                                                                pos)) >= 0) {

                                                                                                                                highlighter.addHighlight(pos, pos + pattern.length(), myHighlightPainter);
                                                                                                                                pos += pattern.length();
                                                                                                }
                                                                } catch (Exception e) {
                                                                                                System.out.println("bugggggggggggggggggggggg");
                                                                }

                                }

                                @Override
                                public void actionPerformed(ActionEvent e) {
                                                                if (e.getSource() == openItem) {
                                                                                                JFileChooser fileChooser = new JFileChooser();
                                                                                                fileChooser.setCurrentDirectory(new File("."));

                                                                                                int response = fileChooser.showOpenDialog(null);

                                                                                                if (response == JFileChooser.APPROVE_OPTION) {
                                                                                                                                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                                                                                                                                Scanner fileIn = null;

                                                                                                                                try {

                                                                                                                                                                fileIn = new Scanner(file);
                                                                                                                                                                if (file.isFile()) {
                                                                                                                                                                                                while (fileIn.hasNextLine()) {
                                                                                                                                                                                                                                String line = fileIn.nextLine() + "\n";
                                                                                                                                                                                                                                textArea.append(line);
                                                                                                                                                                                                }
                                                                                                                                                                }
                                                                                                                                } catch (FileNotFoundException e1) {
                                                                                                                                                                // TODO
                                                                                                                                                                // Auto-generated
                                                                                                                                                                // catch
                                                                                                                                                                // block
                                                                                                                                                                e1.printStackTrace();
                                                                                                                                } finally {
                                                                                                                                                                fileIn.close();
                                                                                                                                }
                                                                                                }
                                                                }
                                                                if (e.getSource() == saveItem) {
                                                                                                JFileChooser fileChooser = new JFileChooser();
                                                                                                fileChooser.setCurrentDirectory(new File("."));

                                                                                                int response = fileChooser.showSaveDialog(null);

                                                                                                if (response == JFileChooser.APPROVE_OPTION) {
                                                                                                                                File file;
                                                                                                                                PrintWriter fileOut = null;
                                                                                                                                file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                                                                                                                                try {

                                                                                                                                                                fileOut = new PrintWriter(file);
                                                                                                                                                                fileOut.println(textArea.getText());
                                                                                                                                } catch (FileNotFoundException e1) {
                                                                                                                                                                e1.printStackTrace();
                                                                                                                                } finally {
                                                                                                                                                                fileOut.close();
                                                                                                                                }
                                                                                                }

                                                                }
                                                                if (e.getSource() == deleteItem) {

                                                                                                textArea.setText(textArea.getText().replace(textArea.getSelectedText(),
                                                                                                                                                                ""));
                                                                }
                                                                if (e.getSource() == cutItem) {

                                                                                                textArea.cut();
                                                                }
                                                                if (e.getSource() == copyItem) {
                                                                                                textArea.copy();
                                                                }
                                                                if (e.getSource() == pasteItem) {
                                                                                                textArea.paste();
                                                                }
                                                                if (e.getSource() == findNextItem) {
                                                                                                findNextFrame = new JFrame("Find text");
                                                                                                findNextButton = new JButton("find text");

                                                                                                findNextFrame.setSize(300, 150);
                                                                                                findNextFrame.setVisible(true);
                                                                                                findNextFrame.setLayout(new FlowLayout());
                                                                                                findNextFrame.setPreferredSize(new Dimension(300, 100));
                                                                                                findNextFrame.setLocationRelativeTo(null);
                                                                                                findNextButton.addActionListener(this);

                                                                                                findTextField = new JTextField(20);

                                                                                                findNextFrame.add(findTextField);
                                                                                                findNextFrame.add(findNextButton);
                                                                }
                                                                if (e.getSource() == selectAllItem) {
                                                                                                textArea.selectAll();
                                                                }
                                                                if (e.getSource() == replaceItem) {
                                                                                                replaceFrame = new JFrame("replace frame");
                                                                                                replaceButton = new JButton("REPLACE");
                                                                                                // replace functions
                                                                                                replaceFrame.setSize(300, 150);
                                                                                                replaceFrame.setVisible(true);
                                                                                                replaceFrame.setLayout(new FlowLayout());
                                                                                                replaceFrame.setPreferredSize(new Dimension(300, 100));
                                                                                                replaceFrame.setLocationRelativeTo(null);
                                                                                                replaceButton.addActionListener(this);

                                                                                                txt = new JTextField(20);
                                                                                                txt2 = new JTextField(20);
                                                                                                JLabel label1 = new JLabel("Test");
                                                                                                label1.setText("replace with");
                                                                                                txt.setSize(100, 50);
                                                                                                txt2.setSize(100, 50);
                                                                                                replaceFrame.add(txt);
                                                                                                replaceFrame.add(label1);
                                                                                                replaceFrame.add(txt2);
                                                                                                replaceFrame.add(replaceButton);

                                                                }
                                                                if (e.getSource() == replaceButton) {
                                                                                                String toBeReplaced = txt.getText();
                                                                                                String replaceWith = txt2.getText();
                                                                                                textArea.setText(textArea.getText().replace(toBeReplaced, replaceWith));

                                                                }
                                                                if (e.getSource() == findNextButton) {
                                                                                                try {
                                                                                                                                Highlighter highlighter = textArea.getHighlighter();
                                                                                                                                Document doc = textArea.getDocument();
                                                                                                                                String textString = doc.getText(0, doc.getLength());

                                                                                                                                if ((pos = textString.toUpperCase().indexOf(findTextField.getText()
                                                                                                                                                                                                .toUpperCase(),
                                                                                                                                                                                                pos)) >= 0) {

                                                                                                                                                                highlighter.addHighlight(pos, pos + findTextField.getText().length(),
                                                                                                                                                                                                                                myHighlightPainter);
                                                                                                                                                                pos += findTextField.getText().length();
                                                                                                                                }
                                                                                                } catch (Exception ex) {
                                                                                                                                System.out.println("bugggggggggggggggggggggg");
                                                                                                }
                                                                }
                                                                if (e.getSource() == goToItem) {
                                                                                                goToFrame = new JFrame("go to");
                                                                                                goToButton = new JButton("go to");

                                                                                                goToFrame.setSize(300, 150);
                                                                                                goToFrame.setVisible(true);
                                                                                                goToFrame.setLayout(new FlowLayout());
                                                                                                goToFrame.setPreferredSize(new Dimension(300, 100));
                                                                                                goToFrame.setLocationRelativeTo(null);
                                                                                                goToButton.addActionListener(this);

                                                                                                goToField = new JTextField(20);

                                                                                                goToFrame.add(goToField);
                                                                                                goToFrame.add(goToButton);
                                                                }
                                                                if (e.getSource() == goToButton) {
                                                                                                try {

                                                                                                                                Document doc = textArea.getDocument();
                                                                                                                                String textString = doc.getText(0, doc.getLength());

                                                                                                                                if ((pos1 = textString.toUpperCase().indexOf(goToField.getText().toUpperCase(),
                                                                                                                                                                                                pos1)) >= 0) {

                                                                                                                                                                textArea.select(pos1, pos1 + goToField.getText().length());
                                                                                                                                                                pos1 += goToField.getText().length();
                                                                                                                                }
                                                                                                } catch (Exception ex) {
                                                                                                                                System.out.println("bugggggggggggggggggggggg");
                                                                                                }
                                                                }
                                                                if (e.getSource() == exitItem) {

                                                                                                System.exit(0);
                                                                }

                                }

}
