package com.spring.service.domain;

import java.util.*;
import java.io.*;

public class WordLadder {

    private static Set<String> dict;//�ֵ�
    private static Set<String> record;//��¼�滻����µ��ʣ������������ѭ��
    private static Stack<String> result;    //WordLadder�Ľ��
    private static Queue<Stack<String>> ladder;//���ڴ洢ladder�Ķ���
    private static String word1;
    private static String word2;

    public WordLadder(){
        this.dict = new HashSet<>();//�ֵ�
        this.record = new HashSet<>();//��¼�滻����µ��ʣ������������ѭ��
        this.result = new Stack<>();    //WordLadder�Ľ��
        this.ladder = new LinkedList<>();//���ڴ洢ladder�Ķ��
    }

    public void setWord(String w1, String w2){
        word1 = w1;
        word2 = w2;
        return;
    }

    public String getLadder(){
        String ss;
        if(result.empty()){
            ss = "No ladder found from " + word2 + " back to " + word1 + ":\n";
            return ss;
        }
        ss = "A ladder from " + word2 + " back to " + word1 + ":\n";
        while(!result.empty()){
            ss += result.pop() ;
            ss += " ";
        }
        ss += "\n";
        return ss;
    }

    public void run() {
        setDict("dictionary.txt");

        Stack<String> ss = new Stack<>();
        ss.push(word1);
        ladder.offer(ss);
        record.add(word1);

        findLadder(word2);
        ladder = new LinkedList<>();
        record = new HashSet<>();

        //���û����õ������յ�ĵ���
        /*while (true) {

            System.out.println("Word #1 (or Enter to quit): ");
            word1 = in.nextLine().toLowerCase();
            if (word1.equals("")) break;

            System.out.println("Word #2 (or Enter to quit): ");
            word2 = in.nextLine().toLowerCase();
            //�Ų鲻�淶����
            if (word2.equals("")) break;
            if (word1.equals(word2)) {
                System.out.println("The two words must be different.");
                continue;
            }
            //��ʼ��ladder����
            Stack<String> ss = new Stack<>();
            ss.push(word1);
            ladder.offer(ss);
            record.add(word1);

            //������ҽ��
            boolean find = findLadder(word2);
            ladder = new LinkedList<>();
            record = new HashSet<>();
            if (find) {
                System.out.println("A ladder from " + word2 + " back to " + word1 + " :");
                while (!result.empty())
                    System.out.print(result.pop() + " ");
                System.out.println("\n");
            } else
                System.out.println("No word ladder found from " + word2 + " back to " + word1 + " .\n");
        }
        System.out.println("Have a nice day!");*/
    }

    private static boolean findLadder(String word2) {
        //System.out.println("ladder size "+ladder.size());
        while (ladder.size() > 0) {
            int size = ladder.size();
            for (int i = 0; i < size; i++) {
                Stack<String> temp = ladder.poll();
                String word = temp.peek();

                String ww1 = "";
                String ww2 = "";
                String ww3 = "";

                //��������ʷֱ�������ֱ仯������ɾ����
                int len = word.length();
                for (int j = 0; j <= len; j++) {
                    if (j != len)
                        ww3 = word.substring(0, j) + word.substring(j + 1);
                    ;//ɾ����ĸ
                    boolean cc = check(ww3, word2, temp);
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        ww2 = word.substring(0, j) + ch + word.substring(j);//�����ĸ
                        if (j != len)
                            ww1 = word.substring(0, j) + ch + word.substring(j + 1);    //�ı���ĸ

                        if (check(ww1, word2, temp) ||
                                check(ww2, word2, temp) ||
                                cc)
                            return true;
                    }
                }
            }
        }
        System.out.println("false!");
        return false;
    }

    public static boolean check(String word, String target, Stack<String> temp) {
        //System.out.println(word + "  " +target);
        if (record.contains(word))
            return false;
        if (word.equals(target)) {
            result = (Stack<String>)temp.clone();
            result.push(word);
            //ladder = queue<stack<string>>();//����
            return true;
        } else if (dict.contains(word)) //�µ�����û���ֹ�����Ч����
        //���ⲿ��wordladder��ӵ�ladder������
        {
            record.add(word);
            Stack<String> save = (Stack<String>)temp.clone();
            save.push(word);
            ladder.offer(save);
        }
        return false;
    }

    private static boolean setDict(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            // һ�ζ���һ�У�ֱ������nullΪ�ļ�����
            while ((tempString = reader.readLine()) != null) {
                dict.add(tempString);
            }
            reader.close();
            return true;
        } catch (IOException e) {
            //e.printStackTrace();
            return false;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }
}
